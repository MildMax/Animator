package cs5004.animator.view;
import cs5004.animator.model.shapes.Shape;

import java.util.List;

import javax.swing.*;

/**
 * This Interface outlines the methods necessary for creating and displaying a
 * frame on screen, drawing simple shapes to that frame, and closing the frame.
 */
public interface AnimationView {

  /**
   * Displays the window on screen.
   */
  void displayFrame();

  /**
   * Draws a new frame to the window according to the shapes in shapeList.
   *
   * @param shapeList takes a list of shapes to be drawn to the window.
   * @throws IllegalArgumentException if the list of shapes is null.
   */
  void drawNewFrame(List<Shape> shapeList) throws IllegalArgumentException;

  /**
   * Closes the frame on screen and exits the animation.
   */
  void closeFrame();

  /**
   * Creates an error message on screen corresponding to String parameter Message.
   *
   * @param message the error message to be printed to the screen.
   */
  static void displayErrorMessage(String message) {
    JOptionPane optionPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
    JDialog dialog = optionPane.createDialog("Easy Animator -- Error");
    dialog.setAlwaysOnTop(true);
    dialog.setVisible(true);
    System.exit(-2);
  }
}
