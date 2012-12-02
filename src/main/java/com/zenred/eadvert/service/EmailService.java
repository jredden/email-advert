package com.zenred.eadvert.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.zenred.eadvert.dao.CampaignDao;
import com.zenred.eadvert.dao.DateEventDao;
import com.zenred.eadvert.dao.EmailDao;
import com.zenred.eadvert.dao.ExcelReaderDao;
import com.zenred.eadvert.dao.FileIODao;
import com.zenred.eadvert.dao.MessageDao;
import com.zenred.eadvert.dao.RuntimeDao;
import com.zenred.eadvert.exception.DataAccessException;
import com.zenred.eadvert.exception.EmailServiceException;
import com.zenred.eadvert.model.domain.DateEvent;
import com.zenred.eadvert.model.domain.EMail;
import com.zenred.eadvert.model.domain.EmailPlusContext;
import com.zenred.eadvert.model.domain.Message;
import com.zenred.eadvert.model.domain.MessageContext;
import com.zenred.eadvert.model.domain.MessageContext.State;
import com.zenred.eadvert.model.domain.MessageContextPlusRevision;
import com.zenred.eadvert.model.domain.ProductionMessage;
import com.zenred.util.MD5Util;

public class EmailService {

	public static String STATUS = "STATUS";

	private EmailDao emailDao;
	private MessageDao messageDao;
	private FileIODao fileIODao;
	private CampaignDao campaignDao;
	private RuntimeDao runtimeDao;
	private DateEventDao dateEventDao;

	private ExcelReaderDao excelDao;

	public ExcelReaderDao getExcelDao() {
		return excelDao;
	}

	public void setExcelDao(ExcelReaderDao excelDao) {
		this.excelDao = excelDao;
	}

	private CampaignService campaignService; // Spring DI complains ... do this
	// one by hand

	private String mailer_master;

	private String userFileReviewPath;
	private String userFileQAPath;
	private String mailGenPath;

	private String userFileProdPath;

	/**
	 * @param mailSendPath
	 *            the mailSendPath to set
	 */
	public void setMailSendPath(String mailSendPath) {
		this.mailSendPath = mailSendPath;

		campaignService = new CampaignService(); // seems circular to Spring ...
		// do it manually
		campaignService.setDateEventDao(new DateEventDao());
	}

	private String mailSendPath;

	// $mailer->AddEmbeddedImage("header_00.jpg","header", "", "base64",
	// "image/jpg");
	private static String img_template = "$mailer->AddEmbeddedImage(\"{0}\",\"{1}\", \"\", \"base64\", \"{2}\");";
	private static String CID = "cid";

	protected class BaseAndLeaf {
		protected String base;

		protected String leaf;
		protected String twig;

		public String baseAndLeaf() {
			return base + "/" + leaf;
		}

		public String toString() {
			return "base:" + base + "\nleaf:" + leaf + "\n twig:" + twig + "\n";
		}
	}

	/**
	 * @param emailDao
	 *            the emailDao to set
	 */

	public void setEmailDao(EmailDao emailDao) {
		this.emailDao = emailDao;
	}

	/**
	 * @param messageDao
	 *            the messageDao to set
	 */
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	/**
	 * @param fileIODao
	 *            the fileIODao to set
	 */
	public void setFileIODao(FileIODao fileIODao) {
		this.fileIODao = fileIODao;
	}

	/**
	 * @param campaignDao
	 *            the campaignDao to set
	 */
	public void setCampaignDao(CampaignDao campaignDao) {
		this.campaignDao = campaignDao;
	}

	/**
	 * @param runtimeDao
	 *            the runtimeDao to set
	 */
	public void setRuntimeDao(RuntimeDao runtimeDao) {
		this.runtimeDao = runtimeDao;
	}

	public void setDateEventDao(DateEventDao dateEventDao) {
		this.dateEventDao = dateEventDao;
	}

	public void setCampaignService(CampaignService campaignService) {
		this.campaignService = campaignService;
	}

	/**
	 * @param mailerMaster
	 *            the mailer_master to set
	 */
	public void setMailer_master(String mailerMaster) {
		mailer_master = mailerMaster;
	}

