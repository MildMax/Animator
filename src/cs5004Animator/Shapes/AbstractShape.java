package cs5004Animator.Shapes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import cs5004Animator.Color;
import cs5004Animator.Transformations.Transformation;
import cs5004Animator.Transformations.TransformationType;

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
    else if (initialWidth <= 0) {
      throw new IllegalArgumentException("Initial width must be greater than zero.");
    }
    else if (name == null) {
      throw new IllegalArgumentException("String name cannot be null");
    }
    else if (initialColor == null) {
      throw new IllegalArgumentException("Color initialColor cannot be null");
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
    if (t == null) {
      throw new IllegalArgumentException("Transformation cannot be null");
    }
    for (Transformation transformation : transformationList) {
      if (t.getType() == transformation.getType()
              && checkTimes(transformation.getStart(), transformation.getEnd(),
              t.getStart(), t.getEnd())) {
        throw new IllegalArgumentException("Move already exists within that period");
      }
    }
    transformationList.add(t);
  }

  @Override
  public void removeTransformation(TransformationType type, int start, int end) {
    if (type == null) {
      throw new IllegalArgumentException("Transformation cannot be null");
    }
    for (Transformation t : transformationList) {
      if (t.getType() == type && t.getStart() == start && t.getEnd() == end) {
        transformationList.remove(t);
        return;
      }
    }
    throw new IllegalArgumentException("That transformation does not exist");
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getTransformationDescription() {
    String out = "";

    transformationList.sort(Comparator.comparing(Transformation::getStart));
    for (Transformation t : transformationList) {
      out += name + " " + t.toString() + "\n";
    }

    return out;
  }

  private boolean checkTimes(int currStart, int currEnd, int newStart, int newEnd) {
    return (newStart >= currStart && newStart <= currEnd)
            || (newEnd >= currStart && newEnd <= currEnd)
            || (currStart > newStart && currEnd < newEnd);
  }
}


