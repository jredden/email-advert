package com.zenred.eadvert.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.annotation.Transactional;



import com.zenred.eadvert.model.domain.Message;
import com.zenred.eadvert.model.domain.MessageContext;
import com.zenred.eadvert.model.domain.MessageContextPlusRevision;
import com.zenred.eadvert.model.domain.MessageIdentifications;
import com.zenred.eadvert.model.view.AddressInterestGroupProviderResponse;
import com.zenred.eadvert.model.view.SentMessagesResponse;
import com.zenred.util.MD5Util;

public class MessageDao extends AbstractJDBCDao {
	
	private class StringRowMapperCampaignNameID implements ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("CampaignID");
		}
	}
	private class StringRowMapperMessageID implements ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("MessageID");
		}
	}
	private class StringRowMapperSendmailID implements ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("SendmailID");
		}
	}
	private class StringRowMapperGraphicsUid implements ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("GraphicUid");
		}
	}
	private class StringRowMapperContentMD5 implements ParameterizedRowMapper<String>{
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("ContentMD5");
		}		
	}
	private class StringRowMapperPosition implements ParameterizedRowMapper<String>{
		public String mapRow(ResultSet rs, int rowNum) throws SQLException{
			return rs.getString("Position");
		}
	}
	private class StringRowMapperTimeSent implements ParameterizedRowMapper<String>{
		public String mapRow(ResultSet rs, int rowNum) throws SQLException{
			return rs.getString("TimeSent");
		}
	}
	private class StringRowMapperAnswer implements ParameterizedRowMapper<String>{
		public String mapRow(ResultSet rs, int rowNum) throws SQLException{
			return rs.getString("Answer");
		}
	}
	private class StringRowMapperEmailAddressBounceID implements ParameterizedRowMapper<String>{
		public String mapRow(ResultSet rs, int rowNum) throws SQLException{
			return rs.getString("EmailAddressBounceID");
		}
	}	
	private class StringRowMapperEmailAddressID implements ParameterizedRowMapper<String>{
		public String mapRow(ResultSet rs, int rowNum) throws SQLException{
			return rs.getString("EmailAddressID");
		}
	}	

	@Transactional
	public void addReviewableMessage(Message message) {
		String askForCampaignIDSql = "SELECT CampaignID FROM Campaign WHERE CampaignName = ?";
		String campaignID = super.jdbcSetUp().getSimpleJdbcTemplate().query(
				askForCampaignIDSql, new StringRowMapperCampaignNameID(),
				message.getCampaign()).get(0);
		Map<String, Object> map = new HashMap<String, Object>();
		String tableName = "Message";
		map.put("State", "review");
		map.put("Revision", message.getVersion());
		map.put("Name", message.getUri()); // a uri is unique
		map.put("CampaignID", campaignID);
		super.jdbcInsertSetup().withTableName(tableName).usingColumns("State",
				"Revision", "Name", "CampaignID").execute(map);
		if (message.getImageUriList().size() > 0) {
			String askForMessageIDSql = "SELECT MessageID FROM Message WHERE Name = ?";
			String imageTableName = "MessageGraphic";
			String messageID = super.jdbcSetUp().getSimpleJdbcTemplate().query(
					askForMessageIDSql, new StringRowMapperMessageID(),
					message.getUri()).get(0);
			List<String> uriList = message.getImageUriList();  // where the Image URIs get picked up
			for (String imageUri : uriList) {
				Map<String, Object> image_map = new HashMap<String, Object>();
				image_map.put("GraphicUid", imageUri);
				image_map.put("MessageID", messageID);
				super.jdbcInsertSetup().withTableName(imageTableName)
						.usingColumns("GraphicUid", "MessageID").execute(
								image_map);
			}
		}
	}

	@Transactional
	public List<Message> readReviewableMessageIdenties(String campaign){
		String askForCampaignIDSql = "SELECT CampaignID FROM Campaign WHERE CampaignName = ?";
		String campaignID = super.jdbcSetUp().getSimpleJdbcTemplate().query(
				askForCampaignIDSql, new StringRowMapperCampaignNameID(),
				campaign).get(0);

		String sqlMessages = "SELECT Name, Revision FROM Message WHERE State = 'review' AND CampaignID = ?";
		List<Map <String, Object>> messageListMap = super.jdbcSetUp().getSimpleJdbcTemplate().queryForList(sqlMessages, campaignID);
		List<Message> messageList = new ArrayList<Message>();
		for (Map <String, Object> messageMap : messageListMap){
			Message message = new Message();
			message.setCampaign(campaign);
			message.setUri((String) messageMap.get("Name"));
			message.setVersion(messageMap.get("Revision").toString());
			messageList.add(message);
		}
		return messageList;
	}
	
	@Transactional
	public List<Message> readAllMessageAssociatedToCampaign(String campaign){
		String askForCampaignIDSql = "SELECT CampaignID FROM Campaign WHERE CampaignName = ?";
		String campaignID = super.jdbcSetUp().getSimpleJdbcTemplate().query(
				askForCampaignIDSql, new StringRowMapperCampaignNameID(),
				campaign).get(0);

		String sqlMessages = "SELECT Name, Revision FROM Message WHERE CampaignID = ?";
		List<Map <String, Object>> messageListMap = super.jdbcSetUp().getSimpleJdbcTemplate().queryForList(sqlMessages, campaignID);
		List<Message> messageList = new ArrayList<Message>();
		for (Map <String, Object> messageMap : messageListMap){
			Message message = new Message();
			message.setCampaign(campaign);
			message.setUri((String) messageMap.get("Name"));
			message.setVersion(messageMap.get("Revision").toString());
			messageList.add(message);
		}
		return messageList;
		
	}
	
	
	@Transactional
	public List<String> readGraphicUidsAssociatedToMessage(String messageUri){
		String graphic_sql = "SELECT GraphicUid FROM MessageGraphic WHERE MessageID = ?";
		String askForMessageIDSql = "SELECT MessageID FROM Message WHERE Name = ?";
		 List<String> messageIDList = super.jdbcSetUp().getSimpleJdbcTemplate().query(
				askForMessageIDSql, new StringRowMapperMessageID(),
				messageUri);
		 if(messageIDList.isEmpty()){return new ArrayList<String>();}  // nothing in it.
		 String messageID = messageIDList.get(0);
		return super.jdbcSetUp().getSimpleJdbcTemplate().query(graphic_sql, new StringRowMapperGraphicsUid(), messageID);		
	}
	
	@Transactional
	/**
	 * add MessageContext in anticipation for promotion to QA
	 * @param message
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public void addMessageContext(Message message)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String messageIdSql = "SELECT MessageID FROM Message WHERE Name = ? AND Revision = ?";
		String messageID = super.jdbcSetUp().getSimpleJdbcTemplate().query(
				messageIdSql, new StringRowMapperMessageID(), message.getUri(),
				message.getVersion()).get(0);
		Map<String, Object> map = new HashMap<String, Object>();
		String tableName = "MessageContext";
		map.put("MessageID", messageID);
		String contentMD5 = MD5Util.MD5(message.getMessage());
		System.out.println("contentMD5::::" + contentMD5);
		map.put("ContentMD5", contentMD5);
		super.jdbcInsertSetup().withTableName(tableName).usingColumns(
				"MessageID", "ContentMD5").execute(map);
	}
	
	public MessageContext readMessageContextAssociatedToMessage(String mD5){
		String messageContextSQL = "SELECT ContentMD5 FROM MessageContext WHERE ContentMD5 = ?";
		List<String> contentMD5List = super.jdbcSetUp().getSimpleJdbcTemplate().query(
				messageContextSQL, new StringRowMapperContentMD5(),
				mD5);
		MessageContext messageContext = new MessageContext();
		messageContext.setMc_state(MessageContext.State.qa);
		if(contentMD5List.isEmpty()){
			return  messageContext;
		}
		messageContext.setContentMD5(contentMD5List.get(0));
		return messageContext;
	}
	
	@Transactional
	public MessageContext readMessageContextAssociatedToMessage(String name, String revision){
		// System.out.println("revision:"+revision+" name:"+name);
		String messageIdSql = "SELECT MessageID FROM Message WHERE Revision = ? AND Name = ?";
		String messageID = super.jdbcSetUp().getSimpleJdbcTemplate().query(
				messageIdSql, new StringRowMapperMessageID(), revision,
				name).get(0);
		String messageContextSql = "SELECT Subject, State, ContentMD5, PlenusMD5 FROM MessageContext WHERE MessageID = ?";
		List<Map <String, Object>> messageContextListMap = super.jdbcSetUp().getSimpleJdbcTemplate().queryForList(messageContextSql, messageID);
		MessageContext messageContext = new MessageContext();
		Map<String, Object> messageContextMap = messageContextListMap.get(0);
		messageContext.setContentMD5(messageContextMap.get("ContentMD5").toString());
		messageContext.setMc_state(messageContextMap.get("State").toString());
		messageContext.setPlenusMD5(messageContextMap.get("PlenusMD5").toString());
		messageContext.setSubject(messageContextMap.get("Subject").toString());
		return messageContext;
	}
	
	@Transactional
	public void deleteMessage(String name, String revision){
		String messageIdSql = "SELECT MessageID FROM Message WHERE Revision = ? AND Name = ?";
		String p_MessageID = super.jdbcSetUp().getSimpleJdbcTemplate().query(
				messageIdSql, new StringRowMapperMessageID(), revision,
				name).get(0);
		SimpleJdbcCall procDeleteEmail = new SimpleJdbcCall(super.jdbcSetUp()
				.getJdbcTemplate()).withProcedureName("MessageDelete")
				.withoutProcedureColumnMetaDataAccess().useInParameterNames(
						"p_MessageID").declareParameters(
						new SqlParameter("p_MessageID", Types.NUMERIC));
		SqlParameterSource in = new MapSqlParameterSource().addValue(
				"p_MessageID", p_MessageID);
		procDeleteEmail.execute(in);

	}
	
	@Transactional
	public MessageContextPlusRevision readMessageGivenUri(String uri, int revision){
		System.out.println("revision:"+revision+" uri:"+uri);
		String messageIdSql = "SELECT MessageID, Revision FROM Message WHERE Name = ? AND Revision = ?";
		List<Map <String, Object>> messageListsMap = super.jdbcSetUp().getSimpleJdbcTemplate().queryForList(
				messageIdSql, uri, revision);
		int index = messageListsMap.size()-1;
		System.out.println("readMessageGivenUri.index:"+index);
		Map <String, Object> messageListMap = messageListsMap.get(index);
		String messageID = messageListMap.get("MessageID").toString();
		String messageContextSql = "SELECT Subject, State, ContentMD5, PlenusMD5 FROM MessageContext WHERE MessageID = ?";
		List<Map <String, Object>> messageContextListMap = super.jdbcSetUp().getSimpleJdbcTemplate().queryForList(messageContextSql, messageID);
		MessageContextPlusRevision messageContext = new MessageContextPlusRevision();
		Map<String, Object> messageContextMap = messageContextListMap.get(0);
		messageContext.setContentMD5(messageContextMap.get("ContentMD5").toString());
		messageContext.setMc_state(messageContextMap.get("State").toString());
		if(null != messageContextMap.get("PlenusMD5")){messageContext.setPlenusMD5(messageContextMap.get("PlenusMD5").toString());}
		if(null != messageContextMap.get("Subject")){messageContext.setSubject(messageContextMap.get("Subject").toString());}
		messageContext.setRevision(messageListMap.get("Revision").toString());
		return messageContext;		
	}
	
	public void updateMessage(Message message) {
		String sqlUpdateMessage = "UPDATE Message SET State = ? WHERE Name = ? AND Revision = ?";
		super.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.update(sqlUpdateMessage,
						new Object[] { "ready", message.getUri(),
								message.getVersion() });
	}
	
	public void updateMessageContext(MessageContext messageContext) {
		String sqlUpdateMessageContext = "UPDATE MessageContext SET State = ?, PlenusMD5 = ?  WHERE ContentMD5 = ?";
		super.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.update(sqlUpdateMessageContext,
						new Object[] { messageContext.getMc_state().toString(),
								messageContext.getPlenusMD5(),
								messageContext.getContentMD5() });
	}
	
	@Transactional
	public void updateMessageContext(MessageContextPlusRevision messageContext, String uri) {
		String sqlUpdateMessageContext = "UPDATE MessageContext SET State = ?, PlenusMD5 = ?  WHERE MessageID = (SELECT MessageID FROM Message WHERE Name = ? AND Revision = ?)";
		super.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.update(sqlUpdateMessageContext,
						new Object[] { messageContext.getMc_state().toString(),
								messageContext.getPlenusMD5(),
								uri, messageContext.getRevision()});
	}

	public List<Message> readMessagesReadyForQAToPromoteToProduction(){
		String messageReadySql = "SELECT cam.CampaignName, msg.Name, msg.Revision FROM Campaign cam " +
		"LEFT JOIN Message msg ON msg.CampaignID = cam.CampaignID " + 
		"LEFT JOIN MessageContext msc ON msg.MessageID = msc.MessageID "+
		"WHERE msg.State = 'review' AND msc.State = 'production'";
		List<Map <String, Object>> messageListMap = super.jdbcSetUp().getSimpleJdbcTemplate().queryForList(messageReadySql);
		List<Message> messageList = new ArrayList<Message>();
		for (Map <String, Object> messageMap : messageListMap){
			Message message = new Message();
			message.setCampaign(messageMap.get("CampaignName").toString());
			message.setUri((String) messageMap.get("Name"));
			message.setVersion(messageMap.get("Revision").toString());
			messageList.add(message);
		}
		return messageList;
	}
	
	public boolean isMessageAlreadyPromotedToProdOrSent(String uri, String revision){
		boolean answer;
		String sql = "SELECT msgcon.State, msg.Name, msg.MessageID FROM Message msg LEFT JOIN MessageContext msgcon ON msg.MessageID = msgcon.MessageID WHERE msg.Name =  ? AND msg.Revision = ? AND msgcon.State IN ('sent', 'production')";
		answer = !super.jdbcSetUp().getSimpleJdbcTemplate().query(sql, new StringRowMapperMessageID(), uri, revision).isEmpty();
		return answer;
	}

	public String readMessageIDUsingName(String name) {
		String sql = "SELECT MessageID FROM Message WHERE Name = ?";
		List<String> idList = super.jdbcSetUp().getSimpleJdbcTemplate()
		.query(sql, new StringRowMapperMessageID(), name);
		if(idList.isEmpty()){return null;}	// likely bogus
		return idList.get(0);
	}
	
	public void addMessageTransferStatus(String messageID, String emailAddressID, String timeSent){
		String tableName = "MessageTransferStatus";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("MessageID", messageID);
		map.put("EmailAddressID", emailAddressID);
		map.put("TimeSent", timeSent);
		map.put("TransferState", "in-transfer");
		super.jdbcInsertSetup().withTableName(tableName).usingColumns("MessageID",
				"EmailAddressID", "TimeSent", "TransferState").execute(map);
	}
	
	public int readMessageLogPosition(String logUid){
		int position = 0;
		String sql = "SELECT Position FROM MessageLogPosition WHERE LogUID = ?";
		String s_position = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql, new StringRowMapperPosition(), logUid).get(0);
		position = new Integer(s_position).intValue();
		return position;
	}
	
	public void updateMessageLogPosition(String logUid, int position){
		String updateSql = "UPDATE MessageLogPosition SET Position = ? WHERE LogUID = ?";
		super.jdbcSetUp()
		.getSimpleJdbcTemplate()
		.update(updateSql, new Object[] {position, logUid});
	}
	
	public List<MessageIdentifications> fetchTimesForSubscriber(
			String emailAddress) {
		// System.out.println("fetchTimesForSubscriber.emailAddress:"+emailAddress+":");
		List<MessageIdentifications> messageIdList = new ArrayList<MessageIdentifications>();
		String sql = "SELECT TimeSent, mts.MessageID, mts.EmailAddressID FROM MessageTransferStatus mts LEFT JOIN EmailAddress ea ON mts.EmailAddressID = ea.EmailAddressID WHERE ea.EmailAddress = ? AND ea.EmailType = 'subscriber' AND mts.TransferState = 'in-transfer'";
		List<Map<String, Object>> messageListMap = super.jdbcSetUp()
				.getSimpleJdbcTemplate().queryForList(sql, emailAddress);
		for (Map<String, Object> messageMap : messageListMap) {
			MessageIdentifications messageIdentifications = new MessageIdentifications();
			messageIdentifications.setEmailAddressID(messageMap.get(
					"EmailAddressID").toString());
			messageIdentifications.setMessageID(messageMap.get("MessageID")
					.toString());
			messageIdentifications.setTimeSent(messageMap.get("TimeSent")
					.toString());
			messageIdList.add(messageIdentifications);
		}
		return messageIdList;
	}
	
	public void updateMessageTransferStatusToRecieved(String sendmailID, String emailAddressID, String messageID){
		String updateSql = "UPDATE MessageTransferStatus SET SendmailID = ?, TransferState = 'received' WHERE EmailAddressID = ? AND MessageID = ?";
		super.jdbcSetUp()
		.getSimpleJdbcTemplate()
		.update(updateSql, new Object[] {sendmailID, emailAddressID, messageID});
	}
	
	public void messageSentAndRead(String sendmailID){
		String updateSql = "UPDATE MessageTransferStatus SET TransferState = 'opened', TimeOpened = NOW() WHERE SendmailID = ?";
		super.jdbcSetUp()
		.getSimpleJdbcTemplate()
		.update(updateSql, new Object[] {sendmailID});
	}
	@Transactional
	public void messageConnectionRefused(String sendmailID) {
		String selectSql = "SELECT DISTINCT(EmailAddressID) FROM MessageTransferStatus WHERE SendmailID = ?";
		String emailAddressID = super
				.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.query(selectSql, new StringRowMapperEmailAddressID(),
						sendmailID).get(0);
		String select2Sql = "SELECT EmailAddressBounceID FROM EmailAddressBounce WHERE EmailAddressID = ? AND BouceType = 'soft'";
		List<String> alreadyThere = super
				.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.query(select2Sql, new StringRowMapperEmailAddressBounceID(),
						emailAddressID);
		if (alreadyThere.isEmpty()) {
			String updateSql = "UPDATE MessageTransferStatus SET TransferState = 'bounced' WHERE SendmailID = ?";
			super.jdbcSetUp().getSimpleJdbcTemplate()
					.update(updateSql, new Object[] { sendmailID });
			String tableName = "EmailAddressBounce";
			Map<String, Object> bounce_map = new HashMap<String, Object>();
			bounce_map.put("EmailAddressID", emailAddressID);
			bounce_map.put("BouceType", "soft");
			super.jdbcInsertSetup().withTableName(tableName)
					.usingColumns("EmailAddressID", "BouceType")
					.execute(bounce_map);
		}
	}
	
	@Transactional
	public void messageSendFailed(String sendmailID, String message) {
		String selectSql = "SELECT DISTINCT(EmailAddressID) FROM MessageTransferStatus WHERE SendmailID = ?";
		String emailAddressID = super
				.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.query(selectSql, new StringRowMapperEmailAddressID(),
						sendmailID).get(0);
		String select2Sql = "SELECT EmailAddressBounceID FROM EmailAddressBounce WHERE EmailAddressID = ? AND BouceType = 'hard' AND Cause = ?";
		List<String> alreadyThere = super
				.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.query(select2Sql, new StringRowMapperEmailAddressBounceID(),
						emailAddressID, message);
		if (alreadyThere.isEmpty()) {
			String updateSql = "UPDATE MessageTransferStatus SET TransferState = 'bounced' WHERE SendmailID = ?";
			super.jdbcSetUp().getSimpleJdbcTemplate()
					.update(updateSql, new Object[] { sendmailID });
			String tableName = "EmailAddressBounce";
			Map<String, Object> bounce_map = new HashMap<String, Object>();
			bounce_map.put("EmailAddressID", emailAddressID);
			bounce_map.put("BouceType", "hard");
			bounce_map.put("Cause", message);
			super.jdbcInsertSetup().withTableName(tableName)
					.usingColumns("EmailAddressID", "BouceType", "Cause")
					.execute(bounce_map);
		}
	}
	@Transactional
	public List<String> fetchSendmailIDsMessagesInProgress(){
		String sql = "SELECT DISTINCT(SendmailID) FROM MessageTransferStatus WHERE SendmailID IS NOT NULL AND TransferState NOT IN ('in-transfer', 'bounced', 'opened')";
		return super.jdbcSetUp().getSimpleJdbcTemplate().query(sql, new StringRowMapperSendmailID());
	}
	
	public List<SentMessagesResponse> fetchMessagesSentInTimeFrame(
			String startDate, String endDate) {
		String sqlPSTPST = "SELECT ca.CampaignName, ea.EmailAddress, mts.TimeSent, mts.TransferState "
				+ "FROM MessageTransferStatus mts "
				+ "JOIN EmailAddress ea ON mts.EmailAddressID = ea.EmailAddressID "
				+ "JOIN EmailAddressCampaign eac ON ea.EmailAddressID = eac.EmailAddressID "
				+ "JOIN Campaign ca ON eac.CampaignID = ca.CampaignID "
				+ "WHERE STR_TO_DATE(mts.TimeSent, '%W %M %d %T PST %Y') <= STR_TO_DATE(?, '%W %M %d %T PST %Y')"
				+ "AND STR_TO_DATE(mts.TimeSent, '%W %M %d %T PST %Y') >= STR_TO_DATE(?, '%W %M %d %T PST %Y')"
				+ "ORDER BY ca.CampaignName";
		String sqlPDTPST = "SELECT ca.CampaignName, ea.EmailAddress, mts.TimeSent, mts.TransferState "
				+ "FROM MessageTransferStatus mts "
				+ "JOIN EmailAddress ea ON mts.EmailAddressID = ea.EmailAddressID "
				+ "JOIN EmailAddressCampaign eac ON ea.EmailAddressID = eac.EmailAddressID "
				+ "JOIN Campaign ca ON eac.CampaignID = ca.CampaignID "
				+ "WHERE STR_TO_DATE(mts.TimeSent, '%W %M %d %T PDT %Y') <=  STR_TO_DATE(?, '%W %M %d %T PST %Y') "
				+ "AND STR_TO_DATE(mts.TimeSent, '%W %M %d %T PST %Y') >= STR_TO_DATE(?, '%W %M %d %T PST %Y') "
				+ "ORDER BY ca.CampaignName";
		String sqlPSTPDT = "SELECT ca.CampaignName, ea.EmailAddress, mts.TimeSent, mts.TransferState "
				+ "FROM MessageTransferStatus mts "
				+ "JOIN EmailAddress ea ON mts.EmailAddressID = ea.EmailAddressID "
				+ "JOIN EmailAddressCampaign eac ON ea.EmailAddressID = eac.EmailAddressID "
				+ "JOIN Campaign ca ON eac.CampaignID = ca.CampaignID "
				+ "WHERE STR_TO_DATE(mts.TimeSent, '%W %M %d %T PST %Y') <=  STR_TO_DATE(?, '%W %M %d %T PST %Y') "
				+ "AND STR_TO_DATE(mts.TimeSent, '%W %M %d %T PDT %Y') >=  STR_TO_DATE(?, '%W %M %d %T PST %Y') "
				+ "ORDER BY ca.CampaignName";
		String sqlPDTPDT = "SELECT ca.CampaignName, ea.EmailAddress, mts.TimeSent, mts.TransferState "
				+ "FROM MessageTransferStatus mts "
				+ "JOIN EmailAddress ea ON mts.EmailAddressID = ea.EmailAddressID "
				+ "JOIN EmailAddressCampaign eac ON ea.EmailAddressID = eac.EmailAddressID "
				+ "JOIN Campaign ca ON eac.CampaignID = ca.CampaignID "
				+ "WHERE STR_TO_DATE(mts.TimeSent, '%W %M %d %T PDT %Y') <=  STR_TO_DATE(?, '%W %M %d %T PST %Y') "
				+ "AND STR_TO_DATE(mts.TimeSent, '%W %M %d %T PDT %Y') >=  STR_TO_DATE(?, '%W %M %d %T PST %Y') "
				+ "ORDER BY ca.CampaignName";
		
		boolean endpdt = endDate.contains("PDT");
		boolean startpdt = startDate.contains("PDT");
		String sql = sqlPDTPDT;
		if(!endpdt && !startpdt){
				sql = sqlPSTPST;
		}
		else{
			if(endpdt &&  !startpdt){
				sql = sqlPSTPDT;
			}
			else{
				if(!endpdt &&  startpdt){
					sql = sqlPDTPST;
				}
			}
		}
		List<Map<String, Object>> messageListMap = super.jdbcSetUp()
				.getSimpleJdbcTemplate().queryForList(sql, endDate, startDate);

		List<SentMessagesResponse> sentMessagesResponseList = new ArrayList<SentMessagesResponse>();
		String currentCampaign = "";
		for (Map<String, Object> messageMap : messageListMap) {
			SentMessagesResponse sentMessagesResponse = new SentMessagesResponse();
			String campaign = messageMap.get("CampaignName").toString();
			if (!currentCampaign.equals(campaign)) {
				sentMessagesResponse.setCampaign(campaign);
				currentCampaign = campaign;
			}
			sentMessagesResponse.setEmailAddress(messageMap.get("EmailAddress")
					.toString());
			sentMessagesResponse.setTimeSent(messageMap.get("TimeSent")
					.toString());
			sentMessagesResponse.setTransferState(messageMap.get(
					"TransferState").toString());
			sentMessagesResponseList.add(sentMessagesResponse);
		}
		return sentMessagesResponseList;
	}
	
	public List<SentMessagesResponse> fetchMessagesSentInTimeFrame(
			String startDate, String endDate, String userName) {
		String sqlPSTPST = "SELECT ca.CampaignName, ea.EmailAddress, mts.TimeSent, mts.TransferState "
				+ "FROM MessageTransferStatus mts "
				+ "JOIN EmailAddress ea ON mts.EmailAddressID = ea.EmailAddressID "
				+ "JOIN EmailAddressCampaign eac ON ea.EmailAddressID = eac.EmailAddressID "
				+ "JOIN Campaign ca ON eac.CampaignID = ca.CampaignID "
				+ "JOIN Oem oe ON oe.OemID = ca.OemID "
				+ "JOIN UserOem uo ON uo.OemID = oe.OemID "
				+ "JOIN User us ON uo.UserID = us.UserID "
				+ "WHERE STR_TO_DATE(mts.TimeSent, '%W %M %d %T PST %Y') <= STR_TO_DATE(?, '%W %M %d %T PST %Y')"
				+ "AND STR_TO_DATE(mts.TimeSent, '%W %M %d %T PST %Y') >= STR_TO_DATE(?, '%W %M %d %T PST %Y') "
				+ "AND us.UserName = ?" + "ORDER BY ca.CampaignName";
		String sqlPDTPST = "SELECT ca.CampaignName, ea.EmailAddress, mts.TimeSent, mts.TransferState "
				+ "FROM MessageTransferStatus mts "
				+ "JOIN EmailAddress ea ON mts.EmailAddressID = ea.EmailAddressID "
				+ "JOIN EmailAddressCampaign eac ON ea.EmailAddressID = eac.EmailAddressID "
				+ "JOIN Campaign ca ON eac.CampaignID = ca.CampaignID "
				+ "JOIN Oem oe ON oe.OemID = ca.OemID "
				+ "JOIN UserOem uo ON uo.OemID = oe.OemID "
				+ "JOIN User us ON uo.UserID = us.UserID "
				+ "WHERE STR_TO_DATE(mts.TimeSent, '%W %M %d %T PDT %Y') <=  STR_TO_DATE(?, '%W %M %d %T PST %Y') "
				+ "AND STR_TO_DATE(mts.TimeSent, '%W %M %d %T PST %Y') >= STR_TO_DATE(?, '%W %M %d %T PST %Y') "
				+ "AND us.UserName = ?" + "ORDER BY ca.CampaignName";
		String sqlPSTPDT = "SELECT ca.CampaignName, ea.EmailAddress, mts.TimeSent, mts.TransferState "
				+ "FROM MessageTransferStatus mts "
				+ "JOIN EmailAddress ea ON mts.EmailAddressID = ea.EmailAddressID "
				+ "JOIN EmailAddressCampaign eac ON ea.EmailAddressID = eac.EmailAddressID "
				+ "JOIN Campaign ca ON eac.CampaignID = ca.CampaignID "
				+ "JOIN Oem oe ON oe.OemID = ca.OemID "
				+ "JOIN UserOem uo ON uo.OemID = oe.OemID "
				+ "JOIN User us ON uo.UserID = us.UserID "
				+ "WHERE STR_TO_DATE(mts.TimeSent, '%W %M %d %T PST %Y') <=  STR_TO_DATE(?, '%W %M %d %T PST %Y') "
				+ "AND STR_TO_DATE(mts.TimeSent, '%W %M %d %T PDT %Y') >=  STR_TO_DATE(?, '%W %M %d %T PST %Y') "
				+ "AND us.UserName = ?" + "ORDER BY ca.CampaignName";
		String sqlPDTPDT = "SELECT ca.CampaignName, ea.EmailAddress, mts.TimeSent, mts.TransferState "
				+ "FROM MessageTransferStatus mts "
				+ "JOIN EmailAddress ea ON mts.EmailAddressID = ea.EmailAddressID "
				+ "JOIN EmailAddressCampaign eac ON ea.EmailAddressID = eac.EmailAddressID "
				+ "JOIN Campaign ca ON eac.CampaignID = ca.CampaignID "
				+ "JOIN Oem oe ON oe.OemID = ca.OemID "
				+ "JOIN UserOem uo ON uo.OemID = oe.OemID "
				+ "JOIN User us ON uo.UserID = us.UserID "
				+ "WHERE STR_TO_DATE(mts.TimeSent, '%W %M %d %T PDT %Y') <=  STR_TO_DATE(?, '%W %M %d %T PST %Y') "
				+ "AND STR_TO_DATE(mts.TimeSent, '%W %M %d %T PDT %Y') >=  STR_TO_DATE(?, '%W %M %d %T PST %Y') "
				+ "AND us.UserName = ?" + "ORDER BY ca.CampaignName";
		
		boolean endpdt = endDate.contains("PDT");
		boolean startpdt = startDate.contains("PDT");
		String sql = sqlPDTPDT;
		if(!endpdt && !startpdt){
				sql = sqlPSTPST;
		}
		else{
			if(endpdt &&  !startpdt){
				sql = sqlPSTPDT;
			}
			else{
				if(!endpdt &&  startpdt){
					sql = sqlPDTPST;
				}
			}
		}
		List<Map<String, Object>> messageListMap = super.jdbcSetUp()
				.getSimpleJdbcTemplate().queryForList(sql, endDate, startDate, userName);

		List<SentMessagesResponse> sentMessagesResponseList = new ArrayList<SentMessagesResponse>();
		String currentCampaign = "";
		for (Map<String, Object> messageMap : messageListMap) {
			SentMessagesResponse sentMessagesResponse = new SentMessagesResponse();
			String campaign = messageMap.get("CampaignName").toString();
			if (!currentCampaign.equals(campaign)) {
				sentMessagesResponse.setCampaign(campaign);
				currentCampaign = campaign;
			}
			sentMessagesResponse.setEmailAddress(messageMap.get("EmailAddress")
					.toString());
			sentMessagesResponse.setTimeSent(messageMap.get("TimeSent")
					.toString());
			sentMessagesResponse.setTransferState(messageMap.get(
					"TransferState").toString());
			sentMessagesResponseList.add(sentMessagesResponse);
		}
		return sentMessagesResponseList;
	}
	
	public List<AddressInterestGroupProviderResponse> fetchEmailAddresses(){
		String sql = "SELECT ea.EmailAddress, ig.InterestGroupName, oe.OemName "
				+ "FROM EmailAddress ea "
				+ "JOIN EmailAddressInterestGroup eai ON ea.EmailAddressID = eai.EmailAddressID "
				+ "JOIN InterestGroup ig ON eai.InterestGroupID = ig.InterestGroupID "
				+ "JOIN OemInterestGroup oig ON oig.InterestGroupID = ig.InterestGroupID "
				+ "JOIN Oem oe ON oe.OemID = oig.OemID "
				+ "ORDER BY oe.OemName, ig.InterestGroupName, ea.EmailAddress";
		List<Map<String, Object>> messageListMap = super.jdbcSetUp()
		.getSimpleJdbcTemplate().queryForList(sql);	
		List<AddressInterestGroupProviderResponse> addressInterestGroupProviderList = new ArrayList<AddressInterestGroupProviderResponse>();
		for (Map<String, Object> messageMap : messageListMap) {
			AddressInterestGroupProviderResponse addressInterestGroupProvider = new AddressInterestGroupProviderResponse();
			addressInterestGroupProvider.setEmailAddress(messageMap.get("EmailAddress").toString());
			addressInterestGroupProvider.setInterestGroup(messageMap.get("InterestGroupName").toString());
			addressInterestGroupProvider.setProvider(messageMap.get("OemName").toString());
			addressInterestGroupProviderList.add(addressInterestGroupProvider);
		}
		return addressInterestGroupProviderList;
	}
	public List<AddressInterestGroupProviderResponse> fetchEmailAddresses(String providerName){
		String sql = "SELECT ea.EmailAddress, ig.InterestGroupName "
				+ "FROM EmailAddress ea "
				+ "JOIN EmailAddressInterestGroup eai ON ea.EmailAddressID = eai.EmailAddressID "
				+ "JOIN InterestGroup ig ON eai.InterestGroupID = ig.InterestGroupID "
				+ "JOIN OemInterestGroup oig ON oig.InterestGroupID = ig.InterestGroupID "
				+ "JOIN Oem oe ON oe.OemID = oig.OemID "
				+ "WHERE oe.OemName = ? "
				+ "ORDER BY ig.InterestGroupName, ea.EmailAddress "
				;
		List<Map<String, Object>> messageListMap = super.jdbcSetUp()
		.getSimpleJdbcTemplate().queryForList(sql, providerName);	
		List<AddressInterestGroupProviderResponse> addressInterestGroupProviderList = new ArrayList<AddressInterestGroupProviderResponse>();
		for (Map<String, Object> messageMap : messageListMap) {
			AddressInterestGroupProviderResponse addressInterestGroupProvider = new AddressInterestGroupProviderResponse();
			addressInterestGroupProvider.setEmailAddress(messageMap.get("EmailAddress").toString());
			addressInterestGroupProvider.setInterestGroup(messageMap.get("InterestGroupName").toString());
			addressInterestGroupProvider.setProvider(providerName);
			addressInterestGroupProviderList.add(addressInterestGroupProvider);
		}
		return addressInterestGroupProviderList;
	}
	// these date comparisons do not work right now ...
	public boolean sendTimeGreaterOrEqualLogTime(String sendTime, String logTime){
		String sql = "SELECT STR_TO_DATE(?, '%W %M %d %T PST %Y') >= STR_TO_DATE(?, '%W, %d %M %Y %d') AS Answer";
		return super.jdbcSetUp().getSimpleJdbcTemplate().query(sql, new StringRowMapperAnswer(), sendTime, logTime).get(0).equals("1");
	}
	// these date comparisons do not work right now ...
	public boolean sendTimeLessOrEqualLogTime(String sendTime, String logTime){
		String sql = "SELECT STR_TO_DATE(?, '%W %M %d %T PST %Y') <= STR_TO_DATE(?, '%W, %d %M %Y %d') AS Answer";
		return super.jdbcSetUp().getSimpleJdbcTemplate().query(sql, new StringRowMapperAnswer(), sendTime, logTime).get(0).equals("1");
	}
}
