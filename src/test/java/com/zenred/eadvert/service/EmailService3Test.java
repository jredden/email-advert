package com.zenred.eadvert.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.zenred.eadvert.RuntimeLikeSetup;
import com.zenred.eadvert.dao.TestSupportDAO;
import com.zenred.eadvert.exception.DataAccessException;
import com.zenred.eadvert.exception.EmailServiceException;
import com.zenred.eadvert.model.domain.Message;

public class EmailService3Test {
	
	private EmailService emailService;
	private RuntimeLikeSetup runtimeLikeSetup;
	private TestSupportDAO testSupportDAO;
	
	@Before
	public void setUp(){
		runtimeLikeSetup = RuntimeLikeSetup.getRuntimeLikeSetup();
		emailService = (EmailService) runtimeLikeSetup.getBean("emailService");
		testSupportDAO = new TestSupportDAO();
	}
/*
 * 
:::/home/email_advert/userfiles/review/mumblesOne/mumblesOne_Mon_Jun_28_14:41:18_PDT_2010.xhtm++3++
:::/home/email_advert/userfiles/review/mumblesOne/mumblesOne_Tue_Jun_29_08:20:49_PDT_2010.xhtm++3++
:::/home/email_advert/userfiles/review/mumblesOne/mumblesOne_Tue_Jun_29_08:47:28_PDT_2010.xhtm++1++
:::/home/email_advert/userfiles/review/mumblesOne/mumblesOne_Tue_Jun_29_08:19:56_PDT_2010.xhtm++2++

 */
	@Test
	public void promoteToQA() throws EmailServiceException{
		boolean testIt = false;
		String campaignName = testSupportDAO.fetchFirstCampaignName();
		List<Message> messageList = new ArrayList<Message>();
		Message message = new Message();
		message.setCampaign(campaignName);
		message.setUri("/home/email_advert/userfiles/review/mumblesOne/mumblesOne_Mon_Jun_28_14:41:18_PDT_2010.xhtml");
		message.setVersion("3");
		messageList.add(message);
		message = new Message();
		message.setCampaign(campaignName);
		message.setUri("/home/email_advert/userfiles/review/mumblesOne/mumblesOne_Tue_Jun_29_08:20:49_PDT_2010.xhtml");
		message.setVersion("3");
		messageList.add(message);
		message = new Message();
		message.setCampaign(campaignName);
		message.setUri("/home/email_advert/userfiles/review/mumblesOne/mumblesOne_Tue_Jun_29_08:47:28_PDT_2010.xhtml");
		message.setVersion("1");
		messageList.add(message);
		message = new Message();
		message.setCampaign(campaignName);
		message.setUri("/home/email_advert/userfiles/review/mumblesOne/mumblesOne_Mon_Jun_28_13:44:02_PDT_2010.xhtml");
		message.setVersion("2");
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
