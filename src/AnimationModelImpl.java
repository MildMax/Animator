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
  public List listOfShapeWrappers(String descriptionFromController) {

    return null;
  }

  @Override
  public String getDescription() {
    String out = "";
    for (ShapeWrapper w : listOfShapeWrapper.values()) {
      out += w.getData() + "\n\n";
    }

    return out;

  }

  @Override
  public List getAllTransformations() {
    return null;
  }

  @Override
  public void addShape(Shape shape) {
    if (listOfShapeWrapper.containsKey(shape.getName())) {
      throw new IllegalArgumentException("That shape already exists");
    }
    else {
      listOfShapeWrapper.put(shape.getName(), new ShapeWrapper(shape));
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
    wrapper.addTransformation(new Move(startTime, endTime, newX, newY));
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
    wrapper.addTransformation(new Scale(startTime, endTime, scaleFactor));
  }

  @Override
  public void addChangeWidth(String shapeName, int startTime, int endTime, int newWidth) {
    ShapeWrapper wrapper = findWrapper(shapeName);
    for (Transformation t : wrapper.listOfTransformations) {
      //if move already exists
      if (t instanceof ChangeWidth && checkTimes(t.getStart(), t.getEnd(), startTime, endTime)) {
        throw new IllegalArgumentException("Change width already exists within that period");
      }
    }
    wrapper.addTransformation(new ChangeWidth(startTime, endTime, newWidth));
  }

  @Override
  public void addChangeHeight(String shapeName, int startTime, int endTime, int newHeight) {
    ShapeWrapper wrapper = findWrapper(shapeName);
    for (Transformation t : wrapper.listOfTransformations) {
      //if move already exists
      if (t instanceof ChangeHeight && checkTimes(t.getStart(), t.getEnd(), startTime, endTime)) {
        throw new IllegalArgumentException("Resize already exists within that period");
      }
    }
    wrapper.addTransformation(new ChangeHeight(startTime, endTime, newHeight));
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
    wrapper.addTransformation(new ChangeColor(startTime, endTime, newColor));
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
    wrapper.addTransformation(new Appearance(startTime, endTime));
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
    wrapper.addTransformation(new ChangeTransparency(startTime, endTime, transparency));
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

    ShapeWrapper(Shape shape) {
      this.shape = shape;
      listOfTransformations = new ArrayList();
    }

    void addTransformation(Transformation t) {
      listOfTransformations.add(t);
    }

    String getData() {
      String ret = "";
      ret += shape.getCreateStatement() + "\n";
      for (Transformation t : listOfTransformations) {
        ret += t.getDescription(shape) + "\n";
      }
      return ret;
    }
  }
}
