package cs5004.animator.model.transformations;

public class TransformationImpl {
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

  public TransformationImpl(String name, int t1, int x1, int y1, int w1,
                            int h1, int r1, int g1, int b1, int t2,
                            int x2, int y2, int w2, int h2, int r2,
                            int g2, int b2) {
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
}
