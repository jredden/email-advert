package com.zenred.eadvert.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.service.EmailService;

public class PrimaryOptionOutController  implements Controller  {
	
	private EmailService emailService;

	/**
	 * @param emailService the emailService to set
	 */
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String emailAddress = request.getParameter("emailAddress");
		emailService.emailAddressOptsOut(emailAddress);
		return null;
	}

}
