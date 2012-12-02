package com.zenred.eadvert.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.zenred.eadvert.dao.UserDAO;
import com.zenred.eadvert.exception.UserServiceException;
import com.zenred.eadvert.model.domain.IProvider;
import com.zenred.eadvert.model.domain.IUser;
import com.zenred.eadvert.model.domain.User;
import com.zenred.eadvert.model.domain.User.UserRole;
import com.zenred.util.MD5Util;

public class UserService {
	
		private UserDAO userDAO;

		/**
		 * @param userDAO the userDAO to set
		 */
		public void setUserDAO(UserDAO userDAO) {
			this.userDAO = userDAO;
		}

		public UserRole validateUser(String userName, String password) throws UserServiceException{
			UserRole userRole = null;
			try {
				String mungedPassword = MD5Util.MD5(password);
				userRole = userDAO.readUserData(userName, mungedPassword);
			} catch (NoSuchAlgorithmException nsae) {
				nsae.printStackTrace();
				throw new UserServiceException("password munging failed");
			} catch (UnsupportedEncodingException uee) {
				uee.printStackTrace();
				throw new UserServiceException("password munging failed");
			}
			return userRole;
		}
		
		public void addNewUser(String userName, String password, String role) throws UserServiceException{
			if(role.equals("root")){
				throw new UserServiceException("cannot add root!");
			}

			IUser user = new User();
			user.setUserName(userName);
			user.setS_userRole(User.getUserRole(role));
			try {
				user.setPassword(MD5Util.MD5(password));
			} catch (NoSuchAlgorithmException nsae) {
				nsae.printStackTrace();
				throw new UserServiceException("password munging failed");
			} catch (UnsupportedEncodingException uee) {
				uee.printStackTrace();
				throw new UserServiceException("password munging failed");
			}
			userDAO.createUser(user);
		}
		public void addNewUser(IProvider user) throws UserServiceException{
			if(user.getS_userRole().toString().equals("root")){
				throw new UserServiceException("cannot add root!");
			}

			try {
				user.setPassword(MD5Util.MD5(user.getNewPassword()));
			} catch (NoSuchAlgorithmException nsae) {
				nsae.printStackTrace();
				throw new UserServiceException("password munging failed");
			} catch (UnsupportedEncodingException uee) {
				uee.printStackTrace();
				throw new UserServiceException("password munging failed");
			}
			userDAO.createUser(user);
			if(user.getS_userRole().toString().equals("provider")){
				userDAO.buildUserProviderRow(user);
			}
		}
		
		public void deleteUser(String userName, String password, String role) throws UserServiceException{
			if(role.equals("root")){
				throw new UserServiceException("cannot delete root!");
			}
			IUser user = new User();
			user.setUserName(userName);
			try {
				user.setPassword(MD5Util.MD5(password));
			} catch (NoSuchAlgorithmException nsae) {
				nsae.printStackTrace();
				throw new UserServiceException("password munging failed");
			} catch (UnsupportedEncodingException uee) {
				uee.printStackTrace();
				throw new UserServiceException("password munging failed");
			}
			userDAO.deleteUser(user);
		}
		public void deleteUserNoMunging(String userName, String password, String role) throws UserServiceException{
			if(role.equals("root")){
				throw new UserServiceException("cannot delete root!");
			}
			IUser user = new User();
			user.setUserName(userName);
			user.setPassword(password);
			userDAO.deleteUser(user);
		}
		public void updateUserRole(String userName, String password, String role) throws UserServiceException{
			IUser user = new User();
			user.setUserName(userName);
			user.setS_userRole(User.getUserRole(role));
			try {
				user.setPassword(MD5Util.MD5(password));
			} catch (NoSuchAlgorithmException nsae) {
				nsae.printStackTrace();
				throw new UserServiceException("password munging failed");
			} catch (UnsupportedEncodingException uee) {
				uee.printStackTrace();
				throw new UserServiceException("password munging failed");
			}
			userDAO.updateUsersRole(user);
		}
		
	public void updateUserPassword(String userName, String newPassword,
			String role) throws UserServiceException {
		String password = userDAO.readUserPassword(userName, role);
		IUser user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		try {
			user.setNewPassword(MD5Util.MD5(newPassword));
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
			throw new UserServiceException("password munging failed");
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
			throw new UserServiceException("password munging failed");
		}
		userDAO.updateUsersPassword(user);
		user.setPassword(user.getNewPassword());
		user.setNewPassword(null);
	}
		
	public boolean doesUserNotExist(String userName, String password)
			throws UserServiceException {
		boolean answer = true;
		String mungedPassword = null;
		try {
			mungedPassword = MD5Util.MD5(password);
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
			throw new UserServiceException("password munging failed");
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
			throw new UserServiceException("password munging failed");
		}
		UserRole role = userDAO.readUserData(userName, mungedPassword);
		if (null != role) {
			answer = false;
		}
		return answer;
	}

	public List<User> readUsersThatAreNotRoot() {
		return userDAO.fetchNonRootUsers();
	}

	public List<User> readUsersInProviderRole() {
		return userDAO.fetchUsersWithProviderRole();
	}
}
