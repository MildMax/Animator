/**
 * Create an interface that creates a generic shape.
 */
public interface Shape {

  /**
   * return the shape's name.
   *
   * @return the shape's name.
   */
  String getName();

  /**
   * return the shape's height.
   *
   * @return the shape's height.
   */
  int getInitialHeight();

  /**
   * return the shape's width.
   *
   * @return the shape's width.
   */
  int getInitialWidth();

  /**
   * return the X coordinate of the shape's center.
   *
   * @return the X coordinate of the shape's center.
   */
  int getInitialCenterX();

  /**
   * return the Y coordinate of the shape's center.
   *
   * @return the Y coordinate of the shape's center.
   */
  int getInitialCenterY();

  /**
   * return the shape's transparency.
   *
   * @return the shape's transparency
   */
  double getInitialTransparency();

  /**
   * return the shape's color.
   *
   * @return the shape's color.
   */
  Color getInitialColor();

  /**
   * return the shape's "Create" statement.
   *
   * @return the shape's "Create" statement.
   */
  String getCreateStatement();
}
