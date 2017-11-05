import java.util.ArrayList;
import java.util.List;

class SumPowersOf2Paths {

    private List<List<Integer>> powersOf2Paths(final int n) {
        final List<List<Integer>> result = new ArrayList<>();
        final List<Integer> currentResult = new ArrayList<>();

        // Start path from 0
        currentResult.add(0);

        powersOf2Paths(n, 0, result, currentResult);
        return result;
    }

    private void powersOf2Paths(final int n, final int curr,
            final List<List<Integer>> result, final List<Integer> currentResult) {
        if (curr == n) {
            result.add(new ArrayList<>(currentResult));
            // Not necessary to return here, because the curr is big enough to make the
            // below for-loop *not* execute
            return;
        }

        for (int i = 0; (curr + (1 << i)) <= n; i++) {
            final int sum = curr + (1 << i);
            // Add to the current result for the purpose of building a path
            currentResult.add(sum);

            powersOf2Paths(n, sum, result, currentResult);

            // Done with the DFS, so remove this path element from currentResult
            currentResult.remove(currentResult.size() - 1);
        }
    }
}
