package com.appName.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/***
 * How to use:
 *         PropertiesReader reader = new PropertiesReader("app.properties");
 *         String serverUrl = reader.getProperty("ipAddress");
 *         int port = reader.getIntProperty("port");
 *         System.out.println("Server URL: " + serverUrl);
 *         System.out.println("Port: " + port);
 */
public class PropertiesReader {
    private Properties properties;
    private final String filePath = System.getProperty("user.dir") + "\\src\\resources\\";

    public PropertiesReader(String fileName) {
        this.properties = new Properties();
        loadProperties(filePath + fileName);
    }

    private void loadProperties(String filePath) {
        try (InputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    // Optionally, you can add convenience methods for retrieving properties of specific types
    public int getIntProperty(String key) {
        String value = getProperty(key);
        if (value != null) {
            return Integer.parseInt(value);
        }
        return 0; // or throw exception, handle as per your application's logic
    }

    public boolean getBooleanProperty(String key) {
        String value = getProperty(key);
        return Boolean.parseBoolean(value); // this will return false if value is null or not "true"
    }

}