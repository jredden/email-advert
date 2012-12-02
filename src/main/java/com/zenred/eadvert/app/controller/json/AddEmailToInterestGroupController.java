package com.zenred.eadvert.app.controller.json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.exception.InterestServiceException;
import com.zenred.eadvert.model.view.AddEmailToInterestGroupResponse;
import com.zenred.eadvert.service.InterestService;

public class AddEmailToInterestGroupController implements Controller {
	public static String INTERESTGROUP = "interestgroup";
	public static String EMAILADDRESS = "email";

	private InterestService interestService;

	public void setInterestService(InterestService interestService) {
		this.interestService = interestService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String emailAddress = request.getParameter(EMAILADDRESS);
		String interestGroup = request.getParameter(INTERESTGROUP);
		AddEmailToInterestGroupResponse addEmailToInterestGroupResponse = new AddEmailToInterestGroupResponse();
		ModelAndView _model_and_view = new ModelAndView(
				new AddEmailToInterestGroupView());
		if(null == emailAddress){
			addEmailToInterestGroupResponse.setMessage("Error: email address is undefined");
			_model_and_view.addObject(AddEmailToInterestGroupView.JSON_ROOT,
					addEmailToInterestGroupResponse);
			return _model_and_view;
		}
		if(null == interestGroup){
			addEmailToInterestGroupResponse.setMessage("Error: interest group is undefined");
			_model_and_view.addObject(AddEmailToInterestGroupView.JSON_ROOT,
					addEmailToInterestGroupResponse);
			return _model_and_view;
		}

		String answer = "Success ";
		try {
			interestService.addEmailAddressToInterestGroup(emailAddress,
					interestGroup);
		} catch (InterestServiceException ise) {
			answer = ise.getMessage();
		}
		addEmailToInterestGroupResponse.setMessage(answer);
		_model_and_view.addObject(AddEmailToInterestGroupView.JSON_ROOT,
				addEmailToInterestGroupResponse);
		return _model_and_view;
	}

}
