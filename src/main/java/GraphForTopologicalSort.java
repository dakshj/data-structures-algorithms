import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GraphForTopologicalSort {

    public static void main(String[] args) {
        // Create nodes
        Node<String> a = new Node<>("a");
        Node<String> b = new Node<>("b");
        Node<String> c = new Node<>("c");
        Node<String> d = new Node<>("d");
        Node<String> e = new Node<>("e");
        Node<String> f = new Node<>("f");
        Node<String> g = new Node<>("g");

        // Add dependencies
        e.addEdge(f);
        f.addEdge(d);
        c.addEdge(a);
        b.addEdge(d);
        d.addEdge(g).addEdge(a);
        a.addEdge(g);

        // Final list of nodes
        List<Node<String>> unsortedNodes = Arrays.asList(a, b, c, d, e, f, g);

        System.out.println(new GraphForTopologicalSort().sort(unsortedNodes));
    }

    private List<Node<String>> sort(List<Node<String>> unsortedNodes) {
        List<Node<String>> sortedNodes = new ArrayList<>();

        // Transfer initially independent nodes to queue
        List<Node<String>> independentNodes = getIndependentNodes(unsortedNodes);

        while (!independentNodes.isEmpty()) {
            // Get first node in the independentNodes list
            Node<String> removedNode = independentNodes.remove(0);

            // Add this to the final list of sorted nodes
            sortedNodes.add(removedNode);

            // Iterate over all the outNodes of the currently removed node
            for (Node<String> outNode : removedNode.outNodes) {
                // Remove current node from the in-nodes list of all out-nodes, because it has
                // already been processed
                outNode.inNodes.remove(removedNode);

                // If now that the current outNode has no inNodes
                if (outNode.inNodes.isEmpty()) {
                    // Then it can be added to the list of independentNodes
                    independentNodes.add(outNode);
                }
            }
        }

        for (Node<String> node : unsortedNodes) {
            // If there still exist any nodes where they have pending inNode dependencies
            if (!node.inNodes.isEmpty()) {
                // Then we have found a cycle in the DAG, which is an illegal state for it
                throw new IllegalStateException("Cycled detected in the DAG!");
            }
        }

        return sortedNodes;
    }

    private List<Node<String>> getIndependentNodes(List<Node<String>> unsortedNodes) {
        // [MEMORIZE]
        List<Node<String>> independentNodes =
                unsortedNodes
                        .stream()
                        .filter(node -> node.inNodes.isEmpty())
                        .collect(Collectors.toList());

        // Sort the initially occurring independent nodes
        Collections.sort(independentNodes, (o1, o2) -> o1.data.compareTo(o2.data));

        return independentNodes;
    }

    private static class Node<T> {
        private T data;
        private List<Node<T>> outNodes;
        private List<Node<T>> inNodes;

        private Node(T data) {
            this.data = data;
            outNodes = new ArrayList<>();
            inNodes = new ArrayList<>();
        }

        private Node<T> addEdge(Node<T> to) {
            outNodes.add(to);
            to.inNodes.add(this);
            return this;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }
}
