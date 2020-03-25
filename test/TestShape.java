import org.junit.Test;
import cs5004Animator.Color;
import cs5004Animator.Shapes.Circle;
import cs5004Animator.Shapes.Oval;
import cs5004Animator.Shapes.Rectangle;
import cs5004Animator.Shapes.Shape;
import cs5004Animator.Shapes.Square;
import cs5004Animator.Shapes.Triangle;
import cs5004Animator.Transformations.Appearance;
import cs5004Animator.Transformations.ChangeColor;
import cs5004Animator.Transformations.ChangeHeight;
import cs5004Animator.Transformations.ChangeTransparency;
import cs5004Animator.Transformations.ChangeWidth;
import cs5004Animator.Transformations.Move;
import cs5004Animator.Transformations.Scale;
import static org.junit.Assert.assertEquals;

/**
 * Create a test class TestShape to test all of the shape classes.
 */
public class TestShape {

  /**
   * Test the circle constructor with valid inputs.
   */
  @Test
  public void testCircleConstructor() {
    Shape s = new Circle("circle", 1, 25, 30, 35,
            Color.RED);
    assertEquals("Create red circle circle with center at (30, 35) and radius 25 on "
            + "layer 1.\n"
            + "\n",
            s.toString());
  }

  /**
   * Test creating a circle with a null value for name; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullNameCircle() {
    Circle shape1 = new Circle(null, 10, 10, 10, 10,
            Color.YELLOW);
    assertEquals("", shape1.toString());
  }

  /**
   * Test creating a circle with a null value for color; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullInitColor() {
    Circle shape1 = new Circle("circle", 10, 10, 10,
            10,null);
    assertEquals("", shape1.toString());
  }

  /**
   * Test creating a circle with a negative radius; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeRadiusHeight() {
    Circle shape1 = new Circle("circle", 10, -5, 10,
            10, Color.YELLOW);
    assertEquals("", shape1.toString());
  }

  /**
   * Test creating; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testZeroInitialRadius() {
    Circle shape1 = new Circle("circle", 10, 0, 10,
            10, Color.RED);
    assertEquals("", shape1.toString());
  }

  /**
   * Test adding a null transformation; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCircleAddNullTransformation() {
    Shape c = new Circle("Circle", 0, 5, 5, 5,
            Color.WHITE);
    c.addTransformation(null);
    assertEquals("", c.toString());
  }

  /**
   * Test removing a null transformation; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCircleRemoveNullTransformation() {
    Shape c = new Circle("Circle", 0, 5, 5, 5,
            Color.WHITE);
    c.removeTransformation(null, 10, 10);
    assertEquals("", c.toString());
  }

  /**
   * Test oval constructor with valid parameters.
   */
  @Test
  public void testOvalConstructor() {
    Shape o = new Oval("oval", -23, 25, 50,
            -10, -10, Color.WHITE);
    assertEquals("Create white oval oval with center at (-10, -10), horizontal radius "
            + "50, and vertical radius 25 on layer -23.\n\n", o.toString());
  }

