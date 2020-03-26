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
   * Adds a Transformation to a Shape.
   *
   * @param shapeName the name of the Shape.
   * @param t The transformation to be added.
   * @throws IllegalArgumentException if the same transformation already exists in the same time
   *                                  frame on a single Shape.
   *                                  If the shape does not exist.
   */
  void addTransformation(String shapeName, Transformation t) throws IllegalArgumentException;

  /**
   *
   *
   * @param shapeName
   * @param type
   * @param start
   * @param end
   * @throws IllegalArgumentException if the shape does not exist.
   */
  void removeTransformation(String shapeName, TransformationType type, int start, int end) throws IllegalArgumentException;

  void setSpeed(double speed);

  void setBackgroundColor(Color windowColor);

}
