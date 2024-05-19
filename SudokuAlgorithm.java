public class SudokuAlgorithm {

    private static final int size = 9;

    protected static int[][] solveSudoku(int[][] board) {
        if (isSolved(board, 0, 0)) {

            return board;
        } else {
            return null;
        }
    }

    private static boolean isSolved(int[][] board, int row, int column) {

        if (row == size - 1 && column == size)
            return true;

        if (column == size) {
            row++;
            column = 0;
        }

        if (board[row][column] != 0)
            return isSolved(board, row, column + 1);

        for (int num = 1; num <= size; num++) {
            if (isAvailable(board, row, column, num)) {
                board[row][column] = num;

                if (isSolved(board, row, column + 1)) {
                    return true;
                }
                board[row][column] = 0; // Backtrack
            }
        }

        return false;
    }

    private static boolean isAvailable(int[][] board, int row, int column, int number) {

        for (int i = 0; i < size; i++) {

            if (board[row][i] == number) {
                return false;
            }
        }

        for (int i = 0; i < size; i++) {

            if (board[i][column] == number) {
                return false;
            }
        }

        int startingRow = row - (row % 3);
        int startingColumn = column - (column % 3);

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (board[startingRow + i][startingColumn + j] == number) {
                    return false;
                }
            }
        }
        return true;
    }
}