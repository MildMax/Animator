import java.util.List;

public interface AnimationModel {

  void addShape(Shape shape, String shapeName);

  void removeShape(String shapeName);

  void addTransformation(String shapeName, Transformation t);

  void removeTransformation(String shapeName, TransformationType type, int start, int end);

  void setSpeed(double speed);

}
