package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.model.domain.EMail;

public class EMailDao2Test {

	private EmailDao emailDao;
	private EMail email;
	private TestSupportDAO testSupportDAO;

	@Before
	public void setUp() {
		emailDao = new EmailDao();
		email = new EMail();
		testSupportDAO = new TestSupportDAO();
		email.setAddress("testaddress@test.org");
		email.setType(EMail.mailType.test);
	}

	@Test
	public void fetchEmailCampaign() {
		boolean testIt = false;
		String campaignName = testSupportDAO.fetchFirstCampaignName();
		List<EMail> emails = emailDao.fetchCampaignsEmails(campaignName);
		for (EMail email : emails) {
			System.out.println(email);
		}
		testIt = true;
		assertTrue(testIt);
	}

}