	public void setUserFileReviewPath(String userFileReviewPath) {
		this.userFileReviewPath = userFileReviewPath;
	}

	/**
	 * @param userFileQAPath
	 *            the userFileQAPath to set
	 */
	public void setUserFileQAPath(String userFileQAPath) {
		this.userFileQAPath = userFileQAPath;
	}

	/**
	 * @param mailGenPath
	 *            the mailGenPath to set
	 */
	public void setMailGenPath(String mailGenPath) {
		this.mailGenPath = mailGenPath;
	}

	public void setUserFileProdPath(String userFileProdPath) {
		this.userFileProdPath = userFileProdPath;
	}

	protected class Result {
		public boolean bad;
		public StringBuffer error;
	}

	private void decodeResult(Result result) throws EmailServiceException {
		if (result.bad) {
			throw new EmailServiceException(result.error.toString());
		}
	}

	public static String nullToNA(String theConcern) {
		return null == theConcern ? "n/a" : theConcern;
	}
	
	public boolean doesEmailAddressForRoleAlreadyExist(String emailAddress, String userRole, String campaignName){
		return emailDao.doesEmailAddressExistForUserType(emailAddress, userRole, campaignName);
	}

	public void addCampaignTestEmail(String emailAddress, String campaignName)
			throws EmailServiceException {
		if (emailAddress.isEmpty()) {
			return;
		}
		decodeResult(isEmailAddressBadOrGood(emailAddress, this));
		EMail email = new EMail();
		email.setAddress(emailAddress);
		email.setType(EMail.mailType.test);
		emailDao.addEmail(email);
		emailDao.addEmailCampaignAssociations(email, campaignName);
	}

	public void addCampaignAdminEmail(String emailAddress, String campaignName)
			throws EmailServiceException {
		if (emailAddress.isEmpty()) {
			return;
		}
		decodeResult(isEmailAddressBadOrGood(emailAddress, this));
		EMail email = new EMail();
		email.setAddress(emailAddress);
		email.setType(EMail.mailType.admin);
		emailDao.addEmail(email);
		emailDao.addEmailCampaignAssociations(email, campaignName);
	}

	public void addCampaignProviderEmail(String emailAddress,
			String campaignName) throws EmailServiceException {
		if (emailAddress.isEmpty()) {
			return;
		}
		decodeResult(isEmailAddressBadOrGood(emailAddress, this));
		EMail email = new EMail();
		email.setAddress(emailAddress);
		email.setType(EMail.mailType.provider);
		emailDao.addEmail(email);
		emailDao.addEmailCampaignAssociations(email, campaignName);
	}

	public void addOemProviderEmail(String emailAddress, String oemName)
			throws EmailServiceException {
		if (emailAddress.isEmpty()) {
			return;
		}
		decodeResult(isEmailAddressBadOrGood(emailAddress, this));
		EMail email = new EMail();
		email.setAddress(emailAddress);
		email.setType(EMail.mailType.provider);
		emailDao.addEmail(email);
		emailDao.addEmailOemAssociations(email, oemName);
	}

	public void addCampaignSubscriberEmail(String emailAddress,
			String campaignName) throws EmailServiceException {
		if (emailAddress.isEmpty()) {
			return;
		}
		decodeResult(isEmailAddressBadOrGood(emailAddress, this));
		EMail email = new EMail();
		email.setAddress(emailAddress);
		email.setType(EMail.mailType.subscriber);
		emailDao.addEmail(email);
		emailDao.addEmailCampaignAssociations(email, campaignName);
	}

	public void addServiceSubscriberEmail(String emailAddress)
			throws EmailServiceException {

		if (emailAddress.isEmpty()) {
			System.out.println("Email Address is Mandatory");
			return;
		}

		decodeResult(isEmailAddressBadOrGood(emailAddress, this));
		EMail email = new EMail();
		email.setAddress(emailAddress);
		email.setType(EMail.mailType.subscriber);
		emailDao.addEmail(email);

	}

	public int fetchNoEmailAddress() {
		int getNo = emailDao.getNoEmailAdd();

		return getNo;
	}

