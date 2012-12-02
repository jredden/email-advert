package com.zenred.eadvert.model.view;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("reviewUpdate")
public class OneMessageReviewOrUpdateResponse {
	
	private String content;
	private String version;
	private String campaign;
	private String uri;
	
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}

}
