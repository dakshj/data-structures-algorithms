import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class NClosestPointsToTarget {

    private List<Point> get(final List<Point> points, final Point target, final int n) {
        final List<Point> result = new ArrayList<>();

        if (points.isEmpty()) return result;

        if (n > points.size()) {
            throw new IllegalArgumentException("n is greater than list of points");
        }

        final PriorityQueue<Point> pq = new PriorityQueue<>(
                Comparator.comparingDouble(p -> p.distance(target)));

        pq.addAll(points);

        for (int i = 0; i < n; i++) {
            result.add(pq.remove());
        }

        return result;
    }

    private static class Point extends java.awt.Point {
        private Point(final int x, final int y) {
            super(x, y);
        }

        @Override
        public String toString() {
            return String.format("{%s,%s}", x, y);
        }
    }

    /**
     * Output: [{18,6}, {12,8}, {9,14}, {33,11}]
     */
    public static void main(String[] args) {
        System.out.println(new NClosestPointsToTarget().get(Arrays.asList(
                new Point(7, 3), new Point(2, 4), new Point(1, 15), new Point(9, 14),
                new Point(12, 8), new Point(1, 0), new Point(18, 6), new Point(33, 11)
        ), new Point(21, 16), 4));
    }
}
