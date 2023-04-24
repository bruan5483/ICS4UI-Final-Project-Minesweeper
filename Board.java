//import essentials
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

/*
 * Class for board objects that will contain a 2D int array based on the difficulty level that is given by the user
 * The int 2D array of each board object will contain all of the positions of mines and also calculate the number of neighboring mines for each square that does not contain a mine (mines will be represented by the number "9" seeing that the maximum number of mines that can be neighboring a non-mine square is 8)
 */
public class Board {
  //instance variables
  private int[][] board;
  private String difficulty;
  private int width;
  private int height;
  private int mineCount;

  //static final HashMap that holds the exact number of mines for each difficulty level
  private final static HashMap<String, Integer> numMines;
  static {
    numMines = new HashMap<String, Integer>();
    numMines.put("easy", 10);
    numMines.put("medium", 40);
    numMines.put("hard", 99);
  }

  /*
   * Constructor for the Board class
   * @precondition: difficult is a valid lowercase difficulty level 
   */
  public Board(String difficult) {
    difficulty = difficult;
    //set values for demensions based on difficulty
    dimensions();
    //make the 2d array board based on dimensions
    board = new int[height][width];
    //get the number of mines from HashMap
    mineCount = numMines.get(difficult);
    //fill in the mines to the 2d int array 
    fillMines(mineCount);
    //fill in the other numbers based on how many neighboring mines there are
    fillNumbers();
  }

  /*
   * Private helper method for the constructor that provides values for int width and int height based on the given difficulty
   * @return void
   */
  private void dimensions() {
    if (difficulty.equals("easy")){
      width = 9;
      height = 9;
      
    } else if (difficulty.equals("medium")){
      width = 14;
      height = 14;
      
    } else if (difficulty.equals("hard")){
      width = 22;
      height = 22;
    }
  } 

  /*
   * Private helper method for the constructor that generates where the positions of the mines will be with no overlap
   * @param number of mines to be added
   * @return void
   */
  private void fillMines(int mineNum) {
    //create a Random object
    Random random = new Random();

    //create an ArrayList to hold all possible postions (ArrayList is used because it has indices and also can have elements removed)
    ArrayList<Integer> positions = new ArrayList<Integer>();

    //put all available positions into ArrayList positions
    for (int i = 0; i < width * height; i++) {
      positions.add(i);
    }

    //for loop that will add mineNum mines at random positions in the 2d array board
    for(int i = 0; i < mineNum ; i++){
      //get a random index for positions (useful once the size of positions starts changing with the removal of elements)
      int rand = random.nextInt(positions.size());

      //remove the randomly selected element from the ArrayList (remove method returns the removed element)
      int index = positions.remove(rand);

      //convert the position element from the ArrayList into the row column format needed for a 2d array
      int row = index/width; 
      int col = index%width;

      //make the random position a mine (value of 9) on 2d array board
      board[row][col] = 9;
    }
  }

  /*
   * Private helper method for the contructor that fills board for the numbers of neighboring mines
   * @return void
   */
  private void fillNumbers(){
    //traversing the 2d array
    for (int row = 0; row < board.length; row++){
      for (int col = 0; col < board[row].length; col++){

        //make sure the element is not a mine and has not been counted already
        if (board[row][col] == 0){

          //set up count variable for number of neighboring mines
          int count = 0;
          
          //check the element above (making sure the row will be in bounds if we subtract 1)
          if (row > 0){
            //checks if the neighbour is a mine (9)
            if (board[row-1][col] == 9){
              count++;
            }
          } 
          
          //check the element below (making sure the row will be in bounds if we add 1)
          if (row < board.length-1){
            if (board[row+1][col] == 9){
              count++;
            }
          }
          
          //check the element to the left (making sure col will be in bounds if we subtract 1)
          if (col > 0){
            if (board[row][col-1] == 9){
              count++;
            }
          }
          
          //check the element to the right (making sure col will be in bounds if we add 1)
          if (col < board[row].length-1){
            if (board[row][col+1] == 9){
              count++;
            }
          }
          
          //check the upper-left element (making sure both row and col will be in bounds)
          if (row > 0 && col > 0){
            if (board[row-1][col-1] == 9){
              count++;
            }
          }
          
          //check the upper-right element (making sure both row and col will be in bounds)
          if (row > 0 && col < board[row].length-1){
            if (board[row-1][col+1] == 9){
              count++;
            }
          }
          
          //check the lower-left element (making sure both row and col will be in bounds)
          if (row < board.length-1 && col > 0){
            if (board[row+1][col-1] == 9){
              count++;
            }
          }
          
          //check the lower-right element (making sure both row and col will be in bounds)
          if (row < board.length-1 && col < board[row].length-1){
            if (board[row+1][col+1] == 9){
              count++;
            }
          }

          //put the count into 2d array board (put an empty string before count to prevent a different data type error)
          board[row][col] = count;
        }
      }
    }
  }

  /*
   * Accessor method for the generated 2d array board
   * @return String[][] board
   */  
  public int[][] getBoard(){
    return board;
  }

  /*
   * Accessor method for the number of mines on the board
   * @return int mineCount
   */
  public int getMineCount(){
    return mineCount;
  }
  
  /*
   * Converts the board into a String for testing purposes
   * @overrides toString
   * @precondition board has at least 1 row
   * @return 2d array board as a string
   */
  public String toString(){
    String s = Arrays.toString(board[0]);
    for (int row = 1; row < board.length; row++){
      s += "\n" + Arrays.toString(board[row]);
    }
    return s;
  }

}