package cs5004.animator.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.event.ChangeListener;

import cs5004.animator.model.AnimationModel;

/**
 * This class displays a window with an animation of simple shapes
 * as supplied by the AnimationModelImpl. Extends the AbstractVisualView
 * class which extends the JFrame class and implements the AniamtionView
 * interface.
 */
public class VisualViewImpl extends AbstractVisualView {

  /**
   * The AnimationViewImpl constructor takes x and y values specifying the
   * coordinates of the upper left corner of the display and a width and height
   * specifying the width and height of the screen and a int ticksPerSecond
   * that specifies the ticks of the animation played over the course of one second.
   * Throws IllegalArgumentException if the x or y values indicating the upper left
   * corner of the display are less than 0, if any width or height values are
   * less than or equal to zero, or if the specified ticks per second are less than
   * or equal to 0.
   *
   * @param x takes the x coordinate of the position of the upper left corner of the display.
   * @param y takes the y coordinate of the position of the upper left corner of the display.
   * @param windowWidth takes the width of the display.
   * @param windowHeight takes the height of the display.
   * @param maxWidth takes the maximum window width.
   * @param maxHeight takes the maximum window height.
   * @param ticksPerSecond takes the ticks per second between frames of the animation
   *                       in milliseconds.
   * @throws IllegalArgumentException if the x or y values indicating the position of the
   *                                  upper left corner of the display are less than 0.
   *                                  If the width or height values of the display window are less
   *                                  than or equal to 0.
   *                                  If the maxWidth or maxHeight values of the display window
   *                                  are less than or equal to 0.
   *                                  If the specified ticks per second is less than 1.
   */
  public VisualViewImpl(int x, int y, int windowWidth, int windowHeight,
                        int maxWidth, int maxHeight, int ticksPerSecond)
          throws IllegalArgumentException {
    super(x, y, windowWidth, windowHeight, maxWidth, maxHeight, ticksPerSecond);

    this.add(scrollPane);
    this.setPreferredSize(new Dimension(windowWidth, windowHeight));
    this.pack();
  }

  /**
   * Runs the visual animation in a window displayed on screen in its entirety
   * and then closes the window and exits the program upon completion.
   *
   * @param m takes an AnimationModel that stores an animation to be written to
   * @throws IllegalArgumentException if AnimationModel m is null.
   */
  @Override
  public void run(AnimationModel m) throws IllegalArgumentException {
    super.run(m);
    runner.startAnim();
  }

  /**
   * Is overridden and nullified -- VisualView not require getting the AnimationRunnerImpl.
   *
   * @throws UnsupportedOperationException Visual View does not require getting the
   *                                       AnimationRunnerImpl.
   */
  @Override
  public AnimationRunnerImpl getRunner() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("VisualViewImpl does not require getting the"
            + "AnimationRunnerImpl");
  }

  /**
   * Is overridden and nullified -- VisualViewImpl does not utilize CommandListeners.
   *
   * @param e takes an ActionListener to be assigned to buttons in a view.
   * @throws UnsupportedOperationException VisualViewImpl does not utilize CommandListeners.
   */
  @Override
  public void setCommandListener(ActionListener e) {
    throw new UnsupportedOperationException("VisualViewImpl does not require command listener");
  }

  /**
   * Is overridden and nullified -- VisualViewImpl does not utilize ChangeListers.
   *
   * @param e the ChangeListener to be set to an object in the view.
   * @throws UnsupportedOperationException VisualViewImpl does not utilize ChangeListeners.
   */
  @Override
  public void setChangeListener(ChangeListener e) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("VisualViewImpl does not require change listener");
  }

  /**
   * Is overridden and nullified -- VisualView does not utilize MouseListeners.
   *
   * @param listener takes a MouseListener to be assigned to an object in the view.
   * @throws UnsupportedOperationException text view does not utilize MouseListeners.
   */
  @Override
  public void setMouseListener(MouseListener listener) {
    throw new UnsupportedOperationException("Text views do not require mouse listener");
  }

}
