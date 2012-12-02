package com.zenred.eadvert.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class OemDao extends AbstractJDBCDao {
	
	private class StringRowMapperOemName implements ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("OemName");
		}
	}

	
	private static String tableName = "Oem";
	
	public void insertOem(String oem){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("OemName", oem);
		super.jdbcInsertSetup().withTableName(tableName).usingColumns("OemName").execute(map);
	}
	
	public List<String> fetchOems(){
		List<String> oems = null;
		String sql = "SELECT OemName FROM Oem";
		oems = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql, new StringRowMapperOemName());
		return oems;
	}

	public List<String> fetchOemsAssociatedToUser(String user) {
		String sql = "SELECT oe.OemName "
				+ " FROM Oem oe JOIN UserOem uo ON oe.OemID = uo.OemID "
				+ " JOIN  User us ON uo.UserID = us.UserID "
				+ " WHERE us.UserName = ?";
		List<String> oems = null;
		oems = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql, new StringRowMapperOemName(), user);
		return oems;		
	}
}
