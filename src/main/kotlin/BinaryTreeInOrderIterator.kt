import BinaryTreeInOrderIterator.Node
import java.util.Stack

private class BinaryTreeInOrderIterator<T>(private var node: Node<T>?) : Iterator<Node<T>> {

    val stack = Stack<Node<T>>()

    init {
        // Typical way of Stack initialization of In-order Iterative Traversal of a Binary Tree
        while (node != null) {
            stack.push(node)
            node = node!!.left
        }
    }

    override fun hasNext(): Boolean = !stack.isEmpty()

    override fun next(): Node<T> {
        if (!hasNext()) throw NoSuchElementException()

        val temp = stack.pop()
        node = temp

        // Going to the right subtree of this node, since it is an In-order Traversal
        if (node!!.right != null) {
            node = node!!.right;

            // Same as in init block
            while (node != null) {
                stack.push(node)
                node = node!!.left
            }
        }

        return temp
    }

    class Node<T>(val data: T) {
        var left: Node<T>? = null
        var right: Node<T>? = null

        override fun toString(): String {
            return data.toString()
        }
    }
}

fun main(args: Array<String>) {
    val root = Node(1)
    root.left = Node(2)
    root.right = Node(3)
    root.left!!.left = Node(4)
    root.left!!.right = Node(5)
    root.right!!.left = Node(6)
    root.right!!.right = Node(7)

    val iterator = BinaryTreeInOrderIterator(root)

    while (iterator.hasNext()) {
        println(iterator.next()) // 4 2 5 1 6 3 7
    }
}
