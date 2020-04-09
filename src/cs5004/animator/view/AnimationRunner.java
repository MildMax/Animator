package cs5004.animator.view;

/**
 * The AnimationRunner interface declares methods necessary to run
 * and modify an animation.
 */
public interface AnimationRunner {

  /**
   * Starts the animation.
   */
  void startAnim();

  /**
   * Restarts the animation -- does not play the animation if the animator
   * is in a paused state.
   */
  void restartAnim();

  /**
   * Toggles looping. If looping is enabled, disables looping. If looping is
   * disabled, enables looping.
   */
  void toggleLoop();

  /**
   * Toggles whether or not the animator is playing the animation. If the animation
   * is playing, stops the animation. If the animation is stopped, plays the
   * animation. If the animation is over, restarts the animation from the beginning.
   */
  void togglePlay();

  /**
   * Returns whether or not the animation is currently running. Returns true
   * if running, false if stopped.
   *
   * @return a boolean value indicating whether or not the animation
   * is running.
   */
  boolean isRunning();

  /**
   * Sets the ticks per second that the animation will play at.
   *
   * @param ticksPerSecond takes the ticks per second the animation will be playe dat.
   * @throws IllegalArgumentException if ticksPerSecond is less than or equal to 0.
   */
  void setTicksPerSecond(int ticksPerSecond) throws IllegalArgumentException;

}
