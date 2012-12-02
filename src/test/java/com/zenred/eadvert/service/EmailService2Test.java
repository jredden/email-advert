package com.zenred.eadvert.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.zenred.eadvert.RuntimeLikeSetup;
import com.zenred.eadvert.dao.TestSupportDAO;
import com.zenred.eadvert.exception.EmailServiceException;
import com.zenred.eadvert.model.domain.EMail;

public class EmailService2Test {
	
	private EmailService emailService;
	private RuntimeLikeSetup runtimeLikeSetup;
	private TestSupportDAO testSupportDAO;
	
	@Before
	public void setUp(){
		runtimeLikeSetup = RuntimeLikeSetup.getRuntimeLikeSetup();
		emailService = (EmailService) runtimeLikeSetup.getBean("emailService");
		testSupportDAO = new TestSupportDAO();
	}

	@Test
	public void deleteEmail() throws EmailServiceException{
		boolean testIt = false;
		String campaignName = testSupportDAO.fetchFirstCampaignName();
		String emailAddress = "bogon@dibulator.com";
		emailService.deleteCampaignTestEmail(emailAddress, campaignName);
		testIt = true;
		assertTrue(testIt);
	}

	
}
