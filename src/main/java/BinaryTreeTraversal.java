import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeTraversal {

    /**
     * Tree:
     * ~~~~~~~~~~~~~~~1~~~~~~~~~~~
     * ~~~~~~2~~~~~~~~~~~~~3~~~~~~
     * ~~~~7~~~6~~~~~~~~~5~~~4~~~~
     * ~~~~~~~~~~10~~~~11~~~~~~~~~
     * Output:
     * {1, 2, 3, 4, 5, 6, 7, 10, 11}
     */
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(7);
        root.left.right = new Node(6);
        root.right.left = new Node(5);
        root.right.right = new Node(4);
        root.left.right.right = new Node(10);
        root.right.left.left = new Node(11);

        BinaryTreeTraversal trav = new BinaryTreeTraversal();
        System.out.println(trav.regular(root));
        System.out.println(trav.zigZagUsingTwoStacks(root));
    }

    private List<Node> zigZagUsingTwoStacks(Node root) {
        if (root == null) return null;

        List<Node> list = new ArrayList<>();
        Stack<Node> stackLeftToRight = new Stack<>();
        Stack<Node> stackRightToLeft = new Stack<>();

        stackRightToLeft.push(root);

        while (!stackRightToLeft.isEmpty() || !stackLeftToRight.isEmpty()) {
            while (!stackRightToLeft.isEmpty()) {
                Node node = stackRightToLeft.pop();
                list.add(node);

                if (node.right != null) {
                    stackLeftToRight.push(node.right);
                }

                if (node.left != null) {
                    stackLeftToRight.push(node.left);
                }
            }

            while (!stackLeftToRight.isEmpty()) {
                Node node = stackLeftToRight.pop();
                list.add(node);

                if (node.left != null) {
                    stackRightToLeft.push(node.left);
                }

                if (node.right != null) {
                    stackRightToLeft.push(node.right);
                }
            }
        }

        return list;
    }

    private List<Node> regular(Node root) {
        if (root == null) return null;

        List<Node> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            list.add(node);

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }

        return list;
    }

    private static class Node {
        private Node left;
        private Node right;
        private int data;

        private Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }
}
