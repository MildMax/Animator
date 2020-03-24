import org.junit.Test;

import java.sql.SQLClientInfoException;
import java.util.Collections;

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

    m.addTransformation("1", new Move(10, 15, 40, 40));
    m.addTransformation("1", new Appearance(5, 20));

    m.addShape(new Triangle("2", 7, 11, 80, 80, 80, Color.GREEN), "2");

    m.addTransformation("2", new Scale(20, 22, 2));
    m.addTransformation("2", new Appearance(16, 30));

    m.addTransformation("1", new Move(16, 20, 10, 10));
    //this throws IllegalArgumentException for occupying same space
    //m.addTransformation("1", new Move(15, 17, 25, 25));


    //m.addMove("3", 24, 43, 20, 30);

    assertEquals("", m.toString());
  }

  //TEST LIST//



  //CREATING MODEL
    //EXCEPTIONS
      //ADDING SHAPES
      //ADDING TRANSFORMATIONS
    //FUNCTIONALITY <-- TEST GET DESCRIPTION HERE AS WELL
      //ADDING SHAPES
      //ADDING TRANSFORMATIONS
}
