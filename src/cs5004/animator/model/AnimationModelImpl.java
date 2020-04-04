package cs5004.animator.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs5004.animator.model.shapes.Shape;
import cs5004.animator.model.shapes.ShapeImpl;
import cs5004.animator.model.shapes.ShapeType;
import cs5004.animator.model.transformations.Transformation;
import cs5004.animator.model.transformations.TransformationImpl;

import cs5004.animator.util.AnimationBuilder;

/**
 * The AnimationModelImpl class holds a series of Shapes and their transformations. Supports adding
 * and removing shapes and transformations. Allows for speed of the animation to be set manually.
 * Allows for the background color to be set manually. Implements the AnimationModel interface.
 */
public class AnimationModelImpl implements AnimationModel {
  private final int boundX;
  private final int boundY;
  private final int windowWidth;
  private final int windowHeight;
  private final int WINDOW_BUFFER = 25;

  private int ticks;
  private Map<String, Shape> shapeMap;

  /**
   * Takes two ints indicating the width and height of the window and set of x and y values
   * indicating the top left corner of the Animation window. Throws IllegalArgumentException if
   * either the specified width or height is less than or equal to 0.
   *
   * @param x takes the x value of the top left corner of the window of the animation.
   * @param y takes the y value of the top left corner of the window of the animation.
   * @param w specifies the width of the window.
   * @param h specifies the height of the window.
   * @throws IllegalArgumentException if the windowHeight or windowWidth is less than or equal to
   *                                  0.
   */
  public AnimationModelImpl(int x, int y, int w, int h) throws IllegalArgumentException {
    if (w <= 0 || h <= 0) {
      throw new IllegalArgumentException("Height and width values cannot be less than"
              + "or equal to 0.");
    }
    this.boundX = x;
    this.boundY = y;
    this.windowWidth = w;
    this.windowHeight = h;
    this.ticks = 0;
    this.shapeMap = new HashMap<>();

  }

