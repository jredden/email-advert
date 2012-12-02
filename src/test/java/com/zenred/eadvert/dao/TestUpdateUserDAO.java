package com.zenred.eadvert.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.RandomName;
import com.zenred.eadvert.RuntimeLikeSetup;
import com.zenred.eadvert.model.domain.IUser;
import com.zenred.eadvert.model.domain.User;
import com.zenred.eadvert.model.domain.User.UserRole;
import com.zenred.util.MD5Util;

public class TestUpdateUserDAO {
	
	private String userName;
	private String userPassword;
	private String userPassword5;
	private UserDAO userDAO;
	private RuntimeLikeSetup runtimeLikeSetup;
	
	@Before
	public void setUp(){
		userName = RandomName.randomName();
		userPassword = RandomName.randomName();
		runtimeLikeSetup = RuntimeLikeSetup.getRuntimeLikeSetup();
		userDAO = (UserDAO) runtimeLikeSetup.getBean("userDAO");
		try {
			userPassword5 = MD5Util.MD5(userPassword);
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}

	}
	
	@Test
	public void updateUser(){
		IUser user = new User();
		user.setUserName(userName);
		user.setS_userRole(UserRole.test);
		user.setPassword(userPassword5);
		System.out.println(user);
		userDAO.createUser(user);
		user.setS_userRole(UserRole.subscriber);
		userDAO.updateUsersRole(user);
		try {
			String newPassword = MD5Util.MD5(RandomName.randomName());
			user.setNewPassword(newPassword);
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}
		userDAO.updateUsersPassword(user);
		System.out.println(user);
		user.setPassword(user.getNewPassword());
		user.setNewPassword(null);
		userDAO.deleteUser(user);
		System.out.println(user);
	}
}
