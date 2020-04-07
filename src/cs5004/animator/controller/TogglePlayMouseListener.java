package cs5004.animator.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cs5004.animator.view.AnimationRunner;
import cs5004.animator.view.AnimationView;
import cs5004.animator.view.PlaybackViewImpl;

public class TogglePlayMouseListener extends MouseAdapter {

  AnimationRunner runner;

  public TogglePlayMouseListener(AnimationRunner runner) {
    this.runner = runner;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    super.mouseClicked(e);
    runner.togglePlay();
  }

}