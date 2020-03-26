package cs5004animator.model;

import java.util.List;

import cs5004animator.model.shapes.Shape;
import cs5004animator.model.transformations.Transformation;
import cs5004animator.model.transformations.TransformationType;

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
   * Returns a list of shapes currently stored in the AnimationModel.
   *
   * @return a list of shapes currently stored in the AnimationModel.
   */
  List<Shape> getShapes();

  /**
   * Returns a list of transformations currently stored in the AnimationModel sorted
   * chronologically by start time.
   *
   * @return a list of transformations currently stored in the AnimationModel sorted
   *         chronologically by start time.
   */
  List<Transformation> getTransformations();

  /**
   * Returns the total number of ticks in the animation.
   *
   * @return the total number of ticks in the animation.
   */
  int getTotalTicks();

  /**
   * Returns a new list of shapes and their respective values at the frame specified by parameter
   * tick.
   *
   * @param tick takes an int indicating the current frame of the animation.
   * @return a list of Shapes and their respective values at the frame specified by parameter tick.
   * @throws IllegalArgumentException if parameter tick is less than 0.
   */
  List<Shape> getShapesAtTick(int tick) throws IllegalArgumentException;

  /**
   * Returns the height of the window.
   *
   * @return the height of the the window.
   */
  int getWindowHeight();

  /**
   * Returns the width of the window.
   *
   * @return the width of the window.
   */
  int getWindowWidth();

  /**
   * Sets the background color of the window.
   *
   * @param windowColor indicates the color of the window background.
   * @throws IllegalArgumentException if Color windowColor is null.
   */
  void setBackgroundColor(Color windowColor) throws IllegalArgumentException;

  /**
   * Returns the color of the window.
   *
   * @return the color of the window.
   */
  Color getBackgroundColor();

}
