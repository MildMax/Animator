/**
 * Create an abstract class called AbstractShape that is a generic shape.
 * This class implements the Shape interface.
 */
public abstract class AbstractShape implements Shape {
  protected final String name;
  protected final int initialHeight;
  protected final int initialWidth;
  protected final int initialCenterX;
  protected final int initialCenterY;
  protected final Color initialColor;
  protected final double initialTransparency;

  /**
   * Create a new instance of AbstractShape.
   *
   * @param initialHeight is the height of the shape.
   * @param initialWidth is the width of the shape.
   * @param initialCenterX is the X coordinate of the center of the shape.
   * @param initialCenterY is the Y coordinate of the center of the shape.
   * @param initialColor is the color of the shape.
   *
   */
  AbstractShape(String name, int initialHeight, int initialWidth, int initialCenterX,
                int initialCenterY, Color initialColor) {
    if (initialHeight <= 0) {
      throw new IllegalArgumentException("Initial height must be greater than zero.");
    }
    if (initialWidth <= 0) {
      throw new IllegalArgumentException("Initial width must be greater than zero.");
    }
    this.name = name;
    this.initialHeight = initialHeight;
    this.initialWidth = initialWidth;
    this.initialCenterX = initialCenterX;
    this.initialCenterY = initialCenterY;
    this.initialColor = initialColor;
    this.initialTransparency = 0.0;
  }

  /**
   * return the shape's name.
   *
   * @return the shape's name.
   */
  public String getName() {
    return this.name;
  }

  /**
   * return the shape's Type.
   *
   * @return the shape's Type.
   */
  public String getType() {
    return null;
  }

  /**
   * return the shape's height.
   *
   * @return the shape's height.
   */
  public int getInitialHeight() {
    return this.initialHeight;
  }

  /**
   * return the shape's width.
   *
   * @return the shape's width.
   */
  public int getInitialWidth() {
    return this.initialWidth;
  }

  /**
   * return the X coordinate of the shape's center.
   *
   * @return the X coordinate of the shape's center.
   */
  public int getInitialCenterX() {
    return this.initialCenterX;
  }

  /**
   * return the Y coordinate of the shape's center.
   *
   * @return the Y coordinate of the shape's center.
   */
  public int getInitialCenterY() {
    return this.initialCenterY;
  }

  /**
   * return the shape's transparency.
   *
   * @return the shape's transparency
   */
  public double getInitialTransparency() {
    return this.initialTransparency;
  }

  /**
   * return the shape's color.
   *
   * @return the shape's color.
   */
  public Color getInitialColor() {
    return this.initialColor;
  }

  /**
   * return the shape's "Create" statement.
   *
   * @return the shape's "Create" statement.
   */
  public String getCreateStatement() {
    return null;
  }
}
