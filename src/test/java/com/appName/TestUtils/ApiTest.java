package com.appName.TestUtils;

import com.appName.utils.YamlReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;

public class ApiTest extends BaseTest{
    public String configFile = "application.yml";
    private static final Logger logger = LogManager.getLogger(ApiTest.class);

    @BeforeClass
    public void setUp() {
        // Set base URI for all API requests
        RestAssured.baseURI = (String) configReader.getProperty("api.baseUri");
        configReader = new YamlReader(configFile);    }

    // Method to make a GET request
    protected Response postRequest(String endpoint, String requestBody) {
        logger.info("Fetched endpoint: "+RestAssured.baseURI+endpoint);
        Response response = RestAssured
                .given()
                .baseUri(RestAssured.baseURI)
                .basePath(endpoint)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post();
        return response;
    }

    protected Response getRequest(String endpoint) {
        logger.info("Fetched endpoint: "+RestAssured.baseURI);
        Response response = RestAssured.get(RestAssured.baseURI + endpoint);
        return response;
    }

}