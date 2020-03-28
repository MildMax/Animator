import org.junit.Test;
import org.junit.Before;
import cs5004.animator.model.Color;
import cs5004.animator.model.transformations.ChangeColor;
import cs5004.animator.model.transformations.ChangeHeight;
import cs5004.animator.model.transformations.ChangeTransparency;
import cs5004.animator.model.transformations.ChangeWidth;
import cs5004.animator.model.transformations.Move;
import cs5004.animator.model.transformations.Scale;
import cs5004.animator.model.transformations.Transformation;
import cs5004.animator.model.transformations.TransformationType;
import static org.junit.Assert.assertEquals;

/**
 * Create a test class TestTransformations to test all of the transformation classes.
 */
public class TestTransformations {

  Color blue;

  /**
   * Initializes color value for blue.
   */
  @Before
  public void setUp() {
    this.blue = new Color(0.0, 0.0, 1.0);
  }

  /**
   * Test that the Appearance class's constructor works correctly.
   */
  @Test
  public void testAppearanceConstructor() {
    Transformation t = new Appearance(10, 15);
    assertEquals(10, t.getStart());
    assertEquals(15, t.getEnd());
    assertEquals("appears at time t=10 and disappears at time t=15.", t.toString());
  }

  /**
   * Test assigning Appearance a negative start time; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAppearanceNegativeStartTime() {
    Transformation t = new Appearance(-1, 10);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning Appearance a negative end time; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAppearanceNegativeEndTime() {
    Transformation t = new Appearance(-10, -5);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning Appearance an end time that is before its start time; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAppearanceEndBeforeStart() {
    Transformation t = new Appearance(5, 4);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning Move a negative start time; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveNegativeStart() {
    Transformation t = new Move(-1, 10, 100, 100);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning Appearance a negative End time; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveNegativeEnd() {
    Transformation t = new Move(-10, -5, 100, 100);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning Move an end time before its start time; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveEndBeforeStart() {
    Transformation t = new Move(5, 1, 100, 100);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning ChangeWidth a negative start time; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeWidthNegativeStart() {
    Transformation t = new ChangeWidth(-1, 5, 10);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning ChangeWidth a negative end time; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeWidthNegativeEnd() {
    Transformation t = new ChangeWidth(-10, -5, 10);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning ChangeWidth an end time before its start time; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeWidthEndBeforeStart() {
    Transformation t = new ChangeWidth(5, 1, 10);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning ChangeWidth a newWidth value of zero; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeWidthZeroWidth() {
    Transformation t = new ChangeWidth(5, 10, 0);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning ChangeWidth a negative value for newWidth; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeWidthNegativeWidth() {
    Transformation t = new ChangeWidth(5, 10, -10);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning ChangeHeight a negative start time; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeHeightNegativeStart() {
    Transformation t = new ChangeHeight(-1, 5, 10);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning ChangeHeight a negative end time; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeHeightNegativeEnd() {
    Transformation t = new ChangeHeight(-10, -5, 10);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning ChangeHeight an end time before its start time; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeHeightEndBeforeStart() {
    Transformation t = new ChangeHeight(5, 3, 10);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning ChangeHeight a value of zero for newHeight; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeHeightZeroHeight() {
    Transformation t = new ChangeHeight(5, 10, 0);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning ChangeHeight a negative value for newHeight; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeHeightNegativeHeight() {
    Transformation t = new ChangeHeight(5, 10, -10);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning Scale a negative start time; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testScaleNegativeStart() {
    Transformation t = new Scale(-1, 5, 1.0);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning Scale a negative end time; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testScaleNegativeEnd() {
    Transformation t = new Scale(-10, -5, 1.0);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning Scale an end time that is before its start time; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testScaleEndBeforeStart() {
    Transformation t = new Scale(5, 1, 1.0);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning Scale a negative value for scaleFactor; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testScaleNegativeScaleFactor() {
    Transformation t = new Scale(5, 10, -0.5);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning ChangeColor a negative start time; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorNegativeStart() {
    Transformation t = new ChangeColor(-1, 10, blue);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning ChangeColor a negative end time; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorNegativeEnd() {
    Transformation t = new ChangeColor(-10, -5, blue);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning ChangeColor an end time that is before its start time; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorEndBeforeStart() {
    Transformation t = new ChangeColor(6, 5, blue);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning ChangeColor a value of newColor = null; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorNullColor() {
    Transformation t = new ChangeColor(10, 15, null);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning ChangeTransparency a negative start time; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeTransparencyNegativeStart() {
    Transformation t = new ChangeTransparency(-1, 5, 10.0);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning ChangeTransparency a negative end time; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeTransparencyNegativeEnd() {
    Transformation t = new ChangeTransparency(-10, -5, 10.0);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning ChangeTransparency an end time before its start time; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeTransparencyEndBeforeStart() {
    Transformation t = new ChangeTransparency(5, 1, 10.0);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning ChangeTransparency negative value for newTransparency; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeTransparencyNegativeTransparency() {
    Transformation t = new ChangeTransparency(5, 10, -1.0);
    assertEquals("", t.toString());
  }

  /**
   * Test assigning ChangeTransparency a value for newTransparency > 100; should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeTransparencyHighTransparency() {
    Transformation t = new ChangeTransparency(5, 10, 100.1);
    assertEquals("", t.toString());
  }

  /**
   * Test all methods on the class Appearance with valid inputs.
   */
  @Test
  public void Test_Appearance_All_Valid() {
    Transformation t1 = new Appearance(1, 1000);
    assertEquals(1, t1.getStart());
    assertEquals(1000, t1.getEnd());
    assertEquals(TransformationType.APPEARANCE, t1.getType());
    assertEquals("appears at time t=1 and disappears at time t=1000.", t1.toString());
  }

