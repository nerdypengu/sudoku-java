/* KELOMPOK 10
 * Eugenia Indrawan - 5026221020
 * Ashila Mahdiyyah - 5026221148
 * Razi Alvaro Arman - 5026221168
 * 
*/

import java.util.Stack;

public class SudokuSolver2 {
    public boolean solve(int board[][]) {
        Stack<Cell> stack = new Stack<>();
        boolean[][] isLocked = setLocked(board);
        int curRow = 0;
        int curCol = 0;
        int curValue = 1;
        int time = 0;
        while (stack.size() < 81) {
            time++;
            if (isLocked[curRow][curCol]) {
                Cell lockedCell = new Cell(curRow, curCol, board[curRow][curCol]);
                stack.push(lockedCell);
                curRow = curRow + (curCol + 1) / 9;
                curCol = (curCol + 1) % 9;
                continue;
            }
            for (; curValue <= 9; curValue++) {
                if (isValid(board, curRow, curCol, curValue)) {
                    break;
                }
            }
            if (curValue <= 9) {
                Cell cell = new Cell(curRow, curCol, curValue);
                board[curRow][curCol] = curValue;
                stack.push(cell);
                curRow = curRow + (curCol + 1) / 9;
                curCol = (curCol + 1) % 9;
                curValue = 1;
            } else {
                if (stack.size() > 0) {
                    Cell cell = stack.pop();

                    while (isLocked[cell.row][cell.col]) {

                        if (stack.size() > 0) {

                            cell = stack.pop();
                        } else {
                            System.out.println("Number of steps: " + time);
                            return false;
                        }
                    }

                    curRow = cell.row;

                    curCol = cell.col;

                    curValue = cell.value + 1;

                    board[curRow][curCol] = 0;
                } else {
                    System.out.println("Number of steps: " + time);
                    return false;
                }
            }
        }
        return true;
    }

    public boolean[][] setLocked(int[][] board) {
        boolean[][] isLocked = new boolean[9][9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] != 0) {
                    isLocked[r][c] = true;
                }
            }
        }

        return isLocked;
    }

    public boolean isValid(int[][] board, int row, int col, int currValue) {
        for (int r = 0; r < 9; r++) {
            if (r != row && board[r][col] == currValue) {
                return false;
            }
        }
        for (int c = 0; c < 9; c++) {
            if (c != col && board[row][c] == currValue) {
                return false;
            }
        }
        int rowStartSquare = row - (row % 3);
        int colStartSquare = col - (col % 3);
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (r != row && c != col && board[rowStartSquare + r][colStartSquare + c] == currValue) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SudokuGenerator generator = new SudokuGenerator();
        int[][] basis = generator.generate(1);
        SudokuSolver2 test = new SudokuSolver2();
        test.solve(basis);
        printBoard(basis);
    }
}
