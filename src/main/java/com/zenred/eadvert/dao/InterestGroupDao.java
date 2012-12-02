package com.zenred.eadvert.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.zenred.eadvert.model.domain.Campaign;
import com.zenred.eadvert.model.domain.EmailAddress;
import com.zenred.eadvert.model.domain.InterestGroup;

public class InterestGroupDao extends AbstractJDBCDao {
	
	private class StringRowMapperInterestGroupNames implements ParameterizedRowMapper<String> {	
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("InterestGroupName");
		}
	}
	private class StringRowMapperInterestGroupID implements ParameterizedRowMapper<String>{
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("InterestGroupID");
		}
		
	}
	private class StringRowMapperProvidersIDs implements ParameterizedRowMapper<String>{
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("OemID");
		}
	}
	private class StringRowMapperProviderName implements ParameterizedRowMapper<String>{
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("OemName");
		}
	}

	private class StringRowMapperCampaignIDs implements ParameterizedRowMapper<String>{
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("CampaignID");
		}
	}
	
	private class IntRowMapperCount implements ParameterizedRowMapper<Integer>{
		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getInt("Count");
		}
		
	}
	
	private class StringRowMapperEmailAddressInterestGroupID implements ParameterizedRowMapper<String>{
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("EmailAddressInterestGroupID");
		}

	}
	
	private String fetchInterestGroupIDUsingName(String interestGroupName){
		String sqlInterestGroupIP = "SELECT InterestGroupID FROM InterestGroup WHERE InterestGroupName = ?";
		List<String> interestGroupIDList = super
				.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.query(sqlInterestGroupIP,
						new StringRowMapperInterestGroupID(), interestGroupName);
		return interestGroupIDList.isEmpty()? null :interestGroupIDList.get(0);
	}
	
	public List<String> fetchInterestGroups() {
		List<String> interestGroups = null;
		String sql = "SELECT InterestGroupName FROM InterestGroup";
		interestGroups = super.jdbcSetUp().getSimpleJdbcTemplate()
				.query(sql, new StringRowMapperInterestGroupNames());
		return interestGroups;
	}
	
	public List<String> fetchInterestGroupsAssociatedToUser(String userName) {
		String sql = "SELECT ig.InterestGroupName "
				+ "FROM InterestGroup ig "
				+ "JOIN OemInterestGroup oig ON ig.InterestGroupID = oig.InterestGroupID "
				+ "JOIN Oem oe ON oe.OemID = oig.OemID "
				+ "JOIN UserOem uo ON uo.OemID = oe.OemID "
				+ "JOIN User us ON uo.UserID = us.UserID "
				+ "WHERE UserName = ?";
		List<String> interestGroups = super.jdbcSetUp().getSimpleJdbcTemplate()
				.query(sql, new StringRowMapperInterestGroupNames(), userName);
		return interestGroups;
	}
	
	@Transactional
	public InterestGroup fetchInterestGroup(String interestGroupName) {
		String providerSql = "SELECT oem.OemName FROM OemInterestGroup oig LEFT JOIN Oem oem ON oig.OemID = oem.OemID WHERE InterestGroupID = ?";
		String campaignSql = "SELECT CampaignID FROM CampaignInterestGroup WHERE InterestGroupID = ?";
		String campaignDataSql = "SELECT oem.OemName, ca.CampaignName FROM Campaign AS ca LEFT JOIN Oem oem ON ca.OemID = oem.OemID WHERE ca.CampaignID = ?";
		InterestGroup interestGroup = new InterestGroup();
		interestGroup.setInterestGroupName(interestGroupName);
		List<String> providerList = super
				.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.query(providerSql, new StringRowMapperProviderName(),
						fetchInterestGroupIDUsingName(interestGroupName));
		List<Campaign> campaignList = new ArrayList<Campaign>();
		List<String> campaignIdList = super
				.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.query(campaignSql, new StringRowMapperCampaignIDs(),
						fetchInterestGroupIDUsingName(interestGroupName));
		interestGroup.setCampaignList(campaignList);
		for (String campaignId : campaignIdList){
			Map <String, Object> campaignMap = super.jdbcSetUp().getSimpleJdbcTemplate().queryForMap(campaignDataSql, campaignId);
			Campaign campaign = new Campaign();
			campaign.setOem(campaignMap.get("OemName").toString());
			campaign.setName(campaignMap.get("CampaignName").toString());
			interestGroup.getCampaignList().add(campaign);
		}
		List<EmailAddress> emailAddressList = new ArrayList<EmailAddress>();  // empty for now
		interestGroup.setProviderList(providerList);
		interestGroup.setEmailAddressList(emailAddressList);
		return interestGroup;
	}
	
	public boolean campaignInInterestGroup(String campaignName, String interestGroupName){
		boolean answer = false;
		String sql = "SELECT COUNT(ca.CampaignName)AS Count FROM Campaign ca LEFT JOIN CampaignInterestGroup cig ON ca.CampaignID = cig.CampaignID LEFT JOIN InterestGroup ig ON cig.InterestGroupID = ig.InterestGroupID WHERE ig.InterestGroupName = ? AND ca.CampaignName = ?";
		Integer count = super.jdbcSetUp().getSimpleJdbcTemplate()
		.query(sql, new IntRowMapperCount(), interestGroupName, campaignName).get(0);		
		if(count.intValue() > 0){answer = true;}
		return answer;
	}
	
	public boolean providerInInterestGroup(String providerName, String interestGroupName){
		boolean answer = false;
		String sql = "SELECT COUNT(oe.OemName) AS Count FROM Oem oe LEFT JOIN OemInterestGroup oig ON oe.OemID = oig.OemID LEFT JOIN InterestGroup ig ON oig.InterestGroupID = ig.InterestGroupID WHERE ig.InterestGroupName = ? AND oe.OemName = ?";
		Integer count = super.jdbcSetUp().getSimpleJdbcTemplate()
		.query(sql, new IntRowMapperCount(), interestGroupName, providerName).get(0);		
		if(count.intValue() > 0){answer = true;}
		return answer;
	}
	
	private static String tableName = "CampaignInterestGroup";
	@Transactional
	public void addCampaignToInterestGroup(InterestGroup interestGroup) {
		String sqlCampaignId = "SELECT CampaignID FROM Campaign WHERE CampaignName = ?";
		int lastAdded = interestGroup.getCampaignList().size() - 1;
		String campaignName = interestGroup.getCampaignList().get(lastAdded)
				.getName();
		String interestGroupName = interestGroup.getInterestGroupName();
		Map<String, Object> map = new HashMap<String, Object>();
		String interestGroupID = fetchInterestGroupIDUsingName(interestGroupName);
		String campaignID = super
				.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.query(sqlCampaignId, new StringRowMapperCampaignIDs(),
						campaignName).get(0);
		map.put("CampaignID", campaignID);
		map.put("InterestGroupID", interestGroupID);
		super.jdbcInsertSetup().withTableName(tableName)
				.usingColumns("CampaignID", "InterestGroupID").execute(map);
	}
	
	@Transactional
	public void addProviderToInterestGroup(InterestGroup interestGroup) {
		String sqlOemId = "SELECT OemID FROM Oem WHERE OemName = ?";
		int lastAdded = interestGroup.getProviderList().size() - 1;
		String OemName = interestGroup.getProviderList().get(lastAdded);
		String interestGroupName = interestGroup.getInterestGroupName();
		Map<String, Object> map = new HashMap<String, Object>();
		String interestGroupID = fetchInterestGroupIDUsingName(interestGroupName);
		String OemID = super
				.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.query(sqlOemId, new StringRowMapperProvidersIDs(),
						OemName).get(0);
		map.put("OemID", OemID);
		map.put("InterestGroupID", interestGroupID);
		super.jdbcInsertSetup().withTableName("OemInterestGroup")
				.usingColumns("OemID", "InterestGroupID").execute(map);
	}
	public boolean interestGroupNameAlreadyThere(String interestGroupName){
		String result = fetchInterestGroupIDUsingName(interestGroupName);
		return null == result || result.isEmpty() ? false : true;
	}
	
	public void addInterestGroup(String interestGroupName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("InterestGroupName", interestGroupName);
		super.jdbcInsertSetup().withTableName("InterestGroup")
				.usingColumns("InterestGroupName").execute(map);
	}
	
	public boolean cestEmailAddressSurLeInterestGroup(String emailAddressID,
			String interestGroupName) {
		boolean answer = true;
		String interestGroupID = fetchInterestGroupIDUsingName(interestGroupName);
		String sql = "SELECT EmailAddressInterestGroupID FROM EmailAddressInterestGroup WHERE EmailAddressID = ? AND InterestGroupID = ?";
		List<String> anId = super
				.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.query(sql, new StringRowMapperEmailAddressInterestGroupID(),
						emailAddressID, interestGroupID);
		if (anId.isEmpty()) {
			answer = false;
		}
		return answer;
	}
	
	public void addEmailAddressToInterestGroup(String emailAddressID,
			String interestGroupName) {
		String interestGroupID = fetchInterestGroupIDUsingName(interestGroupName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("InterestGroupID", interestGroupID);
		map.put("EmailAddressID", emailAddressID);
		super.jdbcInsertSetup().withTableName("EmailAddressInterestGroup")
				.usingColumns("InterestGroupID", "EmailAddressID").execute(map);
	}
	
}
