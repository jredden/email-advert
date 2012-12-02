package com.zenred.eadvert.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.RuntimeLikeSetup;
import com.zenred.eadvert.model.domain.Message;

public class EmailService6Test {

	private EmailService emailService;
	private RuntimeLikeSetup runtimeLikeSetup;
	
	@Before
	public void setUp(){
		runtimeLikeSetup = RuntimeLikeSetup.getRuntimeLikeSetup();
		emailService = (EmailService) runtimeLikeSetup.getBean("emailService");
	}

	@Test
	public void testEligibleMessagesForProduction(){
		boolean result = false;
		assertTrue(null != emailService);
		List<Message> messageList = emailService.readMessagesThatCouldBeProductionReady();
		result = true;
		for(Message message:messageList){
			System.out.println(message);
		}
		assertTrue(result);
	}

}
