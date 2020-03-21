import java.util.List;

public interface AnimationModel {

  List listOfShapeWrappers(String descriptionFromController);

  String getDescription();

  List getAllTransformations();

  void addShape(Shape shape, String shapeName);

  void addMove(String shapeName, int startTime, int endTime, int newX, int newY);

  void addScale(String shapeName, int startTime, int endTime, double scaleFactor);

  void addResize(String shapeName, int startTime, int endTime, int newHeight, int newWidth);

  void addChangeColor(String shapeName, Color newColor, int startTime, int endTime);

  void addAppearance(String shapeName, int startTime, int endTime);

  void addChangeTransparency(String shapeName, int startTime, int endTime, double transparency);
}
