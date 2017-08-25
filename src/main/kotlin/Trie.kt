fun main(args: Array<String>) {
    val trie: Trie = TrieImpl()
    trie.add("Desert")
            .add("Dessert")
}

private interface Trie {

    fun add(word: String): Trie

    fun containsPrefix(prefix: String): Boolean

    fun containsWord(word: String): Boolean

    fun remove(word: String)
}

private class TrieNode {
    val map = mutableMapOf<Char, TrieNode>()
    var endOfWord: Boolean = false
}

private class TrieImpl : Trie {

    private val root: TrieNode = TrieNode()

    override fun add(word: String): Trie {
        if (word.isEmpty()) throw IllegalArgumentException("Word is empty!")
        add(word, root)
        return this
    }

    private fun add(word: String, currentNode: TrieNode) {
        val currentChar = word[0]

        var nextNode = TrieNode()
        if (!currentNode.map.containsKey(currentChar)) {
            nextNode.map[currentChar] = nextNode
        } else {
            nextNode = currentNode.map.getValue(currentChar)
        }

        if (word.length == 1) {
            nextNode.endOfWord = true
        } else {
            add(word.substring(1, word.length), nextNode)
        }
    }

    override fun containsPrefix(prefix: String): Boolean {
        var currentNode = root

        return containsPrefix(prefix, root)
    }

    private fun containsPrefix(prefix: String, currentNode: TrieNode): Boolean {
        val currentChar = prefix[0]

        if (currentNode.map.containsKey(currentChar)) {
            if (prefix.length == 1) {
                return true
            }

            val nextNode = currentNode.map.getValue(currentChar)
            return containsPrefix(prefix.substring(1, prefix.length), nextNode)
        }

        return false
    }

    override fun containsWord(word: String): Boolean {
        TODO("not implemented")
    }

    override fun remove(word: String) {
        TODO("not implemented")
    }
}
