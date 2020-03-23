import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimationModelImpl implements AnimationModel {

  private final int windowXMin;
  private final int windowXMax;
  private final int windowYMin;
  private final int windowYMax;
  private final Color windowColor;
  private Map<String, ShapeWrapper> listOfShapeWrapper;

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
    listOfShapeWrapper = new HashMap<>();
    this.windowXMin = windowXMin;
    this.windowXMax = windowXMax;
    this.windowYMin = windowYMin;
    this.windowYMax = windowYMax;
  }

  @Override
  public Map<String, ShapeWrapper> listOfShapeWrappers(String descriptionFromController) {
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
    if (listOfShapeWrapper.containsKey(shapeName)) {
      throw new IllegalArgumentException("That shape already exists");
    }
    else {
      listOfShapeWrapper.put(shapeName, new ShapeWrapper(shape, shapeName));
    }
  }

  @Override
  public void addMove(String shapeName, int startTime, int endTime, int newX, int newY) {
    ShapeWrapper wrapper = findWrapper(shapeName);
    for (Transformation t : wrapper.listOfTransformations) {
      //if move already exists
      if (t instanceof Move && checkTimes(t.getStart(), t.getEnd(), startTime, endTime)) {
        throw new IllegalArgumentException("Move already exists within that period");
      }
    }
  }

  @Override
  public void addScale(String shapeName, int startTime, int endTime, double scaleFactor) {
    ShapeWrapper wrapper = findWrapper(shapeName);
    for (Transformation t : wrapper.listOfTransformations) {
      //if move already exists
      if (t instanceof Scale && checkTimes(t.getStart(), t.getEnd(), startTime, endTime)) {
        throw new IllegalArgumentException("Scale already exists within that period");
      }
    }
  }

  @Override
  public void addResize(String shapeName, int startTime, int endTime, int newHeight, int newWidth) {
    ShapeWrapper wrapper = findWrapper(shapeName);
    for (Transformation t : wrapper.listOfTransformations) {
      //if move already exists
      if (t instanceof Resize && checkTimes(t.getStart(), t.getEnd(), startTime, endTime)) {
        throw new IllegalArgumentException("Resize already exists within that period");
      }
    }
  }

  @Override
  public void addChangeColor(String shapeName, Color newColor, int startTime, int endTime) {
    ShapeWrapper wrapper = findWrapper(shapeName);
    for (Transformation t : wrapper.listOfTransformations) {
      //if move already exists
      if (t instanceof ChangeColor && checkTimes(t.getStart(), t.getEnd(), startTime, endTime)) {
        throw new IllegalArgumentException("Color change already exists within that period");
      }
    }
  }

  @Override
  public void addAppearance(String shapeName, int startTime, int endTime) {
    ShapeWrapper wrapper = findWrapper(shapeName);
    for (Transformation t : wrapper.listOfTransformations) {
      //if move already exists
      if (t instanceof Appearance && checkTimes(t.getStart(), t.getEnd(), startTime, endTime)) {
        throw new IllegalArgumentException("Appearances already exists within that period");
      }
    }
  }

  @Override
  public void addChangeTransparency(String shapeName, int startTime, int endTime, double transparency) {
    ShapeWrapper wrapper = findWrapper(shapeName);
    for (Transformation t : wrapper.listOfTransformations) {
      //if move already exists
      if (t instanceof ChangeTransparency && checkTimes(t.getStart(), t.getEnd(), startTime, endTime)) {
        throw new IllegalArgumentException("Transparency change already exists within that period");
      }
    }
  }

  private ShapeWrapper findWrapper(String shapeName) {
    ShapeWrapper w = listOfShapeWrapper.get(shapeName);

    if (w == null) {
      throw new IllegalArgumentException("Shape with name " + shapeName + " does not exist");
    }

    return w;
  }

  private boolean checkTimes(int currStart, int currEnd, int newStart, int newEnd) {
    return (newStart >= currStart && newStart <= currEnd)
            || (newEnd >= currStart && newEnd <= currEnd);
  }

  private static class ShapeWrapper {
    private final Shape shape;
    List<Transformation> listOfTransformations;

    ShapeWrapper(Shape shape, String name) {
      this.shape = shape;
      listOfTransformations = new ArrayList();
    }

    String getData() {
      String ret = "";
      ret += shape.getCreateStatement();
      for (Transformation t : listOfTransformations) {
        ret += t.getDescription(shape);
      }
      return ret;
    }
  }
}
