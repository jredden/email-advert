package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.model.domain.DateEvent;

public class TemplateDaoTest {
	
	private TemplateDao templateDAO;
	private TestSupportDAO testSupportDAO;
	

	@Before
	public void setUp() throws Exception {
		templateDAO = new TemplateDao();
		testSupportDAO = new TestSupportDAO();
	}

	@Test
	public void testFetchUnConfigured() {
		boolean testIt = false;
		List<String> templates = templateDAO.fetchTemplatesCestnPasEnCampaign(testSupportDAO.fetchFirstCampaignName());
		testIt = true;
		System.out.println(templates);
		assertTrue(testIt);
	}
	
	@Test
	public void testFetchConfigured(){
		boolean testIt = false;
		List<String> templates = templateDAO.fetchTemplatesCestEnCampaign(testSupportDAO.fetchFirstCampaignName());
		testIt = true;
		System.out.println(templates);
		assertTrue(testIt);

	}

}
