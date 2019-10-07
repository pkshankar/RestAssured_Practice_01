package com.qa.util;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestUtil {

	public static String[][] getDataExcel(String sheetLocation, String sheetName) {
		
		String[][] capitalValues = null;

		try {
			FileInputStream fis = new FileInputStream(sheetLocation);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet(sheetName);
			int lastRowNum = sh.getLastRowNum(); //4
			int lastCellNum = sh.getRow(0).getLastCellNum();//1
			capitalValues = new String[lastRowNum+1][lastCellNum];
			for (int rw = 0; rw <= lastRowNum; rw++) {

				for (int cl = 0; cl < lastCellNum; cl++) {

					capitalValues[rw][cl] = sh.getRow(rw).getCell(cl).getStringCellValue();
					//System.out.println(capitalValues[rw][cl]);
				}
			}
			return capitalValues;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return capitalValues;
	}

}
