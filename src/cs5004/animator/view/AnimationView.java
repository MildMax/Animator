package cs5004.animator.view;

import java.io.IOException;
import java.util.List;

import javax.swing.*;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.shapes.Shape;

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

  void write(AnimationModel m);

  /**
   * Draws a new frame to the window according to the shapes in shapeList.
   *
   * @param shapeList takes a list of shapes to be drawn to the window.
   * @throws IllegalArgumentException if the list of shapes is null.
   */
  void drawNewFrame(List<Shape> shapeList) throws IllegalArgumentException;

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
