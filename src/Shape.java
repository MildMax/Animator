import java.util.List;

/**
 * Create an interface that creates a generic shape.
 */
public interface Shape {

  void addTransformation(Transformation t);

  List<Transformation> getTransformationList();

}
