import org.junit.Test;

import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.view.AnimationRunner;
import cs5004.animator.view.ShapePanel;
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
    new VisualView(0, 0, -1, 100, 500, 600, 50);
  }

  /**
   * Test passing negative height to the VisualView.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationViewImplNegativeHeight() {
    new VisualView(0, 0, 100, -1, 500, 600, 50);
  }

  /**
   * Test passing a negative x value to the VisualView.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationViewImplNegativeX() {
    new VisualView(-10, 0, 100, 100, 500, 600, 50);
  }

  /**
   * Test passing a negative y value to the VisualView.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationViewImplNegativeY() {
    new VisualView(0, -5, 100, 100, 500, 600, 50);
  }

  /**
   * Test passing a negative max width to the VisualView.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationViewNegativeMaxWidth() {
    new VisualView(0, 0, 100, 100, -1, 600, 50);
  }

  /**
   * Test passing a negative max height to the VisualView.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationViewNegativeMaxHeight() {
    new VisualView(0, 0, 100, 100, 500, -1, 50);
  }

  /**
   * Test passing a a ticks per second of 0 to visual view.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationViewZeroTicksPerSecond() {
    new VisualView(0, 0, 100, 100, 200, 200, 0);
  }

  /**
   * Test passing negative Ticks Per Second to visual view.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationViewNegativeTicksPerSecond() {
    new VisualView(0, 0, 100, 100, 200, 200, -1);
  }

  /**
   * Test passing a null View value to AnimationRunner constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationRunnerNullView() {
    new AnimationRunner(new AnimationModelImpl(0, 0, 100, 100), null, 20);
  }

  /**
   * Test passing a null model value to AnimationRunner constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationRunnerNullModel() {
    new AnimationRunner(null, new VisualView(0,0,100,100, 200, 200, 20), 20);
  }

  /**
   * Test passing a zero ticks per second value to AnimationRunner constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnimationRunnerZeroTicksPerSecond() {
    new AnimationRunner(new AnimationModelImpl(0, 0, 100, 100),
            new VisualView(0,0,100,100, 200, 200, 20), 0);
  }

  /**
   * Test passing a null shapeList value to ShapePanel's addFrame method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testShapePanelNullListAddFrame() {
    new ShapePanel().addFrame(null);
  }
}
