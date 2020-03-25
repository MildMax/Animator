package cs5004Animator.Transformations;

/**
 * The Resize class handles resizing a shape according to the new height and height
 * of the shapes x and y values. Contains the following features:
 *
 * <p>Resize() constructor</p>
 *
 * <p>getheight() method</p>
 *
 * <p>getHeight() method</p>
 */
public class ChangeHeight extends AbstractTransformation {

  private final int newHeight;

  /**
   * The Resize constructor takes two ints indicating the start of the transformation
   * animation and the end of the transformation animation and two ints indicating the
   * new height and the new height of the shape. Throws IllegalArgumentException if start
   * is less than 0 or if end is before start. Throws IllegalArgumentException if the
   * new height or height is non-positive.
   *
   * @param startTime takes an int indicating the start time of the transformation.
   * @param endTime   takes an int indicating the end time of the transformation
   * @param newHeight takes the new height value of the shape.
   * @throws IllegalArgumentException if the start time is less than 0.
   *                                  If the end time is before the start time.
   *                                  If the new height or height is non-positive
   */
  public ChangeHeight(int startTime, int endTime, int newHeight)
          throws IllegalArgumentException {
    super(startTime, endTime, TransformationType.CHANGEHEIGHT);

    if (newHeight <= 0) {
      throw new IllegalArgumentException("New height or height cannot be non-positive.");
    }

    this.newHeight = newHeight;
  }

  @Override
  public String toString() {
    return "changes to height:" + this.newHeight + " " + super.toString();
  }
}