  /**
   * Test all methods on the class ChangeColor with valid inputs.
   */
  @Test
  public void Test_ChangeColor_All_Valid() {
    Transformation t1 = new ChangeColor(1, 1000, blue);
    assertEquals(1, t1.getStart());
    assertEquals(1000, t1.getEnd());
    assertEquals(TransformationType.CHANGECOLOR, t1.getType());
    assertEquals("changes to 0.0,0.0,1.0 from time t=1 to time t=1000.", t1.toString());
  }

  /**
   * Test all methods on the class ChangeHeight with valid inputs.
   */
  @Test
  public void Test_ChangeHeight_All_Valid() {
    Transformation t1 = new ChangeHeight(1, 1000, 99);
    assertEquals(1, t1.getStart());
    assertEquals(1000, t1.getEnd());
    assertEquals(TransformationType.CHANGEHEIGHT, t1.getType());
    assertEquals("changes to height:99 from time t=1 to time t=1000.", t1.toString());
  }

  /**
   * Test all methods on the class ChangeTransparency with valid inputs.
   */
  @Test
  public void Test_ChangeTransparency_All_Valid() {
    Transformation t1 = new ChangeTransparency(1, 1000, 50);
    assertEquals(1, t1.getStart());
    assertEquals(1000, t1.getEnd());
    assertEquals(TransformationType.CHANGETRANSPARENCY, t1.getType());
    assertEquals("changes to transparency 50.0 from time t=1 to time t=1000.",
            t1.toString());
  }

  /**
   * Test all methods on the class ChangeWidth with valid inputs.
   */
  @Test
  public void Test_ChangeWidth_All_Valid() {
    Transformation t1 = new ChangeWidth(1, 1000, 77);
    assertEquals(1, t1.getStart());
    assertEquals(1000, t1.getEnd());
    assertEquals(TransformationType.CHANGEWIDTH, t1.getType());
    assertEquals("changes to width:77 from time t=1 to time t=1000.",t1.toString());
  }

  /**
   * Test all methods on the class Move with valid inputs.
   */
  @Test
  public void Test_Move_All_Valid() {
    Transformation t1 = new Move(1, 1000, 10, 10);
    assertEquals(1, t1.getStart());
    assertEquals(1000, t1.getEnd());
    assertEquals(TransformationType.MOVE, t1.getType());
    assertEquals("moves to (10,10) from time t=1 to time t=1000.",t1.toString());
  }

  /**
   * Test all methods on the class Scale with valid inputs.
   */
  @Test
  public void Test_Scale_All_Valid() {
    Transformation t1 = new Scale(1, 1000, 1.5);
    assertEquals(1, t1.getStart());
    assertEquals(1000, t1.getEnd());
    assertEquals(TransformationType.SCALE, t1.getType());
    assertEquals("scales to 1.5 times its current size from time t=1 to time t=1000.",
            t1.toString());
  }

}
