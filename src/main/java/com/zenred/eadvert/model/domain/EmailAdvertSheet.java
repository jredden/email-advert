package com.zenred.eadvert.model.domain;

public class EmailAdvertSheet {

	private String emailAddress;
	private String firstName;
	private String middleName;
	private String lastName;
	private String address1;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	private String address2;
	private String city;
	private String state;
	private String zip;

	private String phoneNumber;
	private String mobilePhoneNumber;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
	 * @return the firstName
	 */

	public String toString() {
		return new StringBuffer().append('\n').append(
				"emailAddress:" + emailAddress + "\n").append(
				"firstName" + firstName + "\n").append(
				"middleName" + middleName + "\n").append(
				"lastName" + lastName + "\n").append(
				"address1:" + address1 + "\n").append(
				"address2:" + address2 + "\n").append("state:" + state + "\n")
				.append("zip:" + zip + "\n").append("city:" + city + "\n")
				.append("phonenumber:" + phoneNumber + "\n").append(
						"mobilePhoneNumber:" + mobilePhoneNumber + "\n")
				.toString();
	}

}
