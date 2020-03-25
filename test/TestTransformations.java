import org.junit.Test;

/*
import cs5004Animator.Color;
import cs5004Animator.Transformations.Appearance;
import cs5004Animator.Transformations.ChangeColor;
import cs5004Animator.Transformations.ChangeHeight;
import cs5004Animator.Transformations.ChangeTransparency;
import cs5004Animator.Transformations.ChangeWidth;
import cs5004Animator.Transformations.Move;
import cs5004Animator.Transformations.Scale;
import cs5004Animator.Transformations.Transformation;

 */

import static org.junit.Assert.assertEquals;

import cs5004Animator.*;
import cs5004Animator.Transformations.*;

public class TestTransformations {

  //CREATING TRANSFORMATIONS
  //EXCEPTIONS
  //GET METHODS
  //APPEARANCE
  @Test
  public void testAppearanceConstructor() {
    Transformation t = new Appearance(10, 15);

    assertEquals(10, t.getStart());
    assertEquals(15, t.getEnd());
    assertEquals("", t.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAppearanceNegativeStartTime() {
    new Appearance(-1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAppearanceEndBeforeStart() {
    new Appearance(5, 4);
  }
  //MOVE
  @Test(expected = IllegalArgumentException.class)
  public void testMoveNegativeStart() {
    new Move(-1, 10, 100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveEndBeforeStart() {
    new Move(5, 1, 100, 100);
  }
  //CHANGEWIDTH
  @Test(expected = IllegalArgumentException.class)
  public void testChangeWidthNegativeStart() {
    new ChangeWidth(-1, 5, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeWidthEndBeforeStart() {
    new ChangeWidth(5, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeWidthZeroWidth() {
    new ChangeWidth(5, 10, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeWidthNegativeWidth() {
    new ChangeWidth(5, 10, -10);
  }
  //CHANGEHEIGHT
  @Test(expected = IllegalArgumentException.class)
  public void testChangeHeightNegativeStart() {
    new ChangeHeight(-1, 5, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeHeightEndBeforeStart() {
    new ChangeHeight(5, 3, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeHeightZeroHeight() {
    new ChangeHeight(5, 10, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeHeightNegativeHeight() {
    new ChangeHeight(5, 10, -10);
  }
  //SCALE
  @Test(expected = IllegalArgumentException.class)
  public void testScaleNegativeStart() {
    new Scale(-1, 5, 1.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testScaleEndBeforeStart() {
    new Scale(5, 1, 1.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testScaleNegativeScaleFactor() {
    new Scale(5, 10, -0.5);
  }
  //CHANGECOLOR
  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorNegativeStart() {
    new ChangeColor(-1, 10, Color.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorEndBeforeStart() {
    new ChangeColor(6, 5, Color.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorNullColor() {
    new ChangeColor(10, 15, null);
  }
  //CHANGETRANSPARENCY
  @Test(expected = IllegalArgumentException.class)
  public void testChangeTransparencyNegativeStart() {
    new ChangeTransparency(-1, 5, 10.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeTransparencyEndBeforeStart() {
    new ChangeTransparency(5, 1, 10.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeTransparencyNegativeTransparency() {
    new ChangeTransparency(5, 10, -1.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeTransparencyHighTransparency() {
    new ChangeTransparency(5, 10, 100.1);
  }

}
