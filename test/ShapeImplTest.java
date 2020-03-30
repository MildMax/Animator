import org.junit.Test;

import cs5004.animator.model.shapes.Shape;
import cs5004.animator.model.shapes.ShapeImpl;
import cs5004.animator.model.shapes.ShapeType;

import static org.junit.Assert.assertEquals;

public class ShapeImplTest {

  @Test
  public void testShapeImplConstructor() {
    Shape s = new ShapeImpl("rectangle", ShapeType.RECTANGLE, 1);

    assertEquals("rectangle", s.getName());
    assertEquals(0, s.getWidth());
    assertEquals(0, s.getHeight());
    assertEquals(0, s.getR());
    assertEquals(0, s.getG());
    assertEquals(0, s.getB());
    assertEquals(0, s.getX());
    assertEquals(0, s.getY());
    assertEquals(0, s.getStart());
    assertEquals(0, s.getWidth());
    assertEquals(ShapeType.RECTANGLE, s.getType());
    assertEquals(1, s.getLayer());
    //assertEquals("rectangle ");

  }

  @Test(expected = IllegalArgumentException.class)
  public void testShapeImplNullName() {
    new ShapeImpl(null, ShapeType.RECTANGLE, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShapeImplNullType() {
    new ShapeImpl("name", null, 0);
  }
}
