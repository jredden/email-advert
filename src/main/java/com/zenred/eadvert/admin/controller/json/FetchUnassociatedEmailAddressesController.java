package com.zenred.eadvert.admin.controller.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.domain.EmailPlusContext;
import com.zenred.eadvert.model.view.ReadUnassocEmailsResponse;
import com.zenred.eadvert.service.EmailService;

public class FetchUnassociatedEmailAddressesController implements Controller {

	private EmailService emailService;
	
	/**
	 * @param emailService the emailService to set
	 */
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<EmailPlusContext> emailsList = emailService.readEmailsNotAssosciatedToCampaign();
		List<ReadUnassocEmailsResponse> unassocEmails = new ArrayList<ReadUnassocEmailsResponse>();
		for(EmailPlusContext email : emailsList){
			ReadUnassocEmailsResponse readUnassocEmailsResponse = new ReadUnassocEmailsResponse();
			readUnassocEmailsResponse.setAddress(email.getAddress());
			readUnassocEmailsResponse.setEventName(email.getEventName());
			readUnassocEmailsResponse.setName(email.getName());
			readUnassocEmailsResponse.setType(email.getType().toString());
			unassocEmails.add(readUnassocEmailsResponse);
		}
		ModelAndView modelAndView = new ModelAndView(new FetchUnassociatedEmailsJsonView());
		modelAndView.addObject(FetchUnassociatedEmailsJsonView.JSON_ROOT, unassocEmails);
		return modelAndView;
	}

}
