package com.appName.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.time.Duration;

public abstract class AppiumUtils {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }

    public AppiumDriverLocalService service;
    private static final Logger logger = LogManager.getLogger(AppiumUtils.class);

    public boolean isAppiumServerUP(String ipAddress, int port) {
        try {
            new Socket(ipAddress, port).close();
            logger.info("Appium Server is already running.");
            return true;
        } catch (IOException e) {
            logger.info("Appium Server is NOT running.");
            return false;
        }
    }

    public AppiumDriverLocalService startAppiumServer(String ipAddress, int port, String path) {
        if (isAppiumServerUP(ipAddress, port) && service != null && service.isRunning()) {
            service.stop();
            logger.info("Stopped existing Appium server.");
        }
        logger.info("Starting the Appium server...");
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File(path))
                .withIPAddress(ipAddress)
                .usingPort(port)
                .build();
        service.start();
        logger.info("Appium server started successfully.");
        return service;
    }

    public void waitForElementToAppear(WebElement ele, AppiumDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));
    }

    public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "//reports" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
        //1. capture and place in folder //2. extent report pick file and attach to report
    }


}
