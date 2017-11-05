class AndroidUnlockPatterns {

    private int numberOfPatterns(final int minPatternLength, final int maxPatternLength) {
        // A non-zero element in the skip[] array indicates that there is a number between
        // the two input nos.
        // A zero element means that the two numbers are adjacent to each other.
        final int skip[][] = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] =
                skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;

        final boolean visited[] = new boolean[10];
        int result = 0;

        // Perform DFS search on each length in the range of [minPatternLength, maxPatternLength]
        for (int i = minPatternLength; i <= maxPatternLength; i++) {
            result += dfs(visited, skip, 1, i - 1) * 4;    // 1, 3, 7, 9 are symmetric
            result += dfs(visited, skip, 2, i - 1) * 4;    // 2, 4, 6, 8 are symmetric
            result += dfs(visited, skip, 5, i - 1);        // 5
        }

        return result;
    }

    private int dfs(final boolean visited[], final int[][] skip,
            final int currentNo, final int remainingLength) {
        if (remainingLength < 0) {
            return 0;
        }

        // This returns a 1 which finally gets added into the previous-level-DFS-call's result
        if (remainingLength == 0) {
            return 1;
        }

        // Mark current no. as visited so that it won't be visited in
        // *only this* DFS call (and its deeper DFS calls)
        visited[currentNo] = true;

        int result = 0;

        for (int i = 1; i <= 9; ++i) {
            // If the current no. is not visited AND
            if (!visited[i] &&
                    // These two numbers are adjacent OR Skip number is already visited
                    //                                      (Then we can go over it)
                    (skip[currentNo][i] == 0 || visited[skip[currentNo][i]])) {
                result += dfs(visited, skip, i, remainingLength - 1);
            }
        }

        // Un-visit this number so that it can be visited for subsequent same-level DFSes
        visited[currentNo] = false;

        return result;
    }
}
