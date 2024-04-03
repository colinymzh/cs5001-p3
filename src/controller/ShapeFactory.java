package controller;

import model.Circle;

import model.*;

/**
 * The `ShapeFactory` class is responsible for creating different types of shapes.
 */
public class ShapeFactory {
    /**
     * Creates a new shape based on the given state.
     *
     * @param state The state that determines the type of shape to create.
     * @return A new shape instance of the specified type, or null if the state is invalid.
     */
    public static model.Shape createShape(int state) {
        return switch (state) {
            case ButtonController.Eraser -> new Eraser();
            case ButtonController.Curve -> new Curve();
            case ButtonController.Line -> new Line();
            case ButtonController.Rectangle -> new model.Rectangle();
            case ButtonController.Square -> new Square();
            case ButtonController.Cube -> new Cube();
            case ButtonController.RoundedRectangle -> new RoundedRectangle();
            case ButtonController.Triangle -> new Triangle();
            case ButtonController.Oval -> new Oval();
            case ButtonController.Circle -> new Circle();
            default -> null;
        };
    }
}
