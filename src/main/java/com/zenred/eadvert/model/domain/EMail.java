package com.zenred.eadvert.model.domain;

public class EMail {
	private static String TEST = "test";
	private static String ADMIN = "admin";
	private static String PROVIDER = "provider";
	private static String SUBSCRIBER = "subscriber";
	public enum mailType {test, admin, provider, subscriber};
	public mailType s_mailtype;
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the type
	 */
	public mailType getType() {
		return type;
	}

	public mailType getType(String s_type) {
		mailType _type = null;
		if (s_type.equals(TEST)) {
			_type = s_mailtype.test;
		} else {
			if (s_type.equals(ADMIN)) {
				_type = s_mailtype.admin;
			} else {
				if (s_type.equals(PROVIDER)) {
					_type = s_mailtype.provider;
				} else {
					if (s_type.equals(SUBSCRIBER)) {
						_type = s_mailtype.subscriber;
					}
				}
			}
		}
		return _type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(mailType type) {
		this.type = type;
	}
	private String address;
	private mailType type;

	public String toString(){
		return new StringBuffer().append("\n address:"+address+"\n")
		.append("type:"+type+"\n")
		.toString();
	}
}