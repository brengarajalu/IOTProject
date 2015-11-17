package com.cmpe273.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MailRequest  {

	@JsonProperty("mailId")
	private String mailId;
	@JsonProperty("message")
	private String message;
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
