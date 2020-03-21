/**
 * Create an abstract class called AbstractShape that is a generic shape.
 * This class implements the Shape interface.
 */
public abstract class AbstractShape implements Shape{
  private int height;
  private int width;
  private int centerX;
  private int centerY;
  private Color color;
  private double transparency;

  /**
   * Create a new instance of AbstractShape.
   *
   * @param height is the height of the shape.
   * @param width is the width of the shape.
   * @param centerX is the X coordinate of the center of the shape.
   * @param centerY is the Y coordinate of the center of the shape.
   * @param color is the color of the shape.
   *
   */
  AbstractShape(int height, int width, int centerX, int centerY, Color color) {
    if (height <= 0) {
      throw new IllegalArgumentException("Height must be greater than zero.");
    }
    if (width <= 0) {
      throw new IllegalArgumentException("Width must be greater than zero.");
    }
    this.height = height;
    this.width = width;
    this.centerX = centerX;
    this.centerY = centerY;
    this.color = color;
    this.transparency = 0.0;
  }

  /**
   *  Move the shape to a new location.
   *
   * @param newX is the X coordinate of the new center.
   * @param newY is the Y coordinate of the new center.
   */
  public void move(int newX, int newY) {
    this.centerX = newX;
    this.centerY = newY;
  }

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
  public void scale(double scaleFactor) {
    if (scaleFactor <= 0) {
      throw new IllegalArgumentException("Scale factor must be greater than zero.");
    }
    this.height = (int) (this.height * scaleFactor);
    this.width = (int) (this.width * scaleFactor);
  }

  /**
   * Resize the shape by specifying a new length and/or width.
   *
   * @param newHeight is the new height.
   * @param newWidth is the new width.
   * @throws IllegalArgumentException if newHeight <= 0.
   * @throws IllegalArgumentException if newWidth <= 0.
   */
  public void resize(int newHeight, int newWidth) {
    if (newHeight <= 0) {
      throw new IllegalArgumentException("newHeight must be greater than zero.");
    }
    if (newWidth <= 0) {
      throw new IllegalArgumentException("newWidth must be greater than zero.");
    }
    this.height = newHeight;
    this.width = newWidth;
  }

  /**
   * Change the color of the shape.
   *
   * @param newColor is the new color.
   */
  public void changeColor(Color newColor) {
    this.color = newColor;
  }

  /**
   * Make the shape visible.
   */
  public void appear() {
    if (this.transparency <= 0.0) {
      this.transparency = 100.0;
    }
  }

  /**
   * Make the shape invisible.
   */
  public void disappear() {
    this.transparency = 0.0;
  }

  /**
   * Change the opacity of the shape.
   *
   * @param transparency is the percentage of opacity from 0 to 100%.
   * @throws IllegalArgumentException if new transparency < 0.
   * @throws IllegalArgumentException if new transparency > 100.
   */
  public void changeTransparency(double transparency) {
    if (transparency < 0.0) {
      throw new IllegalArgumentException("transparencyPercentage may not be negative.");
    }
    if (transparency > 100.0) {
      throw new IllegalArgumentException("transparencyPercentage may not exceed 100.");
    }
    this.transparency = transparency;
  }

  /**
   * return the shape's height.
   *
   * @return the shape's height.
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * return the shape's width.
   *
   * @return the shape's width.
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * return the X coordinate of the shape's center.
   *
   * @return the X coordinate of the shape's center.
   */
  public int getCenterX() {
    return this.centerX;
  }

  /**
   * return the Y coordinate of the shape's center.
   *
   * @return the Y coordinate of the shape's center.
   */
  public int getCenterY() {
    return this.centerY;
  }

  /**
   * return the shape's transparency.
   *
   * @return the shape's transparency
   */
  public double getTransparency() {
    return this.transparency;
  }

  /**
   * return the shape's color.
   *
   * @return the shape's color.
   */
  public Color getColor() {
    return this.color;
  }
}
