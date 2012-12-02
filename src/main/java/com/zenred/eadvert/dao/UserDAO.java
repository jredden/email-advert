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


import com.zenred.eadvert.model.domain.IProvider;
import com.zenred.eadvert.model.domain.IUser;
import com.zenred.eadvert.model.domain.User;
import com.zenred.eadvert.model.domain.User.UserRole;

public class UserDAO extends AbstractJDBCDao {
	private class StringRowMapperUserRole implements ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("UserRole");
		}
	}
	private class StringRowMapperUserPassword implements ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("UserPassword");
		}
	}
	private class StringRowMapperUserID implements ParameterizedRowMapper<String>{
		public String mapRow(ResultSet rs, int rowNum)throws SQLException {
			return rs.getString("UserID");
		}
	}
	private class StringRowMapperOemID implements ParameterizedRowMapper<String>{
		public String mapRow(ResultSet rs, int rowNum)throws SQLException {
			return rs.getString("OemID");
		}
	}

	private SimpleJdbcCall procDeleteUser;
	
	public UserRole readUserData(String userName, String password){
		UserRole userRole = null;
		
		String sql = "SELECT UserRole FROM User WHERE UserName = ? AND UserPassword = ?";
		List<String> result =super.jdbcSetUp().getSimpleJdbcTemplate().query(
				sql, new StringRowMapperUserRole(), userName, password);
		if(result.size() == 0){return userRole;}
		String s_userRole = result.get(0);
		userRole = User.getUserRole(s_userRole);
		return userRole;
	}

	private String tableName = "User"; 

	public void createUser(IUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("UserName", user.getUserName());
		map.put("UserPassword", user.getPassword());
		map.put("UserRole", user.getS_userRole().toString());
		super.jdbcInsertSetup().withTableName(tableName)
				.usingColumns("UserName", "UserPassword", "UserRole")
				.execute(map);
	}
	
	public void deleteUser(IUser user) {
//		System.out.println(user);
		procDeleteUser = new SimpleJdbcCall(super.jdbcSetUp().getJdbcTemplate())
				.withProcedureName("UserDelete")
				.withoutProcedureColumnMetaDataAccess().useInParameterNames(
						"p_UserName", "p_UserPassword").declareParameters(
						new SqlParameter("p_UserName", Types.CHAR),
						new SqlParameter("p_UserPassword", Types.CHAR));
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("p_UserName", user.getUserName());
		mapSqlParameterSource.addValue("p_UserPassword", user.getPassword());
		SqlParameterSource in = mapSqlParameterSource;
		procDeleteUser.execute(in);
	}
	
	public void updateUsersPassword(IUser user) {
		String sql = "UPDATE User set UserPassword = ? WHERE UserName = ? AND UserPassword = ?";
		super.jdbcSetUp().getSimpleJdbcTemplate().update(
				sql,
				new Object[] { user.getNewPassword(), user.getUserName(),
						user.getPassword() });
	}
	
	public void updateUsersRole(IUser user){
		String sql = "UPDATE User set UserRole = ? WHERE UserName = ? AND UserPassword = ?";
		super.jdbcSetUp().getSimpleJdbcTemplate().update(
				sql,
				new Object[] { user.getS_userRole().toString(), user.getUserName(),
						user.getPassword() });
	}
	
	public List<User> fetchNonRootUsers(){
		List<User> userList = new ArrayList<User>();
		String sql = "SELECT UserName, UserPassword, UserRole FROM User WHERE UserRole != ?";
		List<Map <String, Object>> userMapList = super.jdbcSetUp().getSimpleJdbcTemplate().queryForList(sql, "root");
		for (Map <String, Object> userMap : userMapList){
			User user = new User();
			user.setUserName((String) userMap.get("UserName"));
			user.setS_userRole(User.getUserRole((String) userMap.get("UserRole")));
			user.setPassword((String) userMap.get("UserPassword"));
			userList.add(user);
		}
		return userList;
	}
	
	public List<User> fetchUsersWithProviderRole(){
		List<User> userList = new ArrayList<User>();
		String sql = "SELECT UserName, UserPassword, UserRole FROM User WHERE UserRole = ?";
		List<Map <String, Object>> userMapList = super.jdbcSetUp().getSimpleJdbcTemplate().queryForList(sql, "provider");
		for (Map <String, Object> userMap : userMapList){
			User user = new User();
			user.setUserName((String) userMap.get("UserName"));
			user.setS_userRole(User.getUserRole((String) userMap.get("UserRole")));
			user.setPassword((String) userMap.get("UserPassword"));
			userList.add(user);
		}
		return userList;		
	}

	public String readUserPassword(String userName, String role) {
		String sql = "SELECT UserPassword FROM User WHERE UserRole = ? AND UserName = ?";
		String password = super.jdbcSetUp().getSimpleJdbcTemplate().query(
				sql, new StringRowMapperUserPassword(), role, userName).get(0);
		return password;
	}
	
	@Transactional
	public void buildUserProviderRow(IProvider provider) {
		String providerSql = "SELECT OemID FROM Oem WHERE OemName = ?";
		String userSql = "SELECT UserID FROM User WHERE UserName = ? AND UserRole = 'provider'";
		String oemID = super
				.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.query(providerSql, new StringRowMapperOemID(),
						provider.getProvider()).get(0);
		String userId = super
				.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.query(userSql, new StringRowMapperUserID(),
						provider.getUserName()).get(0);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("OemID", oemID);
		map.put("UserID", userId);
		super.jdbcInsertSetup().withTableName("UserOem")
		.usingColumns("OemID", "UserID")
		.execute(map);
	}
}
