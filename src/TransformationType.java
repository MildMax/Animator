public enum TransformationType {
  APPEARANCE("appearance"),
  CHANGECOLOR("change color"),
  CHANGEHEIGHT("change height"),
  CHANGETRANSPARENCY("change transparency"),
  CHANGEWIDTH("change width"),
  MOVE("move");

  private String name;

  TransformationType(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
