package com.zenred.eadvert.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zenred.eadvert.dao.FileIODao;
import com.zenred.eadvert.dao.MessageDao;
import com.zenred.eadvert.exception.DataAccessException;
import com.zenred.eadvert.exception.MessageServiceException;
import com.zenred.eadvert.model.domain.EmailAddressMessage;
import com.zenred.eadvert.model.domain.MessageIdentifications;
import com.zenred.eadvert.model.view.AddressInterestGroupProviderResponse;
import com.zenred.eadvert.model.view.SentMessagesResponse;
import com.zenred.util.DateUtil;

public class ReportService {
	
	private static String START_SEGMENT_TOKEN = "ESMTP Sendmail";
	private static String START_SEGMENT_TOKEN2 = ";";
	private static String END_SEGMENT_TOKEN = "-";
	private static String START_ADDRESS_TOKEN = "MAIL From";
	private static String END_ADDRESS_TOKEN = "SIZE=";
	private static String START_SENDMAIL_ID_TOKEN = "Sent (";
	private static String END_SENDMAIL_ID_TOKEN = " Message";
	
	private static String SENDMAIL_STAT = "stat=";
	private static String SENDMAIL_CONNECTION_REFUSED = "Connection refused";
	private static String SENDMAIL_SENDER_NOTIFY = "sender notify:";
	private static String SENDMAIL_NOSEND = "Cannot send message";
	private static String SENDMAIL_SEND = "Sent";
	
	private MessageDao messageDao;
	private FileIODao fileIODao;
	
