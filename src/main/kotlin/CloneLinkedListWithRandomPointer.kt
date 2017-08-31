private class NodeWithRandom<T>(val data: T) {
    var next: NodeWithRandom<T>? = null
    var random: NodeWithRandom<T>? = null

    override fun toString(): String {
        return data.toString()
    }
}

private class CloneLinkedListWithRandomPointer {

    fun clone(head: NodeWithRandom<Int>?): NodeWithRandom<Int>? {
        if (head == null) return null

        var current = head

        // 1. Create a clone of each node and put the clone after the node
        while (current != null) {
            val clone = NodeWithRandom(current.data)

            clone.next = current.next
            current.next = clone

            current = current.next!!.next
        }

        current = head

        // 2. Update the clone's random pointer to point to the ****clone**** of random
        // (not random itself)
        while (current != null && current.random != null) {
            // Since any node's next is a clone of itself (e.g. current.random.next), thus:
            current.next!!.random = current.random!!.next
            current = current.next!!.next
        }

        // 3. Build a new LinkedList of clones by removing all original nodes
        val headCloned = head.next
        var currentCloned = headCloned

        while (currentCloned != null && currentCloned.next != null) {
            currentCloned.next = currentCloned.next!!.next
            currentCloned = currentCloned.next
        }

        return headCloned
    }

    fun print(head: NodeWithRandom<Int>?) {
        var current = head

        while (current != null) {
            println("Current = $current;\tRandom Next = ${current.random}")
            current = current.next
        }

        println("-------------------------------------------")
    }
}

fun main(args: Array<String>) {
    test1()
    test2()
    test3()
}

fun test1() {
    val head = NodeWithRandom(1)
    val two = NodeWithRandom(2)
    val three = NodeWithRandom(3)
    val four = NodeWithRandom(4)
    val five = NodeWithRandom(5)

    head.next = two
    head.random = four

    two.next = three
    two.random = five

    three.next = four
    three.random = head

    four.next = five
    four.random = five

    five.random = two

    CloneLinkedListWithRandomPointer().print(head)
    val headCloned = CloneLinkedListWithRandomPointer().clone(head)
    CloneLinkedListWithRandomPointer().print(headCloned)
}

fun test2() {
    CloneLinkedListWithRandomPointer().print(null)
    val headCloned = CloneLinkedListWithRandomPointer().clone(null)
    CloneLinkedListWithRandomPointer().print(headCloned)
}

fun test3() {
    val head = NodeWithRandom(1)

    CloneLinkedListWithRandomPointer().print(head)
    val headCloned = CloneLinkedListWithRandomPointer().clone(head)
    CloneLinkedListWithRandomPointer().print(headCloned)
}
