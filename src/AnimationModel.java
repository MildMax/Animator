import java.util.List;

public interface AnimationModel {

  String getDescription();

  void addShape(Shape shape, String shapeName);

  void addTransformation(String shapeName, Transformation t);

}
