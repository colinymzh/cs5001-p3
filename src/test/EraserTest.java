package test;

import model.Eraser;
import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Eraser model.
 * Contains unit tests to verify the functionality of the Eraser class, focusing on
 * coordinate management and behavior of the isClickPointInShape method.
 */
public class EraserTest {

    private Eraser eraser;

    /**
     * Sets up a new Eraser instance before each test.
     */
    @BeforeEach
    public void setUp() {
        eraser = new Eraser();
    }

    /**
     * Tests if the initial coordinates of the eraser are set to (0,0).
     */
    @Test
    public void testInitialCoordinates() {
        assertEquals(0, eraser.getCoordinateA().getX(), "Initial X coordinate of point A should be 0");
        assertEquals(0, eraser.getCoordinateA().getY(), "Initial Y coordinate of point A should be 0");
        assertEquals(0, eraser.getCoordinateB().getX(), "Initial X coordinate of point B should be 0");
        assertEquals(0, eraser.getCoordinateB().getY(), "Initial Y coordinate of point B should be 0");
    }

    /**
     * Tests if setting new coordinates for the eraser works correctly.
     */
    @Test
    public void testSetCoordinates() {
        // 设置新的坐标并测试
        eraser.setCoordinateA(10, 20);
        eraser.setCoordinateB(30, 40);

        assertEquals(10, eraser.getCoordinateA().getX(), "X coordinate of point A should be set to 10");
        assertEquals(20, eraser.getCoordinateA().getY(), "Y coordinate of point A should be set to 20");
        assertEquals(30, eraser.getCoordinateB().getX(), "X coordinate of point B should be set to 30");
        assertEquals(40, eraser.getCoordinateB().getY(), "Y coordinate of point B should be set to 40");
    }

    /**
     * Tests the isClickPointInShape method of the eraser.
     * Since this method always returns false for an eraser, the test verifies this behavior.
     */
    @Test
    public void testIsClickPointInShape() {
        assertFalse(eraser.isClickPointInShape(new Point(5, 5)), "isClickPointInShape should always return false");
    }
}
