import org.junit.Test;
import static org.junit.Assert.assertEquals;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.shapes.ShapeImpl;
import cs5004.animator.model.shapes.ShapeType;
import cs5004.animator.model.transformations.TransformationImpl;

public class TestAnimationModelImpl {

  @Test
  public void genericTest() {
    AnimationModel m = new AnimationModelImpl(10, 10, 10, 10);
    m.addShape(new ShapeImpl("R", ShapeType.RECTANGLE));
    m.addTransformation("R", new TransformationImpl("R", 0, 0, 10, 10,
            10, 50, 50, 50, 20, 100, 100, 20, 20, 100, 100, 100));

    m.addTransformation("R", new TransformationImpl("R", 20, 5, 5, 5, 5, 5, 5, 5,
            20, 5, 5, 5, 5,5, 5, 5));

    m.addShape(new ShapeImpl("Q", ShapeType.RECTANGLE));

    m.addTransformation("Q", new TransformationImpl("Q", 10, 5, 5, 5, 5, 5, 5, 5,
            20, 5, 5, 5, 5,5, 5, 10));

    assertEquals("", m.toString());
  }

}
