package com.zenred.eadvert.model.domain;

public class ProductionMessage extends Message {
	
	private String sendOffDate;

	public String getSendOffDate() {
		return sendOffDate;
	}

	public void setSendOffDate(String sendOffDate) {
		this.sendOffDate = sendOffDate;
	}
	
	public String toString(){
		return super.toString()+"sendOffDate:"+sendOffDate+"\n";
	}

}
