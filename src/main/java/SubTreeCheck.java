public class SubTreeCheck {

    private boolean isSubTree(final Node tree, final Node subTree) {
        // No tree can be a subtree of a null tree
        // Also, this is used when we rec call on tree.left and tree.right
        // and need to stop after we cross leaf nodes
        if (tree == null) return false;

        // A null tree is a subtree of a non-null tree
        if (subTree == null) return true;

        if (areIdentical(tree, subTree)) return true;

        return isSubTree(tree.left, subTree) || isSubTree(tree.right, subTree);
    }

    private boolean areIdentical(final Node t1, final Node t2) {
        if (t1 == null && t2 == null) return true;

        if (t1 == null || t2 == null) return false;

        return t1.data == t2.data &&
                areIdentical(t1.left, t2.left) &&
                areIdentical(t1.right, t2.right);
    }

    private static class Node {
        private final int data;
        private Node left;

        private Node right;

        private Node(final int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        final Node t1 = new Node(26);
        t1.left = new Node(10);
        t1.right = new Node(3);
        t1.left.left = new Node(4);
        t1.left.right = new Node(6);
        t1.right.right = new Node(3);
        t1.left.left.right = new Node(30);

        final Node t2 = new Node(10);
        t2.left = new Node(4);
        t2.right = new Node(6);
        t2.left.right = new Node(30);

        System.out.println(new SubTreeCheck().isSubTree(t1, t2));
    }
}
