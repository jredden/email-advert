package com.zenred.eadvert.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.zenred.eadvert.model.domain.Message;

public class TestSupportDAO extends AbstractJDBCDao {

	private class StringRowMapperCampaignName implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("CampaignName");
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

	private class StringRowMapperOemName implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("OemName");
		}
	}

	private class StringRowMapperTemplateName implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("TemplateName");
		}

	}

	private class StringRowMapperEmailAddress implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("EmailAddress");
		}
	}

	private class StringRowMapperMessageName implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("Name");
		}
	}

	private class StringRowMapperMessageContextMD5 implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("ContentMD5");
		}
	}
	
	
	private class StringRowMapperInterestGroupName implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("InterestGroupName");
		}
	}


	public String fetchFirstCampaignName() {
		String sql = "SELECT CampaignName FROM Campaign";
		String campaignName = super.jdbcSetUp().getSimpleJdbcTemplate().query(
				sql, new StringRowMapperCampaignName()).get(0);
		return campaignName;
	}

	public String fetchFirstCampaigID() {
		String sql = "SELECT CampaignID FROM Campaign";
		String campaignName = super.jdbcSetUp().getSimpleJdbcTemplate().query(
				sql, new StringRowMapperCampaignID()).get(0);
		return campaignName;
	}

	public String fetchCampiagnOemID(String campaignName) {
		String sql = "SELECT OemID FROM Campaign WHERE CampaignName = ?";
		String oemID = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperOemID(), campaignName).get(0);
		return oemID;
	}

	public String fetchOemName(String oemID) {
		String sql = "SELECT OemName FROM Oem WHERE OemID = ?";
		String oemName = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperOemName(), oemID).get(0);
		return oemName;
	}

	public String fetchFirstTemplateName() {
		String sql = "SELECT TemplateName from TemplateVector";
		return super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperTemplateName()).get(0);
	}

	public String fetchFirstEmailAddress() {
		String sql = "SELECT EmailAddress FROM EmailAddress";
		return super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperEmailAddress()).get(0);
	}

	public String fetchNthMessageName(int messageIndex) {
		String sql = "SELECT Name FROM Message";
		return super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperMessageName()).get(messageIndex);
	}

	public Message fetchNthReviewableMessage(int messageIndex) {
		String sqlMessages = "SELECT Name, Revision FROM Message WHERE State = 'review'";
		List<Map<String, Object>> messageListMap = super.jdbcSetUp()
				.getSimpleJdbcTemplate().queryForList(sqlMessages);
		Message message = new Message();
		Map<String, Object> messageMap = messageListMap.get(messageIndex);
		message.setUri(messageMap.get("Name").toString());
		message.setVersion(messageMap.get("Revision").toString());
		return message;
	}

	public String fetchFirstMessageContextMD5() {
		String sqlMessageContext = "SELECT ContentMD5 FROM MessageContext WHERE ContentMD5 IS NOT NULL";
		return super.jdbcSetUp().getSimpleJdbcTemplate().query(
				sqlMessageContext, new StringRowMapperMessageContextMD5()).get(
				0);
	}

	public Map<String, Object> fetchFirstMessageWithMessageContext() {
		String sqlMessage = "SELECT msg.Name, msg.Revision FROM Message msg JOIN MessageContext mscx ON msg.MessageID = mscx.MessageID";
		List<Map<String, Object>> messageListMap = super.jdbcSetUp()
				.getSimpleJdbcTemplate().queryForList(sqlMessage);
		Map<String, Object> messageMap = messageListMap.get(0);
		return messageMap;
	}
	
	public String fetchFirstInterestGroupName(){
		String sqlInterestGroup = "SELECT InterestGroupName FROM InterestGroup";
		return super
				.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.query(sqlInterestGroup, new StringRowMapperInterestGroupName())
				.get(0);
	}
}
