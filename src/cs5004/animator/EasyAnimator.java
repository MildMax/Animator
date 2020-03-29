package cs5004.animator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Scanner;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.shapes.Shape;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.AnimationViewImpl;

public final class EasyAnimator {

  Readable inFile = null;
  Appendable outFile = System.out;
  String viewType = null;
  int speed = 1;


  public static void main(String[] args) throws IOException, InterruptedException {
    // FILL IN HERE
    //create model <--- this is where our builder is
    //create a view
    //stringreader <-- stringreader.staticFunc(file, model.builder);

    //create the animator
    EasyAnimator e = new EasyAnimator();

    //parse arguments and create model
    e.parseArgs(args);
    AnimationModel m = AnimationReader.parseFile(e.inFile, new AnimationModelImpl.Builder());

    //write out put to file
    //TODO - write output to specific file type (svg, text, some other one)
    e.writeToOutFile(m.toString());

    //set up the view
    AnimationViewImpl v = new AnimationViewImpl();
    v.setFrameSize(m.getWindowWidth(), m.getWindowHeight());
    v.setFrameLocation(m.getBoundX(), m.getBoundY());
    v.displayFrame();

    System.out.println(v.getPreferredSize().width + " : " + v.getPreferredSize().height);

    //draw the animation
    //TODO -- set ticks relative to speed
    int sleepTime = 1000 / e.speed;
    int ticks = 0;
    while (ticks < m.getTotalTicks()) {
      //control animation flow here
      List<Shape> sList = m.getShapesAtTick(ticks);
      v.drawNewFrame(sList);
      ++ticks;
      Thread.sleep(sleepTime);
    }

    v.closeFrame();

    return;
  }

  private void parseArgs(String[] args) throws IOException {
    try {
      for (int i = 0; i < args.length; i += 2) {
        if (args[i].compareTo("-in") == 0) {
          File f = new File(args[i + 1]);
          inFile = new FileReader(f);
        } else if (args[i].compareTo("-out") == 0) {
          File f = new File(args[i + 1]);
          outFile = new FileWriter(f);
        } else if (args[i].compareTo("-speed") == 0) {
          try {
            this.speed = Integer.parseInt(args[i + 1]);
          } catch (NumberFormatException e) {
            System.out.println("specified speed is not an int, setting speed to 1");
          }
        } else if (args[i].compareTo("-view") == 0) {
          viewType = args[i + 1];
          if (viewType.compareTo("svg") != 0 && viewType.compareTo("text") != 0
              && viewType.compareTo("visual") != 0) {
            throw new IllegalArgumentException("Invalid view type " + viewType);
          }
        } else {
          throw new IllegalArgumentException("Invalid command line argument " + args[i]);
        }
      }
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Insufficient number of arguments supplied");
    }
    if (inFile == null || viewType == null) {
      throw new IllegalArgumentException("must supply infile and a viewtype");
    }
  }

  private void writeToOutFile(String str) throws IOException {
    if (outFile instanceof FileWriter) {
      ((FileWriter) outFile).write(str);
      ((FileWriter) outFile).close();
    }
    else {
      outFile.append(str);
    }
  }
}