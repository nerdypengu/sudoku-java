public class Puzzle {
    public int difficultyLevel = 1;
    // All variables have package access
    // The numbers on the puzzle
    int[][] numbers = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    // The clues - isGiven (no need to guess) or need to guess
    boolean[][] isGiven = new boolean[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];

    // Constructor
    public Puzzle() {
        super();

    }

    // Generate a new puzzle given the number of cells to be guessed, which can be
    // used
    // to control the difficulty level.
    // This method shall set (or update) the arrays numbers and isGiven
    public void newPuzzle() {
        SudokuGenerator sudokuGenerator = new SudokuGenerator();
        SudokuSolver solveit = new SudokuSolver();
        int[][] basis = sudokuGenerator.generate(difficultyLevel);
        // Need to use input parameter cellsToGuess!
        boolean[][] hardcodedIsGiven = convertBoolean(basis);
        int[][] hardcodedNumbers = solveit.solve(basis);

        // Copy from hardcodedNumbers into the array "numbers"
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                numbers[row][col] = hardcodedNumbers[row][col];
            }
        }

        // Copy from hardcodedIsGiven into array "isGiven"
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

    public void setDiff(int i) {
        difficultyLevel = i;
    }

}
