package com.zenred.eadvert.model.domain;

import java.io.Serializable;

public class EmailAddress implements Serializable {

	private int emailAddressID;
	private String emailAddress;
	private String emailType;

	/**
	 * @return the emailAddressID
	 */
	public int getEmailAddressID() {
		return emailAddressID;
	}

	/**
	 * @param emailAddressID
	 *            the emailAddressID to set
	 */
	public void setEmailAddressID(int emailAddressID) {
		this.emailAddressID = emailAddressID;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress
	 *            the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the emailType
	 */
	public String getEmailType() {
		return emailType;
	}

	/**
	 * @param emailType
	 *            the emailType to set
	 */
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public String toString() {
		return new StringBuffer('\n').append(
				"emailAddressID:" + emailAddressID + "\n").append(
				"emailAddress:" + emailAddress + "\n").append(
				"emailType:" + emailType + "\n").toString();
	}

}
