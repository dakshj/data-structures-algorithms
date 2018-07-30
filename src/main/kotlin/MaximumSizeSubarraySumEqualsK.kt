class MaximumSizeSubarraySumEqualsK {

    fun maxSubArrayLen(nums: IntArray, k: Int): Int {
        val map = mutableMapOf<Int, Int>()

        var max = 0
        var cumulative = 0

        nums.forEachIndexed { i, num ->
            cumulative += num

            if (cumulative == k) {
                max = i + 1
            } else if (map.contains(cumulative - k)) {
                max = maxOf(max, i - map[cumulative - k]!!)
            }

            map.putIfAbsent(cumulative, i)
        }

        return max
    }
}
