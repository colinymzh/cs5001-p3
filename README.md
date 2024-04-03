# Vector Drawing Software

This project is a vector drawing application implemented following the Model-View-Controller (MVC) design pattern, ensuring a well-organized and maintainable code structure.

## 1. Features

- **Model**: Represents the drawing elements like Circle, Square, Triangle, etc., that can be manipulated by the user.

- **View**: Manages the representation of information in a particular format, facilitated by classes like `VectorDrawingApp`.

- **Controller**: Handles the input from the user and converts it into commands for the model or view, including classes like `ButtonController`, `CanvasPanelController`, and `GraphicController`.

- **Utils**: Provides utility services such as `CanvasSaver` for saving work and `ColorMapping` for color manipulation.

- **Tests**: Contains unit tests for each model component ensuring the reliability and stability of the application.

  

- **Requirements have been met:**

  - Drawing straight lines, rectangles, ellipses, and triangles.
  - Undo/Redo functionality.
  - Customizable border/line color, width, and fill colors for each shape.
  - Rotation of shapes.
  - Squares and circles drawing support with aspect ratio lock using the Shift key.
  - Export drawings as JPEG or PNG files.
  - Select and modify previously drawn objects' location, color, or size.
  - Network sharing of drawings with other users.


## 2. Installation

1. Clone the repository to your local machine.
2. Navigate to the project's root directory.
3. Compile the source code using IDE that supports Java.

## 3. Usage

To start the application, run the `VectorDrawingMain` class  and launches the main application window.

![image-20231124133105531](C:\Users\YIMING\AppData\Roaming\Typora\typora-user-images\image-20231124133105531.png)

### Menu Bar

- **File**: Export drawings as PNG or JPG formats.

- **Color**: Custom color settings for shapes.

- **Undo**: Revert the last action.

  ![image-20231124134011677](C:\Users\YIMING\AppData\Roaming\Typora\typora-user-images\image-20231124134011677.png)

### Button Toolbar

- **Functionality Area** (Left Side):

  ![image-20231124134030329](C:\Users\YIMING\AppData\Roaming\Typora\typora-user-images\image-20231124134030329.png)

  - **New Canvas:** Start a fresh drawing.

  - **Undo:** Revert the last action.

  - **Line/Border Color:** Adjust the color of lines or borders. You can set the line color at first, then the shape you draw is the color you set. You also can change the color. You need to select the shapes first, and click the shape you want to change the line color.

  - **Select Tool:** Move and resize shapes.

    Select the shape, then you can see the shape has been selected. Then you can drag the selected shape, change the line width, set the rotation angle, set the border color or fill color.

    <img src="C:\Users\YIMING\AppData\Roaming\Typora\typora-user-images\image-20231124134419807.png" alt="image-20231124134419807" style="zoom:50%;" />

  - **Resize Tool:** Choose this tool and drag the shapes to resize.

  - **Fill Color:** Change the fill color of shapes. You need to select the shapes first, and click the shape you want to change the fill color.

    <img src="C:\Users\YIMING\AppData\Roaming\Typora\typora-user-images\image-20231124135235606.png" alt="image-20231124135235606" style="zoom:50%;" />

- **Drawing Area** (Center):
  
  ![image-20231124134040092](C:\Users\YIMING\AppData\Roaming\Typora\typora-user-images\image-20231124134040092.png)

  - **Shape Tools:** Create various shapes including squares and circles. Use the Shift key to lock aspect ratio for rectangles and ellipses.
  - **Eraser:** Remove parts of your drawing.
  
