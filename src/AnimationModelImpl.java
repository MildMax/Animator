import java.util.ArrayList;
import java.util.Comparator;
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
  public String getDescription() {
    String out = "";
    for (ShapeWrapper w : listOfShapeWrapper.values()) {
      out += w.getData() + "\n\n";
    }
    return out;
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
  public void addTransformation(String shapeName, Transformation t) {
    ShapeWrapper wrapper = findWrapper(shapeName);
    for (Transformation transformation : wrapper.listOfTransformations) {
      if (t.getClass() == transformation.getClass()
              && checkTimes(transformation.getStart(), transformation.getEnd(),
              t.getStart(), t.getEnd())) {
        throw new IllegalArgumentException("Move already exists within that period");
      }
    }
    wrapper.addTransformationToList(t);
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

    void addTransformationToList(Transformation t) {
      listOfTransformations.add(t);
    }

    String getData() {
      listOfTransformations.sort(Comparator.comparing(Transformation::getStart));
      String ret = "";
      ret += shape.getCreateStatement() + "\n";
      for (Transformation t : listOfTransformations) {
        ret += t.getDescription(shape) + "\n";
      }
      return ret;
    }
  }
}
