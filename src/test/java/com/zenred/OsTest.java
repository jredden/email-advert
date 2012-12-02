package com.zenred;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OsTest {
	
	@Test
	public void osTest(){
		String operatingSystem = System.getProperty("os.name");
		System.out.println("operatingSystem:"+operatingSystem);
		assertTrue(operatingSystem.length()>0);
	}

}
