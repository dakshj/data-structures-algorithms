private class CoolDownTime {
    fun get(tasks: List<Char>, coolDown: Int): List<Char> {
        if (coolDown <= 0) return tasks

        val result = mutableListOf<Char>()

        val lastOccurrences = mutableMapOf<Char, Int>()

        // Maintains an amount of dashes that need to be added before a specified index
        // so as to have the required cooldown gap
        val dashesBefore = mutableMapOf<Int, Int>()

        tasks.forEachIndexed { i, c ->
            if (lastOccurrences.containsKey(c)) {
                val lastOccurrence = lastOccurrences[c]

                var charsInBetweenTwoOccurrences = i - lastOccurrence!! - 1

                // Add all previously added dashes between these two occurrences to the
                // amount of chars in between these two occurrences
                for (j in lastOccurrence + 1 until i) {
                    if (dashesBefore.containsKey(j)) {
                        charsInBetweenTwoOccurrences += dashesBefore[j]!!
                    }
                }

                if (charsInBetweenTwoOccurrences < coolDown) {
                    dashesBefore[i] = coolDown - charsInBetweenTwoOccurrences
                }
            }

            // Always update the last occurrence of this char in the map
            lastOccurrences[c] = i
        }

        return buildResult(tasks, dashesBefore)
    }

    private fun buildResult(tasks: List<Char>, dashesBefore: Map<Int, Int>): List<Char> {
        val result = mutableListOf<Char>()

        tasks.forEachIndexed { i, c ->
            if (dashesBefore.containsKey(i)) {
                for (j in 1..dashesBefore[i]!!) {
                    result.add('_')
                }
            }

            result.add(c)
        }

        return result
    }
}

fun main(args: Array<String>) {
    val cdt = CoolDownTime()
    println(cdt.get(tasks = listOf('A', 'B', 'A', 'B'), coolDown = 3))
    println(cdt.get(tasks = listOf('A', 'B', 'C', 'D', 'A', 'D', 'A'), coolDown = 5))
}
