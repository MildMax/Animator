package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs5004.animator.view.AnimationRunner;
import cs5004.animator.view.AnimationView;

/**
 * The ButtonListener class responds to input from buttons in the AnimationView and performs
 * the corresponding action. Implements the ActionListener interface.
 */
public class ButtonListener implements ActionListener {

  private AnimationRunner runner;

  /**
   * Constructor takes an AnimationRunner that the ButtonListener modifies based on
   * input.
   *
   * @param runner takes an AnimationRunner that the ButtonListener modifies.
   * @throws IllegalArgumentException if the AnimationView v is null.
   */
  public ButtonListener(AnimationRunner runner) throws IllegalArgumentException {
    if (runner == null) {
      throw new IllegalArgumentException("View cannot be null");
    }
    this.runner = runner;
  }

  /**
   * Takes an ActionEvent as input from a button in the AnimationView and executes
   * the corresponding actions in the AnimationRunner.
   *
   * @param e takes an ActionEvent tied to a button in the AnimationView.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "play":
        runner.togglePlay();
        break;
      case "restart":
        runner.restartAnim();
        break;
      case "loop":
        runner.toggleLoop();
        break;
    }
  }
}
