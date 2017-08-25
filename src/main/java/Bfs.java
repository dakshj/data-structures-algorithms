import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by daksh on 11-Oct-16.
 */
public class Bfs {

    private static boolean search(Node start, Node end) {
        if (start == end) {
            return true;
        }

        Queue<Node> queue = new LinkedList<>();

        start.marked = true;

        queue.add(start);

        while (!queue.isEmpty()) {
            Node node = queue.remove();

            if (node != null) {
                for (Node neighbor : node.neighbors) {
                    if (!neighbor.marked) {
                        if (neighbor == end) {
                            return true;
                        } else {
                            neighbor.marked = true;
                            queue.add(neighbor);
                        }
                    }
                }
            }
        }

        return false;
    }

    private static class Node {
        private ArrayList<Node> neighbors;
        private boolean marked;
    }
}
