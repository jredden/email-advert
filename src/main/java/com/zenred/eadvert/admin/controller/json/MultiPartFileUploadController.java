package com.zenred.eadvert.admin.controller.json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.view.FileUploadResponse;
import com.zenred.eadvert.service.CampaignService;


// sic  (Latin)

public class MultiPartFileUploadController implements Controller {
	
	private CampaignService campaignService;

	/**
	 * @param campaignService the campaignService to set
	 */
	public void setCampaignService(CampaignService campaignService) {
		this.campaignService = campaignService;
	}
	
	/*
	 * get rid of this method some time ...
	 */
	private ModelAndView turnBackToClient(){
		ModelAndView modelAndView = new ModelAndView(new FileUploadJsonView());
		FileUploadResponse fileUploadResponse = new FileUploadResponse();
		fileUploadResponse.setMessage("message compiled on server, Please submit again");
		modelAndView
		.addObject(FileUploadJsonView.JSON_ROOT, fileUploadResponse);
		return modelAndView;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String responseMessage = "";
		System.out.println("request.campaign:"
				+ request.getSession().getAttribute("campaign"));
		String folder = (String) request.getSession().getAttribute("campaign");
		String message = request.getParameter("file");
		if(message.startsWith("RETURN_TO_CLIENT")){return turnBackToClient();}
		String content = request.getParameter("content");
		String version = request.getParameter("version");
		String uri = (null == request.getParameter("uri")? "NONE": request.getParameter("uri"));
		System.out.println("file:" + message + " content:" + content
				+ " version:" + version + " folder:" + folder
				+ " uri:" +uri);
		
		if (null != content && null != version && !uri.equals("NONE")) {
			responseMessage = campaignService.addReviewableMessage(
					message,
					(null == folder ? request.getSession().getAttribute(
							"campaign") : folder).toString(), version, uri);
		} else {
			if (null != content && null != version) {
				responseMessage = campaignService.addReviewableMessage(message,
						request.getParameter("campaign"), version);
			} else {

				responseMessage = campaignService.addReviewableMessage(message,
						folder, request.getSession().getAttribute("version")
								.toString());
			}
		}
		
		ModelAndView modelAndView = new ModelAndView(new FileUploadJsonView());
		FileUploadResponse fileUploadResponse = new FileUploadResponse();
		fileUploadResponse.setMessage(responseMessage
				+ " was successfully written for Review and/or update");
		modelAndView
				.addObject(FileUploadJsonView.JSON_ROOT, fileUploadResponse);
		return modelAndView;
	}

}
