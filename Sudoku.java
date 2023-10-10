
/*Maya Purohit
 * CS231
 * Project 05
 * 03/06/23
 * The purpose of Sudoku.java is to solve the sudoku board that is entered into the class 
 */
import java.util.Random;

public class Sudoku {

    public Board board;
    LandscapeDisplay ld;


    public Sudoku(int k) {
        // constructor for the sudoku class
        board = new Board(k);
        ld = new LandscapeDisplay(board);
    }

    public Sudoku(String filename){
        //creates a sudoku board with a file name
        board = new Board(filename);
        ld = new LandscapeDisplay(board);

    }

    public int findNextValue(Cell cell) {
        // finds the next valid value for the cell
        int value = cell.getValue();

        for (int i = value + 1; i < 10; i++) {
            boolean valid = board.validValue(cell.getRow(), cell.getCol(), i); // checks to see if the value is valid at the cell's location
            if (valid == true) {
                return i;
            }

        }

        return 0; // if none are valid, 0 is returned
    }

    public Cell findNextCell() {
        // returns the next cell to go to
        for (int i = 0; i < Board.SIZE; i++) { // for each position in the grid, a cell is added and is set to have a value of zero
            for (int j = 0; j < Board.SIZE; j++) {
                Cell cell = board.get(i, j);

                if (cell.getValue() == 0) { // if the cell does not have a value
                    int value = this.findNextValue(cell); // find its next value

                    if (value != 0) { // if the returned value is not 0
                        cell.setValue(value); // set the value of the cell to be the value at the position
                        return cell; // return the cell
                    } else {
                        return null;
                    }
                }
            }
        }

        return null; // means that there is no possible solution
    }

    public Cell findNextCellExtension(){
        //returns the cell with the least valid values (extension)
        int minValid = 10;
        Cell returnCell = new Cell();


        for (int i = 0; i < Board.SIZE; i++) { // for each position in the grid, a cell is added and is set to have a value of zero
            for (int j = 0; j < Board.SIZE; j++) {
                Cell cell = board.get(i, j);
                if(cell.getValue() == 0){
                    int numValid = 0;
                    for(int k = 1; k < 10; k++){
                        if(board.validValue(cell.getRow(), cell.getCol(), k)){ //checks how many of the values are valid
                            numValid++;
                        }
                    }
                    if(numValid < minValid){
                        minValid = numValid; //the new minimum valid is equal to the number of valid values for the cell that has the lowest valid values
                        returnCell = cell; //sets the cell to be returned to be the cell with the most valid values
                    }
                }

            }
        }
        int newVal = findNextValue(returnCell);
        if (newVal == 0){
            return null; //if the next value for the board is 0, no cell should be returned
        } else {
            returnCell.setValue(newVal); //the value that is found for the cell is set to be the value for the cell that is returned
            return returnCell; //the cell is returned
        }

    }

    public boolean solve(int delay) throws InterruptedException {
        // uses a stack to find a solution to the sudoku board
        Stack<Cell> stack = new LinkedList<Cell>();
        while (stack.size() < (81 - board.numLocked())) { //while the size of the stack is not equal to the number of cells that need to be solved 
            if (delay > 0){
                Thread.sleep(delay);
            }
            if (ld != null){
                ld.repaint();
            }

            Cell next = findNextCell(); //finds the next cell

            while (next == null && stack.size() > 0) { //if its null
                Cell popped = stack.pop(); //we get a cell from the stack
                int returned = this.findNextValue(popped); //we find its next valid value

                popped.setValue(returned);

                if (popped.getValue() != 0) {
                    next = popped; //next is set to be the cell that was taken from the stack
                }

            }

            if (next == null) {
                board.finished = true; //if it is still null, we are done solving 
                return false; //the board wasn't solved 
            } else {
                stack.push(next); //otherwise add the cell to the stack
            }

        }
        board.finished = true;
        return true;
    }

    public static void main(String[] args) throws InterruptedException{
        Sudoku sudoku = new Sudoku(0);
        System.out.println(sudoku.board);
        sudoku.solve(0);
        System.out.println(sudoku.board);

    }

}
