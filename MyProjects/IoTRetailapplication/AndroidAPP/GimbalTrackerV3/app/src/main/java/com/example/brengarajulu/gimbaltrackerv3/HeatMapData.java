package com.example.brengarajulu.gimbaltrackerv3;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by brengarajulu on 5/11/2015.
 */
public class HeatMapData {
    @JsonProperty("Id")
    private Integer geoLocId;
    private String beaconId;
    private String createdAt;
    private String leaveAt;
    private String noOfSightings;

    public HeatMapData()
    {

    }
    public HeatMapData(String beaconId, String created_at, String leave_at, String noOfHits)
    {
        super();
        this.beaconId = beaconId;
        this.createdAt = created_at;
        this.leaveAt = leave_at;
        this.noOfSightings = noOfHits;
    }
    public String getBeaconId()
    {
        return beaconId;
    }

    public String getCreatedAt()
    {
        return createdAt;
    }

    public String getLeaveAt()
    {
        return leaveAt;
    }

    public String getNoOfSightings()
    {
        return noOfSightings;
    }

    public void setBeaconId(String beacon)
    {
        this.beaconId = beacon;
    }

    public void setCreatedAt(String time)
    {
        this.createdAt = time;
    }

    public void setLeaveAt(String time)
    {
        this.leaveAt = time;
    }

    public void setNoOfSightings(String noOfHits)
    {
        this.noOfSightings = noOfHits;
    }

    public Integer getIdentity()
    {
        return geoLocId;
    }

    public void setIdentity(Integer id)
    {
        this.geoLocId = id;
    }
}
