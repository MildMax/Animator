package cs5004.animator.view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * This class displays a window with an animation of simple shapes
 * as supplied by the AnimationModelImpl. Extends the JFrame class
 * and implements the AnimationView interface.
 */
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.shapes.Shape;

public class VisualView extends JFrame implements AnimationView {

  private ShapePanel shapePanel;
  private JScrollPane scrollPane;

  /**
   * The AnimationViewImpl constructor takes x and y values specifying the
   * coordinates of the upper left corner of the display and a width and height
   * specifying the width and height of the screen.
   *
   * @param x takes the x coordinate of the position of the upper left corner of the display.
   * @param y takes the y coordinate of the position of the upper left corner of the display.
   * @param windowWidth takes the width of the display.
   * @param windowHeight takes the height of the display.
   * @throws IllegalArgumentException if the x or y values indicating the position of the
   *                                  upper left corner of the display are less than 0.
   *                                  If the width or height values of the display window are less
   *                                  than or equal to 0.
   */
  public VisualView(int x, int y, int windowWidth, int windowHeight,
                    int maxWidth, int maxHeight) throws IllegalArgumentException {
    super();

    if (windowWidth <= 0 || windowHeight <= 0) {
      throw new IllegalArgumentException("Width and height must be greater"
              + "than 0");
    } else if (maxWidth <= 0 || maxHeight <= 0) {
      throw new IllegalArgumentException("Max window width and height must be greater"
              + "than 0");
    } else if (x < 0 || y < 0) {
      throw new IllegalArgumentException("x and y positions cannot be negative");
    }

    shapePanel = new ShapePanel();
    shapePanel.setPreferredSize(new Dimension(maxWidth, maxHeight));

    scrollPane = new JScrollPane(shapePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(maxWidth, maxHeight));

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
   * Closes the window on screen.
   */
  @Override
  public void closeView() {
    this.setVisible(false);
    this.dispose();
  }

  /**
   * Not supported in visual view -- throws IllegalArgumentException.
   *
   * @param m takes an AnimationModel that stores an animation to be written to
   * @throws IllegalStateException if method is called -- writing not supported by visual view.
   */
  @Override
  public void write(AnimationModel m) throws IllegalStateException {
    throw new IllegalStateException("Visual view does not support writing");
  }
}
