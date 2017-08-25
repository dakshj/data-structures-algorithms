public class LongestCommonSubstring {

    public static void main(String[] args) {
        System.out.println(get("What", "That"));
    }

    private static int get(String a, String b) {
        return get(a.toLowerCase().toCharArray(), b.toLowerCase().toCharArray());
    }

    private static int get(char[] a, char[] b) {
        int[][] memo = new int[a.length + 1][b.length + 1];

        int max = Integer.MIN_VALUE;

        for (int i = 1; i < memo.length; i++) {
            for (int j = 1; j < memo[i].length; j++) {
                if (a[i - 1] == b[j - 1]) {
                    memo[i][j] = 1 + memo[i - 1][j - 1];
                    max = Math.max(max, memo[i][j]);
                }
            }
        }

        return max;
    }
}
