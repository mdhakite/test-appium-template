package com.appName.TestUtils;

import com.appName.AppPages.HomePage;
import com.appName.utils.YamlReader;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class IOSBaseTest extends BaseTest {
    public IOSDriver driver;
    public AppiumDriverLocalService service;
    public HomePage homePage;
    public String configFile = "application.yml";

    private static final Logger logger = LogManager.getLogger(IOSBaseTest.class);
    @BeforeClass(alwaysRun = true)
    public void ConfigureAppiumForiOS() {
        logger.info("ConfigureAppiumForiOS{} : Configure Appium For iOS and define app parameters from application.yml file");
        try {
            YamlReader reader = new YamlReader(configFile);
            String ipAddress = (String) reader.getProperty("appium.ipAddress");
            int port = (int) reader.getProperty("appium.port");
            service = appiumUtils.startAppiumServer(ipAddress, port,(String) reader.getProperty("appium.mainJSPath"));

            String platformVersion = Double.toString((Double) reader.getProperty("app.platformVersion"));
            String app = System.getProperty("user.dir") + "\\src\\main\\resources\\" + reader.getProperty("app.appName");

            DesiredCapabilities options = new DesiredCapabilities();
            options.setCapability("platformName", "IOS");
            options.setCapability("automationName", "XCUITest");
            options.setCapability("deviceName", (String) reader.getProperty("app.deviceName"));
            options.setCapability("platformVersion",platformVersion );
            options.setCapability("app", app);

            driver = new IOSDriver(service.getUrl(), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((Long) reader.getProperty("testTimeout")));
            homePage = new HomePage(driver);

        }catch (Exception e){
            logger.info("ERROR in ConfigureAppiumForiOS{} method : ");
            e.printStackTrace();
        }

    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        logger.info("Stop the appium server & closing iOS Driver...");
        driver.quit();
        service.stop();
    }
}