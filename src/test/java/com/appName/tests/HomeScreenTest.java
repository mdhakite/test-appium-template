package com.appName.tests;

import com.appName.templates.HomeScreenTemplate;
import com.appName.utils.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class HomeScreenTest extends HomeScreenTemplate {
	private static final Logger logger = LogManager.getLogger(HomeScreenTest.class);

	@Test(dataProvider="getJsonData",groups= {"Smoke"})
	public void IOSBasicsTest(Object testdata) {
		logger.info("In IOSBasicsTest!");
		logger.info("Entering :"+testdata);
		enterData(testdata.toString());
		logger.info("Test Complete!");
	}

	@DataProvider
	public Object[][] getJsonData() {
		JsonReader reader = new JsonReader("testData.json");
		// Define the keys for the data you want to retrieve
		String[] keys = {"CIC", "ValidationMessages.err1", "tutorial.step1"};
		// Create a 2D array to hold the data
		Object[][] testData = new Object[keys.length][1];
		// Retrieve data for each key and store it in the testData array
		for (int i = 0; i < keys.length; i++) {
			testData[i][0] = reader.getNode(keys[i]).asText();
		}
		return testData;
	}


	

	
	
	

}

