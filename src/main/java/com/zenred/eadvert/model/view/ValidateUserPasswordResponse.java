package com.zenred.eadvert.model.view;
import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("userRole")
public class ValidateUserPasswordResponse {
	
	private String userRole;

	/**
	 * @return the userRole
	 */
	public String getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}


}
