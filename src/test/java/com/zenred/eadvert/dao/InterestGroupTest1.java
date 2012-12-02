package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class InterestGroupTest1 {
	
	private InterestGroupDao interestGroupDao;
	
	@Before
	public void setUp(){
		interestGroupDao = new InterestGroupDao();
	}
	
	@Test
	public void displayInterestGroups(){
		List<String> interestGroups = interestGroupDao.fetchInterestGroups();
		for(String interestGroup : interestGroups){
			System.out.println(interestGroup);
		}
		assertTrue(interestGroups != null);
	}

}
