import org.junit.Test;
import org.junit.Before;

import java.util.List;

import cs5004animator.model.AnimationModel;
import cs5004animator.model.AnimationModelImpl;
import cs5004animator.model.Color;
import cs5004animator.model.shapes.Circle;
import cs5004animator.model.shapes.Oval;
import cs5004animator.model.shapes.Rectangle;
import cs5004animator.model.shapes.Shape;
import cs5004animator.model.shapes.Square;
import cs5004animator.model.shapes.Triangle;
import cs5004animator.model.transformations.Appearance;
import cs5004animator.model.transformations.ChangeColor;
import cs5004animator.model.transformations.ChangeHeight;
import cs5004animator.model.transformations.ChangeTransparency;
import cs5004animator.model.transformations.ChangeWidth;
import cs5004animator.model.transformations.Move;
import cs5004animator.model.transformations.Scale;
import cs5004animator.model.transformations.TransformationType;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the methods and constructors in the AnimationModelImpl class.
 */
public class TestAnimationModelImpl {

  Color green;

  /**
   * Initializes the Color green to be used in throughout the test.
   */
  @Before
  public void setUp() {
    this.green = new Color(0.0, 1.0, 0.0);
  }

  /**
   * Tests creating an AnimationModel with a constructor that takes no arguments.
   */
  @Test
  public void testAnimationModelImplNoArgConstructor() {
    AnimationModel m = new AnimationModelImpl();

    assertEquals("Create window with width 500 and height 500 "
                    + "with background color 0.0,0.0,0.0 and total ticks 0.",
            m.toString());
  }

  /**
   * Tests creating an AnimationModelImpl with a constructor that takes two arguments
   * indicating width and height of the window.
   */
  @Test
  public void testAnimationModelImplTwoArgConstructor() {
    AnimationModel m = new AnimationModelImpl(100, 20);

    assertEquals("Create window with width 100 and height 20 with"
                    + " background color 0.0,0.0,0.0 and total ticks 0.",
            m.toString());
  }

  /**
   * Tests creating an AnimationModelImpl with a constructor that takes two arguments
   * and the specified width is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelImplNegativeWidth() {
    new AnimationModelImpl(-10, 10);
  }

  /**
   * Test creating an AnimationModelImpl with a constructor that takes two arguments
   * and the specified height is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelImplNegativeHeight() {
    new AnimationModelImpl(10, -10);
  }

  /**
   * Test creating an AnimationModelImpl with a constructor that takes one argument
   * indicating the color of the window background.
   */
  @Test
  public void testAnimationModelImplOneArgConstructor() {
    AnimationModel m = new AnimationModelImpl(new Color(0, 0, 0));

    assertEquals("Create window with width 500 and height 500"
            + " with background color 0.0,0.0,0.0 and total ticks 0.",
            m.toString());
  }

  /**
   * Test creating an AnimationModelImpl with a constructor that takes one argument
   * with a null Color value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelOneArgNullColor() {
    new AnimationModelImpl(null);
  }

  /**
   * Test creating an AnimationModelImpl object with a constructor that takes three
   * arguments indicating window width, height and background color.
   */
  @Test
  public void testAnimationModelThreeArgConstructor() {
    AnimationModel m = new AnimationModelImpl(100, 50, new Color(1, 1, 1));

    assertEquals(100, m.getWindowWidth());
    assertEquals(50, m.getWindowHeight());
    assertEquals("1.0,1.0,1.0", m.getBackgroundColor().toString());

    assertEquals("Create window with width 100 and height 50 with background"
            + " color 1.0,1.0,1.0 and total ticks 0.", m.toString());
  }

  /**
   * Test creating an AnimationModelImpl object when the specified width is 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelThreeArgZeroWidth() {
    new AnimationModelImpl(0, 100, new Color(1, 1, 1));
  }

  /**
   * Test creating an AnimationModelImpl object when the specified height is 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelThreeArgZeroHeight() {
    new AnimationModelImpl(100, 0, new Color(0,1,0));
  }

  /**
   * Test creating an AnimationModelImpl object when the specified Color value is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelThreeArgNullColor() {
    new AnimationModelImpl(100, 100, null);
  }

  /**
   * Test adding a shape to the AnimationModelImpl object.
   */
  @Test
  public void testAnimationModelAddShape() {
    AnimationModel m = new AnimationModelImpl();

    String test = "Create window with width 500 and height 500 with background color 0.0,0.0,0.0 "
            + "and total ticks 0.";

    assertEquals(test, m.toString());

    m.addShape(new Circle("C", 0, 10, 0, 0, green));

    test = "Create window with width 500 and height 500 with background color 0.0,0.0,0.0 "
            + "and total ticks 0.\n\n"
            + "Create circle C with center at (0,0) and radius 10 on layer 0 "
            + "with color 0.0,1.0,0.0.";

    assertEquals(test, m.toString());
  }

