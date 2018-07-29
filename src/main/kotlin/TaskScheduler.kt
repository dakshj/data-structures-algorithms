import java.util.*
import kotlin.Comparator

private class TaskScheduler {

    fun leastInterval(tasks: CharArray, n: Int): Int {
        val queue = buildFrequencyQueue(tasks)

        var result = 0
        val cycleTime = n + 1

        while (queue.isNotEmpty()) {
            var currentExecution = 0

            val tempRemovedList = mutableListOf<Int>()

            for (i in 0 until cycleTime) {
                if (queue.isNotEmpty()) {
                    val removed = queue.remove()
                    currentExecution++
                    tempRemovedList.add(removed - 1)
                }
            }

            queue.addAll(tempRemovedList.filter { it > 0 })

            result += if (queue.isEmpty()) currentExecution else cycleTime
        }

        return result
    }

    private fun buildFrequencyQueue(tasks: CharArray): PriorityQueue<Int> {
        val map = mutableMapOf<Char, Int>()
        tasks.forEach { map.put(it, map.getOrDefault(it, 0) + 1) }
        val queue = PriorityQueue<Int>(Comparator.reverseOrder())
        queue.addAll(map.values)
        return queue
    }
}

fun main(args: Array<String>) {
    val scheduler = TaskScheduler()
    println(scheduler.leastInterval(tasks = charArrayOf('A', 'B', 'A', 'B'), n = 3))
    println(scheduler.leastInterval(tasks = charArrayOf('A', 'B', 'C', 'D', 'A', 'D', 'A'), n = 5))
}
