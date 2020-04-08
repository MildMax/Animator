package cs5004.animator.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import cs5004.animator.model.AnimationModel;

/**
 * This class displays a window with an animation of simple shapes
 * as supplied by the AnimationModelImpl. Extends the JFrame class
 * and implements the AnimationView interface.
 */
public class VisualViewImpl extends AbstractVisualView {

  /**
   * The AnimationViewImpl constructor takes x and y values specifying the
   * coordinates of the upper left corner of the display and a width and height
   * specifying the width and height of the screen and a int ticksPerSecond
   * that specifies the ticks of the animation played over the course of one second.
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
   *                                  If the specified ticks per second is less than 1.
   */
  public VisualViewImpl(int x, int y, int windowWidth, int windowHeight,
                        int maxWidth, int maxHeight, int ticksPerSecond)
          throws IllegalArgumentException {
    super(x, y, windowWidth, windowHeight, maxWidth, maxHeight, ticksPerSecond);

    JScrollPane scrollPane = new JScrollPane(shapePanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    this.add(scrollPane);
    this.setTitle("Easy Animator");
    this.setPreferredSize(new Dimension(windowWidth, windowHeight));
    this.setLocation(x, y);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
  }

  /**
   * Runs the visual animation in a window displayed on screen.
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
   * Throws UnsupportedOperationException since visual view does not write any data to a file.
   *
   * @return always throws exception.
   * @throws UnsupportedOperationException since visual views do not write any data to a file.
   */
  @Override
  public String getOutFileContents() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("VisualView does not support getting file contents");
  }

  /**
   * Is overridden and nullified -- VisualView not utilize an AnimationRunner.
   *
   * @throws UnsupportedOperationException Visual View does not utilize an AnimationRunner.
   */
  @Override
  public AnimationRunnerImpl getRunner() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Text views do not support getting runner");
  }

  /**
   * Is overridden and nullified -- VisualView does not utilize CommandListeners.
   *
   * @throws UnsupportedOperationException text view does not utilize CommandListeners.
   */
  @Override
  public void setCommandListener(ActionListener e) {
    throw new UnsupportedOperationException("Text views do not require command listener");
  }

  /**
   * Is overridden and nullified -- VisualView does not utilize MouseListeners.
   *
   * @throws UnsupportedOperationException text view does not utilize MouseListeners.
   */
  @Override
  public void setMouseListener(MouseListener listener) {
    throw new UnsupportedOperationException("Text views do not require mouse listener");
  }

}
