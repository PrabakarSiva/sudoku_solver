
public class SudokuSolverTest extends SudokuSolver{

	public static void main(String[] args) {
		
		//initializing the board which is a 2-d array
		int[][] board = {
				  { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
				  { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
				  { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
				  { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
				  { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
				  { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
				  { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
				  { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
				  { 0, 0, 0, 0, 8, 0, 0, 7, 9 } 
				};
		
		//initializing the solver and printing original board for output
		SudokuSolver solver = new SudokuSolver();
		System.out.println("Printing original board...");
		System.out.println("-------------------------");
		solver.printBoard(board);
		
		//solving the board and recording solver's execution time
		final long startTime = System.currentTimeMillis();
		solver.solve(board);
		System.out.println("Printing solved board...");
		System.out.println("-------------------------");
		solver.printBoard(board);
		final long endTime = System.currentTimeMillis();
		System.out.println("Sudoku puzzle solved in " + (endTime - startTime) + "ms.");
		

	}

}
