package com.zenred.eadvert.model.domain;

import java.io.Serializable;

import com.zenred.eadvert.model.domain.Contact.contactType;

public class ContactName implements Serializable {

	private int contactNameID;
	private Contact contact;
	private String value;
	private String description;

	private static String FIRSTNAME = "firstName";
	private static String MIDDLENAME = "middleName";
	private static String LASTNAME = "lastName";
	private static String ANONYMOUS = "Anonymous";

	public enum contactNameType {
		firstName, middleName, lastName, Anonymous
	};

	public contactNameType c_type;

	/**
	 * @return the type
	 */
	public contactNameType getType() {
		return this.Type;
	}

	public contactNameType getType(String c_type) {
		contactNameType _type = null;
		if (c_type.equals(FIRSTNAME)) {
			_type = Type.firstName;
		} else {
			if (c_type.equals(MIDDLENAME)) {
				_type = Type.middleName;
			} else {
				if (c_type.equals(LASTNAME)) {
					_type = Type.lastName;
				} else {
					if (c_type.equals(LASTNAME)) {
						_type = Type.Anonymous;
					}
				}
			}
		}
		return _type;
	}

	public contactNameType getC_type() {
		return this.c_type;
	}

	public contactNameType Type;

	public void setC_type(contactNameType cType) {
		c_type = cType;
	}

	public void setType(contactNameType type) {
		Type = type;
	}

	/**
	 * @return the contactNameID
	 */
	public int getContactNameID() {
		return contactNameID;
	}

	/**
	 * @param contactNameID
	 *            the contactNameID to set
	 */
	public void setContactNameID(int contactNameID) {
		this.contactNameID = contactNameID;
	}

	/**
	 * @return the contact
	 */
	public Contact getContact() {
		return contact;
	}

	/**
	 * @param contact
	 *            the contact to set
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return new StringBuffer('\n').append(
				"contactNameID:" + contactNameID + "\n").append(
				"contact:" + contact + "\n").append("value:" + value + "\n")
				.append("description:" + Type + "\n").toString();
	}

}
