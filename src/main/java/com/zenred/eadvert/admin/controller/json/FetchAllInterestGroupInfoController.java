package com.zenred.eadvert.admin.controller.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.domain.Campaign;
import com.zenred.eadvert.model.domain.InterestGroup;
import com.zenred.eadvert.model.view.InterestGroupInfoResponse;
import com.zenred.eadvert.service.InterestService;

public class FetchAllInterestGroupInfoController  implements Controller  {
	
	private InterestService interestService;

	public void setInterestService(InterestService interestService) {
		this.interestService = interestService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<InterestGroup> interestGroupList = interestService.fetchInterestGroupInformation();
		List<InterestGroupInfoResponse> interestGroupInfoResponseList = new ArrayList<InterestGroupInfoResponse>();
		for(InterestGroup interestGroup : interestGroupList){
			InterestGroupInfoResponse interestGroupInfoResponse = new InterestGroupInfoResponse();
			interestGroupInfoResponse.setInterestGroup(interestGroup.getInterestGroupName());			
			interestGroupInfoResponse.setOemList(interestGroup.getProviderList());
			List<Campaign> campaignList = interestGroup.getCampaignList();
			List<String> campaignNameList = new ArrayList<String>();
			for(Campaign campaign : campaignList){
				campaignNameList.add(campaign.getName());
			}
			interestGroupInfoResponse.setCampaignList(campaignNameList);
			interestGroupInfoResponseList.add(interestGroupInfoResponse);
		}
		ModelAndView modelAndView = new ModelAndView(new FetchAllInterestGroupInfoJsonView());
		modelAndView.addObject(FetchAllInterestGroupInfoJsonView.JSON_ROOT, interestGroupInfoResponseList);
		return modelAndView;
	}

}
