package cs5004.animator.controller;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.view.AnimationView;
import cs5004.animator.view.PlaybackViewImpl;

/**
 * The AnimationControllerImpl class defines the methods needed to control the flow
 * of information between the AnimationView and the AnimationController. Implements
 * the AnimationController interface.
 */
public class AnimationControllerImpl implements AnimationController {

  private AnimationModel m;
  private AnimationView v;

  /**
   * The AnimationControllerImpl constructor takes a model that holds data for the animation
   * and a view that displays the information. Throws IllegalArgumentException if the AnimationModel
   * or the AnimationView are null.
   *
   * @param m takes a model that stores data for the animation.
   * @param v takes a view that displays the animation.
   * @throws IllegalArgumentException if the AnimationModel m or the AnimationView v is null.
   */
  public AnimationControllerImpl(AnimationModel m, AnimationView v)
          throws IllegalArgumentException {
    if (m == null || v == null) {
      throw new IllegalArgumentException("Model/view cannot be null");
    }
    this.m = m;
    this.v = v;
  }

  /**
   * Starts the animation by introducing the information in the model to the view. Sets
   * listeners if the view requires listeners.
   */
  @Override
  public void go() {
    v.run(m);

    if (v instanceof PlaybackViewImpl) {
      v.setCommandListener(new ButtonListener(this.v.getRunner()));
      v.setMouseListener(new TogglePlayMouseListener(this.v.getRunner()));
      v.setChangeListener(new SliderChangeListener(this.v));
    }
  }

  /**
   * Get the model. This class only exists for testing purposes.
   *
   * @return the model.
   */
  public AnimationModel getAnimationModel() {
    return this.m;
  }

  /**
   * Get the view. This class only exists for testing purposes.
   *
   * @return the view.
   */
  public AnimationView getAnimationView() {
    return this.v;
  }
}
