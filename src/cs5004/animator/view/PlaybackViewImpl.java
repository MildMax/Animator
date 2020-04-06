package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.shapes.Shape;

public class PlaybackViewImpl extends JFrame implements AnimationView {

  private JSplitPane splitPane;
  private JScrollPane scrollPane;
  private JPanel top;
  private JPanel bottom;

  private ShapePanel shapePanel;
  private JButton startButton;
  private JButton pauseButton;
  private JButton resumeButton;
  private JButton restartButton;
  private JToggleButton loopButton;
  private JLabel speedLabel;
  private JTextField speedIn;
  private JButton speedSet;
  private int ticksPerSecond;

  private AnimationRunner runner;

  private final Dimension buttonDims = new Dimension(75, 15);

  private int buttonWidth = 75;
  private int buttonHeight = 25;

  public PlaybackViewImpl(int x, int y, int windowWidth, int windowHeight,
                          int maxWidth, int maxHeight, int ticksPerSecond)
          throws IllegalArgumentException {
    if (windowWidth <= 0 || windowHeight <= 0) {
      throw new IllegalArgumentException("Width and height must be greater"
              + "than 0");
    } else if (maxWidth <= 0 || maxHeight <= 0) {
      throw new IllegalArgumentException("Max window width and height must be greater"
              + "than 0");
    } else if (x < 0 || y < 0) {
      throw new IllegalArgumentException("x and y positions cannot be negative");
    } else if (ticksPerSecond < 1) {
      throw new IllegalArgumentException("Delay cannot be less than 1");
    }

    this.ticksPerSecond = ticksPerSecond;

    splitPane = new JSplitPane();

    top = new JPanel();
    //top.setBounds(0, 0, windowWidth, windowHeight);
    top.setPreferredSize(new Dimension(windowWidth, windowHeight));
    bottom = new JPanel();
    bottom.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    //bottom.setBounds(0, 0, buttonWidth, buttonHeight);
    bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
    //bottom.setLayout(new GridLayout());

    splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
    splitPane.setDividerLocation(windowHeight);
    splitPane.setDividerSize(0);
    splitPane.setResizeWeight(1);
    splitPane.setBottomComponent(bottom);
    splitPane.setTopComponent(top);

    getContentPane().setLayout(new GridLayout());
    getContentPane().add(splitPane);

    shapePanel = new ShapePanel();
    shapePanel.setPreferredSize(new Dimension(maxWidth, maxHeight));

    scrollPane = new JScrollPane(shapePanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(windowWidth, windowHeight));
    top.add(scrollPane);

    startButton = new JButton("Start");
    startButton.setPreferredSize(buttonDims);
    //startButton.addActionListener(this);
    startButton.setActionCommand("start");
    startButton.setPreferredSize(buttonDims);
    bottom.add(startButton);

    pauseButton = new JButton("Pause");
    pauseButton.setPreferredSize(buttonDims);
    //pauseButton.addActionListener(this);
    pauseButton.setActionCommand("pause");
    bottom.add(pauseButton);

    resumeButton = new JButton("Resume");
    resumeButton.setPreferredSize(buttonDims);
    //resumeButton.addActionListener(this);
    resumeButton.setActionCommand("resume");
    bottom.add(resumeButton);

    restartButton = new JButton("Restart");
    restartButton.setPreferredSize(buttonDims);
    //restartButton.addActionListener(this);
    restartButton.setActionCommand("restart");
    bottom.add(restartButton);

    loopButton = new JToggleButton("Loop");
    loopButton.setPreferredSize(buttonDims);
    //loopButton.addActionListener(this);
    loopButton.setActionCommand("loop");
    bottom.add(loopButton);

    speedLabel = new JLabel("Speed:", SwingConstants.RIGHT);
    speedIn = new JTextField();
    speedIn.setPreferredSize(buttonDims);
    speedSet = new JButton("Enter");
    speedSet.setPreferredSize(buttonDims);
    //speedSet.addActionListener(this);
    speedSet.setActionCommand("speed");
    bottom.add(speedLabel);
    bottom.add(speedIn);
    bottom.add(speedSet);

    this.setBounds(x, y, windowWidth, windowHeight + buttonHeight);
    this.setTitle("Easy Animator");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //this.setResizable(false);
    this.pack();
  }

  /**
   * Displays the window on screen.
   */
  @Override
  public void openView() {
    this.setVisible(true);
  }

  /**
   * Closes the window on screen.
   */
  @Override
  public void closeView() {
    this.setVisible(false);
    this.dispose();
  }

  /**
   * Draws a frame on the window according to the list of shapes provided to parameter shapeList.
   * Throws IllegalArgumentException if the list of shapes is null.
   *
   * @param shapeList takes a list of shapes to be drawn.
   * @throws IllegalArgumentException if the list of shapes is null.
   */
  @Override
  public void drawNewFrame(List<Shape> shapeList) throws IllegalArgumentException {
    if (shapeList == null) {
      throw new IllegalArgumentException("shapeList cannot be null");
    }

    //add new rects and ellipses in here
    shapePanel.addFrame(shapeList);

    this.repaint();
    this.revalidate();
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
    runner = new AnimationRunner(m, this, this.ticksPerSecond);
    runner.runAnim();
  }

  public AnimationRunner getRunner() {
    return this.runner;
  }

  public void setText() {
    String text = speedIn.getText();
    int newSpeed;
    try {
      newSpeed = Integer.parseInt(text);
      if (newSpeed > 0) {
        runner.setTicksPerSeconds(newSpeed);
      }
    } catch (NumberFormatException err) {

    }
    speedIn.setText("");
  }

  public void setCommandListener(ActionListener e) {
    startButton.addActionListener(e);
    pauseButton.addActionListener(e);
    resumeButton.addActionListener(e);
    restartButton.addActionListener(e);
    loopButton.addActionListener(e);
    speedSet.addActionListener(e);
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
    shapePanel.setSize(new Dimension(splitPane.getWidth(), splitPane.getHeight() - buttonHeight));
    //scrollPane.setSize(new Dimension(splitPane.getWidth(), splitPane.getHeight() - buttonHeight));
    scrollPane.setBounds(0, 0, splitPane.getWidth(), splitPane.getHeight() - buttonHeight);
    //splitPane.setDividerLocation(splitPane.getHeight() - buttonHeight);
    //speedLabel.setSize(buttonDims);
    //bottom.setSize(400, 25);
    //shapePanel.setSize(new Dimension(splitPane.getWidth(), splitPane.getHeight() - buttonHeight));
    //scrollPane.setSize(new Dimension(splitPane.getWidth(), splitPane.getHeight() - buttonHeight));
    //top.setSize(new Dimension(splitPane.getWidth(), splitPane.getHeight() - buttonHeight));
  }
}
