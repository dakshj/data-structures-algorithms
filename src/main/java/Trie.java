import java.util.HashMap;
import java.util.Map;

class Trie {

    private static class TrieNode {
        final Map<Character, TrieNode> map = new HashMap<>();
        boolean endOfWord;
    }

    private final TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(final String word) {
        insert(word, root);
    }

    private void insert(final String word, final TrieNode currentNode) {
        if (word == null || word.isEmpty()) return;

        final char c = word.charAt(0);

        TrieNode nextNode;

        if (currentNode.map.containsKey(c)) {
            nextNode = currentNode.map.get(c);
        } else {
            nextNode = new TrieNode();
            currentNode.map.put(c, nextNode);
        }

        if (word.length() == 1) {
            nextNode.endOfWord = true;
        } else {
            insert(word.substring(1), nextNode);
        }
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(final String word) {
        return search(word, root);
    }

    private boolean search(final String word, final TrieNode currentNode) {
        if (word == null || word.isEmpty()) return false;

        final char c = word.charAt(0);

        if (!currentNode.map.containsKey(c)) {
            return false;
        }

        final TrieNode nextNode = currentNode.map.get(c);

        if (word.length() == 1) {
            return nextNode.endOfWord;
        } else {
            return search(word.substring(1), nextNode);
        }
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(final String prefix) {
        return startsWith(prefix, root);
    }

    private boolean startsWith(final String prefix, final TrieNode currentNode) {
        if (prefix == null || prefix.isEmpty()) return false;

        final char c = prefix.charAt(0);

        if (!currentNode.map.containsKey(c)) {
            return false;
        }

        final TrieNode nextNode = currentNode.map.get(c);

        return prefix.length() == 1 || startsWith(prefix.substring(1), nextNode);
    }
}
