import java.util.ArrayList;

public class SudokuSolver {
	
	//constant initialization
	private static final int BOARD_SIZE = 9;		//Sudoku is 9x9
	private static final int START_INDEX = 0;		//used for solve so we start at (0,0)
	private static final int SMALLBOARD_SIZE = 3;	//used for checking that the smaller boards are solved
	private static final int MIN_VAL = 1;			//minimum possible value of any square is 1
	private static final int MAX_VAL = 9;			//maximum possible value of any square is 9
	private static final int UNSOLVED_SQUARE = 0;	//0 is used to indicate unsolved arrays
	
	//solve method that solves the board and returns true when fully solved
	protected boolean solve(int[][] board) {
		for (int row = START_INDEX; row < BOARD_SIZE; row++) {
			for (int column = START_INDEX; column < BOARD_SIZE; column++) {
				if(board[row][column] == UNSOLVED_SQUARE) {
					for (int currentValue = MIN_VAL; currentValue <= MAX_VAL; currentValue++) {
						board[row][column] = currentValue; //sets unsolved square to currentValue which goes from 1-9
						if (isValid(board, row, column) && solve(board)) { //checks if currentValue is valid and then sees if it can solve the rest of the board with currentValue
							return true;
						}
						board[row][column] = UNSOLVED_SQUARE; //if above checks fail square returns to being unsolved and we try again with another number
					}
					return false;
				}
			}
		}
		return true;
	}
	
	//checks to see if two of the same numbers exist in the given row, column, or smaller board
	//returns true if there are no conflicts in the row, column, or subsection of the specific position, otherwise returns false
	private boolean isValid(int[][] board, int row, int column) {
		ArrayList<Integer> usedList = new ArrayList<Integer>();	//used to see if a certain number is used beforehand
		
		for (int columnIndex = 0; columnIndex < BOARD_SIZE; columnIndex++) { //checking row first so we iterate through all the numbers in the row by changing column
			if (usedList.contains(board[row][columnIndex]) && board[row][columnIndex] != UNSOLVED_SQUARE) { 
				return false;
			}
			else {
				usedList.add(board[row][columnIndex]); //adds value to usedList so that we can check if the same value appears again
			}
		}
		usedList.clear(); //we clear usedList so we can use the same ArrayList for column and smallboard checks
		
		for (int rowIndex = 0; rowIndex < BOARD_SIZE; rowIndex++) {	//same implementation as row-checking but now we are column-checking
			if (usedList.contains(board[rowIndex][column]) && board[rowIndex][column] != UNSOLVED_SQUARE) {
				return false;
			}
			else {
				usedList.add(board[rowIndex][column]);
			}
		}
		usedList.clear();
		
		int smallboardRowIndex = (row / SMALLBOARD_SIZE) * SMALLBOARD_SIZE; //so that we always start at the top left of the small board Ex: (7 / 3) * 3 = 6
		int smallboardColumnIndex = (column / SMALLBOARD_SIZE) * SMALLBOARD_SIZE;
		for (int rowIndex = smallboardRowIndex; rowIndex < (smallboardRowIndex + SMALLBOARD_SIZE); rowIndex++) { //our smallboard is 3x3 so smallboardRowIndex + SMALLBOARD_SIZE gives us 3 rows
			for (int columnIndex = smallboardColumnIndex; columnIndex < (smallboardColumnIndex + SMALLBOARD_SIZE); columnIndex++) { //smallboardcolumnIndex + SMALLBOARD_SIZE gives us 3 columns making a 3x3
				if (usedList.contains(board[rowIndex][columnIndex]) && board[rowIndex][columnIndex] != UNSOLVED_SQUARE) { //same implementation as the others
					return false;
				}
				else {
					usedList.add(board[rowIndex][columnIndex]);
				}
			}
		}
		
		return true;
	}
	
	//used to print the board
    protected void printBoard(int[][] board) {
        for (int row = START_INDEX; row < BOARD_SIZE; row++) {
            for (int column = START_INDEX; column < BOARD_SIZE; column++) {
                if (column == 0)
                	System.out.print("|" + " ");
            	System.out.print(board[row][column] + " ");
                if ((column + 1) % SMALLBOARD_SIZE == 0) {
                	System.out.print("|" + " ");
                }

            }
            System.out.println();
            if ((row + 1) % SMALLBOARD_SIZE == 0) {
            	System.out.println("-------------------------");
            	
            }
        }
    }
}