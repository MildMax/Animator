import java.util.List;

public interface AnimationModel {

  String getDescription();

  void addShape(Shape shape);

  void addTransformation(String shapeName, Transformation t);

  void addMove(String shapeName, int startTime, int endTime, int newX, int newY);

  void addScale(String shapeName, int startTime, int endTime, double scaleFactor);

  void addChangeWidth(String shapeName, int startTime, int endTime, int newWidth);

  void addChangeHeight(String shapeName, int startTime, int endTime, int newHeight);

  void addChangeColor(String shapeName, Color newColor, int startTime, int endTime);

  void addAppearance(String shapeName, int startTime, int endTime);

  void addChangeTransparency(String shapeName, int startTime, int endTime, double transparency);

}
