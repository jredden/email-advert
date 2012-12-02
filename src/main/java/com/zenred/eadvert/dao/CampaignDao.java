package com.zenred.eadvert.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.zenred.eadvert.model.domain.Campaign;

public class CampaignDao extends AbstractJDBCDao {
	
	private class StringRowMapperOemID implements ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("OemID");
		}
	}
	private class StringRowMapperOemName implements ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("OemName");
		}
	}

	private class StringRowMapperCampaignName implements ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("CampaignName");
		}
	}

	
	private static String tableName = "Campaign";
	
	@Transactional
	public void insertCampaign(Campaign campaign){
		Map<String, Object> map = new HashMap<String, Object>();
		String sql = "SELECT OemID FROM Oem WHERE OemName = ?";
		String oemId = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperOemID(), campaign.getOem()).get(0);
		map.put("CampaignName", campaign.getName());
		map.put("OemID", oemId);
		super.jdbcInsertSetup().withTableName(tableName).usingColumns("CampaignName","OemID").execute(map);
	}

	public boolean doesCampaignExist(String campaignName){
		String sql = "SELECT CampaignName FROM Campaign WHERE CampaignName = ?";
		List<String> name = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperCampaignName(), campaignName);
		if(null == name || name.isEmpty()){return false;}
		boolean answer = name.get(0).equals(campaignName);
		return answer;
	}
	
	public List<String> fetchCampaignNames(){
		List<String> campaignNames = null;
		String sql = "SELECT CampaignName FROM Campaign";
		campaignNames = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperCampaignName());
		return campaignNames;
	}
	
	public String fetchOemNameAssociatedWithCampaign(String campaignName){
		String sql = "SELECT oem.OemName FROM Campaign AS ca LEFT JOIN Oem oem on ca.OemID = oem.OemID WHERE ca.CampaignName = ?";
		return super.jdbcSetUp().getSimpleJdbcTemplate().query(sql, new StringRowMapperOemName(), campaignName).get(0);
	}
	
	public Campaign loadCampaign(String campaignName){
		Campaign campaign = new Campaign();
		campaign.setName(campaignName);
		campaign.setOem(fetchOemNameAssociatedWithCampaign(campaignName));
		return campaign;
	}
	
	public List<String> fetchCampaignsAssociatedToProvider(String provider){
		String sql = "SELECT ca.CampaignName FROM Campaign ca JOIN Oem oe ON ca.OemID = oe.OemID WHERE OemName = ?";
		List<String> result = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql, new StringRowMapperCampaignName(), provider);
		return result;
	}
}
