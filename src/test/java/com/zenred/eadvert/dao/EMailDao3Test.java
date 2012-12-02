package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.model.domain.EMail;
import com.zenred.eadvert.model.domain.EmailPlusContext;

public class EMailDao3Test {
	
	private EmailDao emailDao;
	
	
	@Before
	public void setUp(){
		emailDao = new EmailDao();
	}

	
	@Test
	public void fetchEmailsNotInCampaign(){
		boolean testIt = false;
		
		List<EmailPlusContext> emails = emailDao.fetchEmailsNotAssociatedToCampaign();
		for(EMail email: emails){
			System.out.println(email);
		}
		testIt = true;
		assertTrue(testIt);
	}

}
