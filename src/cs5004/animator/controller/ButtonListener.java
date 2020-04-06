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
    switch (e.getActionCommand()) {
      case "play":
        v.getRunner().togglePlay();
        break;
      case "restart":
        v.getRunner().restartAnim();
        break;
      case "loop":
        v.getRunner().toggleLoop();
        break;
      case "speed":
        v.setSpeed();
        break;
    }
  }
}
