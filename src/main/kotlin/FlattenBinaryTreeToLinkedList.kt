import FlattenBinaryTreeToLinkedList.Node

private class FlattenBinaryTreeToLinkedList {

    var saved: Node? = null

    fun flatten(root: Node?) {
        if (root == null) return

        flatten(root.right)
        flatten(root.left)

        root.right = saved
        root.left = null

        saved = root
    }

    class Node(val data: Int) {
        var left: Node? = null
        var right: Node? = null

        override fun toString(): String {
            return "$data {$left $right }"
        }
    }
}

/**
 * Output:
 * 1 {2 {3 {null null } 4 {null null } } 5 {null 6 {null null } } }
 * 1 {null 2 {null 3 {null 4 {null 5 {null 6 {null null } } } } } }
 */
fun main(args: Array<String>) {
    val root = Node(1)

    root.left = Node(2)
    root.right = Node(5)

    root.left!!.left = Node(3)
    root.left!!.right = Node(4)

    root.right!!.right = Node(6)

    println(root)
    FlattenBinaryTreeToLinkedList().flatten(root)
    println(root)
}
