package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.geom.AffineTransform;

/**
 * Represents a Rectangle shape that can be drawn on a canvas.
 * This class extends the Shape class and includes properties like width and height
 * for the rectangle, as well as methods for drawing, rotating, resizing, and checking if a point is within the rectangle.
 */
public class Rectangle extends model.Shape{
    private int width  = 0;
    private int height = 0;

    // Array of points representing the vertices of the rectangle
    private final model.Point[] vertex = new Point[4];

    /**
     * Constructor for the Rectangle class.
     * Initializes the vertices array to default points.
     */
    public Rectangle() {
        for (int i = 0; i < vertex.length; i++) {
            vertex[i] = new Point(0, 0);
        }
    }

    // Getters and Setters for width and height
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }

    public void setWidth(int width)  { this.width = width; }
    public void setHeight(int height) { this.height = height; }

    /**
     * Draws the rectangle on the canvas using the provided Graphics2D context.
     * Handles both filled and non-filled drawing, as well as rotation and drawing a border if selected.
     *
     * @param g2D The Graphics2D context to use for drawing.
     */
    @Override
    public void draw(Graphics2D g2D) {

        // Save the original transformation
        AffineTransform oldTransform = g2D.getTransform();

        // Calculate rotation center, apply rotation transformation, and update vertex coordinates
        int centerX = vertex[0].getX() + width / 2;
        int centerY = vertex[0].getY() + height / 2;

        g2D.rotate(Math.toRadians(rotationAngle), centerX, centerY);

        vertex[0].setX(Math.min(coordinateA.getX(), coordinateB.getX()));
        vertex[0].setY(Math.min(coordinateA.getY(), coordinateB.getY()));
        vertex[1].setX(vertex[0].getX() + width);
        vertex[1].setY(vertex[0].getY());
        vertex[2].setX(vertex[1].getX());
        vertex[2].setY(vertex[1].getY() + height);
        vertex[3].setX(vertex[0].getX());
        vertex[3].setY(vertex[2].getY());

        // Set stroke properties and draw the rectangle
        g2D.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));

        if (isFilled) {
            g2D.setPaint(fillColor);
            g2D.fillRect(vertex[0].getX(), vertex[0].getY(), width, height);
            g2D.setPaint(shapeColor);
            g2D.drawRect(vertex[0].getX(), vertex[0].getY(), width, height);
        } else {
            g2D.setPaint(shapeColor);
            g2D.drawRect(vertex[0].getX(), vertex[0].getY(), width, height);
        }

        // Draw border if the rectangle is selected
        if (isSelected)
            drawBorder(g2D);

        // Restore the original transformation
        g2D.setTransform(oldTransform);
    }

    /**
     * Determines if a given point is within the shape of the rectangle.
     *
     * @param point The point to check.
     * @return true if the point is within the rectangle, false otherwise.
     */
    @Override
    public boolean isClickPointInShape(Point point) {
        return vertex[0].getX() < point.getX() && point.getX() < vertex[1].getX()
                && vertex[0].getY() < point.getY() && point.getY() < vertex[3].getY();
    }

    /**
     * Draws a border around the rectangle.
     * Invoked when the rectangle is selected.
     *
     * @param g2D The Graphics2D context to use for drawing the border.
     */
    @Override
    public void drawBorder(Graphics2D g2D) {
        g2D.setPaint(Color.LIGHT_GRAY);
        g2D.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL,
                10, new float[]{10, 10}, 0));
        g2D.drawRect(vertex[0].getX(), vertex[0].getY(), width, height);
    }

    /**
     * Resizes the rectangle based on the provided delta values for width and height.
     *
     * @param deltaX The change in width.
     * @param deltaY The change in height.
     */
    public void resize(int deltaX, int deltaY) {
        this.width += deltaX;
        this.height += deltaY;

        if (this.width < 0) {
            this.width = 0;
        }
        if (this.height < 0) {
            this.height = 0;
        }

        updateVertexCoordinates();
    }

    /**
     * Updates the coordinates of the vertices for the bounding box of the rectangle.
     */
    private void updateVertexCoordinates() {
        vertex[0].setX(Math.min(coordinateA.getX(), coordinateB.getX()));
        vertex[0].setY(Math.min(coordinateA.getY(), coordinateB.getY()));
        vertex[1].setX(vertex[0].getX() + width);
        vertex[1].setY(vertex[0].getY());
        vertex[2].setX(vertex[1].getX());
        vertex[2].setY(vertex[1].getY() + height);
        vertex[3].setX(vertex[0].getX());
        vertex[3].setY(vertex[2].getY());
    }

    /**
     * Sets the rotation angle for the rectangle.
     * The rotation is applied around the rectangle's center.
     *
     * @param angle The new rotation angle in degrees.
     */
    @Override
    public void rotate(int angle) {
        this.rotationAngle = angle;
    }
}
