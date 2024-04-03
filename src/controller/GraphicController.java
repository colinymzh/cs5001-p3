package controller;

import java.util.ArrayList;

/**
 * The `GraphicController` class manages a collection of graphic shapes.
 */
public class GraphicController {
    // An ArrayList to store graphic shapes.
    private static final ArrayList<model.Shape> shapes = new ArrayList<model.Shape>();


    /**
     * Initializes a new instance of the `GraphicController` class.
     */
    public GraphicController() {}

    /**
     * Gets the list of graphic shapes.
     *
     * @return The list of graphic shapes.
     */
    public static ArrayList<model.Shape> getShapes() {return shapes;}

    /**
     * Adds a graphic shape to the list.
     *
     * @param shape The graphic shape to add.
     */
    public static void addShape(model.Shape shape) {shapes.add(shape);}


}
