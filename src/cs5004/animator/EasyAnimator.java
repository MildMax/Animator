package cs5004.animator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.AbstractTextView;
import cs5004.animator.view.AnimationView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.VisualView;

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
      view = new VisualView(m.getBoundX(), m.getBoundY(),
              m.getWindowWidth(), m.getWindowHeight());
    }
    else if (e.viewType.compareTo("text") == 0) {
      if (e.outFile != null) {
        view = new TextView(e.outFile);
      } else {
        view = new TextView();
      }
    }
    else if (e.viewType.compareTo("svg") == 0) {
      view = new SVGView(e.outFile);
    }
    else {
      AnimationView.displayErrorMessage("Invalid view type " + e.viewType);
    }

    //run the file
    view.openDisplay();

    if (view instanceof VisualView) {
      ActionListener tickListener = new TickActionListener(m, view, 1000 / e.speed);
      ((TickActionListener)tickListener).start();
      while(((TickActionListener)tickListener).isRunning());
    }
    else {
      view.write(m);
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
            AnimationView.displayErrorMessage("Invalid view type " + viewType);
          }
        } else {
          AnimationView.displayErrorMessage("Invalid command line argument " + args[i]);
        }
      }
    } catch (IndexOutOfBoundsException e) {
      AnimationView.displayErrorMessage("Insufficient number of arguments supplied");
    }
    if (inFile == null || viewType == null) {
      AnimationView.displayErrorMessage("Must supply an in-file and a view-type");
    }
  }
}