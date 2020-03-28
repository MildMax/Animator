package cs5004.animator.model.transformations;

import old.TransformationType;

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

}
