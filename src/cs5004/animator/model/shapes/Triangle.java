package cs5004.animator.model.shapes;

import java.util.List;

import cs5004.animator.model.Color;
import cs5004.animator.model.transformations.ChangeColor;
import cs5004.animator.model.transformations.ChangeHeight;
import cs5004.animator.model.transformations.ChangeTransparency;
import cs5004.animator.model.transformations.ChangeWidth;
import cs5004.animator.model.transformations.Move;
import cs5004.animator.model.transformations.Scale;
import cs5004.animator.model.transformations.Transformation;

/**
 * Create a rectangle class that extends the AbstractShape abstract class.
 */
public class Triangle extends AbstractShape {

  /**
   * Create a new instance of Rectangle.
   *
   * @param name is the unique name of the shape.
   * @param layer is the order (back to front) that the shape appears relative to the other shapes.
   * @param initialHeight  is the height of the shape.
   * @param initialWidth   is the width of the shape.
   * @param initialCenterX is the X coordinate of the center of the shape.
   * @param initialCenterY is the Y coordinate of the center of the shape.
   * @param initialColor   is the color of the shape.
   *
   * @throws IllegalArgumentException if initialHeight <= 0.
   * @throws IllegalArgumentException if initialWidth <= 0.
   */
  public Triangle(String name, int layer, int initialHeight, int initialWidth, int initialCenterX,
           int initialCenterY, Color initialColor) {
    super(name, layer, initialHeight, initialWidth, initialCenterX, initialCenterY, initialColor,
            ShapeType.TRIANGLE);
  }

  /**
   * Return the full description of the shape and its transformations.
   *
   * @return the full description of the shape and its transformations.
   */
  @Override
  public String toString() {
    String out = "";

    out += "Create triangle " + this.name
            + " with center at (" + this.initialCenterX + ","
            + this.initialCenterY + "), width " + this.initialWidth
            + ", and height " + this.initialHeight + " on layer "
            + this.layer + " with color " + this.initialColor.toString()
            + ".\n\n";

    out += getTransformationDescription();

    return out;
  }

  @Override
  public Shape makeModifiedShape(int tick) {
    int newHeight = this.initialHeight;
    int newWidth = this.initialWidth;
    int newX = this.initialCenterX;
    int newY = this.initialCenterY;
    double newTransparency = this.initialTransparency;
    Color newColor = this.initialColor;
    List<Transformation> currList = getCurrentTransformations(tick);
    for (Transformation t : currList) {
      switch (t.getType()) {
        case MOVE:
          newX = ((Move) t).modifyX(newX, tick);
          newY = ((Move) t).modifyY(newY, tick);
          break;
        case SCALE:
          newHeight = ((Scale) t).scaleVal(newHeight, tick);
          newWidth = ((Scale) t).scaleVal(newWidth, tick);
          break;
        case CHANGECOLOR:
          newColor = ((ChangeColor) t).modifyColor(newColor, tick);
          break;
        case CHANGEWIDTH:
          newWidth = ((ChangeWidth) t).modifyWidth(newWidth, tick);
          break;
        case CHANGEHEIGHT:
          newHeight = ((ChangeHeight) t).modifyHeight(newHeight, tick);
          break;
        case CHANGETRANSPARENCY:
          newTransparency = ((ChangeTransparency) t).modifyTransparency(newTransparency, tick);
          break;
      }
    }
    Triangle c = new Triangle(this.name, this.layer, newHeight, newWidth,
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
