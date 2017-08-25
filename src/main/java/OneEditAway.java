/**
 * Created by daksh on 08-Oct-16.
 */
public class OneEditAway {
    public static void main(String[] args) {
        OneEditAway oneEditAway = new OneEditAway();

        System.out.println(oneEditAway.check("pales", "pale")); // true
        System.out.println(oneEditAway.check("bale", "pale"));  // true
        System.out.println(oneEditAway.check("bal", "pale"));   // false
        System.out.println(oneEditAway.check("pal", "pale"));   // true
        System.out.println(oneEditAway.check("pl", "pale"));    // false
    }

    private boolean check(String s1, String s2) {
        if (Math.abs(s1.length() - s2.length()) > 1) {
            return false;
        }

        String smaller = s1.length() < s2.length() ? s1 : s2;
        String larger = s1.length() < s2.length() ? s2 : s1;

        int smallerI = 0, largerI = 0;

        int diffCount = 0;

        while (smallerI < smaller.length() && largerI < larger.length()) {
            if (smaller.charAt(smallerI) != larger.charAt(largerI)) {
                diffCount++;

                if (diffCount > 1) {
                    return false;
                }

                // Only if both are of same length, do you increment smaller's iterator.
                // E.g.: "pale" vs. "bale". When iterator is at "p", "b"

                // If the smaller's length was actually smaller, then it might be that a
                // character has been deleted.
                // THUS, we stay at the same character of smaller and check it with the next char
                // of larger
                // E.g. "ale" vs. "pale". When iterator is at "a", "p"
                if (smaller.length() == larger.length()) {
                    smallerI++;
                }
            } else {
                // If both are same, then we simply go to the next char and check that
                smallerI++;
            }

            largerI++;
        }

        return true;
    }
}
