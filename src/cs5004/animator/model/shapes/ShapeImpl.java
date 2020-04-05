package cs5004.animator.model.shapes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import cs5004.animator.model.transformations.Transformation;

/**
 * Create a class ShapeImpl that specifies a shape type and holds values common to all
 * simple shapes. This class implements the Shape interface.
 */
public class ShapeImpl implements Shape {
  private  String name;
  private final ShapeType type;
  private int layer;
  private int height;
  private int width;
  private int x;
  private int y;
  private int r;
  private int g;
  private int b;
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
  public ShapeImpl(String name, ShapeType type, int layer) throws IllegalArgumentException {
    if (name == null || type == null) {
      throw new IllegalArgumentException("Name and/or type cannot be null");
    }
    this.name = name;
    this.type = type;
    this.layer = layer;
    transformationList = new ArrayList<>();
  }

  /**
   * Add a transformation to the shape's transformation list. If the transformation
   * starts before the appear time of the Shape or after the disappear time of the Shape,
   * resets the appear or disappear time of the shape respectively.
   *
   * @param t is the transformation to be added.
   * @throws IllegalArgumentException if transformation is null.
   *                                  If a transformation already exists on the shape
   *                                  in the same time period.
   */
  @Override
  public void addTransformation(Transformation t) throws IllegalArgumentException {
    if (t == null) {
      throw new IllegalArgumentException("Transformation cannot be null");
    }

    for (Transformation transformation : transformationList) {
      if (!checkTimes(transformation.getStart(), transformation.getEnd(),
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
   * Creates a new shape object of the same type as the current shape with values modified
   * according to a list of transformations at a given tick indicating the frame of the
   * animation and returns the new Shape. Returns null if the shape doesn't appear
   * or doesn't have any transformations yet.
   *
   * @param tick the frame of the animation.
   * @return itself with modified values or null if shape doesn't appear at tick
   *         or doesn't have any transformations.
   */
  @Override
  public Shape getShapeAtTick(double tick) {
    if (tick < appearTime || tick > disappearTime) {
      return null;
    }

    Transformation t = null;

    for (Transformation transformation : transformationList) {
      if (tick >= transformation.getStart() && tick <= transformation.getEnd()) {
        t = transformation;
        break;
      }
    }

    if (t == null) {
      return null;
    }

    double diff;
    if (t.getStart() == t.getEnd()) {
      diff = 0;
    } else {
      diff = (tick - (double)t.getStart()) / ((double)t.getEnd() - (double)t.getStart());
    }

    int newX = t.getX1() + (int)(diff * (t.getX2() - t.getX1()));
    int newY = t.getY1() + (int)(diff * (t.getY2() - t.getY1()));

    int newWidth = t.getW1() + (int)(diff * (t.getW2() - t.getW1()));
    int newHeight = t.getH1() + (int)(diff * (t.getH2() - t.getH1()));

    int newR = t.getR1() + (int)(diff * (t.getR2() - t.getR1()));
    int newG = t.getG1() + (int)(diff * (t.getG2() - t.getG1()));
    int newB = t.getB1() + (int)(diff * (t.getB2() - t.getB1()));

    ShapeImpl s =  new ShapeImpl(this.name, this.type, this.layer);
    s.x = newX;
    s.y = newY;

    s.width = newWidth;
    s.height = newHeight;

    s.r = newR;
    s.g = newG;
    s.b = newB;

    s.appearTime = this.appearTime;
    s.disappearTime = this.disappearTime;

    s.transformationList = this.getTransformationList();

    return s;
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
   * Creates a String representing the creation of the shape. Returns an empty
   * string if there are no transformations on the shape.
   *
   * @return a String representing the creation of the shape.
   */
  @Override
  public String getCreateStatement() {
    if (transformationList.size() == 0) {
      return "";
    }
    getShapeAtTick(getStart());
    return "Create " + this.type.toString() + " " + this.name + " with center at ("
            + this.x + "," + this.y + ") with width " + this.width + " and height "
            + this.height + " and color (" + this.r + "," + this.g + "," + this.b + ")\n";
  }

  /**
   * Returns a String representation of the shape's name and the ticks at which it appears
   * and disappears on screen. Return an empty String if Shape has no transformations.
   *
   * @return a String representation of the shape's name and the ticks at which it appears
   *         and disappears on screen or an empty String if Shape has not transformations.
   */
  @Override
  public String getAppearStatement() {
    if (transformationList.size() == 0) {
      return "";
    }
    else {
      return this.type.toString() + " " + this.name + " appears at time t=" + appearTime
              + " and disappears at time t=" + disappearTime + "\n";
    }
  }

  /**
   * Confirm that a set of start and end times are valid and returns true if the timestamps
   * provided are outside the bounds of the current timestamps.
   *
   * @param currStart is the current start time.
   * @param currEnd is the current end time.
   * @param newStart is the new start time.
   * @param newEnd is the new end time.
   * @return true if the combination of times is valid, else false.
   */
  private boolean checkTimes(int currStart, int currEnd, int newStart, int newEnd) {
    return (newStart <= currStart && newEnd <= currStart)
            || (newStart >= currEnd && newEnd >= currEnd);
  }
}


