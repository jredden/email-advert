package com.zenred.eadvert.admin.controller.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.domain.Message;
import com.zenred.eadvert.model.view.ExecDeleteMessageResponse;
import com.zenred.eadvert.model.view.PromoteMessageResponse;
import com.zenred.eadvert.service.EmailService;

public class ExecDeleteMessageController implements Controller {
	
	private EmailService emailService;

	/**
	 * @param emailService the emailService to set
	 */
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<String> keys = parameterMap.keySet();
		Iterator<String> iter = keys.iterator();
		String campaign = request.getParameter("campaign");
		List<Message> messageList = new ArrayList<Message>();
		while (iter.hasNext()) {
			String key = iter.next();
			String uriset = parameterMap.get(key)[0];
			int end_pos = uriset.indexOf('|');
			if (-1 == end_pos) {
				continue;
			}

			Message message = new Message();
			message.setCampaign(campaign);
			message.setUri(uriset.substring(0, end_pos));
			message.setVersion(uriset.substring(end_pos + 1));
			messageList.add(message);
			System.out.println(":::" + uriset.substring(0, end_pos - 1) + "++"
					+ uriset.substring(end_pos + 1) + "++");
		}
		StringBuffer messageDeleteResponse = new StringBuffer(); 
		for(Message message: messageList){
			messageDeleteResponse.append(emailService.removeMessageAndComponents(message));
		}
		ExecDeleteMessageResponse execDeleteMessageResponse = new ExecDeleteMessageResponse();
		execDeleteMessageResponse.setDeleteMessage(messageDeleteResponse.toString());
		ModelAndView modelAndView = new ModelAndView(new ExecDeleteMessageJsonView());
		modelAndView.addObject(ExecDeleteMessageJsonView.JSON_ROOT, execDeleteMessageResponse);
		return modelAndView;
	}

}
