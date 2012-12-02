package com.zenred.eadvert.admin.controller.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.domain.DateEvent;
import com.zenred.eadvert.model.domain.EMail;
import com.zenred.eadvert.model.view.UpdateCampaignResponse;
import com.zenred.eadvert.service.CampaignService;
import com.zenred.eadvert.service.EmailService;
import com.zenred.eadvert.service.TemplateService;

public class UpdateCampaignController implements Controller {

	private CampaignService campaignService;
	private EmailService emailService;
	private TemplateService templateService;

	/**
	 * @param campaignService the campaignService to set
	 */
	public void setCampaignService(CampaignService campaignService) {
		this.campaignService = campaignService;
	}

	/**
	 * @param emailService the emailService to set
	 */
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	/**
	 * @param templateService the templateService to set
	 */
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}
	
	private void genPrimtiveListsForJson(List<EMail> campaignEmails, UpdateCampaignResponse updateCampaignResponse){
		List<String> campaignEmailAddresses = new ArrayList<String>();
		List<String> campaignEmailTypes = new ArrayList<String>();
		for(EMail email : campaignEmails){
			campaignEmailAddresses.add(email.getAddress());
			campaignEmailTypes.add(email.getType().toString());
		}
		updateCampaignResponse.setCampaignEmailAddresses(campaignEmailAddresses);
		updateCampaignResponse.setCampaignEmailTypes(campaignEmailTypes);
	}


	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String campaignName = request.getParameter("campaigns");
		DateEvent dateEvent = campaignService.readCampaignDateEvent(campaignName);
		List<EMail> campaignEmails = emailService.readCampaignEmails(campaignName);
		List<String> campaignTemplates = templateService.fetchTemplatesInCampaign(campaignName);
		List<String> notCampaignTemplates = templateService.fetchTemplatesNotInCampaign(campaignName);
		UpdateCampaignResponse updateCampaignResponse = new UpdateCampaignResponse();
		updateCampaignResponse.setDateEvent(dateEvent);	
		updateCampaignResponse.setAssociatedTemplates(campaignTemplates);
		updateCampaignResponse.setUnAssociatedTemplates(notCampaignTemplates);
		genPrimtiveListsForJson(campaignEmails, updateCampaignResponse);
		ModelAndView modelAndView = new ModelAndView(new UpdateCampaignJsonView());
		modelAndView.addObject(UpdateCampaignJsonView.JSON_ROOT, updateCampaignResponse);
		return modelAndView;
	}

}
