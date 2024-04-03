package test;

import controller.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

/**
 * Test class for CanvasPanelController.
 * This class contains unit tests to verify the functionality of the CanvasPanelController,
 * particularly focusing on background and shape color settings.
 */
public class CanvasPanelControllerTest {

    private CanvasPanelController canvasController;

    /**
     * Set up method for tests.
     * Initializes a new CanvasPanelController instance before each test is run.
     */
    @BeforeEach
    public void setUp() {
        canvasController = new CanvasPanelController();
    }

    /**
     * Tests if the initial background color of the canvas is white.
     */
    @Test
    public void testBackgroundColorInitial() {
        assertEquals(Color.WHITE, CanvasPanelController.getBackgroundColor(), "Initial background color should be white");
    }

    /**
     * Tests if the background color of the canvas can be set and retrieved correctly.
     */
    @Test
    public void testSetBackgroundColor() {
        Color newColor = Color.BLUE;
        canvasController.setBackgroundColor(newColor);
        assertEquals(newColor, CanvasPanelController.getBackgroundColor(), "Background color should be set to blue");
    }

    /**
     * Tests if the shape color can be set and retrieved correctly.
     */
    @Test
    public void testSetShapeColor() {
        Color newColor = Color.RED;
        canvasController.setShapeColor(newColor);
        assertEquals(newColor, CanvasPanelController.getShapeColor(), "Shape color should be set to red");
    }
}
