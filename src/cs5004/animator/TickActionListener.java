package cs5004.animator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.view.AnimationView;

public class TickActionListener implements ActionListener {

  private int tick = 0;
  private AnimationModel model;
  private AnimationView view;

  public TickActionListener(AnimationModel m, AnimationView v) {
    this.model = m;
    this.view = v;
  }

  public int getTick() {
    return this.tick;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    ++tick;
    try {
      this.view.drawNewFrame(this.model.getShapesAtTick(this.tick));
    } catch (IllegalArgumentException ex) {
      return;
    }
  }
}
