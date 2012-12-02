package com.zenred.eadvert.dao;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExcelReaderDaoTest {

	ExcelReaderDao excelDao;

	String[][] excelContents;

	// String file = "C:/Documents and Settings/Prashant"
	// + "/Desktop/Dashboard/email_advert/src/test/data/myFile.xls";

	String file = "/home/jredden/workspace/email_advert/src/test/data/myFile.xls";

	Map<String, List<String>> testContents;

	@Before
	public void setUp() {
		excelDao = new ExcelReaderDao();
		this.testContents = excelDao.excelReader(file);
	}

	@Test
	public void excelTest() {

		List<String> e = this.testContents.get("EmailAddress");

		for (String s : e) {

			if (null == s || s.isEmpty()) {
				System.out.println("empty cell");
				continue;
			} else {
				System.out.println("Email Address " + s);

			}

		}

		List<String> e1 = this.testContents.get("FirstName");

		for (String s : e1) {

			if (null == s || s.isEmpty()) {
				System.out.println("empty cell");
				continue;
			} else {
				System.out.println("First Name: " + s);

			}

		}

		List<String> e2 = this.testContents.get("LastName");

		for (String s : e2) {

			if (null == s || s.isEmpty()) {
				System.out.println("empty cell");
				continue;
			} else {
				System.out.println("Last Name: " + s);

			}

		}

		List<String> e3 = this.testContents.get("MiddleName");

		for (String s : e3) {

			if (null == s || s.isEmpty()) {
				System.out.println("empty cell");
				continue;
			} else {
				System.out.println("Middle Name: " + s);

			}

		}

		List<String> e4 = this.testContents.get("AddressList1");

		for (String s : e4) {

			if (null == s || s.isEmpty()) {
				System.out.println("empty cell");
				continue;
			} else {
				System.out.println("AddressList1 " + s);

			}

		}

		List<String> e5 = this.testContents.get("AddressList2");

		for (String s : e5) {

			if (null == s || s.isEmpty()) {
				System.out.println("empty cell");
				continue;
			} else {
				System.out.println("AddressList2 " + s);

			}

		}

		List<String> e6 = this.testContents.get("City");

		for (String s : e6) {

			if (null == s || s.isEmpty()) {
				System.out.println("empty cell");
				continue;
			} else {
				System.out.println("City " + s);

			}

		}

		List<String> e7 = this.testContents.get("State");

		for (String s : e7) {

			if (null == s || s.isEmpty()) {
				System.out.println("empty cell");
				continue;
			} else {
				System.out.println("State " + s);

			}

		}

		List<String> e8 = this.testContents.get("Zip");

		for (String s : e8) {

			if (null == s || s.isEmpty()) {
				System.out.println("empty cell");
				continue;
			} else {
				System.out.println("Zip " + s);

			}

		}

		List<String> e9 = this.testContents.get("Phone");

		for (String s : e9) {

			if (null == s || s.isEmpty()) {
				System.out.println("empty cell");
				continue;
			} else {
				System.out.println("Email Address " + s);

			}

		}

		List<String> e10 = this.testContents.get("MobilePhone");

		for (String s : e10) {

			if (null == s || s.isEmpty()) {
				System.out.println("empty cell");
				continue;
			} else {
				System.out.println("Mobile Phone " + s);

			}

		}

	}

	@After
	public void tearDown() {

		excelDao = null;
		this.testContents = null;

	}

}
