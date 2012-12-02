package com.zenred.eadvert.admin.controller.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.domain.DateEvent;
import com.zenred.eadvert.model.domain.Message;
import com.zenred.eadvert.model.view.PromoteToProductionResponse;
import com.zenred.eadvert.service.CampaignService;
import com.zenred.eadvert.service.EmailService;

public class PromoteMessageToProductionController implements Controller {
	
	private EmailService emailService;
	private CampaignService campaignService;

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public void setCampaignService(CampaignService campaignService) {
		this.campaignService = campaignService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<Message> messageList = emailService.readMessagesThatCouldBeProductionReady();
		List<PromoteToProductionResponse> responseList = new ArrayList<PromoteToProductionResponse>();
		for(Message message : messageList){
			PromoteToProductionResponse promoteProductionResponse = new PromoteToProductionResponse();
			promoteProductionResponse.setCampaign(message.getCampaign());
			promoteProductionResponse.setRevision(message.getVersion());
			promoteProductionResponse.setUri(message.getUri());
			DateEvent dateEvent = campaignService.readCampaignDateEvent(message.getCampaign());
			promoteProductionResponse.setDateMinimum(dateEvent.getDateSegmentOne());
			promoteProductionResponse.setDateMaximum(dateEvent.getDateSegmentTwo());
			responseList.add(promoteProductionResponse);
		}
		ModelAndView modelAndView = new ModelAndView(new PromoteMessageToProductionJsonView());
		modelAndView.addObject(PromoteMessageToProductionJsonView.JSON_ROOT, responseList);
		return modelAndView;
	}

}
