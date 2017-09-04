public class LinkedListCycleChecking<T> {

    private boolean check(final Node<T> head) {
        // Since no head node, there is no cycle
        if (head == null) return false;

        Node slow = head, fast = head;

        // Iterate till fast reaches the end
        while (fast != null && fast.next != null) {
            // Increment slow by one node and fast by two nodes
            slow = slow.next;
            fast = fast.next.next;

            // If there is a cycle in the LinkedList, then surely at some point,
            // slow will be equal to fast
            if (slow == fast) return true;
        }

        // If fast or fast.next has reached the end, then there is no cycle
        return false;
    }

    private static class Node<T> {
        private Node next;
        private final T data;

        private Node(final T data) {this.data = data;}

        private Node<T> next(final T data) {
            final Node<T> next = new Node<>(data);
            this.next = next;
            return next;
        }
    }

    public static void main(String[] args) {
        // No cycle
        final Node<String> head = new Node<>("a");
        final Node<String> last = head.next("b").next("c").next("d").next("e").next("f").next("i")
                .next("j").next("k").next("l").next("m").next("n").next("o").next("p").next("q");

        // false
        System.out.println(new LinkedListCycleChecking<String>().check(head));

        // Adding a cycle
        last.next = head.next.next.next.next.next.next.next.next.next.next;

        // true
        System.out.println(new LinkedListCycleChecking<String>().check(head));
    }
}
