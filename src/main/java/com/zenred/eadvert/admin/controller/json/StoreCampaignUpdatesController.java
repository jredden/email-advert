package com.zenred.eadvert.admin.controller.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.domain.CampaignInformation;
import com.zenred.eadvert.model.domain.DateEvent;
import com.zenred.eadvert.model.domain.EMail;
import com.zenred.eadvert.model.view.StoreCampaignUpdatesResponse;
import com.zenred.eadvert.model.view.UpdateCampaignResponse;
import com.zenred.eadvert.service.CampaignService;
import com.zenred.eadvert.service.EmailService;
import com.zenred.eadvert.service.TemplateService;

public class StoreCampaignUpdatesController implements Controller {

	private CampaignService campaignService;
	private EmailService emailService;
	private TemplateService templateService;

	/**
	 * @param campaignService
	 *            the campaignService to set
	 */
	public void setCampaignService(CampaignService campaignService) {
		this.campaignService = campaignService;
	}

	/**
	 * @param emailService
	 *            the emailService to set
	 */
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	/**
	 * @param templateService
	 *            the templateService to set
	 */
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	@SuppressWarnings("unchecked")
	private CampaignInformation buildCampaignInformation(
			HttpServletRequest request) {
		CampaignInformation campaignInformation = new CampaignInformation();
		campaignInformation.generate();
		Map<String, Object[]> map = request.getParameterMap();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			if(key.contains(CampaignInformation.CAMPAIGN_NAME)){
				campaignInformation.getDateEvent().setCampaignName(
						(String) map.get(key)[0]);				
			}
			if (key.equals(CampaignInformation.NAME)) {
				campaignInformation.getDateEvent().setName(
						(String) map.get(key)[0]);
			}
			
			/*	needs to be immutable in this case
			if (key.contains(CampaignInformation.EVENT_NAME)) {
				campaignInformation.getDateEvent().setEventName(
						(String) map.get(key)[0]);
			}
			*/
			campaignInformation.getDateEvent().setEventName("campaign definition");
			
			if (key.contains(CampaignInformation.CAMPAIGN_DATE1)) {
				campaignInformation.getDateEvent().setDateSegmentOne(
						(String) map.get(key)[0]);
			}
			if (key.contains(CampaignInformation.CAMPAIGN_DATE2)) {
				campaignInformation.getDateEvent().setDateSegmentTwo(
						(String) map.get(key)[0]);
			}
			if (key.contains(CampaignInformation.EMAIL_DELETE)) {
				EMail.mailType type = null;
				if (key.contains(EMail.mailType.admin.toString())) {
					type = EMail.mailType.admin;
				}
				if (key.contains(EMail.mailType.subscriber.toString())) {
					type = EMail.mailType.subscriber;
				}
				if (key.contains(EMail.mailType.provider.toString())) {
					type = EMail.mailType.provider;
				}
				if (key.contains(EMail.mailType.test.toString())) {
					type = EMail.mailType.test;
				}
				EMail email = new EMail();
				email.setAddress((String) map.get(key)[0]);
				email.setType(type);
				campaignInformation.getEmailDeletes().add(email);
			}
			if (key.contains(CampaignInformation.ASSOCIATE_EMAIL)) {
				campaignInformation.getTemplateAssociates().add(
						(String) map.get(key)[0]);
			}
			if (key.contains(CampaignInformation.UNASSOCIATE_EMAIL)) {
				campaignInformation.getTemplateUnassociates().add(
						(String) map.get(key)[0]);
			}
			if (key.contains(CampaignInformation.EMAIL_ADDRESS)) {
				campaignInformation.getAddEmail().setAddress(
						(String) map.get(key)[0]);
			}
			if (key.contains(CampaignInformation.GROUP_EMAIL_ADDRESS)) {
				campaignInformation.getAddEmail().setType(
						campaignInformation.getAddEmail().getType(
								(String) map.get(key)[0]));
			}
		}
		return campaignInformation;
	}

	private void genPrimtiveListsForJson(List<EMail> campaignEmail,
			StoreCampaignUpdatesResponse updateCampaignResponse) {
		updateCampaignResponse.setDeletedEmails(new ArrayList<String>());
		for(EMail email: campaignEmail){
			updateCampaignResponse.getDeletedEmails().add(email.getAddress());
		}
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CampaignInformation campaignInformation = buildCampaignInformation(request);
		System.out.println(campaignInformation);
		campaignService.updateCampaign(campaignInformation);
		StoreCampaignUpdatesResponse updateCampaignResponse = new StoreCampaignUpdatesResponse();
		updateCampaignResponse.setDateEvent(campaignInformation.getDateEvent());
		updateCampaignResponse.setAssociatedTemplates(campaignInformation
				.getTemplateAssociates());
		updateCampaignResponse.setUnAssociatedTemplates(campaignInformation
				.getTemplateUnassociates());
		genPrimtiveListsForJson(campaignInformation.getEmailDeletes(),
				updateCampaignResponse);
		updateCampaignResponse.setAddedEmail(campaignInformation.getAddEmail().getAddress());
		ModelAndView modelAndView = new ModelAndView(
				new StoreCampaignUpdatesJsonView());
		modelAndView.addObject(StoreCampaignUpdatesJsonView.JSON_ROOT,
				updateCampaignResponse);
		return modelAndView;
	}

}
