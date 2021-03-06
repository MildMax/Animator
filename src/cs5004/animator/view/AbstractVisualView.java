package cs5004.animator.view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JFrame;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.shapes.Shape;

/**
 * The AbstractVisualView class contains methods relevant to AnimationViews
 * that display the animation on screen in a visual format. Extends the JFrame
 * class and implements the AnimationView interface.
 */
public abstract class AbstractVisualView extends JFrame implements AnimationView {

  protected ShapePanel shapePanel;
  protected JScrollPane scrollPane;
  protected AnimationRunner runner;
  protected int ticksPerSecond;

  /**
   * The AbstractVisualView constructor takes two ints x and y indicating the position
   * of the window, two ints windowWidth and windowHeight indicating the width and
   * height of the window displaying the animation, two ints maxWidth and maxHeight
   * indicating the total size of the animation and an int indicating the ticks per second
   * of the animation. Checks for invalid parameter arguments. Initializes
   * JPanel shapes are drawn to.
   *
   * @param x the left x coordinate of the window.
   * @param y the upper y coordinate of the window
   * @param windowWidth the width of the window displaying the animation.
   * @param windowHeight the height of the window displaying the animation.
   * @param maxWidth the overall width of the animation.
   * @param maxHeight the overall height of the animation.
   * @param ticksPerSecond the ticks per second the animation will be played at.
   * @throws IllegalArgumentException if the x or y values indicating the position of the
   *                                  upper left corner of the display are less than 0.
   *                                  If the width or height values of the display window are less
   *                                  than or equal to 0.
   *                                  If the specified ticks per second is less than 1.
   */
  public AbstractVisualView(int x, int y, int windowWidth, int windowHeight,
                            int maxWidth, int maxHeight, int ticksPerSecond)
          throws IllegalArgumentException {
    if (windowWidth <= 0 || windowHeight <= 0) {
      throw new IllegalArgumentException("Width and height must be greater"
              + "than 0");
    } else if (maxWidth <= 0 || maxHeight <= 0) {
      throw new IllegalArgumentException("Max window width and height must be greater"
              + "than 0");
    } else if (x < 0 || y < 0) {
      throw new IllegalArgumentException("x and y positions cannot be negative");
    } else if (ticksPerSecond < 1) {
      throw new IllegalArgumentException("Ticks per second cannot be less than 1");
    }

    this.ticksPerSecond = ticksPerSecond;

    shapePanel = new ShapePanel();
    shapePanel.setPreferredSize(new Dimension(maxWidth, maxHeight));

    scrollPane = new JScrollPane(shapePanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    this.setTitle("Easy Animator");
    this.setLocation(x, y);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  /**
   * Displays the window on screen.
   */
  @Override
  public void openView() {
    this.setVisible(true);
  }

  /**
   * Closes the window on screen and exits the program.
   */
  @Override
  public void closeView() {
    this.setVisible(false);
    this.dispose();
    System.exit(0);
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
    runner = new AnimationRunnerImpl(m, this, this.ticksPerSecond);
    openView();
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

}
