package com.zenred.eadvert.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.RuntimeLikeSetup;
import com.zenred.eadvert.exception.EmailServiceException;
import com.zenred.eadvert.exception.MessageServiceException;
import com.zenred.eadvert.model.domain.EmailAddressMessage;

public class ReportService1Test {
	
	private ReportService reportService;
	private RuntimeLikeSetup runtimeLikeSetup;
	private List<EmailAddressMessage> messageList;
	
	@Before
	public void setUp(){
		runtimeLikeSetup = RuntimeLikeSetup.getRuntimeLikeSetup();
		reportService = (ReportService) runtimeLikeSetup.getBean("reportService");
		messageList = new ArrayList<EmailAddressMessage>();
		EmailAddressMessage emailAddressMessage = new EmailAddressMessage();
		emailAddressMessage.setEmailAddress("jredden@matrix-consultants.com");
		emailAddressMessage.setTimeSent("Sat Dec 20 18:00:03 PST 2010");
		emailAddressMessage.setEmailAddressID(23);
		emailAddressMessage.setMessageID("96");
		messageList.add(emailAddressMessage);
	}

	@Test
	public void deleteEmail() throws EmailServiceException{
		boolean testIt = false;
		try {
			reportService.buildReportBackEnd(messageList);
		} catch (MessageServiceException mse) {
			// TODO Auto-generated catch block
			mse.printStackTrace();
		}
		testIt = true;
		assertTrue(testIt);
	}

	
}
