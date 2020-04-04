import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

import cs5004.animator.EasyAnimator;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.shapes.Shape;
import cs5004.animator.model.shapes.ShapeImpl;
import cs5004.animator.model.shapes.ShapeType;
import cs5004.animator.model.transformations.TransformationImpl;

/**
 * This class tests the AnimationModelImpl class.
 */
public class AnimationModelImplTest {

  /**
   * Test creating an empty AnimationModel. Test adding shapes to the animation model. Test adding
   * transformations to the shapes in the animation model. Test getting the shapes in the animation
   * at different ticks in the animation. Test getting total ticks in the animation. Test
   * toString output representation of the animation. Test getMethods in the AnimationModel.
   * Test getting the shapes in the animation.
   */
  @Test
  public void testAnimationModelImpl() {
    AnimationModel m = new AnimationModelImpl(0, 100, 200, 300);

    assertEquals(0, m.getBoundX());
    assertEquals(100, m.getBoundY());
    assertEquals(200, m.getWindowWidth());
    assertEquals(300, m.getWindowHeight());
    assertEquals(0, m.getTotalTicks());
    assertEquals(25, m.getAnimationHeight());
    assertEquals(25, m.getAnimationWidth());

    String test = "Create window with width 200 and height 300 with top left corner "
            + "(0,100) and total ticks 0";

    assertEquals(test, m.toString());

    m.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    m.addShape(new ShapeImpl("ellipse", ShapeType.ELLIPSE, 2));

    test = "Create window with width 200 and height 300 with top left corner "
            + "(0,100) and total ticks 0";

    assertEquals(test, m.toString());
    assertEquals(2, m.getShapes().size());
    assertEquals("rectangle", m.getShapes().get(0).getName());
    assertEquals("ellipse", m.getShapes().get(1).getName());
    assertEquals(25, m.getAnimationHeight());
    assertEquals(25, m.getAnimationWidth());

    m.addTransformation("rectangle", new TransformationImpl("rectangle", 10,
            20, -20, 20, 40, 100, 100, 100, 20, 40, -40,
            40, 60, 200, 200, 200));
    m.addTransformation("ellipse", new TransformationImpl("ellipse", 20,
            0, 0, 10, 30, 50, 50, 50, 40, 40, -40,
            40, 60, 150, 150, 150));

    assertEquals(2, m.getTransformations().size());
    assertEquals(10, m.getTransformations().get(0).getStart());
    assertEquals(20, m.getTransformations().get(1).getStart());
    assertEquals(40, m.getTotalTicks());

    List<Shape> sList = m.getShapesAtTick(20);

    assertEquals("rectangle", sList.get(0).getName());
    assertEquals(40, sList.get(0).getX());
    assertEquals(-40, sList.get(0).getY());
    assertEquals(40, sList.get(0).getWidth());
    assertEquals(60, sList.get(0).getHeight());
    assertEquals(200, sList.get(0).getR());
    assertEquals(200, sList.get(0).getG());
    assertEquals(200, sList.get(0).getB());

    assertEquals("ellipse", sList.get(1).getName());
    assertEquals(0, sList.get(1).getX());
    assertEquals(0, sList.get(1).getY());
    assertEquals(10, sList.get(1).getWidth());
    assertEquals(30, sList.get(1).getHeight());
    assertEquals(50, sList.get(1).getR());
    assertEquals(50, sList.get(1).getG());
    assertEquals(50, sList.get(1).getB());

    test = "Create window with width 200 and height 300 with top left corner "
            + "(0,100) and total ticks 40\n\n"
            + "Create rectangle rectangle with center at (20,-20) with width 20 "
            + "and height 40 and color (100,100,100)\n"
            + "Create ellipse ellipse with center at (0,0) with width 10 and "
            + "height 30 and color (50,50,50)\n\n"
            + "rectangle rectangle appears at time t=10 and disappears at time t=20\n"
            + "ellipse ellipse appears at time t=20 and disappears at time t=40\n\n"
            + "rectangle moves from (20,-20) to (40,-40) from time t=10 to time t=20\n"
            + "rectangle changes width from 20 to 40 from time t=10 to time t=20\n"
            + "rectangle changes height from 40 to 60 from time t=10 to time t=20\n"
            + "rectangle changes from color (100,100,100) to color (200,200,200) "
            + "from time t=10 to time t=20\n"
            + "ellipse moves from (0,0) to (40,-40) from time t=20 to time t=40\n"
            + "ellipse changes width from 10 to 40 from time t=20 to time t=40\n"
            + "ellipse changes height from 30 to 60 from time t=20 to time t=40\n"
            + "ellipse changes from color (50,50,50) to color (150,150,150) from "
            + "time t=20 to time t=40";

    assertEquals(test, m.toString());
  }

  /**
   * Test creating an AnimationModelImpl with negative width.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelImplNegativeWidth() {
    new AnimationModelImpl(0, 0, -10, 500);
  }

  /**
   * Test creating an AnimationModelImpl with negative height.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelImplNegativeHeight() {
    new AnimationModelImpl(0, 0, 500, -10);
  }

  /**
   * Test adding a null Shape value to addShape in AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelImplAddNullShape() {
    AnimationModel m = new AnimationModelImpl(0, 0, 100, 100);
    m.addShape(null);
  }

  /**
   * Test adding an existing shape to the AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelImplAddExistingShape() {
    AnimationModel m = new AnimationModelImpl(0, 0, 100, 100);
    m.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    m.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
  }

  /**
   * Test adding a transformation with a null String name value to AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelImplAddTransformationNullName() {
    AnimationModel m = new AnimationModelImpl(0, 0, 100, 100);
    m.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    m.addTransformation(null, new TransformationImpl("rectangle", 0, 0, 0,
            10, 20, 100, 100, 100, 20, 10, 10, 60, 60,
            200, 200, 200));
  }

  /**
   * Test adding a transformation with a null Transformation value to the AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelImplAddTransformationNullTransformation() {
    AnimationModel m = new AnimationModelImpl(0, 0, 100, 100);
    m.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    m.addTransformation("rectangle", null);
  }

  /**
   * Test adding a transformation that already exists to a Shape in the AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelImplAddTransformationExisting() {
    AnimationModel m = new AnimationModelImpl(0, 0, 100, 100);
    m.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    m.addTransformation("rectangle", new TransformationImpl("rectangle", 0, 0, 0,
            10, 20, 100, 100, 100, 20, 10, 10, 60, 60,
            200, 200, 200));
    m.addTransformation("rectangle", new TransformationImpl("rectangle", 0, 0, 0,
            10, 20, 100, 100, 100, 30, 10, 10, 60, 60,
            200, 200, 200));
  }

  /**
   * Test getting shapes at a negative tick value from AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelImplGetShapesNegativeTick() {
    AnimationModel m = new AnimationModelImpl(0,0, 100, 100);
    m.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    m.getShapesAtTick(-5);
  }

  /**
   * Test getting shapes at a tick past the total frame length of the animation from
   * AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelImplGetShapesHighTick() {
    AnimationModel m = new AnimationModelImpl(0,0, 100, 100);
    m.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    m.addTransformation("rectangle", new TransformationImpl("rectangle", 0, 0, 0,
            10, 20, 100, 100, 100, 20, 10, 10, 60, 60,
            200, 200, 200));
    assertEquals(20, m.getTotalTicks());
    m.getShapesAtTick(21);
  }

  @Test
  public void generic() {
    EasyAnimator.main(new String[]{"start", "end"});
  }

}
