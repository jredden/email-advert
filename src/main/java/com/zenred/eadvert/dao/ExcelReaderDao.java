package com.zenred.eadvert.dao;

import java.io.File;

import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;

import com.zenred.util.HashMapUtil;

public class ExcelReaderDao extends AbstractJDBCDao {
	
	public int MAXROW = 2048; 
	public int MAXCOLUMN = 32;

	Map<String, List<String>> contents;

	public Map<String, List<String>> excelReader(String file) {

		int columns = 0;
		int rows = 0;

		File fp = new File(file);

		String[][] excelContents = new String[MAXCOLUMN][MAXROW];
		HashMapUtil hashContents = new HashMapUtil();

		try {
			Workbook wb = Workbook.getWorkbook(fp);
			Sheet sheet = wb.getSheet(0);
			columns = sheet.getColumns();
			rows = sheet.getRows();

			for (int col = 0; col < columns; col++) {

				for (int row = 0; row < rows; row++) {

					if (sheet.getCell(col, row).getContents().isEmpty()) {
						continue;
					} else {
						excelContents[col][row] = sheet.getCell(col, row)
								.getContents();
					}

				}
			}
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}

		this.contents = hashContents.hashMapBuild(excelContents);
		return this.contents;
	}

	public Map<String, List<String>> getContents() {
		return this.contents;
	}

}
