//Rohan Menezes and Roshan Narayan
//Fun-Asg4: Shape intersection

/**
   REFLECTION/SELF-EVALUATION
   
   Code:
   This project was pretty hard to code. I took a long time to learn how 
   MouseListener works and how to implement it in the program. It also took a 
   while to figure out the logic of how to implement the Tic-Tac-Toe game. 
   However, in hindsight, it all seems so simple. Now that we know the 
   intricacies of MouseListener, all this code seems somewhat elementary. 
   
   Strengths:
   This game is almost entirely bug-free. After extensive testing, we have 
   found many bugs and fixed them all. Furthermore, we have gone above and 
   beyond the project requirements to create a great experience for the user. 
   This game not only allows the user to play a game of Tic-Tac-Toe, but allows 
   him/her to play multiple games and count the overall score. Furthermore, our 
   simple, yet comprehensive display makes it easy for the user to learn to play 
   the game and play it correctly. 
   
   Weaknesses: 
   Since we have tested this game a lot, there aren't many weaknesses. The only 
   glaring issue I can think of is the display message containing the 
   instructions. Because there is no other method to put it in besides "paint," 
   the instruction message gets displayed before every new game, even if it's the 
   second or third contest in a series. 
   
   Battleship:
   Making a Battleship program would be relatively easy. Since we already have 
   this code that tracks the user's clicks on a grid, we could easily expand the 
   grid to make it a virtual "ocean." From there, we could store the battleship(s)'
   coordinates in a 2-D array and simply check if the user's click matches up 
   with a point on the ship. Because we made this problem, Battleship would not be 
   as hard as if we tried to code it from scratch.
*/

//import statements
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JApplet;

/**
   The TicTacToe_Menezes class both implements the code for the actual tic tac 
   toe game and dsiplays the user interface on the screen. Basically, this 
   class contains the entire program, from the virtual grid to all the graphics 
   commands. 
*/
public class TicTacToe_Menezes extends JApplet implements MouseListener{

  //instance variables
  boolean xTurn = true;
  public JFrame frame;
  int [][] board = new int[3][3];
  int xScore = 0;
  int oScore = 0;
  

  /** sets up the graphics window, draws the tic tac toe grid, and displays 
      the score. */
  public void paint(Graphics g) {
    super.paint(g);
    frame = new JFrame("TicTacToe");
    frame.getContentPane().setBackground(Color.BLUE);
    g.setColor(Color.red);
    g.drawLine(100,0,100,300);
    g.drawLine(200,0,200,300);
    g.drawLine(0,100,300,100);
    g.drawLine(0,200,300,200);
    g.setColor(Color.black);
    String score = "Score:\n X = " + xScore+ ", O = " + oScore;
    g.drawString(score,85,360);
  }
  
  /** updates the virtual grid (that we stored in a 2-D array) for either the 
      x player or o player */
  public void changeArray(int row, int column)
  {
     if(board [row][column] == 0){
        if(xTurn)
           board [row][column] = 1;
        else
           board [row][column] = -1;
        xTurn = !xTurn;
     }
     else
        JOptionPane.showMessageDialog(frame,"This spot has already been " + 
                                      "selected. Please try again.");
  }
  
  /** updates the user interface based on our virtual grid */
  public void displayBoard(Graphics g)
  {
     g.setColor(Color.blue);
     for(int i = 0; i<3; i++) {
        for(int j = 0; j<3; j++) {
           if(board[j][i] == 1) {
              g.drawLine((j*100)+25,(i*100)+25,(j*100)+75,(i*100)+75);
              g.drawLine((j*100)+75,(i*100)+25,(j*100)+25,(i*100)+75);
           }
           else if(board[j][i] == -1)
              g.drawOval(j*100+25,i*100+25,50,50);
        }
     }
  }
  
