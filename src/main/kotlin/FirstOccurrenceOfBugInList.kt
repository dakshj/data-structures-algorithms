import FirstOccurrenceOfBugInList.Code

private class FirstOccurrenceOfBugInList {

    fun search(list: List<Code>): Int {
        return search(list, 0, list.lastIndex)
    }

    fun search(list: List<Code>, start: Int, end: Int): Int {
        // Base case of Binary Search
        if (start > end) return -1

        val mid = start + (end - start) / 2

        // If current is a BUG, and either (current is 1st element) or (previous is not a BUG)
        return if (list[mid] == Code.BUG && (mid != 0 || list[mid - 1] == Code.CLEAN)) {
            mid
        }
        // If both current and previous are a BUG, then GO LEFT
        else if (list[mid] == Code.BUG && list[mid - 1] == Code.BUG) {
            search(list, start, mid + 1)
        }
        // If current is CLEAN, then GO RIGHT
        else if (list[mid] == Code.CLEAN) {
            search(list, mid + 1, end)
        }
        // Default case
        else {
            -1
        }
    }

    enum class Code { BUG, CLEAN }
}

fun main(args: Array<String>) {
    println(FirstOccurrenceOfBugInList()
            .search(listOf(Code.CLEAN, Code.CLEAN, Code.CLEAN, Code.CLEAN, Code.CLEAN, Code.CLEAN,
                    Code.CLEAN, Code.CLEAN, Code.CLEAN, Code.BUG, Code.BUG, Code.BUG, Code.BUG)))
}
