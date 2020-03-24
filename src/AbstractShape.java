import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Create an abstract class called AbstractShape that is a generic shape.
 * This class implements the Shape interface.
 */
public abstract class AbstractShape implements Shape {
  protected final String name;
  protected final int layer;
  protected final int initialHeight;
  protected final int initialWidth;
  protected final int initialCenterX;
  protected final int initialCenterY;
  protected final Color initialColor;
  protected final double initialTransparency;
  private List<Transformation> transformationList;

  /**
   * Create a new instance of AbstractShape.
   *
   * @param name is the unique name of the shape.
   * @param layer is the order (back to front) that the shape appears relative to the other shapes.
   * @param initialHeight is the height of the shape.
   * @param initialWidth is the width of the shape.
   * @param initialCenterX is the X coordinate of the center of the shape.
   * @param initialCenterY is the Y coordinate of the center of the shape.
   * @param initialColor is the color of the shape.
   *
   * @throws IllegalArgumentException if initialHeight <= 0.
   * @throws IllegalArgumentException if initialWidth <= 0.
   *
   */
  AbstractShape(String name, int layer, int initialHeight, int initialWidth, int initialCenterX,
                int initialCenterY, Color initialColor) {
    if (initialHeight <= 0) {
      throw new IllegalArgumentException("Initial height must be greater than zero.");
    }
    if (initialWidth <= 0) {
      throw new IllegalArgumentException("Initial width must be greater than zero.");
    }
    this.name = name;
    this.layer = layer;
    this.initialHeight = initialHeight;
    this.initialWidth = initialWidth;
    this.initialCenterX = initialCenterX;
    this.initialCenterY = initialCenterY;
    this.initialColor = initialColor;
    this.initialTransparency = 0.0;
    transformationList = new ArrayList<>();
  }

  @Override
  public void addTransformation(Transformation t) {
    transformationList.add(t);
  }

  public void removeTransformation(Transformation t) {
    transformationList.remove(t);
  }

  @Override
  public List<Transformation> getTransformationList() {
    transformationList.sort(Comparator.comparing(Transformation::getStart));
    return transformationList;
  }

  @Override
  public String toString() {
    String out = "";

    out += "Create " + this.getClass().getName() + " " + this.name
            + " with center at (" + this.initialCenterX + ","
            + this.initialCenterY +") width " + this.initialWidth
            + " height " + this.initialHeight + " on layer "
            + this.layer + ".\n\n";

    for (Transformation t : transformationList) {
      out += name + " " + t.toString() + "\n";
    }

    return out;
  }
}
