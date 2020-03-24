/**
 * Create a square class that extends the rectangle class.
 */
public class Square extends Rectangle {

  private final int side;

  /**
   * Create a new instance of Square.
   *
   * @param name is the unique name of the shape.
   * @param layer is the order (back to front) that the shape appears relative to the other shapes.
   * @param initialWidth   is the width of the shape.
   * @param initialCenterX is the X coordinate of the center of the shape.
   * @param initialCenterY is the Y coordinate of the center of the shape.
   * @param initialColor   is the color of the shape.
   *
   * @throws IllegalArgumentException if initialHeight <= 0.
   * @throws IllegalArgumentException if initialWidth <= 0.
   */
  Square(String name, int layer, int initialWidth, int initialCenterX, int initialCenterY,
         Color initialColor) {
    super(name, layer, initialWidth, initialWidth, initialCenterX, initialCenterY, initialColor);

    this.side = initialWidth;
  }

  @Override
  public String toString() {
    String out = "";

    out += "Create Square " + this.name
            + " with center at (" + this.initialCenterX + ","
            + this.initialCenterY +") side " + this.side + " on layer "
            + this.layer + ".\n\n";

    for (Transformation t : this.getTransformationList()) {
      out += name + " " + t.toString() + "\n";
    }

    return out;
  }

}
