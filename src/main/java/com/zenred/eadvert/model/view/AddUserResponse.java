package com.zenred.eadvert.model.view;
import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("addUser")
public class AddUserResponse {
	
	private String addUser;

	/**
	 * @return the addUser
	 */
	public String getAddUser() {
		return addUser;
	}

	/**
	 * @param addUser the addUser to set
	 */
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

}
