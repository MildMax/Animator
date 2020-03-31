package cs5004.animator.view;

import java.io.IOException;

/**
 * This Interface represents the methods that are to be implemented in all classes
 * that have a view.
 */
public interface AnimationView {

  /**
   * Opens the display of the view.
   */
  void openDisplay();

  /**
   * Closes the display of the view.
   */
  void closeDisplay();
}
