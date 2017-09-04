private class ReverseLinkedListInConstantSpace<T> {

    fun reverse(node: Node<T>): Node<T>? {
        var cur: Node<T>? = node

        var nextTemp: Node<T>?
        var prevTemp: Node<T>? = null

        while (cur != null) {
            // Save cur to a temporary var
            nextTemp = cur.next

            // Point cur's next to the previous node (thus reversing the direction)
            cur.next = prevTemp

            // Make cur as prevTemp, so that it can be used in the next iteration
            // for the new cur's next to be set to prevTemp
            prevTemp = cur

            // Increment cur to the original next pointer
            cur = nextTemp
        }

        return prevTemp
    }
}

private class Node<T>(private val data: T) {

    var next: Node<T>? = null

    fun next(next: Node<T>): Node<T> {
        this.next = next
        return next
    }

    fun printLinkedList() {
        var current: Node<T>? = this

        while (current != null) {
            print("${current.data}\t")
            current = current.next
        }
        println()
    }
}

/**
 * Output:
 * 1	2	3	4	5
 * 5	4	3	2	1
 */
fun main(args: Array<String>) {
    val node = Node(1)
    node.next(Node(2)).next(Node(3)).next(Node(4)).next(Node(5))
    node.printLinkedList()
    ReverseLinkedListInConstantSpace<Int>().reverse(node)?.printLinkedList()
}
