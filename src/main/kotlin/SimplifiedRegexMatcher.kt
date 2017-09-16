private class SimplifiedRegexMatcher {

    fun match(s: String, p: String): Boolean {
        val state = Array(s.length + 1) { BooleanArray(p.length + 1) }
        state[0][0] = true

        // For state 0 S, initialize all P states
        (1 until state[0].size)

                // For all chars that are '*' and previous state is true,
                // OR (i > 2 AND state of previous-to-previous is true)
                .filter { p[it - 1] == '*' && (state[0][it - 1] || it > 1 && state[0][it - 2]) }

                // Mark all such as true
                .forEach { state[0][it] = true }

        for (i in 1 until state.size) {
            for (j in 1 until state[0].size) {

                // If both chars in S and P are same, OR if P char is a '.'
                if (s[i - 1] == p[j - 1] || p[j - 1] == '.') {
                    // Copy previous S,P state to current state
                    state[i][j] = state[i - 1][j - 1]
                }

                // If P char is a '*'
                if (p[j - 1] == '*') {
                    // If S char is not equal to previous P char AND previous P car is not a '.'
                    if (s[i - 1] != p[j - 2] && p[j - 2] != '.') {
                        // Copy previous-to-previous P state, to current state
                        state[i][j] = state[i][j - 2]
                    } else {
                        // Copy OR of:
                        // {previous S state, previous P state, previous-to-previous P state}
                        state[i][j] = state[i - 1][j] || state[i][j - 1] || state[i][j - 2]
                    }
                }
            }
        }

        // Return last S,P state
        return state[s.length][p.length]
    }
}

fun main(args: Array<String>) {
    println(SimplifiedRegexMatcher().match("abbcsda", "a.*d."))
}