- **Network Operations** (Right Side):
  
  ![image-20231124134053650](C:\Users\YIMING\AppData\Roaming\Typora\typora-user-images\image-20231124134053650.png)
  
  - **Login:** Authenticate to enable network features.
  
    <img src="C:\Users\YIMING\AppData\Roaming\Typora\typora-user-images\image-20231124134536003.png" alt="image-20231124134536003" style="zoom:50%;" />
  
    <img src="C:\Users\YIMING\AppData\Roaming\Typora\typora-user-images\image-20231124134550992.png" alt="image-20231124134550992" style="zoom:50%;" />
  
    If the token is not right:
  
    <img src="C:\Users\YIMING\AppData\Roaming\Typora\typora-user-images\image-20231124134837190.png" alt="image-20231124134837190" style="zoom:50%;" />
  
  - **Get All Drawings:** Retrieve shared drawings.
  
    <img src="C:\Users\YIMING\AppData\Roaming\Typora\typora-user-images\image-20231124134605692.png" alt="image-20231124134605692" style="zoom:50%;" />
  
    All drawings have been get:
  
    <img src="C:\Users\YIMING\AppData\Roaming\Typora\typora-user-images\image-20231124134633706.png" alt="image-20231124134633706" style="zoom:50%;" />
  
  - **Add Drawing:** Upload new drawing to the network. You need to first select the shape using Select Tool, and click the add drawing button.
  
    <img src="C:\Users\YIMING\AppData\Roaming\Typora\typora-user-images\image-20231124134731848.png" alt="image-20231124134731848" style="zoom:50%;" />
  
    <img src="C:\Users\YIMING\AppData\Roaming\Typora\typora-user-images\image-20231124134748001.png" alt="image-20231124134748001" style="zoom:50%;" />
  
    If the shape is not selected:
  
    <img src="C:\Users\YIMING\AppData\Roaming\Typora\typora-user-images\image-20231124134924254.png" alt="image-20231124134924254" style="zoom:50%;" />
  
  - **Update Drawing:** Modify an existing network drawing. These shape is added by you.
  
    <img src="C:\Users\YIMING\AppData\Roaming\Typora\typora-user-images\image-20231124134944674.png" alt="image-20231124134944674" style="zoom:50%;" />
  
    <img src="C:\Users\YIMING\AppData\Roaming\Typora\typora-user-images\image-20231124135016983.png" alt="image-20231124135016983" style="zoom:50%;" />
  
    
  
  - **Delete Drawing:** Remove a drawing from the network.
  
    <img src="C:\Users\YIMING\AppData\Roaming\Typora\typora-user-images\image-20231124135033007.png" alt="image-20231124135033007" style="zoom:50%;" />
  
  - (Note: Add, Update, and Delete are enabled after login).

### Scrollbar Area

<img src="C:\Users\YIMING\AppData\Roaming\Typora\typora-user-images\image-20231124134507031.png" alt="image-20231124134507031" style="zoom:50%;" /><img src="C:\Users\YIMING\AppData\Roaming\Typora\typora-user-images\image-20231124134429891.png" alt="image-20231124134429891" style="zoom:50%;" />

- **Line Width**: Adjust the border thickness of selected shapes.
- **Rotation**: Set the rotation angle of selected shapes.

## 4. Shape Tests

### Introduction

This project includes JUnit tests for various shape classes such as `Circle`, `Rectangle`, `Triangle`, `Square`, `RoundedRectangle`, etc.

### How to Run the Tests

#### Prerequisites

- Java Development Kit (JDK), JDK 11 or higher.
- An Integrated Development Environment (IDE) that supports JUnit 5 or a build tool like Maven or Gradle.

#### Running Tests in an IDE

1. Open IDE and import the project.
2. Ensure that the project is configured with JUnit 5 dependencies.
3. Navigate to the directory where the test classes are located (under `src/test`).
4. You can run each test class individually, or right-click on the test directory and choose to run all tests.

#### Running Tests with Maven

If your project is set up with Maven, you can follow these steps to run the tests:

1. Open a command line tool.
2. Change to the root directory of the project.
3. Execute the following command:

```bash
mvn test
```

This will automatically run all the test cases.

#### Running Tests with Gradle

If your project is set up with Gradle, you can follow these steps to run the tests:

1. Open a command line tool.
2. Change to the root directory of the project.
3. Execute the following command:

```
gradle test
```

This will automatically run all the test cases.

## 5. Demo

A demo video file has been provided.

## 6. Notes

Due to button icons may not be displayed on some system, so the button icon images are replaced by the plain text in the final version.
