/**
 * Create a circle class that extends the Oval class.
 */
public class Circle extends Oval {

  /*
  /**
   * Create a new instance of circle.
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
   *
  Circle(String name, int layer, int initialHeight, int initialWidth, int initialCenterX,
         int initialCenterY, Color initialColor) {
    super(name, layer, initialHeight, initialWidth, initialCenterX, initialCenterY, initialColor);
  }
  */

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
  }

  /**
   * return the shape's Type.
   *
   * @return the shape's Type.
   */
  @Override
  public String getType() {
    return "Circle";
  }

  //add get radius

  /**
   * return the shape's "Create" statement.
   *
   * @return the shape's "Create" statement.
   */
  @Override
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
            + " on layer "
            + this.layer
            + ".\n"
            ;
  }


}
