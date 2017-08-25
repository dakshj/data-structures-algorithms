public class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        System.out.println(get("agbdba".toCharArray()));
    }

    private static int get(char[] str) {
        // Base cases
        if (str.length == 0) return 0;
        if (str.length == 1) return 1;

        // Stores LPS of substring defined by {start index vs. end index}.
        int[][] memo = new int[str.length][str.length];

        // Initialize diagonal values to 1.
        // This is because when length of substring = 1, a character is a palindrome of itself.
        // Thus, all diagonal values represent the same start and end indexes of the
        // currently-being-processed substring.
        for (int i = 0; i < memo.length; i++) {
            memo[i][i] = 1;
        }

        // Iterate from length 2 to total (inclusive) [since calc. for length 1 has been done above].
        for (int len = 2; len <= str.length; len++) {

            // Limit is because when for e.g. total length = 8, current len = 2,
            // then the last iteration will set i to 6.
            // Thus, i = 6 to j = 7 (both inclusive) makes the length of the substring = 2.
            for (int i = 0; i < str.length - len + 1; i++) {
                int j = i + len - 1;

                if (str[i] == str[j]) {
                    if (len == 2) {
                        // If length 2, then there are no chars between the left and the right char.
                        memo[i][j] = 2;
                    } else {
                        // Else, also add the LPS of the chars lying between the left and right chars.
                        memo[i][j] = 2 + memo[i + 1][j - 1];
                    }
                } else {
                    // If not same, then store max of below value and left value
                    memo[i][j] = Math.max(
                            // Below
                            memo[i + 1][j],
                            // Left
                            memo[i][j - 1]);
                }
            }
        }

        // Return the last element of the 0th row, since this value indicates i = 0, j = last;
        // thus representing the LPS from the 0th index to the last index (both inclusive).
        return memo[0][str.length - 1];
    }
}
