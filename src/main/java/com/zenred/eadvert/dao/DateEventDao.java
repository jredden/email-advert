package com.zenred.eadvert.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.zenred.eadvert.model.domain.DateEvent;

public class DateEventDao extends AbstractJDBCDao {
	
	private class StringRowMapperCampaignID implements ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("CampaignID");
		}
	}
	private class StringRowMapperCampaignName implements ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("CampaignName");
		}
	}

	
	private static String tableName = "DateEvent";
	

	@Transactional
	public void insertDateEvent(DateEvent dateEvent){
		Map<String, Object> map = new HashMap<String, Object>();
		String sql = "SELECT CampaignID FROM Campaign WHERE CampaignName = ?";
		String campaignId = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperCampaignID(), dateEvent.getCampaignName()).get(0);
		map.put("CampaignID", campaignId);
		map.put("Name", dateEvent.getName());
		map.put("EventName", dateEvent.getEventName());
		map.put("DateSegmentOne", dateEvent.getDateSegmentOne());
		map.put("DateSegmentTwo", dateEvent.getDateSegmentTwo());
		super.jdbcInsertSetup().withTableName(tableName).usingColumns("CampaignID","Name", "EventName", "DateSegmentOne", "DateSegmentTwo").execute(map);
	}

	@Transactional
	public DateEvent readCampaignDateEvent(String campaignName){
		DateEvent dateEvent = new DateEvent();
		String sql = "SELECT CampaignID FROM Campaign WHERE CampaignName = ?";
		String campaignId = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperCampaignID(), campaignName).get(0);
		String sqlDateEvent = "SELECT DateEventID, CampaignID, Name, EventName, DateSegmentOne, DateSegmentTwo FROM DateEvent WHERE EventName = 'campaign definition' AND CampaignID = ?";
		Map <String, Object> eventMap = super.jdbcSetUp().getSimpleJdbcTemplate().queryForMap(sqlDateEvent, campaignId);
		dateEvent.setCampaignName(campaignName);
		dateEvent.setName((String) eventMap.get("Name"));
		dateEvent.setEventName((String) eventMap.get("EventName"));
		dateEvent.setDateSegmentOne(eventMap.get("DateSegmentOne").toString());
		dateEvent.setDateSegmentTwo(eventMap.get("DateSegmentTwo").toString());
		return dateEvent;
	}
	
	@Transactional
	public void updateCampaignDateEvent(String campaignName, DateEvent dateEvent) {
		String sql = "SELECT CampaignID FROM Campaign WHERE CampaignName = ?";
		String campaignId = super.jdbcSetUp().getSimpleJdbcTemplate().query(
				sql, new StringRowMapperCampaignID(), campaignName).get(0);
		String sqlUpdateDateEvent = "UPDATE DateEvent SET Name = ?, EventName = ?,  DateSegmentOne = ?,  DateSegmentTwo = ? WHERE EventName = 'campaign definition' AND CampaignID = ?";
		super.jdbcSetUp().getSimpleJdbcTemplate().update(
				sqlUpdateDateEvent,
				new Object[] { dateEvent.getName(), dateEvent.getEventName(),
						dateEvent.getDateSegmentOne(),
						dateEvent.getDateSegmentTwo(),
						campaignId});
	}
	
	@Transactional
	public void updateCampaignDateEvent(DateEvent dateEvent) {
		String sqlUpdateDateEvent = "UPDATE DateEvent SET Name = ?,   DateSegmentOne = ?,  DateSegmentTwo = ? WHERE EventName = ?";
		super.jdbcSetUp().getSimpleJdbcTemplate().update(
				sqlUpdateDateEvent,
				new Object[] { dateEvent.getName(), 
						dateEvent.getDateSegmentOne(),
						dateEvent.getDateSegmentTwo(),
						dateEvent.getEventName()});
	}
	
	@Transactional
	public List<DateEvent> readProductionMessagesForCampaigns(){
		List<DateEvent> dateEvents = new ArrayList<DateEvent>();
		String campaignNameSql = "SELECT CampaignName FROM Campaign WHERE CampaignID = ?";
		String sqlProductionMessageWaiting = "SELECT Distinct DateSegmentOne, DateSegmentTwo, EventName, CampaignID FROM DateEvent WHERE Name = 'PRODUCTION_MESSAGE' ORDER BY CampaignID";
		List<Map <String, Object>> dateEventMapList = super.jdbcSetUp().getSimpleJdbcTemplate().queryForList(sqlProductionMessageWaiting);
		for(Map<String, Object> dateEventMap: dateEventMapList){
			String campaignId  = dateEventMap.get("CampaignID").toString();
			String campaignName = super.jdbcSetUp().getSimpleJdbcTemplate().query(campaignNameSql,new StringRowMapperCampaignName(),campaignId).get(0);
			DateEvent dateEvent = new DateEvent();
			dateEvent.setCampaignName(campaignName);
			dateEvent.setDateSegmentOne(dateEventMap.get("DateSegmentOne").toString());
			dateEvent.setDateSegmentTwo(dateEventMap.get("DateSegmentTwo").toString());
			dateEvent.setEventName(dateEventMap.get("EventName").toString());
			dateEvent.setName("PRODUCTION_MESSAGE");
			dateEvents.add(dateEvent);
		}
		return dateEvents;
	}

}
