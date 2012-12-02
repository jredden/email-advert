package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.model.domain.Message;

public class Messasge6DaoTest {

	private MessageDao messageDao;
	
	@Before
	public void setUp() throws Exception {
		messageDao = new MessageDao();
	}

	@Test
	public void testUpdateMessageContext(){
		boolean result = false;
		List<Message> messageList = messageDao.readMessagesReadyForQAToPromoteToProduction();
		for(Message message : messageList){
			System.out.println(message);
		}
		result = true;
		assertTrue(result);
	}
}
