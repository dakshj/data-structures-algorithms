import java.util.Stack;

public class QueueUsingTwoStacks<T> {

    /**
     * Prints {c, d, e, f}
     */
    public static void main(String[] args) {
        QueueUsingTwoStacks<String> queue = new QueueUsingTwoStacks<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.dequeue();
        queue.enqueue("d");
        queue.enqueue("e");
        queue.dequeue();
        queue.enqueue("f");

        queue.printAndClear();
    }

    private final Stack<T> stack1;
    private final Stack<T> stack2;

    public QueueUsingTwoStacks() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void enqueue(T data) {
        stack1.push(data);
    }

    public T dequeue() throws IllegalStateException {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            throw new IllegalStateException("Queue is empty!");
        }

        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        return stack2.pop();
    }

    public void printAndClear() {
        while (true) {
            try {
                System.out.println(dequeue());
            } catch (IllegalStateException e) {
                break;
            }
        }
    }
}
