package com.zenred.eadvert.admin.controller.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.domain.EMail;
import com.zenred.eadvert.model.view.ReadAssociatedEmailsResponse;
import com.zenred.eadvert.service.EmailService;

public class AssociateEmailAddressController implements Controller {

	private EmailService emailService;

	/**
	 * @param emailService
	 *            the emailService to set
	 */
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String campaignName = (String) request.getSession().getAttribute(
				"campaign");
		Map<String, String[]> parameterMap = request.getParameterMap();
		List<EMail> associatedMailAddressList = new ArrayList<EMail>();
		Set<String> keys = parameterMap.keySet();
		for (String key : keys) {

			if (key.startsWith("emailAssociate")) {
				String emailSet = parameterMap.get(key)[0];
				String[] addressAndType = emailSet.split(":");
				EMail eMail = new EMail();
				eMail.setAddress(addressAndType[0]);
				eMail.setType(eMail.getType(addressAndType[1]));
				associatedMailAddressList.add(eMail);
			}
		}

		emailService.associateEMailAddressesToCampaign(
				associatedMailAddressList, campaignName);
		List<ReadAssociatedEmailsResponse> responseList = new ArrayList<ReadAssociatedEmailsResponse>();
		for (EMail eMail : associatedMailAddressList) {
			ReadAssociatedEmailsResponse readAssociatedEmailsResponse = new ReadAssociatedEmailsResponse();
			readAssociatedEmailsResponse.setAddress(eMail.getAddress());
			readAssociatedEmailsResponse.setType(eMail.getType().toString());
			responseList.add(readAssociatedEmailsResponse);
		}
		ModelAndView modelAndView = new ModelAndView(
				new AssociateEmailAddressesJsonView());
		modelAndView.addObject(AssociateEmailAddressesJsonView.JSON_ROOT,
				responseList);
		return modelAndView;
	}

}
