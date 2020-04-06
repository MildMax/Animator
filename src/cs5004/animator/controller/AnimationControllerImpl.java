package cs5004.animator.controller;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.view.AnimationView;

public class AnimationControllerImpl {

  AnimationModel m;
  AnimationView v;

  public AnimationControllerImpl(AnimationModel m, AnimationView v) {
    this.m = m;
    this.v = v;
  }

  public void go() {
    v.run(m);
  }
}
