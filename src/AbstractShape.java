/**
 * Create an abstract class called AbstractShape that is a generic shape.
 * This class implements the Shape interface.
 */
public abstract class AbstractShape implements Shape {
  protected final String name;
  protected final int layer;
  protected final int initialHeight;
  protected final int initialWidth;
  protected final int initialCenterX;
  protected final int initialCenterY;
  protected final Color initialColor;
  protected final double initialTransparency;

  /**
   * Create a new instance of AbstractShape.
   *
   * @param name is the unique name of the shape.
   * @param layer is the order (back to front) that the shape appears relative to the other shapes.
   * @param initialHeight is the height of the shape.
   * @param initialWidth is the width of the shape.
   * @param initialCenterX is the X coordinate of the center of the shape.
   * @param initialCenterY is the Y coordinate of the center of the shape.
   * @param initialColor is the color of the shape.
   *
   * @throws IllegalArgumentException if initialHeight <= 0.
   * @throws IllegalArgumentException if initialWidth <= 0.
   *
   */
  AbstractShape(String name, int layer, int initialHeight, int initialWidth, int initialCenterX,
                int initialCenterY, Color initialColor) {
    if (initialHeight <= 0) {
      throw new IllegalArgumentException("Initial height must be greater than zero.");
    }
    if (initialWidth <= 0) {
      throw new IllegalArgumentException("Initial width must be greater than zero.");
    }
    this.name = name;
    this.layer = layer;
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
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * return the shape's Type.
   *
   * @return the shape's Type.
   */
  @Override
  public String getType() {
    return null;
  }

  /**
   * return the shape's Layer.
   *
   * @return the shape's Layer.
   */
  @Override
  public int getLayer() {
    return this.layer;
  }

  /**
   * return the shape's height.
   *
   * @return the shape's height.
   */
  @Override
  public int getInitialHeight() {
    return this.initialHeight;
  }

  /**
   * return the shape's width.
   *
   * @return the shape's width.
   */
  @Override
  public int getInitialWidth() {
    return this.initialWidth;
  }

  /**
   * return the X coordinate of the shape's center.
   *
   * @return the X coordinate of the shape's center.
   */
  @Override
  public int getInitialCenterX() {
    return this.initialCenterX;
  }

  /**
   * return the Y coordinate of the shape's center.
   *
   * @return the Y coordinate of the shape's center.
   */
  @Override
  public int getInitialCenterY() {
    return this.initialCenterY;
  }

  /**
   * return the shape's transparency.
   *
   * @return the shape's transparency
   */
  @Override
  public double getInitialTransparency() {
    return this.initialTransparency;
  }

  /**
   * return the shape's color.
   *
   * @return the shape's color.
   */
  @Override
  public Color getInitialColor() {
    return this.initialColor;
  }

  /**
   * return the shape's "Create" statement.
   *
   * @return the shape's "Create" statement.
   */
  @Override
  public String getCreateStatement() {
    return null;
  }
}
