class BurstBalloons {

    public int maxCoins(final int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        final int n = nums.length;

        int[][] dp = new int[n][n];

        // Iter over all window lengths, with the last window being the full array
        for (int len = 1; len <= n; len++) {

            // Shift window 1 index at a time
            for (int start = 0; start <= n - len; start++) {
                int end = start + len - 1;

                // Iter through each element of the window
                for (int i = start; i <= end; i++) {
                    // Calculate coins collected when bursting the current element (balloon)
                    // at the LAST of the current window.
                    // Thus, we use "start - 1" and "end + 1" because they are the immediate
                    // neighbors of the current window.
                    // (When the entire array is the window, then "-1" and "nums.length"
                    // will be the neighboring indices.
                    int coins = getNeighborValue(nums, start - 1) *
                            nums[i] *
                            getNeighborValue(nums, end + 1);

                    // Add ranges to left and right of current element in the current window,
                    // so that we can get the total amount of coins collected when bursting
                    // the current element (balloon) LAST.
                    coins = coins +
                            (i != start ? dp[start][i - 1] : 0) +
                            (i != end ? dp[i + 1][end] : 0);

                    dp[start][end] = Math.max(dp[start][end], coins);
                }
            }
        }

        return dp[0][n - 1];
    }

    /**
     * Helper function to deal with nums[-1] and nums[nums.length]
     */
    private int getNeighborValue(final int[] nums, final int i) {
        // nums[-1] and nums[nums.length] are given as 1 in the question
        if (i < 0 || i >= nums.length) {
            return 1;
        }

        return nums[i];
    }
}
