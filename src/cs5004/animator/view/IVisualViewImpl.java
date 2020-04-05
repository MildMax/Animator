package cs5004.animator.view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.shapes.Shape;

public class IVisualViewImpl extends JFrame implements AnimationView {

  private ShapePanel shapePanel;
  private JButton startButton;
  private JButton pauseButton;
  private JButton resumeButton;
  private JButton restartButton;
  private JToggleButton loopButton;
  private JTextField speedIn;
  private JButton speedSet;
  private int ticksPerSecond;

  private int buttonWidth = 100;
  private int buttonHeight = 25;

  public IVisualViewImpl(int x, int y, int windowWidth, int windowHeight,
                        int maxWidth, int maxHeight, int ticksPerSecond)
          throws IllegalArgumentException {
    super();

    this.ticksPerSecond = ticksPerSecond;

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

    JSplitPane splitPane = new JSplitPane();

    JPanel top = new JPanel();
    top.setBounds(0, 0, windowWidth, windowHeight);
    //top.setPreferredSize(new Dimension(windowWidth, windowHeight));
    JPanel bottom = new JPanel();
    //bottom.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    bottom.setBounds(0, 0, buttonWidth, buttonHeight);
    bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));

    splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
    splitPane.setDividerLocation(windowHeight);
    splitPane.setTopComponent(top);
    splitPane.setBottomComponent(bottom);

    getContentPane().setLayout(new GridLayout());
    getContentPane().add(splitPane);

    shapePanel = new ShapePanel();
    shapePanel.setPreferredSize(new Dimension(maxWidth, maxHeight));

    JScrollPane scrollPane = new JScrollPane(shapePanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(windowWidth, windowHeight));

    top.add(scrollPane);

    int bHeight = y + windowHeight;

    startButton = new JButton("start");
    startButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    bottom.add(startButton);

    pauseButton = new JButton("pause");
    pauseButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    bottom.add(pauseButton);

    resumeButton = new JButton("resume");
    resumeButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    bottom.add(resumeButton);

    restartButton = new JButton("restart");
    restartButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    bottom.add(restartButton);

    loopButton = new JToggleButton("Loop");
    loopButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    bottom.add(loopButton);

    JLabel speedLabel = new JLabel("Speed:");
    speedIn = new JTextField();
    speedIn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    speedSet = new JButton("Enter");
    speedSet.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    bottom.add(speedLabel);
    bottom.add(speedIn);
    bottom.add(speedSet);


    //this.setPreferredSize(new Dimension(windowWidth,
    //        windowHeight + buttonHeight + 200));
    this.setBounds(x, y, windowWidth, windowHeight + buttonHeight);
    this.setTitle("Easy Animator");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    new AnimationRunner(m, this, this.ticksPerSecond).runAnim();
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
}
