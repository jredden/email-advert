package com.zenred.eadvert.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.domain.User;
import com.zenred.eadvert.model.domain.User.UserRole;

public class PrimaryHomeController implements Controller {

	private static String MAIN_HOME = "main_home";
	private static String MAIN_LOGIN = "login_home";
	private static String MAIN_HOME_ADMIN = "main_home_admin";
	private static String MAIN_HOME_USER = "main_home_user";
	private static String MAIN_HOME_QA = "main_home_qa";

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
			if(session.getAttribute("userRole").toString().trim().equals(UserRole.root.toString())){
			_model_and_view.setViewName("/" + MAIN_HOME);
			}
			else{
				if(session.getAttribute("userRole").toString().trim().equals(UserRole.admin.toString())){
					_model_and_view.setViewName("/" + MAIN_HOME_ADMIN);
				}
				else{
					if(session.getAttribute("userRole").toString().trim().equals(UserRole.test.toString())){
						_model_and_view.setViewName("/" + MAIN_HOME_QA);
					}
					else{
						_model_and_view.setViewName("/" + MAIN_HOME_USER);
					}
				}
			}
		}
		return _model_and_view;
	}

}
