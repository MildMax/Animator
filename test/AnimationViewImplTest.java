import org.junit.Test;

import cs5004.animator.view.VisualView;

/**
 * Test the AnimationViewImpl class.
 */
public class AnimationViewImplTest {

  /**
   * Test passing negative width to the AnimationViewImpl constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationViewImplNegativeWidth() {
    //new VisualView(0, 0, -1, 100);
  }

  /**
   * Test passing negative height to the AnimationViewImpl constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationViewImplNegativeHeight() {
    //new VisualView(0, 0, 100, -1);
  }

  /**
   * Test passing a negative x value to the AnimationViewImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationViewImplNegativeX() {
    //new VisualView(-10, 0, 100, 100);
  }

  /**
   * Test passing a negative y value to the AnimationViewImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationViewImplNegativeY() {
    //new VisualView(0, -5, 100, 100);
  }
}