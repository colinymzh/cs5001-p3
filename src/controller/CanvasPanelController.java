package controller;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Graphics;

import model.Oval;
import model.Shape;
import view.VectorDrawingApp;

import static controller.ButtonController.*;
import static utils.ColorMapping.getColorAsString;

/**
 * The `CanvasPanelController` class represents a JPanel that serves as a canvas for drawing shapes.
 */
public class CanvasPanelController extends JPanel {
    private static final int DEFAULT_INDEX = 0;
    private static final int MAX_NUMS_OF_SHAPES = 10000;
    private static final int DEFAULT_STROKE_WIDTH = 3;
    private static final Color DEFAULT_BACKGROUND_COLOR = java.awt.Color.WHITE;
    private static final Color DEFAULT_SHAPE_COLOR = java.awt.Color.BLACK;


    private final GraphicController graphic;
    private model.Shape[] shapeArray = new model.Shape[MAX_NUMS_OF_SHAPES];
    private int shapeIndex = DEFAULT_INDEX;
    private int strokeWidth = DEFAULT_STROKE_WIDTH;
    private static Color backgroundColor = DEFAULT_BACKGROUND_COLOR;
    private static Color shapeColor = DEFAULT_SHAPE_COLOR;
    private int currentCursor = Cursor.CROSSHAIR_CURSOR;
    private int currentTool = ButtonController.Curve;
    private model.Shape currentSelectedShape;
    private int previousX;
    private int previousY;
    public static boolean resizingShape = false;


    /**
     * Creates a new `CanvasPanelController` instance.
     */
    public CanvasPanelController() {
        graphic = new GraphicController();
        setCursor(Cursor.getPredefinedCursor(currentCursor));
        setBackground(backgroundColor);
        setLayout(null);
        addMouseListener(new MouseEventListener());
        addMouseMotionListener(new MouseEventListener());
        prepareNewShape();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Draws the component's background and border.

        // Convert to Graphics2D for advanced drawing features.
        final Graphics2D g2D = (Graphics2D) g;

        // Draw all Shape objects.
        for (int i = 0; i <= shapeIndex; i++) {
            if (shapeArray[i] != null) { // Added a null check
                shapeArray[i].draw(g2D);
            }
        }
    }

    /**
     * Resets the canvas to its default state.
     */
    public void resetCanvas() {
        shapeIndex = DEFAULT_INDEX; // Reset the index to zero
        shapeArray = new model.Shape[MAX_NUMS_OF_SHAPES]; // Reinitialize the shapes array
        graphic.getShapes().clear(); // Clear the shapes collection in the graphic object
        setCursor(Cursor.getPredefinedCursor(currentCursor)); // Set the default cursor shape
        setCurrentTool(ButtonController.Curve); // Set the default tool to curve
        setBackgroundColor(DEFAULT_BACKGROUND_COLOR); // Set the default background color
        prepareNewShape(); // Prepare a new shape item
        repaint(); // Repaint the canvas to reflect changes
    }


    /**
     * Gets the current background color of the canvas.
     *
     * @return The current background color.
     */
    public static Color getBackgroundColor() { return backgroundColor; }

    /**
     * Sets the background color of the canvas.
     *
     * @param color The new background color to set.
     */
    public void setBackgroundColor(Color color) {
        backgroundColor = color;
        setBackground(backgroundColor);
        repaint();
    }

    /**
     * Gets the current shape color.
     *
     * @return The current shape color.
     */
    public static Color getShapeColor() { return shapeColor; }

    /**
     * Sets the current shape color.
     *
     * @param color The new shape color to set.
     */
    public void setShapeColor(Color color) {
        Color newColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        shapeColor = newColor;
        if (currentSelectedShape != null) {
            currentSelectedShape.setShapeColor(newColor);
        } else {
            // If no shape is selected, prepare a new shape.
            prepareNewShape();
        }
        repaint();
    }

