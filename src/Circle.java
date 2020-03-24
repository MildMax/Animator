/**
 * Create a circle class that extends the Oval class.
 */
public class Circle extends Oval {

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
   * @throws IllegalArgumentException if initialHeight <= 0.
   * @throws IllegalArgumentException if initialWidth <= 0.
   */
  Circle(String name, int layer, int radius, int initialCenterX, int initialCenterY,
         Color initialColor) {
    super(name, layer, radius * 2, radius * 2, initialCenterX,
            initialCenterY, initialColor);

    this.radius = radius;
  }

  @Override
  public String toString() {
    String out = "";

    out += "Create Circle " + this.name
            + " with center at (" + this.initialCenterX + ","
            + this.initialCenterY +") radius " + this.radius
            + " on layer " + this.layer + ".\n\n";

    for (Transformation t : this.getTransformationList()) {
      out += name + " " + t.toString() + "\n";
    }

    return out;
  }
}
