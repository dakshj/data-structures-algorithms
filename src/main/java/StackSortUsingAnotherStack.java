import java.util.EmptyStackException;

public class StackSortUsingAnotherStack {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(8);
        stack.push(3);
        stack.push(1);
        stack.push(7);
        stack.push(5);

        StackSortUsingAnotherStack stackSort = new StackSortUsingAnotherStack();
        stackSort.print(stack);
        stackSort.sort(stack);
        System.out.println("~~~~Sorting~~~~");
        stackSort.print(stack);
    }

    private void sort(Stack<Integer> stack) {
        Stack<Integer> temp = new Stack<>();

        while (!stack.isEmpty()) {
            int current = stack.pop();

            while (!temp.isEmpty() && temp.peek() > current) {
                stack.push(temp.pop());
            }

            temp.push(current);
        }

        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    private void print(Stack<Integer> stack) {
        Stack<Integer> holder = new Stack<>();
        while (!stack.isEmpty()) {
            int data = stack.pop();
            System.out.println(data);
            holder.push(data);
        }

        while (!holder.isEmpty()) {
            stack.push(holder.pop());
        }
    }

    private static class Stack<T> {

        private Node<T> top;

        private void push(T data) {
            if (top == null) {
                top = new Node<>(data);
            } else {
                Node<T> node = new Node<>(data);
                node.next = top;
                top = node;
            }
        }

        private T pop() {
            if (top == null) throw new EmptyStackException();

            T data = top.data;
            top = top.next;

            return data;
        }

        private T peek() {
            if (top == null) throw new EmptyStackException();

            return top.data;
        }

        private boolean isEmpty() {
            return top == null;
        }
    }

    private static class Node<T> {

        private T data;
        private Node<T> next;

        private Node(T data) {
            this.data = data;
        }
    }
}
