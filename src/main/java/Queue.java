public class Queue<T> {

    private Node<T> first;
    private Node<T> last;

    public void add(T data) {
        Node<T> node = new Node<>(data);

        // Set new node as currently last node's next, so as to maintain the Queue's link.
        if (last != null) {
            last.next = node;
        }

        // New node needs to be set as last.
        last = node;

        if (first == null) {
            first = last;
        }
    }

    public T remove() throws Exception {
        if (first == null) throw new Exception("Queue is empty!");

        T data = first.data;
        first = first.next;

        if (first == null) {
            last = null;
        }

        return data;
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }
}
