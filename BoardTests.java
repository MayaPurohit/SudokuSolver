/*Maya Purohit
 * CS231
 * Project 05
 * 03/06/23
 * The purpose of BoardTests.java is to test the methods inside of the board class 
 * To run java -ea BoardTests
 */

public class BoardTests {

    public static void main(String[] args) throws InterruptedException {
        // case 1: testing Board(), Board(String filename), Board(int numLock)
        {
            // setup
            Board board = new Board();
            Board board2 = new Board("board2.txt");
            Board board3 = new Board(5);
            int numLocked = 0;

            // verify
            System.out.println(board.get(0,0) + "== 0");
            System.out.println(board2.get(0,1) + " == 2");
            for(int i = 0; i < Board.SIZE; i++){
                for(int j = 0; j < Board.SIZE; j++){
                    if(board3.get(i,j).isLocked() == true){ //counts the number of cells in the grid that are locked 
                       numLocked++;
                    }
                }
            }
            System.out.println(numLocked + " == 5"); //checks to make sure that the server exists 
            
            // test
            assert board.get(0,0).toString().equals("0") : "Error in Board::Board()";
            assert board2.get(0,1).toString().equals("2") : "Error in Board::Board(String filename)";
            assert numLocked == 5 : "Error in Board::Board(int numLocked)";
            
        }

        // case 2: testing get() and read() 
        {
            // setup
            Board board = new Board("board2.txt");

            // verify
            System.out.println(board.get(0,0) + " == 0"); //cheks to see the right value is correct with preexisting board
            System.out.println(board.get(2,3) + " == 1");
            System.out.println(board.get(4,2) + " == 6");
            


            // test
            assert board.get(0,0).toString().equals("0") : "Error in Board::get()";
            assert board.get(2,3).toString().equals("1") : "Error in Board::get()";
            assert board.get(4,2).toString().equals("6") : "Error in Board::get()";
            
        }

        // case 3: testing set() and set(boolean locked) and read()
        {
            // setup
            Board board = new Board("board2.txt");

            // verify
            board.set(1,0,4, true);
            board.set(2,4,8, false);
            board.set(0,1,3);

            System.out.println(board.get(1,0) + " == 4"); //checks to see if the values were set only if the cell was not locked
            System.out.println(board.get(2,4) + " == 8");
            System.out.println(board.get(0,1) + " == 2");
            
            System.out.println(board.get(1,0).isLocked() + " == true");
            System.out.println(board.get(2,4).isLocked() + " == false");


            // test
            assert board.get(1,0).toString().equals("4") : "Error in Board::set()";
            assert board.get(1,0).isLocked() == true : "Error in Board: set(boolean locked)";
            assert board.get(2,4).toString().equals("8") : "Error in Board::set()";
            assert board.get(0,1).toString().equals("2") : "Error in Board::set()";
            
        }

        // case 4: testing getRows() and getCols()
        {
            // setup
            Board board = new Board();
    
            // verify
            
            System.out.println(board.getRows() + " == 9"); //checks that the value of 9 is printed 
            System.out.println(board.getCols() + " == 9");

    
    
            // test
            assert board.getRows() == 9 : "Error in Board::getRows()";
            assert board.getCols() == 9 : "Error in Board::getCols()";
            
                
        }

        // case 5: testing isLocked() and numLocked() and read()
        {
            // setup
            Board board2 = new Board("board2.txt");
        
            // verify
            System.out.println(board2.isLocked(0,0) + " == false"); //checks to see if the correct cells are locked 
            System.out.println(board2.isLocked(2,3) + " == true");
            System.out.println(board2.numLocked() + " == 20");
            
        
        
            // test
            assert board2.isLocked(0,0) == false : "Error in Board::isLocked()";
            assert board2.isLocked(2,3) == true  : "Error in Board::isLocked()";
            assert board2.numLocked() == 20  : "Error in Board::numLocked()";

                    
            }

        // case 6: value()
        {
            // setup
            Board board = new Board("board2.txt");

    
            // verify
            System.out.println(board.value(2,3) + " == 1"); //checks to see if the correct value is returned 
            System.out.println(board.value(6,0) + " == 9");

            
    
            // test
            assert board.value(2,3) == 1 : "Error in Board:: value()";
            assert board.value(6,0) == 9 : "Error in Board:: value()";

                
        }

        // case 7: set()
        {
            // setup
            Board board = new Board();

    
            // verify

            board.set(2,3, 8, false);
            board.set(3,8,5, true);


            System.out.println(board.get(2,3) + " == 8"); //checks the value and lock setting capabilities of the method
            System.out.println(board.isLocked(2,3) + " == false");
            System.out.println(board.get(3,8) + " == 5");
            System.out.println(board.isLocked(3,8) + " == true");

            
    
            // test
            assert board.get(2,3).toString().equals("8") : "Error in Board:: set or get()";
            assert board.isLocked(2,3) == false  : "Error in Board:: set() or isLocked";
            assert board.get(3,8).toString().equals("5"): "Error in Board:: set()";
            assert board.isLocked(3,8) == true  : "Error in Board::set()";

                
        }

        //case 8: validValue()
        {
            // setup
            Board board = new Board("board2.txt");

    
            // verify

            System.out.println(board.validValue(0,0,2) + " == false"); //checks that the valid value is working by using a preexisting board

            System.out.println(board.validValue(0,2,4) + " == false"); //the value here should not be valid
            System.out.println(board.validValue(1,8,6) + " == true"); 

            
    
            // test
            assert board.validValue(0,0,2) == false : "Error in Board:: validValue()";
            assert board.validValue(0,2,4) == false   : "Error in Board:: validValue()";
            assert board.validValue(1,8,6) == true : "Error in Board:: validValue()";
            

                
        }

        //case 8: validSolution()
        {
            // setup
            Sudoku sudoku = new Sudoku(0);
            Sudoku sudoku2 = new Sudoku(40);
           

    
            // verify

            sudoku.solve(0); //calls the solve method of the sudoku class to see if the board will return when the board has been solved. 
            sudoku2.solve(0);

            System.out.println(sudoku.board.validSolution() + " == true");

            System.out.println(sudoku2.board.validSolution() + " == false");
            
            
    
            // test

            assert sudoku.board.validSolution() == true: "Error in Board:: validSolution"; 
            assert sudoku2.board.validSolution() == false: "Error in Board:: validSolution";                 

                
        }




        

        System.out.println("Done testing Board!");
        
    }
    
}
