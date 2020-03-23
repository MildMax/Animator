/**
 * The Resize class handles resizing a shape according to the new width and height
 * of the shapes x and y values. Contains the following features:
 *
 * <p>Resize() constructor</p>
 *
 * <p>getWidth() method</p>
 *
 * <p>getHeight() method</p>
 */
public class Resize extends AbstractTransformation {

  private int newWidth;
  private int newHeight;

  /**
   * The Resize constructor takes two ints indicating the start of the transformation
   * animation and the end of the transformation animation and two ints indicating the
   * new width and the new height of the shape. Throws IllegalArgumentException if start
   * is less than 0 or if end is before start. Throws IllegalArgumentException if the
   * new width or height is non-positive.
   *
   * @param startTime takes an int indicating the start time of the transformation.
   * @param endTime   takes an int indicating the end time of the transformation
   * @param newWidth takes the new width value of the shape.
   * @param newHeight takes the new height value of the shape.
   * @throws IllegalArgumentException if the start time is less than 0.
   *                                  If the end time is before the start time.
   *                                  If the new width or height is non-positive
   */
  public Resize(int startTime, int endTime, int newWidth, int newHeight)
          throws IllegalArgumentException {
    super(startTime, endTime);

    if (newWidth <= 0 || newHeight <= 0) {
      throw new IllegalArgumentException("New width or height cannot be non-positive.");
    }

    this.newWidth = newWidth;
    this.newHeight = newHeight;
  }

  /**
   * Returns an int indicating the new width of the shape.
   *
   * @return an int indicating the new width of the shape.
   */
  public int getWidth() {
    return this.newWidth;
  }

  /**
   * Returns an int indicating the new height of the shape.
   *
   * @return an int indicating the new height of the shape.
   */
  public int getHeight() {
    return this.newHeight;
  }

  @Override
  public String getDescription(Shape shape) {
    return shape.getName() + " changes to width:" + this.getWidth() + " height:"
            + this.getHeight() + super.getDescription(shape);
  }
}
