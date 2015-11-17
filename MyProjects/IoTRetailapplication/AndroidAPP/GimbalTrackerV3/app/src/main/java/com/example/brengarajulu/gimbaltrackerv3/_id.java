package com.example.brengarajulu.gimbaltrackerv3;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by brengarajulu on 5/11/2015.
 */
public class _id {

    // private String "1"";
    @JsonProperty("timestamp")
    private long timestamp;

    @JsonProperty("machineIdentifier")
    private long machineIdentifier;

    @JsonProperty("processIdentifier")
    private long processIdentifier;

    @JsonProperty("counter")
    private long counter;

    @JsonProperty("date")
    private long date;

    @JsonProperty("time")
    private long time;

    @JsonProperty("timeSecond")
    private long timeSecond;


    public long getmachineIdentifier() {
        return this.machineIdentifier;
    }
}
