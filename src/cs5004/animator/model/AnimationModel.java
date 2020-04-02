package cs5004.animator.model;

import java.util.List;

import cs5004.animator.model.shapes.Shape;
import cs5004.animator.model.transformations.Transformation;

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
  List<Shape> getShapesAtTick(double tick) throws IllegalArgumentException;

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
   * Returns the x value of the window's initial position with regard to the upper left corner of
   * the screen.
   *
   * @return the x value of the window's initial position with regard to the upper left corner
   *         of the screen.
   */
  int getBoundX();

  /**
   * Returns the y value of the window's initial position with regard to the upper left corner of
   * the screen.
   *
   * @return the y value of the window's initial position with regard to the upper left corner
   *         of the screen.
   */
  int getBoundY();

  /**
   * Return the maximum width of the window that displays the animation.
   *
   * @return the maximum width of the window that displays the animation.
   */
  int getAnimationWidth();

  /**
   * Return the maximum height of the window that displays the animation.
   *
   * @return the maximum height of the window that displays the animation.
   */
  int getAnimationHeight();

}
