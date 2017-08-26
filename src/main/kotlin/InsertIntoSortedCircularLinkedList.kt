private class InsertIntoSortedCircularLinkedList {

    private class Node(val data: Int) {

        var next: Node? = null
    }

    private var head: Node? = null

    fun insertSorted(dataToBeInserted: Int): InsertIntoSortedCircularLinkedList {
        val newNode = Node(dataToBeInserted)

        // Add a node to an empty list
        when {
            head == null -> {
                newNode.next = newNode
                // Make a loop to self
                head = newNode
            }

        // In this case, newNode is going to be the head
            newNode.data <= head!!.data -> {
                var last = head
                while (last!!.next != head) {
                    last = last.next
                }

                // Loop last to the newNode which is the new head
                last.next = newNode

                // Connect newNode to previous head
                newNode.next = head

                // Assign newNode as new head
                head = newNode
            }

        // All other cases where the value is greater than the head
            else -> {
                var curr = head

                while (curr!!.next != head && newNode.data > curr.next!!.data) {
                    curr = curr.next
                }

                // Now, newNode is smaller than curr.next

                //
                newNode.next = curr.next
                curr.next = newNode
            }
        }

        // Used so as to chain insertSorted() function calls
        return this
    }

    fun printLinkedList() {
        if (head == null) return

        var curr = head

        do {
            print("${curr!!.data}  ")
            curr = curr.next
        } while (curr != head)
    }
}

/**
 * Output:
 * 1  1  2  3  3  4  7  8
 */
fun main(args: Array<String>) {
    val inserter = InsertIntoSortedCircularLinkedList()
    inserter
            .insertSorted(7)
            .insertSorted(3)
            .insertSorted(1)
            .insertSorted(8)
            .insertSorted(4)
            .insertSorted(1)
            .insertSorted(3)
            .insertSorted(2)
            .printLinkedList()
}
