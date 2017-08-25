/**
 * Created by daksh on 09-Oct-16.
 */
public class GetSetBit {

    public static void main(String[] args) {
        int n = 8;

        System.out.println(getBit(n, 3));
        System.out.println(n = setBit(n, 2));
        System.out.println(clearBit(n, 3));
        System.out.println(clearFirstBits(setBit(n, 5), 4));
    }

    private static int getBit(int n, int i) {
        return n & (1 << i);
    }

    private static int setBit(int n, int i) {
        return n | (1 << i);
    }

    private static int clearBit(int n, int i) {
        return n & ~(1 << i);
    }

    private static int clearFirstBits(int n, int i) {
        return n & (Integer.MAX_VALUE << i);
    }
}
