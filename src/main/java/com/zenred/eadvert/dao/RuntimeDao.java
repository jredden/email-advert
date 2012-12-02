package com.zenred.eadvert.dao;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.zenred.eadvert.exception.DataAccessException;
import com.zenred.util.MD5Util;

public class RuntimeDao {
	
	public List<String> executeNativeCommand(String command, String folder) throws DataAccessException{
		List<String> theEmailList = new ArrayList<String>();
		
		Process email_gen_proc = null;
		
		try {
			email_gen_proc = Runtime.getRuntime().exec(command + " " + folder);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new DataAccessException(ioe.getMessage());
		}
		try {
			email_gen_proc.waitFor();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
			throw new DataAccessException(ie.getMessage());
		}
		int exitCode = email_gen_proc.exitValue();
		if (exitCode == 0) {
			System.out.println("successfully executed " + command + " " + folder);
		} else {
			throw new DataAccessException(command + " " + folder + " failed");
		}

		DataInputStream email_gen_in = new DataInputStream(
                email_gen_proc.getInputStream());
		String email_str = "";
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(email_gen_in));
			while ((email_str = reader.readLine()) != null) {;
				theEmailList.add(email_str);
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return theEmailList;
	}
	
	public String executeNativeEmailer(String command) throws DataAccessException{
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(command);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		try {
			process.waitFor();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
//			throw new DataAccessException(ie.getMessage());
			System.err.println("failure to send email:"+command);
			return "failure to send email:"+command;
		}
		int exitCode =process.exitValue();
		if (exitCode == 0) {
			System.out.println("successfully executed " + command);
		} else {
//			throw new DataAccessException(command  + " failed");
			System.err.println("failure to send email:"+command);
			return "failure to send email:"+command;
		}
		DataInputStream email_send_in = new DataInputStream(process.getInputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(email_send_in));
		char [] char_buffer= new char[256];
		try {
			reader.read(char_buffer);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		StringBuffer _sbuf = new StringBuffer();
		for (char _char : char_buffer){
			_sbuf.append(_char);
		}
		return _sbuf.toString();
	}

}
