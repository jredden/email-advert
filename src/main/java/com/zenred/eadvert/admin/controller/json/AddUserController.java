package com.zenred.eadvert.admin.controller.json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.domain.IProvider;
import com.zenred.eadvert.model.domain.IUser;
import com.zenred.eadvert.model.domain.Provider;
import com.zenred.eadvert.model.domain.User;
import com.zenred.eadvert.model.view.AddUserResponse;
import com.zenred.eadvert.service.UserService;

public class AddUserController implements Controller {
	
	private UserService userService;


	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		IProvider user = new Provider();
		if(request.getParameter("groupNewEmailAddress").equals("provider")){
			user.setProvider(request.getParameter("oems"));
		}
		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("userPassword"));
		user.setNewPassword(request.getParameter("userPassword2"));
		user.setS_userRole(User.getUserRole(request.getParameter("groupNewEmailAddress")));
		String result = validate(user);
		AddUserResponse addUserResponse = new AddUserResponse();
		if(result.isEmpty()){
			if(userService.doesUserNotExist(user.getUserName(), user.getPassword())){
			userService.addNewUser(user);
			addUserResponse.setAddUser("successful");
			}
			else{
				addUserResponse.setAddUser("failed, this user "+user.getUserName()+" already exists");
			}
		}
		else{
			addUserResponse.setAddUser(result);
		}
		ModelAndView modelAndView = new ModelAndView(new AddUserJsonView());
		modelAndView.addObject(AddUserJsonView.JSON_ROOT, addUserResponse);
		return modelAndView;
	}
	
	public String validate(IUser user){
		StringBuffer result = new StringBuffer();
		if(!user.getPassword().equals(user.getNewPassword())){
			result.append("passwords do not match");
		}
		return result.toString();
	}

}
