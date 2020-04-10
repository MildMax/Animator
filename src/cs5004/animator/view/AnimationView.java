package cs5004.animator.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.event.ChangeListener;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.shapes.Shape;

/**
 * This Interface represents the methods that are to be implemented in all classes
 * that display an animation, either textually or visually.
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
   * Get the AnimationRunner from the view.
   *
   * @return the AnimationRunner from the view.
   */
  AnimationRunner getRunner();

  /**
   * Sets the command listener for interactive aspects of the view.
   *
   * @param e an ActionListener that handles executing methods for a the view
   *          based on user interaction.
   * @throws IllegalArgumentException if ActionListener e is null.
   */
  void setCommandListener(ActionListener e) throws IllegalArgumentException;

  /**
   * Sets the ChangeListener to components in the view that respond to a measurable
   * change in state.
   *
   * @param e the ChangeListener to be set to an object in the view.
   * @throws IllegalArgumentException if the ChangeListener is null.
   */
  void setChangeListener(ChangeListener e) throws IllegalArgumentException;


  /**
   * Takes a MouseListener object and assigns it to a component in the view.
   *
   * @param listener the listener to be attached to an object in the view.
   * @throws IllegalArgumentException if the MouseListener is null.
   */
  void setMouseListener(MouseListener listener) throws IllegalArgumentException;

  /**
   * Returns a String with the contents written to the specified out file
   * in the current TextView. Used for testing purposes.
   *
   * @return a String with the contents written to the specified out file
   *         in the current TextView.
   */
  String getOutFileContents();

  /**
   * Creates an error message on screen corresponding to String parameter Message. Exits
   * system with exit status -1.
   *
   * @param message the error message to be printed to the screen.
   */
  static void displayErrorMessage(String message) {
    JOptionPane optionPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
    JDialog dialog = optionPane.createDialog("Easy Animator -- Error");
    dialog.setAlwaysOnTop(true);
    dialog.setVisible(true);
    System.exit(-1);
  }
}
