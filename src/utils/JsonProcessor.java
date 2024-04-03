package utils;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;

import java.io.StringReader;


/**
 * The `JsonProcessor` class provides methods for processing JSON data.
 */
public class JsonProcessor {
    /**
     * Filters JSON objects from a JSON array based on a specific condition.
     *
     * @param jsonArrayStr The input JSON array string to filter.
     * @return A filtered JSON array containing only objects that meet the specified condition.
     */
    public static JsonArray filterJsonObjects(String jsonArrayStr) {

        // Create a JSON reader from the input JSON array string
        JsonReader reader = Json.createReader(new StringReader(jsonArrayStr));

        // Read the original JSON array
        JsonArray originalArray = reader.readArray();

        // Create a builder for the filtered JSON array
        JsonArrayBuilder filteredArrayBuilder = Json.createArrayBuilder();

        // Iterate through the JSON objects in the original array
        for (JsonObject jsonObject : originalArray.getValuesAs(JsonObject.class)) {
            try {
                // Use getBoolean method with a default value
                boolean isOwner = jsonObject.getBoolean("isOwner", false); // 默认值 false
                if (isOwner) {
                    filteredArrayBuilder.add(jsonObject);
                }
            } catch (Exception e) {
                // Log the exception or handle it as necessary
                System.err.println("Error processing JSON object: " + e.getMessage());
            }
        }

        // Build and return the filtered JSON array
        return filteredArrayBuilder.build();
    }
}
