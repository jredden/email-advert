package com.zenred.eadvert.service;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.RuntimeLikeSetup;
import com.zenred.eadvert.exception.DataAccessException;
import com.zenred.eadvert.model.domain.Message;
import com.zenred.eadvert.model.domain.ProductionMessage;

public class EmailService7Test {

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
		Message message = messageList.get(0);
		ProductionMessage productionMessage = new ProductionMessage();
		productionMessage.setCampaign(message.getCampaign());
		productionMessage.setSendOffDate("10/10/2010");
		productionMessage.setUri(message.getUri());
		productionMessage.setVersion(message.getVersion());
		Map<String, ProductionMessage> prodMessageMap = new HashMap<String, ProductionMessage>();
		prodMessageMap.put("ooga-booga1", productionMessage);
		message = messageList.get(1);
		productionMessage.setCampaign(message.getCampaign());
		productionMessage.setSendOffDate("09/10/2010");
		productionMessage.setUri(message.getUri());
		productionMessage.setVersion(message.getVersion());
		prodMessageMap.put("ooga-booga2", productionMessage);
		try {
			emailService.promoteToProduction(prodMessageMap);
			result = true;	
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
		assertTrue(result);
	}

}
