import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

class ShortestPathFromFirstToLastRow {

    private List<Point> getShortestPath(final int[][] weightedMatrix) {
        // Validate matrix
        if (weightedMatrix == null || weightedMatrix.length == 0 || weightedMatrix[0].length == 0) {
            return null;
        }

        // Maintain Set of visited Points
        final Set<Point> visited = new HashSet<>();

        // Each Node of the queue needs to have:
        // 1. its coordinates
        // 2. The cost to reach to it
        // 3. A link to its parent
        //
        // Sort via cost using PriorityQueue's Comparator
        final PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparing(O -> O.cost));

        // Add valid elements of first row into queue.
        // This is done because the BFS question implies multiple possible start points
        // (i.e. any valid element of the first row).
        for (int col = 0; col < weightedMatrix[0].length; col++) {
            final int cost = weightedMatrix[0][col];
            if (cost != 0) {
                final Point point = new Point(0, col);

                // Add to queue
                queue.add(new Node(point, null, cost));

                // Mark as visited
                visited.add(point);
            }
        }

        while (!queue.isEmpty()) {
            // Add neighbors of all Nodes at this current BFS level
            final int size = queue.size();
            for (int i = 0; i < size; i++) {
                // This is one node out of the many nodes at this BFS level
                final Node node = queue.remove();

                // Reached destination (i.e. the last row)
                if (node.coord.x == weightedMatrix.length - 1) {
                    return generatePathFromNode(node);
                }

                final List<Node> neighbors = getNeighbors(node, weightedMatrix);

                for (final Node neighbor : neighbors) {
                    if (!visited.contains(neighbor.coord)) {

                        // Add to queue
                        queue.add(neighbor);

                        // Mark as visited
                        visited.add(neighbor.coord);
                    }
                }
            }
        }

        return new ArrayList<>();
    }

    /**
     * Gets neighbors of a node in the four directions: Up, Down, Left, Right.
     * Each neighbor should have a valid weight to it (i.e., 0 is an invalid weight).
     */
    private List<Node> getNeighbors(final Node node, final int[][] weightedMatrix) {
        final List<Node> neighbors = new ArrayList<>();

        final List<Point> directions = new ArrayList<Point>() {{
            add(new Point(1, 0)); // Down
            add(new Point(-1, 0)); // Up
            add(new Point(0, 1)); // Right
            add(new Point(0, -1)); // Left
        }};

        for (final Point dir : directions) {
            final Point newCoord = new Point(node.coord.x + dir.x, node.coord.y + dir.y);

            if (newCoord.x > 0 && newCoord.x < weightedMatrix.length &&
                    newCoord.y > 0 && newCoord.y < weightedMatrix[0].length &&
                    weightedMatrix[newCoord.x][newCoord.y] != 0) {
                final Node next = new Node(newCoord, node, weightedMatrix[newCoord.x][newCoord.y]);
                neighbors.add(next);
            }
        }

        return neighbors;
    }

    /**
     * Generates a path using a Node, and then using the link to that Node's parent
     */
    private List<Point> generatePathFromNode(Node node) {
        Node copy = node;

        final List<Point> result = new ArrayList<>();

        while (copy != null) {
            // Add to front of path list so that it appears
            // first row -> last row (and not vice versa)
            result.add(0, copy.coord);
            copy = copy.parent;
        }

        return result;
    }

    private static class Node {
        private final Point coord;
        private final Node parent;
        private final int cost;

        private Node(final Point coord, final Node parent, final int cost) {
            this.coord = coord;
            this.parent = parent;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        final int[][] weightedMatrix = new int[][]{
                {93, 8, 0, 28, 39},
                {73, 9, 99, 0, 13},
                {0, 95, 59, 67, 41},
                {42, 0, 17, 23, 41},
                {19, 19, 1, 29, 0},
                {50, 47, 34, 36, 32}
        };

        System.out.println(new ShortestPathFromFirstToLastRow().getShortestPath(weightedMatrix));
    }
}
