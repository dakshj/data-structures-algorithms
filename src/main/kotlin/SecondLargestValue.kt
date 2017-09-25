private class SecondLargestValue {

    /**
     * Gets the second largest element from a [set] of unique elements.
     */
    fun get(set: Set<Int>): Int {
        require(set.size >= 2, { "Need at least two unique elements." })

        var first = Int.MIN_VALUE
        var second = Int.MIN_VALUE

        set.forEach {
            // it is the currently seen largest element
            if (it > first) {
                // Make the current largest as second largest, and make it as largest
                second = first
                first = it
            }
            // it lies between second and first
            else if (it between (second to first)) {
                second = it
            }
        }

        return second
    }
}

fun main(args: Array<String>) {
    println(SecondLargestValue().get(setOf(10, 26, -5, 5, 39, 18, 7)))
    println(SecondLargestValue().get(setOf(5, 4, 3, 2, 1)))
    println(SecondLargestValue().get(setOf(1, 2, 3, 4, 5)))
}

private infix fun Int.between(pair: Pair<Int, Int>) = this > pair.first && this < pair.second
