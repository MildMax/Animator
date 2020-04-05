package cs5004.animator;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.AnimationView;
import cs5004.animator.view.IVisualViewImpl;
import cs5004.animator.view.SVGViewImpl;
import cs5004.animator.view.TextViewImpl;
import cs5004.animator.view.VisualViewImpl;

/**
 * Plays the animation and manages interaction between the view and the model.
 */
public final class EasyAnimator {

  private Readable inFile = null;
  private String outFile = null;
  private String viewType = null;
  private int ticksPerSecond = 1;
  private int delay = 1000;

  /**
   * Plays the animation according to supplied command line arguments. Displays error messages
   * to the screen for bad input to command line arguments. Creates AnimationModel and
   * AnimationView as specified by command line arguments.
   *
   * @param args command line arguments specifying the type of view, the speed of the animation,
   *             the input file, and the output file.
   */
  public static void main(String[] args) {
    //create the animator
    EasyAnimator e = new EasyAnimator();
    //parse commandLineArguments
    e.parseArgs(args);
    //set up the model
    AnimationModel model = AnimationReader.parseFile(e.inFile, new AnimationModelImpl.Builder());
    //set up the view
    AnimationView view = e.initializeView(model);

    //populate display/view
    view.run(model);
  }

  private void parseArgs(String[] args) {
    try {
      for (int i = 0; i < args.length; i += 2) {
        if (args[i].toLowerCase().compareTo("-in") == 0) {
          File f = new File(args[i + 1]);
          try {
            inFile = new FileReader(f);
          } catch (IOException e) {
            AnimationView.displayErrorMessage("Could not open specified input file");
          }
        } else if (args[i].toLowerCase().compareTo("-out") == 0) {
          outFile = args[i + 1];
        } else if (args[i].toLowerCase().compareTo("-speed") == 0) {
          try {
            int speedInt = Integer.parseInt(args[i + 1]);
            if (speedInt < 1) {
              AnimationView.displayErrorMessage("Invalid non-positive speed value: " + args[i + 1]);
            }
            this.delay = 1000 / speedInt;
            this.ticksPerSecond = speedInt;
          } catch (NumberFormatException e) {
            AnimationView.displayErrorMessage("Invalid integer speed value: " + args[i + 1]);
          }
        } else if (args[i].compareTo("-view") == 0) {
          viewType = args[i + 1];
          if (viewType.toLowerCase().compareTo("svg") != 0
                  && viewType.toLowerCase().compareTo("text") != 0
              && viewType.toLowerCase().compareTo("visual") != 0
              && viewType.toLowerCase().compareTo("playback") != 0) {
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
      view = new VisualViewImpl(m.getBoundX(), m.getBoundY(),
              m.getWindowWidth(), m.getWindowHeight(),
              m.getAnimationWidth(), m.getAnimationHeight(), this.ticksPerSecond);
    }
    else if (viewType.compareTo("playback") == 0) {
      view = new IVisualViewImpl(m.getBoundX(), m.getBoundY(),
              m.getWindowWidth(), m.getWindowHeight(),
              m.getAnimationWidth(), m.getAnimationHeight(), this.ticksPerSecond);
    }
    else if (viewType.compareTo("text") == 0) {
      if (outFile != null) {
        view = new TextViewImpl(outFile);
      } else {
        view = new TextViewImpl();
      }
    }
    else if (viewType.compareTo("svg") == 0) {
      view = new SVGViewImpl(outFile, delay);
    }
    else {
      AnimationView.displayErrorMessage("Invalid view type " + viewType);
    }
    return view;
  }
}