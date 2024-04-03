package utils;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The `ColorMapping` class provides methods for working with colors.
 */
public class ColorMapping {

    // Define a map that associates color names with their corresponding Color objects.
    private static final Map<String, Color> predefinedColors;

    // Initialize the predefinedColors map with commonly used color names and their Color objects.
    static {
        predefinedColors = new HashMap<>();
        predefinedColors.put("red", Color.RED);
        predefinedColors.put("green", Color.GREEN);
        predefinedColors.put("blue", Color.BLUE);
        predefinedColors.put("yellow", Color.YELLOW);
        predefinedColors.put("black", Color.BLACK);
        predefinedColors.put("white", Color.WHITE);
        predefinedColors.put("cyan", Color.CYAN);
        predefinedColors.put("magenta", Color.MAGENTA);
        predefinedColors.put("orange", Color.ORANGE);
        predefinedColors.put("pink", Color.PINK);
        predefinedColors.put("gray", Color.GRAY);
        predefinedColors.put("darkGray", Color.DARK_GRAY);
        predefinedColors.put("lightGray", Color.LIGHT_GRAY);
    }

    /**
     * Calculates the Euclidean distance between two colors in RGB color space.
     *
     * @param c1 The first color.
     * @param c2 The second color.
     * @return The Euclidean distance between the two colors.
     */
    private static double colorDistance(Color c1, Color c2) {
        int red = c1.getRed() - c2.getRed();
        int green = c1.getGreen() - c2.getGreen();
        int blue = c1.getBlue() - c2.getBlue();
        return Math.sqrt(red * red + green * green + blue * blue);
    }

    /**
     * Finds the closest predefined color name for a given Color object.
     *
     * @param color The Color object to find the closest predefined color name for.
     * @return The name of the closest predefined color.
     */
    private static String getClosestColorName(Color color) {
        String closestColorName = null;
        double minDistance = Double.MAX_VALUE;

        for (Entry<String, Color> entry : predefinedColors.entrySet()) {
            double distance = colorDistance(color, entry.getValue());
            if (distance < minDistance) {
                minDistance = distance;
                closestColorName = entry.getKey();
            }
        }

        return closestColorName;
    }

    /**
     * Gets the closest predefined color name as a string for a given Color object.
     *
     * @param color The Color object to find the closest predefined color name for.
     * @return The name of the closest predefined color as a string.
     */
    public static String getColorAsString(Color color) {
        return getClosestColorName(color);
    }
}
