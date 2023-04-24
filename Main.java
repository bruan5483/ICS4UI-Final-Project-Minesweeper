/*
 * @author Bryant Ruan
 * @author Nuha Akhand
 */

//import essentials
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*; 
import java.io.File;

public class Main {
  // the frame that will contain the game
  JFrame frame = new JFrame("Minesweeper!");
  // play again button that will be used throughout the game
  JButton playAgainButton;
  // back to home screen button that will be shared by the rules and levels screens
  JButton backButton;
  // play button will be used by both home and rules screens
  JButton playButton;

  //for high scores
  File highScoreFile;
  
  /*
   * Constructor for class Main to run the program
   */
  public Main(){
    //set up highScoreFile and the filewriter and scanner used by to find high scores
    highScoreFile = new File("HighScores.txt");
    
    //setting up frame
    frame.setSize(500, 575);
    frame.getContentPane().setBackground(new java.awt.Color(179, 219, 255));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    // go back to home button (for use in rules and levels screens)
    // no specific bounds because it will be used in a layout
    backButton = new JButton("<html>Go Back to Home</html>");
    backButton.setBackground(new Color(40, 48, 178));
    backButton.setForeground(Color.WHITE);
    backButton.setFont(new Font("Courier", Font.BOLD,19));
    backButton.setMaximumSize(new Dimension(210, 50));
    backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { 
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
        homeScreen();
      }
    });
    
    //play again button that will be used in the minesweeper game, mainly used for making a new instance of the game
    playAgainButton = new JButton("Play Again");
    playAgainButton.setBackground(new Color(40, 48, 178));
    playAgainButton.setForeground(Color.BLACK);
    playAgainButton.setFont(new Font("Courier", Font.BOLD,15));
    playAgainButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { 
        //idea about how to implement from https://stackoverflow.com/questions/9347076/how-to-remove-all-components-from-a-jframe-in-java
        //clear screen, revalidate and repaint
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
        setLevel();
      }
    }); 

    //start the game with the home screen
    homeScreen();
  }

  /*
   * Method that places all the components of the home screen on the JFrame
   * Has working button implementations that lead to the rules screen and the levels screen 
   * @return void
   */
  private void homeScreen(){
    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

    JLabel empty = new JLabel ("");
    empty.setMaximumSize(new Dimension(100, 140));
    frame.add(empty);
    
    //intro 1
    JLabel intro1 = new JLabel ("Welcome to");
    intro1.setFont(new Font("Courier", Font.BOLD,30));
    intro1.setAlignmentX(Component.CENTER_ALIGNMENT);
    frame.add(intro1);
    
    //intro 2
    JLabel intro2 = new JLabel ("Minesweeper!");
    intro2.setMaximumSize(new Dimension(360, 90));
    intro2.setFont(new Font("Courier", Font.BOLD,50));
    intro2.setAlignmentX(Component.CENTER_ALIGNMENT);
    frame.add(intro2);

    //empty label (formatting)
    JLabel empty1 = new JLabel ("");
    empty1.setMaximumSize(new Dimension(90, 40));
    frame.add(empty1);
    
    // play button
    playButton = new JButton("Play");
    playButton.setBackground(new Color(40, 48, 178));
    playButton.setForeground(Color.WHITE);
    playButton.setFont(new Font("Courier", Font.BOLD,20));
    playButton.setMaximumSize(new Dimension(210, 50));
    playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    playButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { 
        //clear screen, revalidate and repaint
        //then add levels screen to the frame
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
        setLevel();
      }
    });
    frame.add(playButton);

    //empty label (formatting)
    JLabel empty2 = new JLabel ("");
    empty2.setMaximumSize(new Dimension(90, 25));
    frame.add(empty2);
    
    //Instructions button
    JButton instructionsButton = new JButton("Instructions");
    instructionsButton.setBackground(new Color(40, 48, 178));
    instructionsButton.setForeground(Color.WHITE);
    instructionsButton.setFont(new Font("Courier", Font.BOLD,20));
    instructionsButton.setMaximumSize(new Dimension(210, 50)); instructionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    instructionsButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { 
        //clear screen, revalidate and repaint
        //then add rules screen to the frame
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
        setRules();
      }
    });
    frame.add(instructionsButton);

    frame.setVisible(true);
  }

  /*
   * Method that places all the components of the rules screen on the JFrame
   * Uses the back to home JButton
   * @return void
   */
  private void setRules(){
    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    
    //intro 
    JLabel instructionsIntro = new JLabel ("<html>Instructions:</html>");
    instructionsIntro.setMaximumSize(new Dimension(340, 80));
    instructionsIntro.setFont(new Font("Courier", Font.BOLD,45));
    instructionsIntro.setAlignmentX(Component.CENTER_ALIGNMENT);
    frame.add(instructionsIntro);

    //instructions
    JLabel instructions1 = new JLabel ("<html>The objective of the game is to reveal the entire board without hitting any mines or placing a flag on a square without a mine.<html>");
    instructions1.setFont(new Font("Courier", Font.BOLD,15));
    instructions1.setMaximumSize(new Dimension(400, 100));
    instructions1.setAlignmentX(Component.CENTER_ALIGNMENT);
    frame.add(instructions1);

    //instructions 2
    JLabel instructions2 = new JLabel ("<html>To reveal a square, click on it. If the square is empty, that means that there are no mines surrounding it. However, if there is a number on the square, that means the square is in direct contact with that many mines.<html>");
    instructions2.setFont(new Font("Courier", Font.BOLD,15));
    instructions2.setMaximumSize(new Dimension(400, 110));
    instructions2.setAlignmentX(Component.CENTER_ALIGNMENT);
    frame.add(instructions2);
    
    //instructions 3
    JLabel instructions3 = new JLabel ("<html>Using logic, place flags on all the mines and reveal the entire board. Good luck!<html>");
    instructions3.setFont(new Font("Courier", Font.BOLD,15));
    instructions3.setMaximumSize(new Dimension(400, 100));
    instructions3.setAlignmentX(Component.CENTER_ALIGNMENT);
    frame.add(instructions3);

    frame.add(playButton);

    //empty label
    JLabel empty1 = new JLabel ("");
    empty1.setMaximumSize(new Dimension(90, 20));
    frame.add(empty1);
  
    frame.add(backButton);

    frame.setVisible(true);
  }

  /*
   * Method that sets up the levels screen on the JFrame
   * Each level button leads to a new instance of a Minesweeper object with a corresponding level
   * Uses the back to home JButton
   * @return void
   */
  private void setLevel(){
    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    //header
    JLabel levels = new JLabel ("Levels");
    levels.setMaximumSize(new Dimension(190, 180));
    levels.setFont(new Font("Courier", Font.BOLD,50));
    levels.setAlignmentX(Component.CENTER_ALIGNMENT);
    frame.add(levels);

    // Easy button
    JButton easyButton = new JButton("Easy");
    easyButton.setBackground(new Color(40, 48, 178));
    easyButton.setForeground(Color.WHITE);
    easyButton.setFont(new Font("Courier", Font.BOLD,24));
    easyButton.setMaximumSize(new Dimension(130, 40));
    easyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    easyButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { 
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
        frame.setLayout(new BorderLayout());
        //create an instance of an easy minesweeper game
        Minesweeper m = new Minesweeper("easy", frame, playAgainButton, backButton, highScoreFile);
        m.printGrid();
        frame.setVisible(true);
      }
    });
    frame.add(easyButton);

    //empty label
    JLabel empty1 = new JLabel ("");
    empty1.setMaximumSize(new Dimension(100, 20));
    frame.add(empty1);

    // Medium button
    JButton mediumButton = new JButton("Medium");
    mediumButton.setBackground(new Color(40, 48, 178));
    mediumButton.setForeground(Color.WHITE);
    mediumButton.setFont(new Font("Courier", Font.BOLD,24));
    mediumButton.setMaximumSize(new Dimension(130, 40));
    mediumButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    mediumButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { 
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
        frame.setLayout(new BorderLayout());
        //create an instance of a medium minesweeper game
        Minesweeper m = new Minesweeper("medium", frame, playAgainButton, backButton, highScoreFile);
        m.printGrid();
        frame.setVisible(true);
      }
    });
    frame.add(mediumButton);

    //empty label
    JLabel empty2 = new JLabel ("");
    empty2.setMaximumSize(new Dimension(100, 20));
    frame.add(empty2);

    // Hard button
    JButton hardButton = new JButton("Hard");
    hardButton.setBackground(new Color(40, 48, 178));
    hardButton.setForeground(Color.WHITE);
    hardButton.setFont(new Font("Courier", Font.BOLD,24));
    hardButton.setMaximumSize(new Dimension(130, 40));
    hardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    hardButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { 
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
        frame.setLayout(new BorderLayout());
        //create an instance of a hard minesweeper game
        Minesweeper m = new Minesweeper("hard", frame, playAgainButton, backButton, highScoreFile);
        m.printGrid();
        frame.setVisible(true);
      }
    });
    frame.add(hardButton);

    //empty label
    JLabel empty3 = new JLabel ("");
    empty3.setMaximumSize(new Dimension(110, 70));
    frame.add(empty3);

    frame.add(backButton);
    
    frame.setVisible(true);
  }
  
  /*
   * Runs the program
   * @param the command line arguments
   * @return void
   */
  public static void main(String[] args) {
    Main main = new Main();
  }
}