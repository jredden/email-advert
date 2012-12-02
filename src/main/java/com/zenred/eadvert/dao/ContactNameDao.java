package com.zenred.eadvert.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.zenred.eadvert.model.domain.ContactName;
import com.zenred.eadvert.model.domain.EMail;

public class ContactNameDao extends AbstractJDBCDao {

	int emailID;
	String tableName = "ContactName";
	Map<String, Object> map = new HashMap<String, Object>();
	ContactName nameObj = new ContactName();

	@Transactional
	public void addNames(EMail mapCont, String firstName, String middleName,
			String lastName) {
		String getEmailID = "SELECT EmailAddressID FROM EmailAddress WHERE EmailAddress = ?";
		emailID = super.jdbcSetUp().getSimpleJdbcTemplate().queryForInt(
				getEmailID, mapCont.getAddress());

		String getContactID = "SELECT ContactID FROM Contact WHERE EmailAddressID = ?";
		int contactID = super.jdbcSetUp().getSimpleJdbcTemplate().queryForInt(
				getContactID, emailID);

		if ((firstName == null || firstName.isEmpty())
				&& (middleName == null || middleName.isEmpty())
				&& (lastName == null || lastName.isEmpty())) {
			return;

		} else {
			if (!(firstName == null || firstName.isEmpty())
					&& (middleName == null || middleName.isEmpty())
					&& (lastName == null || lastName.isEmpty())) {

				nameObj.setType(nameObj.Type.firstName);
				map.put("ContactID", contactID);
				map.put("Value", firstName);
				map.put("Description", nameObj.getType().toString());
				super.jdbcInsertSetup().withTableName(tableName).usingColumns(
						"ContactID", "Value", "Description").execute(map);

			} else {
				if (!(firstName == null || firstName.isEmpty())
						&& (middleName == null || middleName.isEmpty())
						&& !(lastName == null || lastName.isEmpty())) {

					nameObj.setType(nameObj.Type.firstName);
					map.put("ContactID", contactID);
					map.put("Value", firstName);
					map.put("Description", nameObj.getType().toString());
					super.jdbcInsertSetup().withTableName(tableName)
							.usingColumns("ContactID", "Value", "Description")
							.execute(map);

					nameObj.setType(nameObj.Type.lastName);
					map.put("ContactID", contactID);
					map.put("Value", lastName);
					map.put("Description", nameObj.getType().toString());
					super.jdbcInsertSetup().withTableName(tableName)
							.usingColumns("ContactID", "Value", "Description")
							.execute(map);

				} else {
					if (!(firstName == null || firstName.isEmpty())
							&& !(middleName == null || middleName.isEmpty())
							&& (lastName == null || lastName.isEmpty())) {

						nameObj.setType(nameObj.Type.firstName);
						map.put("ContactID", contactID);
						map.put("Value", firstName);
						map.put("Description", nameObj.getType().toString());
						super.jdbcInsertSetup().withTableName(tableName)
								.usingColumns("ContactID", "Value",
										"Description").execute(map);

						nameObj.setType(nameObj.Type.middleName);
						map.put("ContactID", contactID);
						map.put("Value", middleName);
						map.put("Description", nameObj.getType().toString());
						super.jdbcInsertSetup().withTableName(tableName)
								.usingColumns("ContactID", "Value",
										"Description").execute(map);
					} else {
						if ((firstName == null || firstName.isEmpty())
								&& !(middleName == null || middleName.isEmpty())
								&& !(lastName == null || lastName.isEmpty())) {
							nameObj.setType(nameObj.Type.middleName);
							map.put("ContactID", contactID);
							map.put("Value", middleName);
							map
									.put("Description", nameObj.getType()
											.toString());
							super.jdbcInsertSetup().withTableName(tableName)
									.usingColumns("ContactID", "Value",
											"Description").execute(map);

							nameObj.setType(nameObj.Type.lastName);
							map.put("ContactID", contactID);
							map.put("Value", lastName);
							map
									.put("Description", nameObj.getType()
											.toString());
							super.jdbcInsertSetup().withTableName(tableName)
									.usingColumns("ContactID", "Value",
											"Description").execute(map);
						} else {
							if ((firstName == null || firstName.isEmpty())
									&& !(middleName == null || middleName
											.isEmpty())
									&& (lastName == null || lastName.isEmpty())) {
								nameObj.setType(nameObj.Type.middleName);
								map.put("ContactID", contactID);
								map.put("Value", middleName);
								map.put("Description", nameObj.getType()
										.toString());
								super.jdbcInsertSetup()
										.withTableName(tableName).usingColumns(
												"ContactID", "Value",
												"Description").execute(map);

							} else {
								if ((firstName == null || firstName.isEmpty())
										&& (middleName == null || middleName
												.isEmpty())
										&& !(lastName == null || lastName
												.isEmpty())) {
									nameObj.setType(nameObj.Type.lastName);
									map.put("ContactID", contactID);
									map.put("Value", lastName);
									map.put("Description", nameObj.getType()
											.toString());
									super.jdbcInsertSetup().withTableName(
											tableName)
											.usingColumns("ContactID", "Value",
													"Description").execute(map);
								} else {
									if (!(firstName == null || firstName
											.isEmpty())
											&& (middleName == null || middleName
													.isEmpty())
											&& !(lastName == null || lastName
													.isEmpty())) {
										nameObj.setType(nameObj.Type.firstName);
										map.put("ContactID", contactID);
										map.put("Value", firstName);
										map.put("Description", nameObj
												.getType().toString());
										super.jdbcInsertSetup().withTableName(
												tableName).usingColumns(
												"ContactID", "Value",
												"Description").execute(map);

										nameObj.setType(nameObj.Type.lastName);
										map.put("ContactID", contactID);
										map.put("Value", lastName);
										map.put("Description", nameObj
												.getType().toString());
										super.jdbcInsertSetup().withTableName(
												tableName).usingColumns(
												"ContactID", "Value",
												"Description").execute(map);
									} else {
										if (!(firstName == null || firstName
												.isEmpty())
												&& !(middleName == null || middleName
														.isEmpty())
												&& !(lastName == null || lastName
														.isEmpty())) {
											nameObj
													.setType(nameObj.Type.firstName);
											map.put("ContactID", contactID);
											map.put("Value", firstName);
											map.put("Description", nameObj
													.getType().toString());

											super.jdbcInsertSetup()
													.withTableName(tableName)
													.usingColumns("ContactID",
															"Value",
															"Description")
													.execute(map);

											nameObj
													.setType(nameObj.Type.middleName);
											map.put("ContactID", contactID);
											map.put("Value", middleName);
											map.put("Description", nameObj
													.getType().toString());

											super.jdbcInsertSetup()
													.withTableName(tableName)
													.usingColumns("ContactID",
															"Value",
															"Description")

													.execute(map);

											nameObj
													.setType(nameObj.Type.lastName);
											map.put("ContactID", contactID);
											map.put("Value", lastName);
											map.put("Description", nameObj
													.getType().toString());

											super.jdbcInsertSetup()
													.withTableName(tableName)
													.usingColumns("ContactID",
															"Value",
															"Description")
													.execute(map);
										}

									}
								}
							}
						}
					}
				}
			}
		}

	}
}
