import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestUniquePrefix {

    public static void main(String[] args) {
        String[] words = new String[]{"mouse", "more", "kitten", "kite", "s"};
        System.out.println(new ShortestUniquePrefix().get(words));
    }

    private List<String> get(String[] words) {
        TrieNode root = new TrieNode();

        for (String word : words) {
            root.insertWord(word);
        }

        List<String> prefixes = new ArrayList<>();

        for (String word : words) {
            String prefix = root.getPrefix(word);
            prefixes.add(prefix);
        }

        return prefixes;
    }

    private static final class TrieNode {

        private Map<Character, TrieNode> children = new HashMap<>();
        private int frequency = 0;

        private void insertWord(String word) {
            insertWord(word, 0);
        }

        private void insertWord(String word, int indexOfNextChar) {
            frequency++;

            // Insert Nodes into root while there are characters left.
            // I.e.: at the last char, it recursively calls once more to set its own frequency = 1,
            // BUT will not add any more children to its map
            if (indexOfNextChar < word.length()) {
                Character current = word.charAt(indexOfNextChar);

                if (!children.containsKey(current)) {
                    children.put(current, new TrieNode());
                }

                TrieNode child = children.get(current);
                child.insertWord(word, indexOfNextChar + 1);
            }
        }

        private String getPrefix(String word) {
            return getPrefix(word, 0);
        }

        /**
         * Finds shortest unique prefix of a word.
         *
         * @param indexOfNextChar Index of char (not first char of a given word)
         *                        Index = 0 represents root, which stores first chars of all words as its CHILDREN
         */
        private String getPrefix(String word, int indexOfNextChar) {
            // Since index is of root, last index of word = index + 1
            if (indexOfNextChar < 0 || indexOfNextChar > word.length()) return null;

            // Added check of {indexOfNextChar != 0} so as to make it work when there
            // is only one word in the Trie.
            // Thus, if the frequency of root is 1, then it will have only one entry in its Map,
            // and also that entry's TrieNode value will have a frequency = 1.
            // We use that TrieNode's getPrefix to return, since the indexOfNextChar will be 1,
            // and thus substring(0, 1) will return the first Character only.
            
            // Since root = index 0, the letter indexes in the word are offset by 1.
            // Thus, when we call substring on {0, index}, we are in effect calling
            // substring on {0, index + 1} !
            if (indexOfNextChar != 0 & frequency == 1) return word.substring(0, indexOfNextChar);

            TrieNode child = children.get(word.charAt(indexOfNextChar));
            return child.getPrefix(word, indexOfNextChar + 1);
        }
    }
}
