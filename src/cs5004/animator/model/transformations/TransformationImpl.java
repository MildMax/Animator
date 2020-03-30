package cs5004.animator.model.transformations;
import cs5004.animator.model.transformations.Transformation;

/**
 * The TransformationImpl class holds values that describes the values a
 * Shape will take on over a given time frame. Implements the Transformation
 * interface.
 */
public class TransformationImpl implements Transformation {
  private String shapeName;
  private int t1;
  private int t2;

  private int x1;
  private int x2;
  private int y1;
  private int y2;

  private int w1;
  private int w2;
  private int h1;
  private int h2;

  private int r1;
  private int r2;
  private int g1;
  private int g2;
  private int b1;
  private int b2;

  public TransformationImpl(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1,
                            int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
                            int b2) {
    if (t1 < 0 || t2 < t1) {
      throw new IllegalArgumentException("invalid start:" + t1 + " and end:" + t2 +" times");
    }
    else if (w1 <= 0 || h1 <= 0 || w2 <= 0 || h2 <= 0) {
      throw new IllegalArgumentException("invalid width and height parameters");
    }
    else if (  r1 < 0 || r2 < 0 || r1 > 255 || r2 > 255
            || g1 < 0 || g2 < 0 || g1 > 255 || g2 > 255
            || b1 < 0 || b2 < 0 || b1 > 255 || b2 > 255) {
      throw new IllegalArgumentException("invalid color parameters");
    }

    this.shapeName = name;
    this.t1 = t1;
    this.t2 = t2;
    this.x1 = x1;
    this.x2 = x2;
    this.y1 = y1;
    this.y2 = y2;
    this.w1 = w1;
    this.w2 = w2;
    this.h1 = h1;
    this.h2 = h2;
    this.r1 = r1;
    this.r2 = r2;
    this.g1 = g1;
    this.g2 = g2;
    this.b1 = b1;
    this.b2 = b2;
  }

  /**
   * Return the name of the shape as a String.
   *
   * @return the name of the shape as a String.
   */
  public String getShapeName() {
    return this.shapeName;
  }

  /**
   * Return the starting x value of the shape in the transformation.
   *
   * @return the starting x value of the shape in the transformation.
   */
  @Override
  public int getX1() {
    return this.x1;
  }

  /**
   * Return the ending x value of the shape in the transformation.
   *
   * @return the ending x value of the shape in the transformation.
   */
  @Override
  public int getX2() {
    return this.x2;
  }

  /**
   * Return the starting y value of the shape in the transformation.
   *
   * @return the starting y value of the shape in the transformation.
   */
  @Override
  public int getY1() {
    return this.y1;
  }

  /**
   * Return the ending y value of the shape in the transformation.
   *
   * @return the ending y value of the shape in the transformation.
   */
  @Override
  public int getY2() {
    return this.y2;
  }

  /**
   * Return the starting width of the shape during the transformation.
   *
   * @return the starting width of the shape during the transformation.
   */
  @Override
  public int getW1() {
    return this.w1;
  }

  /**
   * Return the ending width of the shape during the transformation.
   *
   * @return the ending width of the shape during the transformation.
   */
  @Override
  public int getW2() {
    return this.w2;
  }

  /**
   * Return the starting height of the shape during the transformation.
   *
   * @return the starting height of the shape during the transformation.
   */
  @Override
  public int getH1() {
    return this.h1;
  }

  /**
   * Return the ending height of the shape during the transformation.
   *
   * @return the ending height of the shape during the transformation.
   */
  @Override
  public int getH2() {
    return this.h2;
  }

  /**
   * Return the starting red color value of the shape during the transformation.
   *
   * @return the starting red color value of the shape during the transformation.
   */
  @Override
  public int getR1() {
    return this.r1;
  }

  /**
   * Return the ending red color value of the shape during the transformation.
   *
   * @return the ending red color value of the shape during the transformation.
   */
  @Override
  public int getR2() {
    return this.r2;
  }

  /**
   * Return the starting green color value of the shape during the transformation.
   *
   * @return the starting green color value of the shape during the transformation.
   */
  @Override
  public int getG1() {
    return this.g1;
  }

  /**
   * Return the ending green color value of the shape during the transformation.
   *
   * @return the ending green color value of the shape during the transformation.
   */
  @Override
  public int getG2() {
    return this.g2;
  }

  /**
   * Return the starting blue color value of the shape during the transformation.
   *
   * @return the starting blue color value of the shape during the transformation.
   */
  @Override
  public int getB1() {
    return this.b1;
  }

  /**
   * Return the ending blue color value of the shape during the transformation.
   *
   * @return the ending blue color value of the shape during the transformation.
   */
  @Override
  public int getB2() {
    return this.b2;
  }

  /**
   * Return the start tick of the transformation.
   *
   * @return the start tick of the transformation
   */
  @Override
  public int getStart() {
    return t1;
  }

  /**
   * Return the end tick of the transformation.
   *
   * @return the end tick of the transformation.
   */
  @Override
  public int getEnd() {
    return t2;
  }

  /**
   * Returns a String representation of the transformation.
   *
   * @return a String representation of the transformation.
   */
  @Override
  public String toString() {
    String time = " from time t=" + this.t1 + " to time t=" + this.t2 + "\n";

    String total = "";

    if (x1 != x2 || y1 != y2) {
      total += this.shapeName + " moves from (" + x1 + "," + x2 + ") to (" + x2 + "," + y2
            + ")" + time;
    }

    if (w1 != w2) {
      total += this.shapeName + " changes width from " + w1 + " to " + w2 + time;
    }

    if (h1 != h2) {
      total += this.shapeName + " changes height from " + h1 + " to " + h2 + time;
    }

    if (r1 != r2 || g1 != g2 || b1 != b2) {
      total += this.shapeName + " changes from color (" + r1 + "," + g1 + "," + b1 + ") to color ("
            + r2 + "," + g2 + "," + b2 + ")" + time;
    }

    return total;
  }
}