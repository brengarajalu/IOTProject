package com.example.brengarajulu.gimbaltrackerv3;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Created by brengarajulu on 5/12/2015.
 */
public class ShareablePromotion {

    // private String "1"";
    @JsonProperty("mailId")
    private String mailId;

    @JsonProperty("message")
    private String message;


    public void setEmail (String email) {
        this.mailId = email;
    }

    public void setPromotion (String promotion) {
        this.message = promotion;
    }



}
