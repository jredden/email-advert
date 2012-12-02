package com.zenred.eadvert.admin.controller.json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.domain.User.UserRole;
import com.zenred.eadvert.model.view.ValidateUserPasswordResponse;
import com.zenred.eadvert.service.UserService;

public class ValidateUserPasswordController implements Controller {

	private UserService userService;

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		UserRole userRole = userService.validateUser(userName, password);
		ValidateUserPasswordResponse validateUserPasswordResponse = new ValidateUserPasswordResponse();
		validateUserPasswordResponse.setUserRole(null == userRole ? "failed":userRole.toString());
		HttpSession session = request.getSession();
		session.setAttribute("userName", userName);
		session.setAttribute("userRole", userRole);
		ModelAndView modelAndView = new ModelAndView(new ValidateUserPasswordJsonView());
		modelAndView.addObject(ValidateUserPasswordJsonView.JSON_ROOT, validateUserPasswordResponse);
		return modelAndView;
	}

}
