package com.appName.tests;

import com.appName.TestUtils.ApiTest;
import com.appName.templates.HomeScreenTemplate;
import com.appName.utils.JsonReader;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class LoginApiTest extends ApiTest {
	private static final Logger logger = LogManager.getLogger(LoginApiTest.class);

	@Test(groups= {"Smoke"})
	public void testGetRequest() {
		String userId = "2";
		Response response = getRequest(userId);
		response.then().statusCode(200);
		logger.info("Fetched Response: "+ response.asString());
		logger.info("Test Execution Successful!");
	}

}