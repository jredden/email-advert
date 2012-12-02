package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.model.domain.DateEvent;

public class DateEventDao3Test {
	
	private DateEventDao dateEventDao;

	@Before
	public void setUp() throws Exception {
		dateEventDao = new DateEventDao();
	}

	
	@Test
	public void testReadEventsReadyForProductionTransmission(){
		List<DateEvent> dateEventList = dateEventDao.readProductionMessagesForCampaigns();
		for(DateEvent dateEvent : dateEventList){
			System.out.println(dateEvent);
		}
		assertTrue(dateEventList != null);
	}
}
