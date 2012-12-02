package com.zenred.eadvert.admin.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import com.zenred.eadvert.RandomName;
import com.zenred.eadvert.RuntimeLikeSetup;
import com.zenred.eadvert.admin.controller.json.StoreCampaignUpdatesController;
import com.zenred.eadvert.dao.TestSupportDAO;
import com.zenred.eadvert.model.domain.CampaignInformation;
import com.zenred.eadvert.model.domain.EMail;

import junit.framework.TestCase;

public class StoreCampaignUpdateControllerTest extends TestCase {
	
	private MockHttpServletRequest mockHttpServletRequest;
	private MockHttpServletResponse mockHttpServletResponse;
	private MockHttpSession mockHttpSession;
	private TestSupportDAO testSupportDAO;
	private StoreCampaignUpdatesController storeCampaignUpdatesController;
	private String campaignName;
	
	@Before
	public void setUp(){
		RuntimeLikeSetup runTimeLikeSetup = RuntimeLikeSetup.getRuntimeLikeSetup();
		mockHttpServletRequest = new MockHttpServletRequest();
		mockHttpServletResponse = new MockHttpServletResponse();
		mockHttpSession = new MockHttpSession();
		mockHttpServletRequest.setSession(mockHttpSession);
		testSupportDAO = new TestSupportDAO();
		storeCampaignUpdatesController = (StoreCampaignUpdatesController) runTimeLikeSetup.getBean("storeCampaignUpdatesController");
		campaignName = testSupportDAO.fetchFirstCampaignName();
	}
	
	@Test
	public void testBasicStoreCampaignController(){
		System.out.println("campaignName:"+campaignName);
		mockHttpServletRequest.addParameter(CampaignInformation.CAMPAIGN_NAME, campaignName);
		mockHttpServletRequest.addParameter(CampaignInformation.NAME, RandomName.randomName());
		mockHttpServletRequest.addParameter(CampaignInformation.EVENT_NAME, RandomName.randomName());
		mockHttpServletRequest.addParameter(CampaignInformation.EMAIL_ADDRESS, RandomName.randomName()+"@"+RandomName.randomName()+".org");
		mockHttpServletRequest.addParameter(CampaignInformation.GROUP_EMAIL_ADDRESS, "test");
		mockHttpServletRequest.addParameter(CampaignInformation.CAMPAIGN_DATE1, "01/01/0001");
		mockHttpServletRequest.addParameter(CampaignInformation.CAMPAIGN_DATE2, "01/01/9999");
		ModelAndView modelAndView = null;
		boolean testIt = false;
		try {
			modelAndView = storeCampaignUpdatesController.handleRequest(mockHttpServletRequest, mockHttpServletResponse);
			System.out.println(modelAndView);
			testIt = true;
		} catch (Exception gene) {
			gene.printStackTrace();
		}
		assertTrue(testIt);
	}

}
