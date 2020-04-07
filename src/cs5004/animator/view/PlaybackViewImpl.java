package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.*;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.shapes.Shape;

public class PlaybackViewImpl extends AbstractVisualView {

  private JSplitPane splitPane;
  private JScrollPane scrollPane;
  private JPanel top;
  private JPanel bottom;

  private JButton playButton;
  private JButton restartButton;
  private JCheckBox loopBox;
  private JLabel speedLabel;
  private JTextField speedIn;
  private JButton speedSet;

  private final Dimension buttonDims = new Dimension(75, 15);

  private int buttonWidth = 75;
  private int buttonHeight = 25;

  public PlaybackViewImpl(int x, int y, int windowWidth, int windowHeight,
                          int maxWidth, int maxHeight, int ticksPerSecond)
          throws IllegalArgumentException {
    super(x, y, windowWidth, windowHeight, maxWidth, maxHeight, ticksPerSecond);

    splitPane = new JSplitPane();

    top = new JPanel();
    //top.setBounds(0, 0, windowWidth, windowHeight);
    top.setPreferredSize(new Dimension(windowWidth, windowHeight));
    //top.setSize(new Dimension(windowWidth, windowHeight));
    bottom = new JPanel();
    bottom.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    //bottom.setBounds(0, 0, buttonWidth, buttonHeight);
    //bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
    bottom.setLayout(new GridBagLayout());

    splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
    splitPane.setDividerLocation(windowHeight);
    splitPane.setDividerSize(0);
    splitPane.setResizeWeight(1);
    splitPane.setBottomComponent(bottom);
    splitPane.setTopComponent(top);

    getContentPane().setLayout(new GridLayout());
    getContentPane().add(splitPane);

    scrollPane = new JScrollPane(shapePanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(windowWidth, windowHeight));
    top.add(scrollPane);

    playButton = new JButton("Play");
    //startButton.setPreferredSize(buttonDims);
    playButton.setMinimumSize(buttonDims);
    playButton.setActionCommand("play");
    bottom.add(playButton);

    restartButton = new JButton("Restart");
    //restartButton.setPreferredSize(buttonDims);
    restartButton.setMinimumSize(buttonDims);
    restartButton.setActionCommand("restart");
    bottom.add(restartButton);

    JLabel loopLabel = new JLabel("Loop:", SwingConstants.RIGHT);
    bottom.add(loopLabel);
    loopBox = new JCheckBox();
    loopBox.setActionCommand("loop");
    bottom.add(loopBox);

    speedLabel = new JLabel("Speed:", SwingConstants.RIGHT);
    speedIn = new JTextField();
    //speedIn.setPreferredSize(buttonDims);
    speedIn.setMinimumSize(buttonDims);
    //speedIn.setMaximumSize(buttonDims);
    speedSet = new JButton("Enter");
    //speedSet.setPreferredSize(buttonDims);
    speedSet.setMinimumSize(buttonDims);
    speedSet.setActionCommand("speed");
    bottom.add(speedLabel);
    bottom.add(speedIn);
    bottom.add(speedSet);

    this.setBounds(x, y, windowWidth, windowHeight + buttonHeight);
    this.setTitle("Easy Animator");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
  }

  /**
   * Runs the visual animation in a window displayed on screen.
   *
   * @param m takes an AnimationModel that stores an animation to be written to
   * @throws IllegalArgumentException if AnimationModel m is null.
   */
  @Override
  public void run(AnimationModel m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("Animation Model cannot be null.");
    }
    runner = new AnimationRunnerImpl(m, this, this.ticksPerSecond);
    runner.openWindow();
  }

  /**
   *
   * @return
   */
  @Override
  public AnimationRunner getRunner() {
    return this.runner;
  }

  /**
   *
   */
  @Override
  public void setSpeed() {
    String text = speedIn.getText();
    int newSpeed;
    try {
      newSpeed = Integer.parseInt(text);
      if (newSpeed > 0) {
        runner.setTicksPerSecond(newSpeed);
      }
    } catch (NumberFormatException err) {

    }
    speedIn.setText("");
  }

  public void togglePlayText() {
    if (runner.isRunning()) {
      playButton.setText("Pause");
    }
    else {
      playButton.setText("Play");
    }

  }

  /**
   *
   * @param e an ActionListener that handles executing methods for a the view
   */
  @Override
  public void setCommandListener(ActionListener e) {
    playButton.addActionListener(e);
    restartButton.addActionListener(e);
    loopBox.addActionListener(e);
    speedSet.addActionListener(e);
  }

  /**
   *
   * @param listener the listener to be attached to the object in the view.
   */
  @Override
  public void setMouseListener(MouseListener listener) {
    shapePanel.addMouseListener(listener);
  }

  /**
   * Throws UnsupportedOperationException since visual view does not write any data to a file.
   *
   * @return always throws exception.
   * @throws UnsupportedOperationException since visual views do not write any data to a file.
   */
  @Override
  public String getOutFileContents() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("VisualView does not support getting file contents");
  }

  @Override
  public void validate() {
    super.validate();
    int w = splitPane.getWidth();
    int h = splitPane.getHeight() - buttonHeight;
    shapePanel.setSize(new Dimension(w, h));
    scrollPane.setBounds(0, 0, w, h);
  }
}
