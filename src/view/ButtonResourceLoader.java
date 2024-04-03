package view;

import controller.ButtonController;
import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Utility class for loading button icons and tooltips used in the GUI.
 */
public class ButtonResourceLoader {
    // Constants for the path and file extension of button icons
    private static final String ICON_PATH = "src" + File.separator + "icons" + File.separator;
    private static final String ICON_EXT = ".png";

    // HashMaps to store button icons and tooltips
    public static final HashMap<Integer, Icon> buttonIcons = new HashMap<>();
    public static final HashMap<Integer, String> buttonTips = new HashMap<>();

    // Initialize button resources when the class is loaded
    static {
        loadButtonResources();
    }

    /**
     * Loads button icons and tooltips.
     */
    private static void loadButtonResources() {
        addButtonResource(ButtonController.NewCanvas, "NewCanvas", "New Canvas");
        addButtonResource(ButtonController.Undo, "Undo", "Undo");
        addButtonResource(ButtonController.Eraser, "Eraser", "Eraser");
        addButtonResource(ButtonController.Curve, "Curve", "Curve");
        addButtonResource(ButtonController.Line, "Line", "Line");
        addButtonResource(ButtonController.Rectangle, "Rectangle", "Rectangle");
        addButtonResource(ButtonController.Square, "Square", "Square");
        addButtonResource(ButtonController.Cube, "Cube", "Cube");
        addButtonResource(ButtonController.RoundedRectangle, "RoundedRectangle", "Rounded Rectangle");
        addButtonResource(ButtonController.Oval, "Oval", "Oval");
        addButtonResource(ButtonController.Circle, "Circle", "Circle");
        addButtonResource(ButtonController.Triangle, "Triangle", "Triangle");
        addButtonResource(ButtonController.ShapeColor, "ShapeColor", "Shape Color");
        addButtonResource(ButtonController.Select, "Select", "Select");
        addButtonResource(ButtonController.Fill, "Fill", "Fill");
        addButtonResource(ButtonController.Resize, "Resize", "Resize");
        addButtonResource(ButtonController.Login, "Login", "Login");
        addButtonResource(ButtonController.GetDrawings, "GetDrawings", "Get Drawings");
        addButtonResource(ButtonController.AddDrawing, "AddDrawing", "Add Drawing");
        addButtonResource(ButtonController.UpdateDrawing, "UpdateDrawing", "Update Drawing");
        addButtonResource(ButtonController.DeleteDrawing, "DeleteDrawing", "Delete Drawing");
    }


    /**
     * Adds a button resource (icon and tooltip) to the HashMaps.
     *
     * @param buttonId  The ID of the button.
     * @param iconName  The name of the icon file.
     * @param toolTip   The tooltip text.
     */
    private static void addButtonResource(int buttonId, String iconName, String toolTip) {
        try {
            Icon icon = new ImageIcon(ICON_PATH + iconName + ICON_EXT);
            buttonIcons.put(buttonId, icon);
            buttonTips.put(buttonId, toolTip);
        } catch (Exception e) {
            System.err.println("Error loading icon: " + iconName);
        }
    }

    /**
     * Creates a Hashtable of line width labels for a slider.
     *
     * @return The Hashtable containing line width labels.
     */
    public static Hashtable<Integer, JLabel> createLineWidthLabelTable() {
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(1, new JLabel("1px"));
        labelTable.put(5, new JLabel("5px"));
        labelTable.put(10, new JLabel("10px"));
        labelTable.put(15, new JLabel("15px"));
        labelTable.put(20, new JLabel("20px"));
        return labelTable;
    }

    /**
     * Creates a Hashtable of rotation labels for a slider.
     *
     * @return The Hashtable containing rotation labels.
     */
    public static Hashtable<Integer, JLabel> createRotationLabelTable() {
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel("0"));
        labelTable.put(60, new JLabel("60"));
        labelTable.put(120, new JLabel("120"));
        labelTable.put(180, new JLabel("180"));
        labelTable.put(240, new JLabel("240"));
        labelTable.put(300, new JLabel("300"));
        labelTable.put(360, new JLabel("360"));

        return labelTable;
    }
}
