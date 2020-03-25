package cs5004Animator.Shapes;

import java.util.List;

import cs5004Animator.Transformations.Transformation;

/**
 * Create an interface that creates a generic shape.
 */
public interface Shape {

  void addTransformation(Transformation t);

  void removeTransformation(Transformation t);

  String getName();

  List<Transformation> getTransformationList();

  String getTransformationDescription();
}
