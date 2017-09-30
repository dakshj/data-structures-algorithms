public class LargestBinarySearchTreeInBinaryTree {

    private int largestBst(final Node root) {
        return largest(root).size;
    }

    private Result largest(final Node node) {
        if (node == null) return new Result();

        // Recursive call to left and right
        final Result leftResult = largest(node.left);
        final Result rightResult = largest(node.right);

        final Result currentResult = new Result();

        if (!leftResult.isBst || !rightResult.isBst ||
                leftResult.max > node.data || rightResult.min <= node.data) {
            currentResult.isBst = false;

            // There will always be a BST of at least size 1 (for leaf nodes)
            currentResult.size = Math.max(leftResult.size, rightResult.size);

            // Don't need to care about min and max since this is not a BST, and thus these
            // values will not be used in higher rec calls
        } else {
            // If left node is null, then the smallest value of this BST will be node.data
            // (Similarly for right node)
            currentResult.min = node.left != null ? leftResult.min : node.data;
            currentResult.max = node.right != null ? rightResult.max : node.data;
            currentResult.size = 1 + leftResult.size + rightResult.size;
        }

        return currentResult;
    }

    private static class Result {
        private boolean isBst = true;
        private int min = Integer.MAX_VALUE;
        private int max = Integer.MIN_VALUE;
        private int size = 0;
    }

    private static class Node {

        private final int data;
        private Node left;
        private Node right;

        private Node(final int data) {
            this.data = data;
        }
    }
}
