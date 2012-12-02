package com.zenred.eadvert.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;

import com.zenred.eadvert.dao.CampaignDao;
import com.zenred.eadvert.dao.ContactAddressDao;
import com.zenred.eadvert.dao.ContactDao;
import com.zenred.eadvert.dao.ContactNameDao;
import com.zenred.eadvert.dao.ContactPhoneDao;
import com.zenred.eadvert.dao.DateEventDao;
import com.zenred.eadvert.dao.EmailDao;
import com.zenred.eadvert.dao.ExcelReaderDao;
import com.zenred.eadvert.dao.FileIODao;
import com.zenred.eadvert.dao.MessageDao;
import com.zenred.eadvert.dao.OemDao;
import com.zenred.eadvert.dao.RuntimeDao;
import com.zenred.eadvert.dao.UploadFileDao;
import com.zenred.eadvert.exception.DataAccessException;
import com.zenred.eadvert.model.domain.Contact;
import com.zenred.eadvert.model.domain.DateEvent;
import com.zenred.eadvert.model.domain.EMail;
import com.zenred.eadvert.model.domain.EmailAddressMessage;
import com.zenred.eadvert.validation.ExcelValidator;
import com.zenred.util.DateUtil;
import com.zenred.util.MD5Util;

public class SubscriberService {

	private UploadFileDao uploadDao;
	private FileIODao fileIODao;
	private ExcelReaderDao excelDao;
	private ExcelValidator excelValidator;
	private EmailDao emailDao;
	private DateEventDao dateEventDao;
	private RuntimeDao runtimeDao;
	private OemDao oemDao;
	private CampaignDao campaignDao;
	private MessageDao messageDao;
	
	private String userFileProdPath;
	private String sendMailScript;
	private String sendmailLog;
	private String secondsSleep;
	private String daysRetry;
	

	public Map<String, List<String>> cont;
	EMail emailAdd = new EMail();

	public void setUploadDao(UploadFileDao uploadDao) {
		this.uploadDao = uploadDao;
	}

	public void setExcelValidator(ExcelValidator excelValidator) {
		this.excelValidator = excelValidator;
	}

	public ExcelReaderDao getExcelDao() {
		return this.excelDao;
	}

	public void setExcelDao(ExcelReaderDao excelDao) {
		this.excelDao = excelDao;
	}
	
	public void setFileIODao(FileIODao fileIODao){
		this.fileIODao = fileIODao;
	}

	public void setEmailDao(EmailDao emailDao) {
		this.emailDao = emailDao;
	}

	public void setDateEventDao(DateEventDao dateEventDao) {
		this.dateEventDao = dateEventDao;
	}

	public void setUserFileProdPath(String userFileProdPath) {
		this.userFileProdPath = userFileProdPath;
	}

	public void setSendMailScript(String sendMailScript) {
		this.sendMailScript = sendMailScript;
	}

	public void setRuntimeDao(RuntimeDao runtimeDao) {
		this.runtimeDao = runtimeDao;
	}
	
	public void setOemDao(OemDao oemDao) {
		this.oemDao = oemDao;
	}

	public void setCampaignDao(CampaignDao campaignDao) {
		this.campaignDao = campaignDao;
	}

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	public void setSendmailLog(String sendmailLog) {
		this.sendmailLog = sendmailLog;
	}

	public void setSecondsSleep(String secondsSleep) {
		this.secondsSleep = secondsSleep;
	}

	public void setDaysRetry(String daysRetry) {
		this.daysRetry = daysRetry;
	}

