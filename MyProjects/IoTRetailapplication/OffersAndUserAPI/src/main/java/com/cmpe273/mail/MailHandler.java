package com.cmpe273.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailHandler {
	
	public void sendMessage(String messageString) {
		String[] splitMessage = messageString.split(";");
		 
		final String username = "cmpe273proj@gmail.com";
		final String password = "*******";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(splitMessage[0]));
			message.setSubject("Offers in Emart");
			message.setText(splitMessage[1]);
 
			Transport.send(message);
 
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
