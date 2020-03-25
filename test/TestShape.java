import org.junit.Test;

import cs5004Animator.Color;
import cs5004Animator.Shapes.Circle;
import cs5004Animator.Shapes.Oval;
import cs5004Animator.Shapes.Rectangle;
import cs5004Animator.Shapes.Shape;
import cs5004Animator.Shapes.Square;
import cs5004Animator.Shapes.Triangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
    new Oval("oval", 0, 10, 10, 10, 10, Color.YELLOW);
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
  public void testNullColorTriangleConsctructor() {
    new Triangle("Triangle", 101, 20, 23, 50, 60, null);
  }

  @Test
  public void testGetClass() {
    Shape triangle = new Triangle("1", 2, 3, 4, 5, 6, Color.GREEN);
    Shape circle = new Circle("2", 2, 2, 2, 2, Color.WHITE);
    Shape oval = new Oval("3", 2, 2, 2, 2, 2, Color.WHITE);
    Shape square = new Square("4", 2, 2, 2, 2, Color.WHITE);
    Shape rect = new Rectangle("5", 2, 2, 2, 2, 2, Color.BLUE);

    assertEquals(false, circle.getClass() == oval.getClass());
    assertEquals(false, rect.getClass() == square.getClass());
    assertEquals(false, triangle.getClass() == rect.getClass());
  }
}
