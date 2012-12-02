package com.zenred.eadvert.admin.controller.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.view.ReadOemsResponse;
import com.zenred.eadvert.service.CampaignService;

public class FetchOemsController implements Controller {

	private CampaignService campaignService;


	/**
	 * @param campaignService the campaignService to set
	 */
	public void setCampaignService(CampaignService campaignService) {
		this.campaignService = campaignService;
	}


	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<String> oems = campaignService.readOems();
		List<ReadOemsResponse> oemsList = new ArrayList<ReadOemsResponse>();
		for (String oem : oems) {
			ReadOemsResponse readOemsResponse = new ReadOemsResponse();
			readOemsResponse.setOem(oem);
			oemsList.add(readOemsResponse);
		}
		ModelAndView modelAndView = new ModelAndView(new FetchOemsJsonView());
		modelAndView.addObject(FetchOemsJsonView.JSON_ROOT, oemsList);
		return modelAndView;
	}

}
