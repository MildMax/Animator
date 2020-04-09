package cs5004.animator.view;

import java.io.FileWriter;
import java.io.IOException;

import cs5004.animator.model.AnimationModel;

/**
 * This class holds the textual view used to write a description of the animation
 * either to a text file or to System.out. Extends the AbstractTextView class which
 * implements the AnimationView interface.
 */

public class TextViewImpl extends AbstractTextView {

  /**
   * This constructor takes a String containing the filename of the text file that
   * the description of the animation will be written to. Filename must end in .'txt' and
   * contain valid characters.
   *
   * @param fileName the name of the text file to be written to.
   * @throws IllegalArgumentException if the String fileName argument is null.
   *                                  If the specified fileName does not end with '.txt' or
   *                                  does not contain a valid name.
   */
  public TextViewImpl(String fileName) throws IllegalArgumentException {
    super(fileName);
    if (fileName == null) {
      throw new IllegalArgumentException("String fileName cannot be null");
    }
    else if (fileName.length() < 5
            || fileName.substring(fileName.length() - 4).compareTo(".txt") != 0) {
      throw new IllegalArgumentException("Specified file must have a "
              + "valid name and '.txt' extension");
    }
  }

  /**
   * This constructor passes a null value for the fileName. Will instantiate
   * out to be System.out.
   */
  public TextViewImpl() {
    super(null);
  }

  /**
   * Writes a String representation of an AnimationModel to a text file if one has been
   * specified in command line arguments. Otherwise, prints String representation of an
   * AnimationModel to System.out.
   *
   * @param m takes an AnimationModel that stores an animation.
   * @throws IllegalArgumentException if the AnimationModel is null.
   * @throws IllegalStateException if the out file cannot be written to.
   *                               If System.out cannot be written to.
   */
  @Override
  public void run(AnimationModel m)
          throws IllegalArgumentException , IllegalStateException {
    if (m == null) {
      throw new IllegalArgumentException("AnimationModel cannot be null");
    }
    openView();
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
    closeView();
  }

}
