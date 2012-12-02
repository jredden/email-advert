package com.zenred.eadvert.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PrimaryReportsController implements Controller {
	
	private static String MAIN_LOGIN = "login_home";
	
	private Map<String, String> reportViewMap;

	public void setReportViewMap(Map<String, String> reportViewMap) {
		this.reportViewMap = reportViewMap;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ModelAndView _model_and_view = new ModelAndView();
		if (session.getAttribute("userName") == null
				|| session.getAttribute("userName").toString().length() == 0
				|| session.getAttribute("userRole") == null
				&& (session.getAttribute("userRole").toString().equals("none") && session
						.getAttribute("userName").toString().equals("none"))) {
			_model_and_view.setViewName("/" + MAIN_LOGIN);
		}
		else{
			String role = session.getAttribute("userRole").toString();
			_model_and_view.setViewName("/" + reportViewMap.get(role));
		}
		return _model_and_view;
	}

}
