package utils;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * The `CanvasSaver` class provides a method to save the content of a JPanel as an image file.
 */
public class CanvasSaver {
    /**
     * Saves the content of a JPanel as an image file.
     *
     * @param panel     The JPanel whose content is to be saved.
     * @param format    The image format (e.g., "png", "jpg", "gif").
     * @param filePath  The path to the file where the image will be saved.
     */
    public static void saveCanvas(JPanel panel, String format, String filePath) {
        // Create a BufferedImage with the same size as the JPanel
        BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);

        // Get the Graphics2D context of the BufferedImage
        Graphics2D g2D = image.createGraphics();

        // Paint the content of the JPanel onto the BufferedImage
        panel.paint(g2D);

        // Release resources
        g2D.dispose();

        try {
            // Save the BufferedImage to the specified file path
            ImageIO.write(image, format, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
