package com.appName.TestUtils;

import com.appName.tests.LoginApiTest;
import com.appName.utils.YamlReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;

public class ApiTest {
    public String configFile = "application.yml";
    private static final Logger logger = LogManager.getLogger(ApiTest.class);

    @BeforeClass
    public void setUp() {
        // Set base URI for all API requests
        YamlReader reader = new YamlReader(configFile);
        RestAssured.baseURI = (String) reader.getProperty("api.login.uri");
    }

    // Method to make a GET request
    protected Response getRequest(String endpoint) {
        logger.info("Fetched endpoint: "+endpoint);
        return RestAssured.get(endpoint);
    }

}
