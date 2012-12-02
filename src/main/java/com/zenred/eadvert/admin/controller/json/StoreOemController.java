package com.zenred.eadvert.admin.controller.json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.view.CreateOemResponse;
import com.zenred.eadvert.service.CampaignService;
import com.zenred.eadvert.service.EmailService;

public class StoreOemController implements Controller {

	private CampaignService campaignService;
	private EmailService emailService;

	/**
	 * @param campaignService
	 *            the campaignService to set
	 */
	public void setCampaignService(CampaignService campaignService) {
		this.campaignService = campaignService;
	}

	/**
	 * @param emailService
	 *            the emailService to set
	 */
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String oemName = request.getParameter("oemName");
		campaignService.addOem(oemName);
		String emailAddress = request.getParameter("emailAddress");
		emailService.addOemProviderEmail(emailAddress, oemName);
		CreateOemResponse createOemResponse = new CreateOemResponse();
		createOemResponse
				.setMessage("oem " + oemName + " created successfully");
		ModelAndView modelAndView = new ModelAndView(new StoreOemJsonView());
		modelAndView.addObject(AbstractJsonView.JSON_ROOT, createOemResponse);
		return modelAndView;
	}

}
