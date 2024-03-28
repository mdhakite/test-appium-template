package com.appName.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

/***
 * Example Usage
 *
 *         YamlReader ym = new YamlReader("application.yml");
 *         System.out.println(ym.getProperty("app.port"));
 *         System.out.println(ym.getProperty("browser"));
 */

public class YamlReader {
    public Map<String, Object> yamlData;
    private final String filePath = System.getProperty("user.dir") + "\\src\\resources\\";

    public YamlReader(String fileName) {
        loadYaml(filePath + fileName);
    }

    private void loadYaml(String filePath) {
        try {
            System.out.println(filePath);
            InputStream input = new FileInputStream(filePath);
            Yaml yaml = new Yaml();
            yamlData = yaml.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getProperty(String key) {
        return getProperty(key, yamlData);
    }

    private Object getProperty(String key, Map<String, Object> map) {
        String[] keys = key.split("\\.");
        Object value = map;
        for (String k : keys) {
            if (!(value instanceof Map)) {
                return null; // Key not found or not a nested structure
            }
            value = ((Map<?, ?>) value).get(k);
        }
        return value;
    }

}