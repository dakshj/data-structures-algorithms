/**
 * Created by daksh on 10-Oct-16.
 */
public class BitPairSwap {

    public static void main(String[] args) {
        System.out.println(swap(9));
    }

    private static int swap(int n) {
        return (n & 0xaaaaaaaa) >>> 1 | (n & 0x55555555) << 1;
    }
}
