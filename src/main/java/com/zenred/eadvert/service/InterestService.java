package com.zenred.eadvert.service;

import java.util.ArrayList;
import java.util.List;

import com.zenred.eadvert.dao.CampaignDao;
import com.zenred.eadvert.dao.EmailDao;
import com.zenred.eadvert.dao.InterestGroupDao;
import com.zenred.eadvert.dao.OemDao;
import com.zenred.eadvert.exception.InterestServiceException;
import com.zenred.eadvert.model.domain.Campaign;
import com.zenred.eadvert.model.domain.EMail;
import com.zenred.eadvert.model.domain.EMail.mailType;
import com.zenred.eadvert.model.domain.InterestGroup;
import com.zenred.eadvert.service.EmailService.Result;

public class InterestService {
	
	private InterestGroupDao interestGroupDao;
	private CampaignDao campaignDao;
	private EmailDao emailDao;
	
	public void setInterestGroupDao(InterestGroupDao interestGroupDao) {
		this.interestGroupDao = interestGroupDao;
	}

	public void setCampaignDao(CampaignDao campaignDao) {
		this.campaignDao = campaignDao;
	}


	public void setEmailDao(EmailDao emailDao) {
		this.emailDao = emailDao;
	}

	public List<String> getInterestGroups(){
		return interestGroupDao.fetchInterestGroups();
	}
	
	public List<String> getInterestGroups(String userName){
		return interestGroupDao.fetchInterestGroupsAssociatedToUser(userName);
	}
	
	public String assosciatedInterestGroupToCampaign(String interestGroupName, String campaignName) throws InterestServiceException{
		String result = "";
		if(interestGroupDao.campaignInInterestGroup(campaignName, interestGroupName)){
			throw new InterestServiceException("Warning: campaign name " + campaignName + " already exists in " + interestGroupName);
		}
		InterestGroup interestGroup = interestGroupDao.fetchInterestGroup(interestGroupName);
		Campaign campaign = campaignDao.loadCampaign(campaignName);
		interestGroup.getCampaignList().add(campaign);
		interestGroupDao.addCampaignToInterestGroup(interestGroup);
		result += "campaign " + campaignName + " added to interest group " + interestGroupName;
		return result;
	}
	
	public String associatedInterestedGroupToProvider(String interestGroupName, String providerName) throws InterestServiceException{
		String result = "";
		if(interestGroupDao.providerInInterestGroup(providerName, interestGroupName)){
			throw new InterestServiceException("Warning: provider name " + providerName + " already exists in " + interestGroupName );
		}
		InterestGroup interestGroup = interestGroupDao.fetchInterestGroup(interestGroupName);
		interestGroup.getProviderList().add(providerName);
		interestGroupDao.addProviderToInterestGroup(interestGroup);
		result += "provider " + providerName + " added to interest group " + interestGroupName;
		return result;
	}
	
	public boolean doesInterestGroupAlreadyExist(String interestGroupName){
		boolean alreadyExists = interestGroupDao.interestGroupNameAlreadyThere(interestGroupName);
		return alreadyExists;
	}
	
	public String createAnonymousInterestGroup(String interestGroupName) throws InterestServiceException {
		if(doesInterestGroupAlreadyExist(interestGroupName)){
			throw new InterestServiceException("Warning: interest group " + interestGroupName + " already exists");
		}
		interestGroupDao.addInterestGroup(interestGroupName);
		return "Anonymous interest group " + interestGroupName + " successfully added ";
	}
	
	public void addEmailAddressToInterestGroup(String emailAddress,
			String interestGroupName) throws InterestServiceException {
		Result result = EmailService.isEmailAddressBadOrGood(emailAddress,
				new EmailService());
		if (result.bad) {
			throw new InterestServiceException("Error:" + result.error);
		}
		if (!doesInterestGroupAlreadyExist(interestGroupName)) {
			throw new InterestServiceException("Error: interest group "
					+ interestGroupName + " does not exist");
		}
		if (!emailDao.doesEmailAddressExistForUserType(emailAddress,
				"subscriber")) {
			EMail email = new EMail();
			email.setAddress(emailAddress);
			email.setType(mailType.subscriber);
			emailDao.addEmail(email);
		}
		String emailAddressID = emailDao.fetchEmailAddressIDOfUserType(
				emailAddress, "subscriber").get(0);
		if (interestGroupDao.cestEmailAddressSurLeInterestGroup(emailAddressID,
				interestGroupName)) {
			throw new InterestServiceException("Warning: interest group "
					+ interestGroupName + " already has email address "
					+ emailAddress);
		}
		interestGroupDao.addEmailAddressToInterestGroup(emailAddressID,
				interestGroupName);
	}
	
	public List<InterestGroup> fetchInterestGroupInformation(){
		List<InterestGroup> interestGroupList = new ArrayList<InterestGroup>();
		List<String> interestGroupNameList = getInterestGroups();
		for(String interestGroupName : interestGroupNameList){
			InterestGroup interestGroup = interestGroupDao.fetchInterestGroup(interestGroupName);
			interestGroupList.add(interestGroup);
		}
		return interestGroupList;
	}
	public List<InterestGroup> fetchInterestGroupInformation(String userName){
		List<InterestGroup> interestGroupList = new ArrayList<InterestGroup>();
		List<String> interestGroupNameList = getInterestGroups(userName);
		for(String interestGroupName : interestGroupNameList){
			InterestGroup interestGroup = interestGroupDao.fetchInterestGroup(interestGroupName);
			interestGroupList.add(interestGroup);
		}
		return interestGroupList;
	}

}
