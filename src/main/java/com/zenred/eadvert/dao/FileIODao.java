package com.zenred.eadvert.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.zenred.eadvert.exception.DataAccessException;

public class FileIODao {

	private int bufferSize;

	/**
	 * @param bufferSize
	 *            the bufferSize to set
	 */
	public void setBufferSize(String bufferSize) {
		this.bufferSize = new Integer(bufferSize).intValue();
	}

	private StringBuffer readFileUsingURIInternal(String uri, long seek)
			throws DataAccessException {

		if (!new File(uri).canRead()) {
			throw new DataAccessException("cannot access file URI:" + uri);
		}
		File file = new File(uri);
		file.setReadable(true);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			throw new DataAccessException("readFileUsingURI():" + uri);
		}
		StringBuffer _strbuf = new StringBuffer();
		try {
			while (reader.ready()) {
				reader.skip(seek);
				String _str = reader.readLine();
				_strbuf.append(_str).append("\n");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new DataAccessException("readFileUsingURI():" + uri);
		}

		return _strbuf;
	}

	public StringBuffer readFileUsingURI(String uri) throws DataAccessException {
		return readFileUsingURIInternal(uri, 0);
	}

	public StringBuffer readFileUsingURI(String uri, long seek)
			throws DataAccessException {
		return readFileUsingURIInternal(uri, seek);
	}

	public String readStringFileUsingURI(String uri) throws DataAccessException {
		return readFileUsingURI(uri).toString();
	}

