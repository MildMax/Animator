package cs5004.animator.model.shapes;

/**
 * The shapeType class holds enum values that correspond to shapes that
 * extend the ShapeAbstract class and implement the ShapeInterface
 * for type checking.
 */
public enum ShapeType {
  CIRCLE("circle"),
  ELLIPSE("ellipse"),
  OVAL("oval"),
  RECTANGLE("rectangle"),
  SQUARE("square"),
  TRIANGLE("triangle");

  private String name;

  /**
   * Sets the String representation of the ShapeType enum.
   *
   * @param name the name of the shape.
   */
  ShapeType(String name) {
    this.name = name;
  }

  /**
   * Returns the String representation of the ShapeType.
   *
   * @return the String representation of the ShapeType.
   */
  @Override
  public String toString() {
    return this.name;
  }
}
