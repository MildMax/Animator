import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.shapes.Shape;
import cs5004.animator.model.shapes.ShapeImpl;
import cs5004.animator.model.shapes.ShapeType;
import cs5004.animator.model.transformations.TransformationImpl;
import cs5004.animator.view.AnimationView;
import cs5004.animator.view.SVGViewImpl;

import static org.junit.Assert.assertEquals;

public class TestSVGViewImpl {

  @Test
  public void test001() {

    // Set outfile path.
    String path = Paths.get("").toAbsolutePath().toString() + "\\My_view1.svg";

    // Initialize empty model and test.
    AnimationModel m = new AnimationModelImpl(0, 100, 200, 300);
    AnimationView view1 = new SVGViewImpl(path, 50);
    view1.run(m);
    String SVGcontents = "<svg width=\"25\" height=\"25\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\"> \n" +
            "\n" +
            "</svg>";
    assertEquals(SVGcontents, view1.getOutFileContents());

    // Add shapes and test.
    m.addShape(new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1));
    m.addShape(new ShapeImpl("ellipse", ShapeType.ELLIPSE, 2));
    view1.run(m);
    SVGcontents = "";
    assertEquals("", view1.getOutFileContents());

    // Add Transformations and test.
    m.addTransformation("rectangle", new TransformationImpl("rectangle", 10,
            20, 20, 20, 40, 100, 100, 100, 20, 40, 40,
            40, 60, 200, 200, 200));
    m.addTransformation("ellipse", new TransformationImpl("ellipse", 20,
            0, 0, 10, 30, 50, 50, 50, 40, 40, 40,
            40, 60, 150, 150, 150));
    view1.run(m);
    SVGcontents = "<svg width=\\\"105\\\" height=\\\"125\\\" version=\\\"1.1\\\" xmlns=\\\"http://www.w3.org/2000/svg\\\"> \\n\" +\n" +
            "            \"\\n\" +\n" +
            "            \"<rect id=\\\"rectangle\\\" x=\\\"20\\\" y=\\\"20\\\" width=\\\"20\\\" height=\\\"40\\\" fill=\\\"rgb(100,100,100)\\\" fill-opacity=\\\"1.0\\\" visibility=\\\"visible\\\" > \\n\" +\n" +
            "            \"\\n\" +\n" +
            "            \"<animate attributeType=\\\"xml\\\" begin=\\\"500ms\\\" dur=\\\"500ms\\\" attributeName=\\\"x\\\" from=\\\"20\\\" to=\\\"40\\\" fill=\\\"freeze\\\" /> \\n\" +\n" +
            "            \"\\n\" +\n" +
            "            \"<animate attributeType=\\\"xml\\\" begin=\\\"500ms\\\" dur=\\\"500ms\\\" attributeName=\\\"y\\\" from=\\\"20\\\" to=\\\"40\\\" fill=\\\"freeze\\\" /> \\n\" +\n" +
            "            \"\\n\" +\n" +
            "            \"<animate attributeType=\\\"xml\\\" begin=\\\"500ms\\\" dur=\\\"500ms\\\" attributeName=\\\"height\\\" from=\\\"40\\\" to=\\\"60\\\" fill=\\\"freeze\\\" /> \\n\" +\n" +
            "            \"\\n\" +\n" +
            "            \"<animate attributeType=\\\"xml\\\" begin=\\\"500ms\\\" dur=\\\"500ms\\\" attributeName=\\\"width\\\" from=\\\"20\\\" to=\\\"40\\\" fill=\\\"freeze\\\" /> \\n\" +\n" +
            "            \"\\n\" +\n" +
            "            \"<animate attributeType=\\\"xml\\\" begin=\\\"500ms\\\" dur=\\\"500ms\\\" attributeName=\\\"fill\\\" from=\\\"rgb(100,100,100)\\\" to=\\\"rgb(200,200,200)\\\" fill=\\\"freeze\\\" /> \\n\" +\n" +
            "            \"\\n\" +\n" +
            "            \"</rect> \\n\" +\n" +
            "            \"\\n\" +\n" +
            "            \"<ellipse id=\\\"ellipse\\\" cx=\\\"0\\\" cy=\\\"0\\\" rx=\\\"5\\\" ry=\\\"15\\\" fill=\\\"rgb(50,50,50)\\\" fill-opacity=\\\"1.0\\\" visibility=\\\"visible\\\" > \\n\" +\n" +
            "            \"\\n\" +\n" +
            "            \"<animate attributeType=\\\"xml\\\" begin=\\\"1000ms\\\" dur=\\\"1000ms\\\" attributeName=\\\"cx\\\" from=\\\"0\\\" to=\\\"40\\\" fill=\\\"freeze\\\" /> \\n\" +\n" +
            "            \"\\n\" +\n" +
            "            \"<animate attributeType=\\\"xml\\\" begin=\\\"1000ms\\\" dur=\\\"1000ms\\\" attributeName=\\\"cy\\\" from=\\\"0\\\" to=\\\"40\\\" fill=\\\"freeze\\\" /> \\n\" +\n" +
            "            \"\\n\" +\n" +
            "            \"<animate attributeType=\\\"xml\\\" begin=\\\"1000ms\\\" dur=\\\"1000ms\\\" attributeName=\\\"ry\\\" from=\\\"15\\\" to=\\\"30\\\" fill=\\\"freeze\\\" /> \\n\" +\n" +
            "            \"\\n\" +\n" +
            "            \"<animate attributeType=\\\"xml\\\" begin=\\\"1000ms\\\" dur=\\\"1000ms\\\" attributeName=\\\"rx\\\" from=\\\"5\\\" to=\\\"20\\\" fill=\\\"freeze\\\" /> \\n\" +\n" +
            "            \"\\n\" +\n" +
            "            \"<animate attributeType=\\\"xml\\\" begin=\\\"1000ms\\\" dur=\\\"1000ms\\\" attributeName=\\\"fill\\\" from=\\\"rgb(50,50,50)\\\" to=\\\"rgb(150,150,150)\\\" fill=\\\"freeze\\\" /> \\n\" +\n" +
            "            \"\\n\" +\n" +
            "            \"</ellipse> \\n\" +\n" +
            "            \"\\n\" +\n" +
            "            \"</svg>";
    assertEquals("", view1.getOutFileContents());
  }

}
