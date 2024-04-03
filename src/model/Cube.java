package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.geom.AffineTransform;

/**
 * Represents a Cube shape that can be drawn on a canvas.
 * This class extends the Shape class, providing specific implementations for drawing a cube.
 */
public class Cube extends model.Shape{

    private int edge = 0;
    private final Point a1 = new Point(0, 0);
    private final Point a2 = new Point(0, 0);
    private final Point a3 = new Point(0, 0);
    private final Point a4 = new Point(0, 0);
    private final Point b1 = new Point(0, 0);
    private final Point b2 = new Point(0, 0);
    private final Point b3 = new Point(0, 0);
    private final Point b4 = new Point(0, 0);

    private void setEdge(int edge)   { this.edge = edge; }

    /**
     * Draws the cube on the canvas using the provided Graphics2D context.
     * This method handles the rotation, size adjustment, and actual drawing of the cube.
     * @param g2D The Graphics2D context to use for drawing.
     */
    @Override
    public void draw(Graphics2D g2D) {

        AffineTransform oldTransform = g2D.getTransform();

        // Calculate the rotation center (the 2D center of the cube)
        int centerX = (a1.getX() + b3.getX()) / 2;
        int centerY = (a1.getY() + b3.getY()) / 2;

        // Apply rotation transformation
        g2D.rotate(Math.toRadians(rotationAngle), centerX, centerY);


        // Update the edge length
        int deltaX = coordinateB.getX() - coordinateA.getX();
        int deltaY = coordinateB.getY() - coordinateA.getY();
        int Distance = (int) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        setEdge(Distance / 3);

        // Update the coordinates
        a1.setX(Math.min(coordinateA.getX(), coordinateB.getX()));
        a1.setY(Math.min(coordinateA.getY(), coordinateB.getY()));
        a2.setX(a1.getX() + edge);
        a2.setY(a1.getY());
        a3.setX(a2.getX());
        a3.setY(a2.getY() + edge);
        a4.setX(a1.getX());
        a4.setY(a3.getY());
        b3.setX(Math.max(coordinateA.getX(), coordinateB.getX()));
        b3.setY(Math.max(coordinateA.getY(), coordinateB.getY()));
        b4.setX(b3.getX() - edge);
        b4.setY(b3.getY());
        b1.setX(b4.getX());
        b1.setY(b4.getY() - edge);
        b2.setX(b3.getX());
        b2.setY(b1.getY());

        g2D.setPaint(shapeColor);
        g2D.setStroke(new BasicStroke(strokeWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL));

        // Draw the cube's edges
        g2D.drawLine(a1.getX(), a1.getY(), a2.getX(), a2.getY());
        g2D.drawLine(a2.getX(), a2.getY(), a3.getX(), a3.getY());
        g2D.drawLine(a3.getX(), a3.getY(), a4.getX(), a4.getY());
        g2D.drawLine(a4.getX(), a4.getY(), a1.getX(), a1.getY());

        g2D.drawLine(a1.getX(), a1.getY(), b1.getX(), b1.getY());
        g2D.drawLine(a2.getX(), a2.getY(), b2.getX(), b2.getY());
        g2D.drawLine(a3.getX(), a3.getY(), b3.getX(), b3.getY());
        g2D.drawLine(a4.getX(), a4.getY(), b4.getX(), b4.getY());

        g2D.drawLine(b1.getX(), b1.getY(), b2.getX(), b2.getY());
        g2D.drawLine(b2.getX(), b2.getY(), b3.getX(), b3.getY());
        g2D.drawLine(b3.getX(), b3.getY(), b4.getX(), b4.getY());
        g2D.drawLine(b4.getX(), b4.getY(), b1.getX(), b1.getY());

        // Draw border if selected
        if (isSelected) {
            drawBorder(g2D);
        }

        // Restore the original transformation
        g2D.setTransform(oldTransform);
    }

    /**
     * Determines if a given point is within the shape of the cube.
     * @param point The point to check.
     * @return true if the point is within the cube, false otherwise.
     */
    @Override
    public boolean isClickPointInShape(Point point) {
        return a1.getX() < point.getX() && point.getX() < b3.getX()
                && a1.getY() < point.getY() && point.getY() < b3.getY();
    }

    /**
     * Draws a border around the cube.
     * This method is invoked when the cube is selected.
     * @param g2D The Graphics2D context to use for drawing the border.
     */
    @Override
    public void drawBorder(Graphics2D g2D) {
        g2D.setPaint(Color.LIGHT_GRAY);
        g2D.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL,
                10, new float[]{10, 10}, 0));
        g2D.drawLine(a1.getX() - 2, a1.getY() - 2, a2.getX() + 2, a2.getY() + 2);
        g2D.drawLine(a2.getX() + 2, a2.getY() + 2, a3.getX(), a3.getY());
        g2D.drawLine(a3.getX(), a3.getY(), a4.getX(), a4.getY());
        g2D.drawLine(a4.getX(), a4.getY(), a1.getX(), a1.getY());

        g2D.drawLine(a1.getX(), a1.getY(), b1.getX(), b1.getY());
        g2D.drawLine(a2.getX(), a2.getY(), b2.getX(), b2.getY());
        g2D.drawLine(a3.getX(), a3.getY(), b3.getX(), b3.getY());
        g2D.drawLine(a4.getX(), a4.getY(), b4.getX(), b4.getY());

        g2D.drawLine(b1.getX(), b1.getY(), b2.getX(), b2.getY());
        g2D.drawLine(b2.getX(), b2.getY(), b3.getX(), b3.getY());
        g2D.drawLine(b3.getX(), b3.getY(), b4.getX(), b4.getY());
        g2D.drawLine(b4.getX(), b4.getY(), b1.getX(), b1.getY());
    }

    /**
     * Resize the cube based on the delta values of X and Y.
     *
     * @param deltaX The change in X-axis.
     * @param deltaY The change in Y-axis.
     */
    public void resize(int deltaX, int deltaY) {
        // Calculate the new edge length
        int newEdge = edge + Math.max(deltaX, deltaY);

        // Ensure the edge length is not negative
        if (newEdge < 0) {
            newEdge = 0;
        }

        // Update the edge length
        setEdge(newEdge);

        // Update the coordinates of the cube
        updateVertices();
    }

    /**
     * Updates the coordinates of the vertices for the cube based on the current edge length.
     * This method is called to ensure that the cube's vertices are correctly positioned when resizing.
     */
    private void updateVertices() {
        // Update the coordinates of the 'a' series vertices
        a2.setX(a1.getX() + edge);
        a3.setX(a2.getX());
        a3.setY(a2.getY() + edge);
        a4.setY(a3.getY());

        // Update the coordinates of the 'b' series vertices
        b3.setX(a1.getX() + edge * 2);
        b3.setY(a1.getY() + edge * 2);
        b4.setX(b3.getX() - edge);
        b1.setX(b4.getX());
        b1.setY(b4.getY() - edge);
        b2.setY(b1.getY());
        b2.setX(b3.getX());
    }

    @Override
    public void rotate(int angle) {
        this.rotationAngle = angle;
    }
}
