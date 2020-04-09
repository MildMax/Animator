package cs5004.animator.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Hashtable;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.event.ChangeListener;

/**
 * The PlaybackViewImpl class plays an animation in an interactive window that allows for playing,
 * pausing, looping and changing speed of the animation. Extends the AbstractVisualView class
 * which implements the AnimationView interface.
 */
public class PlaybackViewImpl extends AbstractVisualView {

  private JSplitPane splitPane;
  private JPanel bottom;

  private JButton playButton;
  private JButton restartButton;
  private JCheckBox loopBox;
  private JSlider slider;

  private final int buttonWidth = 75;
  private final int buttonHeight = 25;
  private final int bottomHeight = 75;

  private final Dimension buttonDims = new Dimension(buttonWidth, buttonHeight);

  /**
   * Formats the window the animation will be played in. Takes two ints indicating the x and y
   * position of the window, two ints indicating the width and height of the window, two ints
   * indicating the overall width and height of the animation, and an int indicating the ticks
   * per second of the animation. If the size of the window specified is larger than the screen,
   * reformats the window to fit within the current screen.
   *
   * @param x the x position of the window on the screen.
   * @param y the y position of the window on the screen.
   * @param windowWidth the width of the window.
   * @param windowHeight the height of the window.
   * @param maxWidth the width of the animation.
   * @param maxHeight the height of the animation.
   * @param ticksPerSecond the ticks per second of the animation.
   * @throws IllegalArgumentException if the x or y values indicating the position of the
   *                                  upper left corner of the display are less than 0.
   *                                  If the width or height values of the display window are less
   *                                  than or equal to 0.
   *                                  If the specified ticks per second is less than 1.
   */
  public PlaybackViewImpl(int x, int y, int windowWidth, int windowHeight,
                          int maxWidth, int maxHeight, int ticksPerSecond)
          throws IllegalArgumentException {
    super(x, y, windowWidth, windowHeight, maxWidth, maxHeight, ticksPerSecond);

    //make splitpane
    splitPane = new JSplitPane();

    //set up top component
    //shapePanel set in super constructor
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
    if (this.ticksPerSecond < 10) {
      slider.setValue(10);
    }
    else if (this.ticksPerSecond > 200) {
      slider.setValue(200);
    }
    else {
      slider.setValue(this.ticksPerSecond);
    }
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
    this.pack();
  }

  /**
   * Returns the AnimationRunner running the current animation.
   *
   * @return the AnimationRunner running the current animation.
   */
  @Override
  public AnimationRunner getRunner() {
    return this.runner;
  }

  /**
   * Toggles the text on the Play/Pause button. If the animation is playing, displays "Pause", if
   * the animation is not playing, displays "Play".
   */
  public void togglePlayText() {
    if (runner.isRunning()) {
      playButton.setText("Pause");
    }
    else {
      playButton.setText("Play");
    }
  }

  /**
   * Sets the speed of the animation according to the value specified by the Ticks Per Second
   * slider on the display.
   */
  public void setAnimSpeed() {
    int val = slider.getValue();
    runner.setTicksPerSecond(val);
  }

  /**
   * Sets an ActionListener e to be attached to the play/pause button, the restart button, and
   * the loop checkbox. Throws IllegalArgumentException if the ActionListener is null.
   *
   * @param e an ActionListener that handles executing methods for a the view
   * @throws IllegalArgumentException if the ActionListener e is null.
   */
  @Override
  public void setCommandListener(ActionListener e) throws IllegalArgumentException {
    if (e == null) {
      throw new IllegalArgumentException("ActionListener is null");
    }
    playButton.addActionListener(e);
    restartButton.addActionListener(e);
    loopBox.addActionListener(e);
  }

  /**
   * Sets the ChangeListener to the Ticks Per Second slider in the PlaybackView. Throws
   * IllegalArgumentException if the ChangeListener is null.
   *
   * @param e the ChangeListener to be set to the TicksPerSecondSlider in the Playback view.
   * @throws IllegalArgumentException if the ChangeListener is null.
   */
  @Override
  public void setChangeListener(ChangeListener e) throws IllegalArgumentException {
    if (e == null) {
      throw new IllegalArgumentException("ChangeListener cannot be null");
    }
    slider.addChangeListener(e);
  }

  /**
   * Sets a MouseListener object that listens to the clicks on the AnimationView
   * animation window. Throws IllegalArgumentException if the ChangeListener is null.
   *
   * @param listener the listener to be attached to the animation window.
   * @throws IllegalArgumentException if the MouseListener is null.
   */
  @Override
  public void setMouseListener(MouseListener listener) throws IllegalArgumentException {
    if (listener == null) {
      throw new IllegalArgumentException("MouseListener cannot be null");
    }
    shapePanel.addMouseListener(listener);
  }

  /**
   * Get the playButton.
   *
   * @return playButton.
   */
  public JButton getPlayButton() {
    return this.playButton;
  }

  /**
   * Get the restartButton.
   *
   * @return restartButton.
   */
  public JButton getRestartButton() {
    return this.restartButton;
  }

  /**
   * Get loopBox.
   *
   * @return loopBox.
   */
  public JCheckBox getLoopBox() {
    return this.loopBox;
  }
}
