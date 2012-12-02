package com.zenred.eadvert.admin.controller.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.domain.Message;
import com.zenred.eadvert.model.view.SubmitDeleteMessageResponse;
import com.zenred.eadvert.model.view.SubmitToQAResponse;
import com.zenred.eadvert.service.CampaignService;

public class DeleteMessageController implements Controller {
	
	private CampaignService campaignService;

	public void setCampaignService(CampaignService campaignService) {
		this.campaignService = campaignService;
	}

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String campaignName = request.getParameter("campaigns");
		List<Message> messageList = campaignService.fetchAllCampaignsMessages(campaignName);
		List<SubmitDeleteMessageResponse> listDeleteMessageResponse  = new ArrayList<SubmitDeleteMessageResponse>();
		for(Message message : messageList){
			SubmitDeleteMessageResponse submitDeleteMessageResponse = new SubmitDeleteMessageResponse();
			submitDeleteMessageResponse.setCampaign(campaignName);
			submitDeleteMessageResponse.setUri(message.getUri());
			submitDeleteMessageResponse.setVersion(message.getVersion());
			listDeleteMessageResponse.add(submitDeleteMessageResponse);
		}

		ModelAndView modelAndView = new ModelAndView(new DeleteMessageJsonView());
		modelAndView.addObject(DeleteMessageJsonView.JSON_ROOT, listDeleteMessageResponse);
		return modelAndView;
	}

}
