public class ClosestLesserOrEqualNumberToTargetInBST {

    private int closest(final Node root, final int target) {
        return closest(root, target, new Result()).data;
    }

    private Result closest(final Node root, final int target, final Result result) {
        if (root == null) return result;

        if (target == root.data) {
            result.data = root.data;
            return result;
        }

        if (root.data > target) {
            // No need to check result.minDiff because we need to find a node that is <= target
            return closest(root.left, target, result);
        } else {
            final int currDiff = target - root.data;
            if (currDiff < result.minDiff) {
                result.minDiff = currDiff;
                result.data = root.data;
            }

            return closest(root.right, target, result);
        }
    }

    private static class Result {
        private int minDiff = Integer.MAX_VALUE;
        private int data = -1;
    }

    private static class Node {
        private Node left;
        private Node right;

        private final int data;

        private Node(final int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }

    public static void main(String[] args) {
        final ClosestLesserOrEqualNumberToTargetInBST c =
                new ClosestLesserOrEqualNumberToTargetInBST();

        final Node root = new Node(9);
        root.left = new Node(4);
        root.right = new Node(17);

        root.left.left = new Node(3);
        root.left.right = new Node(6);

        root.left.right.left = new Node(5);
        root.left.right.right = new Node(7);

        root.right.right = new Node(22);
        root.right.right.left = new Node(20);

        System.out.println(c.closest(root, 16));
        System.out.println(c.closest(root, 9));
        System.out.println(c.closest(root, 8));
        System.out.println(c.closest(root, 21));
    }
}
