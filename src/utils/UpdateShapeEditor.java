package utils;

import javax.json.JsonObject;
import javax.json.Json;
import javax.swing.*;
import java.awt.GridLayout;

/**
 * The `UpdateShapeEditor` class provides a method to update the properties of different shapes.
 */
public class UpdateShapeEditor {
    /**
     * Updates the properties of a selected shape and constructs a JSON object with the updated data.
     *
     * @param selectedObject The JSON object representing the selected shape.
     * @return A JSON object containing the updated shape data, or null if no shape is selected.
     */
    public static JsonObject updateRectangleProperties(JsonObject selectedObject) {
        JsonObject properties = selectedObject.getJsonObject("properties");

        JTextField xField = new JTextField(selectedObject.getJsonNumber("x").toString());
        JTextField yField = new JTextField(selectedObject.getJsonNumber("y").toString());

        if (selectedObject == null) {
            JOptionPane.showMessageDialog(null, "You need to select a shape first.");
            return null;
        } else if ("rectangle".equals(selectedObject.getString("type")) || "ellipse".equals(selectedObject.getString("type"))){

            JTextField widthField = new JTextField(properties.getJsonNumber("width").toString());
            JTextField heightField = new JTextField(properties.getJsonNumber("height").toString());
            JTextField rotationField = new JTextField(properties.getJsonNumber("rotation").toString());
            JTextField borderColorField = new JTextField(properties.getString("borderColor"));
            JTextField borderWidthField = new JTextField(properties.getJsonNumber("borderWidth").toString());
            JTextField fillColorField = new JTextField(properties.getString("fillColor"));

            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("x:"));
            panel.add(xField);
            panel.add(new JLabel("y:"));
            panel.add(yField);
            panel.add(new JLabel("width:"));
            panel.add(widthField);
            panel.add(new JLabel("height:"));
            panel.add(heightField);
            panel.add(new JLabel("rotation:"));
            panel.add(rotationField);
            panel.add(new JLabel("borderColor:"));
            panel.add(borderColorField);
            panel.add(new JLabel("borderWidth:"));
            panel.add(borderWidthField);
            panel.add(new JLabel("fillColor:"));
            panel.add(fillColorField);


            int result = JOptionPane.showConfirmDialog(null, panel, "Update Rectangle & Ellipse Properties", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {

                // Construct and return the updated JsonObject
                JsonObject jsonObjectContainData = Json.createObjectBuilder()
                        .add("id", selectedObject.getString("id"))
                        .add("type", selectedObject.getString("type"))
                        .add("x", Integer.parseInt(xField.getText()))
                        .add("y", Integer.parseInt(yField.getText()))
                        .add("properties", Json.createObjectBuilder()
                                .add("width", Integer.parseInt(widthField.getText()))
                                .add("height", Integer.parseInt(heightField.getText()))
                                .add("rotation", Integer.parseInt(rotationField.getText()))
                                .add("borderColor", borderColorField.getText())
                                .add("borderWidth", Integer.parseInt(borderWidthField.getText()))
                                .add("fillColor", fillColorField.getText()))
                        .build();
                JsonObject jsonObjectToUpdate = Json.createObjectBuilder()
                        .add("action","updateDrawing")
                        .add("data",jsonObjectContainData)
                        .build();
                return jsonObjectToUpdate;
            }

        } else if ("triangle".equals(selectedObject.getString("type")) ) {
            JTextField x2Field = new JTextField(properties.getJsonNumber("x2").toString());
            JTextField y2Field = new JTextField(properties.getJsonNumber("y2").toString());
            JTextField x3Field = new JTextField(properties.getJsonNumber("x3").toString());
            JTextField y3Field= new JTextField(properties.getJsonNumber("y3").toString());
            JTextField rotationField = new JTextField(properties.getJsonNumber("rotation").toString());
            JTextField borderColorField = new JTextField(properties.getString("borderColor"));
            JTextField borderWidthField = new JTextField(properties.getJsonNumber("borderWidth").toString());
            JTextField fillColorField = new JTextField(properties.getString("fillColor"));

            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("x:"));
            panel.add(xField);
            panel.add(new JLabel("y:"));
            panel.add(yField);
            panel.add(new JLabel("x2:"));
            panel.add(x2Field);
            panel.add(new JLabel("y2:"));
            panel.add(y2Field);
            panel.add(new JLabel("x3:"));
            panel.add(x3Field);
            panel.add(new JLabel("y3:"));
            panel.add(y3Field);
            panel.add(new JLabel("rotation:"));
            panel.add(rotationField);
            panel.add(new JLabel("borderColor:"));
            panel.add(borderColorField);
            panel.add(new JLabel("borderWidth:"));
            panel.add(borderWidthField);
            panel.add(new JLabel("fillColor:"));
            panel.add(fillColorField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Update Triangle Properties", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {

                // Construct and return the updated JsonObject
                JsonObject jsonObjectContainData = Json.createObjectBuilder()
                        .add("id", selectedObject.getString("id"))
                        .add("type", selectedObject.getString("type"))
                        .add("x", Integer.parseInt(xField.getText()))
                        .add("y", Integer.parseInt(yField.getText()))
                        .add("x2", Integer.parseInt(xField.getText()))
                        .add("y2", Integer.parseInt(yField.getText()))
                        .add("x3", Integer.parseInt(xField.getText()))
                        .add("y3", Integer.parseInt(yField.getText()))
                        .add("properties", Json.createObjectBuilder()
                                .add("rotation", Integer.parseInt(rotationField.getText()))
                                .add("borderColor", borderColorField.getText())
                                .add("borderWidth", Integer.parseInt(borderWidthField.getText()))
                                .add("fillColor", fillColorField.getText()))
                        .build();
                JsonObject jsonObjectToUpdate = Json.createObjectBuilder()
                        .add("action","updateDrawing")
                        .add("data",jsonObjectContainData)
                        .build();
                return jsonObjectToUpdate;
            }

        } else if ("line".equals(selectedObject.getString("type")) ) {
            JTextField x2Field = new JTextField(properties.getJsonNumber("x2").toString());
            JTextField y2Field = new JTextField(properties.getJsonNumber("y2").toString());
            JTextField lineColorField = new JTextField(properties.getString("lineColor"));
            JTextField lineWidthField = new JTextField(properties.getJsonNumber("lineWidth").toString());

            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("x:"));
            panel.add(xField);
            panel.add(new JLabel("y:"));
            panel.add(yField);
            panel.add(new JLabel("x2:"));
            panel.add(x2Field);
            panel.add(new JLabel("y2:"));
            panel.add(y2Field);
            panel.add(new JLabel("lineColor:"));
            panel.add(lineColorField);
            panel.add(new JLabel("lineWidth:"));
            panel.add(lineWidthField);


            int result = JOptionPane.showConfirmDialog(null, panel, "Update Line Properties", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {

                // Construct and return the updated JsonObject
                JsonObject jsonObjectContainData = Json.createObjectBuilder()
                        .add("id", selectedObject.getString("id"))
                        .add("type", selectedObject.getString("type"))
                        .add("x", Integer.parseInt(xField.getText()))
                        .add("y", Integer.parseInt(yField.getText()))
                        .add("x2", Integer.parseInt(xField.getText()))
                        .add("y2", Integer.parseInt(yField.getText()))
                        .add("properties", Json.createObjectBuilder()
                                .add("lineColor", lineColorField.getText())
                                .add("lineWidth", Integer.parseInt(lineWidthField.getText()))
                        )
                        .build();
                JsonObject jsonObjectToUpdate = Json.createObjectBuilder()
                        .add("action","updateDrawing")
                        .add("data",jsonObjectContainData)
                        .build();
                return jsonObjectToUpdate;
            }
        }
        return null;
    }
}
