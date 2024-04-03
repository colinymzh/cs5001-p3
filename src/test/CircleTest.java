package test;

import model.Circle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Circle model.
 * Contains unit tests to verify the functionality of the Circle class, particularly focusing on
 * radius management and vertex coordinate calculations.
 */
public class CircleTest {

    private Circle circle;

    /**
     * Sets up a new Circle instance before each test.
     */
    @BeforeEach
    public void setUp() {
        circle = new Circle();
    }

    /**
     * Tests if the default radius of a new circle is 0.
     */
    @Test
    public void testDefaultRadius() {
        assertEquals(0, circle.getRadius(), "Radius should be initialized to 0");
    }

    /**
     * Tests if setting the radius of the circle works correctly.
     */
    @Test
    public void testSetRadius() {
        circle.setRadius(10);
        assertEquals(10, circle.getRadius(), "Radius should be set to 10");
    }

    /**
     * Tests that setting a negative radius defaults to 0.
     */
    @Test
    public void testNegativeRadius() {
        circle.setRadius(-5);
        assertEquals(0, circle.getRadius(), "Radius should not be negative");
    }

    /**
     * Tests the resize functionality of the circle.
     * Assumes that resizing changes the radius appropriately.
     */
    @Test
    public void testResize() {
        circle.setRadius(10);
        circle.resize(4, 4); // Assuming this operation increases the radius
        assertEquals(12, circle.getRadius(), "Radius should be resized to 12");

        circle.resize(-2, -2); // Assuming this operation decreases the radius
        assertEquals(11, circle.getRadius(), "Radius should be resized back to 11");
    }

    /**
     * Tests if the vertex coordinates are updated correctly for a given radius.
     * Assumes the circle is centered at (0,0) and checks the calculated vertex coordinates.
     */
    @Test
    public void testUpdateVertexCoordinates() {
        circle.setRadius(10);
        circle.updateVertexCoordinates();
        // Test for correct vertex coordinates based on the radius and assumed circle center
        assertEquals(-10, circle.vertex[0].getX(), "Top-left X coordinate should be -10");
        assertEquals(-10, circle.vertex[0].getY(), "Top-left Y coordinate should be -10");
        assertEquals(10, circle.vertex[2].getX(), "Bottom-right X coordinate should be 10");
        assertEquals(10, circle.vertex[2].getY(), "Bottom-right Y coordinate should be 10");
    }
}
