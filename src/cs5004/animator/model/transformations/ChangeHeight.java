package cs5004.animator.model.transformations;

/**
 * The Resize class handles resizing a shape according to the Shape's new height.
 * Extends the AbstractTransformation class which implements the Transformation interface.
 * Contains the following features:
 *
 * <p>ChangeHeight() constructor</p>
 *
 * <p>toString() method</p>
 */
public class ChangeHeight extends AbstractTransformation {

  private final int newHeight;

  /**
   * The Resize constructor takes two ints indicating the start of the transformation
   * animation and the end of the transformation animation and an int indicating the
   * new height of the shape. Throws IllegalArgumentException if start
   * is less than 0 or if end is before start. Throws IllegalArgumentException if the
   * new height is non-positive.
   *
   * @param startTime takes an int indicating the start time of the transformation.
   * @param endTime   takes an int indicating the end time of the transformation
   * @param newHeight takes the new height value of the shape.
   * @throws IllegalArgumentException if the start time is less than 0.
   *                                  If the end time is before the start time.
   *                                  If the new height is non-positive
   */
  public ChangeHeight(int startTime, int endTime, int newHeight)
          throws IllegalArgumentException {
    super(startTime, endTime, TransformationType.CHANGEHEIGHT);

    if (newHeight <= 0) {
      throw new IllegalArgumentException("New height or height cannot be non-positive.");
    }

    this.newHeight = newHeight;
  }

  public int modifyHeight(int size, int tick) {
    int modWidth = newHeight - size;
    double diff = (double)(tick - getStart()) / (getEnd() - getStart());
    return (int)(size + (modWidth * diff));
  }

  /**
   * Returns a String indicating the new height of the Shape and the start and
   * end time of the ChangeHeight transformation.
   *
   * @return a String indicating the new height of the Shape and the start and
   *         end time of the ChangeHeight transformation.
   */
  @Override
  public String toString() {
    return "changes to height:" + this.newHeight + " " + super.toString();
  }
}