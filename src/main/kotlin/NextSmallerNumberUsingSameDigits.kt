private class NextSmallerNumberUsingSameDigits {

    fun get(n: String): String {
        var right = ""
        var increasingDigitIndex = -1

        // Iterate from last to first
        for (i in n.indices.reversed()) {
            // Find the first increasing digit
            if (i > 0 && n[i - 1] > n[i]) {
                right = n.substring(i until n.length)
                increasingDigitIndex = i - 1
                break
            }
        }

        // Check if an increasing digit was found or not
        if (increasingDigitIndex == -1) return n

        var nReplaced = n

        right = right.sortDescending()

        for (i in right.indices) {
            if (right[i] < n[increasingDigitIndex]) {
                val temp = right[i]
                right = right.replaceChar(i, n[increasingDigitIndex])
                nReplaced = n.replaceChar(increasingDigitIndex, temp)

                right = right.sortDescending()
                nReplaced = nReplaced.replaceRange(
                        increasingDigitIndex + 1 until nReplaced.length,
                        right
                )
                break
            }
        }

        return nReplaced
    }
}

private fun String.sortDescending(): String {
    return toList().sortedDescending().joinToString(separator = "")
}

private fun String.replaceChar(index: Int, c: Char): String {
    return replaceRange(index..index, c.toString())
}

fun main(args: Array<String>) {
    println(NextSmallerNumberUsingSameDigits().get("0")) // 0
    println(NextSmallerNumberUsingSameDigits().get("12345")) // 12345
    println(NextSmallerNumberUsingSameDigits().get("123534")) // 123453
    println(NextSmallerNumberUsingSameDigits().get("81012382467")) // 81012378642
}
