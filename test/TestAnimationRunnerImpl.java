import org.junit.Test;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.shapes.ShapeImpl;
import cs5004.animator.model.shapes.ShapeType;
import cs5004.animator.model.transformations.TransformationImpl;
import cs5004.animator.view.AnimationRunner;
import cs5004.animator.view.AnimationRunnerImpl;
import cs5004.animator.view.PlaybackViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the class AnimationRunnerImpl.
 */
public class TestAnimationRunnerImpl {

  /**
   * Attempt to instantiate an AnimationRunnerImpl object with a null model.
   * Should throw IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test001() {
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,10, 100,
            100, 100, 100, 100);
    AnimationRunner ar1 = new AnimationRunnerImpl(null, view1, 10);
  }

  /**
   * Attempt to instantiate an AnimationRunnerImpl object with a null view.
   * Should throw IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test002() {
    AnimationModel model1 = new AnimationModelImpl(0, 100, 200, 300);
    AnimationRunner ar1 = new AnimationRunnerImpl(model1, null, 10);
  }

  /**
   * Attempt to instantiate an AnimationRunnerImpl object with ticksPerSecond = 0.
   * Should throw IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test003() {
    AnimationModel model1 = new AnimationModelImpl(0, 100, 200, 300);
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,10, 100,
            100, 100, 100, 100);
    AnimationRunner ar1 = new AnimationRunnerImpl(model1, view1, 0);
  }

  /**
   * Attempt to instantiate an AnimationRunnerImpl object with negative ticksPerSecond.
   * Should throw IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test004() {
    AnimationModel model1 = new AnimationModelImpl(0, 100, 200, 300);
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,10, 100,
            100, 100, 100, 100);
    AnimationRunner ar1 = new AnimationRunnerImpl(model1, view1, -10);
  }

  /**
   * Attempt to instantiate an AnimationRunnerImpl object correctly.
   *
   */
  @Test
  public void test005() {
    AnimationModel model1 = new AnimationModelImpl(0, 100, 200, 300);
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,10, 100,
            100, 100, 100, 100);
    AnimationRunner ar1 = new AnimationRunnerImpl(model1, view1, 10);
  }

  /**
   * Test that the method openWindow() doesn't throw an error.
   *
   */
  @Test
  public void test006() {
    AnimationModel model1 = new AnimationModelImpl(0, 100, 200, 300);
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,10, 100,
            100, 100, 100, 100);
    AnimationRunner ar1 = new AnimationRunnerImpl(model1, view1, 10);
    ar1.openWindow();
  }

  /**
   * Create an AnimationRunnerImpl object; test that ticksPerFrame is correctly set.
   *
   */
  @Test
  public void test007() {
    AnimationModel model1 = new AnimationModelImpl(0, 100, 200, 300);
    model1.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    model1.addShape(new ShapeImpl("ellipse", ShapeType.ELLIPSE, 2));
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,10, 100,
            100, 100, 100, 100);
    model1.addTransformation("rectangle", new TransformationImpl("rectangle",
            10, 20, 20, 20, 40, 100, 100, 100, 20, 40,
            40, 40, 60, 200, 200, 200));
    model1.addTransformation("ellipse", new TransformationImpl("ellipse", 20,
            0, 0, 10, 30, 50, 50, 50, 40, 40, 40,
            40, 60, 150, 150, 150));
    AnimationRunnerImpl ar1 = new AnimationRunnerImpl(model1, view1, 10);
    assertEquals(0.16666666666666666, ar1.getTicksPerFrame(), 0.001);
  }

  /**
   * Test that startAnim() method runs.
   *
   */
  @Test
  public void test008() {
    AnimationModel model1 = new AnimationModelImpl(0, 100, 200, 300);
    model1.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    model1.addShape(new ShapeImpl("ellipse", ShapeType.ELLIPSE, 2));
    PlaybackViewImpl view1 = new PlaybackViewImpl(1000,1000, 1000,
            1000, 1000, 1000, 100);
    model1.addTransformation("rectangle", new TransformationImpl("rectangle",
            10, 20, 20, 20, 40, 100, 100, 100, 20, 40,
            40, 40, 60, 200, 200, 200));
    model1.addTransformation("ellipse", new TransformationImpl("ellipse", 20,
            0, 0, 10, 30, 50, 50, 50, 40, 40, 40,
            40, 60, 150, 150, 150));
    AnimationRunnerImpl ar1 = new AnimationRunnerImpl(model1, view1, 10);
    ar1.openWindow();
    view1.run(model1);
    ar1.startAnim();
  }

  /**
   * Test calling startAnim() causes the animation (and thus the timer) timer to run.
   *
   */
  @Test
  public void test009() {
    AnimationModel model1 = new AnimationModelImpl(0, 100, 200, 300);
    model1.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    model1.addShape(new ShapeImpl("ellipse", ShapeType.ELLIPSE, 2));
    PlaybackViewImpl view1 = new PlaybackViewImpl(1000,1000, 1000,
            1000, 1000, 1000, 100);
    model1.addTransformation("rectangle", new TransformationImpl("rectangle",
            10, 20, 20, 20, 40, 100, 100, 100, 20, 40,
            40, 40, 60, 200, 200, 200));
    model1.addTransformation("ellipse", new TransformationImpl("ellipse", 20,
            0, 0, 10, 30, 50, 50, 50, 40, 40, 40,
            40, 60, 150, 150, 150));
    AnimationRunnerImpl ar1 = new AnimationRunnerImpl(model1, view1, 10);
    ar1.openWindow();
    view1.run(model1);
    ar1.startAnim();
    assertEquals(true, ar1.getTimer().isRunning());
  }

  /**
   * Test toggling the looping feature on and off.
   * Also make sure the text on the looping button toggles correctly.
   *
   */
  @Test
  public void test010() {
    AnimationModel model1 = new AnimationModelImpl(0, 100, 200, 300);
    model1.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    model1.addShape(new ShapeImpl("ellipse", ShapeType.ELLIPSE, 2));
    PlaybackViewImpl view1 = new PlaybackViewImpl(1000,1000, 1000,
            1000, 1000, 1000, 100);
    model1.addTransformation("rectangle", new TransformationImpl("rectangle",
            10, 20, 20, 20, 40, 100, 100, 100, 20, 40,
            40, 40, 60, 200, 200, 200));
    model1.addTransformation("ellipse", new TransformationImpl("ellipse", 20,
            0, 0, 10, 30, 50, 50, 50, 40, 40, 40,
            40, 60, 150, 150, 150));
    AnimationRunnerImpl ar1 = new AnimationRunnerImpl(model1, view1, 10);
    ar1.openWindow();
    view1.run(model1);
    ar1.startAnim();
    assertEquals(false, ar1.getIsLooping());
    assertEquals(true, ar1.getView().getLoopBox().isEnabled());
    ar1.toggleLoop();
    assertEquals(true, ar1.getIsLooping());
    assertEquals(true, ar1.getView().getLoopBox().isEnabled());
    ar1.toggleLoop();
    assertEquals(false, ar1.getIsLooping());
    assertEquals(true, ar1.getView().getLoopBox().isEnabled());
  }

  /**
   * Test calling togglePlay() causes the animation (and thus the timer) timer to stop running.
   * Then test that a second call of togglePlay() starts animation and timer again.
   *
   */
  @Test
  public void test011() {
    AnimationModel model1 = new AnimationModelImpl(0, 100, 200, 300);
    model1.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    model1.addShape(new ShapeImpl("ellipse", ShapeType.ELLIPSE, 2));
    PlaybackViewImpl view1 = new PlaybackViewImpl(1000,1000, 1000,
            1000, 1000, 1000, 100);
    model1.addTransformation("rectangle", new TransformationImpl("rectangle",
            10, 20, 20, 20, 40, 100, 100, 100, 20, 40,
            40, 40, 60, 200, 200, 200));
    model1.addTransformation("ellipse", new TransformationImpl("ellipse", 20,
            0, 0, 10, 30, 50, 50, 50, 40, 40, 40,
            40, 60, 150, 150, 150));
    AnimationRunnerImpl ar1 = new AnimationRunnerImpl(model1, view1, 10);
    ar1.openWindow();
    view1.run(model1);
    ar1.startAnim();
    assertEquals(true, ar1.getTimer().isRunning());
    ar1.togglePlay();
    assertEquals(false, ar1.getTimer().isRunning());
    ar1.togglePlay();
    assertEquals(true, ar1.getTimer().isRunning());
  }

  /**
   * Test that setTicksPerSecond changes the tick rate.
   *
   */
  @Test
  public void test012() {
    AnimationModel model1 = new AnimationModelImpl(0, 100, 200, 300);
    model1.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    model1.addShape(new ShapeImpl("ellipse", ShapeType.ELLIPSE, 2));
    PlaybackViewImpl view1 = new PlaybackViewImpl(10,10, 100,
            100, 100, 100, 100);
    model1.addTransformation("rectangle", new TransformationImpl("rectangle",
            10, 20, 20, 20, 40, 100, 100, 100, 20, 40,
            40, 40, 60, 200, 200, 200));
    model1.addTransformation("ellipse", new TransformationImpl("ellipse", 20,
            0, 0, 10, 30, 50, 50, 50, 40, 40, 40,
            40, 60, 150, 150, 150));
    AnimationRunnerImpl ar1 = new AnimationRunnerImpl(model1, view1, 10);
    assertEquals(0.16666666666666666, ar1.getTicksPerFrame(), 0.001);
    ar1.setTicksPerSecond(50);
    assertEquals(0.8333333333333333, ar1.getTicksPerFrame(), 0.001);
  }

  /**
   * Test that restartAnim() works
   *
   */
  @Test
  public void test013() {
    AnimationModel model1 = new AnimationModelImpl(0, 100, 200, 300);
    model1.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    model1.addShape(new ShapeImpl("ellipse", ShapeType.ELLIPSE, 2));
    PlaybackViewImpl view1 = new PlaybackViewImpl(1000,1000, 1000,
            1000, 1000, 1000, 100);
    model1.addTransformation("rectangle", new TransformationImpl("rectangle",
            10, 20, 20, 20, 40, 100, 100, 100, 20, 40,
            40, 40, 60, 200, 200, 200));
    model1.addTransformation("ellipse", new TransformationImpl("ellipse", 20,
            0, 0, 10, 30, 50, 50, 50, 40, 40, 40,
            40, 60, 150, 150, 150));
    AnimationRunnerImpl ar1 = new AnimationRunnerImpl(model1, view1, 10);
    ar1.openWindow();
    view1.run(model1);
    ar1.startAnim();
    assertEquals(0, ar1.getFrames());
    ar1.restartAnim();
    assertEquals(1, ar1.getFrames());
  }

  /**
   * Test that fps is set correctly.
   *
   */
  @Test
  public void test014() {
    AnimationModel model1 = new AnimationModelImpl(0, 100, 200, 300);
    model1.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    model1.addShape(new ShapeImpl("ellipse", ShapeType.ELLIPSE, 2));
    PlaybackViewImpl view1 = new PlaybackViewImpl(1000,1000, 1000,
            1000, 1000, 1000, 100);
    model1.addTransformation("rectangle", new TransformationImpl("rectangle",
            10, 20, 20, 20, 40, 100, 100, 100, 20, 40,
            40, 40, 60, 200, 200, 200));
    model1.addTransformation("ellipse", new TransformationImpl("ellipse", 20,
            0, 0, 10, 30, 50, 50, 50, 40, 40, 40,
            40, 60, 150, 150, 150));
    AnimationRunnerImpl ar1 = new AnimationRunnerImpl(model1, view1, 10);
    ar1.openWindow();
    view1.run(model1);
    assertEquals(60, ar1.getFPS());
  }
}
