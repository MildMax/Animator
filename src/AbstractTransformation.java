/**
 * The AbstractTransformation class implements the Transformation interface and defines
 * the methods that are used by all possible transformations of a shape. Contains the
 * following features:
 *
 * <p>AbstractTransformation() constructor</p>
 *
 * <p>getShapeName() method</p>
 *
 * <p>getStart() method</p>
 *
 * <p>getEnd() method</p>
 *
 * <p>getDescription() method</p>
 */
public class AbstractTransformation implements Transformation {
  private final int startTime;
  private final int endTime;

  /**
   * The AbstractTransformation constructor takes two ints indicating the start of the
   * transformation animation and the end of the transformation animation. Throws
   * IllegalArgumentException if start is less than 0 or if end is before start.
   *
   * @param startTime takes an int indicating the start time of the transformation.
   * @param endTime takes an int indicating the end time of the transformation
   * @throws IllegalArgumentException if the start time is less than 0.
   *                                  If the end time is before the start time.
   */
  public AbstractTransformation(int startTime, int endTime)
          throws IllegalArgumentException {
    if (startTime < 0) {
      throw new IllegalArgumentException("Start time cannot be less than 0");
    }
    else if (endTime < startTime) {
      throw new IllegalArgumentException("End time cannot be before start time");
    }
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * Returns the start time of the transformation.
   *
   * @return an int indicating the start time of the transformation.
   */
  @Override
  public int getStart() {
    return this.startTime;
  }

  /**
   * Returns the end time of the transformation.
   *
   * @return an int indicating the end time of the transformation.
   */
  @Override
  public int getEnd() {
    return this.endTime;
  }

  /**
   * return the transformation's description.
   *
   * @return the transformation's description.
   */
  @Override
  public String getDescription(String shapeName) {
    return " from time t=" + startTime + " to time t=" + endTime + ".";
  }

  @Override
  public String toString() {
    return " from time t=" + startTime + " to time t=" + endTime + ".";
  }
}
