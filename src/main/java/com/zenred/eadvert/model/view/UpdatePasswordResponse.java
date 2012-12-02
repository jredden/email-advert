package com.zenred.eadvert.model.view;
import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("updatePassword")
public class UpdatePasswordResponse {
	
	private String updatePassword;

	/**
	 * @return the updatePassword
	 */
	public String getupdatePassword() {
		return updatePassword;
	}

	/**
	 * @param updatePassword the updatePassword to set
	 */
	public void setupdatePassword(String updatePassword) {
		this.updatePassword = updatePassword;
	}

}
