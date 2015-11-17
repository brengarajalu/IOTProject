package com.cmpe273.controller;


import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe273.dao.OffersDAO;
import com.cmpe273.dao.UserDAO;
import com.cmpe273.database.DatabaseConnection;
import com.cmpe273.kafka.KafkaProducer;
import com.cmpe273.model.MailRequest;
import com.cmpe273.model.Offer;
import com.cmpe273.model.User;

@RestController
@RequestMapping("/theshop/api/v1")
public class ShoppingController {
    
    @Autowired
    private OffersDAO offersDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private KafkaProducer producer;
    
    private static DatabaseConnection dbConnection = new DatabaseConnection();
    static{
        dbConnection.setDbConnection();
        
    }
    
	@RequestMapping(value="/getoffers/beaconid/{beaconId}/rss/{rss}",method = RequestMethod.GET)
	public List<Document> getOffers(@PathVariable String beaconId,@PathVariable int rss,@RequestParam("uid") String userId){
        return offersDAO.getOffers(beaconId,rss,userId);
        
	}
	
	@RequestMapping(value="/postoffer",method = RequestMethod.POST)
	public void postOffers(@RequestBody Offer offer){
         offersDAO.postOffer(offer);
        
	}
	
	@RequestMapping(value="/updateoffer",method = RequestMethod.PUT)
	public void updateOffer(@RequestBody Offer offer){
         offersDAO.updateOffer(offer);
        
	}
	
	@RequestMapping(value="/removeoffer",method = RequestMethod.DELETE)
	public void removeOffers(@RequestBody Offer offer){
         offersDAO.removeOffer(offer);
        
	}
	
	@RequestMapping(value="/getalloffers",method = RequestMethod.GET)
	public List<Document> getAllOffers(){
         return offersDAO.getAllOffers();
        
	}

    @RequestMapping(value="/getusers/{userId}",method = RequestMethod.GET)
    public Document getUsers(@PathVariable String userId){
        Document doc =  userDAO.getUsers(userId);
        return doc;
    }

    @RequestMapping(value="/createusers", method = RequestMethod.POST)
    public void createUsers(@RequestBody User user){
        userDAO.postUser(user);
    }

    @RequestMapping(value="/updateusers/{userId}", method = RequestMethod.PUT)
    public void updateUsers(@PathVariable String userId, @RequestBody User user){
        userDAO.updateUser(user, userId);
    }
    
    @RequestMapping(value="/sendmail",method = RequestMethod.POST)
	public void sendMail(@RequestBody MailRequest mailRequest){
    	producer.sendMessage(mailRequest);
	}
}

