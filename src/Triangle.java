/**
 * Create a rectangle class that extends the AbstractShape abstract class.
 */
public class Triangle extends AbstractShape {

  /**
   * Create a new instance of Rectangle.
   *
   * @param height  is the height of the shape.
   * @param width   is the width of the shape.
   * @param centerX is the X coordinate of the center of the shape.
   * @param centerY is the Y coordinate of the center of the shape.
   * @param color   is the color of the shape.
   */
  Triangle(int height, int width, int centerX, int centerY, Color color) {
    super(height, width, centerX, centerY, color);
  }
}
