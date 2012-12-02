package com.zenred.eadvert.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.zenred.eadvert.model.domain.EMail;

public class ContactPhoneDaoTest extends AbstractJDBCDao {

	String contactID;
	String contactPhone;
	String contactMobilePhone;

	ContactPhoneDao contactPhoneDao;

	@Before
	public void setUp() {

		contactPhoneDao = new ContactPhoneDao();
	}

	private class StringRowMapperContactID implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("ContactID");
		}
	}

	private class StringRowMapperContactPhone implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("Phone");
		}
	}

	private class StringRowMapperContactMobilePhone implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("MobilePhone");
		}
	}

	@Test
	public String fetchFirstContactID() {
		String sql = "SELECT ContactID FROM ContactPhone";
		contactID = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperContactID()).get(0);

		System.out.println("Phone : " + contactID);

		return contactID;
	}

	@Test
	public String fetchFirstPhone() {
		String sql = "SELECT Phone FROM ContactPhone";
		contactPhone = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperContactPhone()).get(0);

		System.out.println("Phone : " + contactPhone);

		return contactPhone;
	}

	@Test
	public String fetchFirstMobilePhone() {
		String sql = "SELECT MobilePhone FROM ContactPhone";
		contactMobilePhone = super.jdbcSetUp().getSimpleJdbcTemplate().query(
				sql, new StringRowMapperContactPhone()).get(0);

		System.out.println("Mobile Phone : " + contactMobilePhone);

		return contactMobilePhone;
	}

	@Test
	public void phoneTest() {
		EMail mapCont = new EMail();
		mapCont.setAddress("JunitTest@gmail.com");
		String addPhone = "6264246789";
		String addMobilePhone = "3235250987";
		contactPhoneDao.addPhoneNo(mapCont, addPhone, addMobilePhone);

	}

	@Test
	public void phoneTest1() {
		EMail mapCont = new EMail();
		mapCont.setAddress("JunitTest1@gmail.com");
		String addPhone = "";
		String addMobilePhone = "3233638970";
		contactPhoneDao.addPhoneNo(mapCont, addPhone, addMobilePhone);
	}

	@Test
	public void phoneTest2() {
		EMail mapCont = new EMail();
		mapCont.setAddress("JunitTest2@gmail.com");
		String addPhone = "6264246789";
		String addMobilePhone = "";
		contactPhoneDao.addPhoneNo(mapCont, addPhone, addMobilePhone);
	}

	@After
	public void tearDown() {

		contactID = null;
		contactPhone = null;
		contactMobilePhone = null;

	}

}
