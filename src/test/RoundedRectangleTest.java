package test;

import model.RoundedRectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the RoundedRectangle model.
 * Contains unit tests to verify the functionality of the RoundedRectangle class,
 * focusing on width and height management, resizing behavior, and rotation.
 */
public class RoundedRectangleTest {

    private RoundedRectangle roundedRectangle;

    /**
     * Sets up a new RoundedRectangle instance with default dimensions before each test.
     */
    @BeforeEach
    public void setUp() {
        roundedRectangle = new RoundedRectangle();
    }

    /**
     * Tests if the default width and height of a new rounded rectangle are 0.
     */
    @Test
    public void testDefaultWidthAndHeight() {
        assertEquals(0, roundedRectangle.getWidth(), "Width should be initialized to 0");
        assertEquals(0, roundedRectangle.getHeight(), "Height should be initialized to 0");
    }

    /**
     * Tests if setting the width and height of the rounded rectangle works correctly.
     */
    @Test
    public void testSetWidthAndHeight() {
        roundedRectangle.setWidth(20);
        roundedRectangle.setHeight(30);

        assertEquals(20, roundedRectangle.getWidth(), "Width should be set to 20");
        assertEquals(30, roundedRectangle.getHeight(), "Height should be set to 30");
    }

    /**
     * Tests the resize functionality of the rounded rectangle.
     * Assumes that resizing changes the width and height appropriately.
     */
    @Test
    public void testResize() {
        roundedRectangle.setWidth(20);
        roundedRectangle.setHeight(30);
        roundedRectangle.resize(10, 15);

        assertEquals(30, roundedRectangle.getWidth(), "Width should be resized to 30");
        assertEquals(45, roundedRectangle.getHeight(), "Height should be resized to 45");
    }

    /**
     * Tests the behavior of the resize method with negative values.
     * Ensures that width and height do not become negative after resize.
     */
    @Test
    public void testNegativeResize() {
        roundedRectangle.setWidth(20);
        roundedRectangle.setHeight(30);
        roundedRectangle.resize(-25, -35);

        assertEquals(0, roundedRectangle.getWidth(), "Width should not be negative after resize");
        assertEquals(0, roundedRectangle.getHeight(), "Height should not be negative after resize");
    }

    /**
     * Tests setting the rotation angle of the rounded rectangle.
     */
    @Test
    public void testRotation() {
        roundedRectangle.setRotationAngle(45);
        assertEquals(45, roundedRectangle.getRotationAngle(), "Rotation angle should be set to 45");
    }
}
