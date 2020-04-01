package cs5004.animator.view;

import cs5004.animator.model.AnimationModel;

public class SVGView extends AbstractTextView {

  /**
   * This constructor takes a String containing data to be written to the view and a filename of the
   * text file that the description will be written to.
   *
   * @param fileName the name of the text file to be written to.
   * @throws IllegalArgumentException if the String data or filename arguments are null.
   */
  public SVGView(String fileName) throws IllegalArgumentException {
    super(fileName);
    if (fileName == null) {
      throw new IllegalArgumentException("String fileName cannot be null");
    }
    else if (fileName.length() < 5
            || fileName.substring(fileName.length() - 4).compareTo(".svg") != 0) {
      throw new IllegalArgumentException("Specified file must have a "
              + "valid name and '.svg' extension");
    }
  }

  @Override
  public void write(AnimationModel m) {
    //
  }
}
