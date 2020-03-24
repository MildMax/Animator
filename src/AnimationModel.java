import java.util.List;

public interface AnimationModel {

  void addShape(Shape shape, String shapeName);

  void removeShape(String shapeName);

  void addTransformation(String shapeName, Transformation t);

  void removeTransformation(String shapeName, Class c, int start, int end);

}
