package cs5004.animator;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.AnimationView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.VisualView;

/**
 * Plays the animation and manages interaction between the view and the model.
 */
public final class EasyAnimator {

  Readable inFile = null;
  String outFile = null;
  String viewType = null;
  int speed = 1;

  /**
   * Plays the animation according to supplied command line arguments.
   *
   * @param args command line arguments specifying the type of view, the
   */
  public static void main(String[] args) {
    //create the animator
    EasyAnimator e = new EasyAnimator();
    //parse commandLineArguments
    e.parseArgs(args);
    //set up the model
    AnimationModel m = AnimationReader.parseFile(e.inFile, new AnimationModelImpl.Builder());
    //set up the view
    AnimationView view = e.initializeView(m);

    //open the file/display
    view.openDisplay();

    //populate display/view
    if (view instanceof VisualView) {
      ActionListener animRunner = new AnimationRunner(m, view, 1000 / e.speed);
      ((AnimationRunner)animRunner).runAnim();
    }
    else {
      view.write(m);
    }

    //close the file/display
    view.closeDisplay();

    System.exit(0);
  }

  private void parseArgs(String[] args) {
    try {
      for (int i = 0; i < args.length; i += 2) {
        if (args[i].compareTo("-in") == 0) {
          File f = new File(args[i + 1]);
          try {
            inFile = new FileReader(f);
          } catch (IOException e) {
            System.out.println("Could not open specified input file");
            e.getStackTrace();
          }
        } else if (args[i].compareTo("-out") == 0) {
          outFile = args[i + 1];
        } else if (args[i].compareTo("-speed") == 0) {
          try {
            this.speed = Integer.parseInt(args[i + 1]);
          } catch (NumberFormatException e) {
            AnimationView.displayErrorMessage("Invalid speed value: " + args[i + 1]);
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

  private AnimationView initializeView(AnimationModel m) {
    AnimationView view = null;
    if (viewType.compareTo("visual") == 0) {
      view = new VisualView(m.getBoundX(), m.getBoundY(),
              m.getWindowWidth(), m.getWindowHeight(),
              m.getAnimationWidth(), m.getAnimationHeight());
    }
    else if (viewType.compareTo("text") == 0) {
      if (outFile != null) {
        view = new TextView(outFile);
      } else {
        view = new TextView();
      }
    }
    else if (viewType.compareTo("svg") == 0) {
      view = new SVGView(outFile);
    }
    else {
      AnimationView.displayErrorMessage("Invalid view type " + viewType);
    }
    return view;
  }
}