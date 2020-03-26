package cs5004Animator;

import cs5004Animator.Shapes.Shape;
import cs5004Animator.Transformations.Transformation;
import cs5004Animator.Transformations.TransformationType;

/**
 * The AnimationModel interface declares methods that must be implemented to add shapes,
 * transformations to shapes and set information regarding the speed of the animation
 * and the color of the background.
 */
public interface AnimationModel {

  /**
   * Adds a shape to the Animation Model.
   *
   * @param shape takes the shape to be added.
   * @throws IllegalArgumentException if the shape already exists.
   */
  void addShape(Shape shape) throws IllegalArgumentException;

  /**
   * Removes a shape with a name corresponding to the parameter String shapeName from the
   * Animation Model. Throws IllegalArgumentException if there is no shape with a name
   * corresponding to the parameter String shapeName.
   *
   * @param shapeName takes a String that corresponds to the name of an existing Shape.
   * @throws IllegalArgumentException if the shape does not exist.
   */
  void removeShape(String shapeName) throws IllegalArgumentException;

  /**
   * Adds a Transformation to a Shape. Throws IllegalArgumentException if a transformation of the
   * same type exists with a time frame that overlaps with the time frame of Transformation t or
   * if the Shape does not exist.
   *
   * @param shapeName the name of the Shape.
   * @param t The transformation to be added.
   * @throws IllegalArgumentException if the same transformation already exists in the same time
   *                                  frame on a single Shape.
   *                                  If the shape does not exist.
   */
  void addTransformation(String shapeName, Transformation t) throws IllegalArgumentException;

  /**
   * Removes a Transformation from a Shape according to the name specified by parameter
   * String shapeName. Throws IllegalArgumentException if the transformation specified by the
   * type and time frame does not exist or if the shape does not exist. Throws
   * IllegalArgumentException if the String shapeName or TransformationType type is null.
   *
   * @param shapeName indicates the name of the Shape the transformation will be added to.
   * @param type indicates the type of transformation being removed.
   * @param start indicates the start time of the transformation being removed.
   * @param end indicates the end time of the transformation being removed.
   * @throws IllegalArgumentException If the transformation specified by the type and time frame
   *                                  does not exist.
   *                                  If the shape does not exist.
   */
  void removeTransformation(String shapeName, TransformationType type, int start, int end)
          throws IllegalArgumentException;

  /**
   * Sets the speed the Animation will be played at. Throws IllegalArgumentException if the
   * specified speed is less than or equal to 0 or greater than 16.
   *
   * @param speed indicates the speed the Animation will be played at.
   * @throws IllegalArgumentException if the specified speed is less than or equal to 0 or
   *                                  greater than 16.
   */
  void setSpeed(double speed) throws IllegalArgumentException;

  /**
   * Sets the background color of the window.
   *
   * @param windowColor indicates the color of the window background.
   */
  void setBackgroundColor(Color windowColor);

}
