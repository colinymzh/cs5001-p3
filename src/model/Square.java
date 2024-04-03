package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.geom.AffineTransform;

/**
 * Represents a Square shape that can be drawn on a canvas.
 * This class extends the Shape class and includes properties for the side length of the square,
 * as well as methods for drawing, rotating, and resizing the square.
 */
public class Square extends model.Shape{
    private int side = 0; // Side length of the square

    // Array of points representing the vertices of the square
    private final Point[] vertex = new Point[4];

    // Getters and setters for the side length
    public int getSide() {return this.side;}
    public void setSide(int side) { this.side = side; }


    /**
     * Constructor for the Square class.
     * Initializes the vertices array to default points.
     */
    public Square() {
        for (int i = 0; i < vertex.length; i++) {
            vertex[i] = new Point(0, 0);
        }
    }

    /**
     * Draws the square on the canvas using the provided Graphics2D context.
     * Handles both filled and non-filled drawing, as well as rotation and drawing a border if selected.
     *
     * @param g2D The Graphics2D context to use for drawing.
     */
    @Override
    public void draw(Graphics2D g2D) {
        // Save the original transformation
        AffineTransform oldTransform = g2D.getTransform();

        // Calculate rotation center, apply rotation transformation, and update vertex coordinates
        int centerX = vertex[0].getX() + side / 2;
        int centerY = vertex[0].getY() + side / 2;
        g2D.rotate(Math.toRadians(rotationAngle), centerX, centerY);

        vertex[0].setX(Math.min(coordinateA.getX(), coordinateB.getX()));
        vertex[0].setY(Math.min(coordinateA.getY(), coordinateB.getY()));
        vertex[1].setX(vertex[0].getX() + side);
        vertex[1].setY(vertex[0].getY());
        vertex[2].setX(vertex[1].getX());
        vertex[2].setY(vertex[1].getY() + side);
        vertex[3].setX(vertex[0].getX());
        vertex[3].setY(vertex[2].getY());


        // Set stroke properties and draw the square
        g2D.setStroke(new BasicStroke(strokeWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL));

        if (isFilled) {
            g2D.setPaint(fillColor);
            g2D.fillRect(vertex[0].getX(), vertex[0].getY(), side, side);
            g2D.setPaint(shapeColor);
            g2D.drawRect(vertex[0].getX(), vertex[0].getY(), side, side);
        } else {
            g2D.setPaint(shapeColor);
            g2D.drawRect(vertex[0].getX(), vertex[0].getY(), side, side);
        }

        // Draw border if the square is selected
        if (isSelected)
            drawBorder(g2D);

        // Restore the original transformation
        g2D.setTransform(oldTransform);
    }

    /**
     * Determines if a given point is within the shape of the square.
     *
     * @param point The point to check.
     * @return true if the point is within the square, false otherwise.
     */
    @Override
    public boolean isClickPointInShape(Point point) {

        return vertex[0].getX() < point.getX() && point.getX() < vertex[1].getX()
                && vertex[0].getY() < point.getY() && point.getY() < vertex[3].getY();
    }

    /**
     * Draws a border around the square.
     * Invoked when the square is selected.
     *
     * @param g2D The Graphics2D context to use for drawing the border.
     */
    @Override
    public void drawBorder(Graphics2D g2D) {
        g2D.setPaint(Color.LIGHT_GRAY);
        g2D.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL,
                10, new float[]{10, 10}, 0));
        g2D.drawRect(vertex[0].getX(), vertex[0].getY(), side, side);
    }

    /**
     * Resizes the square based on the provided delta values.
     * This method adjusts the side length of the square, ensuring it remains non-negative.
     *
     * @param deltaX The change in size along the x-axis.
     * @param deltaY The change in size along the y-axis.
     */
    public void resize(int deltaX, int deltaY) {
        int deltaSize = Math.max(Math.abs(deltaX), Math.abs(deltaY));
        if (deltaX < 0 || deltaY < 0) {
            deltaSize = -deltaSize;
        }

        int newSide = side + deltaSize;

        if (newSide < 0) {
            newSide = 0;
        }

        setSide(newSide);

        updateVertexCoordinates();
    }

    /**
     * Updates the coordinates of the vertices for the bounding box of the square.
     */
    private void updateVertexCoordinates() {
        vertex[0].setX(Math.min(coordinateA.getX(), coordinateB.getX()));
        vertex[0].setY(Math.min(coordinateA.getY(), coordinateB.getY()));
        vertex[1].setX(vertex[0].getX() + side);
        vertex[1].setY(vertex[0].getY());
        vertex[2].setX(vertex[1].getX());
        vertex[2].setY(vertex[1].getY() + side);
        vertex[3].setX(vertex[0].getX());
        vertex[3].setY(vertex[2].getY());
    }

    /**
     * Sets the rotation angle for the square.
     * The rotation is applied around the square's center.
     *
     * @param angle The new rotation angle in degrees.
     */
    @Override
    public void rotate(int angle) {
        this.rotationAngle = angle;
    }
}
