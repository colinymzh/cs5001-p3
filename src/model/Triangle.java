package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

/**
 * Represents a Triangle shape that can be drawn on a canvas.
 * This class extends the Shape class, providing specific functionality for drawing a triangle.
 * It maintains three vertices, with the third one being calculated dynamically.
 */
public class Triangle extends model.Shape{
    // Third vertex of the triangle
    private final Point c = new Point(0, 0);
    // Helper vertices for drawing and calculations
    private final Point[] vertex = new Point[4];
    // Flag to determine if vertex C needs recalculation
    private boolean shouldRecalculateC = true;

    /**
     * Constructor for the Triangle class.
     * Initializes the vertices array and the third vertex 'C' to default points.
     */
    public Triangle(){
        for (int i = 0; i < vertex.length; i++) {
            vertex[i] = new Point(0, 0);
        }
    }

    public int getCoordinateCX() {
        return c.getX();
    }

    public int getCoordinateCY() {
        return c.getY();
    }

    public void setCoordinateC(int x, int y) {
        c.setX(x);
        c.setY(y);
        shouldRecalculateC = false; // 设置 C 点后不再重新计算
    }

    public void setShouldRecalculateC(boolean b) {
        this.shouldRecalculateC = b;
    }

    /**
     * Draws the triangle on the canvas using the provided Graphics2D context.
     * Handles both filled and non-filled drawing, as well as rotation and drawing a border if selected.
     *
     * @param g2D The Graphics2D context to use for drawing.
     */
    @Override
    public void draw(Graphics2D g2D) {

        // Save the original transformation
        AffineTransform oldTransform = g2D.getTransform();

        // Calculate rotation center, apply rotation transformation, and update vertex coordinates
        int centerX = (coordinateA.getX() + coordinateB.getX() + c.getX()) / 3;
        int centerY = (coordinateA.getY() + coordinateB.getY() + c.getY()) / 3;

        g2D.rotate(Math.toRadians(rotationAngle), centerX, centerY);

        if (shouldRecalculateC) {
            c.setX(coordinateA.getX() - (coordinateB.getX() - coordinateA.getX()));
            c.setY(coordinateB.getY());
        }

        vertex[0].setX(Math.min(coordinateB.getX(), c.getX()));
        vertex[0].setY(Math.min(coordinateA.getY(), coordinateB.getY()));
        vertex[1].setX(Math.max(coordinateB.getX(), c.getX()));
        vertex[1].setY(vertex[0].getY());
        vertex[2].setX(vertex[1].getX());
        vertex[2].setY(Math.max(coordinateA.getY(), coordinateB.getY()));
        vertex[3].setX(vertex[0].getX());
        vertex[3].setY(vertex[2].getY());


        // Create a polygon to represent the triangle and set stroke properties
        Polygon polygon = new Polygon();
        polygon.addPoint(coordinateA.getX(), coordinateA.getY());
        polygon.addPoint(coordinateB.getX(), coordinateB.getY());
        polygon.addPoint(c.getX(), c.getY());

        g2D.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));

        // Draw the triangle (filled or non-filled)
        if (isFilled) {
            g2D.setPaint(fillColor);
            g2D.fillPolygon(polygon);
            g2D.setPaint(shapeColor);
            g2D.drawPolygon(polygon);
        } else {
            g2D.setPaint(shapeColor);
            g2D.drawPolygon(polygon);
        }

        // Draw border if the triangle is selected
        if (isSelected)
            drawBorder(g2D);

        // Restore the original transformation
        g2D.setTransform(oldTransform);
    }

    /**
     * Determines if a given point is within the shape of the triangle.
     *
     * @param point The point to check.
     * @return true if the point is within the triangle, false otherwise.
     */
    @Override
    public boolean isClickPointInShape(Point point) {
        return vertex[0].getX() < point.getX() && point.getX() < vertex[1].getX()
                && vertex[0].getY() < point.getY() && point.getY() < vertex[3].getY();
    }

    /**
     * Draws a border around the triangle.
     * Invoked when the triangle is selected.
     *
     * @param g2D The Graphics2D context to use for drawing the border.
     */
    @Override
    public void drawBorder(Graphics2D g2D) {
        g2D.setPaint(Color.LIGHT_GRAY);
        g2D.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL,
                10, new float[]{10, 10}, 0));
        g2D.drawRect(vertex[0].getX(), vertex[0].getY(),
                vertex[1].getX() - vertex[0].getX(), vertex[3].getY() - vertex[0].getY());

    }

    /**
     * Resizes the triangle based on the provided delta values.
     * This method adjusts the position of vertices B and C, ensuring the structure of the triangle.
     *
     * @param deltaX The change in size along the x-axis.
     * @param deltaY The change in size along the y-axis.
     */
    public void resize(int deltaX, int deltaY) {
        int newBX = coordinateB.getX() + deltaX;
        int newBY = coordinateB.getY() + deltaY;

        coordinateB.setX(newBX);
        coordinateB.setY(newBY);

        c.setX(coordinateA.getX() - (newBX - coordinateA.getX()));
        c.setY(newBY);

        updateVertexCoordinates();
    }

    /**
     * Updates the coordinates of the vertices for the bounding box of the triangle.
     */
    private void updateVertexCoordinates() {
        vertex[0].setX(Math.min(coordinateB.getX(), c.getX()));
        vertex[0].setY(Math.min(coordinateA.getY(), coordinateB.getY()));
        vertex[1].setX(Math.max(coordinateB.getX(), c.getX()));
        vertex[1].setY(vertex[0].getY());
        vertex[2].setX(vertex[1].getX());
        vertex[2].setY(Math.max(coordinateA.getY(), coordinateB.getY()));
        vertex[3].setX(vertex[0].getX());
        vertex[3].setY(vertex[2].getY());
    }

    /**
     * Sets the rotation angle for the triangle.
     * The rotation is applied around the triangle's geometric center.
     *
     * @param angle The new rotation angle in degrees.
     */
    @Override
    public void rotate(int angle) {

        this.rotationAngle = angle;
    }
}