	public void emailAddressOptsOut(String emailAddress)
			throws EmailServiceException {
		decodeResult(isEmailAddressBadOrGood(emailAddress, this));
		if (!emailDao.doesEmailAddressExist(emailAddress, "subscriber")) {
			throw new EmailServiceException("Email Address " + emailAddress
					+ " doesn't exist!");
		}
		emailDao.setEmailAddressInactive(emailAddress, "subscriber");
	}

	public void deleteCampaignTestEmail(String emailAddress, String campaignName)
			throws EmailServiceException {
		decodeResult(isEmailAddressBadOrGood(emailAddress, this));
		EMail email = new EMail();
		email.setAddress(emailAddress);
		email.setType(EMail.mailType.test);
		emailDao.deleteEmail(email);
	}

	public void deleteCampaignAdminEmail(String emailAddress,
			String campaignName) throws EmailServiceException {
		decodeResult(isEmailAddressBadOrGood(emailAddress, this));
		EMail email = new EMail();
		email.setAddress(emailAddress);
		email.setType(EMail.mailType.admin);
		emailDao.deleteEmail(email);
	}

	public void deleteCampaignProviderEmail(String emailAddress,
			String campaignName) throws EmailServiceException {
		decodeResult(isEmailAddressBadOrGood(emailAddress, this));
		EMail email = new EMail();
		email.setAddress(emailAddress);
		email.setType(EMail.mailType.provider);
		emailDao.deleteEmail(email);
	}

	public void deleteCampaignSubscriberEmail(String emailAddress,
			String campaignName) throws EmailServiceException {
		decodeResult(isEmailAddressBadOrGood(emailAddress, this));
		EMail email = new EMail();
		email.setAddress(emailAddress);
		email.setType(EMail.mailType.subscriber);
		emailDao.deleteEmail(email);
	}

	public List<EMail> readCampaignEmails(String campaignName) {
		List<EMail> emailList = emailDao.fetchCampaignsEmails(campaignName);
		return emailList;
	}

	public List<EmailPlusContext> readEmailsNotAssosciatedToCampaign() {
		return emailDao.fetchEmailsNotAssociatedToCampaign();
	}

	public void associateEMailAddressesToCampaign(List<EMail> addressList,
			String campaignName) {
		for (EMail eMail : addressList) {
			emailDao.addEmailCampaignAssociations(eMail, campaignName);
		}
	}

