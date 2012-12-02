package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.model.domain.IUser;
import com.zenred.eadvert.model.domain.User;

public class TestNonRootUsersDAO {
	
	private UserDAO userDAO;
	
	@Before
	public void setUp(){
		userDAO = new UserDAO();
	}

	@Test
	public void testNonRoot(){
		List<User>  userList = userDAO.fetchNonRootUsers();
		for (IUser user : userList){
			System.out.println(user);
		}
		assertTrue(userList != null);
	}
}
