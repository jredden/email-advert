package com.zenred.eadvert.dao;

import java.util.Properties;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public abstract class AbstractJDBCDao {

	public static SimpleJdbcDaoSupport jdbcSetUp() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		/*
		 * driverManagerDataSource
		 * .setUrl("jdbc:mysql://sherpadb.matrix.cc/email_advert");
		 * driverManagerDataSource.setUsername("jredden");
		 * driverManagerDataSource.setPassword("nCXL3O2GVAwLBJnT");
		 */

		driverManagerDataSource
				.setUrl("jdbc:mysql://sherpadb.matrix.cc/email_advert");
		driverManagerDataSource.setUsername("email_advert");
		driverManagerDataSource
				.setPassword("d017bde08a87d5324de6882e8cd97d2dc94ba684");

		Properties connectionProperties = new Properties();
		connectionProperties.setProperty("noAccessToProcedureBodies", "true"); // *sigh*.
																				// to
																				// allow
																				// sp's
																				// to
																				// run
		driverManagerDataSource.setConnectionProperties(connectionProperties);
		SimpleJdbcDaoSupport simpleJdbcDaoSupport = new SimpleJdbcDaoSupport();
		simpleJdbcDaoSupport.setDataSource(driverManagerDataSource);
		return simpleJdbcDaoSupport;
	}

	public static SimpleJdbcInsert jdbcInsertSetup() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		/*
		 * driverManagerDataSource
		 * .setUrl("jdbc:mysql://sherpadb.matrix.cc/email_advert");
		 * driverManagerDataSource.setUsername("jredden");
		 * driverManagerDataSource.setPassword("nCXL3O2GVAwLBJnT");
		 */

		driverManagerDataSource
				.setUrl("jdbc:mysql://sherpadb.matrix.cc/email_advert");
		driverManagerDataSource.setUsername("email_advert");
		driverManagerDataSource
				.setPassword("d017bde08a87d5324de6882e8cd97d2dc94ba684");
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(
				driverManagerDataSource);
		return simpleJdbcInsert;
	}

}
