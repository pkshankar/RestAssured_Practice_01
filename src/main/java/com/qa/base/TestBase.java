package com.qa.base;

import java.io.FileInputStream;
import java.util.Properties;

import com.qa.data.TestData;

public class TestBase {

	public static Properties prop;

	public TestBase() {

		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(TestData.CONFIG_FILE_LOCATION);
			prop = new Properties();
			prop.load(fis);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
