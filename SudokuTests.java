/*Maya Purohit
 * CS231
 * Project 05
 * 03/12/23
 * The purpose of SudokuTests.java is to test the methods inside of the board class 
 * To run java -ea SudokuTests
 */

public class SudokuTests {

    public static void main(String[] args) throws InterruptedException {
         // case 1: testing Sudoku(int k), Sudoku(String filename)
         {
            // setup
           Sudoku sudoku = new Sudoku(5);
           Sudoku sudoku2 = new Sudoku("board2.txt");
           int numLocked = 0;

            // verify
            System.out.println(sudoku2 + " != null");
            for(int i = 0; i < Board.SIZE; i++){
                for(int j = 0; j < Board.SIZE; j++){
                    if(sudoku.board.get(i,j).isLocked() == true){ //counts the number of cells in the grid that are locked 
                       numLocked++;
                    }
                }
            }
            System.out.println(numLocked + " == 5"); //checks the number of cells that are counted to be locked  
            
            // test
            assert sudoku2 != null: "Error in Sudoku::Sudoku(String filename)";
            assert numLocked == 5 : "Error in Sudoku::Sudoku(int numLocked)";
            
        }

        // case 2: findNextValue() 
        {
            // setup
            Sudoku sudoku = new Sudoku("board2.txt"); //makes the board with the file

            // verify
            System.out.println(sudoku.findNextValue(new Cell(2,3,0)) + " == 1"); //finds different values for the cells 
            System.out.println(sudoku.findNextValue(new Cell(5,8,0)) + " == 2"); 

            


            // test
            assert sudoku.findNextValue(new Cell(2,3,0))  == 1: "Error in Sudoku:: findNextValue()";
            assert sudoku.findNextValue(new Cell(5,8,0))  == 2: "Error in Sudoku:: findNextValue()";
            
            
        }

        // case 3: testing findNextCell()
        {
            // setup
            Sudoku suduko = new Sudoku("board2.txt");

            // verify

            Cell first = suduko.findNextCell(); //calls find next cell for the sudoku class
            Cell second = suduko.findNextCell();
            Cell third = suduko.findNextCell();

            System.out.println(first + " == 1"); //checks to see if all of the values are correct
            System.out.println(second + " == 3");
            System.out.println(third + " == 5");
           


            // test

            assert first.toString().equals("1"): "Error in Sudoku::findNextCell()";
            assert second.toString().equals("3"): "Error in Sudoku::findNextCell()";
            assert third.toString().equals("5"): "Error in Sudoku::findNextCell()";

            
        }

        // case 4: testing solve()
        {
            // setup
            Sudoku solve = new Sudoku(0);
            //Sudoku cantSolve = new Sudoku(0);
    
            // verify
            
            solve.solve(0);
            System.out.println(solve.board.validSolution() + " == true"); //checks to see that the sudoku is solved
            
            

    
    
            // test
            assert solve.board.validSolution()  == true: "Error in Sudoku::solve()";
            
                
        }


       System.out.println("Done Testing Sudoku!");
       
}

}
