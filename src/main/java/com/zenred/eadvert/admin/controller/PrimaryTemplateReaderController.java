package com.zenred.eadvert.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.service.TemplateService;

public class PrimaryTemplateReaderController implements Controller {
	
	private TemplateService templateService;

	/**
	 * @param templateService the templateService to set
	 */
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String campaignName = (String) session.getAttribute("campaign");
		response.getWriter().println(templateService.readTemplateContentsAssociatedToCampaign(campaignName));
		return null;
	}

}
