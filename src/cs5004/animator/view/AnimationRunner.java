package cs5004.animator.view;

public interface AnimationRunner {

  void openWindow();

  void startAnim();

  void restartAnim();

  void toggleLoop();

  void togglePlay();

  boolean isRunning();

  void setTicksPerSecond(int ticksPerSecond);

}
