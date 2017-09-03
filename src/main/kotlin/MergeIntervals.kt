private class MergeIntervals {

    fun merge(intervals: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()

        var start = 0
        var end = 0

        intervals
                // Sort intervals by starting Int
                .sortedBy { it.first }

                // Initialize start and end to the first interval
                // i.e., The first interval is now considered as the interval-being-built
                .apply {
                    start = first().first
                    end = first().second
                }
                .drop(1)
                .forEach {
                    // If the current interval's start Int is within the range of the interval-being-built
                    if (it.first <= end) {
                        // Update end to the furthest end from either the interval-being-built
                        // or the current interval
                        end = maxOf(end, it.second)
                    }

                    // If the current interval's start Int is outside the range of the interval-being-built
                    else {
                        // Add the interval-being-built to the result, as it is now a discrete interval
                        result.add(start to end)

                        // Make the current interval as the now interval-being-built
                        // Now when the code loops, the next interval's start Int will be
                        // checked against the interval-being-built's start Int
                        start = it.first
                        end = it.second
                    }
                }

        // ****Don't forget to add the last interval-being-built to the list too!****
        result.add(start to end)

        return result
    }
}

fun main(args: Array<String>) {
    // Output: [(1, 6), (8, 12)]
    println(MergeIntervals().merge(listOf(1 to 3, 2 to 6, 8 to 12)))

    // Output: [(1, 12), (14, 16), (18, 20)]
    println(MergeIntervals().merge(listOf(1 to 3, 2 to 6, 5 to 12, 14 to 16, 18 to 20)))
}
