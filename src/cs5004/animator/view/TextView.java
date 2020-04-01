package cs5004.animator.view;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import cs5004.animator.model.AnimationModel;

/**
 * This class holds the textual view used to write a description of the animation
 * either to a text file or to System.out. Implements the AnimationView interface.
 */

public class TextView extends AbstractTextView {

  /**
   * This constructor takes a String containing the filename of the text file that
   * the description of the animation will be written to.
   *
   * @param fileName the name of the text file to be written to.
   * @throws IllegalArgumentException if the String fileName arguments is null.
   *                                  If the specified fileName does not end with '.txt' or
   *                                  does not contain a valid name.
   */
  public TextView(String fileName) throws IllegalArgumentException {
    super (fileName);
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
   * This constructor passes a null value for the fileName.
   */
  public TextView() {
    super(null);
  }

  /**
   * Writes a String representation of an AnimationModel to a text file if one has been
   * specified in command line arguments. Otherwise, prints String representation of an
   * AnimationModel to System.out.
   *
   * @param m takes an AnimationModel that stores an animation.
   * @throws IllegalArgumentException if the AnimationModel is null.
   * @throws IllegalStateException if the FileWriter cannot be written to.
   *                               If System.out cannot be written to.
   *                               If an out file is specified but has not been opened.
   */
  @Override
  public void write(AnimationModel m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("AnimationModel cannot be null");
    }
    if (out instanceof FileWriter) {
      try {
        ((FileWriter) out).write(m.toString());
      } catch (IOException e) {
        throw new IllegalStateException("Cannot write to FileWriter " + fileName);
      }
    }
    else if (out != null) {
      throw new IllegalStateException("Out file has not been initialized for writing");
    }
    else {
      try {
        out.append(m.toString());
      } catch (IOException e) {
        throw new IllegalStateException("Cannot append to System.out");
      }
    }
  }

  /**
   * Returns a String with the contents written to the speicifed out file
   * in the current TextView. Used for testing purposes.
   *
   * @return a String with the contents written to the specified out file
   *         in the current TextView.
   */
  public String getOutFileContents() {
    FileReader r;
    try {
      r = new FileReader(fileName);
    } catch (IOException e) {
      throw new IllegalStateException("Cannot open file at destination");
    }
    String fileContents = "";
    Scanner s = new Scanner(r);
    while (s.hasNextLine()) {
      fileContents += s.nextLine() + "\n";
    }
    try {
      r.close();
    } catch (IOException e) {
      throw new IllegalStateException("Cannot close file at destination");
    }
    return fileContents.trim();
  }

}
