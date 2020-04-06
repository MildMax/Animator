package cs5004.animator.controller;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.view.AnimationView;
import cs5004.animator.view.PlaybackViewImpl;

/**
 * The AnimationControllerImpl class
 */
public class AnimationControllerImpl implements AnimationController {

  AnimationModel m;
  AnimationView v;

  public AnimationControllerImpl(AnimationModel m, AnimationView v) {
    this.m = m;
    this.v = v;
  }

  @Override
  public void go() {
    v.run(m);

    if (v instanceof PlaybackViewImpl) {
      v.setCommandListener(new ButtonListener(this.v));
      v.setMouseListener(new TogglePlayMouseListener(v.getRunner()));
    }
  }

}
