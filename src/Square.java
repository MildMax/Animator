/**
 * Create a square class that extends the rectangle class.
 */
public class Square extends Rectangle {

  /**
   * Create a new instance of Square.
   *
   * @param height  is the height of the shape.
   * @param width   is the width of the shape.
   * @param centerX is the X coordinate of the center of the shape.
   * @param centerY is the Y coordinate of the center of the shape.
   * @param color   is the color of the shape.
   */
  Square(String name, int height, int width, int centerX, int centerY, Color color) {
    super(name, height, width, centerX, centerY, color);
  }

  /**
   * Create a new instance of Square.
   *
   * @param width   is the width of the shape.
   * @param centerX is the X coordinate of the center of the shape.
   * @param centerY is the Y coordinate of the center of the shape.
   * @param color   is the color of the shape.
   */
  Square(String name, int width, int centerX, int centerY, Color color) {
    super(name, width, width, centerX, centerY, color);
  }

  /**
   * return the shape's "Create" statement.
   *
   * @return the shape's "Create" statement.
   */
  public String getCreateStatement() {
    return    "Create "
            + this.initialColor
            + " rectangle "
            + this.name
            + " with center at ("
            + this.initialCenterX
            + ", "
            + this.initialCenterY
            + "), height "
            + this.initialHeight
            + ", and width "
            + this.initialWidth
            + ".\n"
            ;
  }

}
