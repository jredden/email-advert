package com.zenred.eadvert.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.zenred.eadvert.model.domain.EMail;

@Transactional
public class ContactAddressDao extends AbstractJDBCDao {

	int emailID;
	String tableName = "ContactAddress";
	Map<String, Object> map = new HashMap<String, Object>();

	public void addAddress(EMail mapCont, String address1, String address2,
			String city, String state, String zip) {
		String getEmailID = "SELECT EmailAddressID FROM EmailAddress WHERE EmailAddress = ?";
		emailID = super.jdbcSetUp().getSimpleJdbcTemplate().queryForInt(
				getEmailID, mapCont.getAddress());

		String getContactID = "SELECT ContactID FROM Contact WHERE EmailAddressID = ?";
		int contactID = super.jdbcSetUp().getSimpleJdbcTemplate().queryForInt(
				getContactID, emailID);

		if ((address1 == null || address1.isEmpty())
				&& (address2 == null || address2.isEmpty())
				&& (city == null || city.isEmpty())
				&& (state == null || state.isEmpty())
				&& (zip == null || zip.isEmpty())) {
			return;
		} else {
			if (!(address1 == null || address1.isEmpty())
					&& (address2 == null || address2.isEmpty())
					&& (city == null || city.isEmpty())
					&& (state == null || state.isEmpty())
					&& (zip == null || zip.isEmpty())) {
				map.put("ContactID", contactID);
				map.put("Address1", address1);
				super.jdbcInsertSetup().withTableName(tableName).usingColumns(
						"ContactID", "Address1").execute(map);

			} else {
				if (!(address1 == null || address1.isEmpty())
						&& !(address2 == null || address2.isEmpty())
						&& (city == null || city.isEmpty())
						&& (state == null || state.isEmpty())
						&& (zip == null || zip.isEmpty())) {
					map.put("ContactID", contactID);
					map.put("Address1", address1);
					map.put("Address2", address2);
					super.jdbcInsertSetup().withTableName(tableName)
							.usingColumns("ContactID", "Address1", "Address2")
							.execute(map);
				} else {
					if (!(address1 == null || address1.isEmpty())
							&& !(address2 == null || address2.isEmpty())
							&& !(city == null || city.isEmpty())
							&& (state == null || state.isEmpty())
							&& (zip == null || zip.isEmpty())) {
						map.put("ContactID", contactID);
						map.put("Address1", address1);
						map.put("Address2", address2);
						map.put("City", city);
						super.jdbcInsertSetup().withTableName(tableName)
								.usingColumns("ContactID", "Address1",
										"Address2", "City").execute(map);
					} else {
						if (!(address1 == null || address1.isEmpty())
								&& !(address2 == null || address2.isEmpty())
								&& !(city == null || city.isEmpty())
								&& !(state == null || state.isEmpty())
								&& (zip == null || zip.isEmpty())) {
							map.put("ContactID", contactID);
							map.put("Address1", address1);
							map.put("Address2", address2);
							map.put("City", city);
							map.put("ContactState", state);
							super.jdbcInsertSetup().withTableName(tableName)
									.usingColumns("ContactID", "Address1",
											"Address2", "City", "ContactState")
									.execute(map);
						} else {
							if ((address1 == null || address1.isEmpty())
									&& !(address2 == null || address2.isEmpty())
									&& (city == null || city.isEmpty())
									&& (state == null || state.isEmpty())
									&& (zip == null || zip.isEmpty())) {
								map.put("ContactID", contactID);
								map.put("Address2", address2);
								super.jdbcInsertSetup()
										.withTableName(tableName).usingColumns(
												"ContactID", "Address2")
										.execute(map);
							} else {
								if ((address1 == null || address1.isEmpty())
										&& !(address2 == null || address2
												.isEmpty())
										&& !(city == null || city.isEmpty())
										&& (state == null || state.isEmpty())
										&& (zip == null || zip.isEmpty())) {
									map.put("ContactID", contactID);
									map.put("Address2", address2);
									map.put("City", city);
									super.jdbcInsertSetup().withTableName(
											tableName).usingColumns(
											"ContactID", "Address2", "City")
											.execute(map);
								} else {
									if ((address1 == null || address1.isEmpty())
											&& !(address2 == null || address2
													.isEmpty())
											&& !(city == null || city.isEmpty())
											&& !(state == null || state
													.isEmpty())
											&& (zip == null || zip.isEmpty())) {
										map.put("ContactID", contactID);
										map.put("Address2", address2);
										map.put("City", city);
										map.put("ContactState", state);
										super.jdbcInsertSetup().withTableName(
												tableName).usingColumns(
												"ContactID", "Address2",
												"City", "ContactState")
												.execute(map);
									} else {
										if ((address1 == null || address1
												.isEmpty())
												&& !(address2 == null || address2
														.isEmpty())
												&& !(city == null || city
														.isEmpty())
												&& !(state == null || state
														.isEmpty())
												&& !(zip == null || zip
														.isEmpty())) {
											map.put("ContactID", contactID);
											map.put("Address2", address2);
											map.put("City", city);
											map.put("ContactState", state);
											map.put("Zip", zip);
											super.jdbcInsertSetup()
													.withTableName(tableName)
													.usingColumns("ContactID",
															"Address2", "City",
															"ContactState",
															"Zip").execute(map);
										} else {
											if ((address1 == null || address1
													.isEmpty())
													&& (address2 == null || address2
															.isEmpty())
													&& !(city == null || city
															.isEmpty())
													&& (state == null || state
															.isEmpty())
													&& (zip == null || zip
															.isEmpty())) {
												map.put("ContactID", contactID);
												map.put("City", city);

												super.jdbcInsertSetup()
														.withTableName(
																tableName)
														.usingColumns(
																"ContactID",
																"City")
														.execute(map);
											} else {
												if ((address1 == null || address1
														.isEmpty())
														&& (address2 == null || address2
																.isEmpty())
														&& !(city == null || city
																.isEmpty())
														&& !(state == null || state
																.isEmpty())
														&& (zip == null || zip
																.isEmpty())) {
													map.put("ContactID",
															contactID);
													map.put("City", city);
													map.put("ContactState",
															state);

													super
															.jdbcInsertSetup()
															.withTableName(
																	tableName)
															.usingColumns(
																	"ContactID",
																	"City",
																	"ContactState")
															.execute(map);
												} else {
													if ((address1 == null || address1
															.isEmpty())
															&& (address2 == null || address2
																	.isEmpty())
															&& !(city == null || city
																	.isEmpty())
															&& !(state == null || state
																	.isEmpty())
															&& !(zip == null || zip
																	.isEmpty())) {
														map.put("ContactID",
																contactID);
														map.put("City", city);
														map.put("ContactState",
																state);
														map.put("Zip", zip);
														super
																.jdbcInsertSetup()
																.withTableName(
																		tableName)
																.usingColumns(
																		"ContactID",
																		"City",
																		"ContactState",
																		"Zip")
																.execute(map);
													} else {
														if ((address1 == null || address1
																.isEmpty())
																&& (address2 == null || address2
																		.isEmpty())
																&& (city == null || city
																		.isEmpty())
																&& !(state == null || state
																		.isEmpty())
																&& (zip == null || zip
																		.isEmpty())) {
															map
																	.put(
																			"ContactID",
																			contactID);

															map
																	.put(
																			"ContactState",
																			state);

															super
																	.jdbcInsertSetup()
																	.withTableName(
																			tableName)
																	.usingColumns(
																			"ContactID",
																			"ContactState")
																	.execute(
																			map);
														} else {
															if ((address1 == null || address1
																	.isEmpty())
																	&& (address2 == null || address2
																			.isEmpty())
																	&& (city == null || city
																			.isEmpty())
																	&& !(state == null || state
																			.isEmpty())
																	&& !(zip == null || zip
																			.isEmpty())) {
																map
																		.put(
																				"ContactID",
																				contactID);
																map
																		.put(
																				"ContactState",
																				state);
																map.put("Zip",
																		zip);
																super
																		.jdbcInsertSetup()
																		.withTableName(
																				tableName)
																		.usingColumns(
																				"ContactID",
																				"ContactState",
																				"Zip")
																		.execute(
																				map);
															} else {
																if (!(address1 == null || address1
																		.isEmpty())
																		&& (address2 == null || address2
																				.isEmpty())
																		&& (city == null || city
																				.isEmpty())
																		&& !(state == null || state
																				.isEmpty())
																		&& (zip == null || zip
																				.isEmpty())) {
																	map
																			.put(
																					"ContactID",
																					contactID);
																	map
																			.put(
																					"Address1",
																					address1);
																	map
																			.put(
																					"ContactState",
																					state);

																	super
																			.jdbcInsertSetup()
																			.withTableName(
																					tableName)
																			.usingColumns(
																					"ContactID",
																					"Address1",
																					"ContactState")
																			.execute(
																					map);
																} else {
																	if (!(address1 == null || address1
																			.isEmpty())
																			&& (address2 == null || address2
																					.isEmpty())
																			&& !(city == null || city
																					.isEmpty())
																			&& !(state == null || state
																					.isEmpty())
																			&& (zip == null || zip
																					.isEmpty())) {
																		map
																				.put(
																						"ContactID",
																						contactID);
																		map
																				.put(
																						"Address1",
																						address1);
																		map
																				.put(
																						"City",
																						city);
																		map
																				.put(
																						"ContactState",
																						state);

																		super
																				.jdbcInsertSetup()
																				.withTableName(
																						tableName)
																				.usingColumns(
																						"ContactID",
																						"Address1",
																						"City",
																						"ContactState")
																				.execute(
																						map);
																	} else {
																		if (!(address1 == null || address1
																				.isEmpty())
																				&& (address2 == null || address2
																						.isEmpty())
																				&& !(city == null || city
																						.isEmpty())
																				&& !(state == null || state
																						.isEmpty())
																				&& !(zip == null || zip
																						.isEmpty())) {
																			map
																					.put(
																							"ContactID",
																							contactID);
																			map
																					.put(
																							"Address1",
																							address1);
																			map
																					.put(
																							"City",
																							city);
																			map
																					.put(
																							"ContactState",
																							state);
																			map
																					.put(
																							"Zip",
																							zip);
																			super
																					.jdbcInsertSetup()
																					.withTableName(
																							tableName)
																					.usingColumns(
																							"ContactID",
																							"Address1",
																							"City",
																							"ContactState",
																							"Zip")
																					.execute(
																							map);
																		} else {
																			if (!(address1 == null || address1
																					.isEmpty())
																					&& (address2 == null || address2
																							.isEmpty())
																					&& (city == null || city
																							.isEmpty())
																					&& !(state == null || state
																							.isEmpty())
																					&& (zip == null || zip
																							.isEmpty())) {
																				map
																						.put(
																								"ContactID",
																								contactID);
																				map
																						.put(
																								"Address1",
																								address1);

																				map
																						.put(
																								"ContactState",
																								state);

																				super
																						.jdbcInsertSetup()
																						.withTableName(
																								tableName)
																						.usingColumns(
																								"ContactID",
																								"Address1",
																								"ContactState")
																						.execute(
																								map);
																			} else {
																				if (!(address1 == null || address1
																						.isEmpty())
																						&& (address2 == null || address2
																								.isEmpty())
																						&& (city == null || city
																								.isEmpty())
																						&& !(state == null || state
																								.isEmpty())
																						&& !(zip == null || zip
																								.isEmpty())) {
																					map
																							.put(
																									"ContactID",
																									contactID);
																					map
																							.put(
																									"Address1",
																									address1);

																					map
																							.put(
																									"ContactState",
																									state);
																					map
																							.put(
																									"Zip",
																									zip);
																					super
																							.jdbcInsertSetup()
																							.withTableName(
																									tableName)
																							.usingColumns(
																									"ContactID",
																									"Address1",
																									"ContactState",
																									"Zip")
																							.execute(
																									map);
																				} else {
																					if (!(address1 == null || address1
																							.isEmpty())
																							&& !(address2 == null || address2
																									.isEmpty())
																							&& (city == null || city
																									.isEmpty())
																							&& !(state == null || state
																									.isEmpty())
																							&& !(zip == null || zip
																									.isEmpty())) {
																						map
																								.put(
																										"ContactID",
																										contactID);
																						map
																								.put(
																										"Address1",
																										address1);
																						map
																								.put(
																										"Address2",
																										address2);
																						map
																								.put(
																										"ContactState",
																										state);
																						map
																								.put(
																										"Zip",
																										zip);
																						super
																								.jdbcInsertSetup()
																								.withTableName(
																										tableName)
																								.usingColumns(
																										"ContactID",
																										"Address1",
																										"Address2",
																										"ContactState",
																										"Zip")
																								.execute(
																										map);

																					} else {
																						if (!(address1 == null || address1
																								.isEmpty())
																								&& !(address2 == null || address2
																										.isEmpty())
																								&& (city == null || city
																										.isEmpty())
																								&& (state == null || state
																										.isEmpty())
																								&& !(zip == null || zip
																										.isEmpty())) {
																							map
																									.put(
																											"ContactID",
																											contactID);
																							map
																									.put(
																											"Address1",
																											address1);
																							map
																									.put(
																											"Address2",
																											address2);

																							map
																									.put(
																											"Zip",
																											zip);
																							super
																									.jdbcInsertSetup()
																									.withTableName(
																											tableName)
																									.usingColumns(
																											"ContactID",
																											"Address1",
																											"Address2",
																											"Zip")
																									.execute(
																											map);
																						} else {
																							if (!(address1 == null || address1
																									.isEmpty())
																									&& !(address2 == null || address2
																											.isEmpty())
																									&& (city == null || city
																											.isEmpty())
																									&& !(state == null || state
																											.isEmpty())
																									&& (zip == null || zip
																											.isEmpty())) {
																								map
																										.put(
																												"ContactID",
																												contactID);
																								map
																										.put(
																												"Address1",
																												address1);
																								map
																										.put(
																												"Address2",
																												address2);
																								map
																										.put(
																												"ContactState",
																												state);

																								super
																										.jdbcInsertSetup()
																										.withTableName(
																												tableName)
																										.usingColumns(
																												"ContactID",
																												"Address1",
																												"Address2",
																												"ContactState")
																										.execute(
																												map);
																							} else {
																								if (!(address1 == null || address1
																										.isEmpty())
																										&& !(address2 == null || address2
																												.isEmpty())
																										&& !(city == null || city
																												.isEmpty())
																										&& (state == null || state
																												.isEmpty())
																										&& !(zip == null || zip
																												.isEmpty())) {
																									map
																											.put(
																													"ContactID",
																													contactID);
																									map
																											.put(
																													"Address1",
																													address1);
																									map
																											.put(
																													"Address2",
																													address2);
																									map
																											.put(
																													"City",
																													city);
																									map
																											.put(
																													"Zip",
																													zip);
																									super
																											.jdbcInsertSetup()
																											.withTableName(
																													tableName)
																											.usingColumns(
																													"ContactID",
																													"Address1",
																													"Address2",
																													"City",
																													"Zip")
																											.execute(
																													map);
																								} else {
																									if (!(address1 == null || address1
																											.isEmpty())
																											&& !(address2 == null || address2
																													.isEmpty())
																											&& !(city == null || city
																													.isEmpty())
																											&& !(state == null || state
																													.isEmpty())
																											&& !(zip == null || zip
																													.isEmpty())) {
																										map
																												.put(
																														"ContactID",
																														contactID);
																										map
																												.put(
																														"Address1",
																														address1);
																										map
																												.put(
																														"Address2",
																														address2);
																										map
																												.put(
																														"City",
																														city);
																										map
																												.put(
																														"ContactState",
																														state);
																										map
																												.put(
																														"Zip",
																														zip);
																										super
																												.jdbcInsertSetup()
																												.withTableName(
																														tableName)
																												.usingColumns(
																														"ContactID",
																														"Address1",
																														"Address2",
																														"City",
																														"ContactState",
																														"Zip")
																												.execute(
																														map);
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
			}

		}

	}

}
