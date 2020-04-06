package cs5004.animator.view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.shapes.Shape;

public abstract class AbstractVisualView extends JFrame implements AnimationView {

  protected ShapePanel shapePanel;
  protected AnimationRunner runner;
  protected int ticksPerSecond;

  public AbstractVisualView(int x, int y, int windowWidth, int windowHeight,
                            int maxWidth, int maxHeight, int ticksPerSecond) {
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

    this.ticksPerSecond = ticksPerSecond;

    shapePanel = new ShapePanel();
    shapePanel.setPreferredSize(new Dimension(maxWidth, maxHeight));

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
    runner = new AnimationRunnerImpl(m, this, this.ticksPerSecond);
    runner.openWindow();
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
