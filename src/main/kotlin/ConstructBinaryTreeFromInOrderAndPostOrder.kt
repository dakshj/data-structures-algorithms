private class ConstructBinaryTreeFromInOrderAndPostOrder {

    fun construct(inOrder: List<Int>, postOrder: List<Int>): Node? {
        if (postOrder.isEmpty()) return null

        // Root is always the last element in post-order
        val root = Node(postOrder.last())

        // All on left in in-order of root are left subtree
        val leftInOrder = inOrder.subList(0, inOrder.indexOf(postOrder.last()))

        // All on right in in-order of root are right subtree
        val rightInOrder = inOrder.subList(inOrder.indexOf(postOrder.last()) + 1, postOrder.size)

        // First {size of left subtree} are left subtree in post-order
        val leftPostOrder = postOrder.subList(0, leftInOrder.size)

        // Remaining {size of right subtree} are right subtree in post-order
        val rightPostOrder = postOrder.subList(leftInOrder.size, postOrder.lastIndex)

        root.left = construct(leftInOrder, leftPostOrder)
        root.right = construct(rightInOrder, rightPostOrder)

        return root
    }

    fun printPreOrder(root: Node?) {
        if (root == null) return

        print("${root.data} ")
        printPreOrder(root.left)
        printPreOrder(root.right)
    }

    private class Node(val data: Int) {
        var left: Node? = null
        var right: Node? = null
    }
}

/**
 * Output: 1 2 4 8 5 3 6 7
 */
fun main(args: Array<String>) {
    val order = ConstructBinaryTreeFromInOrderAndPostOrder()
    order.printPreOrder(
            root = order.construct(
                    inOrder = listOf(4, 8, 2, 5, 1, 6, 3, 7),
                    postOrder = listOf(8, 4, 5, 2, 6, 7, 3, 1)
            )
    )
}
