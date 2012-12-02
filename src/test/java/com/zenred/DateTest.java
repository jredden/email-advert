package com.zenred;

import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DateTest {
	
	@Test
	public void testDate0(){
		DateFormat dateFormatTest0 = new SimpleDateFormat("E");
		Date test = null;
		try {
			test = dateFormatTest0.parse("Mon");
		} catch (ParseException pee) {
			pee.printStackTrace();
		}
		System.out.println(test.toString());
		assertTrue(test.toString().length() > 0);
	}
	@Test
	public void testDate1(){
		DateFormat dateFormatTest0 = new SimpleDateFormat("E MMM");
		Date test = null;
		try {
			test = dateFormatTest0.parse("Mon Nov");
		} catch (ParseException pee) {
			pee.printStackTrace();
		}
		System.out.println(test.toString());
		assertTrue(test.toString().length() > 0);
	}
	@Test
	public void testDate2(){
		DateFormat dateFormatTest0 = new SimpleDateFormat("E, dd");
		Date test = null;
		try {
			test = dateFormatTest0.parse("Mon, 22");
		} catch (ParseException pee) {
			pee.printStackTrace();
		}
		System.out.println(test.toString());
		assertTrue(test.toString().length() > 0);
	}
	@Test
	public void testDate3(){
		DateFormat dateFormatTest0 = new SimpleDateFormat("E, dd MMM");
		Date test = null;
		try {
			test = dateFormatTest0.parse("Mon, 22 Nov");
		} catch (ParseException pee) {
			pee.printStackTrace();
		}
		System.out.println(test.toString());
		assertTrue(test.toString().length() > 0);
	}
	@Test
	public void testDate4(){
		DateFormat dateFormatTest0 = new SimpleDateFormat("E, dd MMM yyyy");
		Date test = null;
		try {
			test = dateFormatTest0.parse("Mon, 22 Nov 2010");
		} catch (ParseException pee) {
			pee.printStackTrace();
		}
		System.out.println(test.toString());
		assertTrue(test.toString().length() > 0);
	}
	@Test
	public void testDate5(){
		DateFormat dateFormatTest0 = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
		Date test = null;
		try {
			test = dateFormatTest0.parse("Thu, 11 Nov 2010 10:36:39");
		} catch (ParseException pee) {
			pee.printStackTrace();
		}
		System.out.println(test.toString());
		assertTrue(test.toString().length() > 0);
	}

//
}
