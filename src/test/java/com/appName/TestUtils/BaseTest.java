package com.appName.TestUtils;

import com.appName.utils.AppiumUtils;
import com.appName.utils.JsonReader;
import com.appName.utils.YamlReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {
    public String configFile = "application.yml";
    public String devConfigFile = "application-dev.yml";
    public String qaConfigFile = "application-qa.yml";
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    public YamlReader configReader;
    public JsonReader testData;
    public AppiumUtils appiumUtils;

    public BaseTest() {
        configReader = new YamlReader(configFile);
        String env = configReader.getProperty("env").toString();
        logger.info("Loading Base url for Environment : "+env);
        testData = new JsonReader("testData.json");
        appiumUtils = new AppiumUtils();
        if(env.equalsIgnoreCase("dev")){
            configReader = new YamlReader(devConfigFile);
        } else if (env.equalsIgnoreCase("qa")) {
            configReader = new YamlReader(qaConfigFile);
        }
    }

}
