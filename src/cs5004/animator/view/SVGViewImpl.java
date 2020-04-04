package cs5004.animator.view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.shapes.Shape;
import cs5004.animator.model.shapes.ShapeType;
import cs5004.animator.model.transformations.Transformation;

/**
 * This class holds the functionality necessary to open an SVG file, format a String in the SVG
 * format, write the formatted string to the file, and then close the file. Extends the
 * AbstractTextView class which implements the AnimationView interface.
 */
public class SVGViewImpl extends AbstractTextView {

  private int delay;

  /**
   * This constructor takes a String a filename of the text file that the formatted SVG description
   * will be written to.
   *
   * @param fileName the name of the text file to be written to.
   * @param delay    represents the delay of each frame of the animation in milliseconds.
   * @throws IllegalArgumentException if the String filename arguments is null. If the specified
   *                                  file does not contain .svg file extension. If the specified
   *                                  file does not have a valid name. If the specified delay is
   *                                  less than 1.
   */
  public SVGViewImpl(String fileName, int delay) throws IllegalArgumentException {
    super(fileName);
    if (fileName == null) {
      throw new IllegalArgumentException("String fileName cannot be null");
    } else if (fileName.length() < 5
            || fileName.substring(fileName.length() - 4).compareTo(".svg") != 0) {
      throw new IllegalArgumentException("Specified file must have a "
              + "valid name and '.svg' extension");
    } else if (delay < 1) {
      throw new IllegalArgumentException("delay cannot be less than 1");
    }

    this.delay = delay;
  }

