/**
 * Create an Oval class that extends the AbstractShape abstract class.
 */
public class Oval extends AbstractShape {

  private final int initialVerticalRadius;
  private final int initialHorizontalRadius;

  /**
   * Create a new instance of Oval.
   *
   * @param name is the unique name of the shape.
   * @param layer is the order (back to front) that the shape appears relative to the other shapes.
   * @param initialVerticalRadius  is the height of the shape.
   * @param initialHorizontalRadius   is the width of the shape.
   * @param initialCenterX is the X coordinate of the center of the shape.
   * @param initialCenterY is the Y coordinate of the center of the shape.
   * @param initialColor   is the color of the shape.
   *
   * @throws IllegalArgumentException if initialHeight <= 0.
   * @throws IllegalArgumentException if initialWidth <= 0.
   */
  Oval(String name, int layer, int initialVerticalRadius, int initialHorizontalRadius,
       int initialCenterX, int initialCenterY, Color initialColor) {
    super(name, layer,initialVerticalRadius * 2,
            initialHorizontalRadius * 2, initialCenterX, initialCenterY, initialColor);

    this.initialVerticalRadius = initialVerticalRadius;
    this.initialHorizontalRadius = initialHorizontalRadius;
  }

  @Override
  public String toString() {
    String out = "";

    out += "Create Oval " + this.name
            + " with center at (" + this.initialCenterX + ","
            + this.initialCenterY +") horizontal radius " + this.initialHorizontalRadius
            + " vertical radius " + this.initialVerticalRadius + " on layer "
            + this.layer + ".\n\n";

    for (Transformation t : this.getTransformationList()) {
      out += name + " " + t.toString() + "\n";
    }

    return out;
  }
}
