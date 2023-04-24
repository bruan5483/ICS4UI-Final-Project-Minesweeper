//import essentials
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*; 

public class Square extends JButton {
  //instance variables
  private boolean isMine;
  private int display;
  private boolean guessed;
  private boolean isFlag;
  private final int row;
  private final int col;
  private String difficulty;

  //define ImageIcons from the attached png images
  ImageIcon m0 = new ImageIcon("Minesweeper_0.png");
  ImageIcon m1 = new ImageIcon("Minesweeper_1.png");
  ImageIcon m2 = new ImageIcon("Minesweeper_2.png");
  ImageIcon m3 = new ImageIcon("Minesweeper_3.png");
  ImageIcon m4 = new ImageIcon("Minesweeper_4.png");
  ImageIcon m5 = new ImageIcon("Minesweeper_5.png");
  ImageIcon m6 = new ImageIcon("Minesweeper_6.png");
  ImageIcon m7 = new ImageIcon("Minesweeper_7.png");
  ImageIcon m8 = new ImageIcon("Minesweeper_8.png");
  ImageIcon mine = new ImageIcon("Minesweeper_mine.png");
  ImageIcon flag = new ImageIcon("Minesweeper_flag.png");

  /*
   * Constructor for Square objects
   */
  public Square(int r, int c, int num, String difficult, MouseListener m){
    //give instance variables row and col values for the location of the Square
    row = r;
    col = c;
    //set guessed to be false (initial state)
    guessed = false;
    //set int display to be the value held by the Square
    display = num;
    //set isFlag to be false (initial state)
    isFlag = false;
    //set String difficulty to be the difficulty level of the minesweeper game
    difficulty = difficult;

    //if the value held by the Square is 9, it is a mine
    if (num == 9) {
      isMine = true;
    //else the Square is not a mine
    } else {
      isMine = false;
    }

    //resize essential images needed by the Square
    resize();

    //use the MouseListener defined in the Minesweeper class
    addMouseListener(m);
  }

  /*
   * Accessor method that returns the row position of the Square in the 2D array found in class Minesweeper
   * @return final int row
   */
  public int getRow(){
    return row;
  }

  /*
   * Accessor method that returns the column position of the Square in the 2D array found in class Minesweeper
   * @return final int col
   */
  public int getCol(){
    return col;
  }
  
  /*
   * Accessor method that returns if the Square is a mine
   * @return boolean isMine
   */
  public boolean mineCheck(){
    return isMine;
  }

  /*
   * Mutator method that sets the icon and disabledicon of the Square based on its display number once the Square has been guessed
   */
  public void setGuessed(){
    //set instance variable guessed to true
    guessed = true;

    //sets the icon and disabledicon based on instance variable int display of the Square
    if (display == 0){
      this.setIcon(m0);
      this.setDisabledIcon(m0);
    } else if (display == 1){
      this.setIcon(m1);
      this.setDisabledIcon(m1);
    } else if (display == 2){
      this.setIcon(m2);
      this.setDisabledIcon(m2);
    } else if (display == 3){
      this.setIcon(m3);
      this.setDisabledIcon(m3);
    } else if (display == 4){
      this.setIcon(m4);
      this.setDisabledIcon(m4);
    } else if (display == 5){
      this.setIcon(m5);
      this.setDisabledIcon(m5);
    } else if (display == 6){
      this.setIcon(m6);
      this.setDisabledIcon(m6);
    } else if (display == 7){
      this.setIcon(m7);
      this.setDisabledIcon(m7);
    } else if (display == 8){
      this.setIcon(m8);
      this.setDisabledIcon(m8);
    } else if (display == 9){
      this.setIcon(mine);
      this.setDisabledIcon(mine);
    } 

    //disable the button from being pressed again
    this.setEnabled(false);
  }

  /*
   * Accessor method for if the Square has been guessed
   * @return boolean guessed
   */
  public boolean isGuessed(){
    return guessed;
  }

  /*
   * Accessor method for if the Square is a flag
   * @return boolean isFlag
   */
  public boolean getIsFlag(){
    return isFlag;
  }

  /*
   * Accessor method for the int display value of a Square
   * @return int display
   */
  public int getDisplay(){
    return display;
  }
  
  /*
   * Mutator method for if a Square is set to be a flag by a MouseListener right-click
   */
  public void setFlag(){
    isFlag = true;
    //setting guessed=true prevents left-clicks from being recognized
    guessed = true; 
    //set the icon of the button to be that of a flag
    this.setIcon(flag);
    this.setDisabledIcon(flag);
  }

  /*
   * Mutator method for if a Square is to have its flag removed by another MouseListener right-click
   */
  public void resetFlag(){
    isFlag = false;
    guessed = false;
    //set the icon of the button to be null
    this.setIcon(null);
    this.setDisabledIcon(null);
  }

  /*
   * Private helper method for the constructor that resizes the images needed by the Square based on the difficulty
   */
  private void resize(){
    //get the size that the image will be resized to
    int size = 52; //easy as default
    if (difficulty.equals("medium")){
      size = 33;
    } else if (difficulty.equals("hard")) {
      size = 21;
    }

    //get the number held by the square (if it is 0, resize the image for 0)
    if (display == 0){
      //transform imageicon to image
      Image m01 = m0.getImage(); 
      //scale the image to the desired size smoothly
      Image m02 = m01.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH); 
      //have ImageIcon reference m0 point to the newly scaled and transformed version of itself
      m0 = new ImageIcon(m02); 

    //all other images can be resized like the first one
    } else if (display == 1){
      Image m11 = m1.getImage();
      Image m12 = m11.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH); 
      m1 = new ImageIcon(m12);
      
    } else if (display == 2){
      Image m21 = m2.getImage();
      Image m22 = m21.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH); 
      m2 = new ImageIcon(m22);
      
    } else if (display == 3){
      Image m31 = m3.getImage();
      Image m32 = m31.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH); 
      m3 = new ImageIcon(m32);
      
    } else if (display == 4){
      Image m41 = m4.getImage();
      Image m42 = m41.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH); 
      m4 = new ImageIcon(m42);
      
    } else if (display == 5){
      Image m51 = m5.getImage();
      Image m52 = m51.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH); 
      m5 = new ImageIcon(m52);
      
    } else if (display == 6){
      Image m61 = m6.getImage();
      Image m62 = m61.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH); 
      m6 = new ImageIcon(m62);
      
    } else if (display == 7){
      Image m71 = m7.getImage();
      Image m72 = m71.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH); 
      m7 = new ImageIcon(m72);
      
    } else if (display == 8){
      Image m81 = m8.getImage();
      Image m82 = m81.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH); 
      m8 = new ImageIcon(m82);
      
    } else if (display == 9){
      Image mine1 = mine.getImage();
      Image mine2 = mine1.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH); 
      mine = new ImageIcon(mine2);
    }

    //flag will always be resized because any Square can become a flag (even if it's wrong)
    Image flag1 = flag.getImage();
    Image flag2 = flag1.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH); 
    flag = new ImageIcon(flag2);  
  }
}