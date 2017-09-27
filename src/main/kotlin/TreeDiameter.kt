import TreeDiameter.TreeNode

private class TreeDiameter {

    class Result {
        var height = 0
        var diameter = 0
    }

    class TreeNode(val data: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun getDiameter(node: TreeNode?): Result {
        val result = Result()
        if (node == null) return result

        val leftResult = getDiameter(node.left)
        val rightResult = getDiameter(node.right)

        result.height = 1 + maxOf(leftResult.height, rightResult.height)

        val nodeDiameter = leftResult.height + 1 + rightResult.height
        result.diameter = maxOf(nodeDiameter, maxOf(leftResult.diameter, rightResult.diameter))

        return result
    }
}

fun main(args: Array<String>) {
    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)

    root.left!!.left = TreeNode(4)

    root.left!!.left!!.left = TreeNode(6)
    root.left!!.left!!.left = TreeNode(7)

    root.right!!.right = TreeNode(7)

    print(TreeDiameter().getDiameter(root).diameter)
}
