package com.appName.templates;

import com.appName.TestUtils.IOSTest;
import com.appName.tests.HomeScreenTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomeScreenTemplate extends IOSTest {
    private static final Logger logger = LogManager.getLogger(HomeScreenTest.class);

    public void enterData(String data){
        logger.info("Entered Data!");
    }


}
