public class Scale extends AbstractTransformation {

  private final double scaleFactor;

  /**
   * The Scale constructor takes two ints that indicate the start
   * of the transformation animation and the end of the transformation animation and
   * a double that indicates the amount of scaling that will be applied to the shape.
   * The double value indicating scale will treat anything <1 as decreasing the size and
   * anything >1 as increasing the size. To make half sizes, pass 0.5, to doulbe size,
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
    super(startTime, endTime);

    if (scaleFactor <= 0) {
      throw new IllegalArgumentException("Scale value cannot be less than or equal to 0");
    }

    this.scaleFactor = scaleFactor;
    this.type = TransformationType.SCALE;
  }

  @Override
  public String toString() {
    return "scales to " + scaleFactor + " times its current size"  + super.toString();
  }
}
