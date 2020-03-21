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
  Square(int height, int width, int centerX, int centerY, Color color) {
    super(height, width, centerX, centerY, color);
  }

  /**
   * Create a new instance of Square.
   *
   * @param width   is the width of the shape.
   * @param centerX is the X coordinate of the center of the shape.
   * @param centerY is the Y coordinate of the center of the shape.
   * @param color   is the color of the shape.
   */
  Square(int width, int centerX, int centerY, Color color) {
    super(width, width, centerX, centerY, color);
  }

}
