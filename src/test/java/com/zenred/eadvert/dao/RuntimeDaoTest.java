package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.exception.DataAccessException;

public class RuntimeDaoTest {
	
	private RuntimeDao runtimeDao;
	
	@Before
	public void setUp(){
		runtimeDao = new RuntimeDao();
	}

	@Test
	public void genQAEmailsTest() {
		boolean doit = false;
		try {
			List<String> emailGenList = runtimeDao.executeNativeCommand(
					"/home/email_advert/webapps/email_advert/_sh/gen_email.sh",
					"/home/email_advert/userfiles/qa/mumblesOne");
			for (String email : emailGenList) {
				System.out.println(email);
			}
			doit = true;
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
		assertTrue(doit);
	}
	@Test
	public void genQAEmails2Test() {
		boolean doit = false;
		try {
			List<String> emailGenList = runtimeDao.executeNativeCommand(
					"/home/email_advert/webapps/email_advert/_sh/gen_email.sh",
					"/home/email_advert/userfiles/qa/varpevw");
			for (String email : emailGenList) {
				System.out.println(email);
			}
			doit = true;
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
		assertTrue(doit);
	}}
