private class DecodeWays1 {

    fun get(s: String): Int {
        if (s.isEmpty()) {
            return 0
        }

        val n = s.length

        val memo = IntArray(size = n + 1)

        memo[n] = 1
        memo[n - 1] = if (s[n - 1] != '0') 1 else 0

        // Iterate from 2nd last to first index
        (n - 2 downTo 0)

                // Skip all 0s
                .filter { s[it] != '0' }

                .forEach {
                    // memo[it] means ways of decoding s.substring(it..lastIndex)

                    // Check if the 2 characters (current and next) are <= 26
                    memo[it] = if (s.substring(it..it + 1).toInt() <= 26) {

                        // If yes, then store values of next-to-end and (next-to-next)-to-end chars
                        memo[it + 1] + memo[it + 2]
                    } else {
                        // If no, then only store value of next char
                        memo[it + 1]
                    }
                }

        return memo[0]
    }
}

fun main(args: Array<String>) {
    val ways = DecodeWays1()

    assert(ways.get("0") == 0)
    assert(ways.get("1") == 1)
    assert(ways.get("10") == 2)
    assert(ways.get("100") == 2)
    assert(ways.get("26") == 2)
    assert(ways.get("101") == 2)
    assert(ways.get("126") == 3)
    assert(ways.get("5432124") == 5)
}
