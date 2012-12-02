package com.zenred.eadvert.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UploadFileDaoTest {

	UploadFileDao uploadDao;

	String serverFileLoc = "C:/Documents and Settings/Prashant/Desktop/"
			+ "Dashboard/email_advert/JUnitTestLoc";

	String fileName = "JunitExcel.xls";
	String filePath = "C:/Documents and Settings/Prashant/Desktop/Dashboard/email_advert/src/test/data/myFile.xls";

	@Before
	public void setUp() {
		uploadDao.copyFile(filePath, serverFileLoc, fileName);
	}

	@Test
	public void writeContents() {
		System.out.println("File Uploaded Successfully to Server");
	}

	@After
	public void tearDown() {
		serverFileLoc = null;
		fileName = null;
		filePath = null;
	}
}
