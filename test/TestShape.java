import org.junit.Test;

import cs5004Animator.*;
import cs5004Animator.Transformations.*;
import cs5004Animator.Shapes.*;

import static org.junit.Assert.assertEquals;

public class TestShape {

  //CREATING SHAPES
  //CIRCLE
  //shouldn't it take one argument for radius?
  @Test
  public void testCircleConstructor() {
    Shape s = new Circle("circle", 1, 25, 30, 35,
            Color.RED);

    assertEquals("Create red circle circle with center at (30, 35), "
            + "and radius 25 on layer 1.", s.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullNameCircle() {
    new Circle(null, 10, 10, 10, 10, Color.YELLOW);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullInitColor() {
    new Circle("circle", 10, 10, 10, 10, null);
  }

  //change to radius
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeRadiusHeight() {
    new Circle("circle", 10, -5, 10, 10, Color.YELLOW);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroInitialRadius() {
    new Circle("circle", 10, 0, 10, 10, Color.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCircleAddNullTransformation() {
    Shape c = new Circle("Circle", 0, 5, 5, 5, Color.WHITE);

    c.addTransformation(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCircleRemoveNullTransformation() {
    Shape c = new Circle("Circle", 0, 5, 5, 5, Color.WHITE);

    c.removeTransformation(null, 10, 10);
  }
  //OVAL
  @Test
  public void testOvalConstructor() {
    Shape o = new Oval("oval", -23, 25, 50, -10, -10, Color.WHITE);

    assertEquals("", o.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullNameOvalConstructor() {
    new Oval(null, 23, 25, 25, 25, 25, Color.WHITE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeVerticalRadiusOvalConstructor() {
    new Oval("oval", 0, -5, 10, 10, 10, Color.WHITE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroVerticalRadiusOvalConstructor() {
    new Oval("oval", 0, 0, 10, 10, 10, Color.WHITE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHorizontalRadiusOvalConstrucotr() {
    new Oval("oval", 0, 10, -10, 10, 10, Color.ORANGE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroHorizontalRadiusOvalConstructor() {
    new Oval("oval", 0, 10, 0, 10, 10, Color.PURPLE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullColorEnumOvalConstructor() {
    new Oval("oval", 0, 10, 10, 10, 10, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOvalAddNullTransformation() {
    Shape c = new Oval("Oval", 0, 5, 5,5, 5, Color.WHITE);

    c.addTransformation(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOvalRemoveNullTransformation() {
    Shape c = new Oval("Oval", 0, 5, 5,5, 5, Color.WHITE);

    c.removeTransformation(null, 10, 10);
  }

  //RECTANGLE
  @Test
  public void testRectangleConstructor() {
    Shape o = new Rectangle("rect", 100, 105, 220, -250, 325, Color.PURPLE);

    assertEquals("", o.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullNameRectangleConstructor() {
    new Rectangle(null, 0, 10, 10, 10, 10, Color.GREEN);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeightRectangleConstructor() {
    new Rectangle("rect", -5, -5, 10, 10, 10, Color.ORANGE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroHeightRectangleConstructor() {
    new Rectangle("rect", 10, 0, 10, 10, 10, Color.GREEN);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidthRectangleConstructor() {
    new Rectangle("rect", 10, 10, -5, 10, 10, Color.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroWidthRectangleConstructor() {
    new Rectangle("rect", 10, 10, 0, 10, 10, Color.BLACK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullColorEnumRectangleConstructor() {
    new Rectangle("rect", 10, 10, 10, 10, 10, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRectangleAddNullTransformation() {
    Shape c = new Rectangle("Rectangle", 0, 5, 5,5, 5, Color.WHITE);

    c.addTransformation(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRectangleRemoveNullTransformation() {
    Shape c = new Rectangle("Rectangle", 0, 5, 5,5, 5, Color.WHITE);

    c.removeTransformation(null, 10, 10);
  }

  //SQUARE
  @Test
  public void testSquareConstructor() {
    Shape s = new Square("square", -1001, 15, 12, 10, Color.BLUE);
    assertEquals("", s.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullNameSquareConstructor() {
    Shape s = new Square(null, 21, 20, 19, 18, Color.BLACK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeSideLengthSquareConstructor() {
    new Square("square", 21, -5, 15, 16, Color.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroSideLengthSquareConstructor() {
    new Square("squizzare", 100, 0, 15, 20, Color.YELLOW);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullColorSquareConstructor() {
    new Square("square", 100, 10, 10, 10, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSquareAddNullTransformation() {
    Shape c = new Square("Square", 0, 5, 5, 5, Color.WHITE);

    c.addTransformation(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSquareRemoveNullTransformation() {
    Shape c = new Square("Square", 0, 5, 5, 5, Color.WHITE);

    c.removeTransformation(null, 10, 10);
  }

  //TRIANGLE
  @Test
  public void testTriangleConstructor() {
    Shape t = new Triangle("triangle", -1234, 10, 15, 20, -25, Color.WHITE);

    assertEquals("", t.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullNameTriangleConstructor() {
    new Triangle(null, 10, 10, 10, 10, 10, Color.WHITE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeightTriangleConstructor() {
    new Triangle("triangle", 0, -10, 10, 10, 10, Color.WHITE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroHeightTriangleConstructor() {
    new Triangle("triangle", -433, 0, 10, 10, 10, Color.YELLOW);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidthTriangleConstructor() {
    new Triangle("triangle", 4, 10, -10, 10, 10, Color.BLUE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroWidthTriangleConstructor() {
    new Triangle("geometric three-way", 6, 10, 0, 10, 10, Color.GREEN);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullColorTriangleConstructor() {
    new Triangle("Triangle", 101, 20, 23, 50, 60, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTriangleAddNullTransformation() {
    Shape c = new Triangle("Triangle", 0, 5, 5,5, 5, Color.WHITE);

    c.addTransformation(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTriangleRemoveNullTransformation() {
    Shape c = new Triangle("Triangle", 0, 5, 5, 5, 5, Color.WHITE);

    c.removeTransformation(null, 10, 10);
  }

    /**
     * Test all methods on the Oval class with valid inputs.
     */
    @Test
    public void Test_Oval() {
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
      assertEquals("", oval1.getTransformationDescription());
      assertEquals("", oval1.toString());


      // Test removeTransformation() and getTransformationDescription() and getName().
      oval1.removeTransformation(t1.getType(), t1.getStart(), t1.getEnd());
      oval1.removeTransformation(t2.getType(), t2.getStart(), t2.getEnd());
      oval1.removeTransformation(t3.getType(), t3.getStart(), t3.getEnd());
      oval1.removeTransformation(t4.getType(), t4.getStart(), t4.getEnd());
      oval1.removeTransformation(t5.getType(), t5.getStart(), t5.getEnd());
      oval1.removeTransformation(t6.getType(), t6.getStart(), t6.getEnd());
      oval1.removeTransformation(t7.getType(), t7.getStart(), t7.getEnd());
      assertEquals("", oval1.getTransformationDescription());
      assertEquals("", oval1.toString());
    }

}
