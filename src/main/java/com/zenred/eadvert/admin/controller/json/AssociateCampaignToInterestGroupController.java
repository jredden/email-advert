package com.zenred.eadvert.admin.controller.json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.exception.InterestServiceException;
import com.zenred.eadvert.model.view.AssociateCampaignInterestResponse;
import com.zenred.eadvert.service.InterestService;

public class AssociateCampaignToInterestGroupController implements Controller {
	
	private InterestService interestService;
	
	public void setInterestService(InterestService interestService) {
		this.interestService = interestService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String campaignName = request.getParameter("campaigns");
		String interestGroupName = request.getParameter("interestGroups");
		String status = "";
		try{
			status = interestService.assosciatedInterestGroupToCampaign(interestGroupName, campaignName);
		}
		catch(InterestServiceException ise){
			status = ise.getMessage();
		}
		AssociateCampaignInterestResponse associateCampaignInterestResponse = new AssociateCampaignInterestResponse();
		associateCampaignInterestResponse.setMessage(status);
		ModelAndView modelAndView = new ModelAndView(new AssociateCampaignToInterestGroupJsonView());
		modelAndView.addObject(AssociateCampaignToInterestGroupJsonView.JSON_ROOT, associateCampaignInterestResponse);
		return modelAndView;
	}

}
