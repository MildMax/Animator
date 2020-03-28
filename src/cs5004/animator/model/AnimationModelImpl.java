package cs5004.animator.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs5004.animator.model.shapes.*;
import cs5004.animator.model.transformations.*;
import cs5004.animator.model.*;

import cs5004.animator.model.shapes.Shape;
import cs5004.animator.model.shapes.AbstractShape;
import cs5004.animator.model.transformations.Transformation;
import cs5004.animator.model.transformations.TransformationType;

import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.model.Color;

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
  private final Color windowColor;
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
    this.windowColor = new Color(0,0,0);
    this.shapeMap = new HashMap<String, Shape>();

  }
//          throws IllegalArgumentException {
//    if (windowWidth <= 0 || windowHeight <= 0) {
//      throw new IllegalArgumentException("Window dimensions cannot be less than or equal to zero");
//    }
//    this.boundX = boundX;
//    this.boundY = boundY;
//    this.windowWidth = windowWidth;
//    this.windowHeight = windowHeight;
//    this.shapeMap = new HashMap<>();
//    this.ticks = 0;
//    this.windowColor = new Color(0,0,0);






  // BEGIN ORIGINAL CONSTRUCTORS
  // BEGIN ORIGINAL CONSTRUCTORS
  // BEGIN ORIGINAL CONSTRUCTORS

  /**
   * Sets the window size to have a width and height of 500.
   * Sets the window background color to 0.0,0.0,0.0 (white).
   */
//  public AnimationModelImpl() {
//    this(500, 500);
//  }

  /**
   * Takes two ints indicating the width and height of the window.
   * Sets the background color to 0.0,0.0,0.0 (white). Throws
   * IllegalArgumentException if the window width or height is less than or equal to 0.
   *
   * @param windowWidth indicates the width of the window.
   * @param windowHeight indicates the height of the window.
   * @throws IllegalArgumentException if the window width or height is less than or equal to 0.
   */
//  public AnimationModelImpl(int windowWidth, int windowHeight) throws IllegalArgumentException {
//    this(windowWidth, windowHeight, new Color(0,0,0));
//  }

  /**
   * Takes a Color that specifies the background color of the window. Sets the window height
   * and width to be 500. Throws IllegalArgumentException if the specified color value is null.
   *
   * @param windowColor takes the background color of the window.
   * @throws IllegalArgumentException if the specified color value is null.
   */
//  public AnimationModelImpl(Color windowColor) throws IllegalArgumentException {
//    this(500, 500, windowColor);
//  }

  /**
   * Takes two ints indicating the width and height of the window and a color specifying the
   * background color of the window. Throws IllegalArgumentException if either the specified
   * width or height is less than or equal to 0 or if the Color value is null.
   *
   * @param windowWidth specifies the width of the window.
   * @param windowHeight specifies the height of the window.
   * @param windowColor specifies the background color of the window.
   * @throws IllegalArgumentException if the windowHeight or windowWidth is less than or equal
   *                                  to 0.
   *                                  If the windowColor us null.
   */
