class BinaryTreeMaximumPathSum {

    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxPathSumRecursive(root);
        return max;
    }

    private int maxPathSumRecursive(final TreeNode node) {
        if (node == null) return 0;

        final int left = Math.max(0, maxPathSumRecursive(node.left));
        final int right = Math.max(0, maxPathSumRecursive(node.right));

        // The current total sum of the subtree, of which "node" is the root of,
        // is left + node.val + right. Compare this to max and store whichever is the higher value.
        max = Math.max(max, left + node.val + right);

        // Now to treat "node" as just another node of a bigger tree, we need to pass along
        // the highest value up till here. Thus, this can only be one chain of nodes going up,
        // as the highest node in the chain will be higher up in the BT's levels, and NOT "node".

        // Thus, to send the max sum of a path that has only increasing nodes (in terms of their
        // levels), we can only use either "left" or "right". Thus, we return the max of
        // "left" and "right", summed with "node.val".
        return Math.max(left, right) + node.val;
    }

    private static class TreeNode {

        private int val;
        private TreeNode left;
        private TreeNode right;

        private TreeNode(int x) {
            val = x;
        }
    }
}
