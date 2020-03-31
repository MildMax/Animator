package cs5004.animator.view;

import cs5004.animator.model.shapes.Shape;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Comparator;
import java.util.List;

import javax.swing.JPanel;



/**
 * This class represents a panel that paints images to a frame that extends the JFrame class.
 */
public class ShapePanel extends JPanel {

  private List<Shape> shapeList;

  public void addFrame(List<Shape> shapeList) {
    this.shapeList = shapeList;
  }

  /**
   * Paints the list of shapes in order of their layer to the screen.
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
        switch (shape.getType()) {
          case RECTANGLE:
            g2d.setColor(new Color(shape.getR(), shape.getG(), shape.getB()));
            g2d.fillRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
            break;
          case ELLIPSE:
            g2d.setColor(new Color(shape.getR(), shape.getG(), shape.getB()));
            g2d.fillOval(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
            break;
        }
      }
    }
  }
}
