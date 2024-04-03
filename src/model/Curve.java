package model;


import java.awt.Graphics2D;
import java.awt.BasicStroke;

/**
 * Represents a simple curve that can be drawn on a canvas.
 * This class extends the {@code model.Shape} class, inheriting its basic shape properties and behaviors.
 */
public class Curve extends model.Shape{

    /**
     * Draws the curve on the canvas using the provided {@code Graphics2D} context.
     *
     * @param g2D The {@code Graphics2D} context used for drawing the curve.
     */
    @Override
    public void draw(Graphics2D g2D) {
        // Set the color and stroke for the curve
        g2D.setPaint(shapeColor);
        g2D.setStroke(new BasicStroke(strokeWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL));

        // Draw a line from point A to point B
        g2D.drawLine(coordinateA.getX(), coordinateA.getY(), coordinateB.getX(), coordinateB.getY());
    }


    @Override
    public boolean isClickPointInShape(Point point) {
        return false;
    }

    @Override
    public void drawBorder(Graphics2D g2D) {
    }
}
