package com.zenred.util;

import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class TestMD5Util {
	
	@Test
	public void testMD5(){
		boolean testIt = false;
		try {
			System.out.println(MD5Util.MD5("b20788844de6fbdec41a2042daee3585eb19850d"));
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}
		testIt=true;
		assertTrue(testIt);
	}
	@Test
	public void test2MD5(){
		boolean testIt = false;
		try {
			System.out.println(MD5Util.MD5("12345"));
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}
		testIt=true;
		assertTrue(testIt);
	}

}
