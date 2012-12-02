package com.zenred.eadvert.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.RuntimeLikeSetup;
import com.zenred.eadvert.model.domain.Message;

public class EmailService8Test {

	private EmailService emailService;
	private RuntimeLikeSetup runtimeLikeSetup;
	
	@Before
	public void setUp(){
		runtimeLikeSetup = RuntimeLikeSetup.getRuntimeLikeSetup();
		emailService = (EmailService) runtimeLikeSetup.getBean("emailService");
	}

	@Test
	public void testEligibleMessagesForProduction(){
		String message = "<p>If you do not wish to receive future e-mails from our system click on this link <a href=\"www.email_advert.com/optOut.do?emailAddress=jredden@matrix-consultants.com\">Please un-register my e-mail address</a></p>";
		String modifiedMessage = emailService.replaceQAEMailAvecSubscribers(message, "mz@matrix-consultants.com");
		System.out.println(modifiedMessage);
		assertTrue(modifiedMessage.length() > 0);
	}

}
