package cs5004.animator.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.shapes.Shape;

/**
 * This class displays a window with an animation of simple shapes
 * as supplied by the AnimationModelImpl. Extends the JFrame class
 * and implements the AnimationView interface.
 */
public class VisualViewImpl extends JFrame implements AnimationView {

  private ShapePanel shapePanel;
  private int ticksPerSecond;

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
    super();

    this.ticksPerSecond = ticksPerSecond;

    if (windowWidth <= 0 || windowHeight <= 0) {
      throw new IllegalArgumentException("Width and height must be greater"
              + "than 0");
    } else if (maxWidth <= 0 || maxHeight <= 0) {
      throw new IllegalArgumentException("Max window width and height must be greater"
              + "than 0");
    } else if (x < 0 || y < 0) {
      throw new IllegalArgumentException("x and y positions cannot be negative");
    } else if (ticksPerSecond < 1) {
      throw new IllegalArgumentException("Delay cannot be less than 1");
    }

    shapePanel = new ShapePanel();
    shapePanel.setPreferredSize(new Dimension(maxWidth, maxHeight));

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
   * Displays the window on screen.
   */
  @Override
  public void openView() {
    this.setVisible(true);
  }

  /**
   * Closes the window on screen.
   */
  @Override
  public void closeView() {
    this.setVisible(false);
    this.dispose();
  }

  /**
   * Draws a frame on the window according to the list of shapes provided to parameter shapeList.
   * Throws IllegalArgumentException if the list of shapes is null.
   *
   * @param shapeList takes a list of shapes to be drawn.
   * @throws IllegalArgumentException if the list of shapes is null.
   */
  @Override
  public void drawNewFrame(List<Shape> shapeList) throws IllegalArgumentException {
    if (shapeList == null) {
      throw new IllegalArgumentException("shapeList cannot be null");
    }

    //add new rects and ellipses in here
    shapePanel.addFrame(shapeList);

    this.repaint();
    this.revalidate();
  }

  /**
   * Runs the visual animation in a window displayed on screen.
   *
   * @param m takes an AnimationModel that stores an animation to be written to
   * @throws IllegalArgumentException if AnimationModel m is null.
   */
  @Override
  public void run(AnimationModel m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("Animation Model cannot be null.");
    }
    AnimationRunner runner = new AnimationRunnerImpl(m, this, this.ticksPerSecond);
    runner.openWindow();
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

  @Override
  public AnimationRunnerImpl getRunner() {
    throw new UnsupportedOperationException("Visual view does not support getting runner");
  }

  @Override
  public void setSpeed() {
    throw new UnsupportedOperationException("Visual view does not allow for setting speed");
  }

  @Override
  public void setCommandListener(ActionListener e) {
    throw new UnsupportedOperationException("Visual view does not require command listener");
  }

  @Override
  public void setMouseListener(MouseListener listener) {
    throw new UnsupportedOperationException("Visual vies does not require mouse listener");
  }
}
