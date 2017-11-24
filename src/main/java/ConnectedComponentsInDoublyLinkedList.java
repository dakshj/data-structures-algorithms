import java.util.HashSet;
import java.util.Set;

class ConnectedComponentsInDoublyLinkedList {

    private int get(final Node[] references) {
        int count = 0;
        final Set<Node> visited = new HashSet<>();

        for (final Node curr : references) {
            // If the neighbor(s) of curr are already visited, then it means that curr is a
            // part of a bigger chain of connected nodes.

            // We first increment count.
            // Then if prev is visited, it means that curr is connected to the left side.
            // Then, again, if next is visited, it means that {prev + current}
            // is connected to the right side.
            // Thus, decrement count in both cases.

            count++;

            if (visited.contains(curr.prev)) {
                count--;
            }

            if (visited.contains(curr.next)) {
                count--;
            }

            visited.add(curr);
        }

        return count;
    }

    private static class Node {
        private Node next;
        private Node prev;
        private final char data;

        public Node(final char data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        final Node a = new Node('a');
        final Node b = new Node('b');
        final Node c = new Node('c');
        final Node d = new Node('d');
        final Node e = new Node('e');
        final Node f = new Node('f');
        final Node g = new Node('g');

        a.next = b;
        b.prev = a;
        b.next = c;
        c.prev = b;
        c.next = d;
        d.prev = c;
        d.next = e;
        e.prev = d;
        e.next = f;
        f.prev = e;
        f.next = g;
        g.prev = f;

        final ConnectedComponentsInDoublyLinkedList cc = new ConnectedComponentsInDoublyLinkedList();

        System.out.println(cc.get(new Node[]{c, e, a, f}));         // 3
        System.out.println(cc.get(new Node[]{c, e, a, f, d}));      // 2
    }
}
