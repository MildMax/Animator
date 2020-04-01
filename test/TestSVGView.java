import org.junit.Test;

import java.util.List;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.shapes.Shape;
import cs5004.animator.model.shapes.ShapeImpl;
import cs5004.animator.model.shapes.ShapeType;
import cs5004.animator.model.transformations.TransformationImpl;
import cs5004.animator.view.SVGView;

import static org.junit.Assert.assertEquals;

public class TestSVGView {

  @Test
  public void test001() {

    AnimationModel m = new AnimationModelImpl(0, 100, 200, 300);

    assertEquals(0, m.getBoundX());
    assertEquals(100, m.getBoundY());
    assertEquals(200, m.getWindowWidth());
    assertEquals(300, m.getWindowHeight());
    assertEquals(0, m.getTotalTicks());

    String test = "Create window with width 200 and height 300 with top left corner "
            + "(0,100) and total ticks 0";

    assertEquals(test, m.toString());

    m.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    m.addShape(new ShapeImpl("ellipse", ShapeType.ELLIPSE, 2));

    test = "Create window with width 200 and height 300 with top left corner "
            + "(0,100) and total ticks 0\n\n"
            + "rectangle rectangle appears at time t=0 and disappears at time t=0\n"
            + "ellipse ellipse appears at time t=0 and disappears at time t=0";

    assertEquals(test, m.toString());
    assertEquals(2, m.getShapes().size());
    assertEquals("rectangle", m.getShapes().get(0).getName());
    assertEquals("ellipse", m.getShapes().get(1).getName());

    m.addTransformation("rectangle", new TransformationImpl("rectangle", 10,
            20, 20, 20, 40, 100, 100, 100, 20, 40, 40,
            40, 60, 200, 200, 200));
    m.addTransformation("ellipse", new TransformationImpl("ellipse", 20,
            0, 0, 10, 30, 50, 50, 50, 40, 40, 40,
            40, 60, 150, 150, 150));

    assertEquals(2, m.getTransformations().size());
    assertEquals(10, m.getTransformations().get(0).getStart());
    assertEquals(20, m.getTransformations().get(1).getStart());
    assertEquals(40, m.getTotalTicks());

    List<Shape> sList = m.getShapesAtTick(20);

    assertEquals("rectangle", sList.get(0).getName());
    assertEquals(40, sList.get(0).getX());
    assertEquals(40, sList.get(0).getY());
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

    test = "Create window with width 200 and height 300 with top left corner (0,100) and total ticks 40\n" +
            "\n" +
            "rectangle rectangle appears at time t=10 and disappears at time t=20\n" +
            "ellipse ellipse appears at time t=20 and disappears at time t=40\n" +
            "\n" +
            "rectangle moves from (20,20) to (40,40) from time t=10 to time t=20\n" +
            "rectangle changes width from 20 to 40 from time t=10 to time t=20\n" +
            "rectangle changes height from 40 to 60 from time t=10 to time t=20\n" +
            "rectangle changes from color (100,100,100) to color (200,200,200) from time t=10 to time t=20\n" +
            "ellipse moves from (0,0) to (40,40) from time t=20 to time t=40\n" +
            "ellipse changes width from 10 to 40 from time t=20 to time t=40\n" +
            "ellipse changes height from 30 to 60 from time t=20 to time t=40\n" +
            "ellipse changes from color (50,50,50) to color (150,150,150) from time t=20 to time t=40";

    assertEquals(test, m.toString());

    SVGView view1 = new SVGView("C:\\Users\\WilliC13\\Documents\\CS Masters\\CS 5004 Object Oriented Design\\Homeworks\\Homework07 GitHub V2\\Animator\\view1.svg", 1);
    view1.run(m);


  }

}
