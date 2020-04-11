import org.junit.Test;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.List;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.controller.AnimationControllerImpl;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.shapes.Shape;
import cs5004.animator.model.shapes.ShapeImpl;
import cs5004.animator.model.shapes.ShapeType;
import cs5004.animator.model.transformations.TransformationImpl;
import cs5004.animator.view.AnimationRunner;
import cs5004.animator.view.AnimationRunnerImpl;
import cs5004.animator.view.AnimationView;
import cs5004.animator.view.PlaybackViewImpl;
import cs5004.animator.view.SVGViewImpl;
import cs5004.animator.view.ShapePanel;
import cs5004.animator.view.TextViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test the Class AnimationControllerImpl.
 */
public class TestAnimationController {

  /**
   * Test the creation of an object of the AnimationControllerImpl class.
   */
  @Test
  public void test001() {
    AnimationModel m = new AnimationModelImpl(0, 100, 200, 300);
    PlaybackViewImpl v = new PlaybackViewImpl(1000,1000, 1000,
            1000, 1000, 1000, 100);
    m.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    m.addShape(new ShapeImpl("ellipse", ShapeType.ELLIPSE, 2));
    m.addTransformation("rectangle", new TransformationImpl("rectangle",
            10, 20, 20, 20, 40, 100, 100, 100, 20, 40,
            40, 40, 60, 200, 200, 200));
    m.addTransformation("ellipse", new TransformationImpl("ellipse", 20,
            0, 0, 10, 30, 50, 50, 50, 40, 40, 40,
            40, 60, 150, 150, 150));

    AnimationController c = new AnimationControllerImpl(m, v);

    c.doAnimation();
    ((AnimationControllerImpl) c).getAnimationView().getRunner().startAnim();

    assertEquals(true, ((AnimationControllerImpl) c).getAnimationView()
            .getRunner().isRunning());
    AnimationRunner r = ((AnimationControllerImpl) c).getAnimationView().getRunner();
    assertEquals(1, ((AnimationRunnerImpl) r).getFrames());
    assertEquals(2, ((AnimationControllerImpl) c).getAnimationModel().getShapes().size());
    assertEquals("rectangle", ((AnimationControllerImpl) c).getAnimationModel()
            .getShapes().get(0).getName());
    assertEquals("ellipse", ((AnimationControllerImpl) c).getAnimationModel()
            .getShapes().get(1).getName());

  }

