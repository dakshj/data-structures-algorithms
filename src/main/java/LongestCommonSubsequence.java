public class LongestCommonSubsequence {

    public static void main(String[] args) {
        System.out.println(print("Daksh".toCharArray(), "Gaurav".toCharArray()));
        System.out.println(print("acbcf".toCharArray(), "abcdaf".toCharArray()));
    }

    private static int print(char[] a, char[] b) {
        int[][] memo = new int[a.length + 1][b.length + 1];

        for (int i = 1; i < memo.length; i++) {
            for (int j = 1; j < memo[0].length; j++) {

                // If both chars are same, store top-left + 1
                if (a[i - 1] == b[j - 1]) {
                    memo[i][j] = 1 + memo[i - 1][j - 1];
                }
                // Else store maximum(left, top)
                else {
                    memo[i][j] = Math.max(
                            // Left
                            memo[i][j - 1],
                            // Top
                            memo[i - 1][j]
                    );
                }
            }
        }

        return memo[a.length][b.length];
    }
}
