class LongestUnivaluePath {

    // Global variable which is updated
    private int len;

    private int longestUnivaluePath(TreeNode root) {
        len = 0;

        if (root != null) {
            dfs(root);
        }

        return len;
    }

    private int dfs(TreeNode node) {
        int l = node.left != null ? dfs(node.left) : 0;
        int r = node.right != null ? dfs(node.right) : 0;

        int resl = node.left != null && node.left.val == node.val ? l + 1 : 0;
        int resr = node.right != null && node.right.val == node.val ? r + 1 : 0;

        len = Math.max(len, resl + resr);
        return Math.max(resl, resr);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        private TreeNode(int x) {
            val = x;
        }
    }
}
