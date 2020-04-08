import org.junit.Test;

import java.nio.file.Paths;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.shapes.ShapeImpl;
import cs5004.animator.model.shapes.ShapeType;
import cs5004.animator.model.transformations.TransformationImpl;
import cs5004.animator.view.AnimationRunner;
import cs5004.animator.view.AnimationRunnerImpl;
import cs5004.animator.view.AnimationView;
import cs5004.animator.view.PlaybackViewImpl;
import cs5004.animator.view.SVGViewImpl;

import static org.junit.Assert.assertEquals;

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
