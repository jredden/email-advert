package com.zenred.eadvert.admin.controller.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.view.ReadInterestGroupNamesResponse;
import com.zenred.eadvert.service.InterestService;

public class FetchInterestGroupController implements Controller {

	private InterestService interestService;


	/**
	 * @param InterestGroupService the InterestGroupService to set
	 */
	public void setInterestService(InterestService interestService) {
		this.interestService = interestService;
	}


	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<String> interestNamesList = interestService.getInterestGroups();
		List<ReadInterestGroupNamesResponse> responseList = new ArrayList<ReadInterestGroupNamesResponse>();
		for(String name : interestNamesList){
			ReadInterestGroupNamesResponse readInterestGroupNamesResponse = new ReadInterestGroupNamesResponse();
			readInterestGroupNamesResponse.setinterestGroupName(name);
			responseList.add(readInterestGroupNamesResponse);
		}
		ModelAndView modelAndView = new ModelAndView(new FetchInterestGroupNamesJsonView());
		modelAndView.addObject(FetchInterestGroupNamesJsonView.JSON_ROOT, responseList);
		return modelAndView;
	}

}