    /**
     * Sets the current drawing tool.
     *
     * @param tool The new drawing tool to set.
     */
    public void setCurrentTool(int tool) { currentTool = tool; }

    /**
     * Sets the current stroke width for drawing.
     *
     * @param newStroke The new stroke width to set.
     */
    public void setCurrentStroke(int newStroke) {
        strokeWidth = newStroke;
        shapeArray[shapeIndex].setStrokeWidth(strokeWidth);
        if (currentSelectedShape != null) {
            currentSelectedShape.setStrokeWidth(newStroke);
            repaint();
        }
    }

    /**
     * Sets the current cursor shape.
     *
     * @param newCursor The new cursor shape to set.
     */
    public void setCurrentCursor(int newCursor) {
        currentCursor = newCursor;
        setCursor(Cursor.getPredefinedCursor(currentCursor));
    }

    /**
     * Prepares a new shape for drawing based on the current tool.
     */
    public void prepareNewShape() {
        model.Shape newShape = ShapeFactory.createShape(currentTool);
        if (newShape != null) {
            newShape.setShapeColor(shapeColor);
            newShape.setStrokeWidth(strokeWidth);
            shapeArray[shapeIndex] = newShape;
        }
    }

    /**
     * Undoes the last drawing action.
     */
    public void undo() {
        if (shapeIndex > 0)
            shapeIndex--;
        prepareNewShape();
        repaint();
    }


