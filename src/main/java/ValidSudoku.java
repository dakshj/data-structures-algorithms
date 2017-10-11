import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {

    private boolean isValidSudoku(final char[][] board) {
        // 9 rows, columns, and cubes
        for (int i = 0; i < 9; i++) {
            final Set<Character> row = new HashSet<>();
            final Set<Character> column = new HashSet<>();
            final Set<Character> cube = new HashSet<>();

            // 9 Cells for each row, column, and cube
            for (int j = 0; j < 9; j++) {

                // Check {i,j} for row [E.g.: {0,1}, {0,2}, {0,3}, etc.
                if (board[i][j] != '.' && !row.add(board[i][j])) {
                    return false;
                }

                // Check {j,i} for column [E.g.: {1,0}, {2,0}, {3,0}, etc.
                if (board[j][i] != '.' && !column.add(board[j][i])) {
                    return false;
                }

                // row and column indices within the cube:
                final int cubeRowIndex = 3 * (i / 3) + (j / 3);
                final int cubeColumnIndex = 3 * (i % 3) + (j % 3);

                // Check {i,j} for row [E.g.: {0,1}, {0,2}, {0,3}, etc.
                if (board[cubeRowIndex][cubeColumnIndex] != '.' &&
                        !cube.add(board[cubeRowIndex][cubeColumnIndex])) {
                    return false;
                }
            }
        }

        return true;
    }
}
