package com.zenred.eadvert.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.event.EventDirContext;

import com.zenred.eadvert.dao.CampaignDao;
import com.zenred.eadvert.dao.DateEventDao;
import com.zenred.eadvert.dao.FileIODao;
import com.zenred.eadvert.dao.MessageDao;
import com.zenred.eadvert.dao.OemDao;
import com.zenred.eadvert.exception.CampaignServiceException;
import com.zenred.eadvert.exception.DataAccessException;
import com.zenred.eadvert.exception.EmailServiceException;
import com.zenred.eadvert.model.domain.Campaign;
import com.zenred.eadvert.model.domain.CampaignInformation;
import com.zenred.eadvert.model.domain.DateEvent;
import com.zenred.eadvert.model.domain.EMail;
import com.zenred.eadvert.model.domain.Message;
import com.zenred.util.DateUtil;

public class CampaignService {
	
	private CampaignDao campaignDao;
	private DateEventDao dateEventDao;
	private OemDao oemDao;
	private FileIODao fileIODao;
	private MessageDao messageDao;
	
	private EmailService emailService;
	private TemplateService templateService;
	
	private String userFileReviewPath;
	
	protected interface AddEmail{
		void doit(String address, String campaignName, CampaignService campaignService)throws EmailServiceException;
	}
	protected interface DeleteEmail{
		void doit(String address, String campaignName, CampaignService campaignService)throws EmailServiceException;
	}
	
	static Map<EMail.mailType, AddEmail> addMailMap = new HashMap<EMail.mailType, AddEmail>();
	static Map<EMail.mailType, DeleteEmail> deleteMailMap = new HashMap<EMail.mailType, DeleteEmail>();
	
	static{
		addMailMap.put(EMail.mailType.admin, new AddEmail() {
			public void doit(String address, String campaignName,
					CampaignService campaignService)
					throws EmailServiceException {
				if (campaignService.emailService
						.doesEmailAddressForRoleAlreadyExist(address,
								EMail.mailType.admin.toString(), campaignName)) {
					throw new EmailServiceException("Error:Address for "
							+ address + " user Role:"
							+ EMail.mailType.admin.toString()
							+ "alerady exists");
				}
				campaignService.emailService.addCampaignAdminEmail(address,
						campaignName);

			}
		});
		addMailMap.put(EMail.mailType.provider, new AddEmail() {
			public void doit(String address, String campaignName,
					CampaignService campaignService) throws EmailServiceException {
				if (campaignService.emailService
						.doesEmailAddressForRoleAlreadyExist(address,
								EMail.mailType.provider.toString(), campaignName)) {
					throw new EmailServiceException("Error:Address for "
							+ address + " user Role:"
							+ EMail.mailType.provider.toString()
							+ "alerady exists");
				}

				campaignService.emailService.addCampaignProviderEmail(address, campaignName);
			}
		});
		addMailMap.put(EMail.mailType.subscriber, new AddEmail() {
			public void doit(String address, String campaignName,
					CampaignService campaignService) throws EmailServiceException {
				if (campaignService.emailService
						.doesEmailAddressForRoleAlreadyExist(address,
								EMail.mailType.subscriber.toString(), campaignName)) {
					throw new EmailServiceException("Error:Address for "
							+ address + " user Role:"
							+ EMail.mailType.subscriber.toString()
							+ "alerady exists");
				}

				campaignService.emailService.addCampaignSubscriberEmail(address, campaignName);
			}
		});
		addMailMap.put(EMail.mailType.test, new AddEmail() {
			public void doit(String address, String campaignName,
					CampaignService campaignService) throws EmailServiceException {
				if (campaignService.emailService
						.doesEmailAddressForRoleAlreadyExist(address,
								EMail.mailType.test.toString(), campaignName)) {
					throw new EmailServiceException("Error:Address for "
							+ address + " user Role:"
							+ EMail.mailType.test.toString()
							+ "alerady exists");
				}

				campaignService.emailService.addCampaignTestEmail(address, campaignName);
			}
		});
		
		deleteMailMap.put(EMail.mailType.admin, new DeleteEmail() {
			public void doit(String address, String campaignName,
					CampaignService campaignService) throws EmailServiceException {
				campaignService.emailService.deleteCampaignAdminEmail(address, campaignName);
				
			}
		});
		deleteMailMap.put(EMail.mailType.provider, new DeleteEmail() {
			public void doit(String address, String campaignName,
					CampaignService campaignService) throws EmailServiceException {
				campaignService.emailService.deleteCampaignProviderEmail(address, campaignName);
				
			}
		});
		deleteMailMap.put(EMail.mailType.subscriber, new DeleteEmail() {
			public void doit(String address, String campaignName,
					CampaignService campaignService) throws EmailServiceException {
				campaignService.emailService.deleteCampaignSubscriberEmail(address, campaignName);
				
			}
		});
		deleteMailMap.put(EMail.mailType.test, new DeleteEmail() {
			public void doit(String address, String campaignName,
					CampaignService campaignService) throws EmailServiceException {
				campaignService.emailService.deleteCampaignTestEmail(address, campaignName);
				
			}
		});
	}
	/**
	 * @param campaignDao the campaignDao to set
	 */
	public void setCampaignDao(CampaignDao campaignDao) {
		this.campaignDao = campaignDao;
	}

