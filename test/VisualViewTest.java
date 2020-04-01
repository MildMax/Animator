import org.junit.Test;

import cs5004.animator.view.VisualView;

/**
 * Test the VisualView class.
 */
public class VisualViewTest {

  /**
   * Test passing negative width to the VisualView.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationViewImplNegativeWidth() {
    new VisualView(0, 0, -1, 100, 500, 600);
  }

  /**
   * Test passing negative height to the VisualView.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationViewImplNegativeHeight() {
    new VisualView(0, 0, 100, -1, 500, 600);
  }

  /**
   * Test passing a negative x value to the VisualView.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationViewImplNegativeX() {
    new VisualView(-10, 0, 100, 100, 500, 600);
  }

  /**
   * Test passing a negative y value to the VisualView.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationViewImplNegativeY() {
    new VisualView(0, -5, 100, 100, 500, 600);
  }

  /**
   * Test passing a negative max width to the VisualView.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationViewNegativeMaxWidth() {
    new VisualView(0, 0, 100, 100, -1, 600);
  }

  /**
   * Test passing a negative max height to the VisualView.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationViewNegativeMaxHeight() {
    new VisualView(0, 0, 100, 100, 500, -1);
  }
}