  /**
   * Adds a shape to the AnimationModelImpl. Throws IllegalArgumentException if the shape is null or
   * if the shape with the same name already exists.
   *
   * @param shape takes the shape to be added.
   * @throws IllegalArgumentException if the shape object is null. If the shape with the same name
   *                                  already exists.
   */
  @Override
  public void addShape(Shape shape) throws IllegalArgumentException {
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    } else if (shapeMap.containsKey(shape.getName())) {
      throw new IllegalArgumentException("That shape already exists");
    } else {
      shapeMap.put(shape.getName(), shape);
    }
  }

  /**
   * Adds a transformation to a shape in the AnimationModelImpl. Throws IllegalArgumentException if
   * the String shapeName is null, if the Transformation t is null, if there is no shape associated
   * with shapeName or if there is already a transformation of the same type at the same time of the
   * Transformation t.
   *
   * @param shapeName the name of the Shape.
   * @param t         The transformation to be added.
   * @throws IllegalArgumentException if the String shapeName is null. If the Transformation t is
   *                                  null. If there is no shape associated with shapeName. If there
   *                                  is already a transformation of the same type at the same time
   *                                  of the Transformation t.
   */
  @Override
  public void addTransformation(String shapeName, Transformation t)
          throws IllegalArgumentException {
    if (shapeName == null || t == null) {
      throw new IllegalArgumentException("Values cannot be null");
    }
    Shape shape = shapeMap.get(shapeName);

    if (shape == null) {
      throw new IllegalArgumentException("Shape with name " + shapeName + " does not exist");
    }

    shape.addTransformation(t);
    if (t.getEnd() > this.ticks) {
      this.ticks = t.getEnd();
    }
  }

  /**
   * Returns a List of shapes currently held in the AnimationModelImpl.
   *
   * @return a List of shapes currently held in the AnimationModelImpl.
   */
  @Override
  public List<Shape> getShapes() {
    List<Shape> sList = new ArrayList<>();
    for (Shape shape : shapeMap.values()) {
      sList.add(shape);
    }

    return sList;
  }

  /**
   * Returns a list of transformations on shapes currently held in the AnimationModelImpl.
   *
   * @return a list of transformations on shapes currently held in the AnimationModelImpl.
   */
  @Override
  public List<Transformation> getTransformations() {
    List<Transformation> tList = new ArrayList<>();
    for (Shape shape : shapeMap.values()) {
      for (Transformation transformation : shape.getTransformationList()) {
        tList.add(transformation);
      }
    }
    tList.sort(Comparator.comparing(Transformation::getStart));
    return tList;
  }

  /**
   * Returns the total number of ticks in the animation.
   *
   * @return the total number of ticks in the animation.
   */
  @Override
  public int getTotalTicks() {
    return this.ticks;
  }

  /**
   * Returns a list of shapes with values corresponding to each shapes transformations at the frame
   * in the animation specified by parameter tick. Throws IllegalArgumentException if the specified
   * tick is less than 0.
   *
   * @param tick takes a double indicating the current frame of the animation.
   * @return a list of shapes with values corresponding to its transformations at the frame in the
   *         animation specified by parameter tick.
   * @throws IllegalArgumentException if parameter tick is less than 0 or greater than the total
   *                                  number of ticks in the animation.
   */
  @Override
  public List<Shape> getShapesAtTick(double tick) throws IllegalArgumentException {
    if (tick < 0 || tick > this.ticks) {
      throw new IllegalArgumentException("invalid tick specifier: " + tick);
    }
    List<Shape> shapeList = new ArrayList<>();
    for (Shape shape : this.shapeMap.values()) {
      Shape s = shape.getShapeAtTick(tick);
      if (s != null) {
        shapeList.add(s);
      }
    }
    return shapeList;
  }

  /**
   * Returns the height of the window.
   *
   * @return the height of the the window.
   */
  public int getWindowHeight() {
    return this.windowHeight;
  }

  /**
   * Returns the width of the window.
   *
   * @return the width of the window.
   */
  public int getWindowWidth() {
    return this.windowWidth;
  }

  /**
   * Returns the x value of the window's initial position with regard to the upper left corner of
   * the screen.
   *
   * @return the x value of the window's initial position with regard to the upper left corner of
   *         the screen.
   */
  @Override
  public int getBoundX() {
    return this.boundX;
  }

  /**
   * Returns the y value of the window's initial position with regard to the upper left corner of
   * the screen.
   *
   * @return the y value of the window's initial position with regard to the upper left corner of
   *         the screen.
   */
  @Override
  public int getBoundY() {
    return this.boundY;
  }

  /**
   * Return the maximum width of the window that displays the animation.
   *
   * @return the maximum width of the window that displays the animation.
   */
  public int getAnimationWidth() {
    List<Transformation> tList = getTransformations();

    int maxWidth = 0;

    for (Transformation t : tList) {
      int maxW = Math.max(t.getW2(), t.getW1());
      int maxX = Math.max(t.getX2(), t.getX1());
      if (maxW + maxX > maxWidth) {
        maxWidth = maxW + maxX;
      }
    }

    return maxWidth + WINDOW_BUFFER;
  }

  /**
   * Return the maximum height of the window that displays the animation.
   *
   * @return the maximum height of the window that displays the animation.
   */
  public int getAnimationHeight() {
    List<Transformation> tList = getTransformations();
    int maxHeight = 0;
    for (Transformation t : tList) {
      int maxW = Math.max(t.getH2(), t.getH1());
      int maxX = Math.max(t.getY2(), t.getY1());
      if (maxW + maxX > maxHeight) {
        maxHeight = maxW + maxX;
      }
    }
    return maxHeight + WINDOW_BUFFER;
  }

  /**
   * Returns a formatted String indicating the size of the window, the color of the window, and a
   * list of shapes and their respective instructions in chronological order.
   *
   * @return a formatted String indicating the size of the window, the color of the window, the
   *         speed of the Animator, a list of shapes and their appearance and disappearance times,
   *         and a list of transformations on each shape in chronological order.
   */
  @Override
  public String toString() {
    String out = "Create window with width " + this.windowWidth + " and height "
            + this.windowHeight + " with top left corner (" + this.boundX + "," + this.boundY
            + ") and total ticks " + this.getTotalTicks() + "\n\n";

    String createStatements = "";
    String appearStatements = "";

    for (Shape shape : shapeMap.values()) {
      createStatements += shape.getCreateStatement();
      appearStatements += shape.getAppearStatement();
    }

    out += createStatements + "\n" + appearStatements + "\n";

    for (Transformation t : getTransformations()) {
      out += t.toString();
    }
    return out.trim();
  }

  /**
   * Builds and returns an AnimationModel object with a specified height, width, initial x and y
   * positions relative to the upper left hand corner of the screen, and a list of shapes and their
   * transformations over the course of the animation.
   */
  public static final class Builder implements AnimationBuilder<AnimationModel> {
    private AnimationModel m;
    private int layer = 0;

    /**
     * Returns the instance of AnimationModel in in this builder. Throws IllegalArgumentException if
     * the model has not had its start position and width and height defined.
     *
     * @return the AnimationModel in this builder.
     * @throws IllegalArgumentException if the model has not been assigned a width and height value
     *                                  and an x and y value.
     */
    @Override
    public AnimationModel build() throws IllegalArgumentException {
      if (m == null) {
        throw new IllegalArgumentException("Must declare bounds and width"
                + "of the animation");
      }
      return this.m;
    }

    /**
     * Initializes the AnimationModel in this object with width and height values specifying the
     * width and height of the window as well as the x and y coordinates the screen will be
     * displayed at with respect to the top left corner of the screen.
     *
     * @param x      The leftmost x value
     * @param y      The topmost y value
     * @param width  The width of the bounding box
     * @param height The height of the bounding box
     * @return this instance of AnimationBuilder
     */
    @Override
    public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
      this.m = new AnimationModelImpl(x, y, width, height);
      //AnimationBuilder<AnimationModel> myBuilder = new Builder(myModel);
      //return myBuilder;
      return this;
    }

    /**
     * Adds a shape to the AnimationModel in this AnimationBuilder.
     *
     * @param name The unique name of the shape to be added. No shape with this name should already
     *             exist.
     * @param type The type of shape (e.g. "ellipse", "rectangle") to be added. The set of supported
     *             shapes is unspecified, but should include "ellipse" and "rectangle" as a
     *             minimum.
     * @return this instance of AnimationBuilder.
     */
    @Override
    public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
      ShapeImpl s = null;
      switch (type) {
        case "circle": {
          s = new ShapeImpl(name, ShapeType.CIRCLE, layer);
          break;
        }
        case "ellipse": {
          s = new ShapeImpl(name, ShapeType.ELLIPSE, layer);
          break;
        }
        case "oval": {
          s = new ShapeImpl(name, ShapeType.OVAL, layer);
          break;
        }
        case "rectangle": {
          s = new ShapeImpl(name, ShapeType.RECTANGLE, layer);
          break;
        }
        case "square": {
          s = new ShapeImpl(name, ShapeType.SQUARE, layer);
          break;
        }
        case "triangle": {
          s = new ShapeImpl(name, ShapeType.TRIANGLE, layer);
          break;
        }
        default:
          throw new IllegalArgumentException("Not a recognized type of shape.");
      }
      this.m.addShape(s);
      ++layer;
      return this;
    }

    /**
     * Adds a transformation to the shape indicated by its associated name.
     *
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t1   The start time of this transformation
     * @param x1   The initial x-position of the shape
     * @param y1   The initial y-position of the shape
     * @param w1   The initial width of the shape
     * @param h1   The initial height of the shape
     * @param r1   The initial red color-value of the shape
     * @param g1   The initial green color-value of the shape
     * @param b1   The initial blue color-value of the shape
     * @param t2   The end time of this transformation
     * @param x2   The final x-position of the shape
     * @param y2   The final y-position of the shape
     * @param w2   The final width of the shape
     * @param h2   The final height of the shape
     * @param r2   The final red color-value of the shape
     * @param g2   The final green color-value of the shape
     * @param b2   The final blue color-value of the shape
     * @return this instance of animation builder.
     */
    @Override
    public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
                                                      int h1, int r1, int g1, int b1, int t2,
                                                      int x2, int y2, int w2, int h2, int r2,
                                                      int g2, int b2) {
      Transformation t = new TransformationImpl(name, t1, x1, y1, w1, h1, r1, g1, b1, t2, x2,
              y2, w2, h2, r2, g2, b2);
      this.m.addTransformation(name, t);
      return this;
    }

  }

}
