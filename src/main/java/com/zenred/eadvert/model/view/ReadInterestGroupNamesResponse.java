package com.zenred.eadvert.model.view;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("interestGroupName")

public class ReadInterestGroupNamesResponse {
	
	private String interestGroupName;

	/**
	 * @return the interestGroupName
	 */
	public String getinterestGroupName() {
		return interestGroupName;
	}

	/**
	 * @param interestGroupName the interestGroupName to set
	 */
	public void setinterestGroupName(String interestGroupName) {
		this.interestGroupName = interestGroupName;
	}


}
