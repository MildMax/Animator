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
public class TickActionListener implements ActionListener {

  private int tick = 0;
  private AnimationModel model;
  private AnimationView view;
  private Timer timer;

  /**
   * Takes an AnimationModel to pull data from, an AnimationView to display the data
   * visually, and a delay to initialize a Timer and play the animation at the specified
   * speed.
   *
   * @param m
   * @param v
   * @param delay
   */
  public TickActionListener(AnimationModel m, AnimationView v, int delay) {
    this.model = m;
    this.view = v;
    this.timer = new Timer(delay, this);
  }

  public int getTick() {
    return this.tick;
  }

  public void start() {
    timer.start();
  }

  public boolean isRunning() {
    return timer.isRunning();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (this.tick > this.model.getTotalTicks()) {
      System.out.println("Stopping action listener");
      timer.stop();
      return;
    }
    ++tick;

    System.out.println("ticks in listener: " + this.tick);
    System.out.println("ticks in anim: " + this.model.getTotalTicks());

    try {
      this.view.drawNewFrame(this.model.getShapesAtTick(this.tick));
    } catch (IllegalArgumentException ex) {
      return;
    }
  }
}
