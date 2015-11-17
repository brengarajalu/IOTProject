package com.cmpe273.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by aditi on 28/03/15.
 */
public class DatabaseConnection {

    private final String dbUser = "273user";
    private final String dbPswd = "273user";
    private final String dbURI = "ds055680.mongolab.com:55680/273project";
    private final String dbName = "273project";
    private MongoClient client;
    private MongoDatabase database;


    public void setDbConnection(){

        this.client = new MongoClient(new MongoClientURI("mongodb://"+
                                this.dbUser + ":" + this.dbPswd + "@" + this.dbURI));

        this.database = this.client.getDatabase(this.dbName);
    }

    public MongoCollection<Document> getCollection(String collectionName){
        return this.database.getCollection(collectionName);
    }

}
