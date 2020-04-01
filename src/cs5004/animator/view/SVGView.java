package cs5004.animator.view;

import java.io.FileWriter;
import java.io.IOException;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.shapes.Shape;
import cs5004.animator.model.shapes.ShapeType;
import cs5004.animator.model.transformations.Transformation;
import cs5004.animator.model.transformations.TransformationImpl;

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
    StringBuffer b = new StringBuffer();

    // Create the background window.
    b.append("<svg width=\"");
    b.append(m.getWindowWidth());
    b.append("\" height=\"");
    b.append(m.getWindowHeight());
    b.append(" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n");

    // Loop through all of the shapes in the model.
    for (Shape s : m.getShapes()) {

      // Assign initial values if shape is rectangle or square.
      if (s.getTypeSVG().equals("rect")) {
        b.append("<");
        b.append(s.getTypeSVG());
        b.append(" id=\"");
        b.append(s.getName());
        b.append("\" x=\"");
        b.append(s.getX() - (s.getWidth() / 2));
        b.append("\" y=\"");
        b.append(s.getY() - (s.getHeight() / 2));
        b.append("\" width=\"");
        b.append(s.getWidth());
        b.append("\" height=\"");
        b.append(s.getHeight());
        b.append("\" fill=\"rgb(");
        b.append(s.getR());
        b.append(",");
        b.append(s.getG());
        b.append(",");
        b.append(s.getB());
        b.append(")\"visibility=\"visible\" >\n\n");

      // Assign initial values if shape is circle, oval, or ellipse.
      } else if (s.getTypeSVG().equals("ellipse")) {
        if (s.getTypeSVG().equals("rect")) {
          b.append("<");
          b.append(s.getTypeSVG());
          b.append(" id=\"");
          b.append(s.getName());
          b.append("\" cx=\"");
          b.append(s.getX());
          b.append("\" cy=\"");
          b.append(s.getY());
          b.append("\" rx=\"");
          b.append(s.getWidth());
          b.append("\" ry=\"");
          b.append(s.getHeight());
          b.append("\" fill=\"rgb(");
          b.append(s.getR());
          b.append(",");
          b.append(s.getG());
          b.append(",");
          b.append(s.getB());
          b.append(")\"visibility=\"visible\" >\n\n");
      }

      // Loop through all of the transformations on each shape.
      for (Transformation t : s.getTransformationList()) {

        // If X value changes, animate the X change.
        if (t.getX1() != t.getX2()) {
          b.append("<animate attributeType=\"xml\" begin=\"");
          b.append(t.getStart());
          b.append("000ms\" dur=\"");
          b.append(t.getEnd() - t.getStart());
          b.append("000ms\" attributeName=\"x\" from=\"");
          b.append(t.getX1());
          b.append("\" to=\"");
          b.append(t.getX2());
          b.append("\" fill=\"freeze\" />\\n\\n");
        }

        // If Y value changes, animate the Y change.
        if (t.getY1() != t.getY2()) {
          b.append("<animate attributeType=\"xml\" begin=\"");
          b.append(t.getStart());
          b.append("000ms\" dur=\"");
          b.append(t.getEnd() - t.getStart());
          b.append("000ms\" attributeName=\"y\" from=\"");
          b.append(t.getY1());
          b.append("\" to=\"");
          b.append(t.getY2());
          b.append("\" fill=\"freeze\" />\\n\\n");
        }

        // If Height value changes, animate the Height change.
        if (t.getY1() != t.getY2()) {
          b.append("<animate attributeType=\"xml\" begin=\"");
          b.append(t.getStart());
          b.append("000ms\" dur=\"");
          b.append(t.getEnd() - t.getStart());
          b.append("000ms\" attributeName=\"y\" from=\"");
          b.append(t.getY1());
          b.append("\" to=\"");
          b.append(t.getY2());
          b.append("\" fill=\"freeze\" />\\n\\n");
        }




      }
      b.append("</");
      b.append(s.getTypeSVG());
      b.append(">");
    }
    b.append("</svg>");
    }










    try {
      ((FileWriter) out).write(m.toString());
    } catch (IOException e) {
      throw new IllegalStateException("Cannot write to FileWriter " + fileName);
    }
  }


  public void writeWithLoopBack(AnimationModel m) {

    {}

    try {
      ((FileWriter) out).write(m.toString());
    } catch (IOException e) {
      throw new IllegalStateException("Cannot write to FileWriter " + fileName);
    }
  }
}
