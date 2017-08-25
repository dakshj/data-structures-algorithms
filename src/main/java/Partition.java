public class Partition {

    private static void partition(int n) {
        partition(n, n, "");
    }

    private static void partition(int n, int max, String prefix) {
        if (n == 0) {
            return;
        }

        for (int i = Math.min(max, n); i >= 1; i--) {
            partition(n - i, i, prefix + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 200; i++) {
            System.out.print(i + " = ");
            long diff = System.currentTimeMillis();
            partition(i);
            diff = System.currentTimeMillis() - diff;
            System.out.println((double) diff / 1000 + " seconds");
        }
    }
}