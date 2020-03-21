public class ChangeColor extends AbstractTransformation {
  /**
   * The AbstractTransformation constructor takes two ints indicating the start of the transformation
   * animation and the end of the transformation animation. Throws IllegalArgumentException if start
   * is less than 0 or if end is before start.
   *
   * @param startTime takes an int indicating the start time of the transformation.
   * @param endTime   takes an int indicating the end time of the transformation
   * @throws IllegalArgumentException if the start time is less than 0. If the end time is before the
   *                                  start time.
   */
  public ChangeColor(int startTime, int endTime) throws IllegalArgumentException {
    super(startTime, endTime);
  }
}
