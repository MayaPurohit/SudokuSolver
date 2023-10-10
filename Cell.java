/*Maya Purohit
 * CS231
 * Project 04
 * 03/06/23
 * The purpose of this class is to create an object that represents a cell in the sudoku board
 */


import java.awt.Graphics;
import java.awt.Color;

public class Cell {
    
    private int rowIndex; //fields for the cell class
    private int colIndex;
    private int valueCell;
    private boolean lockedCell;


    public Cell(){
        //initializes all of the fields to zero or false
        rowIndex = 0;
        colIndex = 0;
        valueCell = 0;
        lockedCell = false;
    }

    public Cell(int row, int col, int value){
        //initializes all of the values to the values that are entered into the constructor
        rowIndex = row;
        colIndex = col;
        valueCell = value;
        lockedCell = false;
    }


    public Cell(int row, int col, int value, boolean locked){
        //initializes all of the values to the values entered into the constructor
        rowIndex = row;
        colIndex = col;
        valueCell = value;
        lockedCell = locked;

    }

    public int getRow(){
        //returns the row index
        return rowIndex;
    }
    
    public int getCol(){
        //returns the column index
        return colIndex;
    }

    public int getValue(){
        //returns the value of the cell
        return valueCell;
    }

    public void setValue(int newVal){
        //changes the value of the cell
        valueCell = newVal;
    }

    public boolean isLocked(){
        //returns if the cell is locked or not
        return lockedCell;
    }

    public void setLocked(boolean lock){
        //chnages if the cell is locked or not
        lockedCell = lock;
    }


    public String toString(){
        return "" + valueCell;
    }

    public void draw(Graphics g, int x, int y, int scale){
        char toDraw = (char) ((int) '0' + getValue());
        g.setColor(isLocked()? Color.BLUE : Color.RED);
        g.drawChars(new char[] {toDraw}, 0, 1, x, y);
    }




}
