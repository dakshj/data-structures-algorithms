import java.util.HashSet;
import java.util.Set;

class LongestPalindromeFromLettersOfString {

    public int longestPalindrome(final String s) {
        if (s == null || s.length() == 0) return 0;

        final Set<Character> set = new HashSet<>();
        int count = 0;

        for (final char c : s.toCharArray()) {
            // Simply putting in chars which have not been seen, or occur only once in the string
            if (!set.contains(c)) {
                set.add(c);
            }

            // If the current char is in the set, then we can take both
            // of these chars (the current char, and the one that was already in the set)
            // as a part of the palindrome.
            else {
                set.remove(c);
                count += 2;
            }
        }

        // If the set still has leftover chars, the it means that either they occurred only once,
        // or that they occurred an odd no. of times.
        // Thus, we can take any ONE of these leftover chars, and make it the central char
        // of an odd-sized palindromic string.
        if (!set.isEmpty()) {
            count++;
        }

        return count;
    }
}
