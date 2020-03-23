/**
 * Create a circle class that extends the Oval class.
 */
public class Circle extends Oval {

  /**
   * Create a new instance of circle.
   *
   * @param initialHeight  is the height of the shape.
   * @param initialWidth   is the width of the shape.
   * @param initialCenterX is the X coordinate of the center of the shape.
   * @param initialCenterY is the Y coordinate of the center of the shape.
   * @param initialColor   is the color of the shape.
   */
  Circle(String name, int initialHeight, int initialWidth, int initialCenterX, int initialCenterY,
         Color initialColor) {
    super(name, initialHeight, initialWidth, initialCenterX, initialCenterY, initialColor);
  }

  /**
   * Create a new instance of circle.
   *
   * @param radius  is the radius of the shape.
   * @param initialCenterX is the X coordinate of the center of the shape.
   * @param initialCenterY is the Y coordinate of the center of the shape.
   * @param initialColor   is the color of the shape.
   */
  Circle(String name, int radius, int initialCenterX, int initialCenterY, Color initialColor) {
    super(name, radius * 2, radius * 2, initialCenterX,
            initialCenterY, initialColor);
  }

  /**
   * return the shape's "Create" statement.
   *
   * @return the shape's "Create" statement.
   */
  public String getCreateStatement() {
    return    "Create "
            + this.initialColor
            + " circle "
            + this.name
            + " with center at ("
            + this.initialCenterX
            + ", "
            + this.initialCenterY
            + "), and radius "
            + this.initialHeight
            + ".\n"
            ;
  }
}
