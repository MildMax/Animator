package cs5004animator.model.shapes;

import cs5004animator.model.Color;

/**
 * Create a square class that extends the rectangle class.
 */
public class Square extends AbstractShape {

  private final int side;

  /**
   * Create a new instance of Square.
   *
   * @param name is the unique name of the shape.
   * @param layer is the order (back to front) that the shape appears relative to the other shapes.
   * @param side   is the width of the shape.
   * @param initialCenterX is the X coordinate of the center of the shape.
   * @param initialCenterY is the Y coordinate of the center of the shape.
   * @param initialColor   is the color of the shape.
   *
   * @throws IllegalArgumentException if side <= 0.
   */
  public Square(String name, int layer, int side, int initialCenterX, int initialCenterY,
                Color initialColor) {
    super(name, layer, side, side, initialCenterX, initialCenterY, initialColor, ShapeType.SQUARE);

    this.side = side;
  }

  @Override
  public String toString() {
    String out = "";

    out += "Create square " + this.name
            + " with center at (" + this.initialCenterX + ","
            + this.initialCenterY + ") and side " + this.side + " on layer "
            + this.layer + " with color " + this.initialColor.toString()
            + ".\n\n";

    out += getTransformationDescription();

    return out;
  }

}
