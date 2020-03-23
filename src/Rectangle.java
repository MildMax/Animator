/**
 * Create a rectangle class that extends the AbstractShape abstract class.
 */
public class Rectangle extends AbstractShape {

  /**
   * Create a new instance of Rectangle.
   *
   * @param initialHeight  is the height of the shape.
   * @param initialWidth   is the width of the shape.
   * @param initialCenterX is the X coordinate of the center of the shape.
   * @param initialCenterY is the Y coordinate of the center of the shape.
   * @param initialColor   is the color of the shape.
   */
  Rectangle(String name, int initialHeight, int initialWidth, int initialCenterX,
            int initialCenterY, Color initialColor) {
    super(name, initialHeight, initialWidth, initialCenterX, initialCenterY, initialColor);
  }

  /**
   * return the shape's Type.
   *
   * @return the shape's Type.
   */
  @Override
  public String getType() {
    return "Rectangle";
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
