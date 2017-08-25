public class NumberMax {

    public static void main(String[] args) {
        System.out.println(getMaxWithIntegerOverflowAvoidance(12, 13));
        System.out.println(getMaxEasy(12, 13));
    }

    private static int getMaxEasy(int a, int b) {
        // "a-b" causes Integer Overflows
        int k = sign(a - b);

        return a * k + b * flip(k);
    }

    private static int getMaxWithIntegerOverflowAvoidance(int a, int b) {
        int c = a - b;

        int sa = sign(a);
        int sb = sign(b);

        // Used only when a and b have same signs, in which case, Integer Overflow is impossible
        int sc = sign(c);

        // If both are different, k = sign(a)
        int useSignOfA = sa ^ sb;

        // If both are same, k = sign(a - b)
        int useSignOfC = flip(sa ^ sb);

        int k = useSignOfA * sa + useSignOfC * sc;

        return a * k + b * flip(k);
    }

    private static int sign(int a) {
        return flip((a >> 31) & 1);
    }

    private static int flip(int bit) {
        return bit ^ 1;
    }
}
