import java.util.List;

class LongestWordInDictionaryThroughDeleting {

    public String findLongestWord(final String template, final List<String> dictionary) {
        String result = "";

        for (final String currDictWord : dictionary) {
            int matchedAmount = 0;

            for (final char c : template.toCharArray()) {
                // If the no. of chars matched is still less than the curr dict word's length,
                // then we have scope to match more chars.
                if (matchedAmount < currDictWord.length() &&
                        currDictWord.charAt(matchedAmount) == c) {
                    matchedAmount++;
                }

                // If we have matched all the chars in the current dict word, and if the
                // current dict word may be a more optimal solution than the currently stored result
                // ("may be" because it may have a worse lexicographical ordering, so will not
                // be the result)
                if (matchedAmount == currDictWord.length() &&
                        currDictWord.length() >= result.length()) {
                    // Replace only if it is longer, OR if it has a lower lexicographical ordering
                    if (currDictWord.length() > result.length() ||
                            // Check the lexicographical ordering against the currently stored
                            // same-length result
                            currDictWord.compareTo(result) < 0) {
                        result = currDictWord;
                    }
                }
            }
        }

        return result;
    }
}
