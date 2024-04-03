package model;

import controller.CanvasPanelController;


import java.awt.Graphics2D;
import java.awt.BasicStroke;

/**
 * Represents an Eraser tool that can be used on a canvas.
 * This class extends the Shape class, providing specific functionality for the eraser tool.
 * The eraser works by drawing lines in the background color, effectively erasing the shapes drawn.
 */
public class Eraser extends model.Shape{

    /**
     * Draws an eraser stroke on the canvas using the provided Graphics2D context.
     * The eraser mimics a pencil by drawing a line in the canvas's background color.
     *
     * @param g2D The Graphics2D context to use for drawing the eraser stroke.
     */
    @Override
    public void draw(Graphics2D g2D) {
        // Set the paint to the background color of the canvas
        g2D.setPaint(CanvasPanelController.getBackgroundColor());
        // Set the stroke properties
        g2D.setStroke(new BasicStroke(strokeWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL));
        // Draw a line representing the eraser stroke
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
