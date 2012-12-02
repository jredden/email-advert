package com.zenred.eadvert.model.domain;

public class User implements IUser {
	private static String TEST = "test";
	private static String ADMIN = "admin";
	private static String PROVIDER = "provider";
	private static String SUBSCRIBER = "subscriber";
	private static String ROOT = "root";
	
	public enum UserRole {test, admin, provider, subscriber, root};
	private UserRole s_userRole;
	private String userName;
	private String password;
	private String newPassword;
	
	/* (non-Javadoc)
	 * @see com.zenred.eadvert.model.domain.IUser#getS_userRole()
	 */
	@Override
	public UserRole getS_userRole() {
		return s_userRole;
	}
	/* (non-Javadoc)
	 * @see com.zenred.eadvert.model.domain.IUser#setS_userRole(com.zenred.eadvert.model.domain.User.UserRole)
	 */
	@Override
	public void setS_userRole(UserRole sUserRole) {
		s_userRole = sUserRole;
	}
	/* (non-Javadoc)
	 * @see com.zenred.eadvert.model.domain.IUser#getUserName()
	 */
	@Override
	public String getUserName() {
		return userName;
	}
	/* (non-Javadoc)
	 * @see com.zenred.eadvert.model.domain.IUser#setUserName(java.lang.String)
	 */
	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/* (non-Javadoc)
	 * @see com.zenred.eadvert.model.domain.IUser#getPassword()
	 */
	@Override
	public String getPassword() {
		return password;
	}
	/* (non-Javadoc)
	 * @see com.zenred.eadvert.model.domain.IUser#setPassword(java.lang.String)
	 */
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static UserRole getUserRole(String s_userRole){
		UserRole userRole = null;
		if(s_userRole.equals(ROOT)){
			userRole = UserRole.root;
		}
		else{
			if(s_userRole.equals(ADMIN)){
				userRole = UserRole.admin;
			}
			else{
				if(s_userRole.equals(PROVIDER)){
					userRole = UserRole.provider;
				}
				else{
					if(s_userRole.equals(SUBSCRIBER)){
						userRole = UserRole.subscriber;
					}
					else{
						if(s_userRole.equals(TEST)){
							userRole = UserRole.test;
						}
					}
				}
			}
		}
		return userRole;
	}
	
	/* (non-Javadoc)
	 * @see com.zenred.eadvert.model.domain.IUser#getNewPassword()
	 */
	@Override
	public String getNewPassword() {
		return newPassword;
	}
	/* (non-Javadoc)
	 * @see com.zenred.eadvert.model.domain.IUser#setNewPassword(java.lang.String)
	 */
	@Override
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	/* (non-Javadoc)
	 * @see com.zenred.eadvert.model.domain.IUser#toString()
	 */
	@Override
	public String toString(){
		return new StringBuffer().append('\n')
		.append("s_userRole:"+s_userRole+"\n")
		.append("userName:"+userName+"\n")
		.append("userPassword:"+password+"\n")
		.append("newPassword:"+newPassword+"\n")
		.toString();
	}
}
