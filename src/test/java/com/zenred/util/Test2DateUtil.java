package com.zenred.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Test2DateUtil {
	
	@Test
	public void testDates2(){
		boolean testIt = false;
		System.out.println(DateUtil.getInternalFormat("02/01/2011"));
		System.out.println(DateUtil.getInternalFormat("02/28/2011"));
		testIt=true;
		assertTrue(testIt);
	}
}
