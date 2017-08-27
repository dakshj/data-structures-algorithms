private class MoveZeroesToEnd {

    fun move(list: MutableList<Int>): MutableList<Int> {
        println(list)
        var leftMostZeroIndex = 0

        for (index in 0 until list.size) {
            if (list[index] != 0) {
                // Swaps a non-zero current item with a zero item pointed by leftMostZeroIndex.
                // What this does is that it moves the left-most zero to the end of the
                // currently-parsed list of non-zero elements.
                // Thus, one by one, all zeroes lying in between non-zero elements are pushed out
                swap(index, leftMostZeroIndex++, list)
            }
        }

        return list
    }

    private fun swap(listIndex: Int, lastNonZeroIndex: Int, list: MutableList<Int>) {
        val temp = list[listIndex]
        list[listIndex] = list[lastNonZeroIndex]
        list[lastNonZeroIndex] = temp
        println(list)
    }
}

/**
 * Output:
 * [0, 1, 0, 3, 12] --> Original
 * [1, 0, 0, 3, 12] ----|
 * [1, 3, 0, 0, 12]     |--> Steps
 * [1, 3, 12, 0, 0] ----|
 * [1, 3, 12, 0, 0] --> Result
 */
fun main(args: Array<String>) {
    println(MoveZeroesToEnd().move(mutableListOf(0, 1, 0, 3, 12)))
}
