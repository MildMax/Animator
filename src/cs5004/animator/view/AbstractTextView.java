package cs5004.animator.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cs5004.animator.model.AnimationModel;

public abstract class AbstractTextView implements TextualView {

  protected String fileName;
  protected Appendable out = null;

  /**
   * This constructor takes a String containing data to be written to the view
   * and a filename of the text file that the description will be written to.
   *
   * @param fileName the name of the text file to be written to.
   * @throws IllegalArgumentException if the String data or filename arguments are null.
   */
  public AbstractTextView(String fileName) throws IllegalArgumentException {
    this.fileName = fileName;
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
        ((FileWriter) out).close();
      } catch (IOException e) {
        throw new IllegalStateException("Cannot close FileWriter " + fileName);
      }
    }
  }

}
