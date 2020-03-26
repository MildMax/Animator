package cs5004Animator;

import java.util.HashMap;
import java.util.Map;

import cs5004Animator.Shapes.Shape;
import cs5004Animator.Transformations.Transformation;
import cs5004Animator.Transformations.TransformationType;

public class AnimationModelImpl implements AnimationModel {

  private final int windowXMin;
  private final int windowXMax;
  private final int windowYMin;
  private final int windowYMax;
  private Color windowColor;

  private double speed;
  private Map<String, Shape> shapeMap;

  public AnimationModelImpl() {
    this(-500, 500, -500, 500);
  }

  public AnimationModelImpl(int windowWidth, int windowHeight) {
    this((windowWidth / 2) * -1, (windowWidth / 2),
            (windowHeight / 2) * -1, (windowHeight / 2));

    if (windowWidth <= 0 || windowHeight <= 0) {
      throw new IllegalArgumentException("Window dimensions cannot be less than or equal to zero");
    }
  }

  public AnimationModelImpl(int windowXMin, int windowXMax,
                            int windowYMin, int windowYMax) throws IllegalArgumentException {
    if (windowXMax <= windowXMin || windowYMax <= windowYMin) {
      throw new IllegalArgumentException("Invalid window size parameters.");
    }

    this.windowXMin = windowXMin;
    this.windowXMax = windowXMax;
    this.windowYMin = windowYMin;
    this.windowYMax = windowYMax;

    this.speed = 1.0;
    this.windowColor = Color.WHITE;
    this.shapeMap = new HashMap<>();
  }

  @Override
  public void addShape(Shape shape) throws IllegalArgumentException {
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    }
    else if (shapeMap.containsKey(shape.getName())) {
      throw new IllegalArgumentException("That shape already exists");
    }
    else {
      shapeMap.put(shape.getName(), shape);
    }
  }

  @Override
  public void removeShape(String shapeName) throws IllegalArgumentException {
    if (shapeName == null) {
      throw new IllegalArgumentException("String shapeName cannot be null");
    }
    else if (shapeMap.containsKey(shapeName)) {
      shapeMap.remove(shapeName);
    }
    else {
      throw new IllegalArgumentException("That shape does not exist");
    }
  }

  @Override
  public void addTransformation(String shapeName, Transformation t) throws IllegalArgumentException {
    if (shapeName == null || t == null) {
      throw new IllegalArgumentException("Values cannot be null");
    }
    Shape shape = findShape(shapeName);
    shape.addTransformation(t);
  }

  @Override
  public void removeTransformation(String shapeName, TransformationType type, int start, int end) {
    if (shapeName == null || type == null) {
      throw new IllegalArgumentException("Values cannot be null");
    }
    Shape shape = findShape(shapeName);
    shape.removeTransformation(type, start, end);
  }

  @Override
  public void setSpeed(double speed) {
    if (speed <= 0 || speed > 16) {
      throw new IllegalArgumentException("Does not support specified speed");
    }
    this.speed = speed;
  }

  @Override
  public void setBackgroundColor(Color windowColor) {
    if (windowColor == null) {
      throw new IllegalArgumentException("Window color cannot be null");
    }
    this.windowColor = windowColor;
  }

  @Override
  public String toString() {
    String out = "Create window with bottom left corner(" + this.windowXMin + "," + this.windowXMax
            + ") top right corner (" + this.windowYMin + "," + this.windowYMax
            + ") with background color " + this.windowColor.toString() + " and speed "
            + this.speed + ".\n\n";

    for (Shape shape : shapeMap.values()) {
      out += shape.toString() + "\n";
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
}
