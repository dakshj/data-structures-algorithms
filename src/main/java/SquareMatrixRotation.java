import java.util.Arrays;

/**
 * Created by daksh on 08-Oct-16.
 */
public class SquareMatrixRotation {
    public static void main(String[] args) {
        int[][] squareMatrix = new int[][]{
                {1, 5, 10, 18, 3},
                {2, 6, 11, 14, 7},
                {3, 7, 12, 15, 4},
                {4, 8, 13, 16, 2},
                {5, 0, 19, 12, 6}};

        SquareMatrixRotation smr = new SquareMatrixRotation();
        smr.printMatrix(squareMatrix);
        System.out.println();
        squareMatrix = smr.rotate(squareMatrix);

        if (squareMatrix != null) {
            smr.printMatrix(squareMatrix);
        }
    }

    private int[][] rotate(int[][] matrix) {
        if (matrix.length == 0 || matrix.length != matrix[0].length) {
            return null;
        }

        for (int currentLayer = 0; currentLayer < matrix.length / 2; currentLayer++) {
            int last = matrix.length - 1 - currentLayer;

            for (int i = currentLayer; i < last; i++) {
                // Offset is required when referring to the left vertical elements of the (>0th) layer
                // Because, when n = 5, currentLayer = 1, (thus) last = 3:
                // If we use {last - i} to access the column of the left vertical, then it will be
                // 3-1 = 2; instead of just 3. Thus, to neutralize the effect of a non-0 starting value
                // of i, we use offset.
                int offset = i - currentLayer;

                int top = matrix[currentLayer][i]; // Top

                matrix[currentLayer][i] = matrix[last - i][currentLayer]; // Left -> Top
                matrix[last - offset][currentLayer] = matrix[last][last - offset]; // Bottom -> Left
                matrix[last][last - offset] = matrix[i][last]; // Right -> Bottom
                matrix[i][last] = top; // Top -> Right
            }
        }

        return matrix;
    }

    private void printMatrix(int[][] squareMatrix) {
        for (int[] ints : squareMatrix) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
