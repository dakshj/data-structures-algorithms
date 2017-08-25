import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class StepsAwayFromGuard {

    // Open Space
    private static final char O = 'O';

    // Guard
    private static final char G = 'G';

    // Wall
    private static final char W = 'W';

    // {Left, Right, Up, Down}
    private static final int rowNeigh[] = {-1, 1, 0, 0};
    private static final int colNeigh[] = {0, 0, 1, -1};

    private static final int DISTANCE_WALL = -1;
    private static final int DISTANCE_GUARD = 0;

    public static void main(String[] args) {
        char[][] mat = {
                {O, O, O, G, O},
                {O, O, W, O, O},
                {O, G, O, O, W},
                {O, W, G, O, O},
                {W, O, O, O, G}
        };

        int[][] steps = getSteps(mat);
        for (int[] step : steps) {
            System.out.println(Arrays.toString(step));
        }
    }

    private static int[][] getSteps(char[][] mat) {
        // Non-Empty Matrix Validation
        if (mat.length == 0 || mat[0].length == 0) return null;

        int[][] steps = new int[mat.length][mat.length];

        Queue<GuardLocation> queueOfGuards = new LinkedList<>();

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                // Default value of all points
                steps[i][j] = DISTANCE_WALL;

                // If guard found, add to queue
                if (mat[i][j] == G) {
                    GuardLocation guardLocation = new GuardLocation(i, j);
                    queueOfGuards.add(guardLocation);
                    steps[i][j] = DISTANCE_GUARD;
                }
            }
        }

        while (!queueOfGuards.isEmpty()) {
            GuardLocation removed = queueOfGuards.remove();

            for (int i = 0; i < rowNeigh.length; i++) {
                int newR = removed.row + rowNeigh[i];
                int newC = removed.col + colNeigh[i];

                if (isValid(newR, newC, mat) &&
                        isUnvisitedOpen(newR, newC, mat, steps)) {
                    steps[newR][newC] = removed.distance + 1;

                    GuardLocation loc = new GuardLocation(newR, newC, removed.distance + 1);
                    queueOfGuards.add(loc);
                }
            }
        }

        return steps;
    }

    private static boolean isValid(int row, int col, char[][] mat) {
        return row >= 0 && row < mat.length && col >= 0 && col < mat[0].length;
    }

    private static boolean isUnvisitedOpen(int row, int col, char[][] mat, int[][] steps) {
        return mat[row][col] == O && steps[row][col] == DISTANCE_WALL;
    }

    private static class GuardLocation {

        private int row;
        private int col;
        private int distance;

        private GuardLocation(int row, int col) {
            this.row = row;
            this.col = col;
        }

        private GuardLocation(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }
}
