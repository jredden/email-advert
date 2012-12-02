package com.zenred.eadvert.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.zenred.eadvert.model.domain.EMail;

public class ContactPhoneDao extends AbstractJDBCDao {

	int emailID;
	String tableName = "ContactPhone";
	Map<String, Object> map = new HashMap<String, Object>();
	
	@Transactional
	public void addPhoneNo(EMail mapCont, String addPhone, String addMobilePhone) {

		String getEmailID = "SELECT EmailAddressID FROM EmailAddress WHERE EmailAddress = ?";
		emailID = super.jdbcSetUp().getSimpleJdbcTemplate().queryForInt(
				getEmailID, mapCont.getAddress());

		String getContactID = "SELECT ContactID FROM Contact WHERE EmailAddressID = ?";
		int contactID = super.jdbcSetUp().getSimpleJdbcTemplate().queryForInt(
				getContactID, emailID);

		if ((addPhone == null || addPhone.isEmpty())
				&& (addMobilePhone == null || addMobilePhone.isEmpty())) {
			return;

		} else {

			if (!(addPhone == null || addPhone.isEmpty())
					&& (addMobilePhone == null || addMobilePhone.isEmpty())) {

				map.put("ContactID", contactID);
				map.put("Phone", addPhone);
				super.jdbcInsertSetup().withTableName(tableName).usingColumns(
						"ContactID", "Phone").execute(map);

			} else {
				if ((addPhone == null || addPhone.isEmpty())
						&& !(addMobilePhone == null || addMobilePhone.isEmpty())) {

					map.put("ContactID", contactID);
					map.put("MobilePhone", addMobilePhone);
					super.jdbcInsertSetup().withTableName(tableName)
							.usingColumns("ContactID", "MobilePhone").execute(
									map);
				} else {

					map.put("ContactID", contactID);
					map.put("Phone", addPhone);
					map.put("MobilePhone", addMobilePhone);
					super.jdbcInsertSetup().withTableName(tableName)
							.usingColumns("ContactID", "Phone", "MobilePhone")
							.execute(map);

				}
			}

		}

	}
}
