package test;

import model.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Square model.
 * Contains unit tests to verify the functionality of the Square class, focusing on
 * side length management, resizing behavior, and rotation.
 */
public class SquareTest {

    private Square square;

    /**
     * Sets up a new Square instance with default side length before each test.
     */
    @BeforeEach
    public void setUp() {
        square = new Square();
    }

    /**
     * Tests if the default side length of a new square is 0.
     */
    @Test
    public void testDefaultSideLength() {
        assertEquals(0, square.getSide(), "Side length should be initialized to 0");
    }

    /**
     * Tests if setting the side length of the square works correctly.
     */
    @Test
    public void testSetSideLength() {
        square.setSide(20);
        assertEquals(20, square.getSide(), "Side length should be set to 20");
    }

    /**
     * Tests the resize functionality of the square.
     * Assumes that resizing changes the side length based on the larger delta value.
     */
    @Test
    public void testResize() {
        square.setSide(20);
        square.resize(10, 15);

        assertEquals(35, square.getSide(), "Side length should be resized to 35 (using the larger delta)");
    }

    /**
     * Tests the behavior of the resize method with negative values.
     * Ensures that the side length does not become negative after resize.
     */
    @Test
    public void testNegativeResize() {
        square.setSide(20);
        square.resize(-25, -10);

        assertEquals(0, square.getSide(), "Side length should not be negative after resize");
    }

    /**
     * Tests setting the rotation angle of the square.
     */
    @Test
    public void testRotation() {
        square.setRotationAngle(45);
        assertEquals(45, square.getRotationAngle(), "Rotation angle should be set to 45");
    }


}
