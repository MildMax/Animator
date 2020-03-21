/**
 * Create a circle class that extends the Oval class.
 */
public class Circle extends Oval {
  private int height;
  private int width;
  private int centerX;
  private int centerY;
  private Color color;
  private double transparency;

  /**
   * Create a new instance of circle.
   *
   * @param height  is the height of the shape.
   * @param width   is the width of the shape.
   * @param centerX is the X coordinate of the center of the shape.
   * @param centerY is the Y coordinate of the center of the shape.
   * @param color   is the color of the shape.
   */
  Circle(int height, int width, int centerX, int centerY, Color color) {
    super(height, width, centerX, centerY, color);
  }

  /**
   * Create a new instance of circle.
   *
   * @param radius  is the radius of the shape.
   * @param centerX is the X coordinate of the center of the shape.
   * @param centerY is the Y coordinate of the center of the shape.
   * @param color   is the color of the shape.
   */
  Circle(int radius, int centerX, int centerY, Color color) {
    super(radius * 2, radius * 2, centerX, centerY, color);
  }
}
