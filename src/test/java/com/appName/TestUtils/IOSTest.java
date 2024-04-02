package com.appName.TestUtils;

import com.appName.AppPages.HomePage;
import com.appName.utils.AppiumUtils;
import com.appName.utils.YamlReader;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class IOSTest extends AppiumUtils {
    public IOSDriver driver;
    public AppiumDriverLocalService service;
    public HomePage homePage;
    public String configFile = "application.yml";

    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }

    @BeforeClass(alwaysRun = true)
    public void ConfigureAppium() {

        YamlReader reader = new YamlReader(configFile);
        String ipAddress = (String) reader.getProperty("app.ipAddress");
        int port = (int) reader.getProperty("app.port");
        String app = System.getProperty("user.dir") + "\\src\\main\\resources\\" + reader.getProperty("app.appName");
        service = startAppiumServer(ipAddress, port);
        DesiredCapabilities options = new DesiredCapabilities();
        options.setCapability("platformName", "IOS");
        options.setCapability("automationName", "XCUITest");
        options.setCapability("deviceName", (String) reader.getProperty("app.deviceName"));
        options.setCapability("platformVersion", (String) reader.getProperty("app.platformVersion"));
        options.setCapability("app", app);

        driver = new IOSDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((Long) reader.getProperty("testTimeout")));
        homePage = new HomePage(driver);

    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        driver.quit();
        service.stop();
    }
}