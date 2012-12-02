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
import com.zenred.eadvert.model.view.PromoteMessageResponse;
import com.zenred.eadvert.service.EmailService;

public class PromoteMessageToQAController implements Controller {
	
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
		// add subject associated to each message.
		for (Message message : messageList) {
			iter = keys.iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				
//				System.out.println("KEY:"+key+":");
				if (key.startsWith("subject")
						&& key.contains(message.getUri()
								+ "::" + message.getVersion())) {
					message.setSubject(parameterMap.get(key)[0]);
					System.out.println("MESSAGE:" + message);
				}
			}
		}
		List<Message> messageListResponse = emailService.promoteCampaignToQA(messageList);
		List<PromoteMessageResponse> responseList = new ArrayList<PromoteMessageResponse>();
		for (Message m_message : messageListResponse){
			PromoteMessageResponse promoteMessageResponse = new PromoteMessageResponse();
			promoteMessageResponse.setCampaign(m_message.getCampaign());
			promoteMessageResponse.setImageUriList(m_message.getImageUriList());
			promoteMessageResponse.setMessage(m_message.getMessage());
			promoteMessageResponse.setSubject(m_message.getSubject());
			promoteMessageResponse.setUri(m_message.getUri());
			promoteMessageResponse.setVersion(m_message.getVersion());
			responseList.add(promoteMessageResponse);
		}
		ModelAndView modelAndView = new ModelAndView(new PromoteMessageToQAJsonView());
		modelAndView.addObject(PromoteMessageToQAJsonView.JSON_ROOT, responseList);
		return modelAndView;
	}

}
