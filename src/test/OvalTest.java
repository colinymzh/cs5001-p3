package test;

import model.Oval;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Oval model.
 * Contains unit tests to verify the functionality of the Oval class, focusing on
 * width and height management, resizing behavior, and rotation.
 */
public class OvalTest {

    private Oval oval;

    /**
     * Sets up a new Oval instance before each test.
     */
    @BeforeEach
    public void setUp() {
        oval = new Oval();
    }

    /**
     * Tests if the default width and height of a new oval are 0.
     */
    @Test
    public void testDefaultWidthAndHeight() {
        assertEquals(0, oval.getWidth(), "Width should be initialized to 0");
        assertEquals(0, oval.getHeight(), "Height should be initialized to 0");
    }

    /**
     * Tests if setting the width and height of the oval works correctly.
     */
    @Test
    public void testSetWidthAndHeight() {
        oval.setWidth(20);
        oval.setHeight(10);

        assertEquals(20, oval.getWidth(), "Width should be set to 20");
        assertEquals(10, oval.getHeight(), "Height should be set to 10");
    }

    /**
     * Tests the resize functionality of the oval.
     * Assumes that resizing changes the width and height appropriately.
     */
    @Test
    public void testResize() {
        oval.setWidth(20);
        oval.setHeight(10);
        oval.resize(5, 5);

        assertEquals(25, oval.getWidth(), "Width should be resized to 25");
        assertEquals(15, oval.getHeight(), "Height should be resized to 15");
    }

    /**
     * Tests the behavior of the resize method with negative values.
     * Ensures that width and height do not become negative after resize.
     */
    @Test
    public void testNegativeResize() {
        oval.setWidth(20);
        oval.setHeight(10);
        oval.resize(-15, -5);

        assertEquals(5, oval.getWidth(), "Width should not be negative after resize");
        assertEquals(5, oval.getHeight(), "Height should not be negative after resize");
    }

    /**
     * Tests setting the rotation angle of the oval.
     */
    @Test
    public void testRotate() {
        oval.setRotationAngle(45);
        assertEquals(45, oval.getRotationAngle(), "Rotation angle should be set to 45");
    }

}
