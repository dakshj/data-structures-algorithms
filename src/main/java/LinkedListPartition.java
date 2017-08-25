import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by daksh on 15-Oct-16.
 */
public class LinkedListPartition {

    public static void main(String[] args) {
        Node node = new Node(3);
        node.next = new Node(5);
        node.next.next = new Node(8);
        node.next.next.next = new Node(5);
        node.next.next.next.next = new Node(10);
        node.next.next.next.next.next = new Node(2);
        node.next.next.next.next.next.next = new Node(1);

        LinkedListPartition partition = new LinkedListPartition();

        partition.printList(new LinkedListPartition().partition(node, 5));
    }

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

    private void printList(Node doNotTouch) {
        Node node = doNotTouch;

        if (node == null) return;

        ArrayList<Integer> list = new ArrayList<>();

        while (node != null) {
            list.add(node.data);
            node = node.next;
        }

        System.out.println(Arrays.toString(list.toArray()));
    }

    private static class Node {
        int data;
        Node next;

        private Node(int data) {
            this.data = data;
        }
    }
}
