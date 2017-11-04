import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import javafx.util.Pair;

class TheMazeII {

    private int shortestDistance(final int[][] maze, final int[] s, final int[] d) {
        if (maze == null || maze.length == 0 || maze[0].length == 0) {
            return -1;
        }

        final int rows = maze.length;
        final int cols = maze[0].length;

        final Point start = new Point(s[0], s[1]);
        final Point dest = new Point(d[0], d[1]);

        // Base case
        if (start.equals(dest)) return 0;

        final int[][] dp = new int[rows][cols];

        // Init dp to have all values set as Integer.MAX_VALUE
        for (int i = 0; i < rows; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        final Point[] directions = new Point[]{
                new Point(-1, 0),   // Up
                new Point(1, 0),    // Down
                new Point(0, -1),   // Left
                new Point(0, 1)     // Right
        };

        // Queue which holds a Point vs. its length
        final Queue<Pair<Point, Integer>> queue = new LinkedList<>();

        queue.add(new Pair<>(start, 0));

        while (!queue.isEmpty()) {
            final Pair<Point, Integer> pair = queue.remove();

            // Do a simultaneous DFS in all four directions of each point removed from the queue
            for (final Point direction : directions) {
                // Use a copy of the point for translating this point later on, **not the original**
                final Point point = (Point) pair.getKey().clone();
                int length = pair.getValue();

                while (point.x >= 0 && point.x < rows &&  // while x is in range AND
                        point.y >= 0 && point.y < cols && // while y is in range AND
                        maze[point.x][point.y] == 0) {    // while this point is not an obstacle

                    // Move in this direction until we hit an obstacle or go out of range.
                    // Note that our coordinates will be of the obstacle or out of range,
                    // so we need to back up!
                    point.translate(direction.x, direction.y);
                    length++;
                }

                // Back up by one point, since we have hit an obstacle or are out of range
                point.translate(-direction.x, -direction.y);
                length--;

                // If this length is more than an already known lesser length to
                // reach the destination, then just don't proceed forward with the DFS later below.
                if (length > dp[dest.x][dest.y]) {
                    continue;
                }

                // If this length is more optimized to reach the same point (x,y),
                // then update dp, and also put it in the queue as an updated (x,y) vs. length Pair.
                if (length < dp[point.x][point.y]) {
                    dp[point.x][point.y] = length;
                    queue.add(new Pair<>(point, length));
                }
            }
        }

        // Finally, if there is an answer, it is stored in the destination's (x,y) coordinate
        if (dp[dest.x][dest.y] != Integer.MAX_VALUE) {
            return dp[dest.x][dest.y];
        } else {
            return -1;
        }
    }
}
