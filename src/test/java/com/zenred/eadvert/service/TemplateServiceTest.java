package com.zenred.eadvert.service;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.RuntimeLikeSetup;
import com.zenred.eadvert.dao.TestSupportDAO;
import com.zenred.eadvert.exception.DataAccessException;

public class TemplateServiceTest {
	private RuntimeLikeSetup runtimeLikeSetup;
	private TemplateService templateService;
	private TestSupportDAO testSupportDAO;
	
	@Before
	public void setUp(){
		runtimeLikeSetup = RuntimeLikeSetup.getRuntimeLikeSetup();
		templateService = (TemplateService)runtimeLikeSetup.getBean("templateService");
		testSupportDAO = new TestSupportDAO();
	}

	@Test
	public void genTemplateXml(){
		boolean doneIt = false;
		try {
			String xml = templateService.readTemplateContentsAssociatedToCampaign(testSupportDAO.fetchFirstCampaignName());
			doneIt = true;
			System.out.println(xml);
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
		assertTrue(doneIt);
	}
}
