/**
 * Created by daksh on 09-Oct-16.
 */
public class FlipBitToWin {
    public static void main(String[] args) {
        System.out.println(getMax1s(1775));
    }

    private static int getMax1s(int n) {
        if (~n == 0) { // If n has all 1s
            return Integer.BYTES * 8; // Returns max amount of 1s
        }

        int previousLength = 0;
        int currentLength = 0;
        int maxLength = 1; // 1 because we can always flip at least 1 bit to make the max = 1

        while (n > 0) { // ...Since we'll be logically shifting each bit to the right
            if ((n & 1) == 1) { // If right-most bit is 1
                currentLength++; // We are still counting the current set of 1s
            } else {
                if ((n & 2) == 0) { // If 2nd most right bit is ALSO 0, i.e. 2 continuous 0s
                    previousLength = 0; // Then we make the previousLength 0 because it is no longer a single 0 away
                } else {
                    previousLength = currentLength; // Else,
                }

                currentLength = 0;
            }

            // "+ 1" because we need to count the bit which can be flipped also
            maxLength = Math.max(maxLength, previousLength + 1 + currentLength);

            // LOGICALLY (not Arithmetically) Shift n one bit to the right, and add a 0 to the left-most newly added bit
            n = n >>> 1;
        }

        return maxLength;
    }
}
