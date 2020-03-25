package cs5004Animator.Shapes;

import java.util.List;

import cs5004Animator.Transformations.Transformation;
import cs5004Animator.Transformations.TransformationType;

/**
 * Create an interface that creates a generic shape.
 */
public interface Shape {

  void addTransformation(Transformation t);

  void removeTransformation(TransformationType type, int start, int end);

  String getName();

  String getTransformationDescription();
}
