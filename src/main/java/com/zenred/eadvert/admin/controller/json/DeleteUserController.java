package com.zenred.eadvert.admin.controller.json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.view.DeleteUserResponse;
import com.zenred.eadvert.service.UserService;

public class DeleteUserController implements Controller {
	private static String MAIN_LOGIN = "main_login";

	private UserService userService;

	/**
	 * @param userService
	 *            the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		String a_userRole = session.getAttribute("userRole").toString().trim();
		System.out.println("userRole:" + a_userRole + ":");
		if (!a_userRole.equals("root")) {
			session.setAttribute("userName", "none");
			session.setAttribute("userRole", "none");
			ModelAndView _model_and_view = new ModelAndView();
			_model_and_view.setViewName("/" + MAIN_LOGIN);
			_model_and_view.addObject("illegal access");
			return _model_and_view;
		}

		String userNameRole = request.getParameter("users");
		String[] userAndName = userNameRole.split(":::");
		String userName = userAndName[0];
		String userRole = userAndName[1];
		String password = (String) session.getAttribute(userName + userRole);
		System.out.println("password:" + password + ":");
		userService.deleteUserNoMunging(userName, password, userRole);
		DeleteUserResponse deleteUserResonse = new DeleteUserResponse();
		deleteUserResonse.setDeleteUser(userName + " retired");
		ModelAndView modelAndView = new ModelAndView(new DeleteUserJsonView());
		modelAndView.addObject(DeleteUserJsonView.JSON_ROOT, deleteUserResonse);
		return modelAndView;

	}

}
