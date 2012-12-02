package com.zenred.eadvert.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PrimaryLoggingController  implements Controller  {

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println(request.getParameter("emailAdvert"));
		response.getWriter().print("OK");
		return null;
	}

}
