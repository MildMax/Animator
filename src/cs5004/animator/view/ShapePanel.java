package cs5004.animator.view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;

import cs5004.animator.model.shapes.Shape;
import cs5004.animator.model.shapes.ShapeType;

import javax.swing.*;

public class ShapePanel extends JPanel {

  List<Shape> shapeList;

  public ShapePanel(List<Shape> shapeList) {
    this.shapeList = shapeList;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D)g;



    for (Shape shape : shapeList) {
      //System.out.println("r:" + shape.getR() + " g:" + shape.getG() + " b:" + shape.getB());
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
