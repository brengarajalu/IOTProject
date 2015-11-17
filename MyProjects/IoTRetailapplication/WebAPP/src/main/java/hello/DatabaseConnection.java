package hello; /**
 * Created by aditi on 17/04/15.
 */

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by aditi on 28/03/15.
 */
public class DatabaseConnection {

    private final String dbURI = "mongodb://localhost";
    private final String dbName = "gimbal";
    private final String collName = "places";

    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public void setDbConnection() throws Exception{

        this.client = new MongoClient(new MongoClientURI(this.dbURI));

        this.database = this.client.getDatabase(this.dbName);
        this.collection = this.database.getCollection(this.collName);

        long nos = this.collection.count();

        if(nos>=0) {
            System.out.println("***** Connection Established *****");
        }
        else {
            System.out.println("Failed to establish connection...");
            throw new Exception("Database connection couldn't be established");
        }
    }

}