  /**
   * Writes a description of the animation held in an AnimationModel to the specified file as a
   * String formatted in SVG.
   *
   * @param m takes an AnimationModel that stores an animation to be written to
   * @throws IllegalArgumentException if the AnimationModel m is null.
   * @throws IllegalStateException    if the specified outfile has not been initialized. If the
   *                                  specified outfile cannot be written to.
   */
  @Override
  public void run(AnimationModel m)
          throws IllegalArgumentException, IllegalStateException {
    if (m == null) {
      throw new IllegalArgumentException("AnimationModel m cannot be null");
    }
    String svgTyp = "";
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
    int h1 = 0;
    int h2 = 0;
    int w1 = 0;
    int w2 = 0;

    StringBuffer b = new StringBuffer();

    // Create the background window.
    b.append("<svg width=\"");
    b.append(m.getAnimationWidth());
    b.append("\" height=\"");
    b.append(m.getAnimationHeight());
    b.append("\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\"> \n\n");

    // Loop through all of the shapes in the model.
    List<Shape> shapeList = m.getShapes();
    shapeList.sort(Comparator.comparing(Shape::getLayer));
    for (Shape s : shapeList) {

      // Get the initial color values for the shape.
      rInit = s.getShapeAtTick(s.getStart()).getR();
      gInit = s.getShapeAtTick(s.getStart()).getG();
      bInit = s.getShapeAtTick(s.getStart()).getB();

      // Assign the shape type and x/y/w/h attribute types in SVG modifier language.
      // Also Get the initial values for the shape.
      if (s.getType() == ShapeType.CIRCLE) {
        svgTyp = "ellipse";
        xTyp = "cx";
        yTyp = "cy";
        wTyp = "rx";
        hTyp = "ry";
        xInit = s.getShapeAtTick(s.getStart()).getX();
        yInit = s.getShapeAtTick(s.getStart()).getY();
        wInit = s.getShapeAtTick(s.getStart()).getWidth() / 2;
        hInit = s.getShapeAtTick(s.getStart()).getHeight() / 2;

      } else if (s.getType() == ShapeType.ELLIPSE) {
        svgTyp = "ellipse";
        xTyp = "cx";
        yTyp = "cy";
        wTyp = "rx";
        hTyp = "ry";
        xInit = s.getShapeAtTick(s.getStart()).getX();
        yInit = s.getShapeAtTick(s.getStart()).getY();
        wInit = s.getShapeAtTick(s.getStart()).getWidth() / 2;
        hInit = s.getShapeAtTick(s.getStart()).getHeight() / 2;

      } else if (s.getType() == ShapeType.OVAL) {
        svgTyp = "ellipse";
        xTyp = "cx";
        yTyp = "cy";
        wTyp = "rx";
        hTyp = "ry";
        xInit = s.getShapeAtTick(s.getStart()).getX();
        yInit = s.getShapeAtTick(s.getStart()).getY();
        wInit = s.getShapeAtTick(s.getStart()).getWidth() / 2;
        hInit = s.getShapeAtTick(s.getStart()).getHeight() / 2;

      } else if (s.getType() == ShapeType.RECTANGLE) {
        svgTyp = "rect";
        xTyp = "x";
        yTyp = "y";
        wTyp = "width";
        hTyp = "height";
        xInit = s.getShapeAtTick(s.getStart()).getX();
        yInit = s.getShapeAtTick(s.getStart()).getY();
        wInit = s.getShapeAtTick(s.getStart()).getWidth();
        hInit = s.getShapeAtTick(s.getStart()).getHeight();

      } else if (s.getType() == ShapeType.SQUARE) {
        svgTyp = "rect";
        xTyp = "x";
        yTyp = "y";
        wTyp = "width";
        hTyp = "height";
        xInit = s.getShapeAtTick(s.getStart()).getX();
        yInit = s.getShapeAtTick(s.getStart()).getY();
        wInit = s.getShapeAtTick(s.getStart()).getWidth();
        hInit = s.getShapeAtTick(s.getStart()).getHeight();
      }

      // Assign initial values to shape.
      b.append("<");
      b.append(svgTyp);
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
      b.append(rInit);
      b.append(",");
      b.append(gInit);
      b.append(",");
      b.append(bInit);
      b.append(")\" fill-opacity=\"1.0\" visibility=\"visible\" > \n\n");

      // Loop through all of the transformations on each shape.
      for (Transformation t : s.getTransformationList()) {

        // Height and Width depend on shape type. need to divide by 2 for ellipses.
        if (svgTyp.equals("ellipse")) {
          h1 = t.getH1() / 2;
          h2 = t.getH2() / 2;
          w1 = t.getW1() / 2;
          w2 = t.getW2() / 2;
        } else {
          h1 = t.getH1();
          h2 = t.getH2();
          w1 = t.getW1();
          w2 = t.getW2();
        }

        // If X value changes, animate the X change.
        if (t.getX1() != t.getX2()) {
          b.append("<animate attributeType=\"xml\" begin=\"");
          b.append(t.getStart() * delay);
          b.append("ms\" dur=\"");
          b.append((t.getEnd() - t.getStart()) * delay);
          b.append("ms\" attributeName=\"");
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
          b.append(t.getStart() * delay);
          b.append("ms\" dur=\"");
          b.append((t.getEnd() - t.getStart()) * delay);
          b.append("ms\" attributeName=\"");
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
          b.append(t.getStart() * delay);
          b.append("ms\" dur=\"");
          b.append((t.getEnd() - t.getStart()) * delay);
          b.append("ms\" attributeName=\"");
          b.append(hTyp);
          b.append("\" from=\"");
          b.append(h1);
          b.append("\" to=\"");
          b.append(h2);
          b.append("\" fill=\"freeze\" /> \n\n");
        }

        // If Width value changes, animate the Width change.
        if (t.getY1() != t.getY2()) {
          b.append("<animate attributeType=\"xml\" begin=\"");
          b.append(t.getStart() * delay);
          b.append("ms\" dur=\"");
          b.append((t.getEnd() - t.getStart()) * delay);
          b.append("ms\" attributeName=\"");
          b.append(wTyp);
          b.append("\" from=\"");
          b.append(w1);
          b.append("\" to=\"");
          b.append(w2);
          b.append("\" fill=\"freeze\" /> \n\n");
        }

        // If color changes, animate the color change.
        if (t.getR1() != t.getR2() || t.getG1() != t.getG2() || t.getB1() != t.getB2()) {
          b.append("<animate attributeType=\"xml\" begin=\"");
          b.append(t.getStart() * delay);
          b.append("ms\" dur=\"");
          b.append((t.getEnd() - t.getStart()) * delay);
          b.append("ms\" attributeName=\"");
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
      b.append(svgTyp);
      b.append("> \n\n");
    }
    b.append("</svg>");

    String finalString = b.toString();

    openView();
    try {
      ((FileWriter) out).write(finalString);
    } catch (IOException e) {
      throw new IllegalStateException("Cannot write to FileWriter " + fileName);
    }
    closeView();
  }

}
