package cs5004.animator.controller;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cs5004.animator.view.AnimationView;
import cs5004.animator.view.PlaybackViewImpl;

/**
 * The SliderChangeListener listens for changes in the state of a slider specifying ticks per
 * second in an animation and reflects those changes in a specified AnimationView.
 */
public class SliderChangeListener implements ChangeListener {

  private AnimationView view;

  /**
   * Takes an AnimationView object that contains the slider that the current class is
   * listening to.
   *
   * @param v takes an AnimationView with a slider component.
   * @throws IllegalArgumentException if the AnimationView v is null.
   */
  public SliderChangeListener(AnimationView v) throws IllegalArgumentException {
    if (v == null) {
      throw new IllegalArgumentException("View cannot be null");
    }
    this.view = v;
  }

  /**
   * Listens to the ticks per second slider in the AnimationView and changes the speed of the
   * animation according to the value specified by the slider.
   *
   * @param e takes a ChangeEvent tied to a slider in the AnimationView.
   */
  @Override
  public void stateChanged(ChangeEvent e) {
    if (view instanceof PlaybackViewImpl) {
      ((PlaybackViewImpl) view).setAnimSpeed();
    }
  }
}
