package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.event.ChangeListener;

import cs5004.animator.model.AnimationModel;

public class PlaybackViewImpl extends AbstractVisualView {

  private JSplitPane splitPane;
  private JScrollPane scrollPane;
  private JPanel bottom;

  private JButton playButton;
  private JButton restartButton;
  private JCheckBox loopBox;
  private JSlider slider;

  private int buttonWidth = 75;
  private int bottomHeight = 75;

  private final Dimension buttonDims = new Dimension(buttonWidth, 25);

  public PlaybackViewImpl(int x, int y, int windowWidth, int windowHeight,
                          int maxWidth, int maxHeight, int ticksPerSecond)
          throws IllegalArgumentException {
    super(x, y, windowWidth, windowHeight, maxWidth, maxHeight, ticksPerSecond);

    //make splitpane
    splitPane = new JSplitPane();

    //set up top component
    //shapePanel set in super constructor
    scrollPane = new JScrollPane(shapePanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    splitPane.setTopComponent(scrollPane);

    //set up bottom component
    bottom = new JPanel();
    GridBagLayout bottomLayout = new GridBagLayout();
    bottom.setLayout(bottomLayout);

    JPanel leftPanel = new JPanel();
    GridBagLayout leftLayout = new GridBagLayout();
    leftPanel.setLayout(leftLayout);

    JPanel buttonPanel = new JPanel();
    GridBagLayout buttonLayout = new GridBagLayout();
    buttonPanel.setLayout(buttonLayout);

    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(5, 5, 5, 5);

    //pause/playbutton setup
    playButton = new JButton("Play");
    playButton.setPreferredSize(buttonDims);
    playButton.setMinimumSize(buttonDims);
    playButton.setActionCommand("play");
    buttonPanel.add(playButton);
    buttonLayout.setConstraints(playButton, c);

    //restart button setup
    restartButton = new JButton("Restart");
    restartButton.setPreferredSize(buttonDims);
    restartButton.setMinimumSize(buttonDims);
    restartButton.setActionCommand("restart");
    buttonPanel.add(restartButton);
    buttonLayout.setConstraints(restartButton, c);

    //loop button setup
    JPanel loopPanel = new JPanel();
    JLabel loopLabel = new JLabel("Loop:", SwingConstants.RIGHT);
    bottom.add(loopLabel);
    loopBox = new JCheckBox();
    loopBox.setActionCommand("loop");
    loopPanel.add(loopLabel);
    loopPanel.add(loopBox);
    buttonPanel.add(loopPanel);

    JTextArea clickText = new JTextArea();
    clickText.setText("Click the screen to pause/play animation!");

    c.gridy = 1;

    leftPanel.add(buttonPanel);
    leftPanel.add(clickText);
    leftLayout.setConstraints(clickText, c);
    bottom.add(leftPanel);

    //slider component setup
    slider = new JSlider(JSlider.HORIZONTAL, 10, 200, 50);
    slider.setMajorTickSpacing(10);
    slider.setPaintTicks(true);
    Hashtable<Integer, JLabel> labelTable = new Hashtable();
    labelTable.put(10, new JLabel("10"));
    labelTable.put(50, new JLabel("50"));
    labelTable.put(100, new JLabel("100"));
    labelTable.put(200, new JLabel("200"));
    slider.setLabelTable(labelTable);
    slider.setPaintLabels(true);
    slider.setFont(new Font("Serif", Font.PLAIN, 10));
    slider.setSnapToTicks(true);

    JPanel sliderPanel = new JPanel();
    sliderPanel.setMinimumSize(new Dimension(buttonWidth * 4, bottomHeight));
    sliderPanel.setBorder(BorderFactory.createTitledBorder("Ticks Per Second"));
    sliderPanel.add(slider);
    bottom.add(sliderPanel);

    //finish setting up splitpane
    splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
    splitPane.setDividerLocation(windowHeight);
    splitPane.setDividerSize(0);
    splitPane.setResizeWeight(1);
    splitPane.setBottomComponent(bottom);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    if (screenSize.height < (windowHeight + bottomHeight)) {
      int buffer = 50;
      splitPane.setDividerLocation(screenSize.height - (buffer * 2) - bottomHeight);
      this.setPreferredSize(new Dimension(windowWidth, screenSize.height - buffer));
    }

    //set up JFrame
    this.add(splitPane);
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

  public void togglePlayText() {
    if (runner.isRunning()) {
      playButton.setText("Pause");
    }
    else {
      playButton.setText("Play");
    }
    validate();
  }

  public void setAnimSpeed() {
    int val = slider.getValue();
    runner.setTicksPerSecond(val);
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
  }

  public void setChangeListener(ChangeListener e) {
    slider.addChangeListener(e);
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
}
