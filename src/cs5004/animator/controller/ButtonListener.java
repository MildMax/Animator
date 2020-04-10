package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs5004.animator.view.AnimationRunner;

/**
 * The ButtonListener class responds to input from buttons in the AnimationView and performs
 * the corresponding action in the view's AnimationRunner. Implements the ActionListener interface.
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
      throw new IllegalArgumentException("AnimationRunner cannot be null");
    }
    this.runner = runner;
  }

  /**
   * Takes an ActionEvent as input from a button in the AnimationView and executes
   * the corresponding actions in the AnimationRunner. If no action corresponds to the command,
   * throws IllegalArgumentException.
   *
   * @param e takes an ActionEvent tied to a button in the AnimationView.
   * @throws IllegalArgumentException if there is no string associated with the ActionCommand.
   */
  @Override
  public void actionPerformed(ActionEvent e) throws IllegalArgumentException {
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
      default:
        throw new IllegalArgumentException("No command corresponding to event");
    }
  }
}
