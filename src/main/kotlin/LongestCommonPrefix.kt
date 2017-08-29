class LongestCommonPrefix {

    fun longestCommonPrefix(a: List<String>): String? {
        if (a.isEmpty()) {
            return null
        }

        if (a.size == 1) {
            return a[0]
        }

        val first = a[0]
        var prefix = ""

        for (i in 0..first.length - 1) {
            var addChar = true
            val currentChar = first[i]

            for (j in 1..a.size - 1) {
                val currentWord = a[j]

                if (i >= currentWord.length) {
                    addChar = false
                    break
                }

                if (currentChar != currentWord[i]) {
                    addChar = false
                    break
                }
            }

            if (addChar) {
                prefix += currentChar
            } else {
                break
            }
        }

        return prefix
    }
}

fun main(args: Array<String>) {
    println(LongestCommonPrefix().longestCommonPrefix(listOf("Testing", "Test")))
}
