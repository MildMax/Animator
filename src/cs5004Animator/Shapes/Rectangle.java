package cs5004Animator.Shapes;

import cs5004Animator.Color;
import cs5004Animator.Transformations.Transformation;

/**
 * Create a rectangle class that extends the AbstractShape abstract class.
 */
public class Rectangle extends AbstractShape {

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
  public Rectangle(String name, int layer, int initialHeight, int initialWidth, int initialCenterX,
            int initialCenterY, Color initialColor) {
    super(name, layer, initialHeight, initialWidth, initialCenterX, initialCenterY, initialColor);
  }

  @Override
  public String toString() {
    String out = "";

    out += "Create Rectangle " + this.name
            + " with center at (" + this.initialCenterX + ","
            + this.initialCenterY +") width " + this.initialWidth
            + " height " + this.initialHeight + " on layer "
            + this.layer + ".\n\n";

    for (Transformation t : this.getTransformationList()) {
      out += name + " " + t.toString() + "\n";
    }

    return out;
  }

}
