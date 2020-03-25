import org.junit.Test;

import cs5004Animator.AnimationModel;
import cs5004Animator.AnimationModelImpl;
import cs5004Animator.Transformations.Appearance;
import cs5004Animator.Color;
import cs5004Animator.Transformations.Move;
import cs5004Animator.Transformations.Scale;
import cs5004Animator.Shapes.Square;
import cs5004Animator.Shapes.Triangle;
import cs5004Animator.Transformations.TransformationType;

import static org.junit.Assert.assertEquals;

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


    m.removeTransformation("1", TransformationType.MOVE, 10, 15);
    //m.removeShape("2");
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