  /**
   * Test creation of an oval where name is null; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullNameOvalConstructor() {
    Oval shape1 = new Oval(null, 23, 25, 25,
            25, 25, Color.WHITE);
    assertEquals("", shape1.toString());
  }

  /**
   * Test creation of an oval with negative vertical radius; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeVerticalRadiusOvalConstructor() {
    Oval shape1 = new Oval("oval", 0, -5, 10,
            10, 10, Color.WHITE);
    assertEquals("", shape1.toString());
  }

  /**
   * Test creation of an oval with vertical radius = 0; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testZeroVerticalRadiusOvalConstructor() {
    Oval shape1 = new Oval("oval", 0, 0, 10,
            10, 10, Color.WHITE);
    assertEquals("", shape1.toString());
  }

  /**
   * Test creation of an oval with negative horizontal radius; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHorizontalRadiusOvalConstrucotr() {
    Oval shape1 = new Oval("oval", 0, 10, -10,
            10, 10, Color.ORANGE);
    assertEquals("", shape1.toString());
  }

  /**
   * Test creation of an oval with horizontal radius = 0; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testZeroHorizontalRadiusOvalConstructor() {
    Oval shape1 = new Oval("oval", 0, 10, 0,
            10, 10, Color.PURPLE);
    assertEquals("", shape1.toString());
  }

  /**
   * Test creation of an oval with color = null; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullColorEnumOvalConstructor() {
    Oval shape1 = new Oval("oval", 0, 10, 10,
            10, 10, null);
    assertEquals("", shape1.toString());
  }

  /**
   * Test adding a null transformation to an oval; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOvalAddNullTransformation() {
    Shape c = new Oval("Oval", 0, 5, 5,
            5, 5, Color.WHITE);
    c.addTransformation(null);
    assertEquals("", c.toString());
  }

  /**
   * Test removing a null transformation from an oval; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOvalRemoveNullTransformation() {
    Shape c = new Oval("Oval", 0, 5, 5,
            5, 5, Color.WHITE);
    c.removeTransformation(null, 10, 10);
    assertEquals("", c.toString());
  }

  /**
   * Test the Rectangle constructor with valid inputs.
   */
  @Test
  public void testRectangleConstructor() {
    Shape o = new Rectangle("rect", 100, 105, 220,
            -250, 325, Color.PURPLE);
    assertEquals("Create purple rectangle rect with center at (-250, 325), width 220, and "
            + "height 105 on layer 100.\n\n", o.toString());
  }

  /**
   * Test creation of a rectangle with name = null; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullNameRectangleConstructor() {
    Rectangle shape1 = new Rectangle(null, 0, 10, 10,
            10, 10, Color.GREEN);
    assertEquals("", shape1.toString());
  }

  /**
   * Test creation of a rectangle with negative height; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeightRectangleConstructor() {
    Rectangle shape1 = new Rectangle("rect", -5, -5, 10,
            10, 10, Color.ORANGE);
    assertEquals("", shape1.toString());
  }

  /**
   * Test creation of a rectangle with height = 0; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testZeroHeightRectangleConstructor() {
    Rectangle shape1 = new Rectangle("rect", 10, 0, 10,
            10, 10, Color.GREEN);
    assertEquals("", shape1.toString());
  }

  /**
   * Test creation of a rectangle with negative width; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidthRectangleConstructor() {
    Rectangle shape1 = new Rectangle("rect", 10, 10, -5,
            10, 10, Color.RED);
    assertEquals("", shape1.toString());
  }

  /**
   * Test creation of a rectangle with width = 0; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testZeroWidthRectangleConstructor() {
    Rectangle shape1 = new Rectangle("rect", 10, 10, 0,
            10, 10, Color.BLACK);
    assertEquals("", shape1.toString());
  }

  /**
   * Test creation of a rectangle with color = null; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullColorEnumRectangleConstructor() {
    Rectangle shape1 = new Rectangle("rect", 10, 10, 10,
            10, 10, null);
    assertEquals("", shape1.toString());
  }

  /**
   * Test adding a null transformation to a rectangle; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRectangleAddNullTransformation() {
    Shape c = new Rectangle("Rectangle", 0, 5, 5,
            5, 5, Color.WHITE);
    c.addTransformation(null);
    assertEquals("", c.toString());
  }

  /**
   * Test removing a null transformation from a rectangle; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRectangleRemoveNullTransformation() {
    Shape c = new Rectangle("Rectangle", 0, 5, 5,
            5, 5, Color.WHITE);
    c.removeTransformation(null, 10, 10);
    assertEquals("", c.toString());
  }

  /**
   * Test the Square constructor with valid inputs.
   */
  @Test
  public void testSquareConstructor() {
    Shape s = new Square("square", -1001, 15, 12, 10,
            Color.BLUE);
    assertEquals("Create blue square square with center at (12, 10) and side 15 on "
            + "layer -1001.\n\n", s.toString());
  }

