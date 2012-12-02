package com.zenred.eadvert.service;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.RuntimeLikeSetup;
import com.zenred.eadvert.exception.EmailServiceException;
import com.zenred.eadvert.exception.InterestServiceException;

public class InterestService1Test {

	private RuntimeLikeSetup runtimeLikeSetup;
	private InterestService interestService;
	private EmailService emailService;

	@Before
	public void setUp() {
		runtimeLikeSetup = RuntimeLikeSetup.getRuntimeLikeSetup();
		interestService = (InterestService) runtimeLikeSetup
				.getBean("interestService");
		emailService = (EmailService) runtimeLikeSetup.getBean("emailService");
	}

	@Test
	public void theTest() {
		boolean testIt = false;
		try {
			interestService.addEmailAddressToInterestGroup("rub@yoyodyne.com",
					"coffee");
			testIt = true;
		} catch (InterestServiceException ise) {
			ise.printStackTrace();
		}
		try {
			emailService
					.deleteCampaignSubscriberEmail("rub@yoyodyne.com", null);
		} catch (EmailServiceException ese) {
			testIt = false;
			ese.printStackTrace();
		}
		assertTrue(testIt);
	}
}
