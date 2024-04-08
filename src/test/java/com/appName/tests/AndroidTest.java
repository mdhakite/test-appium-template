package com.appName.tests;

import com.appName.TestUtils.AndroidBaseTest;
import com.appName.utils.Crypt;
import com.appName.utils.JsonReader;
import io.appium.java_client.AppiumBy;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


@Test
public class AndroidTest extends AndroidBaseTest {
	private static final Logger logger = LogManager.getLogger(AndroidTest.class);

	@Test(dataProvider="getJsonData",groups= {"Smoke"})
	public void AndroidBasicsTest(Object testdata) throws InterruptedException {
		logger.info("Starting AndroidBasicsTest!");
		System.out.println(testdata);
		logger.info("Entering :"+ Crypt.decrypt((String) testdata));
//		driver.findElement(AppiumBy.accessibilityId("Maps")).click();
//		Thread.sleep(3000);
		logger.info("Test Complete!");
	}

	@DataProvider
	public Object[][] getJsonData() {
		JsonReader reader = new JsonReader("testData.json");
		// Define the keys for the data you want to retrieve
		String[] keys = {"password"};
		// Create a 2D array to hold the data
		Object[][] testData = new Object[keys.length][1];
		// Retrieve data for each key and store it in the testData array
		for (int i = 0; i < keys.length; i++) {
			testData[i][0] = reader.getNode(keys[i]).asText();
		}
		return testData;
	}


	

	
	
	

}