  /**
   * Test creating a square with name = null; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullNameSquareConstructor() {
    Shape s = new Square(null, 21, 20, 19, 18,
            Color.BLACK);
    assertEquals("", s.toString());
  }

  /**
   * Test creating a square with negative size length; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeSideLengthSquareConstructor() {
    Square shape1 = new Square("square", 21, -5, 15,
            16, Color.RED);
    assertEquals("", shape1.toString());
  }

  /**
   * Test creating a square with side length = 0; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testZeroSideLengthSquareConstructor() {
    Square shape1 = new Square("squizzare", 100, 0, 15,
            20, Color.YELLOW);
    assertEquals("", shape1.toString());
  }

  /**
   * Test creating a square with color = null; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullColorSquareConstructor() {
    Square shape1 = new Square("square", 100, 10, 10,
            10, null);
    assertEquals("", shape1.toString());
  }

  /**
   * Test adding a null transformation to a square; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSquareAddNullTransformation() {
    Shape c = new Square("Square", 0, 5, 5, 5,
            Color.WHITE);
    c.addTransformation(null);
    assertEquals("", c.toString());
  }

  /**
   * Test removing a null transformation from a square; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSquareRemoveNullTransformation() {
    Shape c = new Square("Square", 0, 5, 5, 5,
            Color.WHITE);
    c.removeTransformation(null, 10, 10);
    assertEquals("", c.toString());
  }

  /**
   * Test the Triangle constructor with valid inputs.
   */
  @Test
  public void testTriangleConstructor() {
    Shape t = new Triangle("triangle", -1234, 10, 15,
            20, -25, Color.WHITE);
    assertEquals("Create white triangle triangle with center at (20, -25), width 15, "
            + "and height 10 on layer -1234.\n\n", t.toString());
  }

  /**
   * Test creating a triangle with name = null; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullNameTriangleConstructor() {
    Triangle shape1 = new Triangle(null, 10, 10, 10,
            10, 10, Color.WHITE);
    assertEquals("", shape1.toString());
  }

  /**
   * Test creating a triangle with negative height; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeightTriangleConstructor() {
    Triangle shape1 = new Triangle("triangle", 0, -10, 10,
            10, 10, Color.WHITE);
    assertEquals("", shape1.toString());
  }

  /**
   * Test creating a triangle with height = 0; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testZeroHeightTriangleConstructor() {
    Triangle shape1 = new Triangle("triangle", -433, 0, 10,
            10, 10, Color.YELLOW);
    assertEquals("", shape1.toString());
  }

  /**
   * Test creating a triangle with negative width; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidthTriangleConstructor() {
    Triangle shape1 = new Triangle("triangle", 4, 10, -10,
            10, 10, Color.BLUE);
    assertEquals("", shape1.toString());
  }

  /**
   * Test creating a triangle with width = 0; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testZeroWidthTriangleConstructor() {
    Triangle shape1 = new Triangle("geometric three-way", 6, 10,
            0, 10, 10, Color.GREEN);
    assertEquals("", shape1.toString());
  }

  /**
   * Test creating a triangle with color = null; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullColorTriangleConstructor() {
    Triangle shape1 = new Triangle("Triangle", 101, 20, 23,
            50, 60, null);
    assertEquals("", shape1.toString());
  }

  /**
   * Test adding a null transformation to a triangle; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTriangleAddNullTransformation() {
    Shape c = new Triangle("Triangle", 0, 5, 5,5,
            5, Color.WHITE);
    c.addTransformation(null);
    assertEquals("", c.toString());
  }

  /**
   * Test removing a null transformation from a triangle; exception should result.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTriangleRemoveNullTransformation() {
    Shape c = new Triangle("Triangle", 0, 5, 5, 5,
            5, Color.WHITE);
    c.removeTransformation(null, 10, 10);
    assertEquals("", c.toString());
  }

  /**
   * Test all methods on the Oval class with valid inputs.
   */
  @Test
  public void Test_Oval_Valid_All() {
    // Test the shape's Constructor.
    Oval oval1 = new Oval("Oval1", 1, 50, 100,
            150, 200, Color.BLUE);

    // Create some transformations to test adding/removing.
    Appearance t1 = new Appearance(1, 1000);
    ChangeColor t2 = new ChangeColor(5, 10, Color.BLUE);
    ChangeHeight t3 = new ChangeHeight(15, 20, 150);
    ChangeTransparency t4 = new ChangeTransparency(25, 30, 50);
    ChangeWidth t5 = new ChangeWidth(35, 40, 75);
    Move t6 = new Move(45, 50, 151, 201);
    Scale t7 = new Scale(55, 60, 2.0);

    // Test getName().
    assertEquals( "Oval1", oval1.getName());

    // Test addTransformation() and getTransformationDescription() and getName().
    oval1.addTransformation(t1);
    oval1.addTransformation(t2);
    oval1.addTransformation(t3);
    oval1.addTransformation(t4);
    oval1.addTransformation(t5);
    oval1.addTransformation(t6);
    oval1.addTransformation(t7);
    assertEquals("Oval1 appears at time t=1 and disappears at time t=1000.\n"
                    + "Oval1 changes to blue from time t=5 to time t=10.\n"
                    + "Oval1 changes to height:150 from time t=15 to time t=20.\n"
                    + "Oval1 changes to transparency 50.0 from time t=25 to time t=30.\n"
                    + "Oval1 changes to width:75 from time t=35 to time t=40.\n"
                    + "Oval1 moves to (151,201) from time t=45 to time t=50.\n"
                    + "Oval1 scales to 2.0 times its current size from time t=55 to time t=60.\n",
                      oval1.getTransformationDescription());
    assertEquals("Create blue oval Oval1 with center at (150, 200), horizontal radius "
                    + "100, and vertical radius 50 on layer 1.\n\n"
                    + "Oval1 appears at time t=1 and disappears at time t=1000.\n"
                    + "Oval1 changes to blue from time t=5 to time t=10.\n"
                    + "Oval1 changes to height:150 from time t=15 to time t=20.\n"
                    + "Oval1 changes to transparency 50.0 from time t=25 to time t=30.\n"
                    + "Oval1 changes to width:75 from time t=35 to time t=40.\n"
                    + "Oval1 moves to (151,201) from time t=45 to time t=50.\n"
                    + "Oval1 scales to 2.0 times its current size from time t=55 to time t=60.\n",
                      oval1.toString());

    // Test removeTransformation() and getTransformationDescription() and getName().
    oval1.removeTransformation(t1.getType(), t1.getStart(), t1.getEnd());
    oval1.removeTransformation(t2.getType(), t2.getStart(), t2.getEnd());
    oval1.removeTransformation(t3.getType(), t3.getStart(), t3.getEnd());
    oval1.removeTransformation(t4.getType(), t4.getStart(), t4.getEnd());
    oval1.removeTransformation(t5.getType(), t5.getStart(), t5.getEnd());
    oval1.removeTransformation(t6.getType(), t6.getStart(), t6.getEnd());
    oval1.removeTransformation(t7.getType(), t7.getStart(), t7.getEnd());
    assertEquals("", oval1.getTransformationDescription());
    assertEquals("Create blue oval Oval1 with center at (150, 200), horizontal radius "
                    + "100, and vertical radius 50 on layer 1.\n\n", oval1.toString());
  }

