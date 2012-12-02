package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.model.domain.TemplateVector;

public class TemplateDao3Test {
	
	private TemplateDao templateDAO;
	private TestSupportDAO testSupportDAO;
	

	@Before
	public void setUp() throws Exception {
		templateDAO = new TemplateDao();
		testSupportDAO = new TestSupportDAO();
	}


	@Test
	public void testFetchConfigured() {
		boolean testIt = false;
		List<TemplateVector> templateVectorList = templateDAO.readTemplatesAssociatedToCampaign( testSupportDAO
				.fetchFirstCampaignName());
		for(TemplateVector templateVector : templateVectorList){
			System.out.println(templateVector);
		}
		testIt = true;
		assertTrue(testIt);
	}

}
