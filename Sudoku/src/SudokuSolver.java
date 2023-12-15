/* KELOMPOK 10
 * Eugenia Indrawan - 5026221020
 * Ashila Mahdiyyah - 5026221148
 * Razi Alvaro Arman - 5026221168
 * 
*/

public class SudokuSolver {

    public static boolean isBlockValid(int x, int y, int board[][]) {
        return board[x][y] == 0;
    }

    public static boolean isComplete(int board[][]) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0)
                    return false;
            }
        }
        return true;
    }

    public static boolean isMoveValid(int x, int y, int move, int board[][]) {

        if (x < 0 || y < 0 || x > 9 || y > 9)
            return false;

        for (int i = 0; i < 9; i++) {
            if (board[i][y] == move)
                return false;
        }

        for (int i = 0; i < 9; i++) {
            if (board[x][i] == move)
                return false;
        }

        for (int i = (x / 3) * 3; i < (x / 3) * 3 + 3; i++) {
            for (int j = (y / 3) * 3; j < (y / 3) * 3 + 3; j++) {
                if (board[i][j] == move)
                    return false;
            }
        }

        return true;
    }

    public static Point getNext(int x, int y, int board[][]) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0)
                    return new Point(i, j);
            }
        }
        return null;
    }

    public static boolean solve(int x, int y, int move, int board[][]) {

        Point next = getNext(x, y, board);

        if (next != null) {

            for (int i = 1; i < 10; i++) {
                if (isMoveValid(next.x, next.y, i, board)) {
                    board[next.x][next.y] = i;
                    if (solve(next.x, next.y, i, board)) {
                        return true;
                    }
                    board[next.x][next.y] = 0;
                }
            }
        } else
            return true;
        return false;
    }

    public static int[][] solve(int[][] board) {
        int garbageBoard[][] = new int[9][9];
        garbageBoard[0][0] = -1;
        int i = 0, j = 0;
        for (int k = 1; k < 10; k++) {
            if (solve(i, j, k, board))
                return board;
        }
        return garbageBoard;
    }
}