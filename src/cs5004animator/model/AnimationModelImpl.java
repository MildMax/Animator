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

  private final int windowWidth;
  private final int windowHeight;
  private final Color windowColor;

  private Map<String, Shape> shapeMap;

  /**
   * Sets the window size to have a width and height of 500.
   * Sets the window background color to 0.0,0.0,0.0 (white).
   */
  public AnimationModelImpl() {
    this(500, 500);
  }

  /**
   * Takes two ints indicating the width and height of the window.
   * Sets the background color to 0.0,0.0,0.0 (white). Throws
   * IllegalArgumentException if the window width or height is less than or equal to 0.
   *
   * @param windowWidth indicates the width of the window.
   * @param windowHeight indicates the height of the window.
   * @throws IllegalArgumentException if the window width or height is less than or equal to 0.
   */
  public AnimationModelImpl(int windowWidth, int windowHeight) throws IllegalArgumentException {
    this(windowWidth, windowHeight, new Color(0,0,0));
  }

  /**
   * Takes a Color that specifies the background color of the window. Sets the window height
   * and width to be 500. Throws IllegalArgumentException if the specified color value is null.
   *
   * @param windowColor takes the background color of the window.
   * @throws IllegalArgumentException if the specified color value is null.
   */
  public AnimationModelImpl(Color windowColor) throws IllegalArgumentException {
    this(500, 500, windowColor);
  }

  /**
   * Takes two ints indicating the width and height of the window and a color specifying the
   * background color of the window. Throws IllegalArgumentException if either the specified
   * width or height is less than or equal to 0 or if the Color value is null.
   *
   * @param windowWidth specifies the width of the window.
   * @param windowHeight specifies the height of the window.
   * @param windowColor specifies the background color of the window.
   * @throws IllegalArgumentException
   */
  public AnimationModelImpl(int windowWidth, int windowHeight, Color windowColor)
          throws IllegalArgumentException {
    if (windowWidth <= 0 || windowHeight <= 0) {
      throw new IllegalArgumentException("Window dimensions cannot be less than or equal to zero");
    }
    else if (windowColor == null) {
      throw new IllegalArgumentException("Window color cannot be null");
    }
    this.windowWidth = windowWidth;
    this.windowHeight = windowHeight;
    this.windowColor = windowColor;
    shapeMap = new HashMap<>();
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
  public void addTransformation(String shapeName, Transformation t)
          throws IllegalArgumentException {
    if (shapeName == null || t == null) {
      throw new IllegalArgumentException("Values cannot be null");
    }
    Shape shape = findShape(shapeName);
    shape.addTransformation(t);
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
    and the view. Returns null currently so project compiles.
     */
    return null;
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
}
