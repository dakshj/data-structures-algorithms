private class WordBreak {

    fun check(s: String, dict: List<String>): Boolean {
        val dp = BooleanArray(s.length + 1)
        dp[0] = true

        for (i in 1..s.length) {
            for (j in 0 until i) {
                if (dp[j] && dict.contains(s.substring(j until i))) {
                    dp[i] = true
                    break
                }
            }
        }

        return dp[s.length]
    }
}

fun main(args: Array<String>) {
    println(WordBreak().check(s = "testistest", dict = listOf("is", "test")))
}
