package com.zenred.eadvert.admin.controller.json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.view.EmptyResponse;

public class NewMessageController implements Controller {
	
	private String content;


	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}


	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String campaignName = request.getParameter("campaigns");
		request.getSession().setAttribute("content", content);
		request.getSession().setAttribute("version", "0");
		request.getSession().setAttribute("campaign", campaignName);
		ModelAndView modelAndView = new ModelAndView(new EmptyJsonView());
		modelAndView.addObject(EmptyJsonView.JSON_ROOT, new EmptyResponse());
		return modelAndView;
	}

}
