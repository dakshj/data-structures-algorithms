/**
 * Created by daksh on 08-Oct-16.
 */
public class PalindromePermutationViaBits {
    public static void main(String[] args) {
        System.out.println(new PalindromePermutationViaBits().check("Rats live on no evil star"));
    }

    private boolean check(String s) {
        int bits = 0;

        for (char c : s.toCharArray()) {
            int charIndex = getCharIndex(c);

            if (charIndex != -1) {
                bits = toggleBitOfCharIndex(bits, charIndex);
            }
        }

        return bits == 0 || checkExactlyOneBitSet(bits);
    }

    /**
     * Checks whether only one bit is set in the int.<br/>
     * This is done by ANDing bits and bits - 1.<br/>
     * Why does it work? Because if only one bit is set, then the no. is a power of 2.<br/>
     * When the no. is ANDed with no. - 1; it produces 0.
     * <p>
     * E.g.:<br/>
     * 4 = 100             <br/>
     * 3 = 011             <br/>
     * 4 & 3 = 000
     */
    private boolean checkExactlyOneBitSet(int bits) {
        return (bits & bits - 1) == 0;
    }

    private int toggleBitOfCharIndex(int bits, int charIndex) {
        int mask = 1 << charIndex;

        if ((bits & mask) == 0) {
            bits = setBit(bits, mask);
        } else {
            bits = unsetBit(bits, mask);
        }

        return bits;
    }

    /**
     * To set a bit, you perform OR.
     * <p>
     * E.g.:    <br/>
     * If bits = 1100; and mask = 0010 (which means that index 1 needs to be set)   <br/>
     * Then bits OR mask:    <br/>
     * 1100 OR          <br/>
     * 0010 =           <br/>
     * 1110
     */
    private int setBit(int bits, int mask) {
        return bits | mask;
    }

    /**
     * To unset a bit, you perform AND with the NOT mask.
     * <p>
     * E.g.:    <br/>
     * If bits = 1100; and mask = 0100 [NOT mask = 1011] (which meaks that index 2 needs to be unset)   <br/>
     * Then bits AND (NOT mask):     <br/>
     * 1100 AND     <br/>
     * 1011 =       <br/>
     * 1000
     */
    private int unsetBit(int bits, int mask) {
        return bits & ~mask;
    }

    private int getCharIndex(char c) {
        if (c < 'a' || c > 'z') {
            return -1;
        }

        return c - 'a';
    }
}
