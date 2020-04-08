import org.junit.Test;
import static org.junit.Assert.assertEquals;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.shapes.ShapeImpl;
import cs5004.animator.model.shapes.ShapeType;
import cs5004.animator.model.transformations.TransformationImpl;
import cs5004.animator.view.PlaybackViewImpl;

/**
 * This class tests the class PlaybackViewImpl.
 */
public class TestPlaybackViewImpl {

  /**
   * Attempt to instantiate a PlaybackViewImpl with negative x value.
   * Should throw IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test001() {
    PlaybackViewImpl view1 = new PlaybackViewImpl(-10,10, 100,
            100, 100, 100, 100);
  }

  /**
   * Attempt to instantiate a PlaybackViewImpl with negative y value.
   * Should throw IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test002() {
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,-10, 100,
            100, 100, 100, 100);
  }

  /**
   * Attempt to instantiate a PlaybackViewImpl with negative windowWidth value.
   * Should throw IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test003() {
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,10, -100,
            100, 100, 100, 100);
  }

  /**
   * Attempt to instantiate a PlaybackViewImpl with negative windowHeight value.
   * Should throw IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test004() {
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,10, 100,
            -100, 100, 100, 100);
  }

  /**
   * Attempt to instantiate a PlaybackViewImpl with negative maxWidth value.
   * Should throw IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test005() {
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,10, 100,
            100, -100, 100, 100);
  }

  /**
   * Attempt to instantiate a PlaybackViewImpl with negative maxWidth value.
   * Should throw IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test006() {
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,10, 100,
            100, 100, -100, 100);
  }

  /**
   * Attempt to instantiate a PlaybackViewImpl with negative ticksPerSecond value.
   * Should throw IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test007() {
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,10, 100,
            100, 100, 100, -100);
  }

  /**
   * Attempt to instantiate a PlaybackViewImpl with zero windowWidth value.
   * Should throw IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test008() {
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,10, 0,
            100, 100, 100, 100);
  }

  /**
   * Attempt to instantiate a PlaybackViewImpl with zero windowHeight value.
   * Should throw IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test009() {
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,10, 100,
            0, 100, 100, 100);
  }

  /**
   * Attempt to instantiate a PlaybackViewImpl with zero maxWidth value.
   * Should throw IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test010() {
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,10, 100,
            100, 0, 100, 100);
  }

  /**
   * Attempt to instantiate a PlaybackViewImpl with zero maxWidth value.
   * Should throw IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test011() {
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,10, 100,
            100, 100, 0, 100);
  }

  /**
   * Attempt to instantiate a PlaybackViewImpl with zero ticksPerSecond value.
   * Should throw IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test0012() {
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,10, 100,
            100, 100, 100, 0);
  }

  /**
   * Successfully create an object of PlaybackViewImpl class.
   */
  @Test
  public void test0013() {
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,10, 100,
            100, 100, 100, 100);

    AnimationModel m = new AnimationModelImpl(0, 0, 100, 100);
    m.addShape(new ShapeImpl("rect", ShapeType.RECTANGLE, 1));
    m.addTransformation("rect", new TransformationImpl("rect",
            0, 10, 10, 10, 10, 255, 255, 255, 50,
            20, 20, 40, 40, 120, 120, 120));
    view1.run(m);
    view1.getRunner().startAnim();

    assertEquals(true, view1.getRunner().isRunning());
  }

  /**
   * Test passing a null shape list to drawNewFrame method on PlaybackViewImpl.
   * Should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test0014() {
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,10, 100,
            100, 100, 100, 100);
    view1.drawNewFrame(null);
  }

  /**
   * Test passing a null AnimationModel to the run method on PlaybackViewImpl.
   * Should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test0015() {
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,10, 100,
            100, 100, 100, 100);
    view1.run(null);
  }

  /**
   * Test getOutFileContents method on PlaybackViewImpl.
   * Should always throw exception.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void test0016() {
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,10, 100,
            100, 100, 100, 100);
    view1.getOutFileContents();
  }
}
