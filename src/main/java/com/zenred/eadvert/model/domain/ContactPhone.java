package com.zenred.eadvert.model.domain;

import java.io.Serializable;

public class ContactPhone  implements Serializable{
	
	private int contactPhoneID;
	private Contact contact;
	private String value;
	private String description;
	private String phone;
	private String mobile;

	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * @return the contactPhoneID
	 */
	public int getContactPhoneID() {
		return contactPhoneID;
	}
	/**
	 * @param contactPhoneID the contactPhoneID to set
	 */
	public void setContactPhoneID(int contactPhoneID) {
		this.contactPhoneID = contactPhoneID;
	}
	/**
	 * @return the contact
	 */
	public Contact getContact() {
		return contact;
	}
	/**
	 * @param contact the contact to set
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
	 * @param value the value to set
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
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return new StringBuffer('\n').append(
				"contactPhoneID:" + contactPhoneID + "\n").append(
				"contact:" + contact + "\n").append("value:" + value + "\n")
				.append("description:" + description + "\n")
				.append("Phone:" + phone + "\n")
				.append("Mobile:" + mobile + "\n").toString();
	}
}