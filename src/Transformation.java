/**
 * The Transformation interface declares methods that must be implemented by every
 * transformation for each animation. Contains the following features:
 *
 * <p>- getStart() method</p>
 *
 * <p>- getEnd() method</p>
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

  /**
   * Returns a description of the transformation.
   *
   * @param name the name of the shape.
   * @return a description of the transformation.
   */
  String getDescription(Shape shape);
}
