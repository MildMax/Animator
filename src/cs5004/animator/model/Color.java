package cs5004.animator.model;

/**
 * The Color enum indicates a Color that a Shape can take on. Provides a toString() method that
 * returns a String representation of the color name.
 */
public class Color {

  private final double r;
  private final double g;
  private final double b;

  /**
   * Takes three doubles indicating the Red, Green, and Blue color values on a scale from 0.0
   * to 1.0, 0 indicating none, 1.0 indicating full. Throws IllegalArgumentException if any
   * r g or b double values are less than 0 or greater than 1.
   *
   * @param r takes the Red value of the color.
   * @param g takes the Green value of the color.
   * @param b takes the Blue value of the color.
   * @throws IllegalArgumentException if any r g or b double values are less than 0 or greater
   *                                  than 1.
   */
  public Color(double r, double g, double b) throws IllegalArgumentException {
    if (r < 0 || g < 0 || b < 0 || r > 1 || g > 1 || b > 1) {
      throw new IllegalArgumentException("Invalid color values supplied: " + r + ","
      + g + "," + b);
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Returns the Red value of the color.
   *
   * @return the Red value of the color.
   */
  public double getR() {
    return this.r;
  }

  /**
   * Returns the Green value of the color.
   *
   * @return the Green value of the color.
   */
  public double getG() {
    return this.g;
  }

  /**
   * Returns the Blue value of the color.
   *
   * @return the Blue value of the color.
   */
  public double getB() {
    return this.b;
  }

  /**
   * Returns a String representing the values of the color.
   *
   * @return a String representing the values of the color.
   */
  @Override
  public String toString() {
    return String.format("%.1f,%.1f,%.1f", r, g, b);
  }
}