  /**
   * Test passing of a null model. Should throw exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test002() {
    AnimationModel m = new AnimationModelImpl(0, 100, 200, 300);
    PlaybackViewImpl v = new PlaybackViewImpl(1000, 1000, 1000,
            1000, 1000, 1000, 100);
    m.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    m.addShape(new ShapeImpl("ellipse", ShapeType.ELLIPSE, 2));
    m.addTransformation("rectangle", new TransformationImpl("rectangle",
            10, 20, 20, 20, 40, 100, 100, 100, 20, 40,
            40, 40, 60, 200, 200, 200));
    m.addTransformation("ellipse", new TransformationImpl("ellipse", 20,
            0, 0, 10, 30, 50, 50, 50, 40, 40, 40,
            40, 60, 150, 150, 150));

    AnimationControllerImpl c = new AnimationControllerImpl(null, v);
  }

  /**
   * Test the passing of a null view. Should throw an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test003() {
    AnimationModel m = new AnimationModelImpl(0, 100, 200, 300);
    PlaybackViewImpl v = new PlaybackViewImpl(1000,1000, 1000,
            1000, 1000, 1000, 100);
    m.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    m.addShape(new ShapeImpl("ellipse", ShapeType.ELLIPSE, 2));
    m.addTransformation("rectangle", new TransformationImpl("rectangle",
            10, 20, 20, 20, 40, 100, 100, 100, 20, 40,
            40, 40, 60, 200, 200, 200));
    m.addTransformation("ellipse", new TransformationImpl("ellipse", 20,
            0, 0, 10, 30, 50, 50, 50, 40, 40, 40,
            40, 60, 150, 150, 150));

    AnimationControllerImpl c = new AnimationControllerImpl(m, null);
  }

  /**
   * Test creating an empty AnimationModel. Test adding shapes to the animation model. Test adding
   * transformations to the shapes in the animation model. Test getting the shapes in the animation
   * at different ticks in the animation. Test getting total ticks in the animation. Test
   * toString output representation of the animation. Test getMethods in the AnimationModel.
   * Test getting the shapes in the animation.
   */
  @Test
  public void testAnimationModelImpl() {
    AnimationModel m = new AnimationModelImpl(0, 100, 200, 300);
    PlaybackViewImpl v = new PlaybackViewImpl(1000,1000, 1000,
            1000, 1000, 1000, 100);

    AnimationControllerImpl c = new AnimationControllerImpl(m, v);

    assertEquals(0, c.getAnimationModel().getBoundX());
    assertEquals(100, c.getAnimationModel().getBoundY());
    assertEquals(200, c.getAnimationModel().getWindowWidth());
    assertEquals(300, c.getAnimationModel().getWindowHeight());
    assertEquals(0, c.getAnimationModel().getTotalTicks());
    assertEquals(25, c.getAnimationModel().getAnimationHeight());
    assertEquals(25, c.getAnimationModel().getAnimationWidth());

    String test = "Create window with width 200 and height 300 with top left corner "
            + "(0,100) and total ticks 0";

    assertEquals(test, c.getAnimationModel().toString());

    c.getAnimationModel().addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    c.getAnimationModel().addShape(new ShapeImpl("ellipse", ShapeType.ELLIPSE, 2));

    test = "Create window with width 200 and height 300 with top left corner "
            + "(0,100) and total ticks 0";

    assertEquals(test, c.getAnimationModel().toString());
    assertEquals(2, c.getAnimationModel().getShapes().size());
    assertEquals("rectangle", c.getAnimationModel().getShapes().get(0).getName());
    assertEquals("ellipse", c.getAnimationModel().getShapes().get(1).getName());
    assertEquals(25, c.getAnimationModel().getAnimationHeight());
    assertEquals(25, c.getAnimationModel().getAnimationWidth());

    c.getAnimationModel().addTransformation("rectangle",
            new TransformationImpl("rectangle", 10,
            20, -20, 20, 40, 100, 100,
                    100, 20, 40, -40,
            40, 60, 200, 200, 200));
    c.getAnimationModel().addTransformation("ellipse",
            new TransformationImpl("ellipse", 20,
            0, 0, 10, 30, 50, 50, 50, 40, 40, -40,
            40, 60, 150, 150, 150));

    assertEquals(2, c.getAnimationModel().getTransformations().size());
    assertEquals(10, c.getAnimationModel().getTransformations().get(0).getStart());
    assertEquals(20, c.getAnimationModel().getTransformations().get(1).getStart());
    assertEquals(40, c.getAnimationModel().getTotalTicks());

    List<Shape> sList = c.getAnimationModel().getShapesAtTick(20);

    assertEquals("rectangle", sList.get(0).getName());
    assertEquals(40, sList.get(0).getX());
    assertEquals(-40, sList.get(0).getY());
    assertEquals(40, sList.get(0).getWidth());
    assertEquals(60, sList.get(0).getHeight());
    assertEquals(200, sList.get(0).getR());
    assertEquals(200, sList.get(0).getG());
    assertEquals(200, sList.get(0).getB());

    assertEquals("ellipse", sList.get(1).getName());
    assertEquals(0, sList.get(1).getX());
    assertEquals(0, sList.get(1).getY());
    assertEquals(10, sList.get(1).getWidth());
    assertEquals(30, sList.get(1).getHeight());
    assertEquals(50, sList.get(1).getR());
    assertEquals(50, sList.get(1).getG());
    assertEquals(50, sList.get(1).getB());

    test =  "Create window with width 200 and height 300 with top "
            + "left corner (0,100) and total ticks 40\n"
            + "\n"
            + "Create rectangle rectangle with center at (20,-20) with"
            + " width 20 and height 40 and color (100,100,100)\n"
            + "Create ellipse ellipse with center at (0,0) with "
            + "width 10 and height 30 and color (50,50,50)\n"
            + "\n"
            + "rectangle rectangle appears at time t=10 and disappears at time t=20\n"
            + "ellipse ellipse appears at time t=20 and disappears at time t=40\n"
            + "\n"
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

    assertEquals(test, c.getAnimationModel().toString());
  }

  /**
   * Test a controller containing a view of class PlaybackViewImpl.
   * Make sure the view in the controller is correct and valid after controller instantiation.
   * Make sure that the view in the controller runs.
   */
  @Test
  public void test0013() {
    PlaybackViewImpl v = new PlaybackViewImpl(10,10, 100,
            100, 100, 100, 100);
    AnimationModel m = new AnimationModelImpl(0, 0, 100, 100);
    m.addShape(new ShapeImpl("rect", ShapeType.RECTANGLE, 1));
    m.addTransformation("rect", new TransformationImpl("rect",
            0, 10, 10, 10, 10, 255, 255, 255, 50,
            20, 20, 40, 40, 120, 120, 120));
    AnimationControllerImpl c = new AnimationControllerImpl(m, v);
    c.getAnimationView().run(m);
    c.getAnimationView().getRunner().startAnim();
    assertEquals(true, c.getAnimationView().getRunner().isRunning());
  }

