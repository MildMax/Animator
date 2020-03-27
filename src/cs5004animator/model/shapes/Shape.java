package cs5004animator.model.shapes;

import java.util.List;

import cs5004animator.model.transformations.Transformation;
import cs5004animator.model.transformations.TransformationType;

/**
 * Create an interface that creates a generic shape.
 */
public interface Shape {

  /**
   * Add a transformation to the shape's transformation list.
   *
   * @param t is the transformation to be added.
   * @throws IllegalArgumentException if transformation is null.
   */
  void addTransformation(Transformation t);

  /**
   * Remove a transformation from the shape's transformation list.
   *
   * @param type is the type of transformation.
   * @param start is the start time of the transformation.
   * @param end is the end time of the transformation.
   * @throws IllegalArgumentException if transformation is null.
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
}
