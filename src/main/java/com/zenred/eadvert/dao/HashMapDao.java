package com.zenred.eadvert.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HashMapDao {

	public HashMapDao(String[][] excelContents) {

		Map<String, List<String>> ssmap = new HashMap<String, List<String>>();
		List<String> emailList = new ArrayList<String>();
		int col1 = 0;

		Set s = ssmap.entrySet();

		for (int row = 1; row < 20; row++) {

			emailList.add(excelContents[col1][row]);

			// System.out.println("Excel Contents String Array = " +
			// excelContents[col][row]);
		}

		ssmap.put("EmailAddress", emailList);

		col1++;

		List<String> firstName = new ArrayList<String>();

		for (int row = 1; row < 20; row++) {

			firstName.add(excelContents[col1][row]);
		}

		ssmap.put("FirstName", firstName);

		col1++;

		List<String> middleName = new ArrayList<String>();

		for (int row = 1; row < 20; row++) {

			middleName.add(excelContents[col1][row]);
		}

		ssmap.put("MiddleName", middleName);

		col1++;

		List<String> lastName = new ArrayList<String>();

		for (int row = 1; row < 20; row++) {

			lastName.add(excelContents[col1][row]);
		}

		ssmap.put("LastName", lastName);

		col1++;

		List<String> address1List = new ArrayList<String>();

		for (int row = 1; row < 20; row++) {

			address1List.add(excelContents[col1][row]);
		}

		ssmap.put("AddressList1", address1List);

		col1++;

		List<String> address2List = new ArrayList<String>();

		for (int row = 1; row < 20; row++) {
			address2List.add(excelContents[col1][row]);
		}

		ssmap.put("AddressList2", address2List);

		col1++;

		List<String> cityList = new ArrayList<String>();

		for (int row = 1; row < 20; row++) {

			cityList.add(excelContents[col1][row]);
		}

		ssmap.put("City", cityList);

		col1++;

		List<String> stateList = new ArrayList<String>();

		for (int row = 1; row < 20; row++) {

			stateList.add(excelContents[col1][row]);
		}

		ssmap.put("State", stateList);

		col1++;

		List<String> zipList = new ArrayList<String>();

		for (int row = 1; row < 20; row++) {

			zipList.add(excelContents[col1][row]);
		}

		ssmap.put("Zip", zipList);

		col1++;

		List<String> phoneList = new ArrayList<String>();

		for (int row = 1; row < 20; row++) {

			phoneList.add(excelContents[col1][row]);
		}

		ssmap.put("Phone", phoneList);

		col1++;

		List<String> mobilePhoneList = new ArrayList<String>();

		for (int row = 1; row < 20; row++) {

			mobilePhoneList.add(excelContents[col1][row]);
		}

		ssmap.put("Mobile Phone", mobilePhoneList);

		// Move next key and value of HashMap by iterator
		Iterator it = s.iterator();

		while (it.hasNext()) {
			// key=value separator this by Map.Entry to get key and value
			Map.Entry m = (Map.Entry) it.next();

			// getKey is used to get key of HashMap
			String key = (String) m.getKey();

			// getValue is used to get value of key in HashMap
			ArrayList value = (ArrayList) m.getValue();

			System.out.println("Key :" + key);
			System.out.println("value :" + value);
		}

	}

}
