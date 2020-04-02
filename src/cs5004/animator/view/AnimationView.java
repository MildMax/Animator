package cs5004.animator.view;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JDialog;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.shapes.Shape;

/**
 * This Interface represents the methods that are to be implemented in all classes
 * that have a view.
 */
public interface AnimationView {

  /**
   * Opens the view.
   */
  void openView();

  /**
   * Closes the view.
   */
  void closeView();

  /**
   * Runs the animation in the view.
   *
   * @param m takes an AnimationModel that stores an animation to be written to
   *          a text file.
   * @throws IllegalArgumentException if the AnimationModel is null.
   */
  void run(AnimationModel m) throws IllegalArgumentException;

  /**
   * Draws a new frame to the window according to the shapes in shapeList.
   *
   * @param shapeList takes a list of shapes to be drawn to the window.
   * @throws IllegalArgumentException if the list of shapes is null.
   */
  void drawNewFrame(List<Shape> shapeList) throws IllegalArgumentException;

  /**
   * Returns a String with the contents written to the specified out file
   * in the current TextView. Used for testing purposes.
   *
   * @return a String with the contents written to the specified out file
   *         in the current TextView.
   */
  String getOutFileContents();

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
