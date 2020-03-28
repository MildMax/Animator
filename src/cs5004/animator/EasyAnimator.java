package cs5004.animator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public final class EasyAnimator {

  Readable inFile = null;
  Appendable outFile = System.out;
  String viewType = null;
  int speed = 1;


  public static void main(String[] args) throws IOException {
    // FILL IN HERE
    //create model <--- this is where our builder is
    //create a view
    //stringreader <-- stringreader.staticFunc(file, model.builder);
    EasyAnimator e = new EasyAnimator();
    e.parseArgs(args);
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
          if (viewType.compareTo("svg") != 0 || viewType.compareTo("text") == 0
              || viewType.compareTo("visual") != 0) {
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
}