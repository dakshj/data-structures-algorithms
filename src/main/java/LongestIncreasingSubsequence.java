public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        System.out.println(get(new int[]{3, 4, -1, 0, 6, 2, -6}));
    }

    private static int get(int[] arr) {
        int[] memo = new int[arr.length];

        // Initialize all values to 1
        for (int i = 0; i < memo.length; i++) {
            memo[i] = 1;
        }

        // Keep track of the max subsequence length in memo[] array.
        int max = Integer.MIN_VALUE;

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    int newVal = 1 + memo[j];

                    // Store subsequence length only if the currently stored sub. length at
                    // that index is lesser than newVal.
                    memo[i] = Math.max(memo[i], newVal);

                    // Update max sub. length
                    max = Math.max(max, memo[i]);
                }
            }
        }

        return max;
    }
}
