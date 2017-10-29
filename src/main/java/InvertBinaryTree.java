class InvertBinaryTree {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        private TreeNode(final int x) {
            val = x;
        }
    }

    private TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        final TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);

        return root;
    }
}