//  public AnimationModelImpl(int windowWidth, int windowHeight, Color windowColor)
//          throws IllegalArgumentException {
//    if (windowWidth <= 0 || windowHeight <= 0) {
//      throw new IllegalArgumentException("Window dimensions cannot be less than or equal to zero");
//    }
//    else if (windowColor == null) {
//      throw new IllegalArgumentException("Window color cannot be null");
//    }
//    this.windowWidth = windowWidth;
//    this.windowHeight = windowHeight;
//    this.windowColor = windowColor;
//    this.shapeMap = new HashMap<>();
//    this.ticks = 0;
//  }

  /**
   * Adds a shape to the AnimationModelImpl. Throws IllegalArgumentException if the shape is null
   * or if the shape with the same name already exists.
   *
   * @param shape takes the shape to be added.
   * @param name takes the name of the shape to be added.
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
   * Removes a shape from the AnimationModelImpl. Throws IllegalArgumentException if the String
   * shapeName is null or if there is no shape associated with the shapeName.
   *
   * @param shapeName takes a String that corresponds to the name of an existing Shape.
   * @throws IllegalArgumentException if the String shapeName is null.
   *                                  If there is no shape associated with the shapeName.
   */
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
   * Removes a transformation from a shape in AnimationModelImpl. Throws IllegalArgumentException
   * if the String shapeName is null, if TransformationType is null, if there is no shape
   * associated with shapeName, or if the transformation does not exist.
   *
   * @param shapeName indicates the name of the Shape the transformation will be added to.
   * @param type indicates the type of transformation being removed.
   * @param start indicates the start time of the transformation being removed.
   * @param end indicates the end time of the transformation being removed.
   * @throws IllegalArgumentException if the String shapeName is null.
   *                                  If the TransformationType is null.
   *                                  If there is no shape associated with shapeName.
   *                                  If the transformation does not exist.
   */
  @Override
  public void removeTransformation(String shapeName, TransformationType type, int start, int end)
          throws IllegalArgumentException {
    if (shapeName == null || type == null) {
      throw new IllegalArgumentException("Values cannot be null");
    }
    Shape shape = findShape(shapeName);
    shape.removeTransformation(type, start, end);
    if (end == this.ticks) {
      this.ticks = findTotalTicks();
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
    if (tick < 0 || tick > this.ticks) {
      throw new IllegalArgumentException("tick cannot be less than 0 or greater than total"
              + " number of ticks");
    }
    List<Shape> modShapes = new ArrayList<>();
    for (Shape s : shapeMap.values()) {
      Shape mod = s.makeModifiedShape(tick);
      if (mod != null) {
        modShapes.add(mod);
      }
    }
    return modShapes;
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
   * Returns the background color of the window.
   *
   * @return the background color of the window.
   */
  @Override
  public Color getBackgroundColor() {
    return  this.windowColor;
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
            + this.windowHeight + " with background color " + this.windowColor.toString()
            + " and total ticks " + this.getTotalTicks() + ".\n\n";

    for (Shape shape : shapeMap.values()) {
      out += shape.toString() + "\n";
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

  private int findTotalTicks() {
    List<Transformation> tList = getTransformations();

    if (tList.size() == 0) {
      return 0;
    }
    else {
      tList.sort(Comparator.comparing(Transformation::getEnd));
      return tList.get(tList.size() - 1).getEnd();
    }
  }

  /**
   * return a copy of the model;
   * @return
   */
  private AnimationModel copy() {
    return this;
  }

  /**
   *  Implement Builder.
   */
  public static final class Builder implements AnimationBuilder<AnimationModel> {
//    private int boundX;
//    private int boundY;
//    private int windowWidth;
//    private int windowHeight;
//    private Color windowColor;
//    private int ticks;
//    private Map<String, Shape> shapeMap;
    private AnimationModel m;


    public Builder(AnimationModel m) {
      this.m = m;
    }
//    public Builder(x,y,width,height,s) {
//      AnimationModel myModel = new AnimationModelImpl();
//      this.boundX = 0;
//      this.boundY = 0;
//      this.windowWidth = 0;
//      this.windowHeight = 0;
//      this.windowColor = new Color(0,0,0);
//      this.ticks = 999;
//      this.shapeMap = new HashMap<String, Shape>();
//    }

    @Override
    public AnimationModel build() {
      return this.m;
    }

    @Override
    public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
      AnimationModel myModel  = new AnimationModelImpl(x,y,width,height);
      AnimationBuilder<AnimationModel> myBuilder = new Builder(myModel);
      return myBuilder;
    }

    @Override
    public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
      AbstractShape s = null;
      switch (type) {
        case "circle": {
          s = new Circle(name);
          break;
        }
        case "ellipse": {
          s = new Ellipse(name);
          break;
        }
        case "oval": {
          s = new Oval(name);
          break;
        }
        case "rectangle": {
          s = new Rectangle(name);
          break;
        }
        case "square": {
          s = new Square(name);
          break;
        }
        case "triangle": {
          s = new Triangle(name);
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
      AbstractTransformation t = null;
      if (x1 != x2 || y1 != y2) {
	      t = new Move(t1, t2, int newX, int newY);
      }
      if (h1 != h2) {
        t = new ChangeHeight();
      }
      if (w1 != w2) {
        t = new ChangeWidth();
      }
      if (r1 != r2 || g1 != g2 || b1 != b2) {
        t = new ChangeColor();
      }
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
