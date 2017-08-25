import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by daksh on 19-Oct-16.
 */
public class PathAvoidObstacles {

    public static void main(String[] args) {
        int[][] maze = new int[][]
                {
                        {1, 0, 0, 0, 0, 0, 0},
                        {1, 1, 1, 0, 0, 0, 0},
                        {1, 0, 1, 0, 0, 0, 0},
                        {1, 0, 1, 1, 1, 0, 0},
                        {1, 0, 0, 0, 1, 0, 0},
                        {1, 0, 0, 0, 1, 0, 0},
                        {1, 0, 0, 0, 1, 1, 1}
                };

        System.out.println(Arrays.toString(new PathAvoidObstacles().getPath(maze).toArray()));
    }

    private List<Point> getPath(int[][] maze) {
        if (maze == null || maze.length == 0) return null;

        List<Point> path = new ArrayList<>();
        Set<Point> failedPoints = new HashSet<>();

        return getPath(maze, maze.length - 1, maze[0].length - 1, path, failedPoints) ? path : null;
    }

    private boolean getPath(int[][] maze, int row, int col, List<Point> path, Set<Point> failedPoints) {
        if (row < 0 || col < 0 || maze[row][col] == 0) return false;

        Point point = new Point(row, col);

        if (failedPoints.contains(point)) {
            return false;
        }

        boolean topLeft = row == 0 && col == 0;

        if (topLeft ||
                getPath(maze, row - 1, col, path, failedPoints) ||
                getPath(maze, row, col - 1, path, failedPoints)) {
            path.add(point);
            return true;
        }

        failedPoints.add(point);
        return false;
    }

    private static class Point {
        int row;
        int col;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "(" + row + "," + col + ")";
        }
    }
}
