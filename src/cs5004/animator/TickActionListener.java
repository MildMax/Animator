package cs5004.animator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.view.VisualView;

public class TickActionListener implements ActionListener {

  private int tick = 0;
  private AnimationModel model;
  private VisualView view;

  public TickActionListener(AnimationModel m, VisualView v) {
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
