package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.model.domain.EMail;

public class EMailDao5Test {

	private EmailDao emailDao;
	private EMail email;
	private TestSupportDAO testSupportDAO;

	@Before
	public void setUp() {
		emailDao = new EmailDao();
		testSupportDAO = new TestSupportDAO();
	}

	@Test
	public void fetchEmailCampaign() {
		boolean testIt = false;
		String campaignName = testSupportDAO.fetchFirstCampaignName();
		List<String> emails = emailDao
				.fetchEmailsOfTypeAssopciatedToCampaignAndInterestGroup(
						campaignName, "subscriber");
		System.out.println(" campaign " + campaignName + " has " + emails.size() );
		for (String email : emails) {
			System.out.println(email);
		}
		testIt = true;
		assertTrue(testIt);
	}

}
