package com.zenred.eadvert.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.zenred.eadvert.exception.DataAccessException;

public class FileDaoTest {

	private FileIODao fileIODao;

	@Before
	public void setUp() {
		fileIODao = new FileIODao();
		fileIODao.setBufferSize("10000");
	}

	@Test
	public void readAndWriteURITest() {
		boolean doit = false;
		try {
			byte[] buffer = fileIODao
					.readFromURL("http://www.email_advert.com/_m/header.jpg");
			fileIODao.writeByteFileUsingURI("/tmp/header.jpg", buffer);
			doit = true;
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
		assertTrue(doit);
	}

	@Test
	public void readCharTest() {
		boolean doit = false;
		char[] result = null;
		try {
			result = fileIODao.readCharFileUsingURI("/tmp/header.jpg");
			doit = true;
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
		int count = 0;
		StringBuffer buf = new StringBuffer();
		System.out.println("----------- chars ------------");
		for (char thechar : result) {
			buf.append("[" + ((short) thechar) + "]");
			++count;
			count %= 32;
			if (count == 0) {
				System.out.println(buf.toString());
				buf = new StringBuffer();
			}
		}
		assertTrue(doit);
	}

	@Test
	public void copy() {
		boolean doit = false;
		try {
			fileIODao.copyChar("/tmp/header.jpg", "/tmp/foo.jpg");
			doit = true;
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
		assertTrue(doit);
	}

	@Test
	public void readByte() {
		boolean doIt = false;
		byte[] result = null;
		try {
			result = fileIODao.readByteFileUsingURI("/tmp/header.jpg");
			doIt = true;
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
		int count = 0;
		StringBuffer buf = new StringBuffer();
		System.out.println("----------- bytes ------------");
		for (byte thebyte : result) {
			buf.append("[" + (thebyte) + "]");
			++count;
			count %= 32;
			if (count == 0) {
				System.out.println(buf.toString());
				buf = new StringBuffer();
			}
		}
		assertTrue(doIt);
	}

	@Test
	public void copyBytes() {
		boolean doit = false;
		try {
			fileIODao.copyByte("/tmp/header.jpg", "/tmp/bar.jpg");
			doit = true;
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
		assertTrue(doit);
	}

}