	public void writeFileUsingURI(String uri, String contents)
			throws DataAccessException {
		File file = new File(uri);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ioe) {
				ioe.printStackTrace();
				throw new DataAccessException("writeFileUsingURI():" + uri);
			}
		}
		file.setWritable(true);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new DataAccessException("writeFileUsingURI():" + uri);
		}
		try {
			writer.write(contents + "\n");
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new DataAccessException("writeFileUsingURI():" + uri);
		}
		try {
			writer.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new DataAccessException("writeFileUsingURI():" + uri);
		}
		try {
			writer.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new DataAccessException("writeFileUsingURI():" + uri);
		}
	}

	public char[] readCharFileUsingURI(String uri) throws DataAccessException {
		File file = new File(uri);
		int sizeOfFile = (int) file.length();
		char[] contents = new char[sizeOfFile];
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			throw new DataAccessException("readCharFileUsingURI():" + uri);
		}
		try {
			reader.read(contents);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new DataAccessException("readCharFileUsingURI():" + uri);
		}
		try {
			reader.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return contents;
	}

	public void writeCharFileUsingURI(String uri, char[] contents)
			throws DataAccessException {
		File file = new File(uri);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ioe) {
				ioe.printStackTrace();
				throw new DataAccessException("writeFileUsingURI():" + uri);
			}
		}
		file.setWritable(true);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new DataAccessException("writeFileUsingURI():" + uri);
		}
		try {
			writer.write(contents);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new DataAccessException("writeFileUsingURI():" + uri);
		}
		try {
			writer.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new DataAccessException("writeFileUsingURI():" + uri);
		}
		try {
			writer.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new DataAccessException("writeFileUsingURI():" + uri);
		}
	}

	public byte[] readByteFileUsingURI(String uri) throws DataAccessException {
		File file = new File(uri);
		int sizeOfFile = (int) file.length();
		byte[] bytes = new byte[sizeOfFile];
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			throw new DataAccessException(fnfe.toString());
		}
		int offset = 0;
		int numRead = 0;
		try {
			while (offset < bytes.length
					&& (numRead = inputStream.read(bytes, offset, bytes.length
							- offset)) >= 0) {
				offset += numRead;
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new DataAccessException(ioe.toString());
		}
		if (offset < bytes.length) {
			throw new DataAccessException("Could not completely read file "
					+ file.getName());
		}
		try {
			inputStream.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new DataAccessException(ioe.toString());
		}
		return bytes;
	}

	public void writeByteFileUsingURI(String uri, byte[] contents)
			throws DataAccessException {
		File file = new File(uri);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ioe) {
				ioe.printStackTrace();
				throw new DataAccessException("writeFileUsingURI():" + uri);
			}
		}
		file.setWritable(true);

		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(uri);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			throw new DataAccessException("writeFileUsingURI():" + uri + " "
					+ fnfe.getMessage());
		}
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		try {
			byteArrayOutputStream.writeTo(outputStream);

			outputStream.write(contents);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new DataAccessException("writeFileUsingURI():" + uri);
		}
		try {
			outputStream.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new DataAccessException("writeFileUsingURI():" + uri);
		}
		try {
			outputStream.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new DataAccessException("writeFileUsingURI():" + uri);
		}
	}

	public void copyChar(String fromUri, String toUri)
			throws DataAccessException {
		char[] contents = readCharFileUsingURI(fromUri);
		writeCharFileUsingURI(toUri, contents);
	}

	public void copyByte(String fromUri, String toUri)
			throws DataAccessException {
		byte[] contents = readByteFileUsingURI(fromUri);
		writeByteFileUsingURI(toUri, contents);
	}

	public void folderTestAndCreate(String uri) {
		File file = new File(uri);
		if (!file.isDirectory()) {
			file.mkdir();
		}
		file.setWritable(true);
	}

	public byte[] readFromURL(String fileURL) throws DataAccessException {
		URL url;
		try {
			url = new URL(fileURL);
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
			throw new DataAccessException(mue.getMessage());

		}
		URLConnection connection;
		try {
			connection = url.openConnection();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new DataAccessException(ioe.getMessage());
		}
		connection.setDoInput(true);
		try {
			connection.connect();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new DataAccessException(ioe.getMessage());
		}
		InputStream in = null;
		try {
			in = connection.getInputStream();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		int bufferSizeAttempt = 0;
		try {
			bufferSizeAttempt = in.available();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		System.out.println("bufferSize as available:" + bufferSizeAttempt);

		// if(bufferSizeAttempt > bufferSize){bufferSize = bufferSizeAttempt;}

		bufferSize = bufferSizeAttempt;
		byte[] readBuffer = new byte[bufferSize];
		byte[] allBuffer = new byte[0];
		int result = 0;
		while (result >= 0) {
			try {
				result = in.read(readBuffer);
				if (result < 0) {
					break;
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
				throw new DataAccessException(ioe.getMessage());
			}
			byte[] temp = new byte[result + allBuffer.length];
			int idex = 0;
			for (; idex < allBuffer.length; idex++) {
				temp[idex] = allBuffer[idex];
			}
			for (int idex2 = 0; idex2 < result; idex2++) {
				temp[idex++] = readBuffer[idex2];
			}
			allBuffer = temp;
			readBuffer = new byte[bufferSize];
			result = 0;
		}
		try {
			in.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new DataAccessException(ioe.getMessage());
		}
		/*
		 * int count=0; byte [] stuff = new byte[1]; for(int idex = 0; true;
		 * idex++){ byte [] newstuff = new byte[stuff.length+1];
		 * newstuff[idex]=readBuffer[idex]; //System.out.print("char:" +
		 * newstuff[idex] + " "); stuff = newstuff; if(newstuff[idex]==0){
		 * ++count; } else{ count = 0; } if(count >= 16){ // the end break; } }
		 * System.out.print(stuff[4]+ ' ' + stuff[8]+'\n'); return stuff;
		 */
		System.out.print("readBuffer:" + allBuffer[4] + ' ' + allBuffer[8]
				+ ' ' + allBuffer[allBuffer.length - 1] + '\n');
		return allBuffer;
	}

	public List<String> directoryFileReader(String uid) {
		List<String> filesList = new ArrayList<String>();
		File folder = new File(uid);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				filesList.add(listOfFiles[i].getName());
			}
		}
		return filesList;
	}

	public static boolean doesFileExist(String uri) {
		File file = new File(uri);
		return file.exists();
	}

	public static boolean doesFileNotExist(String uri) {
		return !doesFileExist(uri);
	}

	public void deleteFile(String uri) throws DataAccessException {
		if (doesFileNotExist(uri)) {
			throw new DataAccessException(
					"Warning:trying to delete a files that does not exist "
							+ uri);
		}
		File file = new File(uri);
		if (!file.canWrite()) {
			throw new DataAccessException("Warning:permission problem on "
					+ uri);
		}
		boolean success = file.delete();
		if (!success) {
			throw new DataAccessException("delete failed for " + uri);
		}
	}
}
