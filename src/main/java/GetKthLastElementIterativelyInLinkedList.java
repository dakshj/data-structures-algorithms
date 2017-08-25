/**
 * Created by daksh on 15-Oct-16.
 */
public class GetKthLastElementIterativelyInLinkedList {

    private Node getKthLastElement(int k, Node head) {
        if (k < 1) return null;

        Node a = head, b = head;

        for (int i = 1; i < k; i++) {
            if (b.next == null) return null;

            b = b.next;
        }

        while (b.next != null) {
            b = b.next;
            a = a.next;
        }

        return a;
    }

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }
}
