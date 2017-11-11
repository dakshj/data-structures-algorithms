class ZeroMatrix {

    public void setZeroes(final int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;

        final int rows = matrix.length;
        final int cols = matrix[0].length;

        // These are required because we can't simply make the first row/col zero immediately,
        // because that will propagate the value to other iterations, and end up making every
        // element in the matrix = 0
        boolean makeFirstRowZeroLater = false;
        boolean makeFirstColZeroLater = false;

        for (int rowI = 0; rowI < rows; rowI++) {
            for (int colI = 0; colI < cols; colI++) {
                if (matrix[rowI][colI] == 0) {
                    // Use the matrix's first row and first column to store the decision to
                    // whether or not make that corresponding row and/or column 0 later on.
                    // This reduces the overall additional space complexity from
                    // O(rows + cols) to just O(1).
                    matrix[0][colI] = 0;
                    matrix[rowI][0] = 0;

                    if (rowI == 0) {
                        makeFirstRowZeroLater = true;
                    }

                    if (colI == 0) {
                        makeFirstColZeroLater = true;
                    }
                }
            }
        }

        for (int rowI = 1; rowI < rows; rowI++) {
            for (int colI = 1; colI < cols; colI++) {
                // If either the first cell in that row or col is a 0, set the current cell to 0
                if (matrix[rowI][0] == 0 || matrix[0][colI] == 0) {
                    matrix[rowI][colI] = 0;
                }
            }
        }

        // If makeFirstRowZeroLater, then make the first row (i.e. all values of [0][cols] = 0
        if (makeFirstRowZeroLater) {
            for (int colI = 0; colI < cols; colI++) {
                matrix[0][colI] = 0;
            }
        }

        // If makeFirstColZeroLater, then make the first col (i.e. all values of [rows][0] = 0
        if (makeFirstColZeroLater) {
            for (int rowI = 0; rowI < rows; rowI++) {
                matrix[rowI][0] = 0;
            }
        }
    }
}
