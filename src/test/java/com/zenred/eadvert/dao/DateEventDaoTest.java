package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.model.domain.DateEvent;

public class DateEventDaoTest {
	
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
	public void testInsertDateEvent() {
		boolean testIt = false;
		dateEvent.setName("ground hog day");
		dateEvent.setCampaignName("mumblesOne");
		dateEvent.setEventName("cyclic event");
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(calendar.getTime());
		dateEvent.setDateSegmentOne(date);
		dateEvent.setDateSegmentTwo(date);
		dateEventDao.insertDateEvent(dateEvent);
		testIt = true;
		assertTrue(testIt);
	}

	@Test
	public void testReadEvent(){
		boolean testIt = false;
		String campaignName = testSupportDAO.fetchFirstCampaignName();
		System.out.println(campaignName);
		System.out.println(dateEventDao.readCampaignDateEvent(campaignName));
		testIt = true;
		assertTrue(testIt);
	}
}
