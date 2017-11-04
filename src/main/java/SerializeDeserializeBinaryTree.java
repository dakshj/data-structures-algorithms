import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class SerializeDeserializeBinaryTree {

    private static final String SPLITTER = ";";
    private static final String NULL = "`";

    private String serialize(final TreeNode root) {
        final StringBuilder result = new StringBuilder();
        buildString(root, result);
        return result.toString();
    }

    private void buildString(final TreeNode node, final StringBuilder result) {
        if (node != null) {
            result.append(node.val).append(SPLITTER);
            buildString(node.left, result);
            buildString(node.right, result);
        } else {
            result.append(NULL).append(SPLITTER);
        }
    }

    private TreeNode deserialize(final String text) {
        final Queue<String> queue = new LinkedList<>(Arrays.asList(text.split(SPLITTER)));
        return buildTree(queue);
    }

    private TreeNode buildTree(final Queue<String> queue) {
        // First element of the queue is always the next node, because of Pre-Order Traversal
        final String rootVal = queue.remove();

        if (rootVal.equals(NULL)) {
            return null;
        }

        // Build TreeNodes based on Pre-Order Traversal
        final TreeNode root = new TreeNode(Integer.parseInt(rootVal));
        root.left = buildTree(queue);
        root.right = buildTree(queue);

        return root;
    }

    private static class TreeNode {

        private final int val;
        private TreeNode left;
        private TreeNode right;

        private TreeNode(final int val) {
            this.val = val;
        }
    }
}
