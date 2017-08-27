import java.util.*

private class TreeNode<T>(val data: T) {
    var left: TreeNode<T>? = null
    var right: TreeNode<T>? = null
}

private class PrintBinaryTreeColumnWise<T> {

    fun print(root: TreeNode<T>) {
        fillMap(root).forEach { _, v -> v.forEach { print("$it ") } }
    }

    private fun fillMap(root: TreeNode<T>): SortedMap<Int, MutableList<T>> {
        val map = sortedMapOf<Int, MutableList<T>>()
        val queue = LinkedList<Pair<Int, TreeNode<T>?>>()

        queue.add(0 to root)

        while (!queue.isEmpty()) {
            val (column, node) = queue.remove()

            if (!map.containsKey(column)) {
                map[column] = mutableListOf(node!!.data)
            } else {
                map[column]!!.add(node!!.data)
            }

            if (node.left != null) {
                queue.add(column - 1 to node.left)
            }

            if (node.right != null) {
                queue.add(column + 1 to node.right)
            }
        }

        return map
    }
}

fun main(args: Array<String>) {
    val root = TreeNode(6)
    root.left = TreeNode(3)
    root.right = TreeNode(4)

    root.left!!.left = TreeNode(5)
    root.left!!.right = TreeNode(1)
    root.right!!.right = TreeNode(0)

    root.left!!.left!!.left = TreeNode(9)
    root.left!!.left!!.right = TreeNode(2)
    root.right!!.right!!.left = TreeNode(8)

    root.left!!.left!!.right!!.right = TreeNode(7)

    PrintBinaryTreeColumnWise<Int>().print(root)
}
