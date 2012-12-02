package com.zenred.eadvert.model.view;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("addressInterestGroupProvider")
public class AddressInterestGroupProviderResponse {
	
	private String emailAddress;
	private String interestGroup;
	private String provider;
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getInterestGroup() {
		return interestGroup;
	}
	public void setInterestGroup(String interestGroup) {
		this.interestGroup = interestGroup;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}

}
