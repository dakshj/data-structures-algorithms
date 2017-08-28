private class WaysToRepresentNumberInString {
    fun dp(n: Int): Int {
        var ways = 0

        if (n == 0) {
            return 1
        }

        ways += dp(n / 10)

        val temp = n % 100

        if (temp in 10..26) {
            ways += dp(n / 100)
        }

        return ways
    }
}

fun main(args: Array<String>) {
    val ways = WaysToRepresentNumberInString()

    println(ways.dp(0))          // 1
    println(ways.dp(1))          // 1
    println(ways.dp(10))         // 2
    println(ways.dp(100))        // 2
    println(ways.dp(26))         // 2
    println(ways.dp(101))        // 2
    println(ways.dp(126))        // 3
    println(ways.dp(5432124))    // 5
}