  /**
   * Test adding a null shape to AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelAddShapeNullShape() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(null);
  }

  /**
   * Test adding a shape that already exists to the AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelAddExistingShape() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
  }

  /**
   * Test removing a shape from the AnimationModelImpl.
   */
  @Test
  public void testAnimationModelRemoveShape() {
    AnimationModel m = new AnimationModelImpl();

    String test = "Create window with width 500 and height 500 with background color 0.0,0.0,0.0 "
            + "and total ticks 0.";

    assertEquals(test, m.toString());

    m.addShape(new Circle("C", 0, 10, 0, 0, green));

    test = "Create window with width 500 and height 500 with background color 0.0,0.0,0.0 "
            + "and total ticks 0.\n\n"
            + "Create circle C with center at (0,0) and radius 10 on layer 0 "
            + "with color 0.0,1.0,0.0.";

    assertEquals(test, m.toString());
    assertEquals(1, m.getShapes().size());

    m.removeShape("C");

    test = "Create window with width 500 and height 500 with background color 0.0,0.0,0.0 "
            + "and total ticks 0.";

    assertEquals(test, m.toString());
    assertEquals(0, m.getShapes().size());
  }

  /**
   * Test removing a null value for shape from AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelRemoveShapeNullShape() {
    AnimationModel m = new AnimationModelImpl();

    m.removeShape(null);
  }

  /**
   * Test removing a shape that does not exist from AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelRemoveShapeNonExistent() {
    AnimationModel m = new AnimationModelImpl();

    m.removeShape("circle");
  }

  /**
   * Test adding a transformation to a shape in AnimationModelImpl.
   */
  @Test
  public void testAddTransformation() {
    AnimationModel m = new AnimationModelImpl();

    String test = "Create window with width 500 and height 500 with background color 0.0,0.0,0.0 "
            + "and total ticks 0.";

    assertEquals(test, m.toString());
    assertEquals(0, m.getTransformations().size());

    m.addShape(new Circle("C", 0, 10, 0, 0, green));
    m.addTransformation("C", new Appearance(0, 10));

    test = "Create window with width 500 and height 500 "
            + "with background color 0.0,0.0,0.0 and total ticks 10.\n\n"
            + "Create circle C with center at (0,0) and radius 10 on layer 0 "
            + "with color 0.0,1.0,0.0.\n\n"
            + "C appears at time t=0 and disappears at time t=10.";

    assertEquals(test, m.toString());
    assertEquals(1, m.getTransformations().size());
  }

  /**
   * Test adding a transformation to a shape with a null name value in
   * AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationNullName() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation(null, new Appearance(10, 15));
  }

  /**
   * Test adding a null Transformation value to a shape in AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationNullTrans() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", null);
  }

  /**
   * Test adding a transformation with a start value that already exists in the same time frame
   * as another transformation of the same type to a shape in AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationExistingTransStart() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.addTransformation("circle", new Appearance(15, 25));
  }

  /**
   * Test adding a transformation to a shape that has the same transformation with the
   * same start time in AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationExistingTransSameStart() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.addTransformation("circle", new Appearance(10, 25));
  }

  /**
   * Test adding a transformation with an end time that exists within the same frame
   * as another transformation of the same type to a shape in AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationExistingTransEnd() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.addTransformation("circle", new Appearance(5, 15));
  }

  /**
   * Test adding a transformation to a shape in AnimationModelImpl that already has a
   * transformation of the same type with the same end time.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationExistingTransSameEnd() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.addTransformation("circle", new Appearance(5, 20));
  }

  /**
   * Test adding a transformation to a shape that has start and end times that encompass
   * the start and end times of another transformation of the same type on the same shape
   * in AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationExistingTransOutside() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.addTransformation("circle", new Appearance(5, 25));
  }

  /**
   * Test adding a transformation to a shape that does not exist in AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationNonExistentShape() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("square", new Appearance(10, 20));
  }

  /**
   * Test removing a transformation from a shape in AnimationModelImpl.
   */
  @Test
  public void testRemoveTransformation() {
    AnimationModel m = new AnimationModelImpl();

    String test = "Create window with width 500 and height 500 with background color 0.0,0.0,0.0 "
            + "and total ticks 0.";

    assertEquals(test, m.toString());
    assertEquals(0, m.getTransformations().size());

    m.addShape(new Circle("C", 0, 10, 0, 0, green));
    m.addTransformation("C", new Appearance(0, 10));

    test = "Create window with width 500 and height 500 with background color 0.0,0.0,0.0 "
            + "and total ticks 10.\n\n"
            + "Create circle C with center at (0,0) and radius 10 on layer 0 "
            + "with color 0.0,1.0,0.0.\n\n"
            + "C appears at time t=0 and disappears at time t=10.";

    assertEquals(test, m.toString());
    assertEquals(1, m.getTransformations().size());

    m.removeTransformation("C", TransformationType.APPEARANCE, 0, 10);

    test = "Create window with width 500 and height 500 with background color 0.0,0.0,0.0 "
            + "and total ticks 0.\n\n"
            + "Create circle C with center at (0,0) and radius 10 on layer 0 "
            + "with color 0.0,1.0,0.0.";

    assertEquals(test, m.toString());
    assertEquals(0, m.getTransformations().size());
  }

