package cs5004.animator;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.shapes.Shape;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.AnimationView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.VisualView;
import cs5004.animator.view.VisualViewImpl;

public final class EasyAnimator {

  Readable inFile = null;
  String outFile = null;
  String viewType = null;
  int speed = 1;


  public static void main(String[] args) throws IOException, InterruptedException {
    //create the animator
    EasyAnimator e = new EasyAnimator();
    //parse commandLineArguments
    e.parseArgs(args);
    //set up the model
    AnimationModel m = AnimationReader.parseFile(e.inFile, new AnimationModelImpl.Builder());
    //set up the view
    AnimationView view = null;

    if (e.viewType.compareTo("visual") == 0) {
      view = new VisualViewImpl(m.getBoundX(), m.getBoundY(),
              m.getWindowWidth(), m.getWindowHeight());
    }
    else if (e.viewType.compareTo("text") == 0) {
      if (e.outFile != null) {
        view = new TextView(m.toString(), e.outFile);
      } else {
        view = new TextView(m.toString());
      }
    }
    else if (e.viewType.compareTo("svg") == 0) {
      //initialize view here
    }
    else {
      VisualView.displayErrorMessage("Invalid view type " + e.viewType);
    }

    //run the file
    view.openDisplay();

    if (view instanceof VisualView) {
      VisualView v = (VisualView) view;

      //run the animation
      int sleepTime = 1000 / e.speed;
      int ticks = 0;
      while (ticks < m.getTotalTicks()) {
        //control animation flow here
        List<Shape> sList = m.getShapesAtTick(ticks);
        v.drawNewFrame(sList);
        ++ticks;
        Thread.sleep(sleepTime);
      }
    }

    view.closeDisplay();

    System.exit(0);
  }

  private void parseArgs(String[] args) throws IOException {
    try {
      for (int i = 0; i < args.length; i += 2) {
        if (args[i].compareTo("-in") == 0) {
          File f = new File(args[i + 1]);
          inFile = new FileReader(f);
        } else if (args[i].compareTo("-out") == 0) {
          outFile = args[i + 1];
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
            VisualView.displayErrorMessage("Invalid view type " + viewType);
          }
        } else {
          VisualView.displayErrorMessage("Invalid command line argument " + args[i]);
        }
      }
    } catch (IndexOutOfBoundsException e) {
      VisualView.displayErrorMessage("Insufficient number of arguments supplied");
    }
    if (inFile == null || viewType == null) {
      VisualView.displayErrorMessage("Must supply an in-file and a view-type");
    }
  }
}