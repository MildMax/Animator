import org.junit.Test;

import java.sql.SQLClientInfoException;
import java.util.Collections;

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

    assertEquals("circle", s.getName());
    assertEquals(1, s.getLayer());
    //assertEquals(50, s.getInitialHeight());
    assertEquals(30, s.getInitialCenterX());
    assertEquals(35, s.getInitialCenterY());
    assertEquals(Color.RED, s.getInitialColor());
    assertEquals("Create red circle circle with center at (30, 35), "
            + "and radius 25 on layer 1.", s.getCreateStatement());
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

    assertEquals("oval", o.getName());
    assertEquals(-23, o.getLayer());
    assertEquals(100, o.getInitialWidth());
    assertEquals(50, o.getInitialHeight());
    assertEquals(-10, o.getInitialCenterX());
    assertEquals(-10, o.getInitialCenterY());
    assertEquals(Color.WHITE, o.getInitialColor());
    assertEquals("", o.getCreateStatement());
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

    assertEquals("rect", o.getName());
    assertEquals(100, o.getLayer());
    assertEquals(105, o.getInitialHeight());
    assertEquals(220, o.getInitialWidth());
    assertEquals(-250, o.getInitialCenterX());
    assertEquals(325, o.getInitialCenterY());
    assertEquals(Color.PURPLE, o.getInitialColor());
    assertEquals("", o.getCreateStatement());
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

    assertEquals("square", s.getName());
    assertEquals(-1001, s.getLayer());
    assertEquals(15, s.getInitialWidth());
    assertEquals(15, s.getInitialHeight());
    assertEquals(12, s.getInitialCenterX());
    assertEquals(10, s.getInitialCenterY());
    assertEquals(Color.BLUE, s.getInitialColor());
    assertEquals("", s.getCreateStatement());
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

    assertEquals("triangle", t.getName());
    assertEquals(-1234, t.getLayer());
    assertEquals(10, t.getInitialHeight());
    assertEquals(15, t.getInitialWidth());
    assertEquals(20, t.getInitialCenterX());
    assertEquals(-25, t.getInitialCenterY());
    assertEquals(Color.WHITE, t.getInitialColor());
    assertEquals("", t.getCreateStatement());
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
}
