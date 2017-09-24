public class LinkedListPartition {

    private Node partition(Node node, int x) {
        if (node == null) return null;

        Node head = node, tail = node;

        while (node != null) {
            Node next = node.next;

            if (node.data < x) {
                node.next = head;
                head = node;
            } else {
                tail.next = node;
                tail = node;
            }

            node = next;
        }

        tail.next = null;

        return head;
    }

    private static class Node {
        int data;
        Node next;

        private Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return String.valueOf(data) + " " + (next != null ? next : "");
        }
    }

    public static void main(String[] args) {
        Node node = new Node(3);
        node.next = new Node(5);
        node.next.next = new Node(8);
        node.next.next.next = new Node(5);
        node.next.next.next.next = new Node(10);
        node.next.next.next.next.next = new Node(2);
        node.next.next.next.next.next.next = new Node(1);

        System.out.println(new LinkedListPartition().partition(node, 5));
    }
}
