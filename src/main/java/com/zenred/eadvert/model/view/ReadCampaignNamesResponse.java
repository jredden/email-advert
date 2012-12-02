package com.zenred.eadvert.model.view;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("campaignName")

public class ReadCampaignNamesResponse {
	
	private String campaignName;

	/**
	 * @return the campaignName
	 */
	public String getcampaignName() {
		return campaignName;
	}

	/**
	 * @param campaignName the campaignName to set
	 */
	public void setcampaignName(String campaignName) {
		this.campaignName = campaignName;
	}


}
