package old;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import cs5004.animator.model.shapes.Shape;
import cs5004.animator.model.shapes.ShapeType;
import cs5004.animator.model.transformations.Transformation;

/**
 * Create an abstract class called AbstractShape that is a generic shape. Contains methods
 * that apply to all shapes. This class implements the Shape interface.
 */
public abstract class AbstractShape implements Shape {
  private  String name;
  private  int layer;
  private  int height;
  private  int width;
  private  int centerX;
  private  int centerY;
  private  int r;
  private  int g;
  private  int b;
  private ShapeType type;
  private List<Transformation> transformationList;
  private int appearTime;
  private int disappearTime;

  /**
   * Create a new instance of AbstractShape.
   *
   * @param name is the unique name of the shape.
   * @throws IllegalArgumentException if initialHeight <= 0.
   * @throws IllegalArgumentException if initialWidth <= 0.
   * @throws IllegalArgumentException if name is null.
   * @throws IllegalArgumentException if initialColor is null.
   *
   */
  AbstractShape(String name, ShapeType type) {
    if (name == null) {
      throw new IllegalArgumentException("Shape name cannot be null");
    }
    this.name = name;
    this.type = type;
    transformationList = new ArrayList<>();
  }

  /**
   * Add a transformation to the shape's transformation list.
   *
   * @param t is the transformation to be added.
   * @throws IllegalArgumentException if transformation is null.
   */
  @Override
  public void addTransformation(Transformation t) {
    if (t == null) {
      throw new IllegalArgumentException("Transformation cannot be null");
    }

    for (Transformation transformation : transformationList) {
      if (checkTimes(transformation.getStart(), transformation.getEnd(),
              t.getStart(), t.getEnd())) {
        throw new IllegalArgumentException("Transformation already exists within that period");
      }
    }

    if (transformationList.size() == 0) {
      this.appearTime = t.getStart();
      this.disappearTime = t.getEnd();
    }
    else {
      if (t.getStart() < this.appearTime) {
        this.appearTime = t.getStart();
      }
      if (t.getEnd() > this.disappearTime) {
        this.disappearTime = t.getEnd();
      }
    }

    transformationList.add(t);
  }

  /**
   * Remove a transformation from the shape's transformation list. Throws an
   * IllegalArgumentException of the transformation does not exist or if the
   * Transformation type is null
   *
   * @param type is the type of transformation.
   * @param start is the start time of the transformation.
   * @param end is the end time of the transformation.
   * @throws IllegalArgumentException if transformation is null.
   *                                  If the transformation does not exist.
   *
   */
  @Override
  public void removeTransformation(TransformationType type, int start, int end) {
    if (type == null) {
      throw new IllegalArgumentException("Transformation type cannot be null");
    }
    for (Transformation t : transformationList) {
      if (t.getType() == type && t.getStart() == start && t.getEnd() == end) {
        transformationList.remove(t);
        return;
      }
    }
    throw new IllegalArgumentException("That transformation does not exist");
  }

  /**
   * Return the name of the shape.
   *
   * @return the name of the shape.
   */
  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getR() {
    return this.r;
  }

  @Override
  public int getG() {
    return this.g;
  }

  @Override
  public int getB() {
    return this.b;
  }

  @Override
  public int getX() {
    return this.centerX;
  }

  @Override
  public int getY() {
    return this.centerY;
  }

  @Override
  public int getStart() {
    return this.appearTime;
  }

  @Override
  public int getEnd() {
    return this.disappearTime;
  }

  /**
   * Return the type of shape.
   *
   * @return the type of shape.
   */
  @Override
  public ShapeType getType() {
    return this.type;
  }

  /**
   * Return the list of all transformations on the shape as a string.
   *
   * @return all transformations on the string.
   */
  @Override
  public String getTransformationDescription() {
    String out = "";

    transformationList.sort(Comparator.comparing(Transformation::getStart));
    for (Transformation t : transformationList) {
      out += name + " " + t.toString() + "\n";
    }
    return out;
  }

  /**
   * Return a  list of all transformations on the shape.
   *
   * @return a list of all transformations on the shape.
   */
  public List<Transformation> getTransformationList() {
    List<Transformation> tList = new ArrayList<>();
    for (Transformation t : this.transformationList) {
      tList.add(t);
    }
    return tList;
  }

  /**
   * Confirm that a set of start and end times are valid.
   *
   * @param currStart is the current start time.
   * @param currEnd is the current end time.
   * @param newStart is the new start time.
   * @param newEnd is the new end time.
   * @return true if the combination of times is valid, else false.
   */
  private boolean checkTimes(int currStart, int currEnd, int newStart, int newEnd) {
    return (newStart > currStart && newStart < currEnd)
            || (newEnd > currStart && newEnd < currEnd)
            || (currStart > newStart && currEnd < newEnd);
  }

  @Override
  public List<Transformation> getCurrentTransformations(int tick) {
    List<Transformation> current = new ArrayList<>();
    for (Transformation t : transformationList) {
      if (tick >= t.getStart() && tick <= t.getEnd()) {
        current.add(t);
      }
    }
    return current;
  }
}


