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
	}
	
	public void save(HeatMapData heatMapData){

		Document document=new Document("_id",heatMapData.getIdentity());
		document.append("beaconId", heatMapData.getBeaconId());
		document.append("noOfSightings", heatMapData.getNoOfSightings());
		document.append("createdAt", heatMapData.getCreatedAt());
		document.append("leaveAt", heatMapData.getLeaveAt());
		heatMapCollection.insertOne(document);
		
	}
	
	public HeatMapData getHeatMapData(int id){
		FindIterable<Document> find = heatMapCollection.find(new Document("_id",id));
		Document heatMapDoc = find.first();
		//Moderator moderator=new Moderator();
		HeatMapData hmd = new HeatMapData();
		hmd.setIdentity(heatMapDoc.getInteger("_id"));
		hmd.setBeaconId(heatMapDoc.getString("beaconId"));
		hmd.setNoOfSightings(heatMapDoc.getString("noOfSightings"));
		hmd.setCreatedAt(heatMapDoc.getString("createdAt"));
		hmd.setLeaveAt(heatMapDoc.getString("leaveAt"));
		return hmd;
	}
	
	public void updateHeatMapData(HeatMapData hmd) {
		Document document=new Document("beaconId", hmd.getBeaconId());
		document.append("noOfSightings", hmd.getNoOfSightings());
		Document setDocument=new Document("$set",document);
		heatMapCollection.updateOne(new Document("_id",hmd.getIdentity()), setDocument);
	}
	
	public void createBeaconId(HeatMapData hmd)
	{
		Document document=new Document("_id",hmd.getIdentity());
		document.append("beaconId", hmd.getBeaconId());
		document.append("noOfSightings", hmd.getNoOfSightings());
		document.append("createdAt", hmd.getCreatedAt());
		document.append("leaveAt", hmd.getLeaveAt());
		
		heatMapCollection.insertOne(document);
	}
	
	public FindIterable<Document> getHeatMaps(){
		
		FindIterable<Document> find = heatMapCollection.find();
		return find;
			
		
	}
	
	public void deleteGeoLocId(int geoLocId)
	{
		heatMapCollection.deleteOne(new Document("_id",geoLocId));
	}
	
	
}
