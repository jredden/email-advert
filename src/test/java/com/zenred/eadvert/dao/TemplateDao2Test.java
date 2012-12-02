package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TemplateDao2Test {
	
	private TemplateDao templateDAO;
	private TestSupportDAO testSupportDAO;
	

	@Before
	public void setUp() throws Exception {
		templateDAO = new TemplateDao();
		testSupportDAO = new TestSupportDAO();
	}

	@Test
	public void testFetchUnConfigured() {
		boolean testIt = false;
		templateDAO.associateTemplateToCampaign(testSupportDAO
				.fetchFirstTemplateName(), testSupportDAO
				.fetchFirstCampaignName());
		testIt = true;
		assertTrue(testIt);
	}

	@Test
	public void testFetchConfigured() {
		boolean testIt = false;
		templateDAO.dissasociateTemplateFromCampaign(testSupportDAO
				.fetchFirstTemplateName(), testSupportDAO
				.fetchFirstCampaignName());
		testIt = true;
		assertTrue(testIt);
	}

}
