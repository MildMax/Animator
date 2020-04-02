import org.junit.Test;

import java.util.List;

import cs5004.animator.model.shapes.Shape;
import cs5004.animator.model.shapes.ShapeImpl;
import cs5004.animator.model.shapes.ShapeType;
import cs5004.animator.model.transformations.Transformation;
import cs5004.animator.model.transformations.TransformationImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests the ShapeImpl class.
 */
public class ShapeImplTest {

  /**
   * Test constructing a Shape. Test adding Shapes. Test adding Transformations to
   * the Shape. Test get methods on Shape. Test getShapeAtTick(). Test getTransformationList().
   * Test getTransformationDescription(). Test toString().
   */
  @Test
  public void testShapeImplConstructor() {
    Shape s = new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1);

    assertEquals("rectangle", s.getName());
    assertEquals(0, s.getWidth());
    assertEquals(0, s.getHeight());
    assertEquals(0, s.getR());
    assertEquals(0, s.getG());
    assertEquals(0, s.getB());
    assertEquals(0, s.getX());
    assertEquals(0, s.getY());
    assertEquals(0, s.getStart());
    assertEquals(0, s.getEnd());
    assertEquals(ShapeType.RECTANGLE, s.getType());
    assertEquals(1, s.getLayer());
    assertEquals("", s.getAppearStatement());
    assertEquals("", s.getCreateStatement());

    s.addTransformation(new TransformationImpl(s.getName(), 10, 10, 10, 20, 20,
            100, 100, 100, 20, 40, 40, 80, 80, 200, 200, 200));
    s.getShapeAtTick(10);

    assertEquals(20, s.getWidth());
    assertEquals(20, s.getHeight());
    assertEquals(100, s.getR());
    assertEquals(100, s.getG());
    assertEquals(100, s.getB());
    assertEquals(10, s.getX());
    assertEquals(10, s.getY());
    assertEquals(10, s.getStart());
    assertEquals(20, s.getEnd());
    assertEquals("Create rectangle rectangle with center at (10,10) "
            + "with width 20 and height 20 and color (100,100,100)\n", s.getCreateStatement());
    assertEquals("rectangle rectangle appears at time t=10 and disappears" +
            " at time t=20\n", s.getAppearStatement());

    s.getShapeAtTick(15);

    assertEquals(50, s.getWidth());
    assertEquals(50, s.getHeight());
    assertEquals(150, s.getR());
    assertEquals(150, s.getG());
    assertEquals(150, s.getB());
    assertEquals(25, s.getX());
    assertEquals(25, s.getY());
    assertEquals(10, s.getStart());
    assertEquals(20, s.getEnd());

    s.getShapeAtTick(20);

    assertEquals(80, s.getWidth());
    assertEquals(80, s.getHeight());
    assertEquals(200, s.getR());
    assertEquals(200, s.getG());
    assertEquals(200, s.getB());
    assertEquals(40, s.getX());
    assertEquals(40, s.getY());
    assertEquals(10, s.getStart());
    assertEquals(20, s.getEnd());

    s.addTransformation(new TransformationImpl(s.getName(),20, 40, 40, 80, 80,
            200, 200, 200, 30, 60, 50, 100, 120, 240, 230, 220));
    s.getShapeAtTick(25);

    assertEquals(90, s.getWidth());
    assertEquals(100, s.getHeight());
    assertEquals(220, s.getR());
    assertEquals(215, s.getG());
    assertEquals(210, s.getB());
    assertEquals(50, s.getX());
    assertEquals(45, s.getY());
    assertEquals(10, s.getStart());
    assertEquals(30, s.getEnd());

    List<Transformation> transformationList = s.getTransformationList();
    assertEquals(2, transformationList.size());
    assertEquals(10, transformationList.get(0).getStart());
    assertEquals(20, transformationList.get(0).getEnd());

    assertEquals(20, transformationList.get(1).getStart());
    assertEquals(30, transformationList.get(1).getEnd());

    String test = "rectangle rectangle moves from (10,10) to (40,40) from time t=10 to time t=20\n"
    + "rectangle changes width from 20 to 80 from time t=10 to time t=20\n"
    + "rectangle changes height from 20 to 80 from time t=10 to time t=20\n"
    + "rectangle changes from color (100,100,100) to color (200,200,200) "
    + "from time t=10 to time t=20\n\n"
    + "rectangle rectangle moves from (40,40) to (60,50) from time t=20 to time t=30\n"
    + "rectangle changes width from 80 to 100 from time t=20 to time t=30\n"
    + "rectangle changes height from 80 to 120 from time t=20 to time t=30\n"
    + "rectangle changes from color (200,200,200) to color (240,230,220)"
    + " from time t=20 to time t=30\n\n";

