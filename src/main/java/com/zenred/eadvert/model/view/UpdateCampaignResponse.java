package com.zenred.eadvert.model.view;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.zenred.eadvert.model.domain.DateEvent;

@XStreamAlias("updateCampaign")
public class UpdateCampaignResponse {
	
	private DateEvent dateEvent;
	
	private List<String> unAssociatedTemplates;
	private List<String> associatedTemplates;
	private List<String> campaignEmailAddresses;
	private List<String> campaignEmailTypes;
	
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


}
