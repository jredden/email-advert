package com.zenred.eadvert.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.zenred.eadvert.model.domain.EMail;

public class ContactAddressDaoTest extends AbstractJDBCDao {

	String contactID;
	String conAdd1;
	String conAdd2;
	String conState;
	String conCity;
	String conZip;

	ContactAddressDao contactAddress;
	
	@Before
	public void setUp() {
       contactAddress = new ContactAddressDao();
	}

	private class StringRowMapperContactID implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("ContactID");
		}
	}

	private class StringRowMapperContactAddress1 implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("Address1");
		}
	}

	private class StringRowMapperContactAddress2 implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("Address2");
		}
	}

	private class StringRowMapperContactState implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("ContactState");
		}
	}

	private class StringRowMapperContactCity implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("City");
		}
	}

	private class StringRowMapperContactZip implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("Zip");
		}
	}

	@Test
	public String fetchFirstContactID() {
		String sql = "SELECT ContactID FROM ContactAddress";
		contactID = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperContactID()).get(0);

		System.out.println("Address(ContactID) : " + contactID);

		return contactID;
	}

	@Test
	public String fetchFirstAdd() {
		String sql = "SELECT Address1 FROM ContactAddress";
		conAdd1 = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperContactAddress1()).get(0);

		System.out.println("Contact Addr1 : " + conAdd1);

		return conAdd1;
	}

	@Test
	public String fetchSecondAdd() {
		String sql = "SELECT Address2 FROM ContactAddress";
		conAdd2 = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperContactAddress2()).get(0);

		System.out.println("Contact Addr2 : " + conAdd2);

		return conAdd2;
	}

	@Test
	public String fetchContactState() {
		String sql = "SELECT ContactState FROM ContactAddress";
		conState = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperContactState()).get(0);

		System.out.println("Contact State : " + conState);

		return conState;
	}

	@Test
	public String fetchContactCity() {
		String sql = "SELECT City FROM ContactAddress";
		conCity = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperContactCity()).get(0);

		System.out.println("Contact Zip : " + conCity);

		return conCity;
	}

	@Test
	public String fetchContactZip() {
		String sql = "SELECT Zip FROM ContactAddress";
		conZip = super.jdbcSetUp().getSimpleJdbcTemplate().query(sql,
				new StringRowMapperContactZip()).get(0);

		System.out.println("Contact Description : " + conZip);

		return conZip;
	}
	
	@Test
	public void contactAddressDao()
	{
		EMail mapCont=new EMail();
		mapCont.setAddress("addressJunitTest@gmail.com");
		contactAddress.addAddress(mapCont, "4216 W Jefferson", "Buckingham/Arlington", "LA", "CA", "90032");		
	}
	
	@Test
	public void contactAddressDao1()
	{
		EMail mapCont=new EMail();
		mapCont.setAddress("addressJunitTest1@gmail.com");
		contactAddress.addAddress(mapCont, "", "Buckingham/Arlington", "LA", "CA", "90032");		
	}
	
	
	@Test
	public void contactAddressDao2()
	{
		EMail mapCont=new EMail();
		mapCont.setAddress("addressJunitTest2@gmail.com");
		contactAddress.addAddress(mapCont, "4216 W Jefferson", "", "LA", "CA", "90032");		
	}
	
	
	@Test
	public void contactAddressDao3()
	{
		EMail mapCont=new EMail();
		mapCont.setAddress("addressJunitTest3@gmail.com");
		contactAddress.addAddress(mapCont, "4216 W Jefferson", "Buckingham/Arlington", "", "CA", "90032");		
	}
	
	
	@Test
	public void contactAddressDao4()
	{
		EMail mapCont=new EMail();
		mapCont.setAddress("addressJunitTest4@gmail.com");
		contactAddress.addAddress(mapCont, "4216 W Jefferson", "Buckingham/Arlington", "LA", "", "90032");		
	}
	
	
	@Test
	public void contactAddressDao5()
	{
		EMail mapCont=new EMail();
		mapCont.setAddress("addressJunitTest5@gmail.com");
		contactAddress.addAddress(mapCont, "4216 W Jefferson", "Buckingham/Arlington", "LA", "CA", "");		
	}
	
	@After
	public void tearDown() {

		contactID = null;
		conAdd1 = null;
		conAdd2 = null;
		conState = null;
		conCity = null;
		conZip = null;

	}

}
