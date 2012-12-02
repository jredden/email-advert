package com.zenred.eadvert.admin.controller.json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.view.CreateCampaignResponse;
import com.zenred.eadvert.service.CampaignService;

public class StoreCampaignController implements Controller {
	
	private CampaignService campaignService;


	/**
	 * @param campaignService the campaignService to set
	 */
	public void setCampaignService(CampaignService campaignService) {
		this.campaignService = campaignService;
	}


	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String dates = request.getParameter("campaignDates");
		String [] date_array = dates.split(",");
		String campaignName = request.getParameter("campaignName").replace(' ', '_');
		String oem = request.getParameter("oems");
		campaignService.addCampaign(date_array[0], date_array[1], campaignName, oem);
		
		CreateCampaignResponse createCampaignRespone = new CreateCampaignResponse();
		createCampaignRespone.setMessage("campaign "+campaignName + " created successfully");
		
		ModelAndView modelAndView = new ModelAndView(new StoreCampaignJsonView());
		modelAndView.addObject(AbstractJsonView.JSON_ROOT, createCampaignRespone);
		return modelAndView;
	}

}
