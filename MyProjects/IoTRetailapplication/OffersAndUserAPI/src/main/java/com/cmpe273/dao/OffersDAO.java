package com.cmpe273.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Component;

import com.cmpe273.database.DatabaseConnection;
import com.cmpe273.model.Offer;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
/**
 * Created by Ram on 30/04/15.
 */
@Component
public class OffersDAO {
	
	private static DatabaseConnection dbConnection = new DatabaseConnection();
    private MongoCollection<Document> offersCollection;
    private MongoCollection<Document> usersCollection;

    static{
        dbConnection.setDbConnection();
    }
    
    public void postOffer(Offer offer){
    	this.offersCollection = dbConnection.getCollection("offers");
    	Document offerDocument=new Document();
    	offerDocument.append("beaconId", offer.getBeaconId());
    	offerDocument.append("RSSmin", offer.getRssMin());
    	offerDocument.append("RSSmax", offer.getRssMax());
    	offerDocument.append("category", offer.getCategory());
    	offerDocument.append("offers", offer.getOffers());
     	offersCollection.insertOne(offerDocument);
    }
    
    public void updateOffer(Offer offer){
    	this.offersCollection = dbConnection.getCollection("offers");
    	Document offerDocument=new Document();
    	offerDocument.append("beaconId", offer.getBeaconId());
    	offerDocument.append("RSSmin", offer.getRssMin());
    	offerDocument.append("RSSmax", offer.getRssMax());
    	offerDocument.append("category", offer.getCategory());
    	offersCollection.deleteOne(offerDocument);
    	offerDocument.append("offers", offer.getOffers());
     	offersCollection.insertOne(offerDocument);
    }
    public List<Document> getOffers(String beaconId, int rss,String userId){
    	this.offersCollection = dbConnection.getCollection("offers");
    	List<String> userDisintrests=getUserDisintrests(userId);
    	Document userDetails=new Document("category",new Document("$not", new Document("$in",userDisintrests)));
    	userDetails.append("beaconId",beaconId);
    	userDetails.append("RSSmin", new Document("$lte",rss));
    	userDetails.append("RSSmax", new Document("$gt",rss));
        return this.offersCollection.find(userDetails).into(new ArrayList<Document>());
    }
    
    public void removeOffer(Offer offer){
    	this.offersCollection = dbConnection.getCollection("offers");
    	Document offerDocument=new Document();
    	offerDocument.append("beaconId", offer.getBeaconId());
    	offerDocument.append("RSSmin", offer.getRssMin());
    	offerDocument.append("RSSmax", offer.getRssMax());
    	offerDocument.append("category", offer.getCategory());
     	offersCollection.deleteOne(offerDocument);
    }
    
    public List<Document> getAllOffers(){
    	this.offersCollection = dbConnection.getCollection("offers");
    	Document userDetails=new Document();
        return this.offersCollection.find(userDetails).into(new ArrayList<Document>());
    }
    
    @SuppressWarnings("unchecked")
	private List<String> getUserDisintrests(String uid){
    	this.usersCollection=dbConnection.getCollection("users");
    	Document userDetails=new Document("userId",uid);
    	FindIterable<Document> userIteratable=usersCollection.find(userDetails).limit(1);
    	Document user = userIteratable.first();
    	return (List<String>) user.get("disinterests");
    	
    }

}

