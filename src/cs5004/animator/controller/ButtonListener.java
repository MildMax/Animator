package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs5004.animator.view.AnimationView;

/**
 * The ButtonListener class responds to input from buttons in the AnimationView and performs
 * the corresponding action. Implements the ActionListener interface.
 */
public class ButtonListener implements ActionListener {

  private AnimationView v;

  /**
   * Constructor takes an AnimationView that the ButtonListener listens to and modifies based on
   * input.
   *
   * @param v takes an AnimationView that the ButtonListener listens to.
   * @throws IllegalArgumentException if the AnimationView v is null.
   */
  public ButtonListener(AnimationView v) throws IllegalArgumentException {
    if (v == null) {
      throw new IllegalArgumentException("View cannot be null");
    }
    this.v = v;
  }

  /**
   * Takes an ActionEvent as input from a button in the AnimationView and executes
   * the corresponding actions.
   *
   * @param e takes an ActionEvent tied to a button in the AnimationView.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "play":
        v.getRunner().togglePlay();
        break;
      case "restart":
        v.getRunner().restartAnim();
        break;
      case "loop":
        v.getRunner().toggleLoop();
        break;
    }
  }
}
