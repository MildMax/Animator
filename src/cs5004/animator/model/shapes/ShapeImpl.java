package cs5004.animator.model.shapes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import cs5004.animator.model.transformations.Transformation;

/**
 * Create a class called AbstractShape that is a generic shape. Contains methods
 * that apply to all shapes. This class implements the Shape interface.
 */
public class ShapeImpl implements Shape {
  private  String name;
  private final ShapeType type;
  private  int layer;
  private  int height;
  private  int width;
  private  int x;
  private  int y;
  private  int r;
  private  int g;
  private  int b;
  private List<Transformation> transformationList;
  private int appearTime;
  private int disappearTime;

  /**
   * New Constructor
   *
   * @param name
   */
  public ShapeImpl(String name, ShapeType type, int layer) {
    this.name = name;
    this.type = type;
    this.layer = layer;
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

    if (t.getEnd() < t.getStart()) {
      throw new IllegalArgumentException("End time of transformation cannot be before start");
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
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
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

  @Override
  public int getLayer() {
    return this.layer;
  }

  @Override
  public Shape getShapeAtTick(int tick) {
    if (tick < appearTime || tick > disappearTime) {
      throw new IllegalArgumentException("Shape does not appear at tick");
    }

    Transformation t = null;

    for (Transformation transformation : transformationList) {
      if (tick >= transformation.getStart() && tick <= transformation.getEnd()) {
        t = transformation;
        break;
      }
    }

    double diff;
    if (t.getStart() == t.getEnd()) {
      diff = 0;
    } else {
      diff = (double)(tick - t.getStart()) / (t.getEnd() - t.getStart());
    }

    //calculate pos
    this.x = t.getX1() + (int)(diff * (t.getX2() - t.getX1()));
    this.y = t.getY1() + (int)(diff * (t.getY2() - t.getY1()));

    //calculate dims
    this.width = t.getW1() + (int)(diff * (t.getW2() - t.getW1()));
    this.height = t.getH1() + (int)(diff * (t.getH2() - t.getH1()));

    //calculate color
    this.r = t.getR1() + (int)(diff * (t.getR2() - t.getR1()));
    this.g = t.getG1() + (int)(diff * (t.getG2() - t.getG1()));
    this.b = t.getB1() + (int)(diff * (t.getB2() - t.getB1()));

    return this;
  }


  @Override
  public String toString() {
    return this.type + " " + this.name + " appears at " + appearTime +
            " and disappears at " + disappearTime + ".\n";
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


