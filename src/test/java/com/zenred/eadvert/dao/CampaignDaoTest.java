package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.model.domain.Campaign;

public class CampaignDaoTest {
	
	private CampaignDao campaignDao;
	private Campaign campaign;

	@Before
	public void setUp() throws Exception {
		campaignDao = new CampaignDao();
		campaign = new Campaign();
	}

	@Test
	public void testInsertCampaign() {
		boolean testIt = false;
		campaign.setName("mumblesOne");
		campaign.setOem("testOem");
		campaignDao.insertCampaign(campaign);
		testIt = true;
		assertTrue(testIt);
	}
	
	@Test
	public void testCampaignExists(){
		boolean testIt = false;
		campaign.setName("mumblesOne");
		testIt = campaignDao.doesCampaignExist(campaign.getName());
		assertTrue(testIt);	
	}

}
