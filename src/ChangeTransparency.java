/**
 * The ChangeTransparency class holds information needed to change the transparency of
 * a shape. Contains the following features:
 *
 * <p>ChangeTransparency() coonstructor</p>
 *
 * <p>getTransparency() method</p>
 */
public class ChangeTransparency extends AbstractTransformation {

  private final double newTransparency;

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
  public ChangeTransparency(int startTime, int endTime, double newTransparency) throws IllegalArgumentException {
    super(startTime, endTime, TransformationType.CHANGETRANSPARENCY);

    if (newTransparency <= 0 || newTransparency > 100) {
      throw new IllegalArgumentException("Invalid transparency value.");
    }

    this.newTransparency = newTransparency;
  }

  @Override
  public String toString() {
    return "changes to transparency " + this.newTransparency + super.toString();
  }
}
