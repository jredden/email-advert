package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class Message4DaoTest {
	
	private MessageDao messageDao;
	private TestSupportDAO testSupportDAO;
	
	@Before
	public void setUp() throws Exception {
		messageDao = new MessageDao();
		testSupportDAO = new TestSupportDAO();
	}

	@Test
	public void testReadGraphicsURI() {
		boolean testIt = false;
		String messageName = testSupportDAO.fetchNthMessageName(0);
		List<String> graphicUids = messageDao.readGraphicUidsAssociatedToMessage(messageName);
		System.out.println("ReadGraphicsURI test one");
		for(String graphicsUid : graphicUids){
			System.out.println(graphicsUid);
		}
		testIt = true;
		assertTrue(testIt);
	}
	
	@Test
	public void testReadGraphicsURI2() {
		boolean testIt = false;
		String messageName = testSupportDAO.fetchNthMessageName(1);
		List<String> graphicUids = messageDao.readGraphicUidsAssociatedToMessage(messageName);
		System.out.println("ReadGraphicsURI test two");
		for(String graphicsUid : graphicUids){
			System.out.println(graphicsUid);
		}
		testIt = true;
		assertTrue(testIt);
	}
}
