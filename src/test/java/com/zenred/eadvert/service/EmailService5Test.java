package com.zenred.eadvert.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.RuntimeLikeSetup;
import com.zenred.eadvert.exception.DataAccessException;
import com.zenred.eadvert.exception.EmailServiceException;
import com.zenred.eadvert.model.domain.Message;

public class EmailService5Test {
	
	private EmailService emailService;
	private RuntimeLikeSetup runtimeLikeSetup;
	
	@Before
	public void setUp(){
		runtimeLikeSetup = RuntimeLikeSetup.getRuntimeLikeSetup();
		emailService = (EmailService) runtimeLikeSetup.getBean("emailService");
	}

	@Test
	public void promoteToQA2() throws EmailServiceException{
		boolean testIt = false;
		String campaignName = "varpevw";
		List<Message> messageList = new ArrayList<Message>();
		Message message = new Message();
		message.setCampaign(campaignName);
		message.setUri("/home/email_advert/userfiles/review/varpevw/varpevw_Tue_Jul_13_08:52:53_PDT_2010.xhtml");
		message.setVersion("0");
		messageList.add(message);
		message = new Message();
		message.setCampaign(campaignName);
		message.setUri("/home/email_advert/userfiles/review/varpevw/varpevw_Tue_Jul_13_09:34:06_PDT_2010.xhtml");
		message.setVersion("0");
		messageList.add(message);
		message = new Message();
		message.setCampaign(campaignName);
		message.setUri("/home/email_advert/userfiles/review/varpevw/varpevw_Tue_Jul_13_09:37:35_PDT_2010.xhtml");
		message.setVersion("0");
		messageList.add(message);
		try {
			emailService.promoteCampaignToQA(messageList);
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
		testIt = true;
		assertTrue(testIt);
	}
	
	
}
