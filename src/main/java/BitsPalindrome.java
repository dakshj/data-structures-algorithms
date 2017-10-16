public class BitsPalindrome {

    private static boolean isPalindrome(int n) {
        int copy = n;
        int reversed = 0;

        while (copy != 0) {
            reversed <<= 1;
            reversed |= (copy & 1);
            copy >>>= 1;
        }
        return (reversed == n);
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(8)); // false
        System.out.println(isPalindrome(9)); // true
    }
}
