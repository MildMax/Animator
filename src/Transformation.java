/**
 * The Transformation interface declares methods that must be implemented by every
 * transformation for each animation. Contains the following features:
 *
 * <p>- getShapeName() method</p>
 *
 * <p>- getStart() method</p>
 *
 * <p>- getEnd() method</p>
 *
 * <p>- getDescription() method</p>
 */

public interface Transformation {

  /**
   * Returns the name of the shape on which the transformation occurs.
   *
   * @return the name of the shape on which the transformation occurs.
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
   * return the transformation's description.
   *
   * @return the transformation's description.
   */
  String getDescription();
}
