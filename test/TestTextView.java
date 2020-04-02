import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.shapes.ShapeImpl;
import cs5004.animator.model.shapes.ShapeType;
import cs5004.animator.model.transformations.TransformationImpl;
import cs5004.animator.view.AnimationView;
import cs5004.animator.view.TextView;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the TextView class.
 */
public class TestTextView {

  /**
   * Test creating a TextView with a specified text file to write output to.
   */
  @Test
  public void testTextViewFileNameConstructor() {
    String path = Paths.get("").toAbsolutePath().toString() + "\\test_out.txt";

    AnimationView t = new TextView(path);
    AnimationModel m = new AnimationModelImpl(50, 60, 500, 600);

    t.run(m);

    String test = "Create window with width 500 and height 600 with top left "
            + "corner (50,60) and total ticks 0";

    assertEquals(test, t.getOutFileContents());

    m.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    m.addShape(new ShapeImpl("ellipse", ShapeType.ELLIPSE, 2));

    t.run(m);

    test = "Create window with width 500 and height 600 with top left "
            + "corner (50,60) and total ticks 0";

    assertEquals(test, t.getOutFileContents());


    m.addTransformation("rectangle", new TransformationImpl("rectangle", 10,
            20, -20, 20, 40, 100, 100, 100, 20, 40, -40,
            40, 60, 200, 200, 200));
    m.addTransformation("ellipse", new TransformationImpl("ellipse", 20,
            0, 0, 10, 30, 50, 50, 50, 40, 40, -40,
            40, 60, 150, 150, 150));

    t.run(m);

    test = "Create window with width 500 and height 600 with top left "
            + "corner (50,60) and total ticks 40\n\n"
            + "Create rectangle rectangle with center at (20,-20) with width 20 "
            + "and height 40 and color (100,100,100)\n"
            + "Create ellipse ellipse with center at (0,0) with width 10 and height "
            + "30 and color (50,50,50)\n\n"
            + "rectangle rectangle appears at time t=10 and disappears at time t=20\n"
            + "ellipse ellipse appears at time t=20 and disappears at time t=40\n\n"
            + "rectangle moves from (20,-20) to (40,-40) from time t=10 to time t=20\n"
            + "rectangle changes width from 20 to 40 from time t=10 to time t=20\n"
            + "rectangle changes height from 40 to 60 from time t=10 to time t=20\n"
            + "rectangle changes from color (100,100,100) to color (200,200,200) "
            + "from time t=10 to time t=20\n"
            + "ellipse moves from (0,0) to (40,-40) from time t=20 to time t=40\n"
            + "ellipse changes width from 10 to 40 from time t=20 to time t=40\n"
            + "ellipse changes height from 30 to 60 from time t=20 to time t=40\n"
            + "ellipse changes from color (50,50,50) to color (150,150,150) "
            + "from time t=20 to time t=40";

    assertEquals(test, t.getOutFileContents());
  }

  /**
   * Test creating a TextView that writes to System.out.
   */
  @Test
  public void testTextViewToSystemOut() {
    AnimationModel m = new AnimationModelImpl(50, 60, 500, 600);

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    AnimationView textView = new TextView();

    textView.run(m);

    String test = "Create window with width 500 and height 600 with top left "
            + "corner (50,60) and total ticks 0";

    assertEquals(test, outContent.toString());

    outContent.reset();

    m.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    m.addShape(new ShapeImpl("ellipse", ShapeType.ELLIPSE, 2));

    textView.run(m);

    test = "Create window with width 500 and height 600 with top left "
            + "corner (50,60) and total ticks 0";

    assertEquals(test, outContent.toString());

    outContent.reset();

    m.addTransformation("rectangle", new TransformationImpl("rectangle", 10,
            20, -20, 20, 40, 100, 100, 100, 20, 40, -40,
            40, 60, 200, 200, 200));
    m.addTransformation("ellipse", new TransformationImpl("ellipse", 20,
            0, 0, 10, 30, 50, 50, 50, 40, 40, -40,
            40, 60, 150, 150, 150));

    textView.run(m);

    test = "Create window with width 500 and height 600 with top left "
            + "corner (50,60) and total ticks 40\n\n"
            + "Create rectangle rectangle with center at (20,-20) with width 20 "
            + "and height 40 and color (100,100,100)\n"
            + "Create ellipse ellipse with center at (0,0) with width 10 and height "
            + "30 and color (50,50,50)\n\n"
            + "rectangle rectangle appears at time t=10 and disappears at time t=20\n"
            + "ellipse ellipse appears at time t=20 and disappears at time t=40\n\n"
            + "rectangle moves from (20,-20) to (40,-40) from time t=10 to time t=20\n"
            + "rectangle changes width from 20 to 40 from time t=10 to time t=20\n"
            + "rectangle changes height from 40 to 60 from time t=10 to time t=20\n"
            + "rectangle changes from color (100,100,100) to color (200,200,200) "
            + "from time t=10 to time t=20\n"
            + "ellipse moves from (0,0) to (40,-40) from time t=20 to time t=40\n"
            + "ellipse changes width from 10 to 40 from time t=20 to time t=40\n"
            + "ellipse changes height from 30 to 60 from time t=20 to time t=40\n"
            + "ellipse changes from color (50,50,50) to color (150,150,150) "
            + "from time t=20 to time t=40";

    assertEquals(test, outContent.toString());

    System.setOut(originalOut);
  }

  /**
   * Test passing a null filename to the TextView constructor that specifies an output.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTextViewConstructorNullFileName() {
    new TextView(null);
  }

  /**
   * Test passing a filename to the TextView that doesn't contain a valid name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTextViewConstructorNoFileName() {
    new TextView(".txt");
  }

  /**
   * Test passing a filename to the TextView that doesn't contain the '.txt' extension.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTextViewConstructorNoFileExtension() {
    new TextView("textfile");
  }

  /**
   * Test writing to a file from a null AnimationModel.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTextViewWriteNullAnimationModel() {
    AnimationView textView = new TextView();

    textView.run(null);
  }

  /**
   * Test drawing a new frame in TextView.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testTextDrawNewFrame() {
    new TextView().drawNewFrame(new ArrayList<>());
  }

  /**
   * Test getting content from file when no file is specified in TextView.
   */
  @Test(expected = IllegalStateException.class)
  public void testTextViewGetOutFileNullName() {
    new TextView().getOutFileContents();
  }

}
