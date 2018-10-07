class BinarySearchTreeToDoublyLinkedList {

    public static class Node {

        private int val;
        private Node left;
        private Node right;

        private Node(final int val, final Node left, final Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private Node prev;

    public Node treeToDoublyList(final Node root) {
        if (root == null) return null;

        Node temp = new Node(0, null, null);
        prev = temp;

        inOrderTraversal(root);

        temp.right.left = prev;
        prev.right = temp.right;

        return temp.right;
    }

    private void inOrderTraversal(final Node node) {
        if (node == null) return;

        inOrderTraversal(node.left);

        prev.right = node;
        node.left = prev;

        prev = node;

        inOrderTraversal(node.right);
    }
}
