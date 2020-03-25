import jdk.jfr.TransitionFrom;

/**
 * The Resize class handles resizing a shape according to the new width
 * of the shapes x value. Contains the following features:
 *
 * <p>Resize() constructor</p>
 *
 * <p>getWidth() method</p>
 */
public class ChangeWidth extends AbstractTransformation {

  private final int newWidth;

  /**
   * The Resize constructor takes two ints indicating the start of the transformation
   * animation and the end of the transformation animation and an int indicating the
   * new width of the shape. Throws IllegalArgumentException if start
   * is less than 0 or if end is before start. Throws IllegalArgumentException if the
   * new width or height is non-positive.
   *
   * @param startTime takes an int indicating the start time of the transformation.
   * @param endTime   takes an int indicating the end time of the transformation
   * @param newWidth takes the new width value of the shape.
   * @throws IllegalArgumentException if the start time is less than 0.
   *                                  If the end time is before the start time.
   *                                  If the new width or height is non-positive
   */
  public ChangeWidth(int startTime, int endTime, int newWidth)
          throws IllegalArgumentException {
    super(startTime, endTime, TransformationType.CHANGEWIDTH);

    if (newWidth <= 0) {
      throw new IllegalArgumentException("New width or height cannot be non-positive.");
    }

    this.newWidth = newWidth;
  }

  @Override
  public String toString() {
    return "changes to width:" + this.newWidth + " " + super.toString();
  }
}
