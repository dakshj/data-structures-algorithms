public class RemoveDuplicateLetters {

    private String removeDuplicateLetters(final String s) {
        // Base case
        if (s == null || s.isEmpty()) return "";

        final int[] letterCounts = new int[26];
        int smallestCharIndex = 0;

        // Build the array of letter counts
        for (int i = 0; i < s.length(); i++) {
            letterCounts[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            // Find the smallest char
            if (s.charAt(i) < s.charAt(smallestCharIndex)) {
                smallestCharIndex = i;
            }

            // If this is the last occurrence of the ith char, then stop here
            if (--letterCounts[s.charAt(i) - 'a'] == 0) {
                break;
            }
        }

        // At the last occurrence of any letter, we now have a smallest char index.
        // It will be the last occurrence of that char
        // Suppose in "caacad", it will stop at index 3, and will prepend with 'a'
        // plus a rec call with the string "cd" (from which all occurrences of 'a' have already
        // been removed)
        // Now "cd" will return 'c' + rec call with "d", which will return 'd' + "".
        // Finally, the string will be 'a' + 'c' + 'd' = "acd".

        // So,
        // If s still has letters, then return the smallest char, appended with
        // rec call of substring from next char of smallestCharIndex which has been stripped down
        // to remove all occurrences of smallest char from that substring
        return s.charAt(smallestCharIndex)
                + removeDuplicateLetters(s.substring(smallestCharIndex + 1)
                .replaceAll(String.valueOf(s.charAt(smallestCharIndex)), ""));
    }

    public static void main(String[] args) {
        System.out.println(new RemoveDuplicateLetters().removeDuplicateLetters("cbacdcbc"));
    }
}
