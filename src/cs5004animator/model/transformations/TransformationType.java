package cs5004animator.model.transformations;

/**
 * The TransformationType enum contains enum values indicating the type of each transformation
 * a Shape can undergo.
 */
public enum TransformationType {
  APPEARANCE("appearance"),
  CHANGECOLOR("change color"),
  CHANGEHEIGHT("change height"),
  CHANGETRANSPARENCY("change transparency"),
  CHANGEWIDTH("change width"),
  MOVE("move"),
  SCALE("scale");

  private String name;

  TransformationType(String name) {
    this.name = name;
  }

  /**
   * Returns a String indicating the name of the Transformation's type.
   *
   * @return a String indicating the name of the Transformation's type.
   */
  @Override
  public String toString() {
    return this.name;
  }
}
