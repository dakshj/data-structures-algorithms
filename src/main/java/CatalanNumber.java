public class CatalanNumber {

    public static void main(String[] args) {
        print(1);
        print(2);
        print(3);
        print(4);
        print(5);
        print(6);
    }

    private static void print(int n) {
        int[] a = new int[n + 1];

        a[0] = 1;
        a[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                a[i] += a[i - j - 1] * a[j];
            }
        }

        System.out.println(a[n]);
    }
}