  /**
   * Test removing a transformation with a null shapeName value from
   * AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveTransformationNullShapeName() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.removeTransformation(null, TransformationType.APPEARANCE, 10, 20);
  }

  /**
   * Test removing a transformation with a null transformationType value from
   * AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveTransformationNullTransType() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.removeTransformation("circle", null, 10, 20);
  }

  /**
   * Test removing a transformation from a shape that does not exist in
   * AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveTransformationShapeNonExistent() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.removeTransformation("square", TransformationType.APPEARANCE, 10, 20);
  }

  /**
   * Test removing a transformation that does not exist but shares the same start and end
   * times of another transformation from a shape in AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveTransformationNonExistentType() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.removeTransformation("circle", TransformationType.MOVE, 10, 20);
  }

  /**
   * Test removing a transformation from a shape when the start times of two similar
   * Transformation types do not match in AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveTransformationNonExistentStart() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.removeTransformation("circle", TransformationType.APPEARANCE, 5, 20);
  }

  /**
   * Test removing a transformation from a shape with a transformation of a similar type
   * but different end times in AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveTransformationNonExistentEnd() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.removeTransformation("circle", TransformationType.APPEARANCE, 10, 25);
  }

  /**
   * Test getting a list of shapes held in AnimationModelImpl.
   */
  @Test
  public void testGetShapes() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("c", 1, 5, 0, 0, green));
    m.addShape(new Square("s", 2, 10, 20, 20, green));

    assertEquals("c", m.getShapes().get(0).getName());
    assertEquals("s", m.getShapes().get(1).getName());
  }

  /**
   * Test getting a list of transformations on shapes in AnimationModelImpl.
   */
  @Test
  public void testGetTransformations() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("c", 1, 5, 0, 0, green));
    m.addShape(new Square("s", 2, 10, 20, 20, green));

    m.addTransformation("s", new Move(5, 15, 0, 0));
    m.addTransformation("c", new Appearance(0, 10));

    assertEquals(0, m.getTransformations().get(0).getStart());
    assertEquals(10, m.getTransformations().get(0).getEnd());
    assertEquals(TransformationType.APPEARANCE, m.getTransformations().get(0).getType());

    assertEquals(5, m.getTransformations().get(1).getStart());
    assertEquals(15, m.getTransformations().get(1).getEnd());
    assertEquals(TransformationType.MOVE, m.getTransformations().get(1).getType());
  }

  /**
   * Test getting the total number of ticks of the animation from AnimationModelImpl.
   */
  @Test
  public void testGetTotalTicks() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("c", 1, 5, 0, 0, green));
    m.addShape(new Square("s", 2, 10, 20, 20, green));

    m.addTransformation("s", new Move(5, 15, 0, 0));
    m.addTransformation("c", new Appearance(0, 10));

    assertEquals(15, m.getTotalTicks());

    m.removeTransformation("s", TransformationType.MOVE, 5, 15);

    assertEquals(10, m.getTotalTicks());

    m.removeTransformation("c", TransformationType.APPEARANCE, 0, 10);

    assertEquals(0, m.getTotalTicks());
  }

  /**
   * Test getting shapes at a time frame specified by a negative tick from
   * AnimationModelImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetShapesAtTickNeg() {
    AnimationModel m = new AnimationModelImpl();

    m.getShapesAtTick(-1);
  }

  /**
   * Test getting the window height from AnimationModelImpl.
   */
  @Test
  public void testGetWindowHeight() {
    AnimationModel m = new AnimationModelImpl();

    assertEquals(500, m.getWindowHeight());
  }

  /**
   * Test getting the window width from AnimationModelImpl.
   */
  @Test
  public void testGetWindowWidth() {
    AnimationModel m = new AnimationModelImpl(100, 100);

    assertEquals(100, m.getWindowWidth());
  }

  /**
   * Test getting the background color from AnimationModelImpl.
   */
  @Test
  public void testGetBackgroundColor() {
    AnimationModel m = new AnimationModelImpl();

    assertEquals("0.0,0.0,0.0", m.getBackgroundColor().toString());
  }

  @Test
  public void testGetShapesAtTick() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 10, 20, 20, new Color(1, 0, 0)));
    m.addTransformation("circle", new Appearance(5, 20));
    m.addTransformation("circle", new Move(10, 20, 0, 0));
    m.addTransformation("circle", new Scale(10, 20, 2.0));
    m.addTransformation("circle", new ChangeColor(10, 20, new Color(0, 0, 1)));
    m.addTransformation("circle", new ChangeWidth(10, 20, 30));
    m.addTransformation("circle", new ChangeHeight(10, 20, 30));
    m.addTransformation("circle", new ChangeTransparency(10, 20, 50));


    m.addShape(new Oval("oval", 1, 20, 25, 0, 0, new Color(0, 1,0)));
    m.addTransformation("oval", new Appearance(10, 30));
    m.addTransformation("oval", new Move(10, 20, 100, 100));
    m.addTransformation("oval", new Scale(10, 20, 2.0));
    m.addTransformation("oval", new ChangeColor(10, 20, new Color(0, 0, 1)));
    m.addTransformation("oval", new ChangeWidth(10, 20, 35));
    m.addTransformation("oval", new ChangeHeight(10, 20, 30));
    m.addTransformation("oval", new ChangeTransparency(10, 20, 50));

    m.addShape(new Rectangle("rectangle", 2, 15, 15, 100, 100, new Color(0, 1, 1)));
    m.addTransformation("rectangle", new Appearance(0, 20));
    m.addTransformation("rectangle", new Move(5, 15, 50, 50));
    m.addTransformation("rectangle", new Scale(10, 20, 2.0));
    m.addTransformation("rectangle", new ChangeColor(10, 20, new Color(0, 0, 1)));
    m.addTransformation("rectangle", new ChangeWidth(10, 20, 35));
    m.addTransformation("rectangle", new ChangeHeight(10, 20, 30));
    m.addTransformation("rectangle", new ChangeTransparency(10, 20, 50));

    m.addShape(new Square("square", 3, 5, 10, 10, new Color(1, 0, 0)));
    m.addTransformation("square", new Appearance(10, 25));
    m.addTransformation("square", new Move(10, 20, 0, 0));
    m.addTransformation("square", new Scale(10, 20, 2.0));
    m.addTransformation("square", new ChangeColor(10, 20, new Color(0, 1, 0)));
    m.addTransformation("square", new ChangeWidth(10, 20, 25));
    m.addTransformation("square", new ChangeHeight(10, 20, 25));
    m.addTransformation("square", new ChangeTransparency(10, 20, 50));

    m.addShape(new Triangle("triangle", 4, 10, 15, 50, 50, new Color(0, 0, 1)));
    m.addTransformation("triangle", new Appearance(0, 50));
    m.addTransformation("triangle", new Move(10, 20, 70, 70));
    m.addTransformation("triangle", new Scale(10, 20, 0.5));
    m.addTransformation("triangle", new ChangeColor(10, 20, new Color(0.5, 0, 0)));
    m.addTransformation("triangle", new ChangeWidth(10, 20, 35));
    m.addTransformation("triangle", new ChangeHeight(10, 20, 30));
    m.addTransformation("triangle", new ChangeTransparency(15, 15, 50));

    List<Shape> modList = m.getShapesAtTick(15);

    assertEquals("", "");
  }

}
