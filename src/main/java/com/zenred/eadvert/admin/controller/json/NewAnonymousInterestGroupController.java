package com.zenred.eadvert.admin.controller.json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.exception.InterestServiceException;
import com.zenred.eadvert.model.view.NewAnonymousInterestGroupResponse;
import com.zenred.eadvert.service.InterestService;

public class NewAnonymousInterestGroupController implements Controller {
	
	private InterestService interestService;


	
	public void setInterestService(InterestService interestService) {
		this.interestService = interestService;
	}



	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String interestGroupName = request.getParameter("interestGroupName");
		String status = "";
		try {
			status = interestService
					.createAnonymousInterestGroup(interestGroupName);
		} catch (InterestServiceException ise) {
			status = ise.getMessage();
		}
		NewAnonymousInterestGroupResponse newAnonymousInterestGroupResponse = new NewAnonymousInterestGroupResponse();
		newAnonymousInterestGroupResponse.setMessage(status);
		ModelAndView modelAndView = new ModelAndView(new NewAnonymousInterestGroupJsonView());
		modelAndView.addObject(NewAnonymousInterestGroupJsonView.JSON_ROOT, newAnonymousInterestGroupResponse);
		return modelAndView;
	}

}
