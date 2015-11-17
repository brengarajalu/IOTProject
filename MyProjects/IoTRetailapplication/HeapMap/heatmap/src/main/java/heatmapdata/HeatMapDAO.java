package heatmapdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import heatmapdata.HeatMapData;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;


	
public class HeatMapDAO {

	private final MongoCollection<Document> heatMapCollection;
	
	public HeatMapDAO(final MongoDatabase blogDatabase) {
		heatMapCollection = blogDatabase.getCollection("heatmap");
		//pollCollection=blogDatabase.getCollection("polls");
		//modCollection.drop();
		//pollCollection.drop();
	}
	
	public void save(HeatMapData heatMapData){

		Document document=new Document("_id",heatMapData.getBeacon());
		document.append("noOfHits", heatMapData.getNoOfHits());
		document.append("time", heatMapData.getTime());
//		document.append("password", moderator.getPassword());
//		document.append("created_at", moderator.getCreated_at());
//		document.append("polls", Arrays.asList());
		heatMapCollection.insertOne(document);
		
	}
	public FindIterable<Document> getHeatMaps(){
		
		FindIterable<Document> find = heatMapCollection.find();
		//Document docume=find.first();
		//String id= docume.getString("_id");
		//docume.remove("_id");
		//docume.put("id", id);
		//docume.remove("results");
		//System.out.println(docume);
		return find;
			
		
	}
	public void updateHeatMapData(HeatMapData heatMapData) 
	{
		Document document=new Document("beacon", heatMapData.getBeacon());
		document.append("noOfHits", heatMapData.getNoOfHits());
		Document setDocument=new Document("$set",document);
		heatMapCollection.updateOne(new Document("_id",heatMapData.getBeacon()), setDocument);
	}
}

