package model;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Abstract base class for all shape types that can be drawn on a canvas.
 * This class provides common properties like coordinates, color, stroke width, and methods
 * for basic operations like drawing, rotating, and resizing a shape.
 */
public abstract class Shape {

    // Base coordinates for the shape
    protected final Point coordinateA = new Point(0, 0);
    protected final Point coordinateB = new Point(0, 0);

    // Attributes of the shape
    protected Color shapeColor = Color.BLACK;
    protected Color fillColor = new Color(255, 255, 255, 0);

    protected int strokeWidth = 1;
    protected boolean isSelected = false;
    protected boolean isFilled = false;
    int rotationAngle = 0;

    // Getters and setters for coordinates, color, stroke width, etc.
    public Point getCoordinateA() { return coordinateA; }
    public void setCoordinateA(int x, int y) { coordinateA.setX(x); coordinateA.setY(y); }

    public Point getCoordinateB() { return coordinateB; }
    public void setCoordinateB(int x, int y) { coordinateB.setX(x); coordinateB.setY(y); }
    public Color getShapeColor() { return shapeColor; }
    public void setShapeColor(Color newColor) { this.shapeColor = newColor; }
    public Color getFillColor() { return fillColor; }
    public void setFillColor(Color newColor) { this.fillColor = newColor; }
    public int getStrokeWidth() { return strokeWidth; }
    public void setStrokeWidth(int newStroke) { this.strokeWidth = newStroke; }
    public void setSelected(boolean bool) { isSelected = bool; }
    public void setFilled(boolean bool) { isFilled = bool; }
    public int getRotationAngle() {
        return this.rotationAngle;
    }
    public void setRotationAngle(int angle) { this.rotationAngle = angle; }

    /**
     * Abstract method for drawing the shape using a Graphics2D context.
     * This method must be implemented by subclasses to define how the shape is drawn.
     *
     * @param g2D The Graphics2D context to use for drawing the shape.
     */
    public abstract void draw(Graphics2D g2D);

    /**
     * Abstract method to determine if a point is within the shape.
     * This method must be implemented by subclasses based on the shape's geometry.
     *
     * @param point The point to check.
     * @return true if the point is within the shape, false otherwise.
     */
    public abstract boolean isClickPointInShape(Point point);

    /**
     * Abstract method for drawing a border around the shape.
     * This method must be implemented by subclasses to define how the border is drawn.
     *
     * @param g2D The Graphics2D context to use for drawing the border.
     */
    public abstract void drawBorder(Graphics2D g2D);

    /**
     * Translates the shape by the specified amounts in x and y directions.
     * This method adjusts the coordinates of the shape by the given delta values.
     *
     * @param x The amount to translate in the x-direction.
     * @param y The amount to translate in the y-direction.
     */
    public void translate(int x, int y) {
        translatePoint(coordinateA, x, y);
        translatePoint(coordinateB, x, y);
    }

    // Private helper method to translate a single point
    private void translatePoint(Point point, int x, int y) {
        point.setX(point.getX() + x);
        point.setY(point.getY() + y);
    }

    /**
     * Method for resizing the shape.
     * The specific implementation will depend on the type of shape and how it should be resized.
     *
     * @param deltaX The change in size in the x-direction.
     * @param deltaY The change in size in the y-direction.
     */
    public void resize(int deltaX, int deltaY) {

    }


    /**
     * Rotates the shape by the specified angle.
     * The specific implementation will depend on the shape and how it should be rotated.
     *
     * @param angle The rotation angle in degrees.
     */
    public void rotate(int angle) {

    }
}
