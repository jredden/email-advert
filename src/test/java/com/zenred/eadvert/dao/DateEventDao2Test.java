package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.model.domain.DateEvent;

public class DateEventDao2Test {
	
	private DateEventDao dateEventDao;
	private DateEvent dateEvent;
	private SimpleDateFormat dateFormat;
	private Calendar calendar;
	private TestSupportDAO testSupportDAO;


	@Before
	public void setUp() throws Exception {
		dateEventDao = new DateEventDao();
		dateEvent = new DateEvent();
		testSupportDAO = new TestSupportDAO();
	}

	
	@Test
	public void testUpdateEvent(){
		boolean testIt = false;
		String campaignName = testSupportDAO.fetchFirstCampaignName();
		DateEvent dateEvent = new DateEvent();
		dateEvent.setDateSegmentOne("2010-01-01");
		dateEvent.setDateSegmentTwo("2012-12-31");
		dateEvent.setName("testUpdate");
		dateEvent.setEventName("testEventName");
		dateEventDao.updateCampaignDateEvent(campaignName, dateEvent);
		testIt = true;
		assertTrue(testIt);
	}
}
