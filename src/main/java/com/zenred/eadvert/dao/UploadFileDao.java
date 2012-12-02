package com.zenred.eadvert.dao;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class UploadFileDao {

	int len;
	byte[] buf = new byte[1024];

	public void copyFile(String filePath, String serverFileLoc, String fileName) {
		try {

			System.out.println("CopyFile.Java");

			// new File(serverFileLoc).mkdir();

			File originalFile = new File(filePath);

			InputStream ins = new FileInputStream(originalFile);

			File newFile = new File(serverFileLoc, fileName);

			newFile.createNewFile();

			OutputStream outs = new FileOutputStream(newFile);

			while ((len = ins.read(buf)) > 0) {
				outs.write(buf, 0, len);
			}

			ins.close();
			outs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
