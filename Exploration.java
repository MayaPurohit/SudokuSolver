/*Maya Purohit
 * CS231
 * Project 05
 * 03/06/23
 * The purpose of Exploration.java is to find the relationship between the initial values placed on the board and the time it takes for the boards to solve and how many are solved
 */

public class Exploration {

    public static void main(String[] args) throws InterruptedException{
        int numOfFinishes = 0;
        for(int j = 0; j < 100; j++){ //repeats the experiment 100 times 
            Sudoku sudoku = new Sudoku(Integer.parseInt(args[0])); //makes a board with a specific number of values 
            sudoku.solve(0);
            if(sudoku.board.validSolution()){
                numOfFinishes++; //counts the number of times the board finishes 
            }
        }
    
        
        System.out.println("The number of boards were completed is: " + numOfFinishes + " out of 100"); //prints out the number of boards finished
            
        numOfFinishes = 0; //resets the value

        }

    
    
}