	/**
	 * @param dateEventDao the dateEventDao to set
	 */
	public void setDateEventDao(DateEventDao dateEventDao) {
		this.dateEventDao = dateEventDao;
	}

	/**
	 * @param oemDao the oemDao to set
	 */
	public void setOemDao(OemDao oemDao) {
		this.oemDao = oemDao;
	}

	/**
	 * @param emailService the emailService to set
	 */
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	/**
	 * @param templateService the templateService to set
	 */
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	/**
	 * @param fileIODao the fileIODao to set
	 */
	public void setFileIODao(FileIODao fileIODao) {
		this.fileIODao = fileIODao;
	}

	/**
	 * @param messageDao the messageDao to set
	 */
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	/**
	 * @param userFileReviewPath the userFileReviewPath to set
	 */
	public void setUserFileReviewPath(String userFileReviewPath) {
		this.userFileReviewPath = userFileReviewPath;
	}

	public boolean dateChecker(String date){
		Pattern datePattern = Pattern.compile("(\\d{2})/(\\d{2})/(\\d{4})");
		Matcher dateMatcher = datePattern.matcher(date);
		return !dateMatcher.find();
	}
	
	public static String convertDateJsonToMysql(String date){
		String [] reparse = date.split("/");
		return reparse[2]+"-"+reparse[0]+"-"+reparse[1];
	}
	
	public static String convertMyqlToJson(String date){
		String [] reparse = date.split("-");
		return reparse[1]+"/"+reparse[2]+"/"+reparse[0];
	}
	
	
	public void addCampaign(String date1, String date2, String campaignName, String oem) throws CampaignServiceException{
		
		if(null == date2 || date2.isEmpty()){date2 = date1;}
		
		if(dateChecker(date1)){throw new CampaignServiceException(date1 + "date 1 wrong format");}
		if(dateChecker(date2)){throw new CampaignServiceException(date2 + "date 2 wrong format");}

		/*
		String [] reparse1 = date1.split("/");
		String [] reparse2 = date2.split("/");
		String ndate1 = reparse1[2]+"-"+reparse1[0]+"-"+reparse1[1];
		String ndate2 = reparse2[2]+"-"+reparse2[0]+"-"+reparse2[1];
		*/
		String ndate1 = convertDateJsonToMysql(date1);
		String ndate2 = convertDateJsonToMysql(date2);
		
		if(campaignDao.doesCampaignExist(campaignName)){throw new CampaignServiceException("campaign "+campaignName+" already exists!");}
		Campaign campaign = new Campaign();
		campaign.setName(campaignName);
		campaign.setOem(oem);
		campaignDao.insertCampaign(campaign);
		
		DateEvent dateEvent = new DateEvent();
		dateEvent.setCampaignName(campaignName);
		dateEvent.setDateSegmentOne(ndate1);
		dateEvent.setDateSegmentTwo(ndate2);
		dateEvent.setEventName("campaign definition");
		dateEvent.setName("n/a");
		dateEventDao.insertDateEvent(dateEvent);
	}

	public void addOem(String oem){
		oemDao.insertOem(oem);
	}
	
	public List<String> readOems(){return oemDao.fetchOems();}
	public List<String> readCampaignNames(){return campaignDao.fetchCampaignNames();}
	
