package heatmapdata;

import org.springframework.data.annotation.Id;

public class HeatMapData {
	@Id
	private String beacon;
	private String time;
	private String noOfHits;
	
	public HeatMapData()
	{
		
	}
	public HeatMapData(String beaconId, String time, String noOfHits)
	{
		super();
		this.beacon = beaconId;
		this.time = time;
		this.noOfHits = noOfHits;
	}
	public String getBeacon()
	{
		return beacon;
	}

	public String getTime()
	{
		return time;
	}
	
	public String getNoOfHits()
	{
		return noOfHits;
	}
	
	public void setBeacon(String beacon)
	{
		this.beacon = beacon;
	}
	
	public void setTime(String time)
	{
		this.time = time;
	}
	
	public void setNoOfHits(String noOfHits)
	{
		this.noOfHits = noOfHits;
	}
}

