class SimplifiedRegexMatcher {

    private boolean isMatch(String s, String p) {
        // INPUT VALIDATIONS
        // If both are empty, then true
        if (s == null && p == null) return true;
        // If only one or the other are empty, then false
        if (s == null || p == null) return false;

        //

        final char[] string = s.toCharArray();
        final char[] pattern = p.toCharArray();

        // Imagine chars of string to be vertical on the left of dp[][],
        // and chars of pattern to be horizontal on top of dp[][].

        // row vs. col = s vs. p
        // i of string  = row + 1 of dp[][]
        // j of pattern = col + 1 of dp[][]
        boolean[][] dp = new boolean[string.length + 1][pattern.length + 1];

        // Init this to true because all matches later on refer to the "[row-1][col-1]" boolean
        dp[0][0] = true;

        // The remaining elements of first column of dp are kept false because for a non-empty
        // String s, an empty pattern p will return false since an empty pattern matches nothing!

        // For the first row of dp, it may be that a regex for e.g. "a*" matches an empty String s.
        // Thus, we need to set it manually.
        for (int col = 1; col < dp[0].length; col++) {
            if (pattern[col - 1] == '*') {
                // "- 2" because we check if 0 occurrences of the prev char in p will make it a
                // regex match for up till the current char in s.
                dp[0][col] = dp[0][col - 2];
            }
        }

        for (int row = 1; row < dp.length; row++) {
            // Each row of dp refers to the row-1 index of string
            // (since we start with the case of what happens when either string or pattern are empty)
            final int sIndex = row - 1;

            for (int col = 1; col < dp[0].length; col++) {
                final int pIndex = col - 1;

                if (string[sIndex] == pattern[pIndex] || pattern[pIndex] == '.') {
                    // Set it to whatever the regex match status was, if we consider that both
                    // the current chars of string and pattern did not exist
                    dp[row][col] = dp[row - 1][col - 1];
                } else if (pattern[pIndex] == '*') {
                    // Assumes 0 occurrences of the prev char of pattern
                    dp[row][col] = dp[row][col - 2];

                    // Now assume >0 occurrences of the prev char of pattern
                    if (pattern[pIndex - 1] == string[sIndex] || pattern[pIndex - 1] == '.') {
                        dp[row][col] = dp[row - 1][col]

                                // Or, use the previously stored value, if it was in case made
                                // true when we considered 0 occurrences of the prev char of pattern.
                                || dp[row][col];
                    }
                } else {
                    // Actually, no need to set it, since each next value of dp is already false
                    dp[row][col] = false;
                }
            }
        }

        // Answer is stored in dp[last][last], which indicates that we were able to reach
        // the last char in s by successfully matching up till the last char in p.
        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static void main(String[] args) {
        SimplifiedRegexMatcher matcher = new SimplifiedRegexMatcher();
        System.out.println(matcher.isMatch("xaabyc", "xa*b.c"));
    }
}
