import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestAnimationModelImpl {

  @Test
  public void genericTest() {
    AnimationModel m = new AnimationModelImpl();

    m.addShape(new Square("1", 10, 10, 20, 20, Color.BLUE), "1");

    m.addAppearance("1", 5, 20);
    m.addMove("1", 10, 15, 40, 40);

    m.addShape(new Triangle("2", 7, 11, 80, 80, Color.GREEN), "2");

    m.addAppearance("2", 16, 30);
    m.addScale("2", 20, 22, 2);

    assertEquals("", m.getDescription());
  }
}
