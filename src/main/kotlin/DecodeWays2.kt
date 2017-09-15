private class DecodeWays2 {

    fun get(n: Int): Int {
        if (n == 0) {
            return 0
        }

        return dp(n)
    }

    private fun dp(n: Int): Int {
        var ways = 0

        if (n == 0) {
            return 1
        }

        ways += dp(n / 10)

        if (n % 100 in 10..26) {
            ways += dp(n / 100)
        }

        return ways
    }
}

fun main(args: Array<String>) {
    val ways = DecodeWays2()

    assert(ways.get(0) == 0)
    assert(ways.get(1) == 1)
    assert(ways.get(10) == 2)
    assert(ways.get(100) == 2)
    assert(ways.get(26) == 2)
    assert(ways.get(101) == 2)
    assert(ways.get(126) == 3)
    assert(ways.get(5432124) == 5)
}
