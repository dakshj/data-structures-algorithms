public class KmpSubstringMatch {

    public static void main(String[] args) {
        System.out.println(indexOf("abcxabcdabcdabcy".toCharArray(), "abcdabcy".toCharArray()));
    }

    private static int indexOf(char[] text, char[] pattern) {
        // Longest Prefix Suffix Array
        int[] lps = getLpsArray(pattern);

        int i = 0;
        int j = 0;

        while (i < text.length && j < pattern.length) {
            if (text[i] == pattern[j]) {
                i++;
                j++;
            } else {
                if (j > 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        // Found
        if (j == pattern.length) {
            return i - pattern.length;
        }

        // Not found
        return -1;
    }

    private static int[] getLpsArray(char[] pattern) {
        int[] lps = new int[pattern.length];

        int j = 0;
        int i = 1;

        while (i < pattern.length) {
            if (pattern[j] == pattern[i]) {
                lps[i] = j + 1;
                i++;
                j++;
            } else {
                if (j > 0) {
                    // Take j to the previous element's value in lps
                    j = lps[j - 1];
                } else {
                    // Set ith element to 0, and move i to next element
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}
