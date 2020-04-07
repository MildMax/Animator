package cs5004.animator.controller;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cs5004.animator.view.AnimationView;
import cs5004.animator.view.PlaybackViewImpl;

public class SliderChangeListener implements ChangeListener {

  AnimationView view;

  public SliderChangeListener(AnimationView v) {
    this.view = v;
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    if (view instanceof PlaybackViewImpl) {
      ((PlaybackViewImpl) view).setAnimSpeed();
    }
  }
}
