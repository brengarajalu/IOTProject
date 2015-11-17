package com.cmpe273.dao;

import com.cmpe273.database.DatabaseConnection;
import com.cmpe273.model.User;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by aditi on 10/05/15.
 */
@Component
public class UserDAO {
    private static DatabaseConnection dbConnection = new DatabaseConnection();
    private MongoCollection<Document> usersCollection;

    static{
        dbConnection.setDbConnection();
    }

    public Document getUsers(String id){
        System.out.println("Check# 1");
        this.usersCollection = dbConnection.getCollection("users");
        Document user=new Document("userId",id);
        Bson projection = new Document("_id",0);
        Document doc =usersCollection.find(user).projection(projection).first();
        return doc;
    }

    public void postUser(User user){
        this.usersCollection = dbConnection.getCollection("users");
        Document userDoc=new Document();
        userDoc.append("userId", user.getUserId());
        userDoc.append("userName", user.getUserName());

        Date today = new Date();
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        userDoc.append("created_at", parser.format(today));
        userDoc.append("interests", user.getInterests());
        userDoc.append("disinterests", user.getDisinterests());
        usersCollection.insertOne(userDoc);
    }

    public void updateUser(User user, String id){
        this.usersCollection = dbConnection.getCollection("users");
        Document filter=new Document("userId", id);
        Document oldUser = usersCollection.find(filter).first();
        Document newUser = new Document("_id", oldUser.getObjectId("_id"));
        newUser.append("userId", id);

        if(user.getUserName()!=null)
            newUser.append("userName", user.getUserName());
        else
            newUser.append("userName", (String)oldUser.get("userName"));

        Date today = new Date();
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        newUser.append("created_at", parser.format(today));

        if(user.getInterests()!=null)
            newUser.append("interests", user.getInterests());
        else
            newUser.append("interests", (List<String>)oldUser.get("interests"));

        if(user.getDisinterests()!=null)
            newUser.append("disinterests", user.getDisinterests());
        else
            newUser.append("disinterests", (List<String>)oldUser.get("disinterests"));

        usersCollection.replaceOne(filter, newUser);
    }

}

