package com.zenred.eadvert.admin.controller.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.domain.Message;
import com.zenred.eadvert.model.view.ReadReviewOrUpdateResponse;
import com.zenred.eadvert.service.CampaignService;

public class MessageReviewOrUpdateController implements Controller {
	
	private CampaignService campaignService;

	/**
	 * @param campaignService the campaignService to set
	 */
	public void setCampaignService(CampaignService campaignService) {
		this.campaignService = campaignService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String campaign = request.getParameter("campaigns");
		List<Message> messageList = campaignService.fetchMessagesForReviewOrUpdate(campaign);
		List<ReadReviewOrUpdateResponse> responseList = new ArrayList<ReadReviewOrUpdateResponse>();
		for(Message message : messageList){
			ReadReviewOrUpdateResponse readReviewOrUpdateResponse = new ReadReviewOrUpdateResponse();
			readReviewOrUpdateResponse.setCampaign(campaign);
			readReviewOrUpdateResponse.setName(message.getUri());
			readReviewOrUpdateResponse.setVersion(message.getVersion());
			responseList.add(readReviewOrUpdateResponse);
		}
		ModelAndView modelAndView = new ModelAndView(new ReviewOrUpdateJsonView());
		modelAndView.addObject(ReviewOrUpdateJsonView.JSON_ROOT, responseList);
		return modelAndView;
	}

}
