package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.model.domain.Message;

public class MessageDaoTest {
	
	private MessageDao messageDao;
	private Message message;
	
	@Before
	public void setUp() throws Exception {
		messageDao = new MessageDao();
		message = new Message();
	}

	@Test
	public void testInsertCampaign() {
		boolean testIt = false;
		List<String> uriList = new ArrayList<String>();
		uriList.add("http://www.email_advert.com/fckeditor/editor/images/smiley/msn/teeth_smile.gif");
		uriList.add("http://www.email_advert.com/fckeditor/editor/images/smiley/msn/lightbulb.gif");
		message.setCampaign("nabfixy");
		message.setImageUriList(uriList);
		message.setMessage("<p>&nbsp;&nbsp; <img src=\"http://www.email_advert.com/fckeditor/editor/images/smiley/msn/teeth_smile.gif\" alt=\"\" />&nbsp;&nbsp;&nbsp;&nbsp; Type in Data and/or load a Template</p><p><img src=\"http://www.email_advert.com/fckeditor/editor/images/smiley/msn/lightbulb.gif\" alt=\"\" />&nbsp;&nbsp; yes</p> ");
		message.setUri("nabfixy_Sun_Jun_20_16:56:49_PDT_2010.xhtml");
		messageDao.addReviewableMessage(message);
		testIt = true;
		assertTrue(testIt);
	}
	


}