	/*
	 * promote from review to QA ... complex because of the moving and parsing.
	 */
	public List<Message> promoteCampaignToQA(List<Message> messageList)
			throws EmailServiceException, DataAccessException {

		/*
		 * first part generates php files for each mail message in the campaign
		 * using a master template
		 */

		String status = "";
		String mailer_master_template = "";
		String campaignName = messageList.get(0).getCampaign();
		String subject = campaignName;
		String from = emailDao.fetchCampaignsProviderAddress(campaignName);
		String sender = campaignDao
				.fetchOemNameAssociatedWithCampaign(campaignName);
		String reply = from;
		List<String> emailMessageList = null;

		List<String> qaTestAddresses = emailDao
				.fetchEmailsOfTypeAssociatedToCampaign(campaignName, "test");
		if(qaTestAddresses.isEmpty()){throw new EmailServiceException("There are no QA users for " + campaignName);}
		System.out.println("qaTestAddresses:" + qaTestAddresses);

		String qACampaignFolder = userFileQAPath + campaignName;
		fileIODao.folderTestAndCreate(qACampaignFolder);

		try {
			mailer_master_template = fileIODao.readStringFileUsingURI(mailer_master);
		} catch (DataAccessException dae) {
			dae.printStackTrace();
			throw new EmailServiceException(dae.getMessage());
		}

		for (Message message : messageList) {
			message.setImageUriList(messageDao
					.readGraphicUidsAssociatedToMessage(message.getUri()));
			try {
				message
						.setMessage(fileIODao
								.readStringFileUsingURI(message.getUri()));
			} catch (DataAccessException dae) {
				dae.printStackTrace();
				System.err.println(dae.getMessage().toString());
				throw new EmailServiceException(dae.getMessage());
			}

		}
		
//		System.out.println("qaTestAddresses.2:" + qaTestAddresses);
		
		int fileCounter = -1;
		for (String qaTestAddress : qaTestAddresses) {
			++fileCounter;
			for (Message message : messageList) {
				List<BaseAndLeaf> baseAndLeafList = genBaseNames(genBaseAndLeaves(message
						.getImageUriList()));
				StringBuffer imageCommands = new StringBuffer("");
				System.out.println("baseAndLeafList:"+baseAndLeafList);
				for (BaseAndLeaf baseAndLeaf : baseAndLeafList) {
					imageCommands.append(MessageFormat.format(img_template,
							new Object[] { baseAndLeaf.leaf, baseAndLeaf.twig,
									"." }));
				}
				String _mailer_master = MessageFormat.format(
						mailer_master_template, new Object[] {
								message.getMessage(), from, sender,
								qaTestAddress, "QA",
								message.getSubject() + " information",
								imageCommands, "<" + qaTestAddress + ">", "QA",
								qaTestAddress });
				String _mailer_master2 = replaceUrisWithCids(_mailer_master,
						baseAndLeafList);
				System.out.println("mailer_master2:" + _mailer_master2);
				int index_uri_leaf = message.getUri().lastIndexOf('/') + 1;

				try {
					fileIODao.writeFileUsingURI(qACampaignFolder
							+ '/'
							+ message.getUri().substring(index_uri_leaf,
									message.getUri().length()).replace(
									".xhtml", "_" + message.getVersion() + ".php"),
							_mailer_master2);
				} catch (DataAccessException dae) {
					dae.printStackTrace();
				}
				// graphic is actually written where php interpreter can find it
				// ...
				for (BaseAndLeaf baseAndLeaf : baseAndLeafList) {
					String fullUri = baseAndLeaf.baseAndLeaf();
					System.out.println("fullUri:"+fullUri+ " fileCounter:" + fileCounter);
					try {
						byte[] theGraphic = fileIODao.readFromURL(fullUri);
						fileIODao.writeByteFileUsingURI(qACampaignFolder + '/'
								+ baseAndLeaf.leaf, theGraphic);
					} catch (DataAccessException dae) {
						dae.printStackTrace();
						throw new EmailServiceException(dae.getMessage());
					}
				}
			}

			/*
			 * the second part generates eml files for each member of QA (in the
			 * largest sense, not just Beckys group)
			 */

			try {
				emailMessageList = runtimeDao.executeNativeCommand(mailGenPath,
						userFileQAPath + campaignName); // "/" already there ...
			} catch (DataAccessException dae) {
				dae.printStackTrace();
				throw new EmailServiceException(dae.getMessage());
			}
		}
		/*
		 * now actually send the message to (larger) QA group
		 * 
		 * check the MD5 first ... messy since you want to pick the most recent
		 * revision
		 */
		for (String messageURI : emailMessageList) {
			String[] leaf_and_other = messageURI.split("\\.");
			System.out.println("messageURI:::::" + leaf_and_other[0] + "::"
					+ leaf_and_other[1]);
			int versionIndex = leaf_and_other[0].lastIndexOf('_')+1;
			int version = new Integer(leaf_and_other[0].substring(versionIndex, leaf_and_other[0].length())).intValue();

			String fullPath = userFileQAPath + campaignName + "/"
					+ leaf_and_other[0] + ".eml";
			String fullPathXhtml = userFileQAPath + campaignName + "/"
					+ leaf_and_other[0];
			fullPathXhtml = fullPathXhtml.replace("/qa/", "/review/");
			int tippieEnd = fullPathXhtml.lastIndexOf('_');
			fullPathXhtml = fullPathXhtml.substring(0, tippieEnd);
			System.out.println("promoteCampaignToQA.version:"+version+":"+leaf_and_other[0]);
			MessageContextPlusRevision messageContext = messageDao.readMessageGivenUri(fullPathXhtml+".xhtml", version);

			// ignore message already in a production or sent State
			if(messageDao.isMessageAlreadyPromotedToProdOrSent(fullPathXhtml+".xhtml", messageContext.getRevision())){
				System.out.println("Skipping:"+fullPathXhtml+".xhtml");
				continue;
			}
			
			

			if (null == messageContext.getContentMD5()) {
				System.err.println("Warning, " + messageContext
						+ " may have been tampered with ...");
			}
			try {
				messageContext.setPlenusMD5(MD5Util.MD5(fileIODao
						.readStringFileUsingURI(fullPath)));
			} catch (NoSuchAlgorithmException nsae) {
				nsae.printStackTrace();
				throw new EmailServiceException(nsae.getMessage());
			} catch (UnsupportedEncodingException uee) {
				uee.printStackTrace();
				throw new EmailServiceException(uee.getMessage());
			} catch (DataAccessException dae) {
				dae.printStackTrace();
				throw new EmailServiceException(dae.getMessage());
			}
			System.out.println("messageContext.done::::" + messageContext+ "]["+fullPath+"]["+mailSendPath+"::::");
			for (String qaTestAddress : qaTestAddresses) {
				String commandBash ="#/bin/sh\n" + MessageFormat.format( mailSendPath, fullPath, qaTestAddress);
				String key = null;
				try {
					key = MD5Util.MD5(qaTestAddress).substring(0, 8);
				} catch (NoSuchAlgorithmException nsae) {
					nsae.printStackTrace();
				} catch (UnsupportedEncodingException uee) {
					uee.printStackTrace();
				}
				String command = "/tmp/blast_"+key+".sh";
				System.out.println("command:"+command + " commandBash" + commandBash);
				try {
					fileIODao.writeFileUsingURI(command, commandBash);
				} catch (Exception gene) {
					gene.printStackTrace();
				}


				status += runtimeDao.executeNativeEmailer("/bin/sh " + command);
			}
			messageContext.setMc_state(State.production); // an official
			System.out.println("processing messageContext:"+messageContext);
			// candidate message
			// is now ...
			// production + review ... not a Jedi yet ...
			messageDao.updateMessageContext(messageContext, fullPathXhtml+".xhtml"); // update message
			// context with
			// production
			// state (almost
			// ready to go)
		}
		Message _status_message = new Message();
		_status_message.setSubject(STATUS);
		_status_message.setMessage(status);
		System.out.println("_status_message:" + _status_message.getMessage());
		messageList.add(_status_message);

		return messageList;
	}

