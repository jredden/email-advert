package com.zenred.eadvert.model.domain;

public class EmailAddressMessage extends EmailAddress {
	
	private String timeSent;
	private String status;
	private String messageID;

	public String getTimeSent() {
		return timeSent;
	}

	public void setTimeSent(String timeSent) {
		this.timeSent = timeSent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	public String toString() {
		return super.toString() + "timeSent:" + timeSent + "\n" + "status:"
				+ status + "\n" + "messageID:" + messageID;
	}
}
