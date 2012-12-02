package com.zenred.eadvert.model.domain;

import java.io.Serializable;

import com.zenred.eadvert.model.domain.EMail.mailType;

public class Contact implements Serializable {

	private int contactID;
	private String state;
	private DateEvent dateEvent;
	private EmailAddress emailAddress;

	private static String INDIVIDUAL = "individual";
	private static String BUSINESS = "business";
	private static String UNKNOWN = "unknown";

	public enum contactType {
		individual, business, unknown
	};

	public contactType c_type;

	public contactType getType() {
		return Type;
	}

	/**
	 * @return the type
	 */

	public contactType getType(String c_type) {
		contactType _type = null;
		if (c_type.equals(INDIVIDUAL)) {
			_type = Type.individual;
		} else {
			if (c_type.equals(BUSINESS)) {
				_type = Type.business;
			} else {
				if (c_type.equals(UNKNOWN)) {
					_type = Type.unknown;
				}

			}
		}
		return _type;
	}

	public contactType Type;

	public contactType getC_type() {
		return c_type;
	}

	public void setC_type(contactType cType) {
		this.c_type = cType;
	}

	public void setType(contactType type) {
		this.Type = type;
	}

	/**
	 * @return the contactID
	 */
	public int getContactID() {
		return contactID;
	}

	/**
	 * @param contactID
	 *            the contactID to set
	 */
	public void setContactID(int contactID) {
		this.contactID = contactID;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the dateEvent
	 */
	public DateEvent getDateEvent() {
		return dateEvent;
	}

	/**
	 * @param dateEvent
	 *            the dateEvent to set
	 */
	public void setDateEvent(DateEvent dateEvent) {
		this.dateEvent = dateEvent;
	}

	/**
	 * @return the emailAddress
	 */
	public EmailAddress getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress
	 *            the emailAddress to set
	 */
	public void setEmailAddress(EmailAddress emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String toString() {
		return new StringBuffer('\n').append("contactID:" + contactID + "\n")
				.append("contactType:" + Type + "\n").append(
						"state:" + state + "\n").append(
						"dateEvent:" + dateEvent + "\n").append(
						"emailAddress:" + emailAddress + "\n").toString();
	}

}
