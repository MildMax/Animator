package cs5004.animator.model.transformations;

/**
 * The Transformation interface declares methods that must be implemented by every
 * transformation for each animation. Contains the following features:
 *
 * <p>- getShapeName() method</p>
 *
 * <p>- getStart() method</p>
 *
 * <p>- getType() method</p>
 *
 * <p>- getDescription() method</p>
 */

public interface Transformation {

  /**
   * Return the name of the shape as a String.
   *
   * @return the name of the shape as a String.
   */
  String getShapeName();

  /**
   * Returns the start time of the transformation.
   *
   * @return an int indicating the start time of the transformation.
   */
  int getStart();

  /**
   * Returns the end time of the transformation.
   *
   * @return an int indicating the end time of the transformation.
   */
  int getEnd();

  /**
   * Return the starting x value of the shape during the transformation.
   *
   * @return the starting x value of the transformation.
   */
  int getX1();

  /**
   * Return the ending x value of the shape during the transformation.
   *
   * @return the ending x value of the transformation.
   */
  int getX2();

  /**
   * Return the starting y value of the shape during the transformation.
   *
   * @return the starting y value of the transformation.
   */
  int getY1();

  /**
   * Return the ending y value of the shape during the transformation.
   *
   * @return the ending y value of the transformation.
   */
  int getY2();

  /**
   * Return the starting width value of the shape during the transformation.
   *
   * @return the starting width value of the shape during the transformation.
   */
  int getW1();

  /**
   * Return the ending width value of the shape during the transformation.
   *
   * @return the ending width value of the shape during the transformation.
   */
  int getW2();

  /**
   * Return the starting height value of the shape during the transformation.
   *
   * @return the starting height value of the shape during the transformation.
   */
  int getH1();

  /**
   * Return the ending height value of the shape during the transformation.
   *
   * @return the ending height value of the shape during the transformation.
   */
  int getH2();

  /**
   * Return the starting red color value of the shape during the transformation.
   *
   * @return the starting red color value of the shape during the transformation.
   */
  int getR1();

  /**
   * Return the ending red color value of the shape during the transformation.
   *
   * @return the ending red color value of the shape during the transformation.
   */
  int getR2();

  /**
   * Return the starting green color value of the shape during the transformation.
   *
   * @return the starting green color value of the shape during the transformation.
   */
  int getG1();

  /**
   * Return the ending green color value of the shape during the transformation.
   *
   * @return the ending green color value of the shape during the transformation.
   */
  int getG2();

  /**
   * Return the starting blue color value of the shape during the transformation.
   *
   * @return the starting blue color value of the shape during the transformation.
   */
  int getB1();

  /**
   * Return the ending blue color value of the shape during the transformation.
   *
   * @return the ending blue color value of the shape during the transformation.
   */
  int getB2();

}
