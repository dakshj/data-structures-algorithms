import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CloneGraph<T> {

    private HashMap<T, GraphNode<T>> map = new HashMap<>();

    private GraphNode<T> clone(final GraphNode<T> node) {
        if (node == null) return null;

        if (map.containsKey(node.data)) {
            return map.get(node.data);
        }

        GraphNode<T> clone = new GraphNode<>(node.data);
        map.put(clone.data, clone);

        node.neighbors.forEach(neighbor -> clone.neighbors.add(clone(neighbor)));

        return clone;
    }

    private static class GraphNode<T> {

        private final T data;
        private List<GraphNode<T>> neighbors;

        private GraphNode(final T data) {
            this.data = data;
            neighbors = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        GraphNode<String> a = new GraphNode<>("a");
        GraphNode<String> b = new GraphNode<>("b");
        GraphNode<String> c = new GraphNode<>("c");
        GraphNode<String> d = new GraphNode<>("d");
        GraphNode<String> e = new GraphNode<>("e");
        GraphNode<String> f = new GraphNode<>("f");
        GraphNode<String> g = new GraphNode<>("g");
        GraphNode<String> h = new GraphNode<>("h");

        a.neighbors.add(b);
        b.neighbors.add(a);

        a.neighbors.add(d);
        d.neighbors.add(a);

        b.neighbors.add(c);
        c.neighbors.add(b);

        c.neighbors.add(d);
        d.neighbors.add(c);

        b.neighbors.add(e);
        e.neighbors.add(b);

        a.neighbors.add(f);
        f.neighbors.add(a);

        e.neighbors.add(g);
        g.neighbors.add(e);

        f.neighbors.add(h);
        h.neighbors.add(f);

        a.neighbors.add(h);
        h.neighbors.add(a);

        final GraphNode<String> clonedA = new CloneGraph<String>().clone(a);
        // Add breakpoint here to analyze "a" vs. "clonedA"
    }
}
