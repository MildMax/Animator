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
 * The AnimationModelImpl class holds a series of Shapes and their transformations. Supports
 * adding and removing shapes and transformations. Allows for speed of the animation to be set
 * manually. Allows for the background color to be set manually. Implements the
 * AnimationModel interface. Contains the following features:
 *
 * <p>AnimationModel() constructors</p>
 *
 * <p>addShape() method</p>
 *
 * <p>removeShape() method</p>
 *
 * <p>addTransformation() method</p>
 *
 * <p>removeTransformation() method</p>
 *
 * <p>getShapes() method</p>
 *
 * <p>getTransformations() method</p>
 *
 * <p>getTotalTicks() method</p>
 *
 * <p>getShapesAtTick() method</p>
 *
 * <p>getWindowHeight() method</p>
 *
 * <p>getWindowWidth() method</p>
 *
 * <p>getBackgroundColor method()</p>
 *
 * <p>toString() method</p>
 */
public class AnimationModelImpl implements AnimationModel {
  private final int boundX;
  private final int boundY;
  private final int windowWidth;
  private final int windowHeight;

  private int ticks;
  private Map<String, Shape> shapeMap;

  /**
   * Takes two ints indicating the width and height of the window and a color specifying the
   * background color of the window. Throws IllegalArgumentException if either the specified
   * width or height is less than or equal to 0 or if the Color value is null.
   *
   * @param w specifies the width of the window.
   * @param h specifies the height of the window.
   * @throws IllegalArgumentException if the windowHeight or windowWidth is less than or equal
   *                                  to 0.
   *                                  If the windowColor us null.
   */
  public AnimationModelImpl(int x, int y, int w, int h) {
    this.boundX = x;
    this.boundY = y;
    this.windowWidth = w;
    this.windowHeight = h;
    this.ticks = 0;
    this.shapeMap = new HashMap<String, Shape>();

  }

  /**
   * Adds a shape to the AnimationModelImpl. Throws IllegalArgumentException if the shape is null
   * or if the shape with the same name already exists.
   *
   * @param shape takes the shape to be added.
   * @throws IllegalArgumentException if the shape object is null.
   *                                  If the shape with the same name already exists.
   */
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

  /**
   * Adds a transformation to a shape in the AnimationModelImpl. Throws IllegalArgumentException
   * if the String shapeName is null, if the Transformation t is null, if there is no shape
   * associated with shapeName or if there is already a transformation of the same type
   * at the same time of the Transformation t.
   *
   * @param shapeName the name of the Shape.
   * @param t The transformation to be added.
   * @throws IllegalArgumentException if the String shapeName is null.
   *                                  If the Transformation t is null.
   *                                  If there is no shape associated with shapeName.
   *                                  If there is already a transformation of the same type at the
   *                                  same time of the Transformation t.
   */
  @Override
  public void addTransformation(String shapeName, Transformation t)
          throws IllegalArgumentException {
    if (shapeName == null || t == null) {
      throw new IllegalArgumentException("Values cannot be null");
    }
    Shape shape = findShape(shapeName);
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
   * in the animation specified by parameter tick. Throws IllegalArgumentException if the
   * specified tick is less than 0.
   *
   * @param tick takes an int indicating the current frame of the animation.
   * @return a list of shapes with values corresponding to its transformations at the frame
   *         in the animation specified by parameter tick.
   * @throws IllegalArgumentException if parameter tick is less than 0.
   */
  @Override
  public List<Shape> getShapesAtTick(int tick) throws IllegalArgumentException {
    List<Shape> shapeList = new ArrayList<>();
    for (Shape shape : this.shapeMap.values()) {
      try {
        shapeList.add(shape.getShapeAtTick(tick));
      } catch (IllegalArgumentException e) {
        System.out.println("Shape does not exist yet");
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

  @Override
  public int getBoundX() {
    return this.boundX;
  }

  @Override
  public int getBoundY() {
    return this.boundY;
  }

  /**
   * Returns a formatted String indicating the size of the window, the color of the window, the
   * speed of the Animator, and a list of shapes and their respective instructions in
   * chronological order.
   *
   * @return a formatted String indicating the size of the window, the color of the window, the
   *         speed of the Animator, and a list of shapes and their respective instructions in
   *         chronological order.
   */
  @Override
  public String toString() {
    String out = "Create window with width " + this.windowWidth + " and height "
            + this.windowHeight + " with top left corner (" + this.boundX + "," + this.boundY
            + ") and total ticks " + this.getTotalTicks() + ".\n\n";

    for (Shape shape : shapeMap.values()) {
      out += shape.toString();
    }

    out += "\n";

    for (Transformation t : getTransformations()) {
      out += t.toString();
    }
    return out.trim();
  }

  //finds a shape according to String shapeName
  //throws IllegalArgumentException if there is not shape associated with shapeName.
  private Shape findShape(String shapeName) {
    Shape shape = shapeMap.get(shapeName);

    if (shape == null) {
      throw new IllegalArgumentException("Shape with name " + shapeName + " does not exist");
    }

    return shape;
  }

  /**
   *  Implement Builder.
   */
  public static final class Builder implements AnimationBuilder<AnimationModel> {
    private AnimationModel m;

    @Override
    public AnimationModel build() {
      return this.m;
    }

    @Override
    public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
      this.m  = new AnimationModelImpl(x,y,width,height);
      //AnimationBuilder<AnimationModel> myBuilder = new Builder(myModel);
      //return myBuilder;
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
      ShapeImpl s = null;
      switch (type) {
        case "circle": {
          s = new ShapeImpl(name, ShapeType.CIRCLE);
          break;
        }
        case "ellipse": {
          s = new ShapeImpl(name, ShapeType.ELLIPSE);
          break;
        }
        case "oval": {
          s = new ShapeImpl(name, ShapeType.OVAL);
          break;
        }
        case "rectangle": {
          s = new ShapeImpl(name, ShapeType.RECTANGLE);
          break;
        }
        case "square": {
          s = new ShapeImpl(name, ShapeType.SQUARE);
          break;
        }
        case "triangle": {
          s = new ShapeImpl(name, ShapeType.TRIANGLE);
          break;
        }
        default:
          throw new IllegalArgumentException("Not a recognized type of shape.");
      }
      this.m.addShape(s);
      return this;
    }

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

    @Override
    public AnimationBuilder<AnimationModel> addKeyframe(String name, int t, int x, int y, int w, int h, int r, int g, int b) {
      return null;
    }
    // FILL IN HERE
  }

}