  /**
   * Test all methods on the Circle class with valid inputs.
   */
  @Test
  public void Test_Circle_Valid_All() {
    // Test the shape's Constructor.
    Circle myShape = new Circle("Circle1", 1, 99, 150,
            200, Color.ORANGE);

    // Create some transformations to test adding/removing.
    Appearance t1 = new Appearance(1, 1000);
    ChangeColor t2 = new ChangeColor(5, 10, Color.BLUE);
    ChangeHeight t3 = new ChangeHeight(15, 20, 150);
    ChangeTransparency t4 = new ChangeTransparency(25, 30, 50);
    ChangeWidth t5 = new ChangeWidth(35, 40, 75);
    Move t6 = new Move(45, 50, 151, 201);
    Scale t7 = new Scale(55, 60, 2.0);

    // Test getName().
    assertEquals( "Circle1", myShape.getName());

    // Test addTransformation() and getTransformationDescription() and getName().
    myShape.addTransformation(t1);
    myShape.addTransformation(t2);
    myShape.addTransformation(t3);
    myShape.addTransformation(t4);
    myShape.addTransformation(t5);
    myShape.addTransformation(t6);
    myShape.addTransformation(t7);
    assertEquals("Circle1 appears at time t=1 and disappears at time t=1000.\n"
                    + "Circle1 changes to blue from time t=5 to time t=10.\n"
                    + "Circle1 changes to height:150 from time t=15 to time t=20.\n"
                    + "Circle1 changes to transparency 50.0 from time t=25 to time t=30.\n"
                    + "Circle1 changes to width:75 from time t=35 to time t=40.\n"
                    + "Circle1 moves to (151,201) from time t=45 to time t=50.\n"
                    + "Circle1 scales to 2.0 times its current size from time t=55 to time t=60.\n",
                      myShape.getTransformationDescription());
    assertEquals("Create orange circle Circle1 with center at (150, 200) and radius 99 "
                    + "on layer 1.\n\n"
                    + "Circle1 appears at time t=1 and disappears at time t=1000.\n"
                    + "Circle1 changes to blue from time t=5 to time t=10.\n"
                    + "Circle1 changes to height:150 from time t=15 to time t=20.\n"
                    + "Circle1 changes to transparency 50.0 from time t=25 to time t=30.\n"
                    + "Circle1 changes to width:75 from time t=35 to time t=40.\n"
                    + "Circle1 moves to (151,201) from time t=45 to time t=50.\n"
                    + "Circle1 scales to 2.0 times its current size from time t=55 to time t=60.\n",
                      myShape.toString());

    // Test removeTransformation() and getTransformationDescription() and getName().
    myShape.removeTransformation(t1.getType(), t1.getStart(), t1.getEnd());
    myShape.removeTransformation(t2.getType(), t2.getStart(), t2.getEnd());
    myShape.removeTransformation(t3.getType(), t3.getStart(), t3.getEnd());
    myShape.removeTransformation(t4.getType(), t4.getStart(), t4.getEnd());
    myShape.removeTransformation(t5.getType(), t5.getStart(), t5.getEnd());
    myShape.removeTransformation(t6.getType(), t6.getStart(), t6.getEnd());
    myShape.removeTransformation(t7.getType(), t7.getStart(), t7.getEnd());
    assertEquals("", myShape.getTransformationDescription());
    assertEquals("Create orange circle Circle1 with center at (150, 200) and radius 99 "
            + "on layer 1.\n\n", myShape.toString());
  }

