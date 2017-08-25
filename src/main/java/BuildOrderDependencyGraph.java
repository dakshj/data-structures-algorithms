import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;

/**
 * Created by daksh on 11-Oct-16.
 */
public class BuildOrderDependencyGraph {

    public static void main(String[] args) {
        String[] projectNames = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        String[][] dependencies = {
                {"a", "b"}, {"b", "c"}, {"a", "c"}, {"a", "c"},
                {"d", "e"}, {"b", "d"}, {"e", "f"}, {"a", "f"},
                {"h", "i"}, {"h", "j"}, {"i", "j"}, {"g", "j"}};

        System.out.println(Arrays.toString(
                new BuildOrderDependencyGraph().getBuildOrder(projectNames, dependencies)
                        .toArray()));
    }

    private ArrayList<GraphNode<String>> getBuildOrder(String[] projectNames, String[][] dependencies) {
        Graph<String> graph = createGraph(projectNames, dependencies);
        return getBuildOrder(graph);
    }

    private Graph<String> createGraph(String[] projectNames, String[][] dependencies) {
        Graph<String> graph = new Graph<>();

        for (String projectName : projectNames) {
            graph.addNodeFromData(projectName);
        }

        for (String[] dependency : dependencies) {
            graph.addEdge(dependency[0], dependency[1]);
        }

        return graph;
    }

    private ArrayList<GraphNode<String>> getBuildOrder(Graph<String> graph) {
        ArrayList<GraphNode<String>> buildOrder = new ArrayList<>();

        buildOrder.addAll(getNonDependantNodes(new HashSet<>(graph.nodesMap.values())));

        if (buildOrder.size() == 0) {
            // Cyclic dependency!
            return null;
        }

        for (int i = 0; i < graph.nodesMap.size(); i++) {
            GraphNode<String> node = buildOrder.get(i);

            for (GraphNode<String> depNode : node.dependantsSet) {
                depNode.dependencyCount--;
            }

            buildOrder.addAll(getNonDependantNodes(node.dependantsSet));
        }

        return buildOrder;
    }

    private ArrayList<GraphNode<String>> getNonDependantNodes(HashSet<GraphNode<String>> set) {
        ArrayList<GraphNode<String>> list = new ArrayList<>();

        for (GraphNode<String> node : set) {
            if (!node.built && node.dependencyCount == 0) {
                node.built = true;
                list.add(node);
            }
        }

        Collections.sort(list, new Comparator<GraphNode<String>>() {
            @Override
            public int compare(GraphNode<String> o1, GraphNode<String> o2) {
                return o1.data.compareTo(o2.data);
            }
        });

        return list;
    }

    private static class Graph<T> {

        private LinkedHashMap<T, GraphNode<T>> nodesMap = new LinkedHashMap<>();

        private void addNodeFromData(T data) {
            if (data == null) return;

            if (!nodesMap.containsKey(data)) {
                nodesMap.put(data, new GraphNode<>(data));
            }
        }

        private GraphNode<T> getNodeFromData(T data) {
            return nodesMap.get(data);
        }

        private void addEdge(T startProjectName, T endProjectName) {
            if (startProjectName == null || endProjectName == null) return;

            GraphNode<T> startNode = getNodeFromData(startProjectName);
            GraphNode<T> endNode = getNodeFromData(endProjectName);

            if (startNode == null || endNode == null) return;

            startNode.addDependant(endNode);
        }
    }

    private static class GraphNode<T> {

        private T data;
        private HashSet<GraphNode<T>> dependantsSet = new HashSet<>();
        private int dependencyCount;
        private boolean built;

        private GraphNode(T data) {
            this.data = data;
        }

        private void addDependant(GraphNode<T> dependantNode) {
            if (dependantNode == null) return;

            if (!dependantsSet.contains(dependantNode)) {
                dependantNode.dependencyCount++;
                dependantsSet.add(dependantNode);
            }
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }
}