	public DateEvent readCampaignDateEvent(String campaignName){
		DateEvent _dateEvent = dateEventDao.readCampaignDateEvent(campaignName);
		/*
		String [] reparse1 = _dateEvent.getDateSegmentOne().split("-");
		String [] reparse2 = _dateEvent.getDateSegmentTwo().split("-");
		String ndate1 = reparse1[1]+"/"+reparse1[2]+"/"+reparse1[0];
		String ndate2 = reparse2[1]+"/"+reparse2[2]+"/"+reparse2[0];
		*/
		String ndate1 = convertMyqlToJson(_dateEvent.getDateSegmentOne());
		String ndate2 = convertMyqlToJson(_dateEvent.getDateSegmentTwo());
		_dateEvent.setDateSegmentOne(ndate1);
		_dateEvent.setDateSegmentTwo(ndate2);
		return _dateEvent;
	}
	
	public boolean isCampaignInCurrentDateRange(String campaignName){
		boolean answer = false;
		DateEvent _dateEvent = dateEventDao.readCampaignDateEvent(campaignName);
		String [] reparse1 = _dateEvent.getDateSegmentOne().split("-");
		String [] reparse2 = _dateEvent.getDateSegmentTwo().split("-");
		String [] reparse3 = DateUtil.getOurFormatToday().split("-");
		int year_start = new Integer(reparse1[0]).intValue();
		int month_start = new Integer(reparse1[1]).intValue();
		int day_start = new Integer(reparse1[2]).intValue();
		int year_end= new Integer(reparse2[0]).intValue();
		int month_end = new Integer(reparse2[1]).intValue();
		int day_end = new Integer(reparse2[2]).intValue();
		int year_now= new Integer(reparse3[0]).intValue();
		int month_now = new Integer(reparse3[1]).intValue();
		int day_now = new Integer(reparse3[2]).intValue();
		Calendar calendar1 = java.util.Calendar.getInstance();
		Calendar calendar2 = java.util.Calendar.getInstance();
		Calendar calendar3 = java.util.Calendar.getInstance();
		calendar1.set(year_start, month_start, day_start);
		calendar2.set(year_end, month_end, day_end);
		calendar3.set(year_now, month_now, day_now);
		boolean start_test = (calendar3.equals(calendar1)) || (calendar3.after(calendar1));
		boolean end_test = (calendar3.equals(calendar2)) || (calendar3.before(calendar2));
		answer = start_test & end_test;
		return answer;
	}
	
	public boolean isTheDateRangeInTheCampaignDateRange(String campaign, DateEvent sendDateEvent){
		boolean answer = false;
		DateEvent campaignDateEvent = dateEventDao.readCampaignDateEvent(campaign);
		String [] reparse1 = campaignDateEvent.getDateSegmentOne().split("-");
		String [] reparse2 = campaignDateEvent.getDateSegmentTwo().split("-");
		int year_start = new Integer(reparse1[0]).intValue();
		int month_start = new Integer(reparse1[1]).intValue();
		int day_start = new Integer(reparse1[2]).intValue();
		int year_end= new Integer(reparse2[0]).intValue();
		int month_end = new Integer(reparse2[1]).intValue();
		int day_end = new Integer(reparse2[2]).intValue();
		
		// send date event domain object has equivalent date segments
		String [] reparse3 = sendDateEvent.getDateSegmentOne().split("-");
		int year_now= new Integer(reparse3[0]).intValue();
		int month_now = new Integer(reparse3[1]).intValue();
		int day_now = new Integer(reparse3[2]).intValue();
		Calendar calendar1 = java.util.Calendar.getInstance();
		Calendar calendar2 = java.util.Calendar.getInstance();
		Calendar calendar3 = java.util.Calendar.getInstance();
		calendar1.set(year_start, month_start, day_start);
		calendar2.set(year_end, month_end, day_end);
		calendar3.set(year_now, month_now, day_now);
		boolean start_test = (calendar3.equals(calendar1)) || (calendar3.after(calendar1));
		boolean end_test = (calendar3.equals(calendar2)) || (calendar3.before(calendar2));
		answer = start_test & end_test;
		return answer;
	}
	
	public void updateCampaignDateEvent( DateEvent dateEvent) throws CampaignServiceException{
		String campaignName = dateEvent.getCampaignName();
		String date1 = dateEvent.getDateSegmentOne();
		String date2 = dateEvent.getDateSegmentTwo();
		if(null == date2 || date2.isEmpty()){date2 = date1;}
		
		if(dateChecker(date1)){throw new CampaignServiceException(date1 + "date 1 wrong format");}
		if(dateChecker(date2)){throw new CampaignServiceException(date2 + "date 2 wrong format");}
		String ndate1 = convertDateJsonToMysql(date1);
		String ndate2 = convertDateJsonToMysql(date2);
		dateEvent.setDateSegmentOne(ndate1);
		dateEvent.setDateSegmentTwo(ndate2);
		dateEventDao.updateCampaignDateEvent(campaignName, dateEvent);
	}
	
