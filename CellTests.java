/*Maya Purohit
 * CS231
 * Project 05
 * 03/06/23
 * The purpose of CellTests.java is to tests the methods inside of the cell class 
 * To run java -ea CellTests
 */

public class CellTests {

    public static void main(String[] args) {
        // case 1: testing Cell(), Cell(int row, int col, int value), Cell(int row, int col, int value, boolean locked)
        {
            // setup
            Cell cell1 = new Cell();
            Cell cell2 = new Cell(1,2,5);
            Cell cell3 = new Cell(1,2,5,false);

            // verify
            System.out.println(cell1 + " != null");
            System.out.println(cell2 + " != null");
            System.out.println(cell3 + " != null"); //checks to make sure that the cell exists 
            
            // test
            assert cell1 != null : "Error in Server::Cell()";
            assert cell2 != null : "Error in Server::Cell(int row, int col, int value)";
            assert cell3 != null : "Error in Server::Cell(int row, int col, int value, boolean locked)";
            
        }

        // case 2: testing getRow() 
        {
            // setup
            Cell cell = new Cell(3,4,5);

            // verify
            System.out.println(cell.getRow() + " == 3");


            // test
            assert cell.getRow() == 3 : "Error in Cell::getRow()";
            
        }

        // case 3: testing getCol() 
        {
            // setup
            Cell cell = new Cell(3,4,5);

            // verify
            System.out.println(cell.getCol() + " ==  4");


            // test
            assert cell.getCol() == 4 : "Error in Cell::getCol()";
            
        }

        // case 4: testing getValue() and setValue()
        {
            // setup
            Cell cell = new Cell(3,4,5);
    
            // verify
            System.out.println(cell.getValue() + " == 5"); //checks for the right value
            int value = cell.getValue();

            cell.setValue(7);
            System.out.println(cell.getValue() + " ==  7" );
    
    
            // test
            assert cell.getValue() == 7 : "Error in Cell::getValue() or setValue()";
            assert value == 5 : "Error in getValue()";
                
        }

        // case 5: testing isLocked() and setLocked()
            {
                // setup
                Cell cell = new Cell(3,4,5, false);
        
                // verify
                System.out.println(cell.isLocked() + " == false");
                boolean locked = cell.isLocked();
                
    
                cell.setLocked(true);
                System.out.println(cell.isLocked() + " == true"); //checks to make sure the cells can be locked
        
        
                // test
                assert cell.isLocked() == true: "Error in Cell::setLocked() or isLocked()";
                assert locked  == false : "Error in isLocked()";
                    
            }

            // case 6: testing getValue() and setValue()
            {
                // setup
                Cell cell = new Cell(3,4,5);
    
                // verify
                System.out.println(cell.toString() + " == 5"); //checks toString method

            
    
                // test
                assert cell.toString().equals("5") : "Error in Server:: toString()";

                
            }


        System.out.println("Done testing Cells!");
    }
    
}

    

