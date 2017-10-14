public class WordSearch {

    private boolean exist(final char[][] board, final String word) {
        final int rows = board.length;
        final int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Skip all chars that don't start with the word's first letter
                if (word.charAt(0) != board[i][j]) continue;

                // Initial call to DFS
                if (dfs(word, board, i, j, new boolean[rows][cols], rows, cols)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(final String word, final char[][] board, final int i, final int j,
            final boolean[][] visited, final int rows, final int cols) {
        // Found the result
        if (word.isEmpty()) return true;

        // Validations for index ranges, char check, and pre-visited check
        if (i < 0 || i >= rows || j < 0 || j >= cols ||
                board[i][j] != word.charAt(0) || visited[i][j]) {
            return false;
        }

        // Set current as visited
        visited[i][j] = true;

        final String sRemaining = word.substring(1, word.length());

        // Recurse for all 4 directions so as to go towards the word's end
        final boolean up = dfs(sRemaining, board, i - 1, j, visited, rows, cols);
        final boolean down = dfs(sRemaining, board, i + 1, j, visited, rows, cols);
        final boolean left = dfs(sRemaining, board, i, j - 1, visited, rows, cols);
        final boolean right = dfs(sRemaining, board, i, j + 1, visited, rows, cols);

        if (up || down || left || right) {
            return true;
        } else {
            // Set current as NOT visited.
            // This is done to backtrack by setting this visited value as false,
            // so that other recursions can use this {i,j}th value as well.
            visited[i][j] = false;
            return false;
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}};

        System.out.println(new WordSearch().exist(board, "ABCESEEEFS"));
    }
}
