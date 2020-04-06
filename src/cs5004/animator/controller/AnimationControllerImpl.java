package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.view.AnimationView;
import cs5004.animator.view.PlaybackViewImpl;

public class AnimationControllerImpl implements ActionListener {

  AnimationModel m;
  AnimationView v;

  public AnimationControllerImpl(AnimationModel m, AnimationView v) {
    this.m = m;
    this.v = v;
  }

  public void go() {
    v.run(m);

    if (v instanceof PlaybackViewImpl) {
      ((PlaybackViewImpl) v).setCommandListener(this);
      ((PlaybackViewImpl) v)
              .setMouseListener(new TogglePlayMouseListener(((PlaybackViewImpl) v).getRunner()));
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    PlaybackViewImpl p = (PlaybackViewImpl) v;
    switch (e.getActionCommand()) {
      case "start":
      case "resume":
        p.getRunner().startAnim();
        break;
      case "pause":
        p.getRunner().pauseAnim();
        break;
      case "restart":
        p.getRunner().restartAnim();
        break;
      case "loop":
        p.getRunner().toggleLoop();
        break;
      case "speed":
        p.setText();
        break;
    }
  }

}
