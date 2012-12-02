package com.zenred.eadvert.admin.controller.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.view.ReadCampaignNamesResponse;
import com.zenred.eadvert.service.SubscriberService;

public class FetchUsersCampaignsController implements Controller {
	
	private SubscriberService subscriberService;

	public void setSubscriberService(SubscriberService subscriberService) {
		this.subscriberService = subscriberService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userName = request.getSession().getAttribute("userName").toString();
		List<String> campaignNameList = subscriberService.accessCampaignsAssociatedToUser(userName);
		List<ReadCampaignNamesResponse> responseList = new ArrayList<ReadCampaignNamesResponse>();
		for(String name : campaignNameList){
			ReadCampaignNamesResponse readCampaignNamesResponse = new ReadCampaignNamesResponse();
			readCampaignNamesResponse.setcampaignName(name);
			responseList.add(readCampaignNamesResponse);
		}
		ModelAndView modelAndView = new ModelAndView(new FetchUserCampaignsJsonView());
		modelAndView.addObject(FetchUserCampaignsJsonView.JSON_ROOT, responseList);
		return modelAndView;
	}

}
