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

public class EmailServiceTest {
	
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
	public void addTestEmail() throws EmailServiceException{
		boolean testIt = false;
		String campaignName = testSupportDAO.fetchFirstCampaignName();
		String emailAddress = "bogon@dibulator.com";
		emailService.addCampaignTestEmail(emailAddress, campaignName);
		testIt = true;
		assertTrue(testIt);
	}
	@Test
	public void addAdminEmail() throws EmailServiceException{
		boolean testIt = false;
		String campaignName = testSupportDAO.fetchFirstCampaignName();
		String emailAddress = "tribulator@dibulator.com";
		emailService.addCampaignAdminEmail(emailAddress, campaignName);
		testIt = true;
		assertTrue(testIt);
	}
	@Test
	public void addProviderEmail() throws EmailServiceException{
		boolean testIt = false;
		String campaignName = testSupportDAO.fetchFirstCampaignName();
		String emailAddress = "nibulator_nive@dibulator.com";
		emailService.addCampaignProviderEmail(emailAddress, campaignName);
		testIt = true;
		assertTrue(testIt);
	}

	@Test
	public void readEmailAddresses(){
		String campaignName = testSupportDAO.fetchFirstCampaignName();
		List<EMail> emails = emailService.readCampaignEmails(campaignName);
		for (EMail email : emails){
			System.out.println(email);
		}
		assertTrue(emails != null );
	}
	
}
