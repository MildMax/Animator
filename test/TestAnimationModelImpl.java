import org.junit.Test;

import java.util.EmptyStackException;

import cs5004Animator.*;
import cs5004Animator.Shapes.*;
import cs5004Animator.Transformations.*;

/*
import cs5004Animator.AnimationModel;
import cs5004Animator.AnimationModelImpl;
import cs5004Animator.Shapes.Circle;
import cs5004Animator.Transformations.Appearance;
import cs5004Animator.Color;
import cs5004Animator.Transformations.Move;
import cs5004Animator.Transformations.Scale;
import cs5004Animator.Shapes.Square;
import cs5004Animator.Shapes.Triangle;
import cs5004Animator.Transformations.TransformationType;

 */

import static org.junit.Assert.assertEquals;

public class TestAnimationModelImpl {

  @Test
  public void genericTest() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Square("1", 10, 10, 20, 20, Color.BLUE));

    m.addTransformation("1", new Move(10, 15, 40, 40));
    m.addTransformation("1", new Appearance(5, 20));

    m.addShape(new Triangle("2", 7, 11, 80, 80, 80, Color.GREEN));

    m.addTransformation("2", new Scale(20, 22, 2));
    m.addTransformation("2", new Appearance(16, 30));

    m.addTransformation("1", new Move(16, 20, 10, 10));
    //this throws IllegalArgumentException for occupying same space
    //m.addTransformation("1", new Move(15, 17, 25, 25));


    m.removeTransformation("1", TransformationType.MOVE, 10, 15);
    //m.removeShape("2");
    //m.addMove("3", 24, 43, 20, 30);

    assertEquals("", m.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelImplNegativeWidth() {
    new AnimationModelImpl(-10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelImplNegativeHeight() {
    new AnimationModelImpl(10, -10);
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

  @Test(expected = IllegalArgumentException.class)
  public void testAnimationModelAddExistingShape() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, Color.YELLOW));
    m.addShape(new Circle("circle", 0, 5, 10, 10, Color.YELLOW));
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

    m.addShape(new Circle("circle", 0, 5, 10, 10, Color.WHITE));
    m.addTransformation(null, new Appearance(10, 15));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationNullTrans() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, Color.WHITE));
    m.addTransformation("circle", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationExistingTransStart() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, Color.WHITE));
    m.addTransformation("circle", new Appearance(10, 20));
    m.addTransformation("circle", new Appearance(15, 25));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationExistingTransSAmeStart() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, Color.WHITE));
    m.addTransformation("circle", new Appearance(10, 20));
    m.addTransformation("circle", new Appearance(10, 25));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationExistingTransEnd() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, Color.WHITE));
    m.addTransformation("circle", new Appearance(10, 20));
    m.addTransformation("circle", new Appearance(5, 15));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationExistingTransSameEnd() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, Color.WHITE));
    m.addTransformation("circle", new Appearance(10, 20));
    m.addTransformation("circle", new Appearance(5, 20));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationExistingTransOutside() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, Color.WHITE));
    m.addTransformation("circle", new Appearance(10, 20));
    m.addTransformation("circle", new Appearance(5, 25));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTransformationNonExistentShape() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, Color.WHITE));
    m.addTransformation("square", new Appearance(10, 20));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveTransformationNullShapeName() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, Color.WHITE));
    m.addTransformation("circle", new Appearance(10, 20));
    m.removeTransformation(null, TransformationType.APPEARANCE, 10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveTransformationNullTransType() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, Color.WHITE));
    m.addTransformation("circle", new Appearance(10, 20));
    m.removeTransformation("circle", null, 10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveTransformationShapeNonExistent() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, Color.WHITE));
    m.addTransformation("circle", new Appearance(10, 20));
    m.removeTransformation("square", TransformationType.APPEARANCE, 10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveTransformationNonExistentType() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, Color.WHITE));
    m.addTransformation("circle", new Appearance(10, 20));
    m.removeTransformation("circle", TransformationType.MOVE, 10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveTransformationNonExistentStart() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, Color.WHITE));
    m.addTransformation("circle", new Appearance(10, 20));
    m.removeTransformation("circle", TransformationType.APPEARANCE, 5, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveTransformationNonExistentEnd() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Circle("circle", 0, 5, 10, 10, Color.WHITE));
    m.addTransformation("circle", new Appearance(10, 20));
    m.removeTransformation("circle", TransformationType.APPEARANCE, 10, 25);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetSpeedZeroSpeed() {
    AnimationModel m = new AnimationModelImpl();

    m.setSpeed(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetSpeedNegSpeed() {
    AnimationModel m = new AnimationModelImpl();

    m.setSpeed(-5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetSpeedHighSpeed() {
    AnimationModel m = new AnimationModelImpl();

    m.setSpeed(16.1);
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
