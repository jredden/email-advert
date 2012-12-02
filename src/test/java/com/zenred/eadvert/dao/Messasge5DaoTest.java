package com.zenred.eadvert.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.model.domain.MessageContext;
import com.zenred.eadvert.model.domain.MessageContext.State;

public class Messasge5DaoTest {

	private MessageDao messageDao;
	private TestSupportDAO testSupportDAO;
	
	@Before
	public void setUp() throws Exception {
		messageDao = new MessageDao();
		testSupportDAO = new TestSupportDAO();
	}

	@Test
	public void testUpdateMessageContext(){
		String contentMD5 = testSupportDAO.fetchFirstMessageContextMD5();
		MessageContext messageContext = new MessageContext();
		messageContext.setContentMD5(contentMD5);
		messageContext.setMc_state(State.production);
		messageContext.setPlenusMD5("1234509876");
		boolean result = false;
		messageDao.updateMessageContext(messageContext);
		result = true;
		assertTrue(result);
	}
}
