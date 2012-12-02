package com.zenred.eadvert.model.view;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("newInterestGroup")
public class NewAnonymousInterestGroupResponse {
	
	private String message;

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

}
