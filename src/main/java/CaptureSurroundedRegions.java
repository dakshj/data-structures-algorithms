class CaptureSurroundedRegions {

    private static int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void captureSurroundedRegions(final char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;

        final int rows = board.length;
        final int cols = board[0].length;

        convertBoundaryOsAndNeighborsToStar(board, rows, cols);

        // After conversion, all remaining 'O's can be safely converted into 'X's,
        // and all converted '*'s can be UNCONVERTED back to 'O's.
        for (int rowI = 0; rowI < rows; rowI++) {
            for (int colI = 0; colI < cols; colI++) {
                if (board[rowI][colI] == 'O') {
                    board[rowI][colI] = 'X';
                } else if (board[rowI][colI] == '*') {
                    board[rowI][colI] = 'O';
                }
            }
        }
    }

    /**
     * Converts all boundary 'O's and their neighbors to '*'s.
     * This is done because all chains of 'O's reaching the boundary of the board, will not be able
     * to be covered by 'X's.
     * <p>
     * <p>
     * Thus, all remaining 'O's after this conversion will certainly be able to be covered by 'X's.
     */
    private void convertBoundaryOsAndNeighborsToStar(final char[][] board,
            final int rows, final int cols) {
        // Iter over all cols of first and last row
        for (int colI = 0; colI < cols; colI++) {
            if (board[0][colI] == 'O') {
                convertOAndItsNeighborToStar(board, 0, colI);
            }
            if (board[rows - 1][colI] == 'O') {
                convertOAndItsNeighborToStar(board, rows - 1, colI);
            }
        }

        // Iter over all rows of first and last col
        for (int rowI = 0; rowI < rows; rowI++) {
            if (board[rowI][0] == 'O') {
                convertOAndItsNeighborToStar(board, rowI, 0);
            }
            if (board[rowI][cols - 1] == 'O') {
                convertOAndItsNeighborToStar(board, rowI, cols - 1);
            }
        }
    }

    /**
     * Uses DFS to convert an 'O' and all its neighbors {up, down, left, right; no diagonals} to '*'.
     */
    private void convertOAndItsNeighborToStar(final char[][] board, final int row, final int col) {
        if (row < 0 || row > board.length - 1 ||
                col < 0 || col > board[0].length - 1 ||
                board[row][col] != 'O') {
            return;
        }

        board[row][col] = '*';

        for (final int[] dir : DIRECTIONS) {
            convertOAndItsNeighborToStar(board, row + dir[0], col + dir[1]);
        }
    }
}
