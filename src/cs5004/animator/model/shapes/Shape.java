package cs5004.animator.model.shapes;

import java.util.List;

import cs5004.animator.model.transformations.Transformation;

/**
 * Contains the methods that must be implemented by every Shape. Contains methods for
 * getting information from the Shape as well as adding Transformations to the Shape.
 */
public interface Shape {

  /**
   * Add a transformation to the shape's transformation list. Throws an
   * IllegalArgumentException if another Transformation of the same type already
   * exists in the same time frame or if the Transformation is null. If the transformation
   * starts before the appear time of the Shape or after the disappear time of the Shape,
   * resets the appear or disappear time of the shape respectively.
   *
   * @param t is the transformation to be added.
   * @throws IllegalArgumentException if transformation is null.
   *                                  If another transformation of the same type already
   *                                  exists in the same time frame.
   */
  void addTransformation(Transformation t) throws IllegalArgumentException;

  /**
   * Return the name of the shape.
   *
   * @return the name of the shape.
   */
  String getName();

  /**
   * Returns the width of the shape.
   *
   * @return the width of the shape.
   */
  int getWidth();

  /**
   * Return the height of the shape.
   *
   * @return the height of the shape.
   */
  int getHeight();

  /**
   * Return the red color value of the shape.
   *
   * @return the red color value of the shape.
   */
  int getR();

  /**
   * Return the green color value of the shape.
   *
   * @return the green color value of the shape.
   */
  int getG();

  /**
   * Return the blue color value of the shape.
   *
   * @return the blue color value of the shape.
   */
  int getB();

  /**
   * Return the center x value of the shape.
   *
   * @return the center x value of the shape.
   */
  int getX();

  /**
   * Return the center y value of the shape.
   *
   * @return the center y value of the shape.
   */
  int getY();

  /**
   * Return the time at which the shape appears.
   *
   * @return the time at which the shape appears.
   */
  int getStart();

  /**
   * Return the time at which the shape disappears.
   *
   * @return the time at which the shape disappears.
   */
  int getEnd();

  /**
   * Calculates the values of the shape according to its list of
   * transformations at a given tick indicating the frame of the
   * animation and returns a new version of itself with modified values.
   *
   * @param tick the frame of the animation.
   * @return a deep copy of itself with modified values.
   */
  Shape getShapeAtTick(double tick);

  /**
   * Return the type of shape.
   *
   * @return the type of shape.
   */
  ShapeType getType();

  /**
   * Return the layer the shape will be displayed on.
   *
   * @return the layer the shape will be displayed on.
   */
  int getLayer();

  /**
   * Return the list of all transformations on the shape as a string.
   *
   * @return the list of all transformations on the shape as a string.
   */
  String getTransformationDescription();

  /**
   * Return the list of all transformations on the shape as a list.
   *
   * @return a list of all transformations on the shape as a list.
   */
  List<Transformation> getTransformationList();

  /**
   * Creates a String representing the creation of the shape.
   *
   * @return a String representing the creation of the shape.
   */
  String getCreateStatement();

  /**
   * Returns a String representation of the shape's name and the ticks at which it appears
   * and disappears on screen.
   *
   * @return a String representation of the shape's name and the ticks at which it appears
   *         and disappears on screen.
   */
  String getAppearStatement();

}
