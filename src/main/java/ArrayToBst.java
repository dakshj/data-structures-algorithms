public class ArrayToBst {

    public static Node getNode(int[] arr) {
        return getNode(arr, 0, arr.length - 1);
    }

    private static Node getNode(int[] arr, int start, int end) {
        if (start == end) return new Node(arr[start]);

        if (start > end) return null;

        int mid = start + (end - start) / 2;

        Node node = new Node(arr[mid]);
        node.left = getNode(arr, start, mid - 1);
        node.right = getNode(arr, mid + 1, end);

        return node;
    }

    private static class Node {
        private int data;
        private Node left;
        private Node right;

        private Node(int data) {
            this.data = data;
        }
    }
}
