package cs5004.animator.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import cs5004.animator.view.AnimationRunner;

/**
 * The TogglePlayMouseListener listens for clicks on the AnimationView window and changes the
 * state of the Animation in the AnimationRunner in the view. Extends the MouseAdapter class.
 */
public class TogglePlayMouseListener extends MouseAdapter {

  private AnimationRunner runner;

  /**
   * Takes an AnimationRunner that is running the current animation and stores it in the
   * current object.
   *
   * @param runner takes an AnimationRunner displaying the current animation.
   * @throws IllegalArgumentException if the animation runner is null.
   */
  public TogglePlayMouseListener(AnimationRunner runner) throws IllegalArgumentException {
    if (runner == null) {
      throw new IllegalArgumentException("AnimationRunner cannot be null");
    }
    this.runner = runner;
  }

  /**
   * When the left mouse button is clicked, changes the play state of the animation in the
   * AnimationRunner. If the animation is playing, pauses the animation. If the animation is paused,
   * plays the animation. When the right mouse button is clicked, restarts the animation.
   *
   * @param e takes a MouseEvent tied to the window that displays the animation.
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    super.mouseClicked(e);
    if (SwingUtilities.isLeftMouseButton(e)) {
      runner.togglePlay();
    } else if (SwingUtilities.isRightMouseButton(e)) {
      runner.restartAnim();
    }
  }

}
