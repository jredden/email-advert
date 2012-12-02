package com.zenred.eadvert.model.view;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("noEmailAddress")
public class ExcelUsersResponse {

	private int emailAddresses;

	public int getEmailAddresses() {
		return emailAddresses;
	}

	public void setEmailAddresses(int emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

}
