package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.model.domain.Message;

public class Message2DaoTest {
	
	private MessageDao messageDao;
	
	@Before
	public void setUp() throws Exception {
		messageDao = new MessageDao();
	}

	@Test
	public void testReadReviewable() {
		boolean testIt = false;
		List<Message> messages = messageDao.readReviewableMessageIdenties("nabfixy");
		for(Message message : messages){
			System.out.println(message);
		}
		testIt = true;
		assertTrue(testIt);
	}
	
	@Test
	public void testReadReviewable2() {
		boolean testIt = false;
		List<Message> messages = messageDao.readReviewableMessageIdenties("mumblesOne");
		for(Message message : messages){
			System.out.println(message);
		}
		testIt = true;
		assertTrue(testIt);
	}


}
