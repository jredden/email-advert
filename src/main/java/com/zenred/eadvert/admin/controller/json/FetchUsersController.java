package com.zenred.eadvert.admin.controller.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.domain.IUser;
import com.zenred.eadvert.model.domain.User;
import com.zenred.eadvert.model.view.ReadUsersResponse;
import com.zenred.eadvert.service.UserService;

public class FetchUsersController implements Controller {
	
	private UserService userService;

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<User> userList = userService.readUsersThatAreNotRoot();
		HttpSession session = request.getSession();
		List<ReadUsersResponse> readUserResponseList = new ArrayList<ReadUsersResponse>();
		
		for(IUser user : userList){
			session.setAttribute(user.getUserName()+user.getS_userRole(), user.getPassword());
			ReadUsersResponse readUsersResponse = new ReadUsersResponse();
			readUsersResponse.setUserNames(user.getUserName());
			readUsersResponse.setUserRoles(user.getS_userRole().toString());
			readUserResponseList.add(readUsersResponse);
		}
		ModelAndView modelAndView = new ModelAndView(new FetchUsersJsonView());
		modelAndView.addObject(FetchUsersJsonView.JSON_ROOT, readUserResponseList);
		return modelAndView;
	}

}
