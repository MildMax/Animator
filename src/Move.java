/**
 * The Move class describes a transformation that moves a shape from its current location
 * to another location on screen. Contains the following methods:
 *
 * <p>Move() constructor</p>
 *
 * <p>getX() method</p>
 *
 * <p>getY() method</p>
 */
public class Move extends AbstractTransformation {

  private int newX;
  private int newY;

  /**
   * The Move constructor takes two ints indicating the start time and the end time of the
   * move animation and two ints indicating the position the shape is moving to. Throws
   * IllegalArgumentException in AbstractTransformation if start time is less than 0 or if
   * the end time is before the start time.
   *
   * @param startTime indicates the time the transformation starts.
   * @param endTime indicates the time the transformation ends.
   * @param newX the x position the shape moves to.
   * @param newY the y position the shape moves to.
   * @throws IllegalArgumentException if start time is less than 0.
   *                                  if end time is less than the start time.
   */
  public Move(int startTime, int endTime, int newX, int newY) throws IllegalArgumentException {
    super(startTime, endTime);

    this.newX = newX;
    this.newY = newY;
  }

  @Override
  public String toString() {
    return "moves to (" + newX + "," + newY + ")" + super.toString();
  }
}
