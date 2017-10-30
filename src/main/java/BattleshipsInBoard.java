class BattleshipsInBoard {

    private int countBattleships(char[][] board) {
        int count = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // Check only the last 'X' cell of each battleship
                // (Can also do the same for the *first* 'X' cell of each battleship by checking
                // left and top neighbors)
                if (board[i][j] == 'X' &&
                        // Thus, need to only check the right and bottom neighbors which are in range
                        (i == board.length - 1 || board[i + 1][j] == '.') &&
                        (j == board[i].length - 1 || board[i][j + 1] == '.')) {
                    // If both the neighbors are '.', then we have found the end of a new battleship
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(new BattleshipsInBoard().countBattleships(new char[][]{
                {'X', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
                {'.', '.', '.', 'X'}
        }));
    }
}