  /**
   * Test all methods on the Rectangle class with valid inputs.
   */
  @Test
  public void Test_Rectangle_Valid_All() {
    // Test the shape's Constructor.
    Rectangle myShape = new Rectangle("Rectangle1", 1, 25, 25,
            150,200, Color.ORANGE);

    // Create some transformations to test adding/removing.
    Appearance t1 = new Appearance(1, 1000);
    ChangeColor t2 = new ChangeColor(5, 10, Color.YELLOW);
    ChangeHeight t3 = new ChangeHeight(15, 20, 150);
    ChangeTransparency t4 = new ChangeTransparency(25, 30, 50);
    ChangeWidth t5 = new ChangeWidth(35, 40, 75);
    Move t6 = new Move(45, 50, 151, 201);
    Scale t7 = new Scale(55, 60, 2.0);

    // Test getName().
    assertEquals( "Rectangle1", myShape.getName());

    // Test addTransformation() and getTransformationDescription() and getName().
    myShape.addTransformation(t1);
    myShape.addTransformation(t2);
    myShape.addTransformation(t3);
    myShape.addTransformation(t4);
    myShape.addTransformation(t5);
    myShape.addTransformation(t6);
    myShape.addTransformation(t7);
    assertEquals("Rectangle1 appears at time t=1 and disappears at time t=1000.\n"
                    + "Rectangle1 changes to yellow from time t=5 to time t=10.\n"
                    + "Rectangle1 changes to height:150 from time t=15 to time t=20.\n"
                    + "Rectangle1 changes to transparency 50.0 from time t=25 to time t=30.\n"
                    + "Rectangle1 changes to width:75 from time t=35 to time t=40.\n"
                    + "Rectangle1 moves to (151,201) from time t=45 to time t=50.\n"
                    + "Rectangle1 scales to 2.0 times its current size from time t=55 to time "
                    + "t=60.\n", myShape.getTransformationDescription());
    assertEquals("Create orange rectangle Rectangle1 with center at (150, 200), width "
                    + "25, and height 25 on layer 1.\n\n"
                    + "Rectangle1 appears at time t=1 and disappears at time t=1000.\n"
                    + "Rectangle1 changes to yellow from time t=5 to time t=10.\n"
                    + "Rectangle1 changes to height:150 from time t=15 to time t=20.\n"
                    + "Rectangle1 changes to transparency 50.0 from time t=25 to time t=30.\n"
                    + "Rectangle1 changes to width:75 from time t=35 to time t=40.\n"
                    + "Rectangle1 moves to (151,201) from time t=45 to time t=50.\n"
                    + "Rectangle1 scales to 2.0 times its current size from time t=55 to time "
                    + "t=60.\n", myShape.toString());

    // Test removeTransformation() and getTransformationDescription() and getName().
    myShape.removeTransformation(t1.getType(), t1.getStart(), t1.getEnd());
    myShape.removeTransformation(t2.getType(), t2.getStart(), t2.getEnd());
    myShape.removeTransformation(t3.getType(), t3.getStart(), t3.getEnd());
    myShape.removeTransformation(t4.getType(), t4.getStart(), t4.getEnd());
    myShape.removeTransformation(t5.getType(), t5.getStart(), t5.getEnd());
    myShape.removeTransformation(t6.getType(), t6.getStart(), t6.getEnd());
    myShape.removeTransformation(t7.getType(), t7.getStart(), t7.getEnd());
    assertEquals("", myShape.getTransformationDescription());
    assertEquals("Create orange rectangle Rectangle1 with center at (150, 200), width "
            + "25, and height 25 on layer 1.\n\n", myShape.toString());
  }