  /**
   * Test a controller containing a view of class TextViewImpl.
   * Make sure the view in the controller is correct and valid after controller instantiation.
   * Make sure that the view in the controller runs.
   *
   */
  @Test
  public void testTextViewFileNameConstructor() {
    String path = Paths.get("").toAbsolutePath().toString() + "\\test_out.txt";

    AnimationView t = new TextViewImpl(path);
    AnimationModel m = new AnimationModelImpl(50, 60, 500, 600);
    AnimationControllerImpl c = new AnimationControllerImpl(m, t);
    c.getAnimationView().run(m);

    String test = "Create window with width 500 and height 600 with top left "
            + "corner (50,60) and total ticks 0";

    assertEquals(test, c.getAnimationView().getOutFileContents());

    m.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    m.addShape(new ShapeImpl("ellipse", ShapeType.ELLIPSE, 2));
    AnimationControllerImpl c2 = new AnimationControllerImpl(m, t);
    c2.getAnimationView().run(m);


    test = "Create window with width 500 and height 600 with top left "
            + "corner (50,60) and total ticks 0";

    assertEquals(test, c2.getAnimationView().getOutFileContents());


    m.addTransformation("rectangle", new TransformationImpl("rectangle", 10,
            20, -20, 20, 40, 100, 100, 100, 20, 40, -40,
            40, 60, 200, 200, 200));
    m.addTransformation("ellipse", new TransformationImpl("ellipse", 20,
            0, 0, 10, 30, 50, 50, 50, 40, 40, -40,
            40, 60, 150, 150, 150));
    AnimationControllerImpl c3 = new AnimationControllerImpl(m, t);
    c3.getAnimationView().run(m);

    test =  "Create window with width 500 and height 600 with top "
            + "left corner (50,60) and total ticks 40\n"
            + "\n"
            + "Create rectangle rectangle with center at (20,-20) with"
            + " width 20 and height 40 and color (100,100,100)\n"
            + "Create ellipse ellipse with center at (0,0) with "
            + "width 10 and height 30 and color (50,50,50)\n"
            + "\n"
            + "rectangle rectangle appears at time t=10 and disappears at time t=20\n"
            + "ellipse ellipse appears at time t=20 and disappears at time t=40\n"
            + "\n"
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

    assertEquals(test, c3.getAnimationView().getOutFileContents());
  }

