/**
 * Create a square class that extends the rectangle class.
 */
public class Square extends Rectangle {

  /**
   * Create a new instance of Square.
   *
   * @param name is the unique name of the shape.
   * @param layer is the order (back to front) that the shape appears relative to the other shapes.
   * @param initialHeight  is the height of the shape.
   * @param initialWidth   is the width of the shape.
   * @param initialCenterX is the X coordinate of the center of the shape.
   * @param initialCenterY is the Y coordinate of the center of the shape.
   * @param initialColor   is the color of the shape.
   *
   * @throws IllegalArgumentException if layer < 0.
   * @throws IllegalArgumentException if initialHeight <= 0.
   * @throws IllegalArgumentException if initialWidth <= 0.
   */
  Square(String name, int layer, int initialHeight, int initialWidth, int initialCenterX,
         int initialCenterY, Color initialColor) {
    super(name, layer, initialHeight, initialWidth, initialCenterX, initialCenterY, initialColor);
  }

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
   * @throws IllegalArgumentException if layer < 0.
   * @throws IllegalArgumentException if initialHeight <= 0.
   * @throws IllegalArgumentException if initialWidth <= 0.
   */
  Square(String name, int layer, int initialWidth, int initialCenterX, int initialCenterY,
         Color initialColor) {
    super(name, layer, initialWidth, initialWidth, initialCenterX, initialCenterY, initialColor);
  }

  /**
   * return the shape's Type.
   *
   * @return the shape's Type.
   */
  @Override
  public String getType() {
    return "Square";
  }

  /**
   * return the shape's "Create" statement.
   *
   * @return the shape's "Create" statement.
   */
  @Override
  public String getCreateStatement() {
    return    "Create "
            + this.initialColor
            + " square "
            + this.name
            + " with center at ("
            + this.initialCenterX
            + ", "
            + this.initialCenterY
            + "), and width "
            + this.initialWidth
            + ".\n"
            ;
  }
}
