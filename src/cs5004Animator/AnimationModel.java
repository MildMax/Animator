package cs5004Animator;

import cs5004Animator.Shapes.Shape;
import cs5004Animator.Transformations.Transformation;
import cs5004Animator.Transformations.TransformationType;

public interface AnimationModel {

  void addShape(Shape shape);

  void removeShape(String shapeName);

  void addTransformation(String shapeName, Transformation t);

  void removeTransformation(String shapeName, TransformationType type, int start, int end);

  void setSpeed(double speed);

  public void setBackgroundColor(Color windowColor);

}
