package test;

import model.Line;
import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Line model.
 * Contains unit tests to verify the functionality of the Line class, focusing on
 * rotation angle, coordinate management, and point-in-line determination.
 */
public class LineTest {

    private Line line;

    /**
     * Sets up a new Line instance before each test.
     */
    @BeforeEach
    public void setUp() {
        line = new Line();
    }

    /**
     * Tests if the initial rotation angle of a new line is 0 degrees.
     */
    @Test
    public void testInitialRotationAngle() {
        assertEquals(0, line.getRotationAngle(), "Initial rotation angle should be 0");
    }

    /**
     * Tests if setting the rotation angle of the line works correctly.
     */
    @Test
    public void testSetRotationAngle() {
        line.setRotationAngle(45);
        assertEquals(45, line.getRotationAngle(), "Rotation angle should be set to 45");
    }

    /**
     * Tests if the initial coordinates of the line are set to (0,0).
     */
    @Test
    public void testInitialCoordinates() {
        assertEquals(0, line.getCoordinateA().getX(), "Initial X coordinate of point A should be 0");
        assertEquals(0, line.getCoordinateA().getY(), "Initial Y coordinate of point A should be 0");
        assertEquals(0, line.getCoordinateB().getX(), "Initial X coordinate of point B should be 0");
        assertEquals(0, line.getCoordinateB().getY(), "Initial Y coordinate of point B should be 0");
    }

    /**
     * Tests if setting new coordinates for the line works correctly.
     */
    @Test
    public void testSetCoordinates() {
        line.setCoordinateA(10, 20);
        line.setCoordinateB(30, 40);

        assertEquals(10, line.getCoordinateA().getX(), "X coordinate of point A should be set to 10");
        assertEquals(20, line.getCoordinateA().getY(), "Y coordinate of point A should be set to 20");
        assertEquals(30, line.getCoordinateB().getX(), "X coordinate of point B should be set to 30");
        assertEquals(40, line.getCoordinateB().getY(), "Y coordinate of point B should be set to 40");
    }

    /**
     * Tests the isClickPointInShape method of the line.
     * Verifies if a point is correctly identified as being on the line or not.
     */
    @Test
    public void testIsClickPointInShape() {
        line.setCoordinateA(10, 10);
        line.setCoordinateB(20, 20);
        assertTrue(line.isClickPointInShape(new Point(15, 15)), "Point (15, 15) should be within the line bounds");
        assertFalse(line.isClickPointInShape(new Point(5, 5)), "Point (5, 5) should not be within the line bounds");
    }
}
