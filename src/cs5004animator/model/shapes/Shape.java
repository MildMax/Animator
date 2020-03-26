package cs5004animator.model.shapes;

import java.util.List;

import cs5004animator.model.transformations.Transformation;
import cs5004animator.model.transformations.TransformationType;

/**
 * Create an interface that creates a generic shape.
 */
public interface Shape {

  void addTransformation(Transformation t);

  void removeTransformation(TransformationType type, int start, int end);

  String getName();

  ShapeType getType();

  String getTransformationDescription();

  List<Transformation> getTransformationList();
}
