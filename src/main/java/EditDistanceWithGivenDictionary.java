import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class EditDistanceWithGivenDictionary {

    private List<String> getSteps(final String start, final String goal, final List<String> dictionary) {
        if (!dictionary.contains(start) || !dictionary.contains(goal) ||
                start.length() != goal.length()) {
            throw new IllegalArgumentException();
        }

        return bfs(start, goal, dictionary);
    }

    private List<String> bfs(final String start, final String goal, final List<String> dictionary) {
        final Set<String> visited = new HashSet<>();
        final Queue<String> queue = new LinkedList<>();
        final List<String> steps = new ArrayList<>();

        // Add start to DSes
        visited.add(start);
        steps.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            final String current = queue.remove();

            // Iterate over all neighbors of current
            for (final String neighbor : dictionary) {
                // Skip visited neighbors
                if (visited.contains(neighbor)) {
                    continue;
                }

                // Filters only valid neighbors of current.
                // Valid neighbors are defined as those words which are just
                // one replacement away from current.
                if (!areOneReplacementAway(current, neighbor)) {
                    continue;
                }

                visited.add(neighbor);

                // Add neighbor to path taken by BFS
                steps.add(neighbor);

                // Found goal
                if (neighbor.equals(goal)) {
                    break;
                }

                queue.add(neighbor);
            }
        }

        return steps;
    }

    private boolean areOneReplacementAway(final String s1, final String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }

        int diff = 0;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }

            if (diff > 1) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        EditDistanceWithGivenDictionary edit = new EditDistanceWithGivenDictionary();
        System.out.println(edit.getSteps("dog", "cat",
                Arrays.asList("cat", "cog", "dog", "cag", "dig", "dot", "pin")));
    }
}