    /**
     * Fills the selected shape with a chosen color.
     */
    private void fillSelectedShape() {
        if (currentSelectedShape != null) {
            // Show a color chooser dialog to select a color.
            Color chosenColor = JColorChooser.showDialog(this, "Choose a color", currentSelectedShape.getFillColor());
            if (chosenColor != null) {
                // Apply the chosen color to the selected shape.
                currentSelectedShape.setFillColor(chosenColor);
                currentSelectedShape.setFilled(true);
                repaint();
            }
        } else {
            JOptionPane.showMessageDialog(this, "You must use SELECT TOOL to select a shape first.", "No Shape Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * This class represents a mouse event listener that handles various mouse actions
     * on the canvas, including drawing, resizing, selecting, and more.
     */
    public class MouseEventListener extends MouseAdapter {
        public static boolean isShiftDown = false;

        /**
         * Invoked when the mouse enters the canvas.
         *
         * @param e The MouseEvent that occurred.
         */
        @Override
        public void mouseEntered(MouseEvent e) {
            // Update mouse state and coordinates when the mouse enters the canvas.
            updateMouseStateWithCoordinates("enters", e);
        }

        /**
         * Invoked when the mouse exits the canvas.
         *
         * @param e The MouseEvent that occurred.
         */
        @Override
        public void mouseExited(MouseEvent e) {
            // Update mouse state and coordinates when the mouse exits the canvas.
            updateMouseStateWithCoordinates("exits", e);
        }

        /**
         * Invoked when the mouse is moved within the canvas.
         *
         * @param e The MouseEvent that occurred.
         */
        @Override
        public void mouseMoved(MouseEvent e) {
            // Update mouse state and coordinates when the mouse is moved.
            updateMouseStateWithCoordinates("is moved", e);
        }

        /**
         * Invoked when a mouse button is pressed on the canvas.
         *
         * @param e The MouseEvent that occurred.
         */
        @Override
        public void mousePressed(MouseEvent e) {
            // Update mouse state and coordinates when the mouse is pressed.
            updateMouseStateWithCoordinates("is pressed", e);
            // Initialize a new shape based on the current tool.
            initializeNewShapeWithCoordinates(e.getX(), e.getY());

            if (currentTool == Resize) {
                // Handle resizing when the Resize tool is selected.
                selectShapeAtCoordinates(e.getX(), e.getY());
                if (currentSelectedShape != null) {
                    resizingShape = true;
                    previousX = e.getX();
                    previousY = e.getY();
                }
            } else if (isDrawingWithTool(currentTool)) {
                // Prepare for a new shape drawing when using drawing tools (Eraser or Curve).
                prepareForNewShape();
            }
        }

        /**
         * Invoked when a mouse button is released on the canvas.
         *
         * @param e The MouseEvent that occurred.
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            resizingShape = false;
            // Update mouse state and coordinates when the mouse is released.
            updateMouseStateWithCoordinates("is released", e);

            int x = e.getX();
            int y = e.getY();

            switch (currentTool) {
                case Select:
                    // Handle shape selection when the Select tool is active.
                    selectShapeAtCoordinates(x, y);
                    break;
                case Fill:
                    // Fill the selected shape when the Fill tool is active.
                    fillSelectedShape();
                    break;
                case Resize:
                    // Handle resizing when the Resize tool is active.
                    selectShapeAtCoordinates(x, y);
                    break;
                default:
                    // Finalize drawing when using other tools.
                    finalizeDrawing(x, y);
                    break;
            }
        }

        /**
         * Invoked when the mouse is dragged on the canvas.
         *
         * @param e The MouseEvent that occurred.
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            if (currentSelectedShape != null && resizingShape) {
                // Handle shape resizing when dragging.
                // Calculate the mouse drag distance.
                int deltaX = e.getX() - previousX;
                int deltaY = e.getY() - previousY;

                // Check the type of the selected shape and call its resize method.
                if (currentSelectedShape instanceof model.Rectangle) {
                    model.Rectangle rectangle = (model.Rectangle) currentSelectedShape;
                    rectangle.resize(deltaX, deltaY);
                } else if (currentSelectedShape instanceof model.Oval) {
                    model.Oval oval = (model.Oval) currentSelectedShape;
                    oval.resize(deltaX, deltaY);
                } else if (currentSelectedShape instanceof model.Circle) {
                    model.Circle circle = (model.Circle) currentSelectedShape;
                    circle.resize(deltaX, deltaY);
                } else if (currentSelectedShape instanceof model.Square) {
                    model.Square square = (model.Square) currentSelectedShape;
                    square.resize(deltaX, deltaY);
                } else if (currentSelectedShape instanceof model.Triangle) {
                    model.Triangle triangle = (model.Triangle) currentSelectedShape;
                    triangle.setShouldRecalculateC(true);
                    triangle.resize(deltaX, deltaY);
                } else if (currentSelectedShape instanceof model.Cube) {
                    model.Cube cube = (model.Cube) currentSelectedShape;
                    cube.resize(deltaX, deltaY);
                } else if (currentSelectedShape instanceof model.RoundedRectangle) {
                    model.RoundedRectangle roundedRectangle = (model.RoundedRectangle) currentSelectedShape;
                    roundedRectangle.resize(deltaX, deltaY);
                }

                // Update previous coordinates.
                previousX = e.getX();
                previousY = e.getY();

                // Repaint the canvas to reflect changes.
                repaint();
                return;
            } else if(currentTool != Resize) {
                // Handle shape drawing or resizing (when not in Resize mode).
                isShiftDown = e.isShiftDown();
                model.Shape currentShape = shapeArray[shapeIndex];

                if (currentShape instanceof Oval) {
                    // Handle Oval shape drawing.
                    Oval oval = (Oval) currentShape;
                    int width = Math.abs(oval.getCoordinateB().getX() - oval.getCoordinateA().getX());
                    int height = Math.abs(oval.getCoordinateB().getY() - oval.getCoordinateA().getY());

                    if (isShiftDown) {
                        int size = Math.min(width, height);
                        oval.setWidth(size);
                        oval.setHeight(size);
                    } else {
                        oval.setWidth(width);
                        oval.setHeight(height);
                    }
                } else if (currentShape instanceof model.Rectangle) {
                    // Handle Rectangle shape drawing.
                    model.Rectangle rectangle = (model.Rectangle) currentShape;
                    int width = Math.abs(rectangle.getCoordinateB().getX() - rectangle.getCoordinateA().getX());
                    int height = Math.abs(rectangle.getCoordinateB().getY() - rectangle.getCoordinateA().getY());

                    if (isShiftDown) {
                        int size = Math.min(width, height);
                        rectangle.setWidth(size);
                        rectangle.setHeight(size);
                    } else {
                        rectangle.setWidth(width);
                        rectangle.setHeight(height);
                    }

                } else if (currentShape instanceof model.Circle) {
                    // Handle Circle shape drawing.
                    model.Circle circle = (model.Circle) currentShape;
                    int deltaX = circle.getCoordinateA().getX() - circle.getCoordinateB().getX();
                    int deltaY = circle.getCoordinateA().getY() - circle.getCoordinateB().getY();
                    circle.setRadius((int) (Math.sqrt((deltaX * deltaX) + (deltaY * deltaY)) / 2));
                }else if (currentShape instanceof model.Square) {
                    // Handle Square shape drawing.
                    model.Square square = (model.Square) currentShape;
                    int deltaX = square.getCoordinateB().getX() - square.getCoordinateA().getX();
                    int deltaY = square.getCoordinateB().getY() - square.getCoordinateA().getY();
                    square.setSide((int) Math.sqrt(deltaX * deltaX + deltaY * deltaY));
                }else if (currentShape instanceof model.RoundedRectangle) {
                    // Handle RoundedRectangle shape drawing.
                    model.RoundedRectangle roundedRectangle = (model.RoundedRectangle) currentShape;
                    int width = Math.abs(roundedRectangle.getCoordinateB().getX() - roundedRectangle.getCoordinateA().getX());
                    int height = Math.abs(roundedRectangle.getCoordinateB().getY() - roundedRectangle.getCoordinateA().getY());
                    roundedRectangle.setWidth(width);
                    roundedRectangle.setHeight(height);
                }

                // Update mouse state and coordinates when dragging.
                updateMouseStateWithCoordinates("is dragged", e);
                int x = e.getX();
                int y = e.getY();

                if (currentTool == ButtonController.Select) {
                    // Drag the selected shape when the Select tool is active.
                    dragSelectedShape(x, y);
                }  else {
                    // Draw with the current tool.
                    drawWithTool(x, y);
                }
            }
        }

        /**
         * Update the mouse state table with the specified mouse action and coordinates.
         *
         * @param mouseAction The description of the mouse action.
         * @param e           The MouseEvent containing mouse coordinates.
         */
        private void updateMouseStateWithCoordinates(String mouseAction, MouseEvent e) {
            VectorDrawingApp.setMouseStateTable(String.format("Mouse %s at：[%d,%d]", mouseAction, e.getX(), e.getY()));
        }

        /**
         * Initialize a new shape's coordinates with the given values.
         *
         * @param x The x-coordinate to set for the new shape.
         * @param y The y-coordinate to set for the new shape.
         */
        private void initializeNewShapeWithCoordinates(int x, int y) {
            if (shapeArray[shapeIndex] != null) {
            shapeArray[shapeIndex].setCoordinateA(x, y);
            shapeArray[shapeIndex].setCoordinateB(x, y);
        }}


        /**
         * Check if the current drawing state involves drawing with a tool (Eraser or Curve).
         *
         * @param state The current tool state.
         * @return True if drawing with a tool, false otherwise.
         */
        private boolean isDrawingWithTool(int state) {
            return state == ButtonController.Eraser || state == ButtonController.Curve;
        }

        /**
         * Prepare for a new shape by incrementing the shape index and creating a new shape.
         */
        private void prepareForNewShape() {
            shapeIndex++;
            prepareNewShape();
        }

        /**
         * Select a shape at the specified coordinates, if one exists, and handle selection logic.
         *
         * @param x The x-coordinate of the selection point.
         * @param y The y-coordinate of the selection point.
         */
        private void selectShapeAtCoordinates(int x, int y) {
            boolean shapeSelected = false;

            for (model.Shape shape : graphic.getShapes()) {
                if (shape.isClickPointInShape(new model.Point(x, y))) {
                    if (currentSelectedShape != null && currentSelectedShape == shape) {
                        // If the same shape is clicked again, deselect and exit
                        currentSelectedShape.setSelected(false);
                        currentSelectedShape = null;
                        repaint();
                        return;
                    }

                    if (currentSelectedShape != null) {
                        currentSelectedShape.setSelected(false);
                    }

                    currentSelectedShape = shape;
                    currentSelectedShape.setSelected(true);
                    shapeSelected = true;

                    previousX = x;
                    previousY = y;
                    repaint();
                    break;
                }
            }

            if (!shapeSelected && currentSelectedShape != null) {
                // If no shape is selected and a shape was previously selected, deselect the previous shape
                currentSelectedShape.setSelected(false);
                currentSelectedShape = null;
                repaint();
            }
        }

        /**
         * Finalize the drawing of the current shape with the specified ending coordinates.
         *
         * @param x The x-coordinate of the ending point.
         * @param y The y-coordinate of the ending point.
         */
        private void finalizeDrawing(int x, int y) {
            model.Shape currentShape = shapeArray[shapeIndex];
            if (isDrawingWithTool(currentTool)) {
                currentShape.setCoordinateA(x, y);
            }
            currentShape.setCoordinateB(x, y);

            graphic.addShape(currentShape);
            repaint();

            shapeIndex++;
            prepareNewShape();
        }

        /**
         * Drag the currently selected shape to the specified coordinates.
         *
         * @param x The x-coordinate to which the shape should be dragged.
         * @param y The y-coordinate to which the shape should be dragged.
         */
        private void dragSelectedShape(int x, int y) {

            if (currentSelectedShape != null) {
                if(currentSelectedShape instanceof model.Triangle){
                    model.Triangle triangle = (model.Triangle) currentSelectedShape;
                    triangle.setShouldRecalculateC(true);
                    currentSelectedShape.translate(x - previousX, y - previousY);
                    updatePreviousCoordinates(x, y);
                    repaint();
                }
                currentSelectedShape.translate(x - previousX, y - previousY);
                updatePreviousCoordinates(x, y);
                repaint();
            }
        }

        /**
         * Draw with the current tool at the specified coordinates.
         *
         * @param x The x-coordinate at which to draw.
         * @param y The y-coordinate at which to draw.
         */
        private void drawWithTool(int x, int y) {
            if (isDrawingWithTool(currentTool)) {
                shapeArray[shapeIndex].setCoordinateA(x, y);
                shapeArray[shapeIndex].setCoordinateB(x, y);
                if (shapeIndex > 0) {
                    shapeArray[shapeIndex - 1].setCoordinateA(x, y);
                }
                shapeIndex++;
                prepareNewShape();
            } else {
                shapeArray[shapeIndex].setCoordinateB(x, y);
            }
            repaint();
        }

        /**
         * Update the previous coordinates used for tracking mouse movement.
         *
         * @param x The current x-coordinate.
         * @param y The current y-coordinate.
         */
        private void updatePreviousCoordinates(int x, int y) {
            previousX = x;
            previousY = y;
        }

    }

    /**
     * This method is used to rotate the currently selected shape by a specified angle.
     *
     * @param rotationAngle The angle (in degrees) by which to rotate the selected shape.
     */
    public void rotateSelectedShape(int rotationAngle) {
        if (currentSelectedShape != null) {
            currentSelectedShape.rotate(rotationAngle);
            repaint();
        }
    }

    //Network
    /**
     * Draws shapes from a JSON array and adds them to the canvas.
     * Support 4 types of shapes including rectangle, ellipse, triangle and line.
     *
     * @param jsonArray JSON array containing shape information.
     */
    public void drawShapesFromJson(JsonArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonDrawing = jsonArray.getJsonObject(i);
            JsonObject properties = jsonDrawing.getJsonObject("properties");

            String type = jsonDrawing.getString("type");
            if ("rectangle".equals(type)) {
                try {
                    int x = jsonDrawing.getInt("x");
                    int y = jsonDrawing.getInt("y");
                    int width = properties.getInt("width");
                    int height = properties.getInt("height");

                    Color fillColor = new Color(0, 0, 0, 0);
                    Color lineColor = new Color(0, 0, 0, 0);
                    Color borderColor = new Color(0, 0, 0, 0);
                    int borderWidth;

                    // Create and add a rectangle to the canvas
                    model.Rectangle rect = new model.Rectangle();

                    if (properties.containsKey("lineColor")) {
                        fillColor = new Color(0, 0, 0, 0);
                        lineColor = getColorFromString(properties.getString("lineColor"));

                        rect.setCoordinateA(x,y);
                        rect.setCoordinateB(x,y);
                        rect.setWidth(width);
                        rect.setHeight(height);

                        rect.setFilled(true);
                        rect.setFillColor(fillColor);
                        rect.setShapeColor(lineColor);
                        addShapeToCanvas(rect);// Add the rectangle to the canvas
                        graphic.addShape(rect);
                    }

                    if (properties.containsKey("borderColor")) {
                        borderColor = getColorFromString(properties.getString("borderColor"));
                        fillColor = getColorFromString(properties.getString("fillColor"));
                        int rotationAngle = properties.getInt("rotation");

                        if (properties.containsKey("borderWidth")) {
                            borderWidth = properties.getInt("borderWidth");
                            rect.setStrokeWidth(borderWidth);
                        }

                        rect.setCoordinateA(x,y);
                        rect.setCoordinateB(x,y);
                        rect.setWidth(width);
                        rect.setHeight(height);

                        rect.setFilled(true);
                        rect.setFillColor(fillColor);
                        rect.setShapeColor(borderColor);
                        rect.setRotationAngle(rotationAngle);

                        addShapeToCanvas(rect); // Add the rectangle to the canvas
                        graphic.addShape(rect);
                    }
                } catch (Exception e) {
                    System.out.println("Error processing JSON object: " + e.getMessage());
                }
            }

            if ("ellipse".equals(type)) {
                try {
                    int x = jsonDrawing.getInt("x");
                    int y = jsonDrawing.getInt("y");
                    int width = properties.getInt("width");
                    int height = properties.getInt("height");

                    Color fillColor = new Color(0, 0, 0, 0);
                    Color lineColor = new Color(0, 0, 0, 0);
                    Color borderColor = new Color(0, 0, 0, 0);
                    int borderWidth;

                    model.Oval oval = new model.Oval();

                    if (properties.containsKey("lineColor")) {
                        fillColor = new Color(0, 0, 0, 0);
                        lineColor = getColorFromString(properties.getString("lineColor"));

                        oval.setCoordinateA(x,y);
                        oval.setCoordinateB(x,y);
                        oval.setWidth(width);
                        oval.setHeight(height);

                        oval.setFilled(true);
                        oval.setFillColor(fillColor);
                        oval.setShapeColor(lineColor);
                        addShapeToCanvas(oval);
                        graphic.addShape(oval);
                    }

                    if (properties.containsKey("borderColor")) {
                        borderColor = getColorFromString(properties.getString("borderColor"));
                        fillColor = getColorFromString(properties.getString("fillColor"));
                        int rotationAngle = properties.getInt("rotation");

                        if (properties.containsKey("borderWidth")) {
                            borderWidth = properties.getInt("borderWidth");
                            oval.setStrokeWidth(borderWidth);
                        }

                        oval.setCoordinateA(x,y);
                        oval.setCoordinateB(x,y);
                        oval.setWidth(width);
                        oval.setHeight(height);

                        oval.setFilled(true);
                        oval.setFillColor(fillColor);
                        oval.setShapeColor(borderColor);
                        oval.setRotationAngle(rotationAngle);

                        addShapeToCanvas(oval);
                        graphic.addShape(oval);
                    }
                } catch (Exception e) {
                    System.out.println("Error processing JSON object: " + e.getMessage());
                }
            }

            if ("triangle".equals(type)) {
                try {
                    int x = jsonDrawing.getInt("x");
                    int y = jsonDrawing.getInt("y");
                    int x2 = properties.getInt("x2");
                    int y2 = properties.getInt("y2");
                    int x3 = properties.getInt("x3");
                    int y3 = properties.getInt("y3");

                    Color borderColor = getColorFromString(properties.getString("borderColor"));
                    Color fillColor = getColorFromString(properties.getString("fillColor"));
                    int rotationAngle = properties.getInt("rotation");
                    int borderWidth = properties.getInt("borderWidth");

                    model.Triangle triangle = new model.Triangle();

                    triangle.setStrokeWidth(borderWidth);
                    triangle.setCoordinateA(x, y);
                    triangle.setCoordinateB(x2, y2);
                    triangle.setCoordinateC(x3, y3);

                    triangle.setFilled(true);
                    triangle.setFillColor(fillColor);
                    triangle.setShapeColor(borderColor);
                    triangle.setRotationAngle(rotationAngle);
                    addShapeToCanvas(triangle);
                    graphic.addShape(triangle);
                } catch (Exception e) {
                    System.out.println("Error processing JSON object: " + e.getMessage());
                }
            }

            if ("line".equals(type)) {
                try {
                    int x = jsonDrawing.getInt("x");
                    int y = jsonDrawing.getInt("y");
                    int x2 = properties.getInt("x2");
                    int y2 = properties.getInt("y2");
                    int lineWidth = properties.getInt("lineWidth");
                    Color lineColor = getColorFromString(properties.getString("lineColor"));

                    model.Line line = new model.Line();


                    line.setCoordinateA(x, y);
                    line.setCoordinateB(x2, y2);
                    line.setStrokeWidth(lineWidth);
                    line.setShapeColor(lineColor);
                    addShapeToCanvas(line);
                    graphic.addShape(line);
                }catch (Exception e) {
                    System.out.println("Error processing JSON object: " + e.getMessage());
                }
            }
        }
        repaint(); // Repaint the canvas to display newly drawn shapes
    }

    /**
     * Adds a shape to the canvas if the shape array is not full.
     *
     * @param shape The shape to add to the canvas.
     */
    private void addShapeToCanvas(Shape shape) {

        // Check if the array is already full
        if (shapeIndex >= MAX_NUMS_OF_SHAPES) {
            // If the array is full, print a message or handle this situation as needed
            System.out.println("Shape array is full, cannot add more shapes.");
            return;
        }

        // Add the new shape to the array at the current index
        shapeArray[shapeIndex] = shape;

        // Update the index to prepare for the next shape
        shapeIndex = shapeIndex + 1;
    }


    /**
     * Sets the currently selected shape.
     *
     * @param shape The shape to set as the currently selected shape.
     */
    private void setCurrentSelectedShape(Shape shape) {
        this.currentSelectedShape = shape;
    }

    /**
     * Converts a color string into a Color object.
     *
     * @param colorStr The color string to convert.
     * @return The Color object representing the specified color string.
     */
    private Color getColorFromString(String colorStr) {
        // Check if it's a simple color string
        switch (colorStr.toLowerCase()) {
            case "black": return Color.BLACK;
            case "red": return Color.RED;
            case "blue": return Color.BLUE;
            case "green": return Color.GREEN;
            case "yellow": return Color.YELLOW;
            case "white": return Color.WHITE;
            case "orange": return Color.ORANGE;
            case "pink": return Color.PINK;
            case "cyan": return Color.CYAN;
            case "magenta": return Color.MAGENTA;
            case "gray": return Color.GRAY;
            case "darkgray": return Color.DARK_GRAY;
            case "lightgray": return Color.LIGHT_GRAY;
            case "purple": return new Color(128, 0, 128);
            case "brown": return new Color(165, 42, 42);
            default:
                // Check if it's a complex color format
                if (colorStr.startsWith("java.awt.color")) {
                    return parseComplexColorString(colorStr);
                }
                return Color.BLACK; // Default color
        }
    }

    /**
     * Parses a complex color string and converts it into a Color object.
     *
     * @param colorStr The complex color string to parse.
     * @return The Color object representing the parsed color.
     */
    private Color parseComplexColorString(String colorStr) {
        try {
            String[] parts = colorStr.split("\\[|\\]|,|=");
            int r = Integer.parseInt(parts[2]); // Red
            int g = Integer.parseInt(parts[4]); // Green
            int b = Integer.parseInt(parts[6]); // Blue
            return new Color(r, g, b);
        } catch (Exception e) {
            e.printStackTrace();
            return Color.BLACK; // Return default color in case of an error
        }
    }

    /**
     * Generates a JSON representation of the currently selected shape.
     *
     * @return The JSON representation of the selected shape.
     */
    public String getSelectedShapeJson() {
        if (currentSelectedShape == null) {
            return "No shape selected";
        }

        JsonObjectBuilder propertiesBuilder = Json.createObjectBuilder();
        String type = "";

        if (currentSelectedShape instanceof model.Rectangle) {
            model.Rectangle rectangle = (model.Rectangle) currentSelectedShape;
            type = "rectangle";
            propertiesBuilder.add("width", rectangle.getWidth())
                    .add("height", rectangle.getHeight())
                    .add("rotation", currentSelectedShape.getRotationAngle())
                    .add("borderColor", getColorAsString(currentSelectedShape.getShapeColor()))
                    .add("borderWidth", currentSelectedShape.getStrokeWidth())
                    .add("fillColor", getColorAsString(currentSelectedShape.getFillColor()));
        } else if (currentSelectedShape instanceof model.Oval) {
            model.Oval oval = (model.Oval) currentSelectedShape;
            type = "ellipse";
            propertiesBuilder.add("width", oval.getWidth())
                    .add("height", oval.getHeight())
                    .add("rotation", currentSelectedShape.getRotationAngle())
                    .add("borderColor", getColorAsString(currentSelectedShape.getShapeColor()))
                    .add("borderWidth", currentSelectedShape.getStrokeWidth())
                    .add("fillColor", getColorAsString(currentSelectedShape.getFillColor()));
        } else if (currentSelectedShape instanceof model.Triangle) {
            model.Triangle triangle = (model.Triangle) currentSelectedShape;
            type = "triangle";
            propertiesBuilder
                    .add("x2", triangle.getCoordinateB().getX())
                    .add("y2", triangle.getCoordinateB().getY())
                    .add("x3", triangle.getCoordinateCX())
                    .add("y3", triangle.getCoordinateCY())
                    .add("rotation", currentSelectedShape.getRotationAngle())
                    .add("borderColor", getColorAsString(currentSelectedShape.getShapeColor()))
                    .add("borderWidth", currentSelectedShape.getStrokeWidth())
                    .add("fillColor", getColorAsString(currentSelectedShape.getFillColor()));
        } else if (currentSelectedShape instanceof model.Line) {
            model.Line line = (model.Line) currentSelectedShape;
            type = "line";
            propertiesBuilder.add("x2", line.getCoordinateB().getX())
                    .add("y2", line.getCoordinateB().getY())
                    .add("lineColor", getColorAsString(currentSelectedShape.getShapeColor()))
                    .add("lineWidth", currentSelectedShape.getStrokeWidth());
        }
        else {
            return "Unsupported shape type";
        }

        JsonObject properties = propertiesBuilder.build();

        int x = currentSelectedShape.getCoordinateA().getX();
        int y = currentSelectedShape.getCoordinateA().getY();

        JsonObject jsonObject = Json.createObjectBuilder()
                .add("type", type)
                .add("x", x)
                .add("y", y)
                .add("properties", properties)
                .build();

        JsonObject uploadingObject = Json.createObjectBuilder()
                .add("action", "addDrawing")
                .add("data", jsonObject)
                .build();

        return uploadingObject.toString();
    }
}
