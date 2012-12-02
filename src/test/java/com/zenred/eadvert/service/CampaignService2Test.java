package com.zenred.eadvert.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.RuntimeLikeSetup;
import com.zenred.eadvert.dao.TestSupportDAO;
import com.zenred.eadvert.exception.CampaignServiceException;
import com.zenred.eadvert.model.domain.DateEvent;

public class CampaignService2Test {
	
	private CampaignService campaignService;
	private RuntimeLikeSetup runtimeLikeSetup;
	private TestSupportDAO testSupportDAO;

	private static List<Character> clist = new ArrayList<Character>();
	static{
		clist.add(new Character('a'));
		clist.add(new Character('e'));
		clist.add(new Character('i'));
		clist.add(new Character('o'));
		clist.add(new Character('u'));
	}
	private static String[] vlist = {
		"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","y","z","p","q","r","s","t","v","w","x","y","z"
	};

	
	@Before
	public void setUp(){
		runtimeLikeSetup = RuntimeLikeSetup.getRuntimeLikeSetup();
		campaignService = (CampaignService) runtimeLikeSetup.getBean("campaignService");
		testSupportDAO = new TestSupportDAO();
	}

	@Test
	public void updateCampaign(){
		String name = randomName();
		String name2 = randomName();
		
		boolean result = false;
		String campaignName = testSupportDAO.fetchFirstCampaignName();
		DateEvent dateEvent = new DateEvent();
		dateEvent.setCampaignName(campaignName);
		dateEvent.setDateSegmentOne("01/01/0001");   // year 1
		dateEvent.setDateSegmentTwo("01/01/9999");	 // year 9999
		dateEvent.setEventName(name);
		dateEvent.setName(name2);
		try {
			campaignService.updateCampaignDateEvent(dateEvent);
			result = true;
		} catch (CampaignServiceException cse) {
			cse.printStackTrace();
		}
		assertTrue(result);
	}

	private String randomName() {
		int r0 = (int) (Math.random()*clist.size());
		int r1 = (int) (Math.random()*clist.size());
		int r2 = (int) (Math.random()*vlist.length);
		int r3 = (int) (Math.random()*vlist.length);
		int r4 = (int) (Math.random()*vlist.length);
		int r5 = (int) (Math.random()*vlist.length);
		int r6 = (int) (Math.random()*vlist.length);
		
		String name = vlist[r2]+clist.get(r0)+vlist[r3]+vlist[r4]+clist.get(r1)+vlist[r5]+vlist[r6];
		return name;
	}
}
