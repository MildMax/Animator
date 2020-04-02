package cs5004.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import cs5004.animator.model.AnimationModel;

/**
 * This class keeps track of the ticks in the animation, and holds methods
 * that start the animation, check if the animation is running, adds frames to
 * the animation, and exits the animation. Implements the ActionListener interface.
 */
public class AnimationRunner implements ActionListener {

  private double ticksPerFrame = 0;
  private int frames = 0;
  private AnimationModel model;
  private AnimationView view;
  private Timer timer;

  /**
   * Takes an AnimationModel to pull data from, an AnimationView to display the data
   * visually, and a delay in milliseconds to initialize a Timer and play the animation at
   * a specified speed.
   *
   * @param m takes an AnimationModel with shapes to be printed to the screen.
   * @param v takes a View that will display the animation.
   * @param delay specifies the frames per second of the animation.
   * @throws IllegalArgumentException If the AnimationModel argument is null.
   *                                  If the AnimationView argument is null.
   *                                  If the delay is less than 1.
   */
  public AnimationRunner(AnimationModel m, AnimationView v, int ticksPerSecond) {
    if (m == null || v == null) {
      throw new IllegalArgumentException("Model/View cannot be null");
    }
    else if (ticksPerSecond < 1) {
      throw new IllegalArgumentException("Delay cannot be less than 1");
    }

    ticksPerFrame = (double)ticksPerSecond / 60.0;
    int millisecondsPerFrame = (int) Math.round(1000.0 / 60.0);

    this.model = m;
    this.view = v;
    this.timer = new Timer(millisecondsPerFrame, this);
  }

  /**
   * Runs the timer in the Listener.
   */
  public void runAnim() {
    view.openView();
    timer.start();
  }

  /**
   * Increments frames of animation and displays a frame of the animation on screen.
   *
   * @param e an ActionEvent object.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if ((this.frames * this.ticksPerFrame) > this.model.getTotalTicks()) {
      view.closeView();
      timer.stop();
      //System.out.println("timer has been stopped");
      return;
    }
    this.view.drawNewFrame(this.model.getShapesAtTick((double)this.frames * this.ticksPerFrame));

    ++frames;
  }
}
