package com.zenred.eadvert.dao;

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

import com.zenred.eadvert.model.domain.EMail;
import com.zenred.eadvert.model.domain.EmailPlusContext;
import com.zenred.eadvert.service.EmailService;

public class EmailDao extends AbstractJDBCDao {

	private static String tableName = "EmailAddress";
	private static String campaignTableName = "EmailAddressCampaign";
	private static String oemTableName = "EmailAddressOem";
	private static String interestGroupTable = "InterestGroup";
	private static String emailAddressInterestGroupTable = "EmailAddressInterestGroup";

	private SimpleJdbcCall procDeleteEmail;

	private class StringRowMapperEmailAddressID implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("EmailAddressID");
		}
	}

	private class StringRowMapperEmailAddress implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("EmailAddress");
		}
	}

	private class StringRowMapperCampaignID implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("CampaignID");
		}
	}

	private class StringRowMapperOemID implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("OemID");
		}
	}

	private class StringRowMapperEventNameID implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("DateEventID");
		}
	}

	private class StringRowMapperInterestGroupID implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("InterestGroupID");
		}
	}

	@Transactional
	public void addEmailCampaignAssociations(EMail email, String campaignName) {
		String sqlEmailAddressID = "SELECT EmailAddressID FROM EmailAddress WHERE EmailAddress = ? AND EmailType = ?";
		String p_EMailAddressID = super.jdbcSetUp().getSimpleJdbcTemplate()
				.query(sqlEmailAddressID, new StringRowMapperEmailAddressID(),
						email.getAddress(), email.getType().toString()).get(0);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("EmailAddressID", p_EMailAddressID);
		String campaignID = fetchCampaignID(campaignName);
		map.put("CampaignID", campaignID);
		String sqlContact = "SELECT DateEventID FROM Contact WHERE EMailAddressID = ?";
		List<String> p_EventNameID = super.jdbcSetUp().getSimpleJdbcTemplate()
				.query(sqlContact, new StringRowMapperEventNameID(),
						p_EMailAddressID);

		if (p_EventNameID.size() > 0) {
			// String updateDateEventSql =
			// "UPDATE DateEvent SET CampaignID = ? WHERE DateEventID = ?";
			// super.jdbcSetUp().getSimpleJdbcTemplate().update(updateDateEventSql,
			// campaignID, p_EventNameID.get(0));
		}
		super.jdbcInsertSetup().withTableName(campaignTableName).usingColumns(
				"EmailAddressID", "CampaignID").execute(map);
	}

	@Transactional
	public void addEmailOemAssociations(EMail email, String oemName) {
		String sqlEmailAddressID = "SELECT EmailAddressID FROM EmailAddress WHERE EmailAddress = ?";
		String p_EMailAddressID = super.jdbcSetUp().getSimpleJdbcTemplate()
				.query(sqlEmailAddressID, new StringRowMapperEmailAddressID(),
						email.getAddress()).get(0);
		String sqlOemID = "SELECT OemID FROM Oem WHERE OemName = ?";
		String oemId = super.jdbcSetUp().getSimpleJdbcTemplate().query(
				sqlOemID, new StringRowMapperOemID(), oemName).get(0);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("EmailAddressID", p_EMailAddressID);
		map.put("OemID", oemId);
		super.jdbcInsertSetup().withTableName(oemTableName).usingColumns(
				"EmailAddressID", "oemID").execute(map);
	}

	@Transactional
	public void addEmail(EMail email) {
		String sql = "SELECT InterestGroupID FROM InterestGroup WHERE InterestGroupName = ?";
		String interestGroupID = super.jdbcSetUp().getSimpleJdbcTemplate()
				.query(sql, new StringRowMapperInterestGroupID(), "generic")
				.get(0);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("EmailAddress", email.getAddress());
		map.put("EmailType", email.getType().toString());
		super.jdbcInsertSetup().withTableName(tableName)
				.usingColumns("EmailAddress", "EmailType").execute(map);
		String sqlEmailAddressID = "SELECT EmailAddressID FROM EmailAddress WHERE EmailAddress = ?";
		String p_EMailAddressID = super
				.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.query(sqlEmailAddressID, new StringRowMapperEmailAddressID(),
						email.getAddress()).get(0);
		map = new HashMap<String, Object>();
		map.put("EmailAddressID", p_EMailAddressID);
		map.put("InterestGroupID", interestGroupID);
		super.jdbcInsertSetup().withTableName(emailAddressInterestGroupTable)
				.usingColumns("EmailAddressID", "InterestGroupID").execute(map);
	}

	public int getNoEmailAdd() {

		int emailAddress = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForInt("SELECT COUNT(EmailAddress)FROM EmailAddress");
		return emailAddress;
	}

	@Transactional
	public void deleteEmail(EMail email) {
		String sql = "SELECT EmailAddressID FROM EmailAddress WHERE EmailAddress = ?";
		String p_EMailAddressID = super.jdbcSetUp().getSimpleJdbcTemplate()
				.query(sql, new StringRowMapperEmailAddressID(),
						email.getAddress()).get(0);

		procDeleteEmail = new SimpleJdbcCall(super.jdbcSetUp()
				.getJdbcTemplate()).withProcedureName("EmailDelete")
				.withoutProcedureColumnMetaDataAccess().useInParameterNames(
						"p_EMailAddressID").declareParameters(
						new SqlParameter("p_EMailAddressID", Types.NUMERIC));
		SqlParameterSource in = new MapSqlParameterSource().addValue(
				"p_EMailAddressID", p_EMailAddressID);
		procDeleteEmail.execute(in);
	}
	
	public List<EMail> fetchCampaignsEmails(String campaignName) {
		List<EMail> emailList = new ArrayList<EMail>();

		String sqlEmail = "SELECT ea.EMailAddress, ea.EmailType "
				+ "FROM EmailAddress ea "
				+ "INNER JOIN EmailAddressCampaign eac "
				+ "ON ea.EmailAddressID = eac.EmailAddressID "
				+ "JOIN  EmailAddressInterestGroup eaig "
				+ "ON eaig.EmailAddressID = ea.EmailAddressID "
				+ "WHERE eaig.EmailState = 'active' "
				+ "AND eac.CampaignID = ?";
		List<Map<String, Object>> emails = super.jdbcSetUp()
				.getSimpleJdbcTemplate().queryForList(sqlEmail,
						fetchCampaignID(campaignName));
		for (Map<String, Object> map : emails) {
			EMail email = new EMail();
			email.setAddress((String) map.get("EmailAddress"));
			email
					.setType(email.getType(map.get("EmailType").toString()
							.trim()));
			emailList.add(email);
		}
		return emailList;
	}

	// or to an interest group
	public List<EmailPlusContext> fetchEmailsNotAssociatedToCampaign() {
		List<EmailPlusContext> emailList = new ArrayList<EmailPlusContext>();
		String sql = "SELECT ea.EmailAddress, ea.EmailType, da.Name, da.EventName FROM "
				+ " EmailAddress ea LEFT JOIN Contact co ON ea.EmailAddressID = co.EmailAddressID "
				+ " LEFT JOIN DateEvent da ON co.DateEventID = da.DateEventID "
				+ " JOIN EmailAddressInterestGroup eaig ON ea.EmailAddressID = eaig.EmailAddressID "
				+ " WHERE eaig.EmailState = 'active' AND ea.EmailAddressID NOT IN (SELECT EmailAddressID "
				+ " FROM EmailAddressCampaign "
				+ "	UNION SELECT EmailAddressID FROM EmailAddressInterestGroup)";
		List<Map<String, Object>> emails = super.jdbcSetUp()
				.getSimpleJdbcTemplate().queryForList(sql);
		for (Map<String, Object> map : emails) {
			EmailPlusContext emailPlusContext = new EmailPlusContext();
			emailPlusContext.setAddress((String) map.get("EmailAddress"));
			emailPlusContext.setType(emailPlusContext.getType(map.get(
					"EmailType").toString().trim()));
			emailPlusContext.setEventName(EmailService.nullToNA((String) map
					.get("EventName")));
			emailPlusContext.setName(EmailService.nullToNA((String) map
					.get("Name")));
			emailList.add(emailPlusContext);
		}
		return emailList;
	}

	@Transactional
	public void setEmailAddressInactive(String emailAddress, String role) {
		String sqlEmailAddressID = "SELECT EmailAddressID FROM EmailAddress WHERE EmailAddress = ? AND Type ?";
		String emailAddressID = super.jdbcSetUp()
				.getSimpleJdbcTemplate().query(sqlEmailAddressID,
						new StringRowMapperEmailAddressID(), emailAddress, role).get(0);

		String sqlUpdateEmailAddress = "UPDATE EmailAddressInterestGroup SET EmailState = 'inactive' WHERE EmailAddressID = ?";
		super.jdbcSetUp().getSimpleJdbcTemplate().update(sqlUpdateEmailAddress,
				new Object[] { emailAddressID });
	}

	@Transactional
	public void setEmailAddressActive(String emailAddress, String role) {
		String sqlEmailAddressID = "SELECT EmailAddressID FROM EmailAddress WHERE EmailAddress = ? AND Type ?";
		String emailAddressID = super.jdbcSetUp()
				.getSimpleJdbcTemplate().query(sqlEmailAddressID,
						new StringRowMapperEmailAddressID(), emailAddress, role).get(0);
		String sqlUpdateEmailAddress = "UPDATE EmailAddressInterestGroup SET EmailState = 'active' WHERE EmailAddressID = ?";
		super.jdbcSetUp().getSimpleJdbcTemplate().update(sqlUpdateEmailAddress,
				new Object[] { emailAddressID });
	}

	public String fetchCampaignsProviderAddress(String campaignName) {
		String sql = "SELECT em.EmailAddress FROM Campaign AS ca LEFT JOIN EmailAddressOem eao ON ca.OemID = eao.OemID LEFT JOIN EmailAddress em ON em.EmailAddressID = eao.EmailAddressID WHERE ca.CampaignName = ?";
		return super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperEmailAddress(), campaignName).get(0);
	}

	public boolean doesEmailAddressExist(String emailAddress, String role) {
		boolean answer = false;
		String sqlEmailAddressID = "SELECT EmailAddressID FROM EmailAddress WHERE EmailAddress = ? AND Type ?";
		List<String> shouldOnlyBeOne = super.jdbcSetUp()
				.getSimpleJdbcTemplate().query(sqlEmailAddressID,
						new StringRowMapperEmailAddressID(), emailAddress, role);
		if (shouldOnlyBeOne.size() > 0) {
			answer = true;
		}
		return answer;
	}
	public boolean doesEmailAddressExistForUserType(String emailAddress, String userType, String campaignName) {
		boolean answer = false;
		String sqlEmailAddressID = "SELECT ea.EmailAddressID FROM EmailAddress ea JOIN EmailAddressCampaign eac ON  ea.EmailAddressID = eac.EmailAddressID JOIN Campaign ca ON eac.CampaignID = ca.CampaignID WHERE EmailAddress = ? AND EmailType = ? AND ca.CampaignName = ?";
		List<String> shouldOnlyBeOne = super.jdbcSetUp()
				.getSimpleJdbcTemplate().query(sqlEmailAddressID,
						new StringRowMapperEmailAddressID(), emailAddress, userType, campaignName);
		if (shouldOnlyBeOne.size() > 0) {
			answer = true;
		}
		return answer;
	}
	public boolean doesEmailAddressExistForUserType(String emailAddress, String userType) {
		boolean answer = false;
		String sqlEmailAddressID = "SELECT EmailAddressID FROM EmailAddress WHERE EmailAddress = ? AND EmailType = ?";
		List<String> shouldOnlyBeOne = super.jdbcSetUp()
				.getSimpleJdbcTemplate().query(sqlEmailAddressID,
						new StringRowMapperEmailAddressID(), emailAddress, userType);
		if (shouldOnlyBeOne.size() > 0) {
			answer = true;
		}
		return answer;
	}

	private String fetchCampaignID(String campaignName) {
		String sqlCampaignID = "SELECT CampaignID FROM Campaign WHERE CampaignName = ?";
		String campaignId = super.jdbcSetUp().getSimpleJdbcTemplate().query(
				sqlCampaignID, new StringRowMapperCampaignID(), campaignName)
				.get(0);
		return campaignId;
	}

	public List<String> fetchEmailsOfTypeAssociatedToCampaign(
			String campaignName, String type) {
		String sql = "SELECT ea.EmailAddress FROM Campaign AS ca LEFT JOIN EmailAddressCampaign AS eac ON ca.CampaignID = eac.CampaignID LEFT JOIN EmailAddress AS ea ON eac.EmailAddressID = ea.EmailAddressID WHERE ca.CampaignName = ? AND ea.EmailType = ?";
		return super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperEmailAddress(), campaignName, type);
	}
	
	public List<String> fetchEmailsOfTypeAssopciatedToCampaignAndInterestGroup(
			String campaignName, String type) {
		String sql = "SELECT ea.EmailAddress"
				+ " FROM Campaign ca JOIN CampaignInterestGroup cig ON ca.CampaignID = cig.CampaignID "
				+ " JOIN InterestGroup ig ON cig.InterestGroupID = ig.InterestGroupID "
				+ " JOIN EmailAddressInterestGroup eai ON ig.InterestGroupID = eai.InterestGroupID "
				+ " JOIN EmailAddress ea ON eai.EmailAddressID = ea.EmailAddressID "
				+ " WHERE ca.CampaignName = ? AND ea.EmailType = ?";
		return super
				.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.query(sql, new StringRowMapperEmailAddress(), campaignName,
						type);
	}
	
	public List<String> fetchEmailAddressIDOfUserType(String emailAddress, String type){
		String sql = "SELECT EmailAddressID FROM EmailAddress WHERE EmailAddress = ? AND EmailType = ?";
		return super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperEmailAddressID(), emailAddress, type);
	}
	
	public String fetchEmailAddressIDOfUserType(String emailAddress, String type, String campaignName){
		String sql = "SELECT ea.EmailAddressID FROM EmailAddress ea "
			+"JOIN EmailAddressCampaign eac ON  ea.EmailAddressID = eac.EmailAddressID "
			+"JOIN Campaign ca ON eac.CampaignID = ca.CampaignID "
			+"WHERE EmailAddress = ? AND EmailType = ? AND ca.CampaignName = ?";
		List<String> idList = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperEmailAddressID(), emailAddress, type, campaignName);
		if(idList.isEmpty()){return null;}  // probably bogus
		return idList.get(0);
	}
}
