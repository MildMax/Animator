/**
 * Create an interface that creates a generic shape.
 */
public interface Shape {

  /**
   *  Move the shape to a new location.
   *
   * @param newX is the X coordinate of the new center.
   * @param newY is the Y coordinate of the new center.
   */
  void move(int newX, int newY);

  /**
   * Change the size of the shape using a percentage to maintain the original proportions.
   * Maintains original length to width ratio.
   * For example, "0.5" would make the width and length each half their previous size.
   * For example, "2.0" would make the width and length each double their previous size.
   *
   * @param scaleFactor is the scaling percentage used.
   *                         New size is percentageChange% of old size.
   * @throws IllegalArgumentException if scaleFactor <= 0.
   */
  void scale(double scaleFactor);

  /**
   * Resize the shape by specifying a new length and/or width.
   *
   * @param newHeight is the new height.
   * @param newWidth is the new width.
   * @throws IllegalArgumentException if newHeight <= 0.
   * @throws IllegalArgumentException if newWidth <= 0.
   */
  void resize(int newHeight, int newWidth);

  /**
   * Change the color of the shape.
   *
   * @param newColor is the new color.
   */
  void changeColor(Color newColor);

  /**
   * Make the shape visible.
   */
  void appear();

  /**
   * Make the shape invisible.
   */
  void disappear();

  /**
   * Change the opacity of the shape.
   *
   * @param transparency is the percentage of opacity from 0 to 100%.
   * @throws IllegalArgumentException if new transparency < 0.
   * @throws IllegalArgumentException if new transparency > 100.
   */
  void changeTransparency(double transparency);

  /**
   * return the shape's height.
   *
   * @return the shape's height.
   */
  int getHeight();

  /**
   * return the shape's width.
   *
   * @return the shape's width.
   */
  int getWidth();

  /**
   * return the X coordinate of the shape's center.
   *
   * @return the X coordinate of the shape's center.
   */
  int getCenterX();

  /**
   * return the Y coordinate of the shape's center.
   *
   * @return the Y coordinate of the shape's center.
   */
  int getCenterY();

  /**
   * return the shape's transparency.
   *
   * @return the shape's transparency
   */
  double getTransparency();

  /**
   * return the shape's color.
   *
   * @return the shape's color.
   */
  Color getColor();
}
