package cs5004animator.model.shapes;

import java.util.List;

import cs5004animator.model.Color;
import cs5004animator.model.transformations.Appearance;
import cs5004animator.model.transformations.ChangeColor;
import cs5004animator.model.transformations.ChangeHeight;
import cs5004animator.model.transformations.ChangeTransparency;
import cs5004animator.model.transformations.ChangeWidth;
import cs5004animator.model.transformations.Move;
import cs5004animator.model.transformations.Scale;
import cs5004animator.model.transformations.Transformation;

/**
 * Create an Oval class that extends the AbstractShape abstract class.
 */
public class Oval extends AbstractShape {

  private final int initialVerticalRadius;
  private final int initialHorizontalRadius;

  /**
   * Create a new instance of Oval.
   *
   * @param name is the unique name of the shape.
   * @param layer is the order (back to front) that the shape appears relative to the other shapes.
   * @param initialVerticalRadius  is the height of the shape.
   * @param initialHorizontalRadius   is the width of the shape.
   * @param initialCenterX is the X coordinate of the center of the shape.
   * @param initialCenterY is the Y coordinate of the center of the shape.
   * @param initialColor   is the color of the shape.
   *
   * @throws IllegalArgumentException if initialVerticalRadius <= 0.
   * @throws IllegalArgumentException if initialHorizontalRadius <= 0.
   */
  public Oval(String name, int layer, int initialVerticalRadius, int initialHorizontalRadius,
       int initialCenterX, int initialCenterY, Color initialColor) {
    super(name, layer,initialVerticalRadius * 2,
            initialHorizontalRadius * 2, initialCenterX, initialCenterY, initialColor,
            ShapeType.OVAL);

    this.initialVerticalRadius = initialVerticalRadius;
    this.initialHorizontalRadius = initialHorizontalRadius;
  }

  /**
   * Return the full description of the shape and its transformations.
   *
   * @return the full description of the shape and its transformations.
   */
  @Override
  public String toString() {
    String out = "";

    out += "Create oval " + this.name
            + " with center at (" + this.initialCenterX + ","
            + this.initialCenterY + "), horizontal radius " + this.initialHorizontalRadius
            + ", and vertical radius " + this.initialVerticalRadius + " on layer "
            + this.layer + " with color " + this.initialColor.toString()
            + ".\n\n";

    out += getTransformationDescription();

    return out;
  }

  @Override
  public Shape makeModifiedShape(int tick) {
    int newVerticalRadius = this.initialVerticalRadius;
    int newHorizontalRadius = this.initialHorizontalRadius;
    int newX = this.initialCenterX;
    int newY = this.initialCenterY;
    double newTransparency = this.initialTransparency;
    Color newColor = this.initialColor;
    List<Transformation> currList = getCurrentTransformations(tick);
    for (Transformation t : currList) {
      switch (t.getType()) {
        case APPEARANCE:
          newTransparency = ((Appearance) t).setAppearance(tick);
          break;
        case MOVE:
          newX = ((Move) t).modifyX(newX, tick);
          newY = ((Move) t).modifyY(newY, tick);
          break;
        case SCALE:
          newVerticalRadius = ((Scale) t).scaleVal(newVerticalRadius, tick);
          newHorizontalRadius = ((Scale) t).scaleVal(newHorizontalRadius, tick);
          break;
        case CHANGECOLOR:
          newColor = ((ChangeColor) t).modifyColor(newColor, tick);
          break;
        case CHANGEWIDTH:
          newHorizontalRadius = ((ChangeWidth) t).modifyWidth(newHorizontalRadius, tick);
          break;
        case CHANGEHEIGHT:
          newVerticalRadius = ((ChangeHeight) t).modifyHeight(newVerticalRadius, tick);
          break;
        case CHANGETRANSPARENCY:
          newTransparency = ((ChangeTransparency) t).modifyTransparency(newTransparency, tick);
          break;
      }
    }
    Oval c = new Oval(this.name, this.layer, newVerticalRadius, newHorizontalRadius,
            newX, newY, newColor);
    c.initialTransparency = newTransparency;
    if (newTransparency == 0) {
      return null;
    }
    else {
      return c;
    }
  }
}
