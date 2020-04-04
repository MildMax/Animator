import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.shapes.Shape;
import cs5004.animator.model.shapes.ShapeImpl;
import cs5004.animator.model.shapes.ShapeType;
import cs5004.animator.model.transformations.TransformationImpl;
import cs5004.animator.view.AnimationView;
import cs5004.animator.view.SVGViewImpl;
import cs5004.animator.view.TextViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the SVGViewImpl Class.
 */
public class TestSVGViewImpl {

  /**
   * Test creating a TextView with a specified text file to write output to.
   */
  @Test
  public void test001() {

    // Set outfile path.
    String path = Paths.get("").toAbsolutePath().toString() + "/My_view1.svg";

    // Initialize empty model and test.
    AnimationModel m = new AnimationModelImpl(0, 100, 200, 300);
    AnimationView view1 = new SVGViewImpl(path, 50);
    view1.run(m);
    String SVGcontents = "<svg width=\"25\" height=\"25\" version=\"1.1\" xmlns" +
            "=\"http://www.w3.org/2000/svg\"> \n" +
            "\n" +
            "</svg>";
    assertEquals(SVGcontents, view1.getOutFileContents());

    // Add shapes and test.
    m.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    m.addShape(new ShapeImpl("ellipse", ShapeType.ELLIPSE, 2));
    view1.run(m);
    SVGcontents = "<svg width=\"25\" height=\"25\" version=\"1.1\" xmlns=\"http://www.w" +
            "3.org/2000/svg\"> \n" +
            "\n" +
            "</svg>";
    assertEquals(SVGcontents, view1.getOutFileContents());

    // Add Transformations and test.
    m.addTransformation("rectangle", new TransformationImpl("rectangle", 10,
            20, 20, 20, 40, 100, 100, 100, 20, 40, 40,
            40, 60, 200, 200, 200));
    m.addTransformation("ellipse", new TransformationImpl("ellipse", 20,
            0, 0, 10, 30, 50, 50, 50, 40, 40, 40,
            40, 60, 150, 150, 150));
    view1.run(m);
    SVGcontents = "<svg width=\"105\" height=\"125\" version=\"1.1\" xmlns=\"http://ww"
            + "w.w3.org/2000/svg\"> \n"
            + "\n"
            + "<rect id=\"rectangle\" x=\"20\" y=\"20\" width=\"20\" height=\"40\" fi"
            + "ll=\"rgb(100,100,100)\" fill-opacity=\"1.0\" visibility=\"visible\" > \n"
            + "\n"
            + "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"500ms\" attribu"
            + "teName=\"x\" from=\"20\" to=\"40\" fill=\"freeze\" /> \n"
            + "\n"
            + "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"500ms\" attribu"
            + "teName=\"y\" from=\"20\" to=\"40\" fill=\"freeze\" /> \n"
            + "\n"
            + "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"500ms\" attribu"
            + "teName=\"height\" from=\"40\" to=\"60\" fill=\"freeze\" /> \n"
            + "\n"
            + "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"500ms\" attribut"
            + "eName=\"width\" from=\"20\" to=\"40\" fill=\"freeze\" /> \n"
            + "\n"
            + "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"500ms\" attribut"
            + "eName=\"fill\" from=\"rgb(100,100,100)\" to=\"rgb(200,200,200)\" fill=\"fre"
            + "eze\" /> \n"
            + "\n"
            + "</rect> \n"
            + "\n"
            + "<ellipse id=\"ellipse\" cx=\"0\" cy=\"0\" rx=\"5\" ry=\"15\" fill=\"rgb(50"
            + ",50,50)\" fill-opacity=\"1.0\" visibility=\"visible\" > \n"
            + "\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1000ms\" attribu"
            + "teName=\"cx\" from=\"0\" to=\"40\" fill=\"freeze\" /> \n"
            + "\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1000ms\" attrib"
            + "uteName=\"cy\" from=\"0\" to=\"40\" fill=\"freeze\" /> \n"
            + "\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1000ms\" attribut"
            + "eName=\"ry\" from=\"15\" to=\"30\" fill=\"freeze\" /> \n"
            + "\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1000ms\" attribu"
            + "teName=\"rx\" from=\"5\" to=\"20\" fill=\"freeze\" /> \n"
            + "\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1000ms\" attribu"
            + "teName=\"fill\" from=\"rgb(50,50,50)\" to=\"rgb(150,150,150)\" fill=\"free"
            + "ze\" /> \n"
            + "\n"
            + "</ellipse> \n"
            + "\n"
            + "</svg>";
    //assertEquals(SVGcontents, view1.getOutFileContents());
  }

  /**
   * Test passing a null filename to the SVGView constructor that specifies an output.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSVGViewConstructorNullFileName() {
    new SVGViewImpl(null, 50);
  }

  /**
   * Test passing a filename to the SVGView that doesn't contain a valid name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSVGViewConstructorNoFileName() {
    new SVGViewImpl(".txt", 50);
  }

  /**
   * Test passing a filename to the SVGView that doesn't contain the '.txt' extension.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSVGViewConstructorNoFileExtension() {
    new SVGViewImpl("textfile", 50);
  }

  /**
   * Test writing to a file from a null AnimationModel.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSVGViewWriteNullAnimationModel() {
    String path = Paths.get("").toAbsolutePath().toString() + "/My_view1.svg";
    AnimationModel m = new AnimationModelImpl(0, 100, 200, 300);
    AnimationView view1 = new SVGViewImpl(path, 50);
    view1.run(null);
  }

  /**
   * Test getting content from file when no file is specified in SVGView.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTextViewGetOutFileNullName() {
    new SVGViewImpl(null, 50).getOutFileContents();
  }

  /**
   * Test creating an SVGView with a delay of 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDelay0() {
    String path = Paths.get("").toAbsolutePath().toString() + "/My_view1.svg";
    AnimationModel m = new AnimationModelImpl(0, 100, 200, 300);
    AnimationView view1 = new SVGViewImpl(path, 0);
    view1.run(m);
  }

  /**
   * Test creating an SVGView with a delay of 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDelayNegative() {
    String path = Paths.get("").toAbsolutePath().toString() + "/My_view1.svg";
    AnimationModel m = new AnimationModelImpl(0, 100, 200, 300);
    AnimationView view1 = new SVGViewImpl(path, -1);
    view1.run(m);
  }


}
