# SudokuSolver

## **Overview**:
In this project, we explored the concepts of Stacks and Depth First Search. A stack describes the structure of a list of data. The stack interface can be applied to any type of list structure and 
contains a pop() method, which removes the item at front of the list, a peek() method, which returns the item at the front of the list without removing it, a size() method, which returns the number of items in the 
list, and a push() method, which adds an item at the front of the list. In a stack, we can only add and remove items from the front of the list, indicating that the last item to be added to the list is the first item 
that will be removed. In this project, we used a stack to perform a depth first search algorithm. Depth first search is an algorithm that searches for the next valid step in a problem set and backtracks to change previous steps when no possible solutions 
can be found given the current state of the problem. For this project, when solving a Sudoku board, we iterated through each cell, which is represented as a number on the board, found a value that would be valid for it depending upon its surrounding cells, 
and then assigned the value to the cell. The cell was then added to the top of the stack and the next cell was found. If there were no valid values for the new cell, the method backtracked. The previous cell was popped off of the stack and a new value was 
found for it. Implementing a stack structure was useful for this project because when there was no valid value for a cell, the stack allowed us to easily access the most recent cell that we evaluated in the board and change its value. 
The stack structure and depth first search algorithm allowed us to backtrack in small increments and make changes when there are no viable solutions to solve the board. The final result of this project was a class 
that can solve a sudoku board when possible, using the depth first search algorithm.

## Process
In order to solve the board, we first needed to make a Cell Class. Each value on the Sudoku board represents a cell. Each cell has attributes that represent its row index, its column index, its value, 
and whether or not it is locked. If a cell is locked (blue values in Figure 1), its value cannot be changed. The board object is a 9x9 2D array of cells. 
In order to initialize a board, we add cells to every row and column combination on the 9x9 board and give it an initial value. The board class has 3 constructors, one that makes a blank board, 
one that reads in a text file and one that will randomly place values on the board in positions that are valid. The number of random values that are added to the board can be specified in the constructor of the
board. In addition to methods that allow us to set and get the value of a cell based on its row and column, we created a validValue() method. The valid value took row, column, and value as parameters and checked 
to see if the value from the parameter was valid for the specified position. In order for a value to be valid, the value should be unique in its row, column, and 3x3 square in the sudoku box. The valid value method 
returns a boolean if the value is valid at the row and column that were entered. The board class also has a validSolution() method that loops through every cell in the board and checks that every value on the board 
is valid and between 1 and 9. 

The sudoku class has a board as a field and contains methods to solve the board. The findNextValue() method takes in a cell as a parameter and looks at its value. It will then loop through every value 
that is between the cell’s current value and 9 and will return the first value that is deemed to be valid by the validValue() method of the board class. If no value is valid, 0 is returned. 
The findNextCell() method loops through the cells on the board until it finds a cell with a value of 0. It will then call the findNextValue() method for the cell and will set the value of the cell to be the 
new value that is found by the method. If 0 is returned by the findNextValue method, then null is returned by findNextCell(). Otherwise, the cell is returned. Finally, the solve method in the sudoku class 
implements the depth first search algorithm with a stack object. The method will loop through the cells in the board that do not have a value and will look for the values that will solve the board. 
It will then add the cell to the stack. If a value cannot be found for one of the cells, the method will backtrack by popping the previous cell off of the stack that was pushed in and will look for a 
new value for it. This method will continue until the board is deemed to have no solution or until it is solved. Figure 1 shows the solved Sudoku board, which was created by the solve method and the backtracking 
algorithm.
