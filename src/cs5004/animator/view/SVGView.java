package cs5004.animator.view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.shapes.Shape;
import cs5004.animator.model.shapes.ShapeType;
import cs5004.animator.model.transformations.Transformation;

public class SVGView extends AbstractTextView {

  /**
   * This constructor takes a String containing data to be written to the view and a filename of the
   * text file that the description will be written to.
   *
   * @param fileName the name of the text file to be written to.
   * @throws IllegalArgumentException if the String data or filename arguments are null.
   */
  public SVGView(String fileName) throws IllegalArgumentException {
    super(fileName);
    if (fileName == null) {
      throw new IllegalArgumentException("String fileName cannot be null");
    }
    else if (fileName.length() < 5
            || fileName.substring(fileName.length() - 4).compareTo(".svg") != 0) {
      throw new IllegalArgumentException("Specified file must have a "
              + "valid name and '.svg' extension");
    }
  }

  @Override
  public void write(AnimationModel m) {
    super.write(m);
    String SVGTyp = "";
    String xTyp = "";
    String yTyp = "";
    String wTyp = "";
    String hTyp = "";
    int xInit = 0;
    int yInit = 0;
    int wInit = 0;
    int hInit = 0;
    int rInit = 0;
    int gInit = 0;
    int bInit = 0;

    StringBuffer b = new StringBuffer();

    // Create the background window.
    b.append("<svg width=\"");
    b.append(m.getWindowWidth());
    b.append("\" height=\"");
    b.append(m.getWindowHeight());
    b.append("\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\"> \n\n");

    // Loop through all of the shapes in the model.
    List<Shape> shapeList = m.getShapes();
    for (Shape s : m.getShapes()) {

      // Get the initial color values for the shape.
      rInit = s.getShapeAtTick(s.getStart()).getR();
      gInit = s.getShapeAtTick(s.getStart()).getG();
      bInit = s.getShapeAtTick(s.getStart()).getB();

      // Assign the shape type and x/y/w/h attribute types in SVG modifier language.
      // Also Get the initial values for the shape.
      if (s.getType() == ShapeType.CIRCLE) {
        SVGTyp = "ellipse";
        xTyp = "cx";
        yTyp = "cy";
        wTyp = "rx";
        hTyp = "ry";
        xInit = s.getShapeAtTick(s.getStart()).getX();
        yInit = s.getShapeAtTick(s.getStart()).getY();
        wInit = s.getShapeAtTick(s.getStart()).getWidth(); // Change depending on whether data provides radius or diameter.
        hInit = s.getShapeAtTick(s.getStart()).getHeight(); // Change depending on whether data provides radius or diameter.

      } else if (s.getType() == ShapeType.ELLIPSE) {
        SVGTyp = "ellipse";
        xTyp = "cx";
        yTyp = "cy";
        wTyp = "rx";
        hTyp = "ry";
        xInit = s.getShapeAtTick(s.getStart()).getX();
        yInit = s.getShapeAtTick(s.getStart()).getY();
        wInit = s.getShapeAtTick(s.getStart()).getWidth(); // Change depending on whether data provides radius or diameter.
        hInit = s.getShapeAtTick(s.getStart()).getHeight(); // Change depending on whether data provides radius or diameter.

      } else if (s.getType() == ShapeType.OVAL) {
        SVGTyp = "ellipse";
        xTyp = "cx";
        yTyp = "cy";
        wTyp = "rx";
        hTyp = "ry";
        xInit = s.getShapeAtTick(s.getStart()).getX();
        yInit = s.getShapeAtTick(s.getStart()).getY();
        wInit = s.getShapeAtTick(s.getStart()).getWidth(); // Change depending on whether data provides radius or diameter.
        hInit = s.getShapeAtTick(s.getStart()).getHeight(); // Change depending on whether data provides radius or diameter.

      } else if (s.getType() == ShapeType.RECTANGLE) {
        SVGTyp = "rect";
        xTyp = "x";
        yTyp = "y";
        wTyp = "width";
        hTyp = "height";
        xInit = s.getShapeAtTick(s.getStart()).getX(); // Change depending on which (x,y) point the data provides.
        yInit = s.getShapeAtTick(s.getStart()).getY(); // Change depending on which (x,y) point the data provides.
        wInit = s.getShapeAtTick(s.getStart()).getWidth();
        hInit = s.getShapeAtTick(s.getStart()).getHeight();

      } else if (s.getType() == ShapeType.SQUARE) {
        SVGTyp = "rect";
        xTyp = "x";
        yTyp = "y";
        wTyp = "width";
        hTyp = "height";
        xInit = s.getShapeAtTick(s.getStart()).getX(); // Change depending on which (x,y) point the data provides.
        yInit = s.getShapeAtTick(s.getStart()).getY(); // Change depending on which (x,y) point the data provides.
        wInit = s.getShapeAtTick(s.getStart()).getWidth();
        hInit = s.getShapeAtTick(s.getStart()).getHeight();
      }

      // Assign initial values to shape.
        b.append("<");
        b.append(SVGTyp);
        b.append(" id=\"");
        b.append(s.getName());
        b.append("\" ");
        b.append(xTyp);
        b.append("=\"");
        b.append(xInit);
        b.append("\" ");
        b.append(yTyp);
        b.append("=\"");
        b.append(yInit);
        b.append("\" ");
        b.append(wTyp);
        b.append("=\"");
        b.append(wInit);
        b.append("\" ");
        b.append(hTyp);
        b.append("=\"");
        b.append(hInit);
        b.append("\" fill=\"rgb(");
        b.append(s.getR());
        b.append(",");
        b.append(s.getG());
        b.append(",");
        b.append(s.getB());
        b.append(")\" visibility=\"visible\" > \n\n");

      // Loop through all of the transformations on each shape.
      for (Transformation t : s.getTransformationList()) {

        // If X value changes, animate the X change.
        if (t.getX1() != t.getX2()) {
          b.append("<animate attributeType=\"xml\" begin=\"");
          b.append(t.getStart());
          b.append("000ms\" dur=\"");
          b.append(t.getEnd() - t.getStart());
          b.append("000ms\" attributeName=\"");
          b.append(xTyp);
          b.append("\" from=\"");
          b.append(t.getX1());
          b.append("\" to=\"");
          b.append(t.getX2());
          b.append("\" fill=\"freeze\" /> \n\n");
        }

        // If Y value changes, animate the Y change.
        if (t.getY1() != t.getY2()) {
          b.append("<animate attributeType=\"xml\" begin=\"");
          b.append(t.getStart());
          b.append("000ms\" dur=\"");
          b.append(t.getEnd() - t.getStart());
          b.append("000ms\" attributeName=\"");
          b.append(yTyp);
          b.append("\" from=\"");
          b.append(t.getY1());
          b.append("\" to=\"");
          b.append(t.getY2());
          b.append("\" fill=\"freeze\" /> \n\n");
        }

        // If Height value changes, animate the Height change.
        if (t.getY1() != t.getY2()) {
          b.append("<animate attributeType=\"xml\" begin=\"");
          b.append(t.getStart());
          b.append("000ms\" dur=\"");
          b.append(t.getEnd() - t.getStart());
          b.append("000ms\" attributeName=\"");
          b.append(hTyp);
          b.append("\" from=\"");
          b.append(t.getH1());
          b.append("\" to=\"");
          b.append(t.getH2());
          b.append("\" fill=\"freeze\" /> \n\n");
        }

        // If Width value changes, animate the Width change.
        if (t.getY1() != t.getY2()) {
          b.append("<animate attributeType=\"xml\" begin=\"");
          b.append(t.getStart());
          b.append("000ms\" dur=\"");
          b.append(t.getEnd() - t.getStart());
          b.append("000ms\" attributeName=\"");
          b.append(wTyp);
          b.append("\" from=\"");
          b.append(t.getW1());
          b.append("\" to=\"");
          b.append(t.getW2());
          b.append("\" fill=\"freeze\" /> \n\n");
        }

        // If color changes, animate the color change.
        if (t.getR1() != t.getR2() || t.getG1() != t.getG2() || t.getB1() != t.getB2()) {
          b.append("<animate attributeType=\"xml\" begin=\"");
          b.append(t.getStart());
          b.append("000ms\" dur=\"");
          b.append(t.getEnd() - t.getStart());
          b.append("000ms\" attributeName=\"");
          b.append("fill");
          b.append("\" from=\"rgb(");
          b.append(t.getR1());
          b.append(",");
          b.append(t.getG1());
          b.append(",");
          b.append(t.getB1());
          b.append(")\" to=\"rgb(");
          b.append(t.getR2());
          b.append(",");
          b.append(t.getG2());
          b.append(",");
          b.append(t.getB2());
          b.append(")\" fill=\"freeze\" /> \n\n");
        }
      }
      b.append("</");
      b.append(SVGTyp);
      b.append("> \n\n");
    }
    b.append("</svg>");

    String finalString = b.toString();

    try {
      ((FileWriter) out).write(finalString);
    } catch (IOException e) {
      throw new IllegalStateException("Cannot write to FileWriter " + fileName);
    }
  }

}
