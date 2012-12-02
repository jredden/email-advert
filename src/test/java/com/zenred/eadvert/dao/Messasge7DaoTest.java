package com.zenred.eadvert.dao;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.model.domain.MessageContext;

public class Messasge7DaoTest {

	private MessageDao messageDao;
	private TestSupportDAO testSupportDAO;
	
	@Before
	public void setUp() throws Exception {
		messageDao = new MessageDao();
		testSupportDAO = new TestSupportDAO();
	}

	@Test
	public void testReadMessageContext() {
		Map<String, Object> messageMap = testSupportDAO
				.fetchFirstMessageWithMessageContext();
		boolean result = false;
		MessageContext messageContext = messageDao
				.readMessageContextAssociatedToMessage(
						messageMap.get("Name").toString(),
						messageMap.get("Revision").toString());
		result = true;
		System.out.println(messageContext);
		assertTrue(result);
	}
}
