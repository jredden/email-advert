package com.zenred.eadvert.model.domain;

import java.io.Serializable;

public class ContactAddress  implements Serializable{
	
	private int contactAddressID;
	private Contact contact;
	private String value;
	private String description;
	private int zip;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	private String city;
	private String address1;
	private String address2;
	private String state;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the contactAddressID
	 */
	public int getContactAddressID() {
		return contactAddressID;
	}
	/**
	 * @param contactAddressID the contactAddressID to set
	 */
	public void setContactAddressID(int contactAddressID) {
		this.contactAddressID = contactAddressID;
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
				"contactAddressID:" + contactAddressID + "\n").append(
				"contact:" + contact + "\n").append("value:" + value + "\n")
				.append("description:" + description + "\n")
				.append("Zip:" +  zip + "\n")
				.append("City:" + city + "\n")
				.append("State:" + state + "\n")
				.append("Address1:" + address1 + "\n")
				.append("Address2:" + address2 + "\n").toString();
	}


}
