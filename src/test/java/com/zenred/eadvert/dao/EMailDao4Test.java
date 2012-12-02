package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.exception.DataAccessException;
import com.zenred.eadvert.model.domain.Message;

public class EMailDao4Test {
	
	private MessageDao messageDao;
	private FileIODao fileIODao;
	private TestSupportDAO testSupportDAO;
	
	
	@Before
	public void setUp(){
		messageDao = new MessageDao();
		fileIODao = new FileIODao();
		testSupportDAO = new TestSupportDAO();
	}

	
	@Test
	public void addMessageContextTest(){
		boolean testIt = false;
		Message message = testSupportDAO.fetchNthReviewableMessage(0);
		try {
			String s_message = fileIODao.readStringFileUsingURI(message.getUri());
			message.setMessage(s_message);
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
		try {
			messageDao.addMessageContext(message);
			testIt = true;
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}
		assertTrue(testIt);
	}

}
