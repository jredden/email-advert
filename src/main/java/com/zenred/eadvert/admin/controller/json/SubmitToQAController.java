package com.zenred.eadvert.admin.controller.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.exception.CampaignServiceException;
import com.zenred.eadvert.model.domain.Message;
import com.zenred.eadvert.model.view.SubmitToQAResponse;
import com.zenred.eadvert.service.CampaignService;

public class SubmitToQAController implements Controller {
	
	private CampaignService campaignService;

	/**
	 * @param campaignService the campaignService to set
	 */
	public void setCampaignService(CampaignService campaignService) {
		this.campaignService = campaignService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String campaignName = request.getParameter("campaigns");
		
		if(!campaignService.isCampaignInCurrentDateRange(campaignName)){
			SubmitToQAResponse submitToQAResponse = new SubmitToQAResponse();
			submitToQAResponse.setError(campaignName + " has an out scope date");
			ModelAndView modelAndView = new ModelAndView(new SubmitToQAJsonView());
			modelAndView.addObject(SubmitToQAJsonView.JSON_ROOT, submitToQAResponse);
			return modelAndView;
		}

		List<Message> messageList = campaignService.promoteCampaignToQAPossibleMessages(campaignName);
		List<SubmitToQAResponse> responseList = new ArrayList<SubmitToQAResponse>();

		for(Message message : messageList){
			SubmitToQAResponse submitToQAResponse = new SubmitToQAResponse();
			submitToQAResponse.setCampaign(campaignName);
			submitToQAResponse.setUri(message.getUri());
			submitToQAResponse.setVersion(message.getVersion());
			responseList.add(submitToQAResponse);
		}
		ModelAndView modelAndView = new ModelAndView(new SubmitToQAJsonView());
		modelAndView.addObject(SubmitToQAJsonView.JSON_ROOT, responseList);
		return modelAndView;
	}

}