    assertEquals(test, s.getTransformationDescription());
  }

  /**
   * Test passing null shape name to ShapeImpl constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testShapeImplNullName() {
    new ShapeImpl(null, ShapeType.RECTANGLE, 0);
  }

  /**
   * Test passing null ShapeType to ShapeImpl constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testShapeImplNullType() {
    new ShapeImpl("name", null, 0);
  }

  /**
   * Test adding null transformation to shape.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testShapeImplAddNullTransformation() {
    Shape s = new ShapeImpl("shape", ShapeType.RECTANGLE, 1);
    s.addTransformation(null);
  }

  /**
   * Test adding an existing transformation to the shape when they have the same start time
   * and different end times.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testShapeImplAddExistingTransformation() {
    Shape s = new ShapeImpl("shape", ShapeType.RECTANGLE, 1);
    s.addTransformation(new TransformationImpl(s.getName(), 10, 10, 10, 15, 15,
            100, 100, 100, 20, 20, 20, 40, 40, 200, 200, 200));
    s.addTransformation(new TransformationImpl(s.getName(), 10, 10, 10, 10, 10, 10,
            10, 10, 25, 20, 20, 20, 20, 20, 20, 20));
  }

  /**
   * Test adding an existing transformation when they have the same end time but different
   * start times.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testShapeImplAddExistingTransformation1() {
    Shape s = new ShapeImpl("shape", ShapeType.RECTANGLE, 1);
    s.addTransformation(new TransformationImpl(s.getName(), 10, 10, 10, 15, 15,
            100, 100, 100, 20, 20, 20, 40, 40, 200, 200, 200));
    s.addTransformation(new TransformationImpl(s.getName(), 5, 10, 10, 10, 10, 10,
            10, 10, 20, 20, 20, 20, 20, 20, 20, 20));
  }

  /**
   * Test adding a transformation tha already exists when they have the same start and end time.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testShapeImplAddExistingTransformation2() {
    Shape s = new ShapeImpl("shape", ShapeType.RECTANGLE, 1);
    s.addTransformation(new TransformationImpl(s.getName(), 10, 10, 10, 15, 15,
            100, 100, 100, 20, 20, 20, 40, 40, 200, 200, 200));
    s.addTransformation(new TransformationImpl(s.getName(), 10, 10, 10, 10, 10, 10,
            10, 10, 20, 20, 20, 20, 20, 20, 20, 20));
  }

  /**
   * Test adding a transformation when the new start and end times exist within the existing
   * transformation's start and end times.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testShapeImplAddExistingTransformation3() {
    Shape s = new ShapeImpl("shape", ShapeType.RECTANGLE, 1);
    s.addTransformation(new TransformationImpl(s.getName(), 10, 10, 10, 15, 15,
            100, 100, 100, 20, 20, 20, 40, 40, 200, 200, 200));
    s.addTransformation(new TransformationImpl(s.getName(), 11, 10, 10, 10, 10, 10,
            10, 10, 19, 20, 20, 20, 20, 20, 20, 20));
  }

  /**
   * Test getting shapes at a tick when the shape doesn't appear yet.
   */
  @Test
  public void  testShapeImplGetShapesAtTickEarly() {
    Shape s = new ShapeImpl("shape", ShapeType.RECTANGLE, 1);
    s.addTransformation(new TransformationImpl(s.getName(), 10, 10, 10, 15, 15,
            100, 100, 100, 20, 20, 20, 40, 40, 200, 200, 200));
    assertEquals(null, s.getShapeAtTick(5));
  }

  /**
   * Test getting shape when tick is after the time the Shape disappears.
   */
  @Test
  public void testShapeImplGetShapesAtTickLate() {
    Shape s = new ShapeImpl("shape", ShapeType.RECTANGLE, 1);
    s.addTransformation(new TransformationImpl(s.getName(), 10, 10, 10, 15, 15,
            100, 100, 100, 20, 20, 20, 40, 40, 200, 200, 200));
    assertEquals(null, s.getShapeAtTick(25));
  }

  /**
   * Test getting shape at tick when there are no transformations.
   */
  @Test
  public void testShapeImplGetShapeAtTickNoTransformations() {
    Shape s = new ShapeImpl("shape", ShapeType.RECTANGLE, 1);
    assertEquals(null, s.getShapeAtTick(s.getStart()));
  }

}
