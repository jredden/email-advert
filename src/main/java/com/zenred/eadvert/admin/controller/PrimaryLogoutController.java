package com.zenred.eadvert.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PrimaryLogoutController implements Controller {

	private static String MAIN_LOGIN = "main_login";
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.setAttribute("userName", "none");
		session.setAttribute("userRole", "none");
		ModelAndView _model_and_view = new ModelAndView();

		_model_and_view.setViewName("/"+MAIN_LOGIN);
		return _model_and_view;
	}

}
