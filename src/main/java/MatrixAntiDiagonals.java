import java.util.ArrayList;
import java.util.List;

public class MatrixAntiDiagonals {

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        get(arr).forEach(System.out::println);
    }

    private static List<List<Integer>> get(int[][] arr) {
        int size = arr.length;

        List<List<Integer>> solution = new ArrayList<>();

        // Iterate over elements of first row and last column.
        // Max is {2 * size - 1} because top right el overlaps first row and last col.
        for (int i = 0; i < 2 * size - 1; i++) {
            List<Integer> list = new ArrayList<>();

            int row;
            int col;

            if (i < size) {
                // If we are still iterating over first row, set values appropriately.
                row = 0;
                col = i;
            } else {
                // Else, we are now iterating over last column, thus set values appropriately.
                row = i - size + 1;
                col = size - 1;
            }

            // While both indexes are in range, move diagonally towards bottom-left.
            while (inRange(row, size) && inRange(col, size)) {
                list.add(arr[row][col]);
                row++;
                col--;
            }

            // When we have reached the last el of the anti-diagonal, add all els to the solution
            solution.add(list);
        }

        return solution;
    }

    private static boolean inRange(int index, int size) {
        return index >= 0 && index < size;
    }
}
