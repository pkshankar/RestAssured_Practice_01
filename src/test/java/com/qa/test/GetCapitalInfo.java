package com.qa.test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.qa.base.TestBase;
import com.qa.data.TestData;
import com.qa.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetCapitalInfo extends TestBase {

	String baseUrl, serviceUrl, url;

	public GetCapitalInfo() {

		super();
	}

	@BeforeMethod
	public void setUp() {

		baseUrl = prop.getProperty("baseUrl");
		serviceUrl = prop.getProperty("serviceUrl");
		url = baseUrl + serviceUrl;
	}

	@DataProvider
	public String[][] getData() {

		String[][] capitalData = TestUtil.getDataExcel(TestData.CAPITAL_INFO_SHEET_LOCATION,
				TestData.CAPITAL_INFO_SHEETNAME);
		return capitalData;
	}

	@Test(dataProvider = "getData")
	public void GetCapitalInfoTest(String capitalValue, String countryName) {

		url = baseUrl + serviceUrl + "/" + capitalValue;
		RestAssured.baseURI = url;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET);
		Assert.assertEquals(response.getStatusCode(), TestData.STATUS_CODE_200);
		JsonPath jPath = response.jsonPath();
		Assert.assertEquals(jPath.get("name").toString().replace("[", "").replace("]", ""), countryName);

	}

}
