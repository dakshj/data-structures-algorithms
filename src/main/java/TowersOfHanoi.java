import java.util.Stack;

/**
 * Created by daksh on 20-Oct-16.
 */
public class TowersOfHanoi {

    public static void main(String[] args) {
        Stack<Integer> one = new Stack<>();
        Stack<Integer> two = new Stack<>();
        Stack<Integer> three = new Stack<>();

        one.push(15);
        one.push(12);
        one.push(10);
        one.push(9);
        one.push(5);
        one.push(2);
        one.push(1);

        System.out.println(one);
        System.out.println(two);
        System.out.println(three);

        move(one.size(), one, three, two);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(one);
        System.out.println(two);
        System.out.println(three);
    }

    private static void move(int size, Stack<Integer> source, Stack<Integer> destination, Stack<Integer> buffer) {
        if (size <= 0) return;

        move(size - 1, source, buffer, destination);

        moveTop(source, destination);

        move(size - 1, buffer, destination, source);
    }

    private static void moveTop(Stack<Integer> source, Stack<Integer> destination) {
        if (source.isEmpty()) return;

        Integer popped = source.pop();

        if (popped != null && (destination.isEmpty() || destination.peek() >= popped)) {
            destination.push(popped);
        }
    }
}
