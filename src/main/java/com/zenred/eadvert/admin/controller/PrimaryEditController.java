package com.zenred.eadvert.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PrimaryEditController implements Controller {
	
	public static final String MAIN_EDIT = "main_edit";
	private static String MAIN_LOGIN = "login_home";

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
			_model_and_view.setViewName("/" + MAIN_EDIT);
		}
		return _model_and_view;
	}

}
