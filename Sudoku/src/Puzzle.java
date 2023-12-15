/* KELOMPOK 10
 * Eugenia Indrawan - 5026221020
 * Ashila Mahdiyyah - 5026221148
 * Razi Alvaro Arman - 5026221168
 * 
*/

public class Puzzle {
    public int difficultyLevel;
    int[][] numbers = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    boolean[][] isGiven = new boolean[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];

    // Constructor
    public Puzzle() {
        super();

    }

    public void newPuzzle(int difficulty) {
        difficultyLevel = difficulty;
        SudokuGenerator sudokuGenerator = new SudokuGenerator();
        SudokuSolver solveit = new SudokuSolver();
        int[][] basis = sudokuGenerator.generate(difficultyLevel);
        boolean[][] hardcodedIsGiven = convertBoolean(basis);
        int[][] hardcodedNumbers = solveit.solve(basis);

        // Copy from hardcodedNumbers into the array "numbers"
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                numbers[row][col] = hardcodedNumbers[row][col];
            }
        }

        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                isGiven[row][col] = hardcodedIsGiven[row][col];
            }
        }

        printBoard(hardcodedNumbers);
    }

    public boolean[][] convertBoolean(int[][] daSudoku) {
        boolean[][] converted = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (daSudoku[i][j] != 0) {
                    converted[i][j] = true;
                } else {
                    converted[i][j] = false;
                }
            }
        }
        return converted;
    }

    private static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

}
