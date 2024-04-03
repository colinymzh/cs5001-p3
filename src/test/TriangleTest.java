package test;

import model.Triangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Triangle model.
 * Contains unit tests to verify the functionality of the Triangle class, focusing on
 * coordinate management, resizing behavior, and rotation.
 */
public class TriangleTest {

    private Triangle triangle;

    /**
     * Sets up a new Triangle instance with default dimensions before each test.
     */
    @BeforeEach
    public void setUp() {
        triangle = new Triangle();
    }

    /**
     * Tests if the initial coordinates of a new triangle are set to (0,0) for all points.
     */
    @Test
    public void testInitialCoordinates() {
        assertEquals(0, triangle.getCoordinateA().getX(), "Initial X coordinate of point A should be 0");
        assertEquals(0, triangle.getCoordinateA().getY(), "Initial Y coordinate of point A should be 0");
        assertEquals(0, triangle.getCoordinateB().getX(), "Initial X coordinate of point B should be 0");
        assertEquals(0, triangle.getCoordinateB().getY(), "Initial Y coordinate of point B should be 0");
        assertEquals(0, triangle.getCoordinateCX(), "Initial X coordinate of point C should be 0");
        assertEquals(0, triangle.getCoordinateCY(), "Initial Y coordinate of point C should be 0");
    }

    /**
     * Tests if setting new coordinates for the triangle's points works correctly.
     */
    @Test
    public void testSetCoordinates() {
        triangle.setCoordinateA(10, 20);
        triangle.setCoordinateB(30, 40);
        triangle.setCoordinateC(50, 60);

        assertEquals(10, triangle.getCoordinateA().getX(), "X coordinate of point A should be set to 10");
        assertEquals(20, triangle.getCoordinateA().getY(), "Y coordinate of point A should be set to 20");
        assertEquals(30, triangle.getCoordinateB().getX(), "X coordinate of point B should be set to 30");
        assertEquals(40, triangle.getCoordinateB().getY(), "Y coordinate of point B should be set to 40");
        assertEquals(50, triangle.getCoordinateCX(), "X coordinate of point C should be set to 50");
        assertEquals(60, triangle.getCoordinateCY(), "Y coordinate of point C should be set to 60");
    }

    /**
     * Tests the resize functionality of the triangle.
     * The expected behavior needs to be adjusted based on the specific implementation of the resize method.
     */
    @Test
    public void testResize() {
        triangle.setCoordinateA(10, 10);
        triangle.setCoordinateB(20, 20);
        triangle.resize(10, 10);

        assertEquals(30, triangle.getCoordinateB().getX(), "X coordinate of point B should be resized");
        assertEquals(30, triangle.getCoordinateB().getY(), "Y coordinate of point B should be resized");
    }

    /**
     * Tests setting the rotation angle of the triangle.
     */
    @Test
    public void testRotation() {
        triangle.setRotationAngle(45);
        assertEquals(45, triangle.getRotationAngle(), "Rotation angle should be set to 45");
    }
}