  /** checks to see if there is a winner and/or if the tic tac toe board is 
      full and displays the appropriate message for the situation */
  public void getResult(){
     String result = "";
     for(int i = 0; i < 3; i++){
        if(board[i][0] + board[i][1] + board[i][2] == 3)
           result = "X";
        if(board[i][0] + board[i][1] + board[i][2] == -3)
           result = "O";
     }
     for(int j = 0; j < 3; j++){
        if(board[0][j] + board[1][j] + board[2][j] == 3)
           result = "X";
        if(board[0][j] + board[1][j] + board[2][j] == -3)
           result = "O";
     }
     if(board[0][0] + board[1][1] + board[2][2] == 3)
        result = "X";
     else if(board[0][0] + board[1][1] + board[2][2] == -3)
        result = "O";
     if(board[2][0] + board[1][1] + board[0][2] == 3)
        result = "X";
     else if(board[2][0] + board[1][1] + board[0][2] == -3)
        result = "O";
     boolean isFilled = true;
     for(int i = 0; i<3; i++){
        for(int j = 0; j<3; j++){
           if(board[j][i] == 0){
              isFilled = false;
           }
        }
     }
     if(isFilled && result.equals("")){
        JOptionPane.showMessageDialog(frame,"Neither player has won the game...");
        playAgain(this.getGraphics());
     }
     else if(!result.equals("")){
        JOptionPane.showMessageDialog(null,result + " has won the game!");
        if(result.equals("X"))
           xScore++;
        else
           oScore++;
        playAgain(this.getGraphics());
     }
  }
  
  /** checks if the user wants to play another tic tac toe game and redirects 
      user to proper screen based on answer */
  public void playAgain(Graphics g){
     int playAgain = JOptionPane.showConfirmDialog(frame,"Would you like to play again?",
                         "Would you like to play again?",JOptionPane.YES_NO_OPTION);
     if(playAgain == JOptionPane.YES_OPTION){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++)
                  board[i][j] = 0;
        }
        update(this.getGraphics());
     }
     else{
        if(xScore > oScore){
           String score = "PLAYER X WINS!!! \n\nFinal Score:\n X = " + xScore+ ", O = " + oScore;
           JOptionPane.showMessageDialog(frame, score);
        }
        else if(oScore > xScore){
           String score = "PLAYER O WINS!!! \n\nFinal Score:\n X = " + xScore+ ", O = " + oScore;
           JOptionPane.showMessageDialog(frame, score);
        }
        else
        {
           String score = "IT'S A TIE!!! \n\nFinal Score:\n X = " + xScore+ ", O = " + oScore;
           JOptionPane.showMessageDialog(frame, score);
        }
     }  
  }
  
  /** insignificant method that we must define to make MouseListener work */
  public void mousePressed(MouseEvent e) {
  }
  
  /** reads in the specific box that the user clicks and updates the virtual 
      grid and user interface to reflect that */
  public void mouseReleased(MouseEvent e) {
     int xpos = (int)(e.getX()/100);
     int ypos = (int)(e.getY()/100);
     if(xpos < 3 && ypos < 3){
        changeArray(xpos, ypos);
        displayBoard(this.getGraphics());
        getResult();
     }
     else
        JOptionPane.showMessageDialog(frame, "Invalid Box. Please try again.");
  }
  
  /** insignificant method that we must define to make MouseListener work */
  public void mouseEntered(MouseEvent e) {
  }
  
  /** insignificant method that we must define to make MouseListener work */
  public void mouseExited(MouseEvent e) {
  }
  
  /** insignificant method that we must define to make MouseListener work */
  public void mouseClicked(MouseEvent e) {
  }
  
  /** initializes the MouseListener object */
  public void init() {
     addMouseListener(this);
     JOptionPane.showMessageDialog(frame,"Welcome to Tic-Tac-Toe by Rohan " +
                                        "Menezes and Roshan Narayan. \nThis " + 
                                        "game requires 2 players: Player X and " + 
                                        "Player O. \nThe object of this game is " + 
                                        "to place 3 of your tokens in a row " + 
                                        "while preventing your opponent from " + 
                                        "placing 3 of his/her tokens in a row." + 
                                        "\nWhen you are able to get 3 in a " + 
                                        "row/column/diagonal, you win! \nWhen " + 
                                        "you decide to quit the game, it will " + 
                                        "display a final tally of each side's " + 
                                        "wins and losses and will show you the " + 
                                        "final winner. \nHave fun and good luck!");
  }
}