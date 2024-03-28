package com.appName.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/***
 * Example Usage
 *
 *     JsonReader reader = new JsonReader("testData.json");
 *     JsonNode sensorData = reader.getNode("sensorData");
 *     System.out.println("windData: " + sensorData.get("windData").asText());
 *     System.out.println(reader.getNode("CIC").asText());
 */
public class JsonReader {
    private JsonNode jsonData;
    private final String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\com\\appName\\fixtures\\";

    public JsonReader(String fileName) {
        loadJson(filePath + fileName);
    }

    private void loadJson(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonData = objectMapper.readTree(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JsonNode getNode(String key) {
        return getNode(key, jsonData);
    }

    private JsonNode getNode(String key, JsonNode node) {
        String[] keys = key.split("\\.");
        for (String k : keys) {
            if (node.has(k)) {
                node = node.get(k);
            } else {
                return null; // Key not found
            }
        }
        return node;
    }
}
