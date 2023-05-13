/*
 * code inspiration taken from: https://stackoverflow.com/questions/33487186/swing-timer-stopwatch-in-java
 * adapted to fit this program
 */

//import essentials
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*; 
import java.time.Duration;

public class Stopwatch extends JPanel {
  //instance variables
  private JLabel label;
  private long lastTick;
  private Timer timer;
  private String time;
  private Duration duration;

  /*
   * Constructor for the Stopwatch class
   */
  public Stopwatch(){
    //set a layout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    //set the light blue background
    setBackground(new java.awt.Color(179, 219, 255));
    //set up the label in the desired format
    label = new JLabel(String.format("%02d:%02d.%03d", 0, 0, 0));
    //make a new timer object with a millisecond checker
    timer = new Timer(100, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        long runningTime = System.currentTimeMillis() - lastTick;
        duration = Duration.ofMillis(runningTime);
        long minutes = duration.toMinutes();
        Duration d = duration.minusMinutes(minutes);
        long mil = d.toMillis();
        long seconds = mil / 1000;
        mil -= (seconds * 1000);
        //update the label
        time = String.format("%02d:%02d.%03d", minutes, seconds, mil);
        label.setText(time);
      }
    });

    //add the updated label to the panel
    add(label);
    //center the label on the JPanel
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
  }

  /*
   * Method that starts the stopwatch
   * @return void
   */
  public void start(){
    if (!timer.isRunning()) {
      lastTick = System.currentTimeMillis();
      timer.start();
    }
  }

  /*
   * Method that stops the stopwatch from running and returns the final tick of the stopwatch when it is stopped
   * @return Duration duration at the ending moment of timer
   */
  public Duration stop(){
    timer.stop();
    return duration;
  } 
}
