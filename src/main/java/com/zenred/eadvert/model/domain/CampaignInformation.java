package com.zenred.eadvert.model.domain;

import java.util.ArrayList;
import java.util.List;


// campaign information pojo

public class CampaignInformation {
	
	public static String CAMPAIGN_NAME = "campaignName";
	public static String NAME = "Name";
	public static String EVENT_NAME = "eventName";
	public static String CAMPAIGN_DATE1 = "campaignDate1";
	public static String CAMPAIGN_DATE2 = "campaignDate2";
	public static String EMAIL_BASE_NAME = "email";
	public static String EMAIL_DELETE = EMAIL_BASE_NAME + "Delete";
	public static String EMAIL_ADDRESS = EMAIL_BASE_NAME + "Address";
	public static String GROUP_EMAIL_ADDRESS ="groupNewEmailAddress";
	public static String UNASSOCIATE_EMAIL = EMAIL_BASE_NAME + "Unassociate";
	public static String ASSOCIATE_EMAIL = EMAIL_BASE_NAME + "Associate";

	private DateEvent dateEvent;
	private List<EMail> emailDeletes;
	private EMail addEmail; 
	private List<String> templateUnassociates;
	private List<String> templateAssociates;
	
	public void generate(){
		dateEvent = new DateEvent();
		emailDeletes = new ArrayList<EMail>();
		templateAssociates = new ArrayList<String>();
		templateUnassociates = new ArrayList<String>();
		addEmail = new EMail();
	}
	
	/**
	 * @return the dateEvent
	 */
	public DateEvent getDateEvent() {
		return dateEvent;
	}
	/**
	 * @param dateEvent the dateEvent to set
	 */
	public void setDateEvent(DateEvent dateEvent) {
		this.dateEvent = dateEvent;
	}
	/**
	 * @return the emailDeletes
	 */
	public List<EMail> getEmailDeletes() {
		return emailDeletes;
	}
	/**
	 * @param emailDeletes the emailDeletes to set
	 */
	public void setEmailDeletes(List<EMail> emailDeletes) {
		this.emailDeletes = emailDeletes;
	}
	/**
	 * @return the addEmail
	 */
	public EMail getAddEmail() {
		return addEmail;
	}
	/**
	 * @param addEmail the addEmail to set
	 */
	public void setAddEmail(EMail addEmail) {
		this.addEmail = addEmail;
	}
	/**
	 * @return the templateUnassociates
	 */
	public List<String> getTemplateUnassociates() {
		return templateUnassociates;
	}
	/**
	 * @param templateUnassociates the templateUnassociates to set
	 */
	public void setTemplateUnassociates(List<String> templateUnassociates) {
		this.templateUnassociates = templateUnassociates;
	}
	/**
	 * @return the templateAssociates
	 */
	public List<String> getTemplateAssociates() {
		return templateAssociates;
	}
	/**
	 * @param templateAssociates the templateAssociates to set
	 */
	public void setTemplateAssociates(List<String> templateAssociates) {
		this.templateAssociates = templateAssociates;
	}

	public String toString(){
		return new StringBuffer().append(this.getClass().getSimpleName())
		.append(':').append('\n')
		.append("dateEvent:"+dateEvent+'\n')
		.append("emailDeletes:"+emailDeletes+'\n')
		.append("addEmail:"+addEmail+'\n')
		.append("templateUnassociates:"+templateUnassociates+'\n')
		.append("templateAssociates:"+templateAssociates+'\n')
		.toString();
	}
}
