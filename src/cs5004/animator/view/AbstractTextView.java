package cs5004.animator.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.swing.event.ChangeListener;

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
  public AbstractTextView(String fileName) {
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
    if (out instanceof FileWriter) {
      try {
        ((FileWriter) out).close();
      } catch (IOException e) {
        throw new IllegalStateException("Cannot close FileWriter " + fileName);
      }
    }
  }

  /**
   * Returns a String with the contents written to the specified out file
   * in the current TextView. Used for testing purposes.
   *
   * @return a String with the contents written to the specified out file
   *         in the current TextView.
   * @throws IllegalStateException if the specified file in fileName cannot be opened.
   *                               If the specified file in fileName cannot be closed.
   *                               If there is no specified file by fileName.
   */
  public String getOutFileContents() throws IllegalStateException {
    if (fileName == null) {
      throw new IllegalStateException("No specified fileName exists");
    }
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

  /**
   * Is overridden and nullified -- textual views do not support drawing frames.
   *
   * @param shapeList takes a list of shapes to be drawn to the window.
   * @throws UnsupportedOperationException text view does not support drawing frames.
   */
  @Override
  public void drawNewFrame(List<Shape> shapeList) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Text-based outputs do not support drawing frames");
  }

  /**
   * Is overridden and nullified -- textual views do not utilize an AnimationRunner.
   *
   * @throws UnsupportedOperationException text view does not utilize an AnimationRunner.
   */
  @Override
  public AnimationRunnerImpl getRunner() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Text views do not support getting runner");
  }

  /**
   * Is overridden and nullified -- textual views do not utilize CommandListeners.
   *
   * @param e takes an ActionListener to be assigned to buttons in a view.
   * @throws UnsupportedOperationException text view does not utilize CommandListeners.
   */
  @Override
  public void setCommandListener(ActionListener e) {
    throw new UnsupportedOperationException("Text views do not require command listener");
  }

  /**
   * Is overridden and nullified -- textual views do not utilize ChangeListeners.
   *
   * @param e the ChangeListener to be set to the TicksPerSecondSlider in the Playback view.
   * @throws UnsupportedOperationException text views do not utilize ChangeListeners.
   */
  @Override
  public void setChangeListener(ChangeListener e) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Text views do not utilize ChangeListeners");
  }

  /**
   * Is overridden and nullified -- textual views do not utilize MouseListeners.
   *
   * @param listener takes a MouseListener to be assigned to the AnimationView window
   * @throws UnsupportedOperationException text view does not utilize MouseListeners.
   */
  @Override
  public void setMouseListener(MouseListener listener) {
    throw new UnsupportedOperationException("Text views do not require mouse listener");
  }
}
