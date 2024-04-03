package test;

import model.Curve;
import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Curve model.
 * Contains unit tests to verify the functionality of the Curve class, particularly focusing on
 * coordinate management and point-in-shape determination.
 */
public class CurveTest {

    private Curve curve;

    /**
     * Sets up a new Curve instance before each test.
     */
    @BeforeEach
    public void setUp() {
        curve = new Curve();
    }

    /**
     * Tests if the initial coordinates of the curve are set to (0,0).
     */
    @Test
    public void testInitialCoordinates() {
        assertEquals(0, curve.getCoordinateA().getX(), "Initial X coordinate of point A should be 0");
        assertEquals(0, curve.getCoordinateA().getY(), "Initial Y coordinate of point A should be 0");
        assertEquals(0, curve.getCoordinateB().getX(), "Initial X coordinate of point B should be 0");
        assertEquals(0, curve.getCoordinateB().getY(), "Initial Y coordinate of point B should be 0");
    }

    /**
     * Tests if setting new coordinates for the curve works correctly.
     */
    @Test
    public void testSetCoordinates() {
        curve.setCoordinateA(10, 20);
        curve.setCoordinateB(30, 40);

        assertEquals(10, curve.getCoordinateA().getX(), "X coordinate of point A should be set to 10");
        assertEquals(20, curve.getCoordinateA().getY(), "Y coordinate of point A should be set to 20");
        assertEquals(30, curve.getCoordinateB().getX(), "X coordinate of point B should be set to 30");
        assertEquals(40, curve.getCoordinateB().getY(), "Y coordinate of point B should be set to 40");
    }

    /**
     * Tests the isClickPointInShape method of the curve.
     * Since this method always returns false for a curve, the test verifies this behavior.
     */
    @Test
    public void testIsClickPointInShape() {
        assertFalse(curve.isClickPointInShape(new Point(5, 5)), "isClickPointInShape should always return false");
    }
}
