package com.zenred.eadvert.admin.controller.json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.event.def.OnReplicateVisitor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.domain.Message;
import com.zenred.eadvert.model.view.OneMessageReviewOrUpdateResponse;
import com.zenred.eadvert.service.CampaignService;

public class OneMessageReviewOrUpdateController implements Controller {

	private CampaignService campaignService;

	/**
	 * @param campaignService
	 *            the campaignService to set
	 */
	public void setCampaignService(CampaignService campaignService) {
		this.campaignService = campaignService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String uri_key = request.getParameter("editView");
		String[] uri_and_version = uri_key.split(" ");
		String uri = uri_and_version[0];
		String version = uri_and_version[1];
		System.out.println("OneMessageReviewOrUpdateController:::" + uri_key + "::" + uri + "::" + version + "::"
				+ request.getParameter("campaign"));
		Message message = campaignService.fetchMessageForReviewAndEditing(uri,
				version);
		String content = "RETURN_TO_CLIENT" + message.getMessage();  // temp fix for now.
		message.setMessage(content);
		request.getSession().setAttribute("content", message.getMessage());
		request.getSession().setAttribute("version", message.getVersion());
		OneMessageReviewOrUpdateResponse oneMessageReviewOrUpdateResponse = new OneMessageReviewOrUpdateResponse();
		oneMessageReviewOrUpdateResponse.setContent(message.getMessage());
		oneMessageReviewOrUpdateResponse.setVersion(message.getVersion());
		oneMessageReviewOrUpdateResponse.setCampaign(request
				.getParameter("campaign"));
		oneMessageReviewOrUpdateResponse.setUri(uri);
		ModelAndView modelAndView = new ModelAndView(new OneMessageJsonView());
		modelAndView.addObject(OneMessageJsonView.JSON_ROOT,
				oneMessageReviewOrUpdateResponse);
		return modelAndView;
	}

}