	public List<BaseAndLeaf> genBaseAndLeaves(List<String> graphicsUriList) {
		List<BaseAndLeaf> baseAndLeafList = new ArrayList<BaseAndLeaf>();
		for (String uri : graphicsUriList) {
			int index = uri.lastIndexOf('/');
			int slength = uri.length();
			if (-1 == index) {
				System.err.println("uri:" + uri + " seems bogus");
				continue;
			}
			BaseAndLeaf baseAndLeaf = new BaseAndLeaf();
			baseAndLeaf.base = uri.substring(0, index);
			baseAndLeaf.leaf = uri.substring(index + 1, slength);
			baseAndLeafList.add(baseAndLeaf);
		}

		return baseAndLeafList;
	}

	public List<BaseAndLeaf> genBaseNames(List<BaseAndLeaf> baseAndLeafList) {

		for (BaseAndLeaf baseAndLeaf : baseAndLeafList) {
			int index = baseAndLeaf.leaf.lastIndexOf('.');
			baseAndLeaf.twig = baseAndLeaf.leaf.substring(0, index);
		}
		return baseAndLeafList;
	}

	public String replaceUrisWithCids(String copy,
			List<BaseAndLeaf> baseAndLeafList) {
		int currIndex = 0;
		System.out.println("copy:" + copy);
		while (currIndex >= 0) {
			currIndex = copy.indexOf("<img", currIndex);
			if (currIndex < 0) {
				break;
			}
			System.out.println("currIndex1:" + currIndex + ":");
			currIndex = copy.indexOf("src", currIndex);
			System.out.println("currIndex2:" + currIndex + ":");
			currIndex = copy.indexOf('=', currIndex);
			System.out.println("currIndex3:" + currIndex + ":");
			currIndex = copy.indexOf('"', currIndex);
			System.out.println("currIndex4:" + currIndex + ":");
			int start_string_pos = currIndex;
			int end_pos = copy.indexOf('"', currIndex + 1);
			System.out.println("spos:" + start_string_pos + " epos:" + end_pos
					+ "substring:" + copy.substring(start_string_pos, end_pos));
			String complete = copy.substring(start_string_pos, end_pos);
			System.out.println("complete:" + complete);
			boolean doit = false;
			String _baseLeaf = "";
			String _twig = "";
			for (BaseAndLeaf baseAndLeaf : baseAndLeafList) {
				System.out.println("baseAndLeaf:" + baseAndLeaf.baseAndLeaf()
						+ ":");
				if (complete.contains(baseAndLeaf.baseAndLeaf())) {
					System.out.println("match:" + complete + ":"
							+ baseAndLeaf.baseAndLeaf() + ":" + CID + ':'
							+ baseAndLeaf.twig);
					_baseLeaf = baseAndLeaf.baseAndLeaf();
					_twig = baseAndLeaf.twig;
					doit = true;
				}
			}
			currIndex = copy.indexOf("/>", currIndex);
			System.out.println("currIndex5:" + currIndex + ":");
			if (doit) {
				copy = copy.replace(_baseLeaf, CID + ':' + _twig);
			}
		}
		return copy;
	}

