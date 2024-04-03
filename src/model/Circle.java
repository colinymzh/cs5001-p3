package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

/**
 * Represents a Circle shape that can be drawn on a canvas.
 * This class extends the Shape class, providing specific implementations for drawing a circle.
 */
public class Circle extends model.Shape{
    private int radius;

    // Four vertices to define the bounding rectangle of the circle
    public final Point[] vertex = new Point[4];


    /**
     * Constructs a Circle object with default radius and vertex positions.
     */
    public Circle() {
        for (int i = 0; i < vertex.length; i++) {
            vertex[i] = new Point(0, 0);
        }
    }

    /**
     * Gets the radius of the circle.
     *
     * @return The radius of the circle.
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Sets the radius of the circle, ensuring it's non-negative.
     *
     * @param radius The radius to set for the circle.
     */
    public void setRadius(int radius) {
        if (radius < 0) {
            this.radius = 0;
        } else {
            this.radius = radius;
        }
    }

    /**
     * Draws the circle on the canvas using the provided Graphics2D context.
     * The method handles drawing both filled and unfilled circles and also draws a border if the circle is selected.
     *
     * @param g2D The Graphics2D context to use for drawing.
     */
    @Override
    public void draw(Graphics2D g2D) {

        // Update the coordinates of the bounding rectangle vertices
        vertex[0].setX(Math.min(coordinateA.getX(), coordinateB.getX()));
        vertex[0].setY(Math.min(coordinateA.getY(), coordinateB.getY()));
        vertex[1].setX(vertex[0].getX() + radius*2);
        vertex[1].setY(vertex[0].getY());
        vertex[2].setX(vertex[1].getX());
        vertex[2].setY(vertex[1].getY() + radius*2);
        vertex[3].setX(vertex[0].getX());
        vertex[3].setY(vertex[2].getY());

        // Set the stroke properties
        g2D.setStroke(new BasicStroke(strokeWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL));

        if (isFilled) {
            g2D.setPaint(fillColor);
            g2D.fillOval(vertex[0].getX(), vertex[0].getY(), radius*2, radius*2);
            g2D.setPaint(shapeColor);
            g2D.drawOval(vertex[0].getX(), vertex[0].getY(), radius*2, radius*2);
        } else {
            g2D.setPaint(shapeColor);
            g2D.drawOval(vertex[0].getX(), vertex[0].getY(), radius*2, radius*2);
        }

        if (isSelected) {
            drawBorder(g2D);
        }
    }

    /**
     * Determines if a given point is within the shape of the circle.
     * This implementation checks if the point lies within the bounding rectangle of the circle.
     *
     * @param point The point to check.
     * @return true if the point is within the circle, false otherwise.
     */
    @Override
    public boolean isClickPointInShape(Point point) {
        return vertex[0].getX() < point.getX() && point.getX() < vertex[1].getX()
                && vertex[0].getY() < point.getY() && point.getY() < vertex[3].getY();
    }

    /**
     * Draws a border around the circle.
     * This method is invoked when the circle is selected.
     *
     * @param g2D The Graphics2D context to use for drawing the border.
     */
    @Override
    public void drawBorder(Graphics2D g2D) {
        g2D.setPaint(Color.LIGHT_GRAY);
        g2D.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL,
                10, new float[]{10, 10}, 0));
        g2D.drawRect(vertex[0].getX(), vertex[0].getY(), radius*2, radius*2);
    }

    /**
     * Resizes the circle by changing its radius based on delta values.
     *
     * @param deltaX The change in the X-coordinate.
     * @param deltaY The change in the Y-coordinate.
     */
    public void resize(int deltaX, int deltaY) {
        int deltaSize = (int) (Math.sqrt(deltaX * deltaX + deltaY * deltaY) / 2);
        if (deltaX < 0 || deltaY < 0) {
            deltaSize = -deltaSize; // Use a negative value if resizing to shrink
        }

        int newRadius = radius + deltaSize;

        // Ensure the radius is non-negative
        if (newRadius < 0) {
            newRadius = 0;
        }

        // Update the radius
        setRadius(newRadius);

        // Update the coordinates of the bounding rectangle vertices
        updateVertexCoordinates();
    }

    /**
     * Updates the coordinates of the bounding rectangle vertices based on the circle's radius.
     */
    public void updateVertexCoordinates() {
        // Calculate the center coordinates of the circle
        int centerX = (coordinateA.getX() + coordinateB.getX()) / 2;
        int centerY = (coordinateA.getY() + coordinateB.getY()) / 2;

        // Calculate the coordinates of the four vertices of the bounding rectangle
        vertex[0].setX(centerX - radius); // Top-left
        vertex[0].setY(centerY - radius);
        vertex[1].setX(centerX + radius); // Top-right
        vertex[1].setY(centerY - radius);
        vertex[2].setX(centerX + radius); // Bottom-right
        vertex[2].setY(centerY + radius);
        vertex[3].setX(centerX - radius); // Bottom-left
        vertex[3].setY(centerY + radius);
    }
}
