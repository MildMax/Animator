package cs5004.animator.view;

public interface AnimationRunner {

  void openWindow();

  void startAnim();

  void pauseAnim();

  void restartAnim();

  void toggleLoop();

  void togglePlay();

  void setTicksPerSecond(int ticksPerSecond);

}
