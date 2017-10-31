class BinaryTreeLongestConsecutiveSequence {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }

    int max = 0;

    private int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        max = 0;
        longestConsecutive(root, 0, root.val);
        return max;
    }

    private void longestConsecutive(final TreeNode root, int count, final int target) {
        if (root == null) return;

        if (root.val == target) {
            count++;
        } else {
            count = 1;
        }

        max = Math.max(max, count);

        longestConsecutive(root.left, count, root.val + 1);
        longestConsecutive(root.right, count, root.val + 1);
    }
}
