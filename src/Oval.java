/**
 * Create an Oval class that extends the AbstractShape abstract class.
 */
public class Oval extends AbstractShape {
  private int height;
  private int width;
  private int centerX;
  private int centerY;
  private Color color;
  private double transparency;

  /**
   * Create a new instance of Oval.
   *
   * @param verticalRadius  is the height of the shape.
   * @param horizontalRadius   is the width of the shape.
   * @param centerX is the X coordinate of the center of the shape.
   * @param centerY is the Y coordinate of the center of the shape.
   * @param color   is the color of the shape.
   */
  Oval(int verticalRadius, int horizontalRadius, int centerX, int centerY, Color color) {
    super(verticalRadius * 2, horizontalRadius * 2, centerX, centerY, color);
  }
}
