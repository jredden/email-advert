package com.zenred.eadvert.model.view;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("promoteProduction")

public class PromoteToProductionResponse {
	
	private String campaign;
	private String uri;
	private String revision;
	private String dateMinimum;
	private String dateMaximum;
	
	public String getCampaign() {
		return campaign;
	}
	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getRevision() {
		return revision;
	}
	public void setRevision(String revision) {
		this.revision = revision;
	}
	public String getDateMinimum() {
		return dateMinimum;
	}
	public void setDateMinimum(String dateMinimum) {
		this.dateMinimum = dateMinimum;
	}
	public String getDateMaximum() {
		return dateMaximum;
	}
	public void setDateMaximum(String dateMaximum) {
		this.dateMaximum = dateMaximum;
	}

}
