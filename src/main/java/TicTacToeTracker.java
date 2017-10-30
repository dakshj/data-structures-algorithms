/**
 * Tracks the number of "O"s or "X"s added to each row, column, diagonal, and anti-diagonal.
 * <p>
 * Since a game stops when one of the 4 vectors above has only "O"s or "X"s, we can say that
 * if any vector's count reaches +n or -n (by considering +1 for Player 1 and -1 for Player 2),
 * then the game has been won by that player.
 */
class TicTacToeTracker {

    private final int[] rows;
    private final int[] cols;
    private int diagonal;
    private int antiDiagonal;

    public TicTacToeTracker(int n) {
        rows = new int[n];
        cols = new int[n];
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     *
     * @param row    The row of the board.
     * @param col    The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either:<br/>
     * 0: No one wins<br/>
     * 1: Player 1 wins<br/>
     * 2: Player 2 wins
     */
    public int move(int row, int col, int player) {
        final int toAdd = player == 1 ? 1 : -1;

        rows[row] += toAdd;
        cols[col] += toAdd;

        if (row == col) {
            diagonal += toAdd;
        }

        if (col == (cols.length - row - 1)) {
            antiDiagonal += toAdd;
        }

        final int size = rows.length;

        if (Math.abs(rows[row]) == size ||
                Math.abs(cols[col]) == size ||
                Math.abs(diagonal) == size ||
                Math.abs(antiDiagonal) == size) {
            return player;
        }

        return 0;
    }
}
