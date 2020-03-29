package cs5004.animator.view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.*;

import cs5004.animator.model.shapes.Shape;

public class AnimationViewImpl extends JFrame {

  public AnimationViewImpl() {
    super();
    this.setTitle("Easy Animator");
  }

  public void setFrameSize(int width, int height) {
    this.setPreferredSize(new Dimension(width, height));
  }

  public void setFrameLocation(int x, int y) {
    this.setLocation(x, y);
  }

  public void displayFrame() {
    this.pack();
    this.setVisible(true);
  }

  public void drawNewFrame(List<Shape> shapeList) {
    this.getContentPane().removeAll();

    //add new rects and ellipses in here
    this.add(new ShapePanel(shapeList));

    this.repaint();
    this.revalidate();
  }

}
