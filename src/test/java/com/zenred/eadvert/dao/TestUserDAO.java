package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestUserDAO {
	
	private UserDAO userDAO;
	
	@Before
	public void setUp(){
		userDAO = new UserDAO();
	}

	@Test
	public void testRoot(){
		String result = userDAO.readUserData("root", "2e99513f5b1fb45e8c6478c5686d2f49").toString();
		System.out.println(result);
		boolean answer = result.equals("root");
		assertTrue(answer);
	}
}
