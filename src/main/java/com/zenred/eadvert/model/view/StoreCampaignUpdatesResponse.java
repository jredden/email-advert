package com.zenred.eadvert.model.view;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.zenred.eadvert.model.domain.DateEvent;

@XStreamAlias("storeCampaignUpdate")
public class StoreCampaignUpdatesResponse {
	
	private String message;
	private DateEvent dateEvent;
	
	private List<String> unAssociatedTemplates;
	private List<String> associatedTemplates;
	private List<String> campaignEmailAddresses;
	private List<String> campaignEmailTypes;
	private List<String> deletedEmails;
	private String addedEmail;


	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
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
	 * @return the unAssociatedTemplates
	 */
	public List<String> getUnAssociatedTemplates() {
		return unAssociatedTemplates;
	}

	/**
	 * @param unAssociatedTemplates the unAssociatedTemplates to set
	 */
	public void setUnAssociatedTemplates(List<String> unAssociatedTemplates) {
		this.unAssociatedTemplates = unAssociatedTemplates;
	}

	/**
	 * @return the associatedTemplates
	 */
	public List<String> getAssociatedTemplates() {
		return associatedTemplates;
	}

	/**
	 * @param associatedTemplates the associatedTemplates to set
	 */
	public void setAssociatedTemplates(List<String> associatedTemplates) {
		this.associatedTemplates = associatedTemplates;
	}

	/**
	 * @return the campaignEmailAddresses
	 */
	public List<String> getCampaignEmailAddresses() {
		return campaignEmailAddresses;
	}

	/**
	 * @param campaignEmailAddresses the campaignEmailAddresses to set
	 */
	public void setCampaignEmailAddresses(List<String> campaignEmailAddresses) {
		this.campaignEmailAddresses = campaignEmailAddresses;
	}

	/**
	 * @return the campaignEmailTypes
	 */
	public List<String> getCampaignEmailTypes() {
		return campaignEmailTypes;
	}

	/**
	 * @param campaignEmailTypes the campaignEmailTypes to set
	 */
	public void setCampaignEmailTypes(List<String> campaignEmailTypes) {
		this.campaignEmailTypes = campaignEmailTypes;
	}
	/**
	 * @return the deletedEmails
	 */
	public List<String> getDeletedEmails() {
		return deletedEmails;
	}
	/**
	 * @param deletedEmails the deletedEmails to set
	 */
	public void setDeletedEmails(List<String> deletedEmails) {
		this.deletedEmails = deletedEmails;
	}
	/**
	 * @return the addedEmail
	 */
	public String getAddedEmail() {
		return addedEmail;
	}
	/**
	 * @param addedEmail the addedEmail to set
	 */
	public void setAddedEmail(String addedEmail) {
		this.addedEmail = addedEmail;
	}

}
