package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs5004.animator.view.AnimationView;
import cs5004.animator.view.PlaybackViewImpl;

public class ButtonListener implements ActionListener {

  AnimationView v;

  public ButtonListener(AnimationView v) {
    this.v = v;
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
        p.setSpeed();
        break;
    }
  }
}
