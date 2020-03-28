package old;

import java.util.List;

import cs5004.animator.model.shapes.Shape;
import cs5004.animator.model.shapes.ShapeType;
import cs5004.animator.model.transformations.Transformation;

/**
 * Create a circle class that extends the Oval class.
 */
public class Circle extends AbstractShape {

  private final int radius;

  /**
   * Create a new instance of circle.
   *
   * @param name is the unique name of the shape.
   * @param layer is the order (back to front) that the shape appears relative to the other shapes.
   * @param radius  is the radius of the shape.
   * @param initialCenterX is the X coordinate of the center of the shape.
   * @param initialCenterY is the Y coordinate of the center of the shape.
   * @param initialColor   is the color of the shape.
   *
   * @throws IllegalArgumentException if radius <= 0.
   */
  public Circle(String name, int layer, int radius, int initialCenterX, int initialCenterY,
         Color initialColor) {
    super(name, ShapeType.CIRCLE);

    this.radius = radius;
  }

  /**
   * Return the full description of the shape and its transformations.
   *
   * @return the full description of the shape and its transformations.
   */
  @Override
  public String toString() {
    String out = "";

    out += "Create circle " + this.getName()
            + " with center at (" + this.getX() + ","
            + this.getY() + ") and radius " + this.radius
            + " on layer " + this + " with color " + this.getR() + "," + this.getG() + ","
            + this.getB() + ".\n\n";

    out += getTransformationDescription();

    return out;
  }

  @Override
  public Shape makeModifiedShape(int tick) {
    int newRadius = this.radius;
    int newX = this.initialCenterX;
    int newY = this.initialCenterY;
    double newTransparency = this.initialTransparency;
    Color newColor = this.initialColor;
    List<Transformation> currList = getCurrentTransformations(tick);
    for (Transformation t : currList) {
      switch (t.getType()) {
        case MOVE:
          newX = ((Move) t).modifyX(newX, tick);
          newY = ((Move) t).modifyY(newY, tick);
          break;
        case SCALE:
          newRadius = ((Scale) t).scaleVal(newRadius, tick);
          break;
        case CHANGECOLOR:
          newColor = ((ChangeColor) t).modifyColor(newColor, tick);
          break;
        case CHANGEWIDTH:
          newRadius = ((ChangeWidth) t).modifyWidth(newRadius, tick);
          break;
        case CHANGEHEIGHT:
          newRadius = ((ChangeHeight) t).modifyHeight(newRadius, tick);
          break;
        case CHANGETRANSPARENCY:
          newTransparency = ((ChangeTransparency) t).modifyTransparency(newTransparency, tick);
          break;
      }
    }
    Circle c = new Circle(this.name, this.layer, newRadius, newX, newY, newColor);
    c.initialTransparency = newTransparency;
    if (newTransparency == 0) {
      return null;
    }
    else {
      return c;
    }
  }
}