	public String uploadExcelFile(String filePath, List items) {

		String itemName = null;
		System.out.println("Items" + items);
		Iterator itr = items.iterator();

		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			System.out
					.println("SuscriberService -> uploadExcelFile -> fileItemContentSize="
							+ item.get().length);
			if (item.isFormField()) {
				String name = item.getFieldName();
				String value = item.getString();
			} else {
				try {
					itemName = item.getName();
					itemName = FilenameUtils.getName(itemName);
					System.out.println("File Utils = " + itemName);

					File savedFile = new File(filePath + itemName);

					item.write(savedFile);

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.toString());
				}
			}
		}
		return itemName;
	}

	/*
	 * public void uploadFile(String filePath, String serverFileLoc, String
	 * fileName) { this.uploadDao.copyFile(filePath, serverFileLoc, fileName); }
	 */

	public void excelServer(String serverPath) {

		this.cont = this.excelDao.excelReader(serverPath);
	}

	public Map<String, List<String>> getCont() {
		return this.cont;
	}

	public void emailIDToContact(String emailAddress) {
		ContactDao copy = new ContactDao();
		Contact conObj = new Contact();

		emailAdd.setAddress(emailAddress);
		conObj.setType(conObj.Type.individual);
		copy.copyToContact(emailAdd, conObj);
	}

	public void contactPhone(String addPhone, String addMobilePhone) {
		ContactPhoneDao phoneObj = new ContactPhoneDao();
		phoneObj.addPhoneNo(emailAdd, addPhone, addMobilePhone);

	}

	public void contactName(String firstName, String middleName, String lastName) {
		ContactNameDao nameObj = new ContactNameDao();
		nameObj.addNames(emailAdd, firstName, middleName, lastName);
	}

	public void contactAddress(String address1, String address2, String city,
			String state, String zip) {
		ContactAddressDao addrObj = new ContactAddressDao();
		addrObj.addAddress(emailAdd, address1, address2, city, state, zip);
	}
	
	public String convertByteStreamIntoTempFile(byte [] byteStream){
		String tempFileName = null;
		try {
			tempFileName = "/tmp/mBlastUpload"+MD5Util.MD5(""+Math.random()).substring(0, 8);
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}
		try {
			fileIODao.writeByteFileUsingURI(tempFileName, byteStream);
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
		return tempFileName;
	}

	public List<EmailAddressMessage> lookForMessagesReadyToSendToSubscribers() throws DataAccessException{
		System.out.println("start lookForMessagesReadyToSendToSubscribers");
		
		List<DateEvent> dateEventList = dateEventDao.readProductionMessagesForCampaigns();
		String [] todayRay = DateUtil.getOurFormatToday().split("-");
		int year_now= new Integer(todayRay[0]).intValue();
		int month_now = new Integer(todayRay[1]).intValue()-1; // January is zero
		int day_now = new Integer(todayRay[2]).intValue();
		Calendar calendarNow = java.util.Calendar.getInstance();
		calendarNow.set(year_now, month_now, day_now);
		List<EmailAddressMessage> messageList = sendAndLoad(calendarNow, dateEventList);
		
		// give sendmail a chance to do its thing ...
		
		int seconds = new Integer(secondsSleep).intValue() *1000;
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		return messageList;
	}
	
	@SuppressWarnings("deprecation")
	private List<EmailAddressMessage> sendAndLoad(Calendar calendarNow,
			List<DateEvent> dateEventList) throws DataAccessException {
		List<EmailAddressMessage> messageList = new ArrayList<EmailAddressMessage>();
		for (DateEvent dateEvent : dateEventList) {
			String basePath = userFileProdPath + dateEvent.getCampaignName()
					+ "/";
			List<String> subscriberEmailsMessagesInCampaign = fileIODao
					.directoryFileReader(basePath);
			String[] targetDateRay = dateEvent.getDateSegmentOne().split("-");
			int year_target = new Integer(targetDateRay[0]).intValue();
			int month_target = new Integer(targetDateRay[1]).intValue();
			int day_target = new Integer(targetDateRay[2]).intValue();
			Calendar calendarTarget = java.util.Calendar.getInstance();
			calendarTarget.set(year_target, month_target, day_target);
			System.out.println("lookForMessagesReadyToSendToSubscribers:"
					+ calendarNow + ":" + calendarTarget + ":"
					+ calendarNow.before(calendarTarget) + ":"
					+ calendarNow.equals(calendarTarget) + ":"
					+ calendarNow.after(calendarTarget));
			if (calendarNow.before(calendarTarget)
					|| calendarNow.equals(calendarTarget)) {
				String targetName = dateEvent.getEventName()
						.replace("/review", "/prod").replace(".xhtml", "");
				for (String subscriberEmailMessage : subscriberEmailsMessagesInCampaign) {
					EmailAddressMessage emailAddressMessage = new EmailAddressMessage();
					String fullPathToSubscriberMessage = this.userFileProdPath
							+ dateEvent.getCampaignName() + "/"
							+ subscriberEmailMessage;
					System.out.println("targetName: "
							+ fullPathToSubscriberMessage + ":" + targetName);
					if (fullPathToSubscriberMessage.contains(targetName)) {
						String commandBash = "#!/bin/sh\n"
								+ MessageFormat.format(sendMailScript,
										fullPathToSubscriberMessage);
						commandBash += " >> " + sendmailLog + " 2>&1";
						String key = null;
						try {
							key = MD5Util.MD5(Math.random() + "").substring(0,
									8);
						} catch (NoSuchAlgorithmException nsae) {
							nsae.printStackTrace();
						} catch (UnsupportedEncodingException uee) {
							uee.printStackTrace();
						}
						String command = "/tmp/blast_" + key + ".sh";
						System.out.println("Subscriber_command:" + command
								+ " commandBash: " + commandBash + " >> "
								+ sendmailLog + " 2>&1");
						try {
							fileIODao.writeFileUsingURI(command, commandBash);
						} catch (Exception gene) {
							gene.printStackTrace();
						}
						emailAddressMessage.setStatus(runtimeDao
								.executeNativeEmailer("/bin/sh " + command));
						StringBuffer e_mail = fileIODao.readFileUsingURI(fullPathToSubscriberMessage);
						int pos0 = e_mail.indexOf("To: ");
						int pos1 = e_mail.indexOf("\n", pos0);
						String address = e_mail.substring(pos0+"To: ".length(),pos1);
						System.out.println("address:"+address+" campaignName:"+dateEvent.getCampaignName());
						String emailAddressId = emailDao
								.fetchEmailAddressIDOfUserType(address,
										"subscriber", dateEvent.getCampaignName());
						if(null == emailAddressId){continue;}	// bogus most likely
						emailAddressMessage.setEmailAddressID(new Integer(
								emailAddressId).intValue());

						dateEvent.setName("SENT_TO_SUBSCRIBER");
						dateEventDao.updateCampaignDateEvent(dateEvent);
						String messageID = messageDao
								.readMessageIDUsingName(dateEvent
										.getEventName());
						if(null == messageID){continue;}	// bogus most likely
						messageDao.addMessageTransferStatus(messageID,
								emailAddressId, calendarNow.getTime()
										.toString());
						emailAddressMessage.setMessageID(messageID);
						messageList.add(emailAddressMessage);
					}
				}
			}
		}
		return messageList;
	}	
	
	public List<String> accessCampaignsAssociatedToUser(String user){
		List<String> campaignList = new ArrayList<String>();
		List<String> oemsAssociatedToUsers = oemDao.fetchOemsAssociatedToUser(user);
		for(String oemAssociatedToUser : oemsAssociatedToUsers){
			List<String> oneList = campaignDao.fetchCampaignsAssociatedToProvider(oemAssociatedToUser);
			campaignList.addAll(oneList);
		}
		return campaignList;
	}
}