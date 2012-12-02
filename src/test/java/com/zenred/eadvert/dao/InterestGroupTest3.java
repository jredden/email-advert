package com.zenred.eadvert.dao;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class InterestGroupTest3 {
	
	private InterestGroupDao interestGroupDao;
	private TestSupportDAO testSupportDAO;
	
	@Before
	public void setUp(){
		interestGroupDao = new InterestGroupDao();
		testSupportDAO = new TestSupportDAO();
	}
	
	@Test
	public void emptyCampaignInterestGroup(){
		boolean answer = interestGroupDao.campaignInInterestGroup("grok_the_bean", testSupportDAO.fetchFirstInterestGroupName());
		System.out.println("answer:"+answer);
		assertFalse(answer);
	}

}
