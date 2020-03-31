package cs5004.animator.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TextView implements AnimationView {

  private String data;
  private String fileName;
  private Appendable out;

  public TextView(String data, String fileName) {
    this.data = data;
    this.fileName = fileName;
  }

  public TextView(String data) {
    this.data = data;
    fileName = null;
    out = null;
  }

  @Override
  public void openDisplay() throws IOException {
    if (fileName != null) {
      File f = new File(fileName);
      out = new FileWriter(f);
    } else {
      out = System.out;
    }
  }

  @Override
  public void closeDisplay() throws IOException {
    if (out instanceof FileWriter) {
      ((FileWriter) out).write(data);
      ((FileWriter) out).close();
    }
    else {
      out.append(data);
    }
  }
}
