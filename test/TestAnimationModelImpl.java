import org.junit.Test;
import org.junit.Before;

import cs5004animator.model.AnimationModel;
import cs5004animator.model.AnimationModelImpl;
import cs5004animator.model.Color;
import cs5004animator.model.shapes.Circle;
import cs5004animator.model.transformations.Appearance;
import cs5004animator.model.transformations.TransformationType;

import static org.junit.Assert.assertEquals;

public class TestAnimationModelImpl {

  Color green;

  @Before
  public void setUp() {
    this.green = new Color(0.0, 1.0, 0.0);
  }

  @Test
  public void testAnimationModelImplNoArgConstructor() {
    AnimationModel m = new AnimationModelImpl();

    assertEquals("Create window with bottom left corner(-500,-500) "
            + "top right corner (500,500) with background color 0.0,0.0,0.0 "
                    + "and total ticks 0.",
            m.toString());
  }

  @Test
  public void testAnimationModelImplTwoArgConstructor() {
    AnimationModel m = new AnimationModelImpl(100, 100);

    assertEquals("Create window with bottom left corner(-50,-50) "
                    + "top right corner (50,50) with background color 0.0,0.0,0.0"
                    + " and total ticks 0.",
            m.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelImplNegativeWidth() {
    new AnimationModelImpl(-10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelImplNegativeHeight() {
    new AnimationModelImpl(10, -10);
  }

  @Test
  public void testAnimationModelImplFourArgConstructor() {
    AnimationModel m = new AnimationModelImpl(-200, 250, -100, 150);

    assertEquals("Create window with bottom left corner(-200,-100) "
                    + "top right corner (250,150) with background color 0.0,0.0,0.0 "
                    + "and total ticks 0.",
            m.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelImplBadXValues() {
    new AnimationModelImpl(0, -10, 20, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelImplSameXValues() {
    new AnimationModelImpl(0, 0, 20, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelImplBadYValues() {
    new AnimationModelImpl(-10, 10, 0, -10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelImplSameYValues() {
    new AnimationModelImpl(-10, 10, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelAddShapeNullShape() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(null);
  }

  @Test
  public void testAnimationModelAddShape() {
    AnimationModel m = new AnimationModelImpl();

    String test = "Create window with bottom left corner(-500,-500) "
            + "top right corner (500,500) with background color 0.0,0.0,0.0 "
            + "and total ticks 0.";

    assertEquals(test, m.toString());

    m.addShape(new Circle("C", 0, 10, 0, 0, green));

    test = "Create window with bottom left corner(-500,-500) "
            + "top right corner (500,500) with background color 0.0,0.0,0.0 and total ticks 0.\n\n"
            + "Create circle C with center at (0,0) and radius 10 on layer 0 "
            + "with color 0.0,1.0,0.0.";

    assertEquals(test, m.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelAddExistingShape() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelRemoveShapeNullShape() {
    AnimationModel m = new AnimationModelImpl();

    m.removeShape(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelRemoveShapeNonExistent() {
    AnimationModel m = new AnimationModelImpl();

    m.removeShape("circle");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationNullName() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation(null, new Appearance(10, 15));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationNullTrans() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationExistingTransStart() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.addTransformation("circle", new Appearance(15, 25));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationExistingTransSAmeStart() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.addTransformation("circle", new Appearance(10, 25));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationExistingTransEnd() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.addTransformation("circle", new Appearance(5, 15));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationExistingTransSameEnd() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.addTransformation("circle", new Appearance(5, 20));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationExistingTransOutside() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.addTransformation("circle", new Appearance(5, 25));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationNonExistentShape() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("square", new Appearance(10, 20));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveTransformationNullShapeName() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.removeTransformation(null, TransformationType.APPEARANCE, 10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveTransformationNullTransType() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.removeTransformation("circle", null, 10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveTransformationShapeNonExistent() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.removeTransformation("square", TransformationType.APPEARANCE, 10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveTransformationNonExistentType() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.removeTransformation("circle", TransformationType.MOVE, 10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveTransformationNonExistentStart() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.removeTransformation("circle", TransformationType.APPEARANCE, 5, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveTransformationNonExistentEnd() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, green));
    m.addTransformation("circle", new Appearance(10, 20));
    m.removeTransformation("circle", TransformationType.APPEARANCE, 10, 25);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetBackgroundColorNullColor () {
    AnimationModel m = new AnimationModelImpl();

    m.setBackgroundColor(null);
  }


  //TEST LIST//



  //CREATING MODEL
    //EXCEPTIONS
      //ADDING SHAPES
      //ADDING TRANSFORMATIONS
    //FUNCTIONALITY <-- TEST GET DESCRIPTION HERE AS WELL
      //ADDING SHAPES
      //ADDING TRANSFORMATIONS
}
