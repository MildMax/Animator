package cs5004animator.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs5004animator.model.shapes.Shape;
import cs5004animator.model.transformations.Transformation;
import cs5004animator.model.transformations.TransformationType;

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
 * <p>setBackgroundColor method()</p>
 *
 * <p>getBackgroundColor method()</p>
 *
 * <p>toString() method</p>
 */
public class AnimationModelImpl implements AnimationModel {

  private final int windowXMin;
  private final int windowXMax;
  private final int windowYMin;
  private final int windowYMax;
  private Color windowColor;

  private int ticks;
  private Map<String, Shape> shapeMap;

  /**
   * Sets the window size to have a minimum x and y values of
   * -500 and max x and y values of 500 centered around (0,0).
   * Sets the speed of the animation and the background color of the window white.
   */
  public AnimationModelImpl() {
    this(-500, 500, -500, 500);
  }

  /**
   * Takes two ints indicating the width and height of the window. Centers the window around (0,0).
   * Sets the speed of the animation to 1.0. Sets the background color to white. Throws
   * IllegalArgumentException if the window width or height is less than or equal to 0.
   *
   * @param windowWidth indicates the width of the window.
   * @param windowHeight indicates the height of the window.
   * @throws IllegalArgumentException if the window width or height is less than or equal to 0.
   */
  public AnimationModelImpl(int windowWidth, int windowHeight) throws IllegalArgumentException {
    this((windowWidth / 2) * -1, (windowWidth / 2),
            (windowHeight / 2) * -1, (windowHeight / 2));

    if (windowWidth <= 0 || windowHeight <= 0) {
      throw new IllegalArgumentException("Window dimensions cannot be less than or equal to zero");
    }
  }

  /**
   * Takes four ints indicating the minimum and maximum x and y values of the window. Sets the
   * speed of the animation to 1.0. Sets the background of the window to white. Throws
   * IllegalArgumentException if the maximum x or y values are less than or equal to their
   * respective minimum values.
   *
   * @param windowXMin indicates the minimum x value of the window.
   * @param windowXMax indicates the maximum x value of the window.
   * @param windowYMin indicates the minimum y value of the window.
   * @param windowYMax indicates the minimum y value of the window.
   * @throws IllegalArgumentException if the maximum x or y values are less than or equal to their
   *                                  respective minimum values.
   */
  public AnimationModelImpl(int windowXMin, int windowXMax,
                            int windowYMin, int windowYMax) throws IllegalArgumentException {
    if (windowXMax <= windowXMin || windowYMax <= windowYMin) {
      throw new IllegalArgumentException("Invalid window size parameters.");
    }

    this.windowXMin = windowXMin;
    this.windowXMax = windowXMax;
    this.windowYMin = windowYMin;
    this.windowYMax = windowYMax;

    this.ticks = 0;
    this.windowColor = new Color(0,0, 0);
    this.shapeMap = new HashMap<>();
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
  public void addTransformation(String shapeName, Transformation t) throws IllegalArgumentException {
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
   * Returns a list of shapes with values corresponding to its transformations at the frame
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
    if (tick < 0) {
      throw new IllegalArgumentException("tick cannot be less than 0");
    }
    /*
    This method body will be filled out when we know more about the controller
    and the view. Returns null currently so project compiles
     */
    return null;
  }

  /**
   * Returns the height of the window.
   *
   * @return the height of the the window.
   */
  public int getWindowHeight() {
    return this.windowYMax - this.windowYMin;
  }

  /**
   * Returns the width of the window.
   *
   * @return the width of the window.
   */
  public int getWindowWidth() {
    return this.windowXMax - this.windowXMin;
  }


  /**
   * Sets the background color of the window. Throws IllegalArgumentException if the windowColor
   * is null.
   *
   * @param windowColor indicates the color of the window background.
   * @throws IllegalArgumentException if the windowColor is null.
   */
  @Override
  public void setBackgroundColor(Color windowColor) throws IllegalArgumentException {
    if (windowColor == null) {
      throw new IllegalArgumentException("Window color cannot be null");
    }
    this.windowColor = windowColor;
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
    String out = "Create window with bottom left corner(" + this.windowXMin + "," + this.windowYMin
            + ") top right corner (" + this.windowXMax + "," + this.windowYMax
            + ") with background color " + this.windowColor.toString() + " and total ticks "
            + this.ticks + ".\n\n";

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
}
