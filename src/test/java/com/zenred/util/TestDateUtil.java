package com.zenred.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestDateUtil {
	
	@Test
	public void testDates(){
		boolean testIt = false;
		System.out.println(DateUtil.getToday());
		System.out.println(DateUtil.getOurFormatToday());
		testIt=true;
		assertTrue(testIt);
	}
}
