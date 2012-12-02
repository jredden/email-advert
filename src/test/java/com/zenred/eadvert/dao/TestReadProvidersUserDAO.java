package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.model.domain.IUser;
import com.zenred.eadvert.model.domain.User;

public class TestReadProvidersUserDAO {
	
	private UserDAO userDAO;
	
	@Before
	public void setUp(){
		userDAO = new UserDAO();
	}

	@Test
	public void testProvider() {
		List<User> result = userDAO.fetchUsersWithProviderRole();
		for (IUser user : result) {
			System.out.println(user);
		}
		System.out.println(result.get(0).getS_userRole());
		boolean answer = result.get(0).getS_userRole().toString().equals("provider");
		assertTrue(answer);
	}
}
