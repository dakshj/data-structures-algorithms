public class LongestPathWithObstacles {

    public static void main(String[] args) {
        // Map size
        boolean[][] mapOfObstacles = new boolean[3][10];

        // Obstacles
        mapOfObstacles[1][2] = true;
        mapOfObstacles[1][5] = true;
        mapOfObstacles[1][8] = true;

        // Start point
        int aRow = 0;
        int aCol = 0;

        // End point
        int bRow = 1;
        int bCol = 7;

        boolean[][] history = new boolean[mapOfObstacles.length][mapOfObstacles[0].length];

        System.out.println(getLongestPathWithObstacles(mapOfObstacles, history, aRow, aCol, bRow, bCol));
    }

    /**
     * Required because when we start from a, moving from {nothing, nothing} to {aRow, aCol} is
     * counted as 1 step.
     */
    private static int getLongestPathWithObstacles(boolean[][] mapOfObstacles, boolean[][] history, int aRow, int aCol, int bRow, int bCol) {
        return getLongestPathWithObstaclesRec(mapOfObstacles, history, aRow, aCol, bRow, bCol) - 1;
    }

    private static int getLongestPathWithObstaclesRec(boolean[][] mapOfObstacles, boolean[][] history,
            int aRow, int aCol, int bRow, int bCol) {
        // Validate a and b
        if (aRow < 0 || aRow >= mapOfObstacles.length ||
                aCol < 0 || aCol >= mapOfObstacles[0].length ||
                bRow < 0 || bRow >= mapOfObstacles.length ||
                bCol < 0 || bCol >= mapOfObstacles[0].length) {
            return 0;
        }

        // Check if a is not an obstacle
        if (mapOfObstacles[aRow][aCol]) return 0;

        // Check if a is not already visited
        if (history[aRow][aCol]) return 0;

        // If a and b are at the same place
        if (aRow == bRow && aCol == bCol) return 1;

        // If pass all above validations, mark as visited
        history[aRow][aCol] = true;

        // Recursively get longest path when moving in all 4 directions
        int up = getLongestPathWithObstaclesRec(mapOfObstacles, history, aRow - 1, aCol, bRow, bCol);
        int down = getLongestPathWithObstaclesRec(mapOfObstacles, history, aRow + 1, aCol, bRow, bCol);
        int left = getLongestPathWithObstaclesRec(mapOfObstacles, history, aRow, aCol - 1, bRow, bCol);
        int right = getLongestPathWithObstaclesRec(mapOfObstacles, history, aRow, aCol + 1, bRow, bCol);

        // Get max of all 4 directions
        int max = Math.max(Math.max(up, down), Math.max(left, right));

        return 1 + max;
    }
}
