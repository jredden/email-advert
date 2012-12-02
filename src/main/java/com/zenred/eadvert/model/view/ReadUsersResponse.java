package com.zenred.eadvert.model.view;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("readUsers")
public class ReadUsersResponse {
	
	private String userNames;
	private String userRoles;
	private String password;
	
	/**
	 * @return the userNames
	 */
	public String getUserNames() {
		return userNames;
	}
	/**
	 * @param userNames the userNames to set
	 */
	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}
	/**
	 * @return the userRoles
	 */
	public String getUserRoles() {
		return userRoles;
	}
	/**
	 * @param userRoles the userRoles to set
	 */
	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
