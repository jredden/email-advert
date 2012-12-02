package com.zenred.eadvert.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.zenred.eadvert.model.domain.Contact;
import com.zenred.eadvert.model.domain.EMail;

public class ContactDaoTest extends AbstractJDBCDao {

	String contactID;
	String contactType;
	String emailID;
	ContactDao contactDao;
	Contact obj;

	@Before
	public void setUp() {
		contactDao = new ContactDao();
		obj = new Contact();
	}

	private class StringRowMapperContactID implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("ContactID");
		}
	}

	private class StringRowMapperContactType implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("ContactType");
		}
	}

	private class StringRowMapperContactEmailAddressID implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("EmailAddressID");
		}
	}

	@Test
	public String fetchFirstContactID() {
		String sql = "SELECT ContactID FROM Contact";
		contactID = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperContactID()).get(0);

		System.out.println("Name(ContactID) : " + contactID);

		return contactID;
	}

	@Test
	public String fetchContactType() {
		String sql = "SELECT ContactType FROM Contact";
		contactType = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperContactType()).get(0);

		System.out.println("Contact Type : " + contactType);

		return contactType;
	}

	@Test
	public String fetchFirstDescription() {
		String sql = "SELECT EmailAddressID FROM Contact";
		emailID = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperContactEmailAddressID()).get(0);

		System.out.println("Contact Email ID : " + emailID);

		return emailID;
	}

	@Test
	public void contactTest() {
		EMail emailAdd = new EMail();
		emailAdd.setAddress("contactTest@gmail.com");
		obj.setType(obj.Type.business);
		contactDao.copyToContact(emailAdd, obj);
	}

	@After
	public void tearDown() {

		contactID = null;
		contactType = null;
		emailID = null;

	}

}
