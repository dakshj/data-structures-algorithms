private class PrefixToPostfix {

    fun convert(prefix: String): String {
        val stack = Stack<String>()

        prefix.reversed().forEach {
            if (!it.isOperator()) {
                stack.push(it.toString())
            } else {
                val a = stack.pop()
                val b = stack.pop()
                stack.push(a + b + it)
            }
        }

        return stack.pop()
    }
}

private fun Char.isOperator() = this == '+' || this == '-' || this == '*' || this == '/'

fun main(args: Array<String>) {
    assert(PrefixToPostfix().convert("-*+ABC*-DE+FG") == "AB+C*DE-FG+*-")
}
