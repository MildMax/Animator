package cs5004.animator.view;

import cs5004.animator.model.shapes.Shape;
import cs5004.animator.model.shapes.ShapeType;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Comparator;
import java.util.List;

import javax.swing.JPanel;

/**
 * This class represents a panel that paints shapes to a window in JFrame.
 * Extends the JFrame class.
 */
public class ShapePanel extends JPanel {

  private List<Shape> shapeList;

  /**
   * Adds a shapelist containing data representing a frame of an animation
   * to be displayed on screen.
   *
   * @param shapeList takes list of shapes to be displayed on screen.
   * @throws IllegalArgumentException if the shape list is null.
   */
  public void addFrame(List<Shape> shapeList) throws IllegalArgumentException {
    if (shapeList == null) {
      throw new IllegalArgumentException("shapeList cannot be null");
    }
    this.shapeList = shapeList;
  }

  /**
   * Paints the list of shapes in order of their layer to the screen in JFrame.
   *
   * @param g takes a Graphics g object.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D)g;

    if (shapeList != null) {
      shapeList.sort(Comparator.comparing(Shape::getLayer));
      for (Shape shape : shapeList) {
        if (shape.getType() == ShapeType.RECTANGLE) {
          g2d.setColor(new Color(shape.getR(), shape.getG(), shape.getB()));
          g2d.fillRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
        }
        else if (shape.getType() == ShapeType.ELLIPSE) {
          g2d.setColor(new Color(shape.getR(), shape.getG(), shape.getB()));
          g2d.fillOval(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
        }
      }
    }
  }
}
