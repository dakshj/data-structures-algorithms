private class SquareRoot {

    fun get(n: Double): String {
        if (n == 0.0 || n == 1.0) return n.toString()

        val negative = n < 0

        val nAbs = Math.abs(n)

        var sqrt = nAbs / 2
        var g: Double

        do {
            g = sqrt
            sqrt = (g + nAbs / g) / 2
        } while (g - sqrt != 0.0)

        return if (!negative) {
            "$sqrt"
        } else {
            "-${sqrt}i"
        }
    }
}

/**
 * Output:
 * 5.0
 * 2.23606797749979
 * 2.0
 * 1.8708286933869707
 * 0.0
 * -5.0i
 */
fun main(args: Array<String>) {
    println(SquareRoot().get(25.0))
    println(SquareRoot().get(5.0))
    println(SquareRoot().get(4.0))
    println(SquareRoot().get(3.5))
    println(SquareRoot().get(0.0))
    println(SquareRoot().get(-25.0))
}
