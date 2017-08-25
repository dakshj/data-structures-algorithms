import java.util.LinkedList;
import java.util.Queue;

public class StackUsingTwoQueues<T> {

    /**
     * Prints {f, d, b, a}
     */
    public static void main(String[] args) {
        StackUsingTwoQueues<String> stack = new StackUsingTwoQueues<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.pop();
        stack.push("d");
        stack.push("e");
        stack.pop();
        stack.push("f");

        stack.printAndClear();
    }

    private final Queue<T> q;
    private final Queue<T> temp;

    public StackUsingTwoQueues() {
        q = new LinkedList<>();
        temp = new LinkedList<>();
    }

    public void push(T data) {
        while (!q.isEmpty()) {
            temp.add(q.remove());
        }

        q.add(data);

        while (!temp.isEmpty()) {
            q.add(temp.remove());
        }
    }

    public T pop() throws IllegalStateException {
        if (q.isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }

        return q.remove();
    }

    public void printAndClear() {
        while (true) {
            try {
                System.out.println(pop());
            } catch (IllegalStateException e) {
                break;
            }
        }
    }
}
