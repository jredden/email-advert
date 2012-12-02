package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.model.domain.InterestGroup;

public class InterestGroupTest2 {
	
	private InterestGroupDao interestGroupDao;
	private TestSupportDAO testSupportDAO;
	
	@Before
	public void setUp(){
		interestGroupDao = new InterestGroupDao();
		testSupportDAO = new TestSupportDAO();
	}
	
	@Test
	public void displayInterestGroup(){
		String interestGroupName = testSupportDAO.fetchFirstInterestGroupName();
		InterestGroup interestGroup = interestGroupDao.fetchInterestGroup(interestGroupName);
		System.out.println(interestGroup);
		assertTrue(interestGroup != null);
	}

}
