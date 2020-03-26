package cs5004Animator.Transformations;

/**
 * The Scale class represents a change in scale of a Shape. Extends the AbstractTransformation
 * class which implements the Transformation interface. Offers the following features.
 *
 * <p>Scale() constructor</p>
 *
 * <p>toString() method</p>
 */
public class Scale extends AbstractTransformation {

  private final double scaleFactor;

  /**
   * The Scale constructor takes two ints that indicate the start
   * of the transformation animation and the end of the transformation animation and
   * a double that indicates the amount of scaling that will be applied to the shape.
   * The double value indicating scale will treat anything <1 as decreasing the size and
   * anything >1 as increasing the size. To make half sizes, pass 0.5, to double size,
   * pass 2.0. Takes  Throws IllegalArgumentException if start is less than 0 or if end
   * is before start or if the scale value is less than or equal to 0.
   *
   * @param startTime takes an int indicating the start time of the transformation.
   * @param endTime   takes an int indicating the end time of the transformation
   * @param scaleFactor indicates the amount of scaling that will be applied to the shape.
   * @throws IllegalArgumentException if the start time is less than 0.
   *                                  If the end time is before the start time.
   *                                  If the scale value is less than or equal to 0.
   */
  public Scale(int startTime, int endTime, double scaleFactor) throws IllegalArgumentException {
    super(startTime, endTime, TransformationType.SCALE);

    if (scaleFactor <= 0) {
      throw new IllegalArgumentException("Scale value cannot be less than or equal to 0");
    }

    this.scaleFactor = scaleFactor;
  }

  /**
   * Returns a String indicating the coefficient by which the size of a Shape will be scaled
   * and the start and end time of the Scale transformation.
   *
   * @return a String indicating the coefficient by which the size of a Shape will be scaled
   *         and the start and end time of the Scale transformation.
   */
  @Override
  public String toString() {
    return "scales to " + scaleFactor + " times its current size "  + super.toString();
  }

}