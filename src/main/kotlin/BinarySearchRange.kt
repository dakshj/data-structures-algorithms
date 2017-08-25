fun main(args: Array<String>) {
    val range = BinarySearchRange()
    println(range.execute(listOf(1, 2, 3, 5, 5, 5, 8, 10, 15), 5))
}

private class BinarySearchRange {

    fun execute(list: List<Int>, x: Int): List<Int> {
        return listOf(
                first(list, x, 0, list.size - 1),
                last(list, x, 0, list.size - 1)
        )
    }

    private fun first(list: List<Int>, x: Int, start: Int, end: Int): Int {
        if (start > end) return -1

        val mid = start + (end - start) / 2

        return if (mid == 0 || (x == list[mid] && x > list[mid - 1])) {
            mid
        } else if (x > list[mid]) {
            first(list, x, mid + 1, end)
        } else {
            first(list, x, start, mid - 1)
        }
    }

    private fun last(list: List<Int>, x: Int, start: Int, end: Int): Int {
        if (start > end) return -1

        val mid = start + (end - start) / 2

        return if (mid == list.size - 1 || (x == list[mid] && x < list[mid + 1])) {
            mid
        } else if (x < list[mid]) {
            last(list, x, start, mid - 1)
        } else {
            last(list, x, mid + 1, end)
        }
    }
}
