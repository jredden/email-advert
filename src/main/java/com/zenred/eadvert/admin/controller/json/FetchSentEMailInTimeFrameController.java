package com.zenred.eadvert.admin.controller.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.view.SentMessagesResponse;
import com.zenred.eadvert.service.ReportService;

public class FetchSentEMailInTimeFrameController implements Controller {
	
	private String message;
	private ReportService reportService;

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	private List<SentMessagesResponse> singleMessage(String message){
		List<SentMessagesResponse> sentMessagesResponseList  = new ArrayList<SentMessagesResponse>();
		SentMessagesResponse sentMessagesResponse = new SentMessagesResponse();
		sentMessagesResponse.setStatusMessage(message);
		sentMessagesResponseList.add(sentMessagesResponse);
		return sentMessagesResponseList;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String [] date_array = request.getParameter("reportTimeFrame").split(",");
		List<SentMessagesResponse> sentMessagesResponseList = null;
		if(validateDate(date_array)){
			sentMessagesResponseList = reportService.messagesSentInTimeFrame(date_array[0], date_array[1]);
			if(0 == sentMessagesResponseList.size()){
				sentMessagesResponseList = singleMessage("No records found for date criteria");
			}
		}
		else{
			sentMessagesResponseList = singleMessage(message);
		}
		ModelAndView modelAndView = new ModelAndView(new FetchSentEMailTimeFrameJsonView());
		modelAndView.addObject(FetchSentEMailTimeFrameJsonView.JSON_ROOT, sentMessagesResponseList);
		return modelAndView;
	}
	
	private boolean validateDate(String [] date_array){
		boolean result = true;
		message = "";
		if(null == date_array[0] || date_array[0].isEmpty()){
			message += " First date is empty.";
			result = false;
		}
		if(null == date_array[1] || date_array[1].isEmpty()){
			message += " Second date is empty.";
			result = false;
		}
		int date1size = date_array[0].split("/").length;
		int date2size = date_array[1].split("/").length;
		if(3 != date1size){
			message += " Format Error for first date:"+date_array[0];
			result = false;
		}
		if(3 != date2size){
			message += " Format Error for second date:"+date_array[1];
			result = false;
		}
		return result;
	}

}
