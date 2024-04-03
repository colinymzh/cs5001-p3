package test;

import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Point model.
 * Contains unit tests to verify the functionality of the Point class, focusing on
 * the management of x and y coordinates.
 */
public class PointTest {

    private Point point;

    /**
     * Sets up a new Point instance with initial coordinates (0,0) before each test.
     */
    @BeforeEach
    public void setUp() {
        point = new Point(0, 0);
    }

    /**
     * Tests if the initial coordinates of a new point are (0,0).
     */
    @Test
    public void testInitialCoordinates() {
        assertEquals(0, point.getX(), "Initial X coordinate should be 0");
        assertEquals(0, point.getY(), "Initial Y coordinate should be 0");
    }

    /**
     * Tests if setting the x and y coordinates of the point works correctly.
     */
    @Test
    public void testSetCoordinates() {
        point.setX(10);
        point.setY(20);

        assertEquals(10, point.getX(), "X coordinate should be set to 10");
        assertEquals(20, point.getY(), "Y coordinate should be set to 20");
    }

    /**
     * Tests the constructor of the Point class with parameters.
     * Verifies if the point is correctly initialized with the given coordinates.
     */
    @Test
    public void testConstructorWithParameters() {
        Point newPoint = new Point(30, 40);

        assertEquals(30, newPoint.getX(), "X coordinate should be set to 30");
        assertEquals(40, newPoint.getY(), "Y coordinate should be set to 40");
    }
}
