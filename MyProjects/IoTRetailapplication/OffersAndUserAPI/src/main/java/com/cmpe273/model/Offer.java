package com.cmpe273.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Created by Ram on 30/04/15.
 */
public class Offer {

	@JsonProperty("beaconId")
	private String beaconId;
	@JsonProperty("RSSmin")
	private int rssMin;
	@JsonProperty("RSSmax")
	private int rssMax;
	@JsonProperty("category")
	private String category;
	@JsonProperty("offers")
	private List<String> offers;
	
	public String getBeaconId() {
		return beaconId;
	}
	public void setBeaconId(String beaconId) {
		this.beaconId = beaconId;
	}
	public int getRssMin() {
		return rssMin;
	}
	public void setRssMin(int rssMin) {
		this.rssMin = rssMin;
	}
	public int getRssMax() {
		return rssMax;
	}
	public void setRssMax(int rssMax) {
		this.rssMax = rssMax;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<String> getOffers() {
		return offers;
	}
	public void setOffers(List<String> offers) {
		this.offers = offers;
	}
	
	
}
