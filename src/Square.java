/**
 * Create a square class that extends the rectangle class.
 */
public class Square extends Rectangle {

  /**
   * Create a new instance of Square.
   *
   * @param initialHeight  is the height of the shape.
   * @param initialWidth   is the width of the shape.
   * @param initialCenterX is the X coordinate of the center of the shape.
   * @param initialCenterY is the Y coordinate of the center of the shape.
   * @param initialColor   is the color of the shape.
   */
  Square(String name, int initialHeight, int initialWidth, int initialCenterX, int initialCenterY,
         Color initialColor) {
    super(name, initialHeight, initialWidth, initialCenterX, initialCenterY, initialColor);
  }

  /**
   * Create a new instance of Square.
   *
   * @param initialWidth   is the width of the shape.
   * @param initialCenterX is the X coordinate of the center of the shape.
   * @param initialCenterY is the Y coordinate of the center of the shape.
   * @param initialColor   is the color of the shape.
   */
  Square(String name, int initialWidth, int initialCenterX, int initialCenterY,
         Color initialColor) {
    super(name, initialWidth, initialWidth, initialCenterX, initialCenterY, initialColor);
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
