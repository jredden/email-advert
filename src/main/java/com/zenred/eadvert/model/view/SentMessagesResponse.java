package com.zenred.eadvert.model.view;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("sentMessagesResponse")
public class SentMessagesResponse {
	
	private String campaign;
	private String emailAddress;
	private String timeSent;
	private String transferState;
	private String statusMessage;
	
	public String getCampaign() {
		return campaign;
	}
	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getTimeSent() {
		return timeSent;
	}
	public void setTimeSent(String timeSent) {
		this.timeSent = timeSent;
	}
	public String getTransferState() {
		return transferState;
	}
	public void setTransferState(String transferState) {
		this.transferState = transferState;
	}

	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public String toString() {
		return new StringBuffer().append("campaign:" + campaign + "\n")
				.append("emailAddress:" + emailAddress + "\n")
				.append("timeSent:" + timeSent + "\n")
				.append("transferState:" + transferState + "\n")
				.append("statusMessage:" + statusMessage + "\n").toString();
	}
}
