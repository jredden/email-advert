package com.zenred.eadvert.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PrimaryLoginController implements Controller {
	
	public static final String MAIN_LOGIN = "main_login";

	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		ModelAndView _model_and_view = new ModelAndView();

		_model_and_view.setViewName("/"+MAIN_LOGIN);
		return _model_and_view;
	}

}
