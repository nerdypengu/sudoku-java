import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameBoardPanel extends JPanel {
    private static final long serialVersionUID = 1L; // to prevent serial warning
    private SudokuMain sudokuMain;

    // Define named constants for UI sizes
    public static final int CELL_SIZE = 60; // Cell width/height in pixels
    public static final int BOARD_WIDTH = CELL_SIZE * SudokuConstants.GRID_SIZE;
    public static final int BOARD_HEIGHT = CELL_SIZE * SudokuConstants.GRID_SIZE;

    // Board width/height in pixels

    // Define properties

    private Cell[][] cells = new Cell[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];

    private Puzzle puzzle = new Puzzle();

    /** Constructor */
    public GameBoardPanel(SudokuMain sudokuMain) {
        this.sudokuMain = sudokuMain;
        super.setLayout(new GridLayout(SudokuConstants.GRID_SIZE, SudokuConstants.GRID_SIZE)); // JPanel

        // Allocate the 2D array of Cell, and added into JPanel.
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col] = new Cell(row, col);
                super.add(cells[row][col]); // JPanel
            }
        }

        CellInputListener listener = new CellInputListener();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (cells[i][j].isEditable()) {
                    cells[i][j].addActionListener(listener); // For all editable rows and cols
                }
            }
        }

        super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
    }

    /**
     * Generate a new puzzle; and reset the gameboard of cells based on the puzzle.
     * You can call this method to start a new game.
     */
    public void newGame() {
        // Generate a new puzzle
        puzzle.newPuzzle();

        // Initialize all the 9x9 cells, based on the puzzle.
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
            }
        }
        sudokuMain.updateRemainingStatusLabel();
    }

    /**
     * Return true if the puzzle is solved
     * i.e., none of the cell have status of TO_GUESS or WRONG_GUESS
     */
    public boolean isSolved() {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (cells[row][col].status == CellStatus.TO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getRemainingCellsToGuess() {
        int remainingCells = 0;

        // Iterate through each cell in the grid
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Cell cell = cells[row][col];

                // Check if the cell is a cell to guess
                if (cell.getStatus() == CellStatus.TO_GUESS || cell.getStatus() == CellStatus.WRONG_GUESS) {
                    remainingCells++;
                }
            }
        }

        return remainingCells;
    }

    private class CellInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get a reference of the JTextField that triggers this action event
            Cell sourceCell = (Cell) e.getSource();
            // Retrieve the int entered
            int numberIn = Integer.parseInt(sourceCell.getText());
            // For debugging
            System.out.println("You entered " + numberIn);
            if (numberIn == sourceCell.getNumber()) {
                sourceCell.setStatus(CellStatus.CORRECT_GUESS);
                sudokuMain.updateRemainingStatusLabel();
            } else {
                sourceCell.setStatus(CellStatus.WRONG_GUESS);
            }
            sourceCell.paint(); // re-paint this cell based on its status
            if (isSolved()) {
                JOptionPane.showMessageDialog(null, "Congratulations! You've solved the puzzle!");
            }
        }
    }
}