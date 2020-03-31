package cs5004.animator.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class holds the textual view used to write a description of the animation
 * either to a text file or to System.out. Implements the AnimationView interface.
 */

public class TextView implements AnimationView {

  private String data;
  private String fileName;
  private Appendable out = null;

  /**
   * This constructor takes a String containing data to be written to the view
   * and a filename of the text file that the description will be written to.
   *
   * @param data the description to be written to the file.
   * @param fileName the name of the text file to be written to.
   * @throws IllegalArgumentException if the String data or filename arguments are null.
   */
  public TextView(String data, String fileName) throws IllegalArgumentException {
    if (data == null || fileName == null) {
      throw new IllegalArgumentException("String data and filename cannot be null");
    }
    this.data = data;
    this.fileName = fileName;
  }

  /**
   * This constructor takes a String containing data to be written to System.out.
   *
   * @param data is the data to be written to System.out.
   * @throws IllegalArgumentException if the String data argument is null.
   */
  public TextView(String data) throws IllegalArgumentException {
    if (data == null) {
      throw new IllegalArgumentException("String data cannot be null");
    }
    this.data = data;
    fileName = null;
  }

  /**
   * If there is a specified file to be written to, opens file associated with
   * specified fileName and sets file to be written to. Else, sets output to be
   * System.out.
   *
   * @throws IllegalStateException if FileWriter cannot be created.
   */
  @Override
  public void openDisplay() throws IllegalStateException {
    if (fileName != null) {
      File f = new File(fileName);
      try {
        out = new FileWriter(f);
      } catch (IOException e) {
        throw new IllegalStateException("Cannot open FileWriter " + fileName);
      }
    } else {
      out = System.out;
    }
  }

  /**
   * Writes to specified output location and, if is writing to a File, closes the file as
   * well.
   *
   * @throws IllegalStateException if output cannot be written to specified File.
   *                               If File cannot be closed.
   *                               If data cannot be appended to System.out.
   */
  @Override
  public void closeDisplay() throws IllegalStateException {
    if (out instanceof FileWriter) {
      try {
        ((FileWriter) out).write(data);
        ((FileWriter) out).close();
      } catch (IOException e) {
        throw new IllegalStateException("Cannot write to or close FileWriter " + fileName);
      }
    }
    else {
      try {
        out.append(data);
      } catch (IOException e) {
        throw new IllegalStateException("Cannot append to System.out");
      }
    }
  }
}
