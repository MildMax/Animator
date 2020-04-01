package cs5004.animator.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.shapes.Shape;

/**
 * This class holds the textual view used to write a description of the animation
 * either to a text file or to System.out. Implements the AnimationView interface.
 */

public class TextView extends AbstractTextView {

  /**
   * This constructor takes a String containing data to be written to the view
   * and a filename of the text file that the description will be written to.
   *
   * @param fileName the name of the text file to be written to.
   * @throws IllegalArgumentException if the String data or filename arguments are null.
   */
  public TextView(String fileName) throws IllegalArgumentException {
    super (fileName);
    if (fileName == null) {
      throw new IllegalArgumentException("String data and filename cannot be null");
    }
    else if (fileName.length() < 5
            || fileName.substring(fileName.length() - 4).compareTo(".txt") != 0) {
      throw new IllegalArgumentException("Specified file must have a "
              + "valid name and '.txt' extension");
    }
  }

  /**
   * This constructor passes a null value for the fileName.
   */
  public TextView() {
    super(null);
  }

  @Override
  public void write(AnimationModel m) {
    if (out instanceof FileWriter) {
      try {
        ((FileWriter) out).write(m.toString());
      } catch (IOException e) {
        throw new IllegalStateException("Cannot write to FileWriter " + fileName);
      }
    }
    else {
      try {
        out.append(m.toString());
      } catch (IOException e) {
        throw new IllegalStateException("Cannot append to System.out");
      }
    }
  }

}
