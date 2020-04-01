package cs5004.animator.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.shapes.Shape;

/**
 * This class defines the methods that should be implemented by all views that display text.
 * Implements the AnimationView interface.
 */
public abstract class AbstractTextView implements AnimationView {

  protected String fileName;
  protected Appendable out = System.out;

  /**
   * Takes a String containing the filename of the file that the description will be written to.
   *
   * @param fileName the name of the text file to be written to.
   */
  public AbstractTextView(String fileName) throws IllegalArgumentException {
    this.fileName = fileName;
  }

  /**
   * If there is a specified file to be written to, opens file associated with
   * fileName specified in constructor and sets file to be written to.
   *
   * @throws IllegalStateException if FileWriter cannot be created.
   */
  @Override
  public void openView() throws IllegalStateException {
    if (fileName != null) {
      File f = new File(fileName);
      try {
        out = new FileWriter(f);
      } catch (IOException e) {
        throw new IllegalStateException("Cannot open FileWriter " + fileName);
      }
    }
  }

  /**
   * If writing to a File, closes the file.
   *
   * @throws IllegalStateException If File cannot be closed.
   */
  @Override
  public void closeView() throws IllegalStateException {
    if (out != null && out instanceof FileWriter) {
      try {
        ((FileWriter) out).close();
      } catch (IOException e) {
        throw new IllegalStateException("Cannot close FileWriter " + fileName);
      }
    }
  }

  /**
   * Is overridden and nullified -- textual views do not support drawing frames.
   *
   * @param shapeList takes a list of shapes to be drawn to the window.
   * @throws IllegalArgumentException
   */
  @Override
  public void drawNewFrame(List<Shape> shapeList) throws IllegalArgumentException {
    throw new IllegalStateException("Text-based outputs do not support drawing frames");
  }
}
