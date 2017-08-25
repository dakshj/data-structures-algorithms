public class LowestCommonAncestorBinaryTreeWithoutParentLink {

    public static void main(String[] args) {
        // Refer Binary Tree Ideal Example.png for visualization of this Binary Tree

        Node root = new Node(3);

        root.left = new Node(6);
        root.left.left = new Node(2);
        root.left.right = new Node(11);
        root.left.right.left = new Node(9);
        root.left.right.right = new Node(5);

        root.right = new Node(8);
        root.right.right = new Node(13);
        root.right.right.left = new Node(7);

        printLca(root, 5, 2);
    }

    private static void printLca(Node node, int a, int b) {
        System.out.println(getLca(node, a, b));
    }

    private static Node getLca(Node node, int a, int b) {
        if (node == null) return null;

        // If its data matches either of the 2 nodes whose LCA needs to be found,
        // return itself.
        if (node.data == a || node.data == b) return node;

        Node left = getLca(node.left, a, b);
        Node right = getLca(node.right, a, b);

        // If both are non-null, return itself
        if (left != null && right != null) {
            return node;
        }

        // Either return a non-null left, or a non-null right.
        // If both are null, then returns null (since right is null).
        return left != null ? left : right;
    }

    private static class Node {

        private int data;
        private Node left;
        private Node right;

        private Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }
}
