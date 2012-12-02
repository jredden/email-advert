package com.zenred.eadvert.admin.controller.json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.exception.InterestServiceException;
import com.zenred.eadvert.model.view.AssociateProviderInterestResponse;
import com.zenred.eadvert.service.InterestService;

public class AssociateProviderToInterestGroupController implements Controller {
	
	private InterestService interestService;
	
	public void setInterestService(InterestService interestService) {
		this.interestService = interestService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String providerName = request.getParameter("providers");
		String interestGroupName = request.getParameter("interestGroups");
		String status = "";
		try{
			status = interestService.associatedInterestedGroupToProvider(interestGroupName, providerName);
		}
		catch(InterestServiceException ise){
			status = ise.getMessage();
		}
		AssociateProviderInterestResponse associateProviderInterestResponse = new AssociateProviderInterestResponse();
		associateProviderInterestResponse.setMessage(status);
		ModelAndView modelAndView = new ModelAndView(new AssociateProviderToInterestGroupJsonView());
		modelAndView.addObject(AssociateProviderToInterestGroupJsonView.JSON_ROOT, associateProviderInterestResponse);
		return modelAndView;
	}

}
