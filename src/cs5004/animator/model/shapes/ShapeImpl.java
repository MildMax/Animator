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
   * Take a String name indicating the name of the shape, a ShapeType enum indicating the type of
   * the shape, and a layer that the shape will be displayed on. Throws IllegalArgumentException
   * if the String name is null or of the ShapeType type is null.
   *
   * @param name takes the name associated with the shape.
   * @param type takes the type of the shape.
   * @param layer takes the layer the shape will be displayed on.
   * @throws IllegalArgumentException If the String name is null.
   *                                  If the ShapeType type is null.
   */
  public ShapeImpl(String name, ShapeType type, int layer) {
    if (name == null || type == null) {
      throw new IllegalArgumentException("Name and/or type cannot be null");
    }
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
   *                                  If a transformation already exists on the shape
   *                                  in the same time period.
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
   * Return the name of the shape.
   *
   * @return the name of the shape.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Return the width of the shape.
   *
   * @return the width of the shape.
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Return the height of the shape.
   *
   * @return the height of the shape.
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Return the red color value of the shape.
   *
   * @return the red color value of the shape.
   */
  @Override
  public int getR() {
    return this.r;
  }

  /**
   * Return the green color value of the shape.
   *
   * @return the green color value of the shape.
   */
  @Override
  public int getG() {
    return this.g;
  }

  /**
   * Return the blue color value of the shape.
   *
   * @return the blue color value of the shape.
   */
  @Override
  public int getB() {
    return this.b;
  }

  /**
   * Return the center x value of the shape.
   *
   * @return the center x value of the shape.
   */
  @Override
  public int getX() {
    return this.x;
  }

  /**
   * Return the center y value of the shape.
   *
   * @return the center y value of the shape.
   */
  @Override
  public int getY() {
    return this.y;
  }

  /**
   * Return the time at which the shape appears.
   *
   * @return the time at which the shape appears.
   */
  @Override
  public int getStart() {
    return this.appearTime;
  }

  /**
   * Return the time at which the shape disappears.
   *
   * @return the time at which the shape disappears.
   */
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
   * Return the layer at which the shape will be displayed.
   *
   * @return the layer on which the shape will be displayed.
   */
  @Override
  public int getLayer() {
    return this.layer;
  }

  /**
   * Changes the internal values of the shape according to its list of
   * transformations at a given tick indicating the frame of the
   * animation and returns itself.
   *
   * @param tick the frame of the animation.
   * @return itself with modified values.
   * @throws IllegalArgumentException if the shape is not visible at the frame specified
   *                                  by the tick.
   */
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
   * Returns a String representation of the shape's name and the ticks at which it appears
   * and disappears on screen.
   *
   * @return a String representation of the shape's name and the ticks at which it appears
   *         and disappears on screen.
   */
  @Override
  public String toString() {
    return this.type + " " + this.name + " appears at " + appearTime +
            " and disappears at " + disappearTime + ".\n";
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

}


