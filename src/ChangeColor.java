/**
 * The ChangeColor class holds information needed to change a shape to a new color. Contains
 * the following features:
 *
 * <p>ChangeColor() constructor</p>
 *
 * <p>getColor() method</p>
 */
public class ChangeColor extends AbstractTransformation {

  private final Color newColor;

  /**
   * The AbstractTransformation constructor takes two ints indicating the start of the transformation
   * animation and the end of the transformation animation and a color enum indicating the color
   * that the shape should take on. Throws IllegalArgumentException if start is less than 0
   * or if end is before start or if the color enum is null.
   *
   * @param startTime takes an int indicating the start time of the transformation.
   * @param endTime   takes an int indicating the end time of the transformation
   * @throws IllegalArgumentException if the start time is less than 0.
   *                                  If the end time is before the start time.
   *                                  If the color enum is null.
   */
  public ChangeColor(int startTime, int endTime, Color newColor) throws IllegalArgumentException {
    super(startTime, endTime, TransformationType.CHANGECOLOR);

    if (newColor == null) {
      throw new IllegalArgumentException("Color cannot be null");
    }

    this.newColor = newColor;
  }

  @Override
  public String toString() {
    return "changes to " + newColor.toString() +  " " +super.toString();
  }
}
