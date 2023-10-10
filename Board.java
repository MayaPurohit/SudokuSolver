import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*Maya Purohit
 * CS231
 * Project 04
 * 03/06/23
 * The purpose of Board.java is to create an object that represents a sudoku board 
 */

import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;

public class Board {

    private Cell[][] board;
    public static final int SIZE = 9; //belongs to the class but not the object 
    private int numLocked;
    public boolean finished;


    public Board(){
        board = new Cell[Board.SIZE][Board.SIZE]; //creates a two by two grid of cell objects
        for(int i = 0; i < Board.SIZE; i++){ //for each position in the grid, a cell is added and is set to have a value of zero
            for(int j = 0; j < Board.SIZE; j++){
                board[i][j] = new Cell(i, j, 0);
            }
        }

        numLocked = 0;
        finished = false;

    }

    public Board(String filename){
        //constructor that calls the read function of the board class with a given file 
        board = new Cell[Board.SIZE][Board.SIZE];
        for(int i = 0; i < Board.SIZE; i++){
            for(int j = 0; j < Board.SIZE; j++){
                board[i][j] = new Cell(i, j, 0);
            }
        }

        read(filename); //calls the read file function that reads the board onto the sudoku board
        finished = false;

    }

    public Board(int numLock){
        //constructor that creates a randomly generated board with a set number of fixed cells
        Random rand = new Random();
        numLocked = numLock; //sets the number of cells locked to be the number inputted as a parameter
        board = new Cell[Board.SIZE][Board.SIZE];
        for(int i = 0; i < Board.SIZE; i++){
            for(int j = 0; j < Board.SIZE; j++){
                board[i][j] = new Cell(i, j, 0); //sets all of the values on the board to be 0
            }
        }

        for(int i = 0; i < numLock; i++){
            //sets random positons of the board to be locked with a random value
            int row = rand.nextInt(9); //random row
            int col = rand.nextInt(9); //random column
            int value = rand.nextInt(1,10);
            if(this.validValue(row, col, value) == true && this.get(row, col).getValue() == 0){ //checks to make sure that the randomly chosen value is valid at the location
                this.set(row, col, value, true);
            }
            else{
                numLock ++; //if the value is not valid, the number of iterations is increased by one.
            }

            finished = false;
            
            
            
        }


    }

    public Cell get(int row, int col){
        //returns the cell at a certain position 
        return board[row][col];
    }

    public void set(int row, int col, int value){
        //sets the value at a specific position to be a certain value 
        if(board[row][col].isLocked() == false){
            board[row][col].setValue(value);
        }
    }

    public void set(int row, int col, boolean locked){
        //sets if the cell is locked on the board or not
        board[row][col].setLocked(locked);
    }
    
    public int getCols(){
        //returns the number of columns in the grid
        return Board.SIZE;
    }

    public int getRows(){
        //returns the number of rows in the grid
        return Board.SIZE;
    }

    public boolean isLocked(int r, int c){
        //returns if the cell at a specific location is locked or not 
        return board[r][c].isLocked();
    }

    public int numLocked(){
        //returns the number of locked cells.
        return numLocked;


    }

    public int value(int r, int c){
        //returns the value at a specific index

        return board[r][c].getValue();
    }

    public void set(int row, int col, int value, boolean locked){
        //sets if the cell is locked on the board or not and its value
        board[row][col].setValue(value);
        board[row][col].setLocked(locked);
    }

    public boolean validValue(int row, int col, int value){
        int rowIndex = row - row % 3; //represents the index of the row that is at the top of the 3x3 grid

        int colIndex = col - col % 3; //represents the index of the column that is at the top of the 3x3 grid


        if(value > 9 || value < 1){
            return false; //if the value is not between 1 and 9 inclusive, return false 
        } 

        // if(board[row][col].isLocked() == true){
        //     return false;
        // }
        for(int c = 0; c < this.getCols(); c++){
            if(c == col){ //if the column is the same column as the location we are testing, then we should skip
                continue;
            }
            if(board[row][c].getValue() == value){ //checks to see if any of the values in the row are the same 
                return false;
            }

        }

        for(int r = 0; r < this.getRows(); r++){
            if(r == row){
                continue;
            }
            if(board[r][col].getValue() == value){ //checks to see if any values in the columns are the same as the new value 
                return false;
            }

        }


        for(int r = rowIndex; r < rowIndex + 3; r++){  //checks to see if any of the values in the cell's 3x3 grid are the same 
            for(int c = colIndex; c < colIndex + 3; c++){
                if(r == row){
                    continue;
                }
                if(c == col){ //if the column is the column of the area we are testing, we should continue 
                    continue;
                }
                if(board[r][c].getValue() == value){
                    return false;
                }
            }
        }



        return true; //otherwise the cell is valid 

    }

