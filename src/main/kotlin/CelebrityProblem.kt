import java.util.Stack

private class CelebrityProblem {

    fun findCelebrity(matrix: Array<Array<Boolean>>): Int {
        val celeb: Int

        val stack = Stack<Int>()

        matrix.indices.forEach { stack.push(it) }

        while (stack.size > 1) {
            val a = stack.pop()
            val b = stack.pop()

            if (knows(matrix, a, b)) {
                // a is for sure not a celeb, but b may be
                stack.push(b)
            } else {
                // b is for sure not a celeb, but a may be later on (since a does know know b)
                stack.push(a)
            }
        }

        celeb = stack.pop()

        if (matrix.indices
                // All persons other than celeb...
                .filter { it != celeb }

                // If any person does not know the celeb, OR if the celeb knows any person
                .filter { !knows(matrix, it, celeb) || knows(matrix, celeb, it) }

                // If there are such persons satisfying the above criteria
                .count() > 0) {
            // Means that the celeb is not a true celeb
            return -1
        }

        // Celeb is a true celeb
        return celeb
    }

    private fun knows(matrix: Array<Array<Boolean>>, a: Int, b: Int): Boolean = matrix[a][b]
}

fun main(args: Array<String>) {
    // Everyone knows index 1, but index 1 knows no one
    var matrix = arrayOf(
            arrayOf(false, true, false, false),
            arrayOf(false, true, false, false),
            arrayOf(false, true, false, false),
            arrayOf(false, true, false, false)
    )
    println("Celeb is at index = ${CelebrityProblem().findCelebrity(matrix)}")

    // Everyone knows index 1, but index 1 also knows index 2
    matrix = arrayOf(
            arrayOf(false, true, false, false),
            arrayOf(false, true, true, false),
            arrayOf(false, true, false, false),
            arrayOf(false, true, false, false)
    )
    println("Celeb is at index = ${CelebrityProblem().findCelebrity(matrix)}")
}
