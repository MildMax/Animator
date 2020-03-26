package cs5004animator.model.shapes;

import cs5004animator.model.Color;

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
    super(name, layer, radius * 2, radius * 2, initialCenterX,
            initialCenterY, initialColor, ShapeType.CIRCLE);

    this.radius = radius;
  }

  @Override
  public String toString() {
    String out = "";

    out += "Create "  + this.initialColor + " circle " + this.name
            + " with center at (" + this.initialCenterX + ", "
            + this.initialCenterY +") and radius " + this.radius
            + " on layer " + this.layer + ".\n\n";

    out += getTransformationDescription();

    return out;
  }
}
