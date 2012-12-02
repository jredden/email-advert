package com.zenred.eadvert.model.view;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("deleteUser")
public class DeleteUserResponse {
	
	private String deleteUser;

	/**
	 * @return the deleteUser
	 */
	public String getDeleteUser() {
		return deleteUser;
	}

	/**
	 * @param deleteUser the deleteUser to set
	 */
	public void setDeleteUser(String deleteUser) {
		this.deleteUser = deleteUser;
	}
	
	

}