  /**
   * Test a controller containing a view of class TextViewImpl.
   * Make sure the view in the controller is correct and valid after controller instantiation.
   * Make sure that the view in the controller runs.
   * Test creating a TextView that writes to System.out.
   *
   */
  @Test
  public void testTextViewToSystemOut() {
    AnimationModel m = new AnimationModelImpl(50, 60, 500, 600);

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    AnimationView t = new TextViewImpl();

    AnimationControllerImpl c = new AnimationControllerImpl(m, t);
    c.getAnimationView().run(m);

    String test = "Create window with width 500 and height 600 with top left "
            + "corner (50,60) and total ticks 0";

    assertEquals(test, outContent.toString());

    outContent.reset();

    m.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    m.addShape(new ShapeImpl("ellipse", ShapeType.ELLIPSE, 2));

    AnimationControllerImpl c2 = new AnimationControllerImpl(m, t);
    c2.getAnimationView().run(m);

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

    AnimationControllerImpl c3 = new AnimationControllerImpl(m, t);
    c3.getAnimationView().run(m);

    test =  "Create window with width 500 and height 600 with top "
            + "left corner (50,60) and total ticks 40\n"
            + "\n"
            + "Create rectangle rectangle with center at (20,-20) with"
            + " width 20 and height 40 and color (100,100,100)\n"
            + "Create ellipse ellipse with center at (0,0) with "
            + "width 10 and height 30 and color (50,50,50)\n"
            + "\n"
            + "rectangle rectangle appears at time t=10 and disappears at time t=20\n"
            + "ellipse ellipse appears at time t=20 and disappears at time t=40\n"
            + "\n"
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
   * Test a controller containing a view of class SVGViewImpl.
   * Make sure the view in the controller is correct and valid after controller instantiation.
   * Make sure that the view in the controller runs.
   *
   */
  @Test
  public void test00x() {

    // Set outfile path.
    String path = Paths.get("").toAbsolutePath().toString() + "/My_view1.svg";

    // Initialize empty model and test.
    AnimationModel m = new AnimationModelImpl(0, 100, 200, 300);
    AnimationView v = new SVGViewImpl(path, 50);

    AnimationControllerImpl c = new AnimationControllerImpl(m, v);
    c.getAnimationView().run(m);

    String scgContents = "<svg width=\"25\" height=\"25\" version=\"1.1\" xmlns"
            + "=\"http://www.w3.org/2000/svg\"> \n"
            + "\n"
            + "</svg>";
    assertEquals(scgContents, c.getAnimationView().getOutFileContents());

    // Add shapes and test.
    m.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    m.addShape(new ShapeImpl("ellipse", ShapeType.ELLIPSE, 2));

    // Add Transformations and test.
    m.addTransformation("rectangle", new TransformationImpl("rectangle", 10,
            20, 20, 20, 40, 100, 100, 100, 20, 40, 40,
            40, 60, 200, 200, 200));
    m.addTransformation("ellipse", new TransformationImpl("ellipse", 20,
            0, 0, 10, 30, 50, 50, 50, 40, 40, 40,
            40, 60, 150, 150, 150));

    AnimationControllerImpl c3 = new AnimationControllerImpl(m, v);
    c3.getAnimationView().run(m);

    scgContents = "<svg width=\"105\" height=\"125\" version=\"1.1\" xmlns=\"http://ww"
            + "w.w3.org/2000/svg\"> \n"
            + "\n"
            + "<rect id=\"rectangle\" x=\"20\" y=\"20\" width=\"20\" height=\"40\" fi"
            + "ll=\"rgb(100,100,100)\" fill-opacity=\"0.0\" visibility=\"visible\" > \n"
            + "\n"
            + "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"1ms\" "
            + "attributeName=\"fill-opacity\" from=\"0.0\" to=\"1.0\" fill=\"freeze\" />\n"
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
            + ",50,50)\" fill-opacity=\"0.0\" visibility=\"visible\" > \n"
            + "\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1ms\" "
            + "attributeName=\"fill-opacity\" from=\"0.0\" to=\"1.0\" fill=\"freeze\" />\n"
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
    assertEquals(scgContents, c.getAnimationView().getOutFileContents());
  }

  /**
   * Tests simulating a click on the play button. Triggers ButtonListener.
   */
  @Test
  public void testPlayButtonListener() {
    AnimationModel m = new AnimationModelImpl(0, 0, 100, 100);
    m.addShape(new ShapeImpl("name", ShapeType.RECTANGLE, 1));
    m.addTransformation("name", new TransformationImpl("name", 0, 0, 0,
            10, 10, 10, 10, 10, 50, 20, 20, 20, 20,
            20, 20, 20));
    AnimationView v = new PlaybackViewImpl(0, 0, 500, 500, 500, 500, 20);
    AnimationController c = new AnimationControllerImpl(m, v);
    c.doAnimation();
    assertEquals(false, v.getRunner().isRunning());

    ((PlaybackViewImpl) v).getPlayButton().doClick();

    assertEquals(true, v.getRunner().isRunning());

    ((PlaybackViewImpl) v).getPlayButton().doClick();

    assertEquals(false, v.getRunner().isRunning());
  }

  /**
   * Test simulating a click on the loop check box. Triggers ButtonListener.
   */
  @Test
  public void testLoopButtonListener() {
    AnimationModel m = new AnimationModelImpl(0, 0, 100, 100);
    m.addShape(new ShapeImpl("name", ShapeType.RECTANGLE, 1));
    m.addTransformation("name", new TransformationImpl("name", 0, 0, 0,
            10, 10, 10, 10, 10, 50, 20, 20, 20, 20,
            20, 20, 20));
    AnimationView v = new PlaybackViewImpl(0, 0, 500, 500, 500, 500, 20);
    AnimationController c = new AnimationControllerImpl(m, v);
    c.doAnimation();

    assertEquals(false, ((PlaybackViewImpl) v).getLoopBox().isSelected());

    ((PlaybackViewImpl) v).getLoopBox().doClick();

    assertEquals(true, ((PlaybackViewImpl) v).getLoopBox().isSelected());

    ((PlaybackViewImpl) v).getLoopBox().doClick();

    assertEquals(false, ((PlaybackViewImpl) v).getLoopBox().isSelected());
  }

  /**
   * Test simulating a click on the restart button. Triggers ButtonListener.
   */
  @Test
  public void testRestartButtonListener() {
    AnimationModel m = new AnimationModelImpl(0, 0, 100, 100);
    m.addShape(new ShapeImpl("name", ShapeType.RECTANGLE, 1));
    m.addTransformation("name", new TransformationImpl("name", 0, 0, 0,
            10, 10, 10, 10, 10, 50, 20, 20, 20, 20,
            20, 20, 20));
    AnimationView v = new PlaybackViewImpl(0, 0, 500, 500, 500, 500, 20);
    AnimationController c = new AnimationControllerImpl(m, v);
    c.doAnimation();

    ((PlaybackViewImpl) v).getPlayButton().doClick();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    assertEquals(true, ((AnimationRunnerImpl)v.getRunner()).getFrames() > 1);

    ((PlaybackViewImpl) v).getPlayButton().doClick();
    ((PlaybackViewImpl) v).getRestartButton().doClick();

    assertEquals(1, ((AnimationRunnerImpl)v.getRunner()).getFrames());
  }

  /**
   * Test simulating a change on the ticks per second slider. Triggers SliderChangeListener.
   */
  @Test
  public void testSpeedSlider() {
    AnimationModel m = new AnimationModelImpl(0, 0, 100, 100);
    m.addShape(new ShapeImpl("name", ShapeType.RECTANGLE, 1));
    m.addTransformation("name", new TransformationImpl("name", 0, 0, 0,
            10, 10, 10, 10, 10, 50, 20, 20, 20, 20,
            20, 20, 20));
    AnimationView v = new PlaybackViewImpl(0, 0, 500, 500, 500, 500, 20);
    AnimationController c = new AnimationControllerImpl(m, v);
    c.doAnimation();

    assertEquals(0.33, ((AnimationRunnerImpl) v.getRunner()).getTicksPerFrame(), 0.01);

    ((PlaybackViewImpl) v).getSpeedSlider().setValue(50);

    assertEquals(0.833, ((AnimationRunnerImpl) v.getRunner()).getTicksPerFrame(), 0.01);
  }

  /**
   * Test simulating a left mouse click on the shapePanel, playing/pausing the animation. Triggers
   * the TogglePlayMouseListener.
   */
  @Test
  public void testMouseListenerLeftClick() {
    AnimationModel m = new AnimationModelImpl(0, 0, 100, 100);
    m.addShape(new ShapeImpl("name", ShapeType.RECTANGLE, 1));
    m.addTransformation("name", new TransformationImpl("name", 0, 0, 0,
            10, 10, 10, 10, 10, 50, 20, 20, 20, 20,
            20, 20, 20));
    AnimationView v = new PlaybackViewImpl(0, 0, 500, 500, 500, 500, 20);
    AnimationController c = new AnimationControllerImpl(m, v);
    c.doAnimation();

    assertEquals(false, v.getRunner().isRunning());

    ShapePanel p = ((PlaybackViewImpl) v).getShapePanel();
    MouseListener[] mouseListeners = p.getMouseListeners();
    MouseEvent me = new MouseEvent(p, 0, 0, InputEvent.BUTTON1_DOWN_MASK, 100, 100, 1, false);
    mouseListeners[0].mouseClicked(me);

    assertEquals(true, v.getRunner().isRunning());

    mouseListeners[0].mouseClicked(me);

    assertEquals(false, v.getRunner().isRunning());

  }

  /**
   * Tests simulating a right mouse click on shape panel, restarting the animation. Triggers
   * the TogglePlayMouseListener.
   */
  @Test
  public void testMouseListenerRightClick() {
    AnimationModel m = new AnimationModelImpl(0, 0, 100, 100);
    m.addShape(new ShapeImpl("name", ShapeType.RECTANGLE, 1));
    m.addTransformation("name", new TransformationImpl("name", 0, 0, 0,
            10, 10, 10, 10, 10, 50, 20, 20, 20, 20,
            20, 20, 20));
    AnimationView v = new PlaybackViewImpl(0, 0, 500, 500, 500, 500, 20);
    AnimationController c = new AnimationControllerImpl(m, v);
    c.doAnimation();

    ((PlaybackViewImpl) v).getPlayButton().doClick();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    assertEquals(true, ((AnimationRunnerImpl)v.getRunner()).getFrames() > 1);

    ((PlaybackViewImpl) v).getPlayButton().doClick();

    ShapePanel p = ((PlaybackViewImpl) v).getShapePanel();
    MouseListener[] mouseListeners = p.getMouseListeners();
    MouseEvent me = new MouseEvent(p, 0, 0, InputEvent.BUTTON3_DOWN_MASK, 100, 100, 1, false);
    mouseListeners[0].mouseClicked(me);

    assertEquals(1, ((AnimationRunnerImpl)v.getRunner()).getFrames());
  }
}
