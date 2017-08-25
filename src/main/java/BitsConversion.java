/**
 * Created by daksh on 09-Oct-16.
 */
public class BitsConversion {

    public static void main(String[] args) {
        System.out.println(count(8, 12));
    }

    private static int count(int a, int b) {
        int xor = a ^ b;
        return countSetBits(xor);
    }

    private static int countSetBits(int n) {
        int count = 0;

        while (n != 0) {
            count++;
            n &= n - 1;
        }

        return count;
    }
}
