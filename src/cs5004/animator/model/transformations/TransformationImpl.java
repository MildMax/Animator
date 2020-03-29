package cs5004.animator.model.transformations;
import cs5004.animator.model.transformations.Transformation;

public class TransformationImpl implements Transformation {
  private String shapeName;
  private int t1;
  private int t2;

  private int x1;
  private int x2;
  private int y1;
  private int y2;

  private int w1;
  private int w2;
  private int h1;
  private int h2;

  private int r1;
  private int r2;
  private int g1;
  private int g2;
  private int b1;
  private int b2;

  public TransformationImpl(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1,
                            int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
                            int b2) {
    this.shapeName = name;
    this.t1 = t1;
    this.t2 = t2;
    this.x1 = x1;
    this.x2 = x2;
    this.y1 = y1;
    this.y2 = y2;
    this.w1 = w1;
    this.w2 = w2;
    this.h1 = h1;
    this.h2 = h2;
    this.r1 = r1;
    this.r2 = r2;
    this.g1 = g1;
    this.g2 = g2;
    this.b1 = b1;
    this.b2 = b2;
  }

  public String getShapeName() {
    return this.shapeName;
  }

  public int getT1() {
    return this.t1;
  }

  public int getT2() {
    return this.t2;
  }

  @Override
  public int getX1() {
    return this.x1;
  }

  @Override
  public int getX2() {
    return this.x2;
  }

  @Override
  public int getY1() {
    return this.y1;
  }

  @Override
  public int getY2() {
    return this.y2;
  }

  @Override
  public int getW1() {
    return this.w1;
  }

  @Override
  public int getW2() {
    return this.w2;
  }

  @Override
  public int getH1() {
    return this.h1;
  }

  @Override
  public int getH2() {
    return this.h2;
  }

  @Override
  public int getR1() {
    return this.r1;
  }

  @Override
  public int getR2() {
    return this.r2;
  }

  @Override
  public int getG1() {
    return this.g1;
  }

  @Override
  public int getG2() {
    return this.g2;
  }

  @Override
  public int getB1() {
    return this.b1;
  }

  @Override
  public int getB2() {
    return this.b2;
  }

  @Override
  public int getStart() {
    return t1;
  }

  @Override
  public int getEnd() {
    return t2;
  }

  @Override
  public String toString() {
    String time = " from time t=" + this.t1 + " to time t=" + this.t2 + "\n";

    String total = "";

    if (x1 != x2 || y1 != y2) {
      total += this.shapeName + " moves from (" + x1 + "," + x2 + ") to (" + x2 + "," + y2
            + ")" + time;
    }

    if (w1 != w2) {
      total += this.shapeName + " changes width from " + w1 + " to " + w2 + time;
    }

    if (h1 != h2) {
      total += this.shapeName + " changes height from " + h1 + " to " + h2 + time;
    }

    if (r1 != r2 || g1 != g2 || b1 != b2) {
      total += this.shapeName + " changes from color (" + r1 + "," + g1 + "," + b1 + ") to color ("
            + r2 + "," + g2 + "," + b2 + ")" + time;
    }

    return total;
  }
}