import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class TheMaze {

    private boolean hasPath(final int[][] maze, final int[] s, final int[] d) {
        int rows = maze.length, cols = maze[0].length;

        final Point start = new Point(s[0], s[1]);
        final Point dest = new Point(d[0], d[1]);

        // Base case
        if (start.equals(dest)) return true;

        final Point[] directions = new Point[]{
                new Point(-1, 0),   // Up
                new Point(1, 0),    // Down
                new Point(0, -1),   // Left
                new Point(0, 1)     // Right
        };

        final HashSet<Point> visited = new HashSet<>();

        final Queue<Point> queue = new LinkedList<>();

        visited.add(start);

        queue.add(start);

        while (!queue.isEmpty()) {
            final Point originalPoint = queue.remove();

            // Do a simultaneous DFS in all four directions of each point removed from the queue
            for (final Point direction : directions) {
                final Point point = (Point) originalPoint.clone();

                while (point.x >= 0 && point.x < rows &&  // while x is in range AND
                        point.y >= 0 && point.y < cols && // while y is in range AND
                        maze[point.x][point.y] == 0) {    // while this point is not an obstacle

                    // Move in this direction until we hit an obstacle or go out of range.
                    // Note that our coordinates will be of the obstacle or out of range,
                    // so we need to back up!
                    point.translate(direction.x, direction.y);
                }

                // Back up by one point, since we have hit an obstacle or are out of range
                point.translate(-direction.x, -direction.y);

                // If this point has been visited before from some other directional DFS,
                // then no need to do a DFS again on it (in the later code below.
                if (visited.contains(point)) {
                    continue;
                }

                // Else, mark this point as visited,
                // and if not at the destination, do a DFS in the next queue round.
                visited.add(point);

                if (point.equals(dest)) {
                    // We were able to reach the destination
                    return true;
                }

                // If not yet at the destination, add this point to the queue for further DFS
                queue.add(point);
            }
        }

        return false;
    }
}
