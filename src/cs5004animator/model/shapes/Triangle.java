package cs5004animator.model.shapes;

import cs5004animator.model.Color;

/**
 * Create a rectangle class that extends the AbstractShape abstract class.
 */
public class Triangle extends AbstractShape {

  /**
   * Create a new instance of Rectangle.
   *
   * @param name is the unique name of the shape.
   * @param layer is the order (back to front) that the shape appears relative to the other shapes.
   * @param initialHeight  is the height of the shape.
   * @param initialWidth   is the width of the shape.
   * @param initialCenterX is the X coordinate of the center of the shape.
   * @param initialCenterY is the Y coordinate of the center of the shape.
   * @param initialColor   is the color of the shape.
   *
   * @throws IllegalArgumentException if initialHeight <= 0.
   * @throws IllegalArgumentException if initialWidth <= 0.
   */
  public Triangle(String name, int layer, int initialHeight, int initialWidth, int initialCenterX,
           int initialCenterY, Color initialColor) {
    super(name, layer, initialHeight, initialWidth, initialCenterX, initialCenterY, initialColor,
            ShapeType.TRIANGLE);
  }

  @Override
  public String toString() {
    String out = "";

    out += "Create "  + this.initialColor.toString() + " triangle " + this.name
            + " with center at (" + this.initialCenterX + ", "
            + this.initialCenterY +"), width " + this.initialWidth
            + ", and height " + this.initialHeight + " on layer "
            + this.layer + ".\n\n";

    out += getTransformationDescription();

    return out;
  }

}
