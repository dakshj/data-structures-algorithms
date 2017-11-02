import java.util.ArrayList;
import java.util.List;

/**
 * Exceeds the time limit on LeetCode, but I'm <strong>done</strong> with this question!
 */
class WordBreakII {

    private List<String> wordBreak(final String s, final List<String> wordDict) {
        final List<String> result = new ArrayList<>();

        wordBreak(s, wordDict, new StringBuilder(), result);

        return result;
    }

    private void wordBreak(final String s, final List<String> wordDict,
            final StringBuilder sentence, final List<String> result) {
        if (s.isEmpty()) {
            result.add(sentence.toString());
            return;
        }

        for (int i = 1; i <= s.length(); i++) {
            final StringBuilder newSentence = new StringBuilder(sentence);

            if (wordDict.contains(s.substring(0, i))) {
                if (newSentence.length() != 0) {
                    newSentence.append(" ");
                }

                newSentence.append(s.substring(0, i));

                wordBreak(s.substring(i), wordDict, newSentence, result);
            }
        }
    }

    public static void main(String[] args) {
        final List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");

        System.out.println(new WordBreakII().wordBreak("catsanddog", wordDict));
    }
}