	public void updateCampaign(CampaignInformation campaignInformation) throws CampaignServiceException, EmailServiceException{
		
		
		String campaignName = campaignInformation.getDateEvent().getCampaignName();
		for (EMail deleteEmail : campaignInformation.getEmailDeletes()){
			if(null == deleteEmail.getType()){throw new EmailServiceException("null mail type");}
			DeleteEmail deleteEmailComponent = deleteMailMap.get(deleteEmail.getType());
			if(null == deleteEmailComponent){throw new EmailServiceException("unknown mail type:" + deleteEmailComponent);}
			deleteEmailComponent.doit(deleteEmail.getAddress(), campaignName, this);
		}
		for (String attachTemplate : campaignInformation.getTemplateAssociates()){
			templateService.attachTemplateToCampaign(attachTemplate, campaignName);
		}
		for (String detachTemplate : campaignInformation.getTemplateUnassociates()){
			templateService.detachTemplateToCampaign(detachTemplate, campaignName);
		}
		
		if(null != campaignInformation.getAddEmail().getType()){
			AddEmail addEmail = addMailMap.get(campaignInformation.getAddEmail().getType());
			addEmail.doit(campaignInformation.getAddEmail().getAddress(), campaignName, this);
		}
		updateCampaignDateEvent(campaignInformation.getDateEvent());
	}
	
	public String addReviewableMessage(String message, String folder, String version) throws DataAccessException{
		String folderAndFile = userFileReviewPath + folder;
		String fileName = folder+'_'+DateUtil.getToday().replace(' ', '_')+".xhtml";
		fileIODao.folderTestAndCreate(folderAndFile);
		folderAndFile+='/';
		folderAndFile+=fileName;
		return addReviewableMessageInternal(folderAndFile, message, folder, version);
	}
	
	public String addReviewableMessage(String message, String folder, String version, String uri) throws DataAccessException{
		return addReviewableMessageInternal(uri, message, folder, version);
	}

	private String addReviewableMessageInternal(String folderAndFile, String message, String folder, String version) throws DataAccessException{
		fileIODao.writeFileUsingURI(folderAndFile, message);
		List<String> imageUriS = imageURIs(message.replace('\n', ' '));
		for(String imageUri : imageUriS){
			System.out.println("image uri"+imageUri);
		}
		Message message_domain = new Message();
		message_domain.setCampaign(folder);
		message_domain.setImageUriList(imageUriS);
		message_domain.setMessage(message.replace('\n', ' '));
		message_domain.setUri(folderAndFile);
		message_domain.setVersion(version);
		messageDao.addReviewableMessage(message_domain);
		try {
			messageDao.addMessageContext(message_domain);
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
			throw new DataAccessException(nsae.getMessage());
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
			throw new DataAccessException(uee.getMessage());
		}

		return folderAndFile;
	}
	
	public List<Message> fetchMessagesForReviewOrUpdate(String campaign){
		return messageDao.readReviewableMessageIdenties(campaign);
	}
	
	
	public static List<String> imageURIs(String generatedFile){
		List<String> uriList = new ArrayList<String>();
		String [] breakItUp = generatedFile.split("<img");
		System.out.println("breakItUp:"+breakItUp);
		for(String imgChunk : breakItUp){
			System.out.println(imgChunk);
			int startPosition = imgChunk.indexOf("src=\"")+"src=\"".length();
			int endPosition = imgChunk.indexOf("\"", startPosition);
			if(startPosition == -1 || endPosition == -1){continue;}
			String uri = imgChunk.substring(startPosition, endPosition);
			System.out.println("imageUri:"+uri);
			uriList.add(uri);
		}
		return uriList;
	}
	
	public Message fetchMessageForReviewAndEditing(String uri, String version) throws DataAccessException{
		Message message = new Message();
		int i_version = new Integer(version).intValue();
		i_version += 1;
		message.setVersion(""+i_version);
		message.setUri(uri);
		message.setMessage(fileIODao.readStringFileUsingURI(uri));
		return message;
	}
	
	public List<Message> promoteCampaignToQAPossibleMessages(String campaignName) throws CampaignServiceException{
		return messageDao.readReviewableMessageIdenties(campaignName);
	}
	
	public List<Message> fetchAllCampaignsMessages(String campaignName){
		return messageDao.readAllMessageAssociatedToCampaign(campaignName);
	}
}
