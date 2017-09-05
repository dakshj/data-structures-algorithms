private class RemoveInvalidParantheses {

    fun remove(input: String): List<String> {
        val result = mutableListOf<String>()
        remove(input, result, 0, 0, listOf('(', ')'))
        return result
    }

    private fun remove(input: String, result: MutableList<String>, lastI: Int, lastJ: Int,
            par: List<Char>) {
        var parCount = 0
        for (i in lastI until input.length) {
            // Check for balance between parentheses
            if (input[i] == par[0]) parCount++
            if (input[i] == par[1]) parCount--
            if (parCount >= 0) continue

            (lastJ..i)
                    // If jth char is a 2nd parentheses AND
                    // (j is lastJ OR
                    // previous character is NOT a 2nd parentheses; i.e., j is the first occurrence
                    // of a 2nd parentheses)
                    .filter { input[it] == par[1] && (it == lastJ || input[it - 1] != par[1]) }
                    .forEach {
                        // Remove jth char from input, and call remove() using i and j
                        // as new lastI and lastJ values
                        // Basically, we have now removed the first-occurring
                        // par[1] {be it a ')' or a '(' in the reverse case}
                        // and then sent the resultant string as input to be rechecked for validity
                        remove(input.substring(0, it) + input.substring(it + 1, input.length),
                                result, i, it, par)
                    }

            return
        }

        // Now that removal of all illegal ')'s has been done, use the same algorithm to
        // remove all illegal '('s by simply sending a reverse of the input String to the function
        val reversedInput = input.reversed()

        if (par[0] == '(') {
            remove(reversedInput, result, 0, 0, listOf(')', '('))
        } else {
            // If both Left-toRight and Right-to-Left checking have finished,
            // add this solution to the result. There may be more solutions added to this list.
            result.add(reversedInput)
        }
    }
}

fun main(args: Array<String>) {
    println(RemoveInvalidParantheses().remove("((a)(b)"))
}
