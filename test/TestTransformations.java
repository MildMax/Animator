import org.junit.Test;

import java.sql.SQLClientInfoException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestTransformations {

  //CREATING TRANSFORMATIONS
  //EXCEPTIONS
  //GET METHODS
  //APPEARANCE
  @Test
  public void testAppearanceConstructor() {
    Transformation t = new Appearance(10, 15);
    Shape s = new Square("square", 10, 10, 10, 10, Color.WHITE);

    assertEquals(10, t.getStart());
    assertEquals(15, t.getEnd());
    assertEquals("", t.getDescription("@"));
  }
  //MOVE
  //CHANGEWIDTH
  //CHANGEHEIGHT
  //SCALE
  //CHANGECOLOR
  //CHANGETRANSPARENCY


}
