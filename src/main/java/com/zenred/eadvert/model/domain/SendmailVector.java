package com.zenred.eadvert.model.domain;

public class SendmailVector {
	
	private String timeSent;
	private String receivingEmailAddress;
	private String sendmailID;
	
	public String getTimeSent() {
		return timeSent;
	}

	public void setTimeSent(String timeSent) {
		this.timeSent = timeSent;
	}

	public String getReceivingEmailAddress() {
		return receivingEmailAddress;
	}

	public void setReceivingEmailAddress(String receivingEmailAddress) {
		this.receivingEmailAddress = receivingEmailAddress;
	}

	public String getSendmailID() {
		return sendmailID;
	}

	public void setSendmailID(String sendmailID) {
		this.sendmailID = sendmailID;
	}

	public String toString(){
		return new StringBuffer()
		.append("timeSent:").append(timeSent).append("\n")
		.append("receivingEmailAddress:").append(receivingEmailAddress).append("\n")
		.append("sendmailID:").append(sendmailID).append("\n")
		.toString();
	}

}
