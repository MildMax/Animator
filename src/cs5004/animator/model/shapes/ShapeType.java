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

  ShapeType(String name) {
    this.name = name;
  }


  @Override
  public String toString() {
    return this.name;
  }
}
