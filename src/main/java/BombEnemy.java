class BombEnemy {

    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        final int rows = grid.length;
        final int cols = grid[0].length;

        int killedInRow = 0;
        final int[] killedInCol = new int[cols];

        // Don't set to Integer.MIN_VALUE, because if no enemies were killed, then return 0
        int max = 0;

        for (int rowI = 0; rowI < rows; rowI++) {
            for (int colI = 0; colI < cols; colI++) {
                // Skip all Walls
                if (grid[rowI][colI] == 'W') continue;

                // If there is a Wall to the left, then only see how many were killed to the right.
                // Basically, the killedInRow calculates from either 1st col or the start
                // after a Wall, to the next time a Wall is hit.
                if (colI == 0 || grid[rowI][colI - 1] == 'W') {
                    killedInRow = killedInRow(grid, rowI, colI, cols);
                }

                // If there is a Wall to the top, the only see how many were killed to the bottom.
                // Need to save only one killedInRow and multiple killedInCols because we are
                // staying on the same row till we reach its end, but the col changes each time.
                if (rowI == 0 || grid[rowI - 1][colI] == 'W') {
                    // Save killedInCol for later lower rows where we can use the same killedInCol
                    // value, which is basically from {either first row or nearest upper Wall}
                    // to nearest lower Wall.
                    killedInCol[colI] = killedInCol(grid, rowI, colI, rows);
                }

                // If the current cell is empty
                if (grid[rowI][colI] == '0') {
                    max = Math.max(max, killedInRow + killedInCol[colI]);
                }
            }
        }

        return max;
    }

    private int killedInRow(final char[][] grid, final int rowI, final int colI, final int cols) {
        int killed = 0;

        // Iterate from current cell to right-most cell until we hit a Wall
        for (int i = colI; i < cols; i++) {
            // If hit a Wall, stop
            if (grid[rowI][i] == 'W') break;

            // If killed an Enemy
            if (grid[rowI][i] == 'E') killed++;
        }

        return killed;
    }

    private int killedInCol(final char[][] grid, final int rowI, final int colI, final int rows) {
        int killed = 0;

        // Iterate from current cell to bottom-most cell until we hit a Wall
        for (int i = rowI; i < rows; i++) {
            // If hit a Wall, stop
            if (grid[i][colI] == 'W') break;

            // If killed an Enemy
            if (grid[i][colI] == 'E') killed++;
        }

        return killed;
    }
}
