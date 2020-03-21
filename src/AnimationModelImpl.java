import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AnimationModelImpl implements AnimationModel {

  private final int windowXMin;
  private final int windowXMax;
  private final int windowYMin;
  private final int windowYMax;
  Color windowColor;
  List<ShapeWrapper> listOfShapeWrapper;

  public AnimationModelImpl() {
    this(Color.WHITE, -500, 500, -500, 500);
  }

  public AnimationModelImpl(Color windowColor) {
    this(windowColor, -500, 500, -500, 500);
  }

  public AnimationModelImpl(int windowXMin, int windowXMax, int windowYMin, int windowYMax) {
    this(Color.WHITE, windowXMin, windowXMax, windowYMin, windowYMax);
  }

  public AnimationModelImpl(Color windowColor, int windowXMin, int windowXMax,
                            int windowYMin, int windowYMax) throws IllegalArgumentException {

    if (windowXMax <= windowXMin || windowYMax <= windowYMin) {
      throw new IllegalArgumentException("Invalid window size parameters.");
    }
    else if (windowColor == null) {
      throw new IllegalArgumentException("Window color cannot be null.");
    }

    this.windowColor = windowColor;
    listOfShapeWrapper = new ArrayList<>();
    this.windowXMin = windowXMin;
    this.windowXMax = windowXMax;
    this.windowYMin = windowYMin;
    this.windowYMax = windowYMax;
  }

  @Override
  public List listOfShapeWrappers(String descriptionFromController) {
    List<ShapeWrapper> listOfShapeWrapperCopy = new ArrayList<>();
    for (ShapeWrapper s : listOfShapeWrapper) {

    }

    return listOfShapeWrapperCopy;
  }

  @Override
  public String getDescription() {
    return null;
  }

  @Override
  public List getAllTransformations() {
    return null;
  }

  @Override
  public void addShape(Shape shape, String shapeName) {
  }

  @Override
  public void addMove(String shapeName, int startTime, int endTime, int newX, int newY) {
    ShapeWrapper wrapper = findWrapper(shapeName);
    for (Transformation t : wrapper.listOfTransformations) {
      //if move already exists
      if (t instanceof Move) {
        //if start is during another move throw illegal argument
        if (startTime >= t.getStart() && startTime <= t.getEnd()) {
          throw new IllegalArgumentException();
        }
        //if end is during another move throw illegal argument
        else if (endTime >= t.getStart() && endTime <= t.getEnd()) {
          throw new IllegalArgumentException();
        }
      }
    }
  }

  @Override
  public void addScale(String shapeName, int startTime, int endTime, double scaleFactor) {

  }

  @Override
  public void addResize(String shapeName, int startTime, int endTime, int newHeight, int newWidth) {

  }

  @Override
  public void addChangeColor(String shapeName, Color newColor, int startTime, int endTime) {

  }

  @Override
  public void addAppearance(String shapeName, int startTime, int endTime) {

  }

  @Override
  public void addChangeTransparency(String shapeName, int startTime, int endTime, double transparency) {

  }

  private ShapeWrapper findWrapper(String shapeName) {
    for (ShapeWrapper w : listOfShapeWrapper) {
      if (w.shapeName.compareTo(shapeName) == 0) {
        return w;
      }
    }

    throw new IllegalArgumentException("Shape " + shapeName + " does not exist");
  }

  private static class ShapeWrapper {
    private Shape shape;
    private String shapeName;
    List<Transformation> listOfTransformations;

    ShapeWrapper(Shape shape, String shapeName) {
      this.shape = shape;
      this.shapeName = shapeName;
      listOfTransformations = new ArrayList();
    }
  }
}
