package com.zenred.eadvert.model.domain;

import java.util.List;



public class Message {
	
	private String campaign;
	private String message; 
	private String uri; 
	private List<String> imageUriList;
	private String version;
	private String subject;
	
	/**
	 * @return the campaign
	 */
	public String getCampaign() {
		return campaign;
	}
	/**
	 * @param campaign the campaign to set
	 */
	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}
	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
	/**
	 * @return the imageUriList
	 */
	public List<String> getImageUriList() {
		return imageUriList;
	}
	/**
	 * @param imageUriList the imageUriList to set
	 */
	public void setImageUriList(List<String> imageUriList) {
		this.imageUriList = imageUriList;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String toString(){
		return new StringBuffer()
		 .append("campaign:"+ campaign + "\n")
		 .append("message:" + message + "\n") 
		 .append("uri:" + uri + "\n")
		 .append("imageUriList:" + imageUriList + "\n")
		 .append("version:" + version + "\n")
		 .append("subject:" + subject + "\n")
		.toString();
	}
}
