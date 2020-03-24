import java.util.List;

public interface AnimationModel {

  void addShape(Shape shape, String shapeName);

  void addTransformation(String shapeName, Transformation t);

}
