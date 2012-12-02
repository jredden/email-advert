package com.zenred.eadvert.model.domain;

import com.zenred.eadvert.model.domain.User.UserRole;

public interface IUser {

	/**
	 * @return the s_userRole
	 */
	public abstract UserRole getS_userRole();

	/**
	 * @param sUserRole the s_userRole to set
	 */
	public abstract void setS_userRole(UserRole sUserRole);

	/**
	 * @return the userName
	 */
	public abstract String getUserName();

	/**
	 * @param userName the userName to set
	 */
	public abstract void setUserName(String userName);

	/**
	 * @return the password
	 */
	public abstract String getPassword();

	/**
	 * @param password the password to set
	 */
	public abstract void setPassword(String password);

	/**
	 * @return the newPassword
	 */
	public abstract String getNewPassword();

	/**
	 * @param newPassword the newPassword to set
	 */
	public abstract void setNewPassword(String newPassword);

	public abstract String toString();

}