package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.model.domain.EMail;

public class EMailDaoTest {
	
	private EmailDao emailDao;
	private EMail email;
	private TestSupportDAO testSupportDAO;
	
	
	@Before
	public void setUp(){
		emailDao = new EmailDao();
		email = new EMail();
		testSupportDAO = new TestSupportDAO();
		email.setAddress("testaddress@test.org");
		email.setType(EMail.mailType.test);
	}

	@Test
	public void addEmail(){
		boolean testIt = false;
		emailDao.addEmail(email);
		testIt = true;
		assertTrue(testIt);
	}
	
	@Test
	public void addEmailCampaign(){
		boolean testIt = false;
		String campaignName = testSupportDAO.fetchFirstCampaignName();
		String oemId = testSupportDAO.fetchCampiagnOemID(campaignName);
		String oemName = testSupportDAO.fetchOemName(oemId);
		emailDao.addEmailCampaignAssociations(email, campaignName);
		emailDao.addEmailOemAssociations(email, oemName);
		testIt = true;
		assertTrue(testIt);
	}
	
	@Test
	public void deleteEmail(){
		boolean testIt = false;
		emailDao.deleteEmail(email);
		testIt = true;
		assertTrue(testIt);
	}
}
