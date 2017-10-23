public class PerfectSquares {

    private int numSquares(final int n) {
        final int[] dp = new int[n + 1];

        for (int currN = 1; currN <= n; currN++) {
            // Stores the minimum steps to reach currN given two numbers, one of which is known
            // to be a perfect square (i.e., i*i)
            int minSteps = Integer.MAX_VALUE;

            // Iterate over all perfect squares
            for (int i = 1; i * i <= currN; i++) {
                // "+ 1" because we see how many steps it took us to reach "currN - i*i"
                // and then add the steps for "i*i" (i.e. **1**) to it
                minSteps = Math.min(minSteps, dp[currN - i * i] + 1);
            }

            dp[currN] = minSteps;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new PerfectSquares().numSquares(13));
    }
}