  /**
   * Test all methods on the Square class with valid inputs.
   */
  @Test
  public void Test_Square_Valid_All() {
    // Test the shape's Constructor.
    Square myShape = new Square("Square1", 1, 33, 150,
            200, Color.GREEN);

    // Create some transformations to test adding/removing.
    Appearance t1 = new Appearance(1, 1000);
    ChangeColor t2 = new ChangeColor(5, 10, Color.BLUE);
    ChangeHeight t3 = new ChangeHeight(15, 20, 150);
    ChangeTransparency t4 = new ChangeTransparency(25, 30, 50);
    ChangeWidth t5 = new ChangeWidth(35, 40, 75);
    Move t6 = new Move(45, 50, 151, 201);
    Scale t7 = new Scale(55, 60, 2.0);

    // Test getName().
    assertEquals( "Square1", myShape.getName());

    // Test addTransformation() and getTransformationDescription() and getName().
    myShape.addTransformation(t1);
    myShape.addTransformation(t2);
    myShape.addTransformation(t3);
    myShape.addTransformation(t4);
    myShape.addTransformation(t5);
    myShape.addTransformation(t6);
    myShape.addTransformation(t7);
    assertEquals("Square1 appears at time t=1 and disappears at time t=1000.\n"
            + "Square1 changes to blue from time t=5 to time t=10.\n"
            + "Square1 changes to height:150 from time t=15 to time t=20.\n"
            + "Square1 changes to transparency 50.0 from time t=25 to time t=30.\n"
            + "Square1 changes to width:75 from time t=35 to time t=40.\n"
            + "Square1 moves to (151,201) from time t=45 to time t=50.\n"
            + "Square1 scales to 2.0 times its current size from time t=55 to time t=60.\n",
            myShape.getTransformationDescription());
    assertEquals("Create green square Square1 with center at (150, 200) and side 33 on "
            + "layer 1.\n\n"
            + "Square1 appears at time t=1 and disappears at time t=1000.\n"
            + "Square1 changes to blue from time t=5 to time t=10.\n"
            + "Square1 changes to height:150 from time t=15 to time t=20.\n"
            + "Square1 changes to transparency 50.0 from time t=25 to time t=30.\n"
            + "Square1 changes to width:75 from time t=35 to time t=40.\n"
            + "Square1 moves to (151,201) from time t=45 to time t=50.\n"
            + "Square1 scales to 2.0 times its current size from time t=55 to time t=60.\n",
            myShape.toString());

    // Test removeTransformation() and getTransformationDescription() and getName().
    myShape.removeTransformation(t1.getType(), t1.getStart(), t1.getEnd());
    myShape.removeTransformation(t2.getType(), t2.getStart(), t2.getEnd());
    myShape.removeTransformation(t3.getType(), t3.getStart(), t3.getEnd());
    myShape.removeTransformation(t4.getType(), t4.getStart(), t4.getEnd());
    myShape.removeTransformation(t5.getType(), t5.getStart(), t5.getEnd());
    myShape.removeTransformation(t6.getType(), t6.getStart(), t6.getEnd());
    myShape.removeTransformation(t7.getType(), t7.getStart(), t7.getEnd());
    assertEquals("", myShape.getTransformationDescription());
    assertEquals("Create green square Square1 with center at (150, 200) and side 33 on "
            + "layer 1.\n\n", myShape.toString());
  }

