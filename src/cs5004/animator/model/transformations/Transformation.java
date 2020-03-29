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

  int getX1();

  int getX2();

  int getY1();

  int getY2();

  int getW1();

  int getW2();

  int getH1();

  int getH2();

  int getR1();

  int getR2();

  int getG1();

  int getG2();

  int getB1();

  int getB2();

}
