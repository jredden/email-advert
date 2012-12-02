package com.zenred.eadvert.model.domain;

public class MessageIdentifications {
	
	private String messageID;
	private String emailAddressID;
	private String timeSent;
	
	public String getMessageID() {
		return messageID;
	}
	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}
	public String getEmailAddressID() {
		return emailAddressID;
	}
	public void setEmailAddressID(String emailAddressID) {
		this.emailAddressID = emailAddressID;
	}
	
	public String getTimeSent() {
		return timeSent;
	}
	public void setTimeSent(String timeSent) {
		this.timeSent = timeSent;
	}
	public String toString(){
		return new StringBuffer().append("messageID:"+messageID+"\n").append("emailAddressID:"+emailAddressID+"\n")
		.append("timeSent:"+timeSent+"\n")
		.toString();
	}

}
