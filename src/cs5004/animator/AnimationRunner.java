package cs5004.animator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.view.AnimationView;

/**
 * This class keeps track of the ticks in the animation, and holds methods
 * that start the animation, check if the animation is running, adds frames to
 * the animation, and exits the animation. Implements the ActionListener interface.
 */
public class AnimationRunner implements ActionListener {

  private int tick = 0;
  private AnimationModel model;
  private AnimationView view;
  private Timer timer;

  /**
   * Takes an AnimationModel to pull data from, an AnimationView to display the data
   * visually, and a delay to initialize a Timer and play the animation at the specified
   * speed.
   *
   * @param m takes an AnimationModel with shapes to be printed to the screen.
   * @param v takes a View that will display the animation.
   * @param delay specifies the frames per second of the animation.
   * @throws IllegalArgumentException If the AnimationModel argument is null.
   *                                  If the AnimationView argument is null.
   */
  public AnimationRunner(AnimationModel m, AnimationView v, int delay) {
    if (m == null || v == null) {
      throw new IllegalArgumentException("Model/View cannot be null");
    }
    this.model = m;
    this.view = v;
    this.timer = new Timer(delay, this);
  }

  /**
   * Runs the timer in the Listener.
   */
  public void runAnim() {
    timer.start();
    while (timer.isRunning());
  }

  /**
   * Increments frames of animation and displays a frame of the animation on screen.
   *
   * @param e an ActionEvent object.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (this.tick > this.model.getTotalTicks()) {
      timer.stop();
      return;
    }
    this.view.drawNewFrame(this.model.getShapesAtTick(this.tick));
    ++tick;
  }
}
