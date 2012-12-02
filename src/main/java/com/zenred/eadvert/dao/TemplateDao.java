package com.zenred.eadvert.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.zenred.eadvert.model.domain.TemplateVector;

public class TemplateDao extends AbstractJDBCDao {
	private static String tableName = "TemplateVectorCampaign";

	private class StringRowMapperCampaignID implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("CampaignID");
		}
	}

	private class StringRowMapperTemplateName implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("TemplateName");
		}
	}

	private class StringRowMapperTemplateVectorID implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("TemplateVectorID");
		}
	}

	public List<String> fetchTemplatesCestnPasEnCampaign(String campaignName) {
		String campaignId = readCampaignId(campaignName);

		List<String> templates = null;
		String templateSql = "SELECT tv.TemplateName, tv.Uid FROM TemplateVector tv WHERE tv.TemplateVectorID NOT IN (SELECT TemplateVectorID FROM TemplateVectorCampaign WHERE CampaignID = ?)";
		templates = super.jdbcSetUp().getSimpleJdbcTemplate().query(
				templateSql, new StringRowMapperTemplateName(), campaignId);
		return templates;
	}

	private String readCampaignId(String campaignName) {
		String sql = "SELECT CampaignID FROM Campaign WHERE CampaignName = ?";
		String campaignId = super.jdbcSetUp().getSimpleJdbcTemplate().query(
				sql, new StringRowMapperCampaignID(), campaignName).get(0);
		return campaignId;
	}

	private String readTemplateVectorId(String templateName) {
		String sql = "SELECT TemplateVectorID FROM TemplateVector WHERE TemplateName = ?";
		String templateVectorId = super
				.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.query(sql, new StringRowMapperTemplateVectorID(), templateName)
				.get(0);
		return templateVectorId;
	}

	public List<String> fetchTemplatesCestEnCampaign(String campaignName) {
		String campaignId = readCampaignId(campaignName);

		List<String> templates = null;
		String templateSql = "SELECT tv.TemplateName FROM TemplateVector tv INNER JOIN TemplateVectorCampaign tvc ON tv.TemplateVectorID = tvc.TemplateVectorID Where tvc.CampaignID = ?";
		templates = super.jdbcSetUp().getSimpleJdbcTemplate().query(
				templateSql, new StringRowMapperTemplateName(), campaignId);
		return templates;

	}

	public void dissasociateTemplateFromCampaign(String templateName,
			String campaignName) {
		String templateVectorId = readTemplateVectorId(templateName);
		String campaignId = readCampaignId(campaignName);
		String sql = "DELETE FROM TemplateVectorCampaign WHERE TemplateVectorID = ? AND CampaignID = ?";
		super.jdbcSetUp().getSimpleJdbcTemplate().update(sql, templateVectorId,
				campaignId);
	}

	public void associateTemplateToCampaign(String templateName,
			String campaignName) {
		String templateVectorId = readTemplateVectorId(templateName);
		String campaignId = readCampaignId(campaignName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("TemplateVectorID", templateVectorId);
		map.put("CampaignID", campaignId);
		super.jdbcInsertSetup().withTableName(tableName).usingColumns(
				"TemplateVectorID", "CampaignID").execute(map);
	}

	public List<TemplateVector> readTemplatesAssociatedToCampaign(
			String campaignName) {
		List<TemplateVector> templateList = new ArrayList<TemplateVector>();
		String campaignId = readCampaignId(campaignName);
		String templateSql = "SELECT tv.TemplateName, tv.Uid, tv.GraphicUid FROM TemplateVector tv INNER JOIN TemplateVectorCampaign tvc ON tv.TemplateVectorID = tvc.TemplateVectorID Where tvc.CampaignID = ?";
		List<Map<String, Object>> templateVectorList = super.jdbcSetUp()
				.getSimpleJdbcTemplate().queryForList(templateSql, campaignId);
		for (Map<String, Object> templateVectorMap : templateVectorList) {
			TemplateVector templateVector = new TemplateVector();
			templateVector.setTemplateName((String) templateVectorMap
					.get("TemplateName"));
			templateVector.setUid((String) templateVectorMap.get("Uid"));
			templateVector.setGraphicUid((String) templateVectorMap
					.get("GraphicUid"));
			templateList.add(templateVector);
		}
		return templateList;
	}
}
