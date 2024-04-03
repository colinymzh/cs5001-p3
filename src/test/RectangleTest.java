package test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the Rectangle model.
 * Contains unit tests to verify the functionality of the Rectangle class, focusing on
 * width, height, resizing, and rotation behavior.
 */
public class RectangleTest {

    private model.Rectangle rectangle;

    /**
     * Sets up a new Rectangle instance with default dimensions before each test.
     */
    @BeforeEach
    public void setUp() {
        rectangle = new model.Rectangle();
    }

    /**
     * Tests if the default width and height of a new rectangle are 0.
     */
    @Test
    public void testDefaultWidthAndHeight() {
        assertEquals(0, rectangle.getWidth(),"Width should be initialized to 0" );
        assertEquals( 0, rectangle.getHeight(),"Height should be initialized to 0");
    }

    /**
     * Tests if setting the width and height of the rectangle works correctly.
     */
    @Test
    public void testSetWidthAndHeight() {
        rectangle.setWidth(10);
        rectangle.setHeight(20);

        assertEquals(10, rectangle.getWidth(),"Width should be set to 10");
        assertEquals( 20, rectangle.getHeight(),"Height should be set to 20");
    }

    /**
     * Tests the resize functionality of the rectangle.
     * Assumes that resizing changes the width and height appropriately.
     */
    @Test
    public void testResize() {
        rectangle.setWidth(10);
        rectangle.setHeight(20);
        rectangle.resize(5, 10);

        assertEquals( 15, rectangle.getWidth(),"Width should be resized to 15");
        assertEquals( 30, rectangle.getHeight(),"Height should be resized to 30");
    }

    /**
     * Tests the behavior of the resize method with negative values.
     * Ensures that width and height do not become negative after resize.
     */
    @Test
    public void testNegativeResize() {
        rectangle.setWidth(10);
        rectangle.setHeight(20);
        rectangle.resize(-5, -10);

        assertEquals( 5, rectangle.getWidth(),"Width should not be negative");
        assertEquals( 10, rectangle.getHeight(),"Height should not be negative");
    }

    /**
     * Tests setting the rotation angle of the rectangle.
     */
    @Test
    public void testRotate() {
        rectangle.setRotationAngle(90);
        assertEquals( 90, rectangle.getRotationAngle(),"Rotation angle should be set to 90");
    }
}
