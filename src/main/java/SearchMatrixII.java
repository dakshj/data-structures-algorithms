class SearchMatrixII {

    private boolean searchMatrix(final int[][] matrix, final int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        // Start with top-right element
        int row = 0;
        int col = matrix[0].length - 1;

        while (col >= 0 && row < matrix.length) {
            // Found element
            if (matrix[row][col] == target) {
                return true;
            }

            // Element cannot be in this col, so search only up till previous column
            else if (matrix[row][col] > target) {
                col--;
            }

            // Element cannot be in this row, so search only from next row
            else {
                row++;
            }
        }

        return false;
    }
}
