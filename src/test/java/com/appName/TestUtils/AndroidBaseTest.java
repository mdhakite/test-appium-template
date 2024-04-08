package com.appName.TestUtils;

import com.appName.utils.AppiumUtils;
import com.appName.utils.YamlReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class AndroidBaseTest extends AppiumUtils {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public String configFile = "application.yml";

    private static final Logger logger = LogManager.getLogger(IOSBaseTest.class);

    @BeforeClass(alwaysRun = true)
    public void ConfigureAppiumForAndroid() {
        logger.info("ConfigureAppiumForAndroid{} : Configuring Appium for Android");
        try {
            YamlReader reader = new YamlReader(configFile);
            String ipAddress = (String) reader.getProperty("appium.ipAddress");
            int port = (int) reader.getProperty("appium.port");
            service = startAppiumServer(ipAddress, port, (String) reader.getProperty("appium.mainJSPath"));

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", (String) reader.getProperty("android.deviceName"));
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("automationName", "UiAutomator2");
            driver = new AndroidDriver(service.getUrl(), capabilities);
            logger.info("ConfigureAppiumForAndroid{} : Appium Configuration Complete for Android");
        } catch (Exception e) {
            logger.info("ERROR in ConfigureAppiumForAndroid{} method : ");
            e.printStackTrace();
        }

    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        logger.info("tearDown{} : Stop Appium Server and Driver");
        driver.quit();
        service.stop();
    }

}
