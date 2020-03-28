package cs5004.animator.model.shapes;

import java.util.List;

import cs5004.animator.model.transformations.Transformation;
import cs5004.animator.model.transformations.TransformationType;

/**
 * Create an interface that creates a generic shape.
 */
public interface Shape {

  /**
   * Add a transformation to the shape's transformation list. Throws an
   * IllegalArgumentException if another Transformation of the same type already
   * exists in the same time frame or if the Transformation is null.
   *
   * @param t is the transformation to be added.
   * @throws IllegalArgumentException if transformation is null.
   *                                  If another transformation of the same type already
   *                                  exists in the same time frame.
   */
  void addTransformation(Transformation t);

  /**
   * Remove a transformation from the shape's transformation list. Throws an
   * IllegalArgumentException if the Transformation corresponding to the type
   * and time from does not exist or if the TransformationType is null.
   *
   * @param type is the type of transformation.
   * @param start is the start time of the transformation.
   * @param end is the end time of the transformation.
   * @throws IllegalArgumentException if transformation is null.
   *                                  If the transformation does not exist.
   *
   */
  void removeTransformation(TransformationType type, int start, int end);

  /**
   * Return the name of the shape.
   *
   * @return the name of the shape.
   */
  String getName();

  /**
   * Return the type of shape.
   *
   * @return the type of shape.
   */
  ShapeType getType();

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

  Shape makeModifiedShape(int tick);

  List<Transformation> getCurrentTransformations(int tick);
}
