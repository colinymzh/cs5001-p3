package model;

import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.geom.AffineTransform;

/**
 * Represents a Line shape that can be drawn on a canvas.
 * This class extends the Shape class, providing specific functionality for drawing a line,
 * including the ability to rotate the line around its midpoint.
 */
public class Line extends model.Shape{
    /**
     * Constructs a Line instance.
     */
    public Line() {}

    /**
     * Draws the line on the canvas using the provided Graphics2D context.
     * The line is drawn between two coordinates (coordinateA and coordinateB) and can be rotated.
     *
     * @param g2D The Graphics2D context to use for drawing the line.
     */
    @Override
    public void draw(Graphics2D g2D) {

        // Save the original transformation
        AffineTransform oldTransform = g2D.getTransform();

        // Calculate the rotation center (the midpoint of the line)
        int centerX = (coordinateA.getX() + coordinateB.getX()) / 2;
        int centerY = (coordinateA.getY() + coordinateB.getY()) / 2;

        // Apply rotation transformation
        g2D.rotate(Math.toRadians(rotationAngle), centerX, centerY);

        g2D.setPaint(shapeColor);

        // Define the stroke properties for the line
        float lineThickness = strokeWidth; // Use the strokeWidth for the line thickness
        int capStyle = BasicStroke.CAP_ROUND; // Use rounded caps for the stroke
        int joinStyle = BasicStroke.JOIN_BEVEL; // Use a bevel join for the stroke

        // Create and set the stroke using the defined properties
        BasicStroke solidStroke = new BasicStroke(lineThickness, capStyle, joinStyle);
        g2D.setStroke(solidStroke);

        // Draw the line between coordinateA and coordinateB
        g2D.drawLine(coordinateA.getX(), coordinateA.getY(), coordinateB.getX(), coordinateB.getY());

        // Restore the original transformation
        g2D.setTransform(oldTransform);
    }

    /**
     * Determines if a given point is on the line.
     * This implementation checks if the point is within the bounds of the line segment.
     *
     * @param point The point to check.
     * @return true if the point is on the line, false otherwise.
     */
    @Override
    public boolean isClickPointInShape(Point point) {
        // Check if the point is within the bounds of the line segment
        boolean isXWithinBounds = isWithinBounds(point.getX(), coordinateA.getX(), coordinateB.getX());
        boolean isYWithinBounds = isWithinBounds(point.getY(), coordinateA.getY(), coordinateB.getY());

        return isXWithinBounds && isYWithinBounds;
    }

    /**
     * Helper method to determine if a coordinate is within the bounds of two points.
     *
     * @param pointCoord The coordinate of the point (either x or y).
     * @param bound1 The first boundary coordinate (either x or y).
     * @param bound2 The second boundary coordinate (either x or y).
     * @return true if pointCoord is between bound1 and bound2, false otherwise.
     */
    private boolean isWithinBounds(int pointCoord, int bound1, int bound2) {
        int lowerBound = Math.min(bound1, bound2);
        int upperBound = Math.max(bound1, bound2);
        return lowerBound < pointCoord && pointCoord < upperBound;
    }

    @Override
    public void drawBorder(Graphics2D g2D) {//can not use this method
    }

    /**
     * Sets the rotation angle for the line.
     * The rotation is applied around the line's midpoint.
     *
     * @param angle The new rotation angle in degrees.
     */
    @Override
    public void rotate(int angle) {
        this.rotationAngle = angle;
    }
}
