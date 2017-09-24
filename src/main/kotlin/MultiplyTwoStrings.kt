private class MultiplyTwoStrings {

    fun multiply(num1: String, num2: String): String {
        val solution = IntArray(num1.length + num2.length)

        for (i in num1.indices.reversed()) {
            for (j in num2.indices.reversed()) {
                // Multiply two single-digit numbers
                val mul = (num1[i] - '0') * (num2[j] - '0')

                // Left index of a (max) 2-digit mul
                val left = i + j
                // Right index of a (max) 2-digit mul
                val right = left + 1

                // Add this multiplication and 10s digit of previous multiplication
                // (Which is basically a carry-over)
                val sum = mul + solution[right]

                // Store the carry-over
                solution[left] += sum / 10

                // This digit has been finalized in the answer
                solution[right] = sum % 10
            }
        }

        val sb = StringBuilder()

        solution
                // Skip all 0s when sb is empty; i.e. Avoid adding useless 0s to front of answer
                .filterNot { sb.isEmpty() && it == 0 }
                .forEach { sb.append(it) }

        return if (sb.isEmpty()) "0" else sb.toString()
    }
}

fun main(args: Array<String>) {
    println(MultiplyTwoStrings().multiply("989", "76"))
}
