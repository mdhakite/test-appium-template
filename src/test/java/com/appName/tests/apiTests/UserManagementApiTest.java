package com.appName.tests.apiTests;

import com.appName.TestUtils.ApiTest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

@Test
public class UserManagementApiTest extends ApiTest {
	private static final Logger logger = LogManager.getLogger(UserManagementApiTest.class);

	@Test()
	public void testLoginRequest() {
		String requestUri = configReader.getProperty("api.usermanagement.login").toString();
		String requestBody = testData.getNode("login").toString();
		Response response = postRequest(requestUri, requestBody);
		response.then().statusCode(200);
		logger.info("Fetched Response: "+ response.asString());
		logger.info("Test Execution Successful!");
	}

}