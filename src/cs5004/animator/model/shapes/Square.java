package cs5004.animator.model.shapes;

import java.util.List;

import cs5004.animator.model.Color;
import cs5004.animator.model.transformations.Appearance;
import cs5004.animator.model.transformations.ChangeColor;
import cs5004.animator.model.transformations.ChangeHeight;
import cs5004.animator.model.transformations.ChangeTransparency;
import cs5004.animator.model.transformations.ChangeWidth;
import cs5004.animator.model.transformations.Move;
import cs5004.animator.model.transformations.Scale;
import cs5004.animator.model.transformations.Transformation;

/**
 * Create a square class that extends the rectangle class.
 */
public class Square extends AbstractShape {

  private final int side;

  /**
   * Create a new instance of Square.
   *
   * @param name is the unique name of the shape.
   * @param layer is the order (back to front) that the shape appears relative to the other shapes.
   * @param side   is the width of the shape.
   * @param initialCenterX is the X coordinate of the center of the shape.
   * @param initialCenterY is the Y coordinate of the center of the shape.
   * @param initialColor   is the color of the shape.
   *
   * @throws IllegalArgumentException if side <= 0.
   */
  public Square(String name, int layer, int side, int initialCenterX, int initialCenterY,
                Color initialColor) {
    super(name, layer, side, side, initialCenterX, initialCenterY, initialColor, ShapeType.SQUARE);

    this.side = side;
  }

  /**
   * Return the full description of the shape and its transformations.
   *
   * @return the full description of the shape and its transformations.
   */
  @Override
  public String toString() {
    String out = "";

    out += "Create square " + this.name
            + " with center at (" + this.initialCenterX + ","
            + this.initialCenterY + ") and side " + this.side + " on layer "
            + this.layer + " with color " + this.initialColor.toString()
            + ".\n\n";

    out += getTransformationDescription();

    return out;
  }

  @Override
  public Shape makeModifiedShape(int tick) {
    int newSide = this.side;
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
          newSide = ((Scale) t).scaleVal(newSide, tick);
          break;
        case CHANGECOLOR:
          newColor = ((ChangeColor) t).modifyColor(newColor, tick);
          break;
        case CHANGEWIDTH:
          newSide = ((ChangeWidth) t).modifyWidth(newSide, tick);
          break;
        case CHANGEHEIGHT:
          newSide = ((ChangeHeight) t).modifyHeight(newSide, tick);
          break;
        case CHANGETRANSPARENCY:
          newTransparency = ((ChangeTransparency) t).modifyTransparency(newTransparency, tick);
          break;
      }
    }
    Square c = new Square(this.name, this.layer, newSide,
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
