package cs5004Animator;

/**
 * The Color enum indicates a Color that a Shape can take on. Provides a toString() method that
 * returns a String representation of the color name.
 */
public enum Color {
  RED("red"),
  ORANGE("orange"),
  YELLOW("yellow"),
  GREEN("green"),
  BLUE("blue"),
  PURPLE("purple"),
  WHITE("white"),
  BLACK("black");

  private String name;

  Color(String name) {
    this.name = name;
  }

  /**
   * Returns a String representing the name of the color.
   *
   * @return a String representing the name of the color.
   */
  @Override
  public String toString() {
    return this.name;
  }
}
