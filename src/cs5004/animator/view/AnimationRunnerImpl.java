package cs5004.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import cs5004.animator.model.AnimationModel;

/**
 * The AnimationRunnerImpl class keeps track of the ticks in the animation, and holds methods
 * that start the animation, check if the animation is running, adds frames to
 * the animation, and exit the animation. Implements the ActionListener interface.
 */
public class AnimationRunnerImpl implements ActionListener, AnimationRunner {

  private double ticksPerFrame;
  private int frames = 0;
  private AnimationModel model;
  private AnimationView view;
  private Timer timer;
  private int fps = 60;
  private boolean isLooping = false;

  /**
   * Takes an AnimationModel to pull data from, an AnimationView to display the data
   * visually, and a delay in milliseconds to initialize a Timer and play the animation at
   * a specified speed.
   *
   * @param m takes an AnimationModel with shapes to be printed to the screen.
   * @param v takes a View that will display the animation.
   * @param ticksPerSecond specifies the ticks per second of the animation.
   * @throws IllegalArgumentException If the AnimationModel argument is null.
   *                                  If the AnimationView argument is null.
   *                                  If the delay is less than 1.
   */
  public AnimationRunnerImpl(AnimationModel m, AnimationView v, int ticksPerSecond)
          throws IllegalArgumentException {
    if (m == null || v == null) {
      throw new IllegalArgumentException("Model/View cannot be null");
    }
    else if (ticksPerSecond < 1) {
      throw new IllegalArgumentException("Delay cannot be less than 1");
    }

    ticksPerFrame = (double)ticksPerSecond / (double)fps;
    int millisecondsPerFrame = (int) Math.round(1000.0 / (double)fps);

    this.model = m;
    this.view = v;
    this.timer = new Timer(millisecondsPerFrame, this);
  }

  /**
   * Starts the animation. Toggles text on the Play/Pause button upon starting.
   */
  @Override
  public void startAnim() {
    timer.start();
    togglePlayText();
  }

  /**
   * Sets the state of the animation to the first specified tick. Does not play the animation.
   * Toggles the text on the Play/Pause button.
   */
  @Override
  public void restartAnim() {
    this.frames = 1;
    this.view.drawNewFrame(this.model.getShapesAtTick(1));
    togglePlayText();
  }

  /**
   * Toggles the loop setting on the Animation. If the animation is not set to loop, sets the
   * animation to loop. If the animation is set to loop, sets the animation to not loop.
   * Toggles the text on the Play/Pause button.
   */
  @Override
  public void toggleLoop() {
    isLooping = !isLooping;
    if (!timer.isRunning() && isLooping &&
            this.frames * this.ticksPerFrame > this.model.getTotalTicks()) {
      timer.start();
    }
    togglePlayText();
  }

  /**
   * Toggles the state of the timer. If the timer is not running, starts the timer. If the
   * timer is running, stops the timer. Toggles the text on the Play/Pause button.
   */
  @Override
  public void togglePlay() {
    if (timer.isRunning()) {
      timer.stop();
    } else {
      timer.start();
    }
    togglePlayText();
  }

  /**
   * Sets the ticks per second the animation plays at. Throws IllegalArgumentException
   * if the new ticksPerSecond specified is less than or equal to zero.
   *
   * @param ticksPerSecond takes the ticks per second the animation will be played at.
   * @throws IllegalArgumentException if ticksPerSecond is less than or equal to 0.
   */
  @Override
  public void setTicksPerSecond(int ticksPerSecond) throws IllegalArgumentException {
    if (ticksPerSecond <= 0) {
      throw new IllegalArgumentException("ticksPerSecond cannot be less than or equal"
              + "to zero");
    }
    ticksPerSecond = (int)Math.round((double)ticksPerSecond / 10) * 10;
    double newTPF = (double)ticksPerSecond / (double)fps;
    frames = (int)Math.round((frames * ticksPerFrame) / newTPF);
    ticksPerFrame = newTPF;
  }

  /**
   * Returns whether or not the timer in the AnimationRunner is running. Returns true if animation
   * is running, false if not.
   *
   * @return a boolean value indicating whether or not the timer in the AnimationRunner is running.
   */
  @Override
  public boolean isRunning() {
    return timer.isRunning();
  }

  /**
   * Increments frames of animation and displays a frame of the animation on screen.
   *
   * @param e an ActionEvent object.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    ++frames;
    if ((this.frames * this.ticksPerFrame) > this.model.getTotalTicks()) {
      this.view.drawNewFrame(this.model.getShapesAtTick(model.getTotalTicks()));
      if (view instanceof VisualViewImpl) {
        view.closeView();
      }
      else if (isLooping) {
        frames = 1;
      }
      else {
        timer.stop();
        togglePlayText();
      }
      return;
    }
    this.view.drawNewFrame(this.model.getShapesAtTick((double)this.frames * this.ticksPerFrame));
  }

  /**
   * Get ticksPerFrame.
   *
   * @return ticksPerFrame.
   */
  public double getTicksPerFrame() {
    return this.ticksPerFrame;
  }

  /**
   * Get the timer.
   *
   * @return timer.
   */
  public Timer getTimer() {
    return this.timer;
  }

  /**
   * Get the looping status.
   *
   * @return the looping status.
   */
  public boolean getIsLooping() {
    return this.isLooping;
  }

  /**
   *  Get frames.
   *
   * @return frames.
   */
  public int getFrames() {
    return this.frames;
  }

  /**
   * Get fps.
   *
   * @return fps.
   */
  public int getFPS() {
    return this.fps;
  }

  /**
   * Get the view.
   *
   * @return the view.
   */
  public PlaybackViewImpl getView() {
    return (PlaybackViewImpl) this.view;
  }

  /**
   * Get the model.
   *
   * @return the model.
   */
  public AnimationModel getModel() {
    return this.model;
  }

  //calls the togglePlayText method in the PlaybackViewImpl class
  //don't want the buttons exposed, so this method calls an identical method
  //such that the buttons inside the view are not accessible outside of
  //the view. Any other public methods that make the buttons relevant are
  //designed for TESTING PURPOSES ONLY.
  private void togglePlayText() {
    if (view instanceof PlaybackViewImpl) {
      ((PlaybackViewImpl) view).togglePlayText();
    }
  }
}
