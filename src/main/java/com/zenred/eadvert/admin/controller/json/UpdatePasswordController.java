package com.zenred.eadvert.admin.controller.json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.view.UpdatePasswordResponse;
import com.zenred.eadvert.service.UserService;

public class UpdatePasswordController implements Controller {

	private UserService userService;

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String provider = request.getParameter("providerusers").split(":::")[0];
		String password1 = request.getParameter("userPassword");
		String password2 = request.getParameter("userPassword2");
		UpdatePasswordResponse updatePasswordResponse = new UpdatePasswordResponse();
		if(!password1.equals(password2)){
			updatePasswordResponse.setupdatePassword("Warning: first and second password you typed did not match!");
		}
		else{
			userService.updateUserPassword(provider, password1, "provider");
			updatePasswordResponse.setupdatePassword("Password for provider " + provider + " successfully updated.");
		}
		ModelAndView modelAndView = new ModelAndView(new UpdatePasswordJsonView());
		modelAndView.addObject(UpdatePasswordJsonView.JSON_ROOT, updatePasswordResponse);
		return modelAndView;
	}

}
