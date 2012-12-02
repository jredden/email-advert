package com.zenred.eadvert.service;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.RuntimeLikeSetup;
import com.zenred.eadvert.dao.TestSupportDAO;

public class CampaignService4Test {

	private CampaignService campaignService;
	private RuntimeLikeSetup runtimeLikeSetup;
	private TestSupportDAO testSupportDAO;
	
	@Before
	public void setUp(){
		runtimeLikeSetup = RuntimeLikeSetup.getRuntimeLikeSetup();
		campaignService = (CampaignService) runtimeLikeSetup.getBean("campaignService");
		testSupportDAO = new TestSupportDAO();
	}


	@Test
	public void testCampaignDate() {
		String campaignName = testSupportDAO.fetchFirstCampaignName();
		System.out.println(campaignName);
		boolean result = false;
		System.out.println(campaignService.isCampaignInCurrentDateRange(campaignName));
		result = true;
		assertTrue(result);
	}
	@Test
	public void testCampaignDate2() {
		boolean result = false;
		System.out.println(campaignService.isCampaignInCurrentDateRange("mumbles_three"));
		result = true;
		assertTrue(result);
	}

}