    public boolean validSolution(){
        //returns if the board is solved
        for(int i = 0; i < Board.SIZE; i++){ //for each position in the grid, a cell is added and is set to have a value of zero
            for(int j = 0; j < Board.SIZE; j++){
                if(this.validValue(i, j, board[i][j].getValue()) == false || board[i][j].getValue() < 1 || board[i][j].getValue() > 9){ //if the value is not valid or the value is not between 1 and 9
                    return false; //the solution is not valid
                }
            }
        }

        return true;
    }




    public boolean read(String filename) {
        //reads a file into the sudoku board that we have 
        try {
          // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
          FileReader fr = new FileReader(filename);
          // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
          BufferedReader br = new BufferedReader(fr);
          // create a new Board object, which we'll update with the contents of the file and return after parsing.
          Board output = new Board();
          
          // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
          String line = br.readLine();
          // start a while loop that loops while line isn't null
          int numCalls = 0;
          while(line != null){
              // print line
              //System.out.println(line);
              // assign to an array of Strings the result of splitting the line up by spaces (line.split("[ ]+"))
              String[] lines = line.split("[ ]+");
              // print the size of the String array (you can use .length)
              //System.out.println(lines.length);
              // use the line to set various Cells of output accordingly
              for(int i = 0; i < lines.length; i++){
                board[numCalls][i].setValue(Integer.parseInt(lines[i]));;
                if(board[numCalls][i].getValue() != 0){
                    board[numCalls][i].setLocked(true);
                    numLocked ++;
                }
              }
              // assign to line the result of calling the readLine method of your BufferedReader object.
              line = br.readLine();
              numCalls++;
          }
          // call the close method of the BufferedReader
          br.close();
          return true;
        }
        catch(FileNotFoundException ex) {
          System.out.println("Board.read():: unable to open file " + filename );
        }
        catch(IOException ex) {
          System.out.println("Board.read():: error reading file " + filename);
        }
    
        return false;
      }

      public String toString(){
        //returns a textual representation of the sudoku board
        String output = "";
        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if(j % 3 == 2){ //condition to separate the suduko board into 3x3 cells 
                    output += board[i][j] + "   ";
                }
                else{
                    output += board[i][j] + " ";
                }
                
                
    
            }
            if(i % 3 == 2){ //separates the rows if i is equal to a certain number 
                output += "\n\n";   

            }
            else{
                output += "\n";
            }
            
        }

        return output; //returns the final string 

      }

      public void draw(Graphics g, int scale){
        //draws the board for the viewer to see
        for(int i = 0; i<getRows(); i++){
            for(int j = 0; j<getCols(); j++){
                get(i, j).draw(g, j*scale+5, i*scale+10, scale);
                
            }
        } if(finished){ //checks to see if the board is done solving 
            if(validSolution()){
                g.setColor(new Color(0, 127, 0));
                g.drawChars("Hurray!".toCharArray(), 0, "Hurray!".length(), scale*3+5, scale*10+10);
            } else {
                g.setColor(new Color(127, 0, 0));
                g.drawChars("No solution!".toCharArray(), 0, "No Solution!".length(), scale*3+5, scale*10+10);
            }

            for(int i = 0; i < 3; i++){
                g.drawLine(3*scale*i, 0, 3*scale*i, 9*scale);
                g.drawLine(0, 3*scale*i, 9*scale, 3*scale*i);

            }
        }
    }

      public static void main(String[] args){
        Board board = new Board(args[0]);
        System.out.println(board.numLocked());

        System.out.println(board); //tests for the board
        System.out.println(board.validValue(1,4,8));
        System.out.println(board.get(5,6));
        System.out.println(board.isLocked(5,6));
        System.out.println(board.value(2,3));
        

        
        
      }








    
}
