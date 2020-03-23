public enum Color {
  RED("red"),
  ORANGE("orange"),
  YELLOW("yellow"),
  GREEN("green"),
  BLUE("blue"),
  PURPLE("purple"),
  WHITE("white"),
  BLACK("black");

  private String name;

  Color(String name) {
    this.name = name;
  }


  @Override
  public String toString() {
    return this.name;
  }
}
