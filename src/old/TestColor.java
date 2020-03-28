package old;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import old.Color;

/**
 * This class tests the Color class in cs5004animator.model.color.
 */
public class TestColor {

  /**
   * Tests the constructor for Color, get methods on color, and toString on color.
   */
  @Test
  public void testColorConstructor() {
    Color c = new Color(1.0, 0.5, 0.0);

    Assert.assertEquals(1.0, c.getR(), 0.01);
    Assert.assertEquals(0.5, c.getG(), 0.01);
    Assert.assertEquals(0.0, c.getB(), 0.01);

    Assert.assertEquals("1.0,0.5,0.0", c.toString());
  }

  /**
   * Tests passing a negative value for Red to Color object.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testColorNegR() {
    new Color(-1.0, 0, 0);
  }

  /**
   * Tests passing a negative value for Green to color object.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testColorNegG() {
    new Color(0.0, -1.0, 0);
  }

  /**
   * Tests passing a negative value for Blue to color object.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testColorNegB() {
    new Color(0.0, 0, -1.0);
  }

  /**
   * Test passing a value greater than 1.0 for Red to Color object.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testColorHighR() {
    new Color(1.1, 0, 0);
  }

  /**
   * Tests passing a value greater than 1.0 for Green to Color object.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testColorHighG() {
    new Color(0, 1.1, 0);
  }

  /**
   * Tests passing a value greater than 1.0 for Blue to Color object.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testColorHighB() {
    new Color(0, 0, 1.1);
  }

}
