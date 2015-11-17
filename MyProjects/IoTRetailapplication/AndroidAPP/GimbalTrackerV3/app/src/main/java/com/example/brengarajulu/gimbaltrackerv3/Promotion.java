package com.example.brengarajulu.gimbaltrackerv3;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by brengarajulu on 4/18/2015.
 */
public class Promotion {

   // private String "1"";
   @JsonProperty("beaconId")
    private String beaconId;

    @JsonProperty("id")
    private String id;

    @JsonProperty("RSSmax")
    private int RSSmax;

    @JsonProperty("RSSmin")
    private int RSSmin;

    @JsonProperty("category")
    private String category;

    @JsonProperty("_id")
    private _id Id;

    @JsonProperty("offers")
    private List<String> offers;

   public String getBeacon_id() {
        return this.beaconId;
    }

    public int getRss() {
        return this.RSSmax;
    }

    public List<String> getOffers() {
        return this.offers;
    }

}



//[{"_id":{"timestamp":1431299753,"machineIdentifier":8929664,"processIdentifier":1350,"counter":5468575,"date":1431299753000,"time":1431299753000,"timeSecond":1431299753},"beaconId":"RS34A","RSSmin":0,"RSSmax":100,"category":"bakery","offers":["40% on biscuits","20% on choclate cip cake"]}