import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WordLadder {

    private int ladderLength(final String start, final String goal, final List<String> dictionary) {
        if (!dictionary.contains(goal)) {
            return 0;
        }

        return bfs(start, goal, dictionary);
    }

    private int bfs(final String start, final String goal, final List<String> dictionary) {
        // 2 because includes start and goal words as well
        int steps = 2;

        final Queue<String> queue = new LinkedList<>();
        addNeighborsToQueue(start, dictionary, queue);

        while (!queue.isEmpty()) {
            // Give equal chance to all {current steps} level neighbors,
            // by iterating over then all at once.
            final int currentQueueSize = queue.size();

            // This is done by removing only those many items from the queue that were
            // at the start of this {current steps amount} level
            for (int i = 0; i < currentQueueSize; i++) {
                final String cur = queue.remove();

                // We have found goal at this level of {current steps}
                if (cur.equals(goal)) {
                    return steps;
                }

                // If not found goal, then add all {current steps + 1} level neighbors of cur
                addNeighborsToQueue(cur, dictionary, queue);
            }

            steps++;
        }

        return 0;
    }

    private void addNeighborsToQueue(final String word, final List<String> dictionary,
            final Queue<String> queue) {

        final List<String> tempDictionary = new ArrayList<>(dictionary);

        for (final String neighbor : tempDictionary) {
            if (areOneReplacementAway(word, neighbor)) {
                queue.add(neighbor);
                // Instead of maintaining a visited array / HashSet, we can simply
                // remove the visited words from the dictionary.
                dictionary.remove(neighbor);
            }
        }
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
        final String start = "hit";
        final String goal = "cog";
        final List<String> dictionary = new ArrayList<String>() {{
            add("hot");
            add("dot");
            add("dog");
            add("lot");
            add("log");
            add("cog");
        }};

        WordLadder edit = new WordLadder();
        System.out.println(edit.ladderLength(start, goal, dictionary));
    }
}
