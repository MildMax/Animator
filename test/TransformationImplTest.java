import org.junit.Test;
import static org.junit.Assert.assertEquals;
import cs5004.animator.model.transformations.Transformation;
import cs5004.animator.model.transformations.TransformationImpl;

public class TransformationImplTest {

  @Test
  public void testTransformationImpl() {
    Transformation t = new TransformationImpl("shape", 10, 20, 30, 40, 50,
            100, 110, 120, 20, 60, 70, 80, 90, 200, 210, 220);

    assertEquals("shape", t.getShapeName());
    assertEquals(20, t.getX1());
    assertEquals(60, t.getX2());
    assertEquals(30, t.getY1());
    assertEquals(70, t.getY2());
    assertEquals(40, t.getW1());
    assertEquals(80, t.getW2());
    assertEquals(50, t.getH1());
    assertEquals(90, t.getH2());
    assertEquals(100, t.getR1());
    assertEquals(200, t.getR2());
    assertEquals(110, t.getG1());
    assertEquals(210, t.getG2());
    assertEquals(120, t.getB1());
    assertEquals(220, t.getB2());
    assertEquals(10, t.getStart());
    assertEquals(20, t.getEnd());

    String test = "shape moves from (20,60) to (60,70) from time t=10 to time t=20\n"
    + "shape changes width from 40 to 80 from time t=10 to time t=20\n"
    + "shape changes height from 50 to 90 from time t=10 to time t=20\n"
    + "shape changes from color (100,110,120) to color (200,210,220) from time t=10 to time t=20\n";

    assertEquals(test, t.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationNegativeStart() {
    Transformation t = new TransformationImpl("shape", -5, 20, 30, 40, 50,
            100, 110, 120, 20, 60, 70, 80, 90, 200, 210, 220);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationEndBeforeStart() {
    Transformation t = new TransformationImpl("shape", 10, 20, 30, 40, 50,
            100, 110, 120, 5, 60, 70, 80, 90, 200, 210, 220);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationNegativeWidth1() {
    Transformation t = new TransformationImpl("shape", 10, 20, 30, -5, 50,
            100, 110, 120, 20, 60, 70, 80, 90, 200, 210, 220);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationNegativeWidth2() {
    Transformation t = new TransformationImpl("shape", 10, 20, 30, 40, 50,
            100, 110, 120, 20, 60, 70, -5, 90, 200, 210, 220);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationNegativeHeight1() {
    Transformation t = new TransformationImpl("shape", 10, 20, 30, 40, -5,
            100, 110, 120, 20, 60, 70, 80, 90, 200, 210, 220);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationNegativeHeight2() {
    Transformation t = new TransformationImpl("shape", 10, 20, 30, 40, 50,
            100, 110, 120, 20, 60, 70, 80, -5, 200, 210, 220);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationNegativeR1() {
    Transformation t = new TransformationImpl("shape", 10, 20, 30, 40, 50,
            -1, 110, 120, 20, 60, 70, 80, 90, 200, 210, 220);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationNegativeR2() {
    Transformation t = new TransformationImpl("shape", 10, 20, 30, 40, 50,
            100, 110, 120, 20, 60, 70, 80, 90, -1, 210, 220);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationLargeR1() {
    Transformation t = new TransformationImpl("shape", 10, 20, 30, 40, 50,
            256, 110, 120, 20, 60, 70, 80, 90, 200, 210, 220);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationLargeR2() {
    Transformation t = new TransformationImpl("shape", 10, 20, 30, 40, 50,
            100, 110, 120, 20, 60, 70, 80, 90, 256, 210, 220);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationNegativeG1() {
    Transformation t = new TransformationImpl("shape", 10, 20, 30, 40, 50,
            100, -1, 120, 20, 60, 70, 80, 90, 200, 210, 220);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationNegativeG2() {
    Transformation t = new TransformationImpl("shape", 10, 20, 30, 40, 50,
            100, 110, 120, 20, 60, 70, 80, 90, 200, -1, 220);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationLargeG1() {
    Transformation t = new TransformationImpl("shape", 10, 20, 30, 40, 50,
            100, 256, 120, 20, 60, 70, 80, 90, 200, 210, 220);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationLargeG2() {
    Transformation t = new TransformationImpl("shape", 10, 20, 30, 40, 50,
            100, 110, 120, 20, 60, 70, 80, 90, 200, 256, 220);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationNegativeB1() {
    Transformation t = new TransformationImpl("shape", 10, 20, 30, 40, 50,
            100, 110, -1, 20, 60, 70, 80, 90, 200, 210, 220);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationNegativeB2() {
    Transformation t = new TransformationImpl("shape", 10, 20, 30, 40, 50,
            100, 110, 120, 20, 60, 70, 80, 90, 200, 210, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationLargeB1() {
    Transformation t = new TransformationImpl("shape", 10, 20, 30, 40, 50,
            100, 110, 256, 20, 60, 70, 80, 90, 200, 210, 220);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationLargeB2() {
    Transformation t = new TransformationImpl("shape", 10, 20, 30, 40, 50,
            100, 110, 120, 20, 60, 70, 80, 90, 200, 210, 256);
  }

}