	private String mailLog;
	private String sendmailLog;
	private String logStartConnectionToken;
	private String logEndConnectionToken;
	
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}
	public void setFileIODao(FileIODao fileIODao) {
		this.fileIODao = fileIODao;
	}
	public void setMailLog(String mailLog) {
		this.mailLog = mailLog;
	}
	public void setSendmailLog(String sendmailLog) {
		this.sendmailLog = sendmailLog;
	}
	
	public void setLogStartConnectionToken(String logStartConnectionToken) {
		this.logStartConnectionToken = logStartConnectionToken;
	}
	public void setLogEndConnectionToken(String logEndConnectionToken) {
		this.logEndConnectionToken = logEndConnectionToken;
	}
	
	public List<SentMessagesResponse> messagesSentInTimeFrame(String startDateRaw, String endDateRaw){
		String startDate = DateUtil.getInternalFormat(startDateRaw);
		String endDate = DateUtil.getInternalFormat(endDateRaw);
		return messageDao.fetchMessagesSentInTimeFrame(startDate, endDate);
	}

	public List<SentMessagesResponse> messagesSentInTimeFrame(
			String startDateRaw, String endDateRaw, String userName) {
		String startDate = DateUtil.getInternalFormat(startDateRaw);
		String endDate = DateUtil.getInternalFormat(endDateRaw);
		return messageDao.fetchMessagesSentInTimeFrame(startDate, endDate, userName);
	}
	
	public List<AddressInterestGroupProviderResponse> allEmailAddressesWithInterestGroupAndProvider(){
		return messageDao.fetchEmailAddresses();
	}
	public List<AddressInterestGroupProviderResponse> providerEmailAddressesWithInterestGroup(String provider){
		return messageDao.fetchEmailAddresses(provider);
	}
	
	private class BeforeAndAfter{
		public boolean before;
		public boolean after;
	}
	
	public void buildReportBackEnd(List<EmailAddressMessage> messageList) throws MessageServiceException{
		List<String> logSegments = new ArrayList<String>();
		
		int sendmailLogPosition = messageDao.readMessageLogPosition(sendmailLog);
		StringBuffer sendmailLogAsStringBuffer = null;
		try {
			sendmailLogAsStringBuffer = fileIODao.readFileUsingURI(sendmailLog, sendmailLogPosition);
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
		
		int currentPos = 0;
		int previousPos = 0;
		int sendmailLogAsStringBufferLength = sendmailLogAsStringBuffer.length();
		while(previousPos != -1 ){
			previousPos = sendmailLogAsStringBuffer.indexOf(logStartConnectionToken, previousPos);
			currentPos = sendmailLogAsStringBuffer.indexOf(logEndConnectionToken, previousPos);
			System.out.println("previousPos:" + previousPos + " currentPos:" + currentPos);
			if(previousPos == -1){break;}
			if(currentPos == -1){
				currentPos = sendmailLogAsStringBufferLength;
			}
			String logSegment = sendmailLogAsStringBuffer.substring(previousPos, currentPos);
			logSegments.add(logSegment);
			previousPos = currentPos;
		}
		
		currentPos = 0;
		previousPos = 0;
		for (String logsegment : logSegments) {

			previousPos = logsegment.indexOf(START_SEGMENT_TOKEN);
			previousPos += START_SEGMENT_TOKEN.length();
			currentPos = logsegment.indexOf(START_SEGMENT_TOKEN2, previousPos);

			String token = logsegment.substring(previousPos, currentPos);
			previousPos = currentPos + 1;

			currentPos = logsegment.indexOf(END_SEGMENT_TOKEN, previousPos);
			String tokenDate = logsegment.substring(previousPos, currentPos).trim();

			previousPos = currentPos + 1;
			currentPos = logsegment.indexOf(START_ADDRESS_TOKEN, previousPos);
			previousPos = currentPos + START_ADDRESS_TOKEN.length();
			currentPos = logsegment.indexOf(END_ADDRESS_TOKEN, previousPos);
			String tokenAddress = logsegment.substring(previousPos+1, currentPos)
					.replace('<', ' ').replace('>', ' ').trim();
			
			previousPos = currentPos + 1;
			currentPos = logsegment.indexOf(START_SENDMAIL_ID_TOKEN, previousPos);
			previousPos = currentPos + START_SENDMAIL_ID_TOKEN.length();
			currentPos = logsegment.indexOf(END_SENDMAIL_ID_TOKEN, previousPos);
			String tokenSendMailID = logsegment.substring(previousPos, currentPos);

			System.out.println("========\n" + logsegment + "========\n"
					+ "tokenDate:" + tokenDate + ":\n" + "tokenAddress:"
					+ tokenAddress + "\n" + "tokenSendMailID:" +tokenSendMailID +":\n");

			List<MessageIdentifications> sendTimeList = messageDao
					.fetchTimesForSubscriber(tokenAddress);

			for (MessageIdentifications messageIdentifications : sendTimeList) {
				String sendTime = messageIdentifications.getTimeSent();
				BeforeAndAfter l_beforeAndAfter = beforeAndAfter(sendTime,
						tokenDate);
				if (l_beforeAndAfter.after) {
					System.out.println("Update xferstatus 1:" + tokenSendMailID + ":"+messageIdentifications);
					messageDao.updateMessageTransferStatusToRecieved(
							tokenSendMailID,
							messageIdentifications.getEmailAddressID(),
							messageIdentifications.getMessageID());
				}
			}

			for (EmailAddressMessage emailAddressMessage : messageList) {
				String sendTime = emailAddressMessage.getTimeSent();
				if (tokenAddress.equals(emailAddressMessage.getEmailAddress())) {
					BeforeAndAfter l_beforeAndAfter = beforeAndAfter(sendTime,
							tokenDate);
					if (l_beforeAndAfter.after) {
						System.out.println("Update xferstatus 2:" + tokenSendMailID + ":"+emailAddressMessage);

						messageDao.updateMessageTransferStatusToRecieved(
								tokenSendMailID,
								emailAddressMessage.getEmailAddressID()+"",
								emailAddressMessage.getMessageID());
					}
				}
			}
		}		
		
		
		int mailLogPosition = messageDao.readMessageLogPosition(mailLog);
		StringBuffer mailLogAsStringBuffer = null;
		try {
			mailLogAsStringBuffer = fileIODao.readFileUsingURI(mailLog, mailLogPosition);
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
		
		
		List<String> sendmailIDList = messageDao.fetchSendmailIDsMessagesInProgress();
		for(String sendmailID : sendmailIDList){
			System.out.println("sendmailID:"+sendmailID);
			boolean wrapped = false;
			int presendmailIDPos = 0;
			int cursendmailIDPos = mailLogAsStringBuffer.indexOf(sendmailID, presendmailIDPos);
			while (cursendmailIDPos != -1){
				wrapped = true;
				presendmailIDPos = cursendmailIDPos+sendmailID.length();
				
				String chunk = "";
				cursendmailIDPos = mailLogAsStringBuffer.indexOf(sendmailID, presendmailIDPos);
				if(-1 == cursendmailIDPos){
					chunk = mailLogAsStringBuffer.substring(presendmailIDPos, mailLogAsStringBuffer.length()-1);
				}
				else{
					chunk = mailLogAsStringBuffer.substring(presendmailIDPos, cursendmailIDPos);
				}
//				System.out.println("sendmailID:::"+sendmailID+"chunk:::"+chunk+":::"+presendmailIDPos+":"+cursendmailIDPos);
				parseChunk(chunk, sendmailID);
			}
		}
		
	}
	
	private void parseChunk(String chunk, String sendmailId) {
		boolean continueOrNot = true;
		int chunkCurrentPos = 0;
		while (continueOrNot) {
			int targetPos = chunk.indexOf(SENDMAIL_STAT, chunkCurrentPos);
			if (-1 != targetPos) {
				chunkCurrentPos += SENDMAIL_STAT.length();
				targetPos = chunk.indexOf(SENDMAIL_SEND, chunkCurrentPos);
				if (targetPos != -1) {
					System.out.println("%%%%% "+sendmailId + " sent and read");
					messageDao.messageSentAndRead(sendmailId);
					chunkCurrentPos += SENDMAIL_SEND.length();
				}
				targetPos = chunk.indexOf(SENDMAIL_CONNECTION_REFUSED, chunkCurrentPos);
				if (targetPos != -1) {
					System.out.println("%%%%% "+sendmailId + " connection refused");
					messageDao.messageConnectionRefused(sendmailId);
					chunkCurrentPos += SENDMAIL_CONNECTION_REFUSED.length();
				}
			}
			targetPos = chunk.indexOf(SENDMAIL_SENDER_NOTIFY, chunkCurrentPos);
			if (-1 != targetPos) {
				chunkCurrentPos += SENDMAIL_SENDER_NOTIFY.length();
				targetPos = chunk.indexOf(SENDMAIL_NOSEND, chunkCurrentPos);
				if(targetPos != -1){
					chunkCurrentPos = targetPos;
					chunkCurrentPos += SENDMAIL_NOSEND.length(); 
					String _s = chunk.substring(chunkCurrentPos, chunk.length()-1);
					String [] messageRaw = _s.split(" ");
					String message = messageRaw[0] + " " + messageRaw[1] + " " + messageRaw[2] + " " + messageRaw[3];
					//System.out.println("_s:"+_s);
					System.out.println("%%%%% "+sendmailId + " send failed " + message + "%%%%%");
					messageDao.messageSendFailed(sendmailId, message);
					continueOrNot = false;
					break;
				}
				else{
					messageDao.messageSendFailed(sendmailId, " send failing");
					System.out.println("%%%%% "+sendmailId + " send failing");
				}
			}
			if(-1 == targetPos){continueOrNot = false;}
		}
	}

	private BeforeAndAfter beforeAndAfter(String sendTime, String tokenDate) throws MessageServiceException{
		BeforeAndAfter beforeAndAfter = new BeforeAndAfter();
		beforeAndAfter.after = false;
		try {
			beforeAndAfter.after = sendTimeGreaterOrEqualLogTime(
					sendTime, tokenDate);
		} catch (ParseException pee) {
			pee.printStackTrace();
			throw new MessageServiceException(pee.getMessage());
		}
		beforeAndAfter.before = false;
		try {
			beforeAndAfter.before = sendTimeLessOrEqualLogTime(
					sendTime, tokenDate);
		} catch (ParseException pee) {
			pee.printStackTrace();
			throw new MessageServiceException(pee.getMessage());
		}
		System.out.println(" sendTime:" + sendTime + " tokenDate:" + tokenDate
				+ " after:" + beforeAndAfter.after + " before:" + beforeAndAfter.before);

		return beforeAndAfter;
		
	}
	
	// sendTime:Mon Nov 22 10:00:03 PST 2010 logDate: Mon, 22 Nov 2010 10:00:04
	private boolean sendTimeGreaterOrEqualLogTime(String sendTime, String logDate) throws ParseException{
		
		boolean answer;
		DateFormat dateFormatSend = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
		DateFormat dateFormatLog = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
		
		Date send = dateFormatSend.parse(sendTime);
		Date log = dateFormatLog.parse(logDate);
		answer = send.after(log);
		
		return answer;
	}
	private boolean sendTimeLessOrEqualLogTime(String sendTime, String logDate) throws ParseException{
		
		boolean answer;
		DateFormat dateFormatSend = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
		DateFormat dateFormatLog = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
		
		Date send = dateFormatSend.parse(sendTime);
		Date log = dateFormatLog.parse(logDate);
		answer = send.before(log);
		
		return answer;
	}

}
