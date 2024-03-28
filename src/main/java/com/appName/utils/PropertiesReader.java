package com.appName.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private Properties properties;

    public PropertiesReader(String filePath) {
        this.properties = new Properties();
        loadProperties(filePath);
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

    // Add more methods as needed for your specific use case

    public static void main(String[] args) {
        PropertiesReader reader = new PropertiesReader("app.properties");
        // Example usage:
        String serverUrl = reader.getProperty("server.url");
        int port = reader.getIntProperty("server.port");
        boolean debugMode = reader.getBooleanProperty("app.debug");
        System.out.println("Server URL: " + serverUrl);
        System.out.println("Port: " + port);
        System.out.println("Debug Mode: " + debugMode);
    }
}