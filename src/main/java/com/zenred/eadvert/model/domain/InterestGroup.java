package com.zenred.eadvert.model.domain;

import java.util.List;

public class InterestGroup {
	
	private String interestGroupName;
	private List<String> providerList;   // provider is the same as "oem"
	private List<Campaign> campaignList;
	private List<EmailAddress> emailAddressList;
	
	public String getInterestGroupName() {
		return interestGroupName;
	}
	public void setInterestGroupName(String interestGroupName) {
		this.interestGroupName = interestGroupName;
	}
	public List<String> getProviderList() {
		return providerList;
	}
	public void setProviderList(List<String> providerList) {
		this.providerList = providerList;
	}
	public List<Campaign> getCampaignList() {
		return campaignList;
	}
	public void setCampaignList(List<Campaign> campaignList) {
		this.campaignList = campaignList;
	}
	public List<EmailAddress> getEmailAddressList() {
		return emailAddressList;
	}
	public void setEmailAddressList(List<EmailAddress> emailAddressList) {
		this.emailAddressList = emailAddressList;
	}
	
	public String toString(){
		return  new StringBuffer()
		.append("interestGroupName:"+interestGroupName+"\n")
		.append("providerList:"+providerList+"\n")
		.append("campaignList:"+campaignList+"\n")
		.append("emailAddressList:"+emailAddressList+"\n")
		.toString();
	}

}
