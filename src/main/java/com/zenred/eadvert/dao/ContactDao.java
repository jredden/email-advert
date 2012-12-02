package com.zenred.eadvert.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.zenred.eadvert.model.domain.Contact;
import com.zenred.eadvert.model.domain.EMail;

public class ContactDao extends AbstractJDBCDao {

	int emailID;
	String tableName = "Contact";
	Map<String, Object> map = new HashMap<String, Object>();

	@Transactional
	public void copyToContact(EMail emailAddress, Contact conObj) {

		String getEmailID = "SELECT EmailAddressID FROM EmailAddress WHERE EmailAddress = ?";
		emailID = super.jdbcSetUp().getSimpleJdbcTemplate().queryForInt(
				getEmailID, emailAddress.getAddress());

		map.put("EmailAddressID", emailID);
		map.put("ContactType", conObj.getType().toString());
		super.jdbcInsertSetup().withTableName(tableName).usingColumns(
				"EmailAddressID", "ContactType").execute(map);

	}

}
