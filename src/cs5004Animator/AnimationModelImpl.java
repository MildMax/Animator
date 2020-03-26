package cs5004Animator;

import java.util.HashMap;
import java.util.Map;

import cs5004Animator.Shapes.Shape;
import cs5004Animator.Transformations.Transformation;
import cs5004Animator.Transformations.TransformationType;

/**
 * The AnimationModelImpl class holds a series of Shapes and their transformations. Supports
 * adding and removing shapes and transformations. Allows for speed of the animation to be set
 * manually. Allows for the background color to be set manually. Implements the
 * AnimationModel interface. Contains the following features:
 *
 * <p>AnimationMode() constructors</p>
 *
 * <p>addShape() method</p>
 *
 * <p>removeShape() method</p>
 *
 * <p>addTransformation() method</p>
 *
 * <p>removeTransformation() method</p>
 *
 * <p>setSpeed() method</p>
 *
 * <p>setBackgroundColor method()</p>
 *
 * <p>toString() method</p>
 */
public class AnimationModelImpl implements AnimationModel {

  private final int windowXMin;
  private final int windowXMax;
  private final int windowYMin;
  private final int windowYMax;
  private Color windowColor;

  private double speed;
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

    this.speed = 1.0;
    this.windowColor = Color.WHITE;
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
   * Sets the speed of the animation. Throws IllegalArgumentException if the speed is less than
   * or equal to 0 or greater than 16.
   *
   * @param speed indicates the speed the Animation will be played at.
   * @throws IllegalArgumentException if the speed is less than or equal to 0 or greater than 16.
   */
  @Override
  public void setSpeed(double speed) throws IllegalArgumentException {
    if (speed <= 0 || speed > 16) {
      throw new IllegalArgumentException("Does not support specified speed");
    }
    this.speed = speed;
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
    String out = "Create window with bottom left corner(" + this.windowXMin + "," + this.windowXMax
            + ") top right corner (" + this.windowYMin + "," + this.windowYMax
            + ") with background color " + this.windowColor.toString() + " and speed "
            + this.speed + ".\n\n";

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
