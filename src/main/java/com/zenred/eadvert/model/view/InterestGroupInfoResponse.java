package com.zenred.eadvert.model.view;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("interestGroupInfo")
public class InterestGroupInfoResponse {
	
	private String interestGroup;
	private List<String> oemList;
	private List<String> campaignList;
	
	public String getInterestGroup() {
		return interestGroup;
	}
	public void setInterestGroup(String interestGroup) {
		this.interestGroup = interestGroup;
	}
	public List<String> getOemList() {
		return oemList;
	}
	public void setOemList(List<String> oemList) {
		this.oemList = oemList;
	}
	public List<String> getCampaignList() {
		return campaignList;
	}
	public void setCampaignList(List<String> campaignList) {
		this.campaignList = campaignList;
	}
}
