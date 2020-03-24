import java.util.List;

public interface AnimationModel {

  String getDescription();

  void addShape(Shape shape);

  void addTransformation(String shapeName, Transformation t);

}
