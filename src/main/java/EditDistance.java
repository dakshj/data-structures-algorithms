public class EditDistance {

    public static void main(String[] args) {
        System.out.println(print("Daksh".toCharArray(), "Gaurav".toCharArray()));
    }

    private static int print(char[] a, char[] b) {
        int[][] memo = new int[a.length + 1][b.length + 1];

        // Initialize 0th column
        for (int i = 0; i < memo.length; i++) {
            memo[i][0] = i;
        }

        // Initialize 0th row
        for (int i = 0; i < memo[0].length; i++) {
            memo[0][i] = i;
        }

        for (int i = 1; i < memo.length; i++) {
            for (int j = 1; j < memo[0].length; j++) {

                // If both chars are same, copy top left
                if (a[i - 1] == b[j - 1]) {
                    memo[i][j] = memo[i - 1][j - 1];
                }
                // Else store minimum(left, top-left, top) + 1
                else {
                    memo[i][j] = 1 + Math.min(
                            // Left
                            memo[i][j - 1],
                            Math.min(
                                    // Top Left
                                    memo[i - 1][j - 1],
                                    // Top
                                    memo[i - 1][j]
                            ));
                }
            }
        }

        return memo[a.length][b.length];
    }
}