  /**
   * Test all methods on the Triangle class with valid inputs.
   */
  @Test
  public void Test_Triangle_Valid_All() {
    // Test the shape's Constructor.
    Triangle myShape = new Triangle("Triangle1", 1, 100, 65,
            150,200, Color.PURPLE);

    // Create some transformations to test adding/removing.
    Appearance t1 = new Appearance(1, 1000);
    ChangeColor t2 = new ChangeColor(5, 10, Color.BLUE);
    ChangeHeight t3 = new ChangeHeight(15, 20, 150);
    ChangeTransparency t4 = new ChangeTransparency(25, 30, 50);
    ChangeWidth t5 = new ChangeWidth(35, 40, 75);
    Move t6 = new Move(45, 50, 151, 201);
    Scale t7 = new Scale(55, 60, 2.0);

    // Test getName().
    assertEquals( "Triangle1", myShape.getName());

    // Test addTransformation() and getTransformationDescription() and getName().
    myShape.addTransformation(t1);
    myShape.addTransformation(t2);
    myShape.addTransformation(t3);
    myShape.addTransformation(t4);
    myShape.addTransformation(t5);
    myShape.addTransformation(t6);
    myShape.addTransformation(t7);
    assertEquals("Triangle1 appears at time t=1 and disappears at time t=1000.\n"
            + "Triangle1 changes to blue from time t=5 to time t=10.\n"
            + "Triangle1 changes to height:150 from time t=15 to time t=20.\n"
            + "Triangle1 changes to transparency 50.0 from time t=25 to time t=30.\n"
            + "Triangle1 changes to width:75 from time t=35 to time t=40.\n"
            + "Triangle1 moves to (151,201) from time t=45 to time t=50.\n"
            + "Triangle1 scales to 2.0 times its current size from time t=55 to time t=60.\n",
            myShape.getTransformationDescription());
    assertEquals("Create purple triangle Triangle1 with center at (150, 200), width 65, "
            + "and height 100 on layer 1.\n\n"
            + "Triangle1 appears at time t=1 and disappears at time t=1000.\n"
            + "Triangle1 changes to blue from time t=5 to time t=10.\n"
            + "Triangle1 changes to height:150 from time t=15 to time t=20.\n"
            + "Triangle1 changes to transparency 50.0 from time t=25 to time t=30.\n"
            + "Triangle1 changes to width:75 from time t=35 to time t=40.\n"
            + "Triangle1 moves to (151,201) from time t=45 to time t=50.\n"
            + "Triangle1 scales to 2.0 times its current size from time t=55 to time t=60.\n",
            myShape.toString());

    // Test removeTransformation() and getTransformationDescription() and getName().
    myShape.removeTransformation(t1.getType(), t1.getStart(), t1.getEnd());
    myShape.removeTransformation(t2.getType(), t2.getStart(), t2.getEnd());
    myShape.removeTransformation(t3.getType(), t3.getStart(), t3.getEnd());
    myShape.removeTransformation(t4.getType(), t4.getStart(), t4.getEnd());
    myShape.removeTransformation(t5.getType(), t5.getStart(), t5.getEnd());
    myShape.removeTransformation(t6.getType(), t6.getStart(), t6.getEnd());
    myShape.removeTransformation(t7.getType(), t7.getStart(), t7.getEnd());
    assertEquals("", myShape.getTransformationDescription());
    assertEquals("Create purple triangle Triangle1 with center at (150, 200), width 65, "
            + "and height 100 on layer 1.\n\n", myShape.toString());
  }
}
