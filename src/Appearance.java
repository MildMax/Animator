/**
 * The Appearance class holds the values at which an object appears on screen and when
 * the object disappears.
 */

public class Appearance extends AbstractTransformation {

  /**
   * The AbstractTransformation constructor takes two ints indicating when the shape appears
   * and when the shape disappears. Throws IllegalArgumentException if start
   * is less than 0 or if end is before start.
   *
   * @param startTime takes an int indicating the time the shape appears
   * @param endTime   takes an int indicating the time the shape disappears
   * @throws IllegalArgumentException if the start time is less than 0.
   *                                  If the end time is before the start time.
   */
  public Appearance(int startTime, int endTime) throws IllegalArgumentException {
    super(startTime, endTime);
  }

  @Override
  public Transformation copy() {
    return new Appearance(this.getStart(), this.getEnd());
  }

  @Override
  public String toString() {
    return "appears at time t=" + getStart()
            + " and disappears at time t=" + getEnd() + ".";
  }
}
