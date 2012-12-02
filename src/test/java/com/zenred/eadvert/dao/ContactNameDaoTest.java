package com.zenred.eadvert.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.zenred.eadvert.model.domain.EMail;

public class ContactNameDaoTest extends AbstractJDBCDao {

	String contactID;
	String nameValue;
	String nameDescription;
	ContactNameDao contactName;

	@Before
	public void setUp() {
		contactName = new ContactNameDao();
	}

	private class StringRowMapperContactID implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("ContactID");
		}
	}

	private class StringRowMapperContactValue implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("Value");
		}
	}

	private class StringRowMapperContactDescription implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("Description");
		}
	}

	@Test
	public String fetchFirstContactID() {
		String sql = "SELECT ContactID FROM ContactName";
		contactID = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperContactID()).get(0);

		System.out.println("Name(ContactID) : " + contactID);

		return contactID;
	}

	@Test
	public String fetchFirstName() {
		String sql = "SELECT Value FROM ContactName";
		nameValue = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperContactValue()).get(0);

		System.out.println("Contact Value : " + nameValue);

		return nameValue;
	}

	@Test
	public String fetchFirstDescription() {
		String sql = "SELECT Description FROM ContactName";
		nameDescription = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperContactDescription()).get(0);

		System.out.println("Contact Description : " + nameDescription);

		return nameDescription;
	}

	@Test
	public void testContactName() {
		EMail mapCont = new EMail();
		mapCont.setAddress("testContactName@gmail.com");
		contactName.addNames(mapCont, "John", "T", "Redden");

	}

	@Test
	public void testContactName1() {
		EMail mapCont = new EMail();
		mapCont.setAddress("testContactName1@gmail.com");
		contactName.addNames(mapCont, "", "T", "Redden");

	}

	@Test
	public void testContactName2() {
		EMail mapCont = new EMail();
		mapCont.setAddress("testContactName2@gmail.com");
		contactName.addNames(mapCont, "John", "", "Redden");
	}

	@Test
	public void testContactName3() {
		EMail mapCont = new EMail();
		mapCont.setAddress("testContactName3@gmail.com");
		contactName.addNames(mapCont, "John", "T", "");
	}

	@After
	public void tearDown() {

		contactID = null;
		nameValue = null;
		nameDescription = null;

	}

}