	public List<Message> readMessagesThatCouldBeProductionReady() {
		List<Message> messageList = messageDao
				.readMessagesReadyForQAToPromoteToProduction();
		List<Message> filteredMessageList = new ArrayList<Message>();

		for (Message message : messageList) {
			System.out.println("campaign:" + message.getCampaign());
			if (campaignService.isCampaignInCurrentDateRange(message
					.getCampaign())) {

				filteredMessageList.add(message);
			}
		}
		return filteredMessageList;
	}

	/*
	 * promotes messages to production environment and schedules them using
	 * quartz
	 */
	public List<String> promoteToProduction(
			Map<String, ProductionMessage> messageMap)
			throws DataAccessException {
		List<String> whatWasMovedToProd = new ArrayList<String>();
		Set<String> keys = messageMap.keySet();
		Iterator<String> iter = keys.iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			ProductionMessage productionMessage = messageMap.get(key);
			String campaign = productionMessage.getCampaign();
			List<String> subscriberAddresses = emailDao
					.fetchEmailsOfTypeAssociatedToCampaign(campaign,
							"subscriber");
			subscriberAddresses.addAll(emailDao
					.fetchEmailsOfTypeAssopciatedToCampaignAndInterestGroup(
							campaign, "subscriber"));
			String prodCampaignFolder = userFileProdPath + campaign + "/";
			fileIODao.folderTestAndCreate(prodCampaignFolder);
			String basename = productionMessage.getUri()
					.replace(userFileReviewPath + campaign + "/", "")
					.replace(".xhtml", "");
			System.out.println("prodCampaignFolder:" + prodCampaignFolder
					+ " basename:" + basename + " campaign:" + campaign);
			String qaFile = userFileQAPath + campaign + "/" + basename + "_"
					+ productionMessage.getVersion() + ".eml";
			if (FileIODao.doesFileNotExist(qaFile)) {
				System.err.println("Warning, " + qaFile
						+ " should exist, does not!");
				continue;
			}
			StringBuffer nowATemplate = fileIODao.readFileUsingURI(qaFile);
			int fromColonPosition = nowATemplate.indexOf("From: ", 0);
			// fromColonPosition -= "From: ".length();
			int lastPosition = nowATemplate.length();
			String readyToAddSubscriber = nowATemplate.substring(
					fromColonPosition, lastPosition);
			for (String subscriberAddress : subscriberAddresses) {
				String subscriberAddressPattern = subscriberAddress.replace(
						'@', '_');
				String prodFile = prodCampaignFolder + basename + "_"
						+ productionMessage.getVersion() + "_"
						+ subscriberAddressPattern + ".eml";
				String finalizedMessage = "Date: \nTo: " + subscriberAddress
						+ "\n" + readyToAddSubscriber;
				// System.out.println("ProdFileDataBefore:"+ finalizedMessage+":::");
				finalizedMessage = replaceQAEMailAvecSubscribers(finalizedMessage, subscriberAddress);
				System.out.println("ProdFileDataAfter:" + finalizedMessage+":::");
				fileIODao.writeFileUsingURI(prodFile, finalizedMessage);
				whatWasMovedToProd.add(prodFile);
				DateEvent dateEvent = new DateEvent();
				dateEvent.setCampaignName(campaign);
				dateEvent.setEventName(productionMessage.getUri());
				dateEvent.setName("PRODUCTION_MESSAGE");
				dateEvent.setDateSegmentOne(CampaignService
						.convertDateJsonToMysql(productionMessage
								.getSendOffDate()));
				dateEvent.setDateSegmentTwo(CampaignService
						.convertDateJsonToMysql(productionMessage
								.getSendOffDate()));

				// make sure that the requested send date is in the time frame
				// of the campaign
				//
				if (campaignService.isTheDateRangeInTheCampaignDateRange(
						campaign, dateEvent)) {
					dateEventDao.insertDateEvent(dateEvent);
				} else {
					throw new DataAccessException(
							"Date for sending Matrix Blast in not in the range of the Campaign:"
									+ campaign + ":" + dateEvent);
				}
			}
			MessageContext messageContext = messageDao
					.readMessageContextAssociatedToMessage(
							productionMessage.getUri(),
							productionMessage.getVersion());
			messageContext.setMc_state(State.sent);
			messageDao.updateMessageContext(messageContext);
			Message message = new Message();
			message.setCampaign(campaign);
			message.setUri(productionMessage.getUri());
			message.setVersion(productionMessage.getVersion());
			messageDao.updateMessage(message);
		}
		return whatWasMovedToProd;
	}	
	
	public String removeMessageAndComponents(Message message) throws DataAccessException{
		messageDao.deleteMessage(message.getUri(), message.getVersion());
		int index_uri_leaf = message.getUri().lastIndexOf('/') + 1;
		String qACampaignFolder = userFileQAPath + message.getCampaign();
		try {
			fileIODao.deleteFile(qACampaignFolder
								+ '/'
								+ message.getUri().substring(index_uri_leaf,
										message.getUri().length()).replace(
										".xhtml", "_" + message.getVersion() + ".php"));
		} catch (DataAccessException dae) {
			if(dae.getMessage().startsWith("Warning:")){
				System.err.println(dae.getMessage());
			}
			else{
				dae.printStackTrace();
				throw new DataAccessException(dae.getMessage());
			}
		}
		try {
			fileIODao.deleteFile(qACampaignFolder
								+ '/'
								+ message.getUri().substring(index_uri_leaf,
										message.getUri().length()).replace(
										".xhtml", "_" + message.getVersion() + ".eml"));
		} catch (DataAccessException dae) {
			if(dae.getMessage().startsWith("Warning:")){
				System.err.println(dae.getMessage());
			}
			else{
				dae.printStackTrace();
				throw new DataAccessException(dae.getMessage());
			}
		}

		return message.getUri() + " version " + message.getVersion() + " deleted";
	}
	
	protected String replaceQAEMailAvecSubscribers(String message, String subscriberEmailAddress){
		String tokenSet = "optOut.do?emailAddress=";
		int tokenSetLength = tokenSet.length();
		int startpos = message.indexOf(tokenSet)+tokenSetLength;
		int endpos = message.indexOf("\">");
		int messageLength = message.length()-1;
		String front = message.substring(0, startpos);
		String back = message.substring(endpos, messageLength);
		return front + subscriberEmailAddress + back;
	}

	private static Result validate(String email, EmailService emailService) {
		Pattern p = null;
		Matcher m = null;

		StringBuffer error = new StringBuffer("");
		Result result = emailService.new Result();
		result.bad = false;

		try {
			// CHECK @
			if (email.indexOf('@') == -1) {
				// EMAIL ADDRESS MUST CONTAIN @!!
				result.bad = true;
			} else if (email.indexOf('@') != email.lastIndexOf('@')) {
				// EMAIL ADDRESS CANNOT HAVE MORE THAN ONE @!!
				result.bad = true;
			} else if ((email.indexOf('@') == 0)
					|| (email.lastIndexOf('@') == email.length() - 1)) {
				// EMAIL ADDRESS CANNOT START OR END WITH @!!
				result.bad = true;
			}

			// CHECK PERIOD
			else if (email.indexOf('.') == -1) {
				// EMAIL ADDRESS MUST CONTAIN PERIODS '.' !!
				result.bad = true;
			} else if ((email.indexOf('.') == 0)
					|| (email.lastIndexOf('.') == email.length() - 1)) {
				// EMAIL ADDRESS CANNOT START OR END WITH A PERIOD!!
				result.bad = true;
			} else if (email.indexOf("..") != -1) {
				// EMAIL ADDRESS CANNOT CONATIN TWO CONSECUTIVE PERIODS!!
				result.bad = true;
			}

			// CHECK LOCAL PART AND DOMAIN PART SEPERATELY
			else {
				String local = email.substring(0, email.indexOf('@'));
				String domain = email.substring(email.indexOf('@') + 1);

				// CHECK LENGTH
				if (local.length() > 64)
					// LOCAL PART (THE PART BEFORE @) CANNOT BE LONGER THAN 64
					// CHARACTERS!!
					result.bad = true;
				if (domain.length() > 255) {
					// DOMAIN PART (THE PART AFTER @) CANNOT BE LONGER THAN 255
					// CHARACTERS!!
					result.bad = true;
				} else {
					String[] sections = domain.split("\\.");
					for (int i = 0; i < sections.length; i++) {
						if (sections[i].length() > 63) {
							// DOMAIN SECTIONS (SEPERATED BY PERIODS) CANNOT BE
							// LONGER THANT 63 CHARACTERS!!
							result.bad = true;
						}
					}
				}

				// CHECK LOCAL PART ILLEGAL CHARACTERS
				p = Pattern.compile("[^A-Za-z0-9!#$%&*+-/=?\\^_`{|}~]");
				m = p.matcher(local);
				if (m.find()) {
					// LOCAL PART (THE PART BEFORE @) CONTAINS ILLEGAL
					// CHARACTERS SUCH AS SPACE, COMMA OR SEMICOLON!!
					result.bad = true;
				} else {
					p = Pattern.compile("^[^A-Za-z0-9]");
					m = p.matcher(local);
					if (m.find()) {
						// LOCAL PART (THE PART BEFORE @) CANNOT START WITH A
						// NON WORD CHARACTER
						result.bad = true;
					}
				}

				// CHECK DOMAIN PART ILLEGAL CHARACTERS
				p = Pattern.compile("[^a-zA-Z0-9\\-\\.]");
				m = p.matcher(domain);
				if (m.find()) {
					// DOMAIN PART (THE PART AFTER @) CONTAINS ILLEGAL
					// CHARACTERS SUCH AS COMMA, SEMICOLON, etc
					result.bad = true;
				} else {
					p = Pattern.compile("^[^a-zA-Z0-9]");
					m = p.matcher(domain);
					if (m.find()) {
						error
								.append("DOMAIN PART (THE PART AFTER @) CANNOT START WITH A NONWORD CHARACTER");
						result.bad = true;
					} else {
						p = Pattern.compile("[a-zA-Z]");
						m = p.matcher(domain);
						if (!m.find()) {
							error
									.append("DOMAIN PART (THE PART AFTER @) CANNOT BE NUMBERS ONLY");
							result.bad = true;
						} else {
							String last = domain.substring(domain
									.lastIndexOf('.') + 1);
							m = p.matcher(last);
							if (!m.find()) {
								error
										.append("LAST SECTION OF DOMAIN PART (THE PART AFTER @) CANNOT BE NUMBERS ONLY");
								result.bad = true;
							}
						}
					}
				}
			}
		} catch (PatternSyntaxException pe) {
			// System.err.println("something wrong with the regular expression");
		}
		return result;
	}

	public static Result isEmailAddressBadOrGood(String input,
			EmailService emailService) {
		return validate(input, emailService);
	}

}
