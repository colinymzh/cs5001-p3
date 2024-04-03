package model;

/**
 * Represents a point in a two-dimensional space.
 * This class encapsulates the x and y coordinates of a point,
 * and provides methods to access and modify these coordinates.
 */
public class Point {
    private int x;
    private int y;

    /**
     * Constructs a new Point with the specified x and y coordinates.
     *
     * @param x The x-coordinate of the new point.
     * @param y The y-coordinate of the new point.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return The x-coordinate.
     */
    public int getX() { return x; }

    /**
     * Sets the x-coordinate of this point.
     *
     * @param x The new x-coordinate.
     */
    public void setX(int x) { this.x = x; }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return The y-coordinate.
     */
    public int getY() { return y; }

    /**
     * Sets the y-coordinate of this point.
     *
     * @param y The new y-coordinate.
     */
    public void setY(int y) { this.y = y; }
}
