public class Stack<T> {

    private Node<T> top;

    public void push(T data) {
        Node<T> node = new Node<>(data);

        node.next = top;
        top = node;
    }

    public T pop() throws Exception {
        if (top == null) throw new Exception("Stack is empty!");

        T data = top.data;
        top = top.next;

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
