import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimationModelImpl implements AnimationModel {

  private final int windowXMin;
  private final int windowXMax;
  private final int windowYMin;
  private final int windowYMax;
  private final Color windowColor;

  private double speed;
  private Map<String, Shape> shapeMap;

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
    this.shapeMap = new HashMap<>();
    this.windowXMin = windowXMin;
    this.windowXMax = windowXMax;
    this.windowYMin = windowYMin;
    this.windowYMax = windowYMax;
    this.speed = 1.0;
  }

  @Override
  public void addShape(Shape shape, String shapeName) {
    if (shapeMap.containsKey(shapeName)) {
      throw new IllegalArgumentException("That shape already exists");
    }
    else {
      shapeMap.put(shapeName, shape);
    }
  }

  @Override
  public void removeShape(String shapeName) {
    if (shapeMap.containsKey(shapeName)) {
      shapeMap.remove(shapeName);
    }
    else {
      throw new IllegalArgumentException("That shape does not exist");
    }
  }

  @Override
  public void addTransformation(String shapeName, Transformation t) {
    Shape shape = findShape(shapeName);
    for (Transformation transformation : shape.getTransformationList()) {
      if (t.getType() == transformation.getType()
              && checkTimes(transformation.getStart(), transformation.getEnd(),
              t.getStart(), t.getEnd())) {
        throw new IllegalArgumentException("Move already exists within that period");
      }
    }
    shape.addTransformation(t);
  }

  @Override
  public void setSpeed(double speed) {
    if (speed <= 0 || speed > 16) {
      throw new IllegalArgumentException("Does not support specified speed");
    }
    this.speed = speed;
  }

  @Override
  public void removeTransformation(String shapeName, TransformationType type, int start, int end) {
    Shape shape = findShape(shapeName);
    for (Transformation t : shape.getTransformationList()) {
      if (t.getType() == type && t.getStart() == start && t.getEnd() == end) {
        shape.removeTransformation(t);
        return;
      }
    }
    throw new IllegalArgumentException("That transformation does not exist");
  }

  @Override
  public String toString() {
    String out = "";
    for (Shape shape : shapeMap.values()) {
      out += shape.toString() + "\n\n";
    }
    return out.trim();
  }

  private Shape findShape(String shapeName) {
    Shape shape = shapeMap.get(shapeName);

    if (shape == null) {
      throw new IllegalArgumentException("Shape with name " + shapeName + " does not exist");
    }

    return shape;
  }

  private boolean checkTimes(int currStart, int currEnd, int newStart, int newEnd) {
    return (newStart >= currStart && newStart <= currEnd)
            || (newEnd >= currStart && newEnd <= currEnd);
  }
